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

import com.sgem.datatypes.DataEventoDeportivo;

@RequestScoped
@Path("/EventoDeportivoService")
public interface IEventoDeportivoService {

	@RolesAllowed("ORGANIZADOR")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/altaEventoDeportivo")
	public Response guardarEventoDeportivo(DataEventoDeportivo datos);

	@PermitAll
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/listarDeportes/{tenantID}/{sexo}")
	public Response listarDeportes(@PathParam("tenantID") int tenantID,	@PathParam("sexo") String sexo);

	@PermitAll
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/listarDisciplinas/{tenantID}/{sexo}/{selectDeportes}")
	public Response listarDisciplinas(@PathParam("tenantID") int tenantID,	@PathParam("sexo") String sexo,	
									  @PathParam("selectDeportes") String nombreDeporte);

	@PermitAll
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/listarRondas/{tenantID}/{sexo}/{selectDeportes}/{selectDisciplinas}")
	public Response listarRondas(@PathParam("tenantID") int tenantID, @PathParam("sexo") String sexo, 
								 @PathParam("selectDeportes") String nombreDeporte, @PathParam("selectDisciplinas") String nombreDisciplina);

	@PermitAll
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/listarDeportes/{tenantID}")
	public Response listarDeportes(@PathParam("tenantID") int tenantID);

	@PermitAll
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/listarDisciplinasEventoDeportivo/{tenantID}/{nombreDeporte}")
	public Response listarDisciplinasEventoDeportivo(@PathParam("tenantID") int tenantID, @PathParam("nombreDeporte") String nombreDeporte);
	
	@RolesAllowed("ORGANIZADOR")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes("multipart/form-data")
	@Path("/subirImagenDeporte")
	public Response subirImagenEventoDeportivo(MultipartFormDataInput input);

}
