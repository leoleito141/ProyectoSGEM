package com.sgem.servicios;


import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import com.sgem.controladores.IEventoDeportivoController;
import com.sgem.datatypes.DataEventoDeportivo;
import com.sgem.seguridad.excepciones.AplicacionException;
import com.sgem.seguridad.excepciones.UsuarioYaExisteException;


@Stateless
public class EventoDeportivoService implements IEventoDeportivoService{
 
@EJB
private IEventoDeportivoController iec;

	// localhost:8080/GestionEventoMultideportivo/rest/ServicioUsuario/status/
	
	public Response guardarEventoDeportivo(DataEventoDeportivo dataEventoDeportivo) {

		try {
			return Response.ok(iec.guardarEventoDeportivo(dataEventoDeportivo)).build();

		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;

	}
	
	
	public Response listarDeportes(int tenantID, String sexo){

		try {
			return Response.ok(iec.listarDeportes(tenantID, sexo)).build();

		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;

	}
	
	
	public Response listarDisciplinas(int tenantID, String nombreDeporte, String sexo){

		try {
			return Response.ok(iec.listarDisciplinas(tenantID,nombreDeporte,sexo)).build();

		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;

	}


	@Override
	public Response listarRondas(int tenantID, String sexo, String nombreDeporte, String nombreDisciplina) {
		try {
			return Response.ok(iec.listarRondas(tenantID,nombreDeporte,sexo,nombreDisciplina)).build();

		} catch (Exception e) {
			e.printStackTrace();

		}
		
		
		return null;
	}

	@Override
	public Response listarDeportes(int tenantID){
		try {
			return Response.ok(iec.listarDeportes(tenantID)).build();
		} catch (AplicacionException e) {
			return Response.serverError().build();
		}
	}

	@Override
	public Response listarDisciplinasEventoDeportivo(int tenantID, String nombreDeporte){
		try {
			return Response.ok(iec.listarDisciplinasEventoDeportivo(tenantID,nombreDeporte)).build();
		} catch (AplicacionException e) {
			return Response.serverError().build();
		}
	}


	@Override
	public Response subirImagenEventoDeportivo(MultipartFormDataInput input) {
		try {						
			return Response.ok(iec.subirImagenEventoDeportivo(input)).build();	
		}catch (UsuarioYaExisteException e) {
			return Response.status(Status.FOUND).entity(new Boolean(false)).build();
		} catch (AplicacionException e) {
			return Response.serverError().build();
		}	
	}

}
