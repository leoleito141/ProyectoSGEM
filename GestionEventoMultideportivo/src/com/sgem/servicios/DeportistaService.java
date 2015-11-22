package com.sgem.servicios;


import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import com.sgem.controladores.IDeportistaController;
import com.sgem.datatypes.DataBusquedaDeportista;
import com.sgem.datatypes.DataDeportista;
import com.sgem.seguridad.excepciones.AplicacionException;



@Stateless
public class DeportistaService implements IDeportistaService{
 
@EJB
private IDeportistaController idc;


	public Response guardarDeportista(DataDeportista dataDeportista) {		
		try {
			return Response.ok(idc.guardarDeportista(dataDeportista)).build();	
		} catch (AplicacionException e) {
			return Response.serverError().build();
		}			
	}
	
	@Override
	public Response subirImagenDeportista(MultipartFormDataInput input) {
		try {						
			return Response.ok(idc.subirImagenDeportista(input)).build();	
		} catch (AplicacionException e) {
			return Response.serverError().build();
		}	
	}
	
	@Override
	public Response listarDeportistas(int tenantID, String sexo, String nombreDeporte, String nombreDisciplina) {
		try {
			return Response.ok(idc.listarDeportistas(tenantID,nombreDeporte,sexo,nombreDisciplina)).build();
	
		} catch (Exception e) {
			e.printStackTrace();
	
		}
		return null;
	}

	@Override
	public Response listarDeportistasPorComite(int tenantID, int comiteID) {
		try {
			return Response.ok(idc.listarDeportistasPorComite(tenantID,comiteID)).build();
		} catch (AplicacionException e) {
			return Response.serverError().build();
		}
	}
	
	@Override
	public Response listarFiltroDeportista(int tenantID, String sexo) {
		try {
			return Response.ok(idc.listarFiltroDeportista(tenantID, sexo)).build();

		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;
	}
	
	@Override
	public Response buscarDesportistas(DataBusquedaDeportista databusqueda) {

		try {
			
			return Response.ok(idc.buscarDesportistas(databusqueda)).build();

		} catch (Exception e) {
			e.printStackTrace();


		}
		return null;
	}
	

}
