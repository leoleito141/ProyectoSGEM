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

import com.sgem.datatypes.DataCompetencia;
import com.sgem.datatypes.DataCompraEntrada;


@RequestScoped
@Path("/CompetenciaService")
public interface ICompetenciaService {


	
//	@RolesAllowed("ORGANIZADOR")
		@PermitAll
		@POST
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		@Path("/guardarCompetencia")
		public Response guardarCompetencia(DataCompetencia datos);
		
		
		@PermitAll
		@GET
		@Produces(MediaType.APPLICATION_JSON)
		@Path("/listarCompetenciasPorRonda/{tenantID}/{sexo}/{nombreDeporte}/{nombreDisciplina}/{ronda}")
		public Response listarCompetenciasPorRonda(@PathParam("tenantID") int tenantID, @PathParam("sexo") String sexo,  @PathParam("nombreDeporte") String nombreDeporte,@PathParam("nombreDisciplina") String nombreDisciplina, @PathParam("ronda") int ronda);
	
		@PermitAll
		@GET
		@Produces(MediaType.APPLICATION_JSON)
		@Path("/obtenerPrecio/{tenantID}/{idCompetencia}")
		public Response obtenerPrecio(@PathParam("tenantID") int tenantID, @PathParam("idCompetencia") int idCompetencia);
	
		
//		@RolesAllowed("USUARIO_COMUN")
			@PermitAll
			@POST
			@Produces(MediaType.APPLICATION_JSON)
			@Consumes(MediaType.APPLICATION_JSON)
			@Path("/comprarEntradas")
			public Response comprarEntradas(DataCompraEntrada datos);
	
	
}