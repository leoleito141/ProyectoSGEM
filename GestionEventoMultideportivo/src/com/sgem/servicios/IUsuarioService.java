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

import com.sgem.datatypes.DataUsuario;

@RequestScoped
@Path("/UsuarioService")
public interface IUsuarioService {

//	@PermitAll
//	@DenyAll
	@RolesAllowed("ADMIN")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/status")
	public Response getStatus();
	
//	@RolesAllowed("ADMIN")
	@PermitAll	// para pruebas
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/usuarios")
	public Response guardarUsuario(DataUsuario dataUsuario);
	
	@RolesAllowed("ADMIN")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/prueba")
	public Response prueba();
	
//	@RolesAllowed("ADMIN")
//	@GET
//	@Produces(MediaType.APPLICATION_JSON)
//	@Path("/status")
//	public Response obtenerPorId(@PathParam("id") long id);

	@PermitAll
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/login")
	public Response login(DataUsuario dataUsuario);
	
	@RolesAllowed("ADMIN")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/eventos")
	public Response altaEvento(String datos);
}
