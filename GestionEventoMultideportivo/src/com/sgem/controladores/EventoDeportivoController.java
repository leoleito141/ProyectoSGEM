package com.sgem.controladores;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.sgem.datatypes.DataEventoDeportivo;
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
	
	
}
