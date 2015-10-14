package com.sgem.servicios;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.sgem.controladores.IEventoMultiController;
import com.sgem.datatypes.DataEvento;
import com.sgem.datatypes.DataTenant;

@Stateless
public class EventoMultiService implements IEventoMultiService{
	
	@EJB
	private IEventoMultiController iemc;
	
	@Override
	public Response getStatus() {
		return Response
				.ok("{\"status\":\"El servicio de los eventosMultideportivos esta funcionando...\"}")
				.build();
	}
	
	@Override
	public Response altaEvento(DataEvento datosEvento) {
		System.out.println("Entre alta evento" +datosEvento.toString());
		 
		try {
			
			boolean alta = iemc.guardarEventoMultideportivo(datosEvento);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return Response.status(400).entity("Alta Incorrecta").build();
		}
		
		
		return Response
				.ok("{\"status\":\"Alta Correcta\"}")
				.build();
	}
	
//	@Override
//	public Response obtenerDatos(String tenant) {
//		
//		System.out.println("Entre obtener datos tenant" + tenant);
//		
//		
//		return Response
//				.ok("{\"image\":\"Lighthouse.jpg\"}")
//				.build();
//	}
//
//	@Override
//	public Response obtenerTenant(String tenant) {
//		
//		//el json de abajo podría ser un Data por nosotros que tenga todo lo del tenant asi ya transforma
//		//el json, va a ser enorme.. jaja, despues vemos. Debería ir contra la BD, y si no existe
//		// hacer lo que hablaron bruno y maxi de mostrar error.
//		
//		String s = new String( "{\"tenant\" : {"
//								+ "\"tenantId\":\"1\","
//								+ "\"login_back_img\":\"Lighthouse.jpg\","
//								+ "\"registro_back_img\":\"Lighthouse.jpg\" } }");
//		return Response
//				.ok(s)
//				.build();
//		
//		
//	}
	
	@Override
	public Response obtenerDataTenant(String tenant) {
		
		DataTenant dt = new DataTenant();
		
		try{
			
			dt = iemc.obtenerDataTenant(tenant);
			
			if(dt == null)
				return Response.status(Status.NOT_FOUND).build();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return Response
				.ok(dt)
				.build();
		
		
	}
	

}
