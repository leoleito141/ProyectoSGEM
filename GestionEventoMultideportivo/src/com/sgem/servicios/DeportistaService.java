package com.sgem.servicios;


import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.Response;

import com.sgem.controladores.IDeportistaController;
import com.sgem.datatypes.DataDeportista;
import com.sgem.datatypes.DataEventoDeportivo;




@Stateless
public class DeportistaService implements IDeportistaService{
 
@EJB
private IDeportistaController idc;


public Response guardarDeportista(DataDeportista dataDeportista) {
	
	try {
		return Response.ok(idc.guardarDeportista(dataDeportista)).build();

	} catch (Exception e) {
		e.printStackTrace();

	}
	return null;
	
}








}
