package com.sgem.controladores;


import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.sgem.datatypes.DataBusquedaDeportista;
import com.sgem.datatypes.DataComite;
import com.sgem.datatypes.DataDeportista;
import com.sgem.datatypes.DataEventoDeportivo;
import com.sgem.datatypes.DataImagen;
import com.sgem.datatypes.DataPais;
import com.sgem.dominio.Deportista;
import com.sgem.dominio.EventoDeportivo;
import com.sgem.dominio.EventoMultideportivo;
import com.sgem.dominio.Ronda;
import com.sgem.persistencia.IEventoDeportivoDAO;
import com.sgem.persistencia.IRondaDAO;
import com.sgem.seguridad.excepciones.AplicacionException;


@Stateless
public class EventoDeportivoController implements IEventoDeportivoController {

	@EJB
	private IEventoDeportivoDAO EventosDAO;
	
	@EJB
	private IRondaDAO RondaDAO;
	
	@EJB
	private IEventoMultiController  iemc;
	
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
	return new DataDeportista(d.getTenantID(),d.getDeportistaID(),d.getNombre(),d.getApellido(),d.getSexo(),
							  d.getFechaNac(),dc,deporte,new ArrayList<String>(), di);
	}
	@Override
	public boolean guardarEventoDeportivo(DataEventoDeportivo dataEventoDeportivo) {
		try {

			EventoDeportivo eventoDeportivo = null;
		//// Falta mandar el tennat del evento multideportivo al que pertenece 
			        int idEventoMulti = 0;
					eventoDeportivo = new EventoDeportivo();
					
				
					eventoDeportivo.setNombreDeporte(dataEventoDeportivo.getNombreDeporte());;
					eventoDeportivo.setDisciplina(dataEventoDeportivo.getNombreDisciplina());
					eventoDeportivo.setSexo(dataEventoDeportivo.getSexo());
					eventoDeportivo.setFechaInicio(dataEventoDeportivo.getFechaInicio());
					eventoDeportivo.setFechaFin(dataEventoDeportivo.getFechaFin());
					eventoDeportivo.setTenantId(dataEventoDeportivo.getTenantId());
					eventoDeportivo.setTipo(dataEventoDeportivo.getTipo());
					
					
					
					EventoMultideportivo emd = iemc.obtenerEventoMultideportivoXTenantId(dataEventoDeportivo.getTenantId()) ;
					
				
					int cantidadRondas = dataEventoDeportivo.getCantRondas();
					
					
		
					
					
					boolean guardado = EventosDAO.guardarEventoDeportivo(eventoDeportivo,emd);
					
					if(guardado==true){
						
						EventoDeportivo eventoDep	= EventosDAO.traerEventoDeportivo(eventoDeportivo);
						
						for (int i = 0; i < cantidadRondas; i++) {
							
							int j = i+1;
							Ronda r = new Ronda();
							
							r.setNumeroRonda(j);
							r.setTenantId(emd.getTenantHandler().getTenantID());
//							r.setEventoDepId(eventoDep.getEventoDepId());
							r.setEventoDeportivo(eventoDep);
							eventoDep.addRonda(r);
							
							RondaDAO.guardarRonda(r);
							
							
						}
						
						
						
						
					}else{
						return false;
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
	public List<String> listarDisciplinas(int tenantID, String nombreDeporte, String sexo) {
		try {
				
			return EventosDAO.listarDisciplinas(tenantID,nombreDeporte,sexo);
		
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
			
			listaDeportas.add(ed);			
		}		
		return listaDeportas;		
	}
	
	public DataBusquedaDeportista listarFiltroDeportista(int tenantID,String sexo) {
		try {

			List<EventoDeportivo> listEvento = EventosDAO.listarEventoDeportivo(tenantID,sexo);
			List<String> paises = EventosDAO.listarPaises();
			
			DataBusquedaDeportista dataBusqueda = new DataBusquedaDeportista();
			List<String> nombreDeporte    = new ArrayList<String>();
			List<String> nombreDisciplina = new ArrayList<String>();
			for(int i = 0; i< listEvento.size(); i++){
				nombreDeporte.add(listEvento.get(i).getNombreDeporte());
				nombreDisciplina.add(listEvento.get(i).getDisciplina());
			}
			dataBusqueda.setNombreDeporte(nombreDeporte);
			dataBusqueda.setNombreDisciplina(nombreDisciplina);
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
			List<Deportista> dep = EventosDAO.listarDeportistas(databusqueda.getTenantId(),databusqueda.getNombreDeportista(),databusqueda.getDeporte(),databusqueda.getDisciplina(),databusqueda.getPais(),databusqueda.getSexo());
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
	
	
}
