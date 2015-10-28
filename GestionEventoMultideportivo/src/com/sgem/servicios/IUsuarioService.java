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

import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import com.sgem.datatypes.DataComite;
import com.sgem.datatypes.DataHistorialLogin;
import com.sgem.datatypes.DataNovedad;
import com.sgem.datatypes.DataUsuario;

@RequestScoped
@Path("/UsuarioService")
public interface IUsuarioService {

	@PermitAll
//	@DenyAll
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/status")
	public Response getStatus();
	
	@PermitAll
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/loginAdmin")
	public Response loginAdmin(DataUsuario dataUsuario);
	
	@RolesAllowed("VISITANTE")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/usuarios")
	public Response guardarUsuario(DataUsuario dataUsuario);
	
	@PermitAll
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/loginUsuario")
	public Response loginUsuario(DataUsuario dataUsuario);

	//	@RolesAllowed("ADMIN")
	@PermitAll	// para pruebas
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/altaComite")
	public Response altaComite(DataComite dataComite);

	@RolesAllowed("COMITE_OLIMPICO")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/novedades")
	public Response guardarNovedad(DataNovedad dataNovedad);

	@RolesAllowed("COMITE_OLIMPICO")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes("multipart/form-data")
	@Path("/subirImagen")
	public Response subirImagen(MultipartFormDataInput input);
	
//	@RolesAllowed("ORGANIZADOR")
//	@POST
//	@Produces(MediaType.APPLICATION_JSON)
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Path("/estados")
//	public Response guardarEstado(DataHistorialLogin hl);
	
	@RolesAllowed("ORGANIZADOR")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/obtenerHistorial/{tenantId}")
	public Response obtenerHistorial(@PathParam("tenantId") String tenantId);
}
