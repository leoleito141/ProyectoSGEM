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

import com.sgem.datatypes.DataDeportista;
import com.sgem.datatypes.DataEventoDeportivo;

	@RequestScoped
	@Path("/DeportistaService")
	public interface IDeportistaService {


	//	@RolesAllowed("COMITE_OLIMPICO")
		@PermitAll
		@POST
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		@Path("/altaDeportista")
		public Response guardarDeportista(DataDeportista datos);
		
		
		
	
		
	}
	
