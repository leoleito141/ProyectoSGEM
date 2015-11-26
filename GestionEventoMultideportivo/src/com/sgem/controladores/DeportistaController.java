package com.sgem.controladores;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;

import org.apache.commons.io.IOUtils;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import com.sgem.datatypes.DataBusquedaDeportista;
import com.sgem.datatypes.DataComite;
import com.sgem.datatypes.DataCompetencia;
import com.sgem.datatypes.DataDeportista;
import com.sgem.datatypes.DataEstadistica;
import com.sgem.datatypes.DataEventoDeportivo;
import com.sgem.datatypes.DataImagen;
import com.sgem.datatypes.DataJuez;
import com.sgem.datatypes.DataPais;
import com.sgem.dominio.ComiteOlimpico;
import com.sgem.dominio.Competencia;
import com.sgem.dominio.Deportista;
import com.sgem.dominio.Estadistica;
import com.sgem.dominio.EventoDeportivo;
import com.sgem.dominio.Imagen;
import com.sgem.dominio.Juez;
import com.sgem.persistencia.IDeportistaDAO;
import com.sgem.persistencia.IImagenDAO;
import com.sgem.seguridad.excepciones.AplicacionException;
import com.sgem.utilidades.ImagenUtil;



@Stateless
public class DeportistaController implements IDeportistaController {

	@EJB
	private IDeportistaDAO DeportistaDAO;
	
	@EJB
	private IUsuarioController iuc;
	
	@EJB
	private IEventoDeportivoController iedc;
	
	@EJB
	private IImagenDAO ImagenDAO;
		
	
	private Set<DataEventoDeportivo> convertirEventosDeportivos(Set<EventoDeportivo> deportes) {
		Set<DataEventoDeportivo> listaDeportas = new HashSet<DataEventoDeportivo>();
		for(EventoDeportivo ev : deportes){
			
			DataEventoDeportivo ed = new DataEventoDeportivo();
			
			ed.setCantRondas(ev.getRonda().size());
			ed.setFechaFin(ev.getFechaFin());
			ed.setFechaInicio(ev.getFechaInicio());
			ed.setNombreDeporte(ev.getNombreDeporte());
			ed.setNombreDisciplina(ev.getDisciplina() == null ? "" : ev.getDisciplina() );
			ed.setSexo(ev.getSexo());
			ed.setTenantId(ev.getTenantId());
			ed.setTipo(ev.getTipo());
			
			listaDeportas.add(ed);	
			
			
		}
		
		
		return listaDeportas;		
	}
	
	private Set<DataEstadistica> convertirEstadisticas(Set<Estadistica> estadisticas) {
		Set<DataEstadistica> listaEstadisticas = new HashSet<DataEstadistica>();
		for(Estadistica es : estadisticas){
			DataEstadistica de = new DataEstadistica();
			
			de.setEstadisticaId(es.getEstadisticaId());
			de.setResultadoId(es.getResultado().getResultadoId());
			de.setTenantId(es.getTenantId());
			de.setPosicion(es.getPosicion());
			de.setDatoInformativo(es.getDatoInformativo());
			
			listaEstadisticas.add(de);	
			
		}
		
		
		return listaEstadisticas;		
	}
	
	private DataJuez convertirJuez(Juez juez){
		
		return new DataJuez(juez.getTenantID(),juez.getNombre(),juez.getApellido(),"",juez.getEmail(),"",juez.getId().intValue());
		
	}

	private Set<DataCompetencia> convertirListaCompetencias(Set<Competencia> competencias) {
			
			Set<DataCompetencia> dataCompetencias = new HashSet<DataCompetencia>();
			for(Competencia co : competencias){
				DataCompetencia c = new DataCompetencia();
				
				c.setJuez(convertirJuez(co.getJuez()));
				c.setNombreDeporte(co.getEventoDeportivo().getNombreDeporte());
				c.setNombreDisciplina(co.getEventoDeportivo().getDisciplina());
				c.setSexo(co.getEventoDeportivo().getSexo());
				c.setRonda(co.getRonda().getNumeroRonda());
				c.setTenantId(co.getTenantId());
				c.setEstadio(co.getEstadio());
				c.setCantEntradas(co.getCantEntradas());
				c.setFecha(co.getFecha());
				c.setPrecioEntrada(co.getPrecioEntrada());
				c.setIdCompetencia(co.getCompetenciaId());
				c.setEntradasVendidas(co.getEntradasVendidas());
				c.setTipoDeporte(co.getEventoDeportivo().getTipo());
				c.setFinalizada(co.isFinalizada());
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
		
		DataPais pais = new DataPais(d.getComiteOlimpico().getPais().getPaisID(), d.getComiteOlimpico().getPais().getPais(), d.getComiteOlimpico().getPais().getCiudad());
		DataComite dc = new DataComite(d.getComiteOlimpico().getEmail(), "", d.getComiteOlimpico().getCodigo(),
				pais, d.getComiteOlimpico().getFacebook(), d.getComiteOlimpico().getTwitter(), d.getComiteOlimpico().getPaypal(), 
				d.getComiteOlimpico().getTenantID(),d.getComiteOlimpico().getId().intValue(), UsuarioController.USUARIO_COMITE);
		
		DataDeportista dd = new DataDeportista(d.getTenantID(),d.getDeportistaID(),d.getNombre(),d.getApellido(),d.getSexo(),
				  d.getFechaNac(),dc,di);
		
		
		dd.setListcompetencias(convertirListaCompetencias(DeportistaDAO.traerCompetenciasDelDeportista(d.getDeportistaID(),d.getTenantID())));
		dd.setListeventodeportivo(convertirEventosDeportivos(d.getEventoDep()));
		dd.setListestadisticas(convertirEstadisticas(d.getEstadisticas()));
		return dd;
		
	
	}
	
	@Override
	public boolean guardarDeportista(DataDeportista dataDeportista) throws AplicacionException {
		boolean guardo = false;
		Imagen foto = new Imagen(dataDeportista.getFoto().getMime(), dataDeportista.getFoto().getRuta(), dataDeportista.getFoto().getTenantId());
		
		if(ImagenDAO.guardarImagen(foto)){				
		
			Deportista deportista = new Deportista();
		
			deportista.setNombre(dataDeportista.getNombre());
			deportista.setApellido(dataDeportista.getApellido());
			deportista.setFechaNac(dataDeportista.getFechaNac());
			deportista.setSexo(dataDeportista.getSexo());
			deportista.setTenantID(dataDeportista.getTenantId());
			deportista.setFoto(foto);
			
			List<ComiteOlimpico>  comiteOlimpico = iuc.buscarComiteporPais(dataDeportista.getComite().getPais().getPaisID(),dataDeportista.getTenantId());
			
			deportista.setComiteOlimpico(comiteOlimpico.get(0));
			
			List<EventoDeportivo> eventosDep = iedc.buscarEventosDeportivos(dataDeportista.getListeventodeportivo());
			
			for (int i = 0; i < eventosDep.size(); i++) {
				
				EventoDeportivo dep = eventosDep.get(i);
				
				deportista.addEventoDeportivo(dep);
				
			}	
			
			guardo = DeportistaDAO.guardarDeportista(deportista);
					
		}else{
			try {
				ImagenUtil.borrarImagen(dataDeportista.getFoto().getRuta());
			} catch (IOException e) {
				throw new AplicacionException("Error al guardar Comite Olimpico. No se pudo borrar la imagen para dicho comite.");
			}	
			throw new AplicacionException("Error al guardar imagen");
		}
		
		return guardo;
	}

	@Override
	public List<DataDeportista> listarDeportistas(int tenantID, String nombreDeporte, String sexo, String nombreDisciplina) {
		try {
					
				return convertir(DeportistaDAO.listarDeportistas(tenantID, nombreDeporte, sexo, nombreDisciplina));
				
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			return null;
	}
	
	
	
	public List<DataDeportista> convertir(List<Deportista> deportista){
		List<DataDeportista> dataDeportista = new ArrayList<DataDeportista>();
		
		for(int i = 0; i< deportista.size(); i++){
			Deportista d = deportista.get(i);
			
			DataImagen di;

			if(d.getFoto() != null){			
				di = new DataImagen(d.getFoto().getMime(), d.getFoto().getRuta(), d.getFoto().getTenantId());
			}else{
				di = new DataImagen("","", 1);
			}
			
			DataPais pais = new DataPais(d.getComiteOlimpico().getPais().getPaisID(), d.getComiteOlimpico().getPais().getPais(), d.getComiteOlimpico().getPais().getCiudad());
			DataComite dc = new DataComite(d.getComiteOlimpico().getEmail(), "", d.getComiteOlimpico().getCodigo(),
					pais, d.getComiteOlimpico().getFacebook(), d.getComiteOlimpico().getTwitter(), d.getComiteOlimpico().getPaypal(), 
					d.getComiteOlimpico().getTenantID(),d.getComiteOlimpico().getId().intValue(), UsuarioController.USUARIO_COMITE);
			
			DataDeportista ddep  = new DataDeportista(d.getTenantID(),d.getDeportistaID(),d.getNombre(),d.getApellido(),d.getSexo(),
									  d.getFechaNac(),dc, di);
		

			dataDeportista.add(ddep);			
		}
		
		return dataDeportista;		
		
	}
	
	
	@Override
	public List<Deportista> listarDeportistas(List<DataDeportista> deportistas) {
		try {
					
			List<Deportista> listaDeportistas = new ArrayList<Deportista>();
			
			for(int i = 0; i< deportistas.size(); i++){
				
				Deportista d = new Deportista();
				
				d = DeportistaDAO.buscarDeportista(deportistas.get(i).getDeportistaID());
				
				listaDeportistas.add(d);
				
			} 
				
			return listaDeportistas;
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			return null;
	}

	@Override
	public Imagen subirImagenDeportista(MultipartFormDataInput input) throws AplicacionException {
		String fileName = "";
		String tenantId = "";
		String comiteId = "";
		File f = null;
		
		try {
			Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
			List<InputPart> inputParts = uploadForm.get("file");
		
			tenantId = uploadForm.get("tenantId").get(0).getBodyAsString();	
			comiteId = uploadForm.get("comiteId").get(0).getBodyAsString();	
		
			for (InputPart inputPart : inputParts) {		
	
				MultivaluedMap<String, String> header = inputPart.getHeaders();
				
				fileName = ImagenUtil.getDeportistaFilePath(header,tenantId,comiteId);
	
				InputStream inputStream;
			
				inputStream = inputPart.getBody(InputStream.class,null);				
	
				byte [] bytes = IOUtils.toByteArray(inputStream);
					
				String dir = ImagenUtil.getDeportistaDirectoryName(tenantId,comiteId);
				
				f = ImagenUtil.writeFile(bytes,fileName,dir);			
			}
		} catch (IOException e) {
			throw new AplicacionException("Error al subir la imagen");
		}catch(Exception e){
			throw new AplicacionException("Error al subir la imagen");
		}		
		
		return (new Imagen(ImagenUtil.getMimeType(f), fileName,Integer.parseInt(tenantId)));
		
	}

	@Override
	public List<DataDeportista> listarDeportistasPorComite(int tenantID, int comiteID) throws AplicacionException {
		try {
			List<Deportista> ld = DeportistaDAO.listarDeportistasPorComite(tenantID,comiteID);
			List<DataDeportista> dataDep = new ArrayList<DataDeportista>();
			for(int i = 0; i< ld.size(); i++){
				DataDeportista d = convertirDeportista(ld.get(i),"");
			
				dataDep.add(d);
			}
			return dataDep;
		}catch (Exception e){
			e.printStackTrace();
			throw new AplicacionException("Error al obtener deportistas");
		}
	}
	
	public DataBusquedaDeportista listarFiltroDeportista(int tenantID,String sexo) {
		try {
			List<String> paises = DeportistaDAO.listarPaises(tenantID);
			DataBusquedaDeportista dataBusqueda = new DataBusquedaDeportista();
			List<String> nombreDeporte    = DeportistaDAO.listarDeportes(tenantID,sexo);
			dataBusqueda.setNombreDeporte(nombreDeporte);
			dataBusqueda.setNombrePais(paises);
			return dataBusqueda;
		
		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;
	}
	
	@Override
	public List<DataDeportista> buscarDesportistas(DataBusquedaDeportista databusqueda) {
		try {
			List<Deportista> dep = DeportistaDAO.listarDeportistas(databusqueda.getTenantId(),databusqueda.getNombreDeportista(),databusqueda.getDeporte(),databusqueda.getPais(),databusqueda.getSexo());
			List<DataDeportista> dataDep = new ArrayList<DataDeportista>();
			for(int i = 0; i< dep.size(); i++){
				DataDeportista d = convertirDeportista(dep.get(i),databusqueda.getDeporte());
			
				dataDep.add(d);
			}
			return dataDep;
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;	
	}

	@Override
	public List<DataDeportista> listarDeportistasPorEventoDeportivo(int tenantID, String nombreDeporte) throws AplicacionException {
		try {
			return convertir(DeportistaDAO.listarDeportistasPorEventoDeportivo(tenantID,nombreDeporte));
		}catch (Exception e){
			e.printStackTrace();
			throw new AplicacionException("Error al obtener deportistas");
		}
	}
	
}
