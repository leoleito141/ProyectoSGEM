package com.sgem.servicios;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.Response;

import com.sgem.controladores.IEventoMultiController;
import com.sgem.datatypes.DataEvento;

@Stateless
public class EventoMultiService implements IEventoMultiService{
	
	@EJB
	private IEventoMultiController iec;
	
	@Override
	public Response altaEvento(DataEvento datosEvento) {
		System.out.println("Entre alta evento" +datosEvento.toString());
		 
		try {
			
			boolean alta = iec.guardarEventoMultideportivo(datosEvento);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return Response.status(400).entity("Alta Incorrecta").build();
		}
		
		
		return Response
				.ok("{\"status\":\"Alta Correcta\"}")
				.build();
	}
	
	@Override
	public Response obtenerDatos(String tenant) {
		
		System.out.println("Entre obtener datos tenant" + tenant);
		
		
		return Response
				.ok("{\"image\":\"Lighthouse.jpg\"}")
				.build();
	}

}
