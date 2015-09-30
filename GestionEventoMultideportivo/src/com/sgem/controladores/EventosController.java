package com.sgem.controladores;


import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.sgem.datatypes.DataEventoDeportivo;
import com.sgem.dominio.EventoDeportivo;
import com.sgem.persistencia.IEventoDAO;


@Stateless
public class EventosController implements IEventosController {

	public static final String ROL_ADMIN = "Administrador"; // faltan mas roles...
	
	@EJB
	private IEventoDAO EventosDAO;
	
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
