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

	@RequestScoped
	@Path("/EventoDeportivoService")
	public interface IEventoDeportivoService {


		
//		@RolesAllowed("ADMIN")
		@PermitAll
		@POST
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		@Path("/altaEventoDeportivo")
		public Response guardarEventoDeportivo(DataEventoDeportivo datos);
		
////		@RolesAllowed("COMITE_OLIMPICO")
		@PermitAll
///		@RolesAllowed("USUARIO_COMUN")
		@GET
		@Produces(MediaType.APPLICATION_JSON)
		@Path("/listarDeportes/{tenantID}/{sexo}")
		public Response  listarDeportes(@PathParam("tenantID") int tenantID, @PathParam("sexo") String sexo);
		
		
		@PermitAll
		@GET
		@Produces(MediaType.APPLICATION_JSON)
		@Path("/listarDisciplinas/{tenantID}/{sexo}/{selectDeportes}")
		public Response  listarDisciplinas(@PathParam("tenantID") int tenantID, @PathParam("sexo") String sexo,  @PathParam("selectDeportes") String nombreDeporte);
		
		@PermitAll
		@GET
		@Produces(MediaType.APPLICATION_JSON)
		@Path("/listarRondas/{tenantID}/{sexo}/{selectDeportes}/{selectDisciplinas}")
		public Response  listarRondas(@PathParam("tenantID") int tenantID, @PathParam("sexo") String sexo,  @PathParam("selectDeportes") String nombreDeporte,@PathParam("selectDisciplinas") String nombreDisciplina);
		
	}
	
