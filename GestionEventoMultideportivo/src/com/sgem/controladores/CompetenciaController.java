package com.sgem.controladores;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.sgem.datatypes.DataComite;
import com.sgem.datatypes.DataCompetencia;
import com.sgem.datatypes.DataCompraEntrada;
import com.sgem.datatypes.DataDeportista;
import com.sgem.datatypes.DataEstadistica;
import com.sgem.datatypes.DataImagen;
import com.sgem.datatypes.DataJuez;
import com.sgem.datatypes.DataPais;
import com.sgem.datatypes.DataResultado;
import com.sgem.dominio.ComiteOlimpico;
import com.sgem.dominio.Competencia;
import com.sgem.dominio.Deportista;
import com.sgem.dominio.Entrada;
import com.sgem.dominio.Estadistica;
import com.sgem.dominio.EventoDeportivo;
import com.sgem.dominio.Juez;
import com.sgem.dominio.Resultado;
import com.sgem.dominio.Ronda;
import com.sgem.dominio.UsuarioComun;
import com.sgem.persistencia.ICompetenciaDAO;
import com.sgem.persistencia.IDeportistaDAO;
import com.sgem.persistencia.IEntradaDAO;
import com.sgem.persistencia.IEstadisticaDAO;
import com.sgem.persistencia.IEventoDeportivoDAO;
import com.sgem.persistencia.IResultadoDAO;
import com.sgem.persistencia.IRondaDAO;
import com.sgem.persistencia.IUsuarioDAO;
import com.sgem.seguridad.excepciones.AplicacionException;

@Stateless
public class CompetenciaController implements ICompetenciaController {

	@EJB
	private ICompetenciaDAO CompetenciaDAO;
	
	@EJB
	private IEventoDeportivoDAO EventosDeportivosDAO;
	
	@EJB
	private IUsuarioDAO UsuarioDAO;
	
	@EJB
	private IDeportistaDAO DeportistaDAO;
	
	@EJB
	private IEntradaDAO EntradaDAO;
	
	@EJB
	private IRondaDAO RondaDAO;
	
	@EJB
	private IEstadisticaDAO EstadisticaDAO;
		
	@EJB
	private IResultadoDAO ResultadoDAO;
	
	@EJB
	private IDeportistaController  idc;

	@Override
	public Boolean guardarCompetencia(DataCompetencia dataCompetencia) {
		
		
		int tenantID = dataCompetencia.getTenantId();
		String nombreDeporte = dataCompetencia.getNombreDeporte();
		String nombreDisciplina = dataCompetencia.getNombreDisciplina();
		String sexo = dataCompetencia.getSexo();
		int numeroRonda = dataCompetencia.getRonda();
		int tenantidJuez = dataCompetencia.getJuez().getTenantId();
		String mailJuez =  dataCompetencia.getJuez().getEmail();
		List<DataDeportista> listaDeportistas = dataCompetencia.getDeportistas();
		
				
		
		int idEventoDep = EventosDeportivosDAO.traerIDEventoDeportivo(tenantID, nombreDeporte, nombreDisciplina, sexo);
		
		EventoDeportivo ed = EventosDeportivosDAO.traerEventoDeportivo(idEventoDep);
		
		Ronda r = RondaDAO.buscarRonda(tenantID,idEventoDep,numeroRonda);
		
		Juez j = (Juez) UsuarioDAO.buscarUsuario(tenantidJuez, mailJuez, "Juez");
		
		List<Deportista> deportistas = idc.listarDeportistas(listaDeportistas);
		

		
		Competencia competencia = new Competencia();
	
		competencia.setTenantId(dataCompetencia.getTenantId());
		competencia.setCantEntradas(dataCompetencia.getCantEntradas());
		competencia.setEntradasVendidas(0);
		competencia.setEstadio(dataCompetencia.getEstadio());
		competencia.setFecha(dataCompetencia.getFecha());
		competencia.setPrecioEntrada(dataCompetencia.getPrecioEntrada());
		competencia.setJuez(j);
		competencia.setRonda(r);
		competencia.setEventoDeportivo(ed);
		competencia.setPuesto(dataCompetencia.getPuesto());
		
		
			for(int i = 1; i< (dataCompetencia.getCantEntradas()+1); i++){
			
			Entrada e = new Entrada();
			e.setCompetencia(competencia);
			e.setTenantId(dataCompetencia.getTenantId());
			e.setFecha(dataCompetencia.getFecha());
			e.setNumeroAsiento(i);
			e.setPrecioEntrada(dataCompetencia.getPrecioEntrada());
			e.setVendida(false);
			
			EntradaDAO.guardarEntrada(e);
			
			competencia.addEntrada(e);
			
			} 
			
			for(int i = 0; i< deportistas.size(); i++){
				
				
//				deportistas.get(i).addCompetencia(competencia);
				competencia.addDeportista(deportistas.get(i));
				
				} 
	
		
		return CompetenciaDAO.guardarCompetencia(competencia);
		
			
		
		
	}

	@Override
	public List<DataCompetencia> listarCompetenciasPorRonda(int tenantID, String nombreDeporte, String sexo,String nombreDisciplina, int ronda) {
		try {
			
			int idEventoDep = EventosDeportivosDAO.traerIDEventoDeportivo(tenantID, nombreDeporte, nombreDisciplina, sexo);
			
			return convertirListaCompetencias(CompetenciaDAO.listarCompetenciasPorRonda(tenantID, idEventoDep , ronda));
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<DataCompetencia> listarCompetenciasPendientes(int tenantID, int juezID) throws AplicacionException {
		try{
			return convertirListaCompetencias(CompetenciaDAO.listarCompetenciasPendientes(tenantID,juezID));
		}catch(Exception e){
			e.printStackTrace();
			throw new AplicacionException("Error obteniendo competencias.");
		}
	}
	
	private List<DataCompetencia> convertirListaCompetencias(List<Competencia> competencias) {			
		List<DataCompetencia> dataCompetencias = new ArrayList<DataCompetencia>();
		
		for(int i = 0; i< competencias.size(); i++){
			DataCompetencia c = convertirCompetencia(competencias.get(i));
			dataCompetencias.add(c);			
		}
		
		return dataCompetencias;
	}

	private DataDeportista convertirDeportista(Deportista d) {		
		DataImagen di;

		if(d.getFoto() != null){			
			di = new DataImagen(d.getFoto().getMime(), d.getFoto().getRuta(), d.getFoto().getTenantId());
		}else{
			di = new DataImagen("","", 1);
		}
		
		DataPais pais = new DataPais(d.getComiteOlimpico().getPais().getPaisID(), d.getComiteOlimpico().getPais().getPais(), d.getComiteOlimpico().getPais().getCiudad());
		DataComite dc = new DataComite(d.getComiteOlimpico().getEmail(), "", d.getComiteOlimpico().getCodigo(),
				pais, d.getComiteOlimpico().getFacebook(), d.getComiteOlimpico().getTwitter(), d.getComiteOlimpico().getPaypal(), 
				d.getComiteOlimpico().getTenantID(),d.getComiteOlimpico().getId().intValue(), UsuarioController.USUARIO_COMITE,d.getComiteOlimpico().getPuesto1(),
				d.getComiteOlimpico().getPuesto2(),d.getComiteOlimpico().getPuesto3());
		dc.setLogo(new DataImagen(d.getComiteOlimpico().getLogo().getMime(), d.getComiteOlimpico().getLogo().getRuta(), d.getComiteOlimpico().getLogo().getTenantId()));
		return new DataDeportista(d.getTenantID(),d.getDeportistaID(),d.getNombre(),d.getApellido(),d.getSexo(),
								  d.getFechaNac(),dc,di);
	}

	@Override
	public float obtenerPrecio(int tenantID, int idCompetencia) {
		float precio = 0;
	try {
			
			 precio = CompetenciaDAO.obtenerPrecio(tenantID, idCompetencia);
			
			} catch (Exception e) {
			e.printStackTrace();
		}
				
		return precio;
	}

	@Override
	public boolean comprarEntradas(DataCompraEntrada datos) {
		
		String mail = datos.getMail();
		int cantEntradas = datos.getCantEntradas();
		int tenantId = datos.getTenantId();
		int idCompetencia = datos.getIdCompetencia();
		
		UsuarioComun u = (UsuarioComun) UsuarioDAO.buscarUsuario(tenantId, mail, "UsuarioComun");
		
		Competencia c = CompetenciaDAO.buscarCompetencia(tenantId, idCompetencia);
		
		List<Entrada> entradas = EntradaDAO.listarEntradas(tenantId, idCompetencia);
		
		
		int entradasActuales = c.getEntradasVendidas();
		
		int entradasConCompra = entradasActuales + cantEntradas;
		
		c.setEntradasVendidas(entradasConCompra);
		
		for(int i = 0; i< cantEntradas; i++){
					entradas.get(i).setUsuarioComun(u);
					entradas.get(i).setVendida(true);	
					
					u.addEntrada(entradas.get(i));
		}
		
		

		return EntradaDAO.guardarCompra(u,c);
				
				
	}
	
	private DataJuez convertirJuez(Juez juez){
		
		return new DataJuez(juez.getTenantID(),juez.getNombre(),juez.getApellido(),"",juez.getEmail(),"",juez.getId().intValue());
		
	}

	@Override
	public boolean guardarResultado(DataResultado resultado) throws AplicacionException {
	
		boolean guardo = false;
		Deportista primero = new Deportista();
		Deportista segundo = new Deportista();
		Deportista tercero = new Deportista();
		List<String> comites = new ArrayList<String>();
		
		Set<Estadistica> estadisticas = new HashSet<Estadistica>();
		for(DataEstadistica e : resultado.getEstadisticas()){
			Deportista deportista = DeportistaDAO.buscarDeportista(e.getDeportista().getDeportistaID());
			Estadistica estadistica = convertirEstadistica(e,deportista);
			
			if (!EstadisticaDAO.guardarEstadistica(estadistica)) {
				throw new AplicacionException("Error al guardar el resultado. Error al guardar estadistica");
			} 			
			
			deportista.addEstadistica(estadistica);
			if (!DeportistaDAO.modificarDeportista(deportista)){
				throw new AplicacionException("Error al guardar el deportista. Error al guardar estadistica");
			} 
			estadisticas.add(estadistica);
		}
		
		Competencia competencia = CompetenciaDAO.buscarCompetencia(resultado.getTenantId(), resultado.getCompetencia().getIdCompetencia());
		
		Resultado r = new Resultado(resultado.getTenantId(), estadisticas, competencia);
		guardo = ResultadoDAO.guardarResultado(r);
		
		Iterator<Estadistica> it = estadisticas.iterator();		
		while(it.hasNext()){
			Estadistica e = it.next();
			e.setResultado(r);
			
			if (!EstadisticaDAO.modificarEstadistica(e)) {
				throw new AplicacionException("Error al guardar el resultado. Error al guardar estadistica");
			} 	
			
			if(e.getPosicion() == 1){
				primero = e.getDeportista();
			}else if(e.getPosicion() == 2){
				segundo = e.getDeportista();
			}else{
				tercero = e.getDeportista();
			}
			
			if(comites.indexOf(e.getDeportista().getComiteOlimpico().getCodigo()) == -1){
				comites.add(e.getDeportista().getComiteOlimpico().getCodigo());
			}
		}
				
		
		if(competencia.getEventoDeportivo().getTipo().equals("individual") && competencia.getPuesto() == 1){
			
			if(competencia.getDeportistas().size() > 2){
				primero.getComiteOlimpico().setPuesto1(primero.getComiteOlimpico().getPuesto1()+1);
				segundo.getComiteOlimpico().setPuesto2(segundo.getComiteOlimpico().getPuesto2()+1);
				tercero.getComiteOlimpico().setPuesto3(tercero.getComiteOlimpico().getPuesto3()+1);				
			}else{// es mano a mano
				primero.getComiteOlimpico().setPuesto1(primero.getComiteOlimpico().getPuesto1()+1);
				segundo.getComiteOlimpico().setPuesto2(segundo.getComiteOlimpico().getPuesto2()+1);
			}
			
		} else if(competencia.getEventoDeportivo().getTipo().equals("colectivo") && competencia.getPuesto() == 1){
			
			if(comites.size() > 2){
				primero.getComiteOlimpico().setPuesto1(primero.getComiteOlimpico().getPuesto1()+1);
				segundo.getComiteOlimpico().setPuesto2(segundo.getComiteOlimpico().getPuesto2()+1);
				tercero.getComiteOlimpico().setPuesto3(tercero.getComiteOlimpico().getPuesto3()+1);				
			}else{// es mano a mano
				primero.getComiteOlimpico().setPuesto1(primero.getComiteOlimpico().getPuesto1()+1);
				segundo.getComiteOlimpico().setPuesto2(segundo.getComiteOlimpico().getPuesto2()+1);
			}
			
		} else if (competencia.getEventoDeportivo().getTipo().equals("individual") && competencia.getPuesto() == 3 
				|| competencia.getEventoDeportivo().getTipo().equals("colectivo") && competencia.getPuesto() == 3){
			
			primero.getComiteOlimpico().setPuesto3(primero.getComiteOlimpico().getPuesto3()+1);
			
		}
				
		competencia.setFinalizada(true);
		competencia.setResultado(r);
		CompetenciaDAO.modificarCompetencia(competencia);
		
		return guardo;
		
	}

	private Estadistica convertirEstadistica(DataEstadistica e,Deportista deportista) {			
		return new Estadistica(e.getTenantId(),e.getPosicion(),e.getDatoInformativo(),deportista,null);	
	}

	@Override
	public List<DataCompetencia> listarCompetenciasPorDisciplina(int tenantID, String nombreDeporte, String nombreDisciplina, String sexo) throws AplicacionException {
		try{
			return convertirListaCompetencias(CompetenciaDAO.listarCompetenciasPorDisciplina(tenantID,nombreDeporte,nombreDisciplina,sexo));
		}catch(Exception e){
			e.printStackTrace();
			throw new AplicacionException("Error obteniendo competencias.");
		}
	}

	private List<DataResultado> convertirListaResultados(List<Resultado> resultados) {
		List<DataResultado> dataResultados = new ArrayList<DataResultado>();
				
		for(int i = 0; i< resultados.size(); i++){
			DataResultado dr = new DataResultado();
			
			dr.setResultadoId(resultados.get(i).getResultadoId());
			dr.setTenantId(resultados.get(i).getTenantId());			
			dr.setCompetencia(convertirCompetencia(resultados.get(i).getCompetencia()));
			dr.setEstadisticas(convertirListaEstadisticas(resultados.get(i).getEstadisticas()));
			
			dataResultados.add(dr);			
		}		
		
		return dataResultados;
	}
	
	private List<DataEstadistica> convertirListaEstadisticas(Set<Estadistica> setEstadisticas) {
		List<DataEstadistica> dataEstadisticas = new ArrayList<DataEstadistica>();
		List<Estadistica> estadisticas = new ArrayList<Estadistica>();
		
		Iterator<Estadistica> it = setEstadisticas.iterator();
		
		while(it.hasNext()){
			estadisticas.add(it.next());
		}
		
		for(int i = 0; i< estadisticas.size(); i++){
			DataEstadistica de = convertirEstadistica(estadisticas.get(i));
			dataEstadisticas.add(de);			
		}
		
		return dataEstadisticas;
	}

	private DataEstadistica convertirEstadistica(Estadistica estadistica) {
		DataEstadistica de = new DataEstadistica();
		
		de.setDatoInformativo(estadistica.getDatoInformativo());
		de.setDeportista(convertirDeportista(estadistica.getDeportista()));
		de.setEstadisticaId(estadistica.getEstadisticaId());
		de.setPosicion(estadistica.getPosicion());
		de.setResultadoId(estadistica.getResultado().getResultadoId());
		de.setTenantId(estadistica.getTenantId());
		
		return de;
	}

	public DataCompetencia convertirCompetencia(Competencia competencia){
		DataCompetencia c = new DataCompetencia();
		List<DataDeportista> deportistas = new ArrayList<DataDeportista>();
						
		for(Deportista d : competencia.getDeportistas()){
			deportistas.add(convertirDeportista(d));
		}		
		
		c.setDeportistas(deportistas);
		c.setJuez(convertirJuez(competencia.getJuez()));
		c.setNombreDeporte(competencia.getEventoDeportivo().getNombreDeporte());
		c.setNombreDisciplina(competencia.getEventoDeportivo().getDisciplina());
		c.setSexo(competencia.getEventoDeportivo().getSexo());
		c.setRonda(competencia.getRonda().getNumeroRonda());
		c.setTenantId(competencia.getTenantId());
		c.setEstadio(competencia.getEstadio());
		c.setCantEntradas(competencia.getCantEntradas());
		c.setFecha(competencia.getFecha());
		c.setPrecioEntrada(competencia.getPrecioEntrada());
		c.setIdCompetencia(competencia.getCompetenciaId());
		c.setEntradasVendidas(competencia.getEntradasVendidas());
		c.setTipoDeporte(competencia.getEventoDeportivo().getTipo());
		c.setFinalizada(competencia.isFinalizada());
		c.setPuesto(competencia.getPuesto());
		
		return c;
	}

	@Override
	public DataResultado listarResultadosCompetencia(int tenantID, int competenciaID) {
		
		 return convertirResultado(ResultadoDAO.traerResultado(tenantID,competenciaID));
		
	}
	
	private DataResultado convertirResultado(Resultado r) {		
	
		DataResultado dr = new DataResultado();
		
		dr.setCompetencia(convertirCompetencia(r.getCompetencia()));
		dr.setResultadoId(r.getResultadoId());
		dr.setTenantId(r.getTenantId());
		dr.setEstadisticas(convertirListaEstadisticas(r.getEstadisticas()));
		
		return dr;
		
	}

	@Override
	public DataEstadistica listarEstadisticaPorPais(int tenantID, int competenciaID, int comiteID) throws AplicacionException {
		try{
			return convertirEstadistica(EstadisticaDAO.buscarEstadisticaPorPais(tenantID, competenciaID,comiteID));
		}catch(Exception e){
			e.printStackTrace();
			throw new AplicacionException("Error al obtener estadistica para la competencia nro.: "+competenciaID, e);
		}
	}
	
	
}

