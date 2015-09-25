	package com.sgem.servicios;

	import javax.annotation.security.PermitAll;
	import javax.annotation.security.RolesAllowed;
	import javax.enterprise.context.RequestScoped;
	import javax.ws.rs.Consumes;
	import javax.ws.rs.GET;
	import javax.ws.rs.POST;
	import javax.ws.rs.Path;
	import javax.ws.rs.PathParam;
	import javax.ws.rs.Produces;
	import javax.ws.rs.core.MediaType;
	import javax.ws.rs.core.Response;

import com.sgem.datatypes.DataEventoDeportivo;
import com.sgem.datatypes.DataUsuario;

	@RequestScoped
	@Path("/EventoDeportivoService")
	public interface IEventoDeportivoService {


		
		@RolesAllowed("ADMIN")
		@POST
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		@Path("/altaEventoDeportivo")
		public Response guardarEventoDeportivo(DataEventoDeportivo datos);
	}
	
