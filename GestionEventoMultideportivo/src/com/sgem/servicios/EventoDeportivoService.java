package com.sgem.servicios;


import java.io.IOException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.util.Base64;

import com.sgem.controladores.IEventosController;
import com.sgem.controladores.IUsuarioController;
import com.sgem.datatypes.DataEventoDeportivo;
import com.sgem.datatypes.DataUsuario;
import com.sgem.seguridad.Token;

@Stateless
public class EventoDeportivoService implements IEventoDeportivoService{
 
@EJB
private IEventosController iec;

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
