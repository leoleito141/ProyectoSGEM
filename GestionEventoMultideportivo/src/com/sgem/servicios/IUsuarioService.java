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
import com.sgem.datatypes.DataJuez;
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
		
	@PermitAll
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/loginAndroid")
	public Response loginAndroid(DataUsuario dataUsuario);
	
	@PermitAll
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/loginIonic")
	public Response loginIonic(DataJuez dataJuez);
	
	@PermitAll
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/loginUsuario")
	public Response loginUsuario(DataUsuario dataUsuario);

	@RolesAllowed("VISITANTE")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/usuarios")
	public Response guardarUsuario(DataUsuario dataUsuario);
	
	@RolesAllowed("ORGANIZADOR")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/altaComite")
	public Response altaComite(DataComite dataComite);
	
	@RolesAllowed("ORGANIZADOR")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes("multipart/form-data")
	@Path("/subirImagenComite")
	public Response subirImagenComite(MultipartFormDataInput input);
	
	
//	@RolesAllowed("ADMIN")
	@PermitAll	// para pruebas
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/altaJuez")
	public Response altaJuez(DataUsuario Usuario);

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
	@Path("/subirImagenNovedad")
	public Response subirImagenNovedad(MultipartFormDataInput input);
	
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
	
	@RolesAllowed("ORGANIZADOR")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/obtenerCantidadRegistrados/{tenantId}")
	public Response obtenerCantidadRegistrados(@PathParam("tenantId") String tenantId);
	

	//@RolesAllowed("ORGANIZADOR")
	@PermitAll	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/listarJueces/{tenantId}")
	public Response listarJueces(@PathParam("tenantId") Integer tenantId);
	
	@PermitAll
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/getNovedades/{tenantId}")
	public Response getNovedad(@PathParam("tenantId") String tenantId);
	
}
