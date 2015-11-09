package com.sgem.controladores;


import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.sgem.datatypes.DataEventoDeportivo;
import com.sgem.dominio.EventoDeportivo;
import com.sgem.dominio.EventoMultideportivo;
import com.sgem.dominio.Ronda;
import com.sgem.persistencia.IEventoDeportivoDAO;
import com.sgem.persistencia.IRondaDAO;


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
							r.setTenantId(emd.getTenant().getTenantID());
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
	
	
	
}
