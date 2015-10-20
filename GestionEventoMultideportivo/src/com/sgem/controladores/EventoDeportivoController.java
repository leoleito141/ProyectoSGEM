package com.sgem.controladores;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.sgem.datatypes.DataEventoDeportivo;
import com.sgem.dominio.EventoDeportivo;
import com.sgem.dominio.EventoMultideportivo;
import com.sgem.persistencia.IEventoDeportivoDAO;


@Stateless
public class EventoDeportivoController implements IEventoDeportivoController {

	@EJB
	private IEventoDeportivoDAO EventosDAO;
	
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
					
					
					
					EventoMultideportivo emd = iemc.obtenerEventoMultideportivoXTenantId(dataEventoDeportivo.getTenantId()) ;
					
				
					
					
					
					
					return  EventosDAO.guardarEventoDeportivo(eventoDeportivo,emd);
					
					
			
				
		
			
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
	
}
