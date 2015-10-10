package com.sgem.controladores;


import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.sgem.datatypes.DataEventoDeportivo;
import com.sgem.dominio.EventoDeportivo;
import com.sgem.persistencia.IEventoDeportivoDAO;


@Stateless
public class EventoDeportivoController implements IEventoDeportivoController {

	@EJB
	private IEventoDeportivoDAO EventosDAO;
	
	@Override
	public boolean guardarEventoDeportivo(DataEventoDeportivo dataEventoDeportivo) {
		try {

			EventoDeportivo eventoDeportivo = null;
		//// Falta mandar el tennat del evento multideportivo al que pertenece 

					eventoDeportivo = new EventoDeportivo();
					
				
					eventoDeportivo.setNombreDeporte(dataEventoDeportivo.getNombreDeporte());;
					eventoDeportivo.setDisciplina(dataEventoDeportivo.getNombreDisciplina());
					eventoDeportivo.setSexo(dataEventoDeportivo.getSexo());
					eventoDeportivo.setFechaInicio(dataEventoDeportivo.getFechaInicio());
					eventoDeportivo.setFechaFin(dataEventoDeportivo.getFechaFin());
					
					return EventosDAO.guardarEventoDeportivo(eventoDeportivo);
					
			
				
		
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
