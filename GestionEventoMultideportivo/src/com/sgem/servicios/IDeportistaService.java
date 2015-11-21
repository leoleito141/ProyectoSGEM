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

import com.sgem.datatypes.DataDeportista;
import com.sgem.datatypes.DataEventoDeportivo;

	@RequestScoped
	@Path("/DeportistaService")
	public interface IDeportistaService {


		@RolesAllowed("COMITE_OLIMPICO")
		@POST
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		@Path("/altaDeportista")
		public Response guardarDeportista(DataDeportista datos);
		
		
		@PermitAll
		@GET
		@Produces(MediaType.APPLICATION_JSON)
		@Path("/listarDeportistas/{tenantID}/{sexo}/{selectDeportes}/{selectDisciplinas}")
		public Response  listarDeportistas(@PathParam("tenantID") int tenantID, @PathParam("sexo") String sexo,  @PathParam("selectDeportes") String nombreDeporte,@PathParam("selectDisciplinas") String nombreDisciplina);
	
		@PermitAll
		@GET
		@Produces(MediaType.APPLICATION_JSON)
		@Path("/listarDeportistasPorComite/{tenantID}/{comiteID}")
		public Response listarDeportistasPorComite(@PathParam("tenantID") int tenantID, @PathParam("comiteID") int comiteID);
	
		
		@RolesAllowed("COMITE_OLIMPICO")
		@POST
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes("multipart/form-data")
		@Path("/subirImagenDeportista")
		public Response subirImagenDeportista(MultipartFormDataInput input);
		
		
	}
	
