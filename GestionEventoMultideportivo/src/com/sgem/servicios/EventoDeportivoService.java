package com.sgem.servicios;


import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.Response;

import com.sgem.controladores.IEventoDeportivoController;
import com.sgem.datatypes.DataEventoDeportivo;


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







}
