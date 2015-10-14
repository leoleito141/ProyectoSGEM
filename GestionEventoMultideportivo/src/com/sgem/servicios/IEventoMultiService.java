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

import com.sgem.datatypes.DataEvento;


@RequestScoped
@Path("/EventoMultiService")
public interface IEventoMultiService {
	

	@PermitAll
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/status")
	public Response getStatus();
	
	@RolesAllowed("ADMIN")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/eventos")
	public Response altaEvento(DataEvento datosEvento);

	
//	//@RolesAllowed("VISITANTE")
//	@PermitAll	
//	@GET
//	@Produces(MediaType.APPLICATION_JSON)
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Path("/obtenerDatosTenant/{tenant}")
//	public Response obtenerDatos(@PathParam("tenant") String tenant);
//	
//	
//	//@RolesAllowed("VISITANTE")
//	@PermitAll	
//	@GET
//	@Produces(MediaType.APPLICATION_JSON)
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Path("/obtenerTenant/{tenant}")
//	public Response obtenerTenant(@PathParam("tenant") String tenant);
//

	@PermitAll	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/obtenerDataTenant/{tenant}")
	public Response obtenerDataTenant(@PathParam("tenant") String tenant);

		
}
