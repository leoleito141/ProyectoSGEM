package com.sgem.servicios;


import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.Response;

import com.sgem.controladores.IEventoDeportivoController;
import com.sgem.datatypes.DataEventoDeportivo;


@Stateless
public class EventoDeportivoService implements IEventoDeportivoService{
 
@EJB
private IEventoDeportivoController iec;

	// localhost:8080/GestionEventoMultideportivo/rest/ServicioUsuario/status/
	
	public Response guardarEventoDeportivo(DataEventoDeportivo dataEventoDeportivo) {

		try {
			return Response.ok(iec.guardarEventoDeportivo(dataEventoDeportivo)).build();

		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;

	}
	
	
	

}
