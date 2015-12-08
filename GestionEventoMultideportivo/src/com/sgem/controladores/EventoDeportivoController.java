package com.sgem.controladores;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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

import com.sgem.datatypes.DataEventoDeportivo;
import com.sgem.datatypes.DataImagen;
import com.sgem.dominio.EventoDeportivo;
import com.sgem.dominio.EventoMultideportivo;
import com.sgem.dominio.Imagen;
import com.sgem.dominio.Ronda;
import com.sgem.persistencia.IEventoDeportivoDAO;
import com.sgem.persistencia.IImagenDAO;
import com.sgem.persistencia.IRondaDAO;
import com.sgem.seguridad.excepciones.AplicacionException;
import com.sgem.seguridad.excepciones.UsuarioYaExisteException;
import com.sgem.utilidades.ImagenUtil;


@Stateless
public class EventoDeportivoController implements IEventoDeportivoController {

	@EJB
	private IEventoDeportivoDAO EventosDAO;
	
	@EJB
	private IRondaDAO RondaDAO;
	
	@EJB
	private IEventoMultiController  iemc;
	
	@EJB
	private IImagenDAO ImagenDAO;


	@Override
	public boolean guardarEventoDeportivo(DataEventoDeportivo dataEventoDeportivo) {
		try {
						
			if(EventosDAO.traerIDEventoDeportivo(dataEventoDeportivo.getTenantId(), dataEventoDeportivo.getNombreDeporte(), 
												 dataEventoDeportivo.getNombreDisciplina(), dataEventoDeportivo.getSexo()) == null){
				
				Imagen foto = new Imagen(dataEventoDeportivo.getFoto().getMime(), dataEventoDeportivo.getFoto().getRuta(), dataEventoDeportivo.getFoto().getTenantId());
			
				if(ImagenDAO.guardarImagen(foto)){	
					
					EventoDeportivo eventoDeportivo = new EventoDeportivo();	
					eventoDeportivo.setNombreDeporte(dataEventoDeportivo.getNombreDeporte());;
					eventoDeportivo.setDisciplina(dataEventoDeportivo.getNombreDisciplina());
					eventoDeportivo.setSexo(dataEventoDeportivo.getSexo());
					eventoDeportivo.setFechaInicio(dataEventoDeportivo.getFechaInicio());
					eventoDeportivo.setFechaFin(dataEventoDeportivo.getFechaFin());
					eventoDeportivo.setTenantId(dataEventoDeportivo.getTenantId());
					eventoDeportivo.setTipo(dataEventoDeportivo.getTipo());					
					eventoDeportivo.setFoto(foto);
					
					EventoMultideportivo emd = iemc.obtenerEventoMultideportivoXTenantId(dataEventoDeportivo.getTenantId());					
				
					int cantidadRondas = dataEventoDeportivo.getCantRondas();
					
					boolean guardado = EventosDAO.guardarEventoDeportivo(eventoDeportivo,emd);
					
					if(guardado==true){
						
						EventoDeportivo eventoDep = EventosDAO.traerEventoDeportivo(eventoDeportivo);
						
						for (int i = 0; i < cantidadRondas; i++) {							
							int j = i+1;
							
							Ronda r = new Ronda();							
							r.setNumeroRonda(j);
							r.setTenantId(emd.getTenantHandler().getTenantID());
							r.setEventoDeportivo(eventoDep);
							
							eventoDep.addRonda(r);
							
							RondaDAO.guardarRonda(r);
						}
						
					}else{
						return false;
					}
					
				}else{
					try {
						ImagenUtil.borrarImagen(dataEventoDeportivo.getFoto().getRuta());
					} catch (IOException e) {
						throw new AplicacionException("Error al guardar Evento Deportivo. No se pudo borrar la imagen.",e);
					}		
					throw new AplicacionException("Error al guardar imagen");
				}
			}else{
				try {
					ImagenUtil.borrarImagen(dataEventoDeportivo.getFoto().getRuta());
				} catch (IOException e) {
					throw new AplicacionException("Error al guardar Evento Deportivo. No se pudo borrar la imagen.",e);
				}				
				throw new UsuarioYaExisteException("El Comite Olimpico con codigo "+dataEventoDeportivo.getNombreDeporte()+" - " + dataEventoDeportivo.getNombreDisciplina()+ 
												   "-" +dataEventoDeportivo.getSexo()+ " ya existe.");				
			}		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public List<String> listarDeportes(int tenantID, String sexo) {
		try {
	      					
			return EventosDAO.listarDeportes(tenantID,sexo);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<DataEventoDeportivo> listarDisciplinas(int tenantID, String nombreDeporte, String sexo) {
		try {
				
			return convertirEventosDeportivos(EventosDAO.listarDisciplinas(tenantID,nombreDeporte,sexo));
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<EventoDeportivo> buscarEventosDeportivos(Integer tenantId, String deporte, List<String> disciplinas, String sexo) {
		
		try {
		
		List<EventoDeportivo> led = new ArrayList<EventoDeportivo>();
		Integer idEventoDep = 0;
		EventoDeportivo ed = null;
		
		for (int i = 0; i < disciplinas.size(); i++) {
			
			String disciplina =	disciplinas.get(i).toString();
			
			idEventoDep = EventosDAO.traerIDEventoDeportivo(tenantId, deporte,disciplina,sexo);
			
			ed = EventosDAO.traerEventoDeportivo(idEventoDep);
			
			led.add(ed);
			
			
		}
		
		return led;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Integer> listarRondas(int tenantID, String nombreDeporte, String sexo, String nombreDisciplina) {
		try {
			
			return EventosDAO.listarRondas(tenantID,nombreDeporte,sexo,nombreDisciplina);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;	
		
	}

	@Override
	public List<DataEventoDeportivo> listarDeportes(int tenantID) throws AplicacionException {
		try {				
			return convertirEventosDeportivos(EventosDAO.listarDeportes(tenantID));		
		} catch (Exception e) {
			e.printStackTrace();
			throw new AplicacionException("Error al obtener eventos deportivos",e);
		}
	}

	private List<DataEventoDeportivo> convertirEventosDeportivos(List<EventoDeportivo> deportes) {
		List<DataEventoDeportivo> listaDeportas = new ArrayList<DataEventoDeportivo>();
		
		for(int i = 0; i< deportes.size(); i++){
			DataEventoDeportivo ed = new DataEventoDeportivo();
							
			ed.setCantRondas(deportes.get(i).getRonda().size());
			ed.setFechaFin(deportes.get(i).getFechaFin());
			ed.setFechaInicio(deportes.get(i).getFechaInicio());
			ed.setNombreDeporte(deportes.get(i).getNombreDeporte());
			ed.setNombreDisciplina(deportes.get(i).getDisciplina() == null ? "" : deportes.get(i).getDisciplina() );
			ed.setSexo(deportes.get(i).getSexo());
			ed.setTenantId(deportes.get(i).getTenantId());
			ed.setTipo(deportes.get(i).getTipo());
			ed.setFoto(new DataImagen(deportes.get(i).getFoto().getMime(), deportes.get(i).getFoto().getRuta(), deportes.get(i).getFoto().getTenantId()));
			ed.setEventoDeportivoID(ed.getEventoDeportivoID());
			
			listaDeportas.add(ed);			
		}		
		return listaDeportas;		
	}
	
	
	@Override
	public List<EventoDeportivo> buscarEventosDeportivos(Set<DataEventoDeportivo> listeventodeportivo) {
		try {
			
			Iterator<DataEventoDeportivo> iter = listeventodeportivo.iterator();
			List<EventoDeportivo> led = new ArrayList<EventoDeportivo>();
			Integer idEventoDep = 0;
			EventoDeportivo ed = null;
			while (iter.hasNext()) {
				DataEventoDeportivo evd = iter.next();
				
				idEventoDep = EventosDAO.traerIDEventoDeportivo(evd.getTenantId(), evd.getNombreDeporte(),evd.getNombreDisciplina(),evd.getSexo());
				
				ed = EventosDAO.traerEventoDeportivo(idEventoDep);
				
				led.add(ed);
				
			}
			
			return led;
			}catch (Exception e) {
				e.printStackTrace();
			}
			return null;
	}

	@Override
	public List<DataEventoDeportivo> listarDisciplinasEventoDeportivo(int tenantID, String nombreDeporte) throws AplicacionException {
		try {				
			return convertirEventosDeportivos(EventosDAO.listarDisciplinasEventoDeportivo(tenantID,nombreDeporte));		
		} catch (Exception e) {
			e.printStackTrace();
			throw new AplicacionException("Error al obtener eventos deportivos",e);
		}
	}

	@Override
	public Imagen subirImagenEventoDeportivo(MultipartFormDataInput input) throws AplicacionException {

		String fileName = "";
		String tenantId = "";
		String eventoDeportivoID = "";	
		File f = null;
		
		try {
			Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
			List<InputPart> inputParts = uploadForm.get("file");
		
			tenantId = uploadForm.get("tenantID").get(0).getBodyAsString();		
			eventoDeportivoID = String.valueOf(EventosDAO.obtenerMaximoEventoDeportivo());	
			
			for (InputPart inputPart : inputParts) {		
	
				MultivaluedMap<String, String> header = inputPart.getHeaders();
				
				// Ojo con esto, puede que subam 2 noticias y la foto si se llama igual va a hacer cualquiera, quizas renombrarla..
				fileName = ImagenUtil.getDeporteFilePath(header, tenantId, eventoDeportivoID);
	
				InputStream inputStream;
			
				inputStream = inputPart.getBody(InputStream.class,null);				
	
				byte [] bytes = IOUtils.toByteArray(inputStream);
				
				String dir = ImagenUtil.getDeporteDirectoryName(tenantId, eventoDeportivoID);
				
				f = ImagenUtil.writeFile(bytes,fileName,dir);			
			}
		} catch (IOException e) {
			throw new AplicacionException("Error al subir la imagen del evento deportivo");
		}			
		
		return (new Imagen(ImagenUtil.getMimeType(f), fileName,Integer.parseInt(tenantId)));
		
	}
	
}
