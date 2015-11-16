package com.sgem.controladores;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.sgem.datatypes.DataCompetencia;
import com.sgem.datatypes.DataCompraEntrada;
import com.sgem.datatypes.DataDeportista;
import com.sgem.datatypes.DataEstadistica;
import com.sgem.datatypes.DataImagen;
import com.sgem.datatypes.DataJuez;
import com.sgem.datatypes.DataPais;
import com.sgem.datatypes.DataResultado;
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
			DataCompetencia c = new DataCompetencia();
			List<DataDeportista> deportistas = new ArrayList<DataDeportista>();
							
			for(Deportista d : competencias.get(i).getDeportistas()){
				deportistas.add(convertirDeportista(d,competencias.get(i).getEventoDeportivo().getNombreDeporte()));
			}		
			
			c.setDeportistas(deportistas);
			c.setJuez(convertirJuez(competencias.get(i).getJuez()));
			c.setNombreDeporte(competencias.get(i).getEventoDeportivo().getNombreDeporte());
			c.setNombreDisciplina(competencias.get(i).getEventoDeportivo().getDisciplina());
			c.setSexo(competencias.get(i).getEventoDeportivo().getSexo());
			c.setRonda(competencias.get(i).getRonda().getNumeroRonda());
			c.setTenantId(competencias.get(i).getTenantId());
			c.setEstadio(competencias.get(i).getEstadio());
			c.setCantEntradas(competencias.get(i).getCantEntradas());
			c.setFecha(competencias.get(i).getFecha());
			c.setPrecioEntrada(competencias.get(i).getPrecioEntrada());
			c.setIdCompetencia(competencias.get(i).getCompetenciaId());
			c.setEntradasVendidas(competencias.get(i).getEntradasVendidas());
			c.setTipoDeporte(competencias.get(i).getEventoDeportivo().getTipo());
			c.setFinalizada(competencias.get(i).isFinalizada());
			dataCompetencias.add(c);			
		}
		
		
		return dataCompetencias;
	}

	private DataDeportista convertirDeportista(Deportista d, String deporte) {		
		DataImagen di;

		if(d.getFoto() != null){			
			di = new DataImagen(d.getFoto().getMime(), d.getFoto().getRuta(), d.getFoto().getTenantId());
		}else{
			di = new DataImagen("","", 1);
		}
		
		DataPais pais = new DataPais(d.getComiteOlimpico().getPais().getPaisID(), d.getComiteOlimpico().getPais().getPais(),  d.getComiteOlimpico().getPais().getCiudad());
		
		return new DataDeportista(d.getTenantID(),d.getDeportistaID(),d.getNombre(),d.getApellido(),d.getSexo(),
								  d.getFechaNac(),pais,deporte,new ArrayList<String>(), di);
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
		competencia.setFinalizada(true);
		
		CompetenciaDAO.modificarCompetencia(competencia);
		
		Resultado r = new Resultado(resultado.getTenantId(), estadisticas, competencia);
		guardo = ResultadoDAO.guardarResultado(r);
		
		Iterator<Estadistica> it = estadisticas.iterator();		
		while(it.hasNext()){
			Estadistica e = it.next();
			e.setResultado(r);
			if (!EstadisticaDAO.modificarEstadistica(e)) {
				throw new AplicacionException("Error al guardar el resultado. Error al guardar estadistica");
			} 	
		}
		
		return guardo;
		
	}

	private Estadistica convertirEstadistica(DataEstadistica e,Deportista deportista) {			
		return new Estadistica(e.getTenantId(),e.getPosicion(),e.getDatoInformativo(),deportista,null);	
	}
	
}

