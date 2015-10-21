package com.sgem.servicios;


import java.io.IOException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.jboss.resteasy.util.Base64;

import com.sgem.controladores.IUsuarioController;
import com.sgem.datatypes.DataComite;
import com.sgem.datatypes.DataEvento;
import com.sgem.datatypes.DataUsuario;
import com.sgem.seguridad.Token;

@Stateless
public class UsuarioService implements IUsuarioService{
 
@EJB
private IUsuarioController iuc;

	// localhost:8080/GestionEventoMultideportivo/rest/ServicioUsuario/status/
	@Override
	public Response getStatus() {
		return Response
				.ok("{\"status\":\"El servicio de los usuarios esta funcionando...\"}")
				.build();
	}
	
	@Override
	public Response login(DataUsuario dataUsuario) {

		System.out.println("Entre Login ");
		  try {
			  System.out.println("Usuario - " + dataUsuario.toString() + " Password : " + new String(Base64.decode(dataUsuario.getPassword())));
          } catch (IOException e) {
        	  e.printStackTrace();
          }
		
		Token jwt;
		try{
		
			jwt = iuc.loginAdmin(dataUsuario);
		
		}catch(Exception e){
			e.printStackTrace();
			return Response.status(404).entity("El usuario con email '"+dataUsuario.getEmail()+"' no existe.").build();	
		}
		
//		if(jwt == null){
//			// hay que decir que no se encontro.
//			
//		}	
		
		
		return Response.ok(jwt).build();

	
	}
	
	@Override
	public Response guardarUsuario(DataUsuario dataUsuario) {

		try {
			
			if(iuc.guardarUsuario(dataUsuario)){
				return Response.ok(new Boolean(true)).build();				
			}
			return Response.status(Status.FOUND).entity(new Boolean(false)).build();

		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}	

	}
	
	
	public Response altaComite(DataComite dataComite) {

		try {
			return Response.ok(iuc.guardarComite(dataComite)).build();

		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;

	}

	@Override
	public Response loginUsuario(DataUsuario dataUsuario) {
			
		Token jwt = null;
		try{		
			jwt = iuc.loginUsuario(dataUsuario);
		}catch(Exception e){
			e.printStackTrace();	
		}
		
		if (jwt == null){
			return Response.status(Status.NOT_FOUND).build();	
		}
		
		return Response.ok(jwt).build();

	}

	
//	@GET
//	@Produces(MediaType.APPLICATION_JSON)
//	@Path("/usuarioPrueba/{nombre}")
//	public Response darUsuario(@PathParam("nombre") String nombre)
//	{
//		System.out.println("Entre get Usuario " + nombre);
//		 JSONObject jsonObj = new JSONObject();
//
//		 try {
//			Usuario usuario = iuc.buscarUsuario(nombre);
//			System.out.println("Entre get Usuario ");
//			System.out.println("Usuario - " +usuario.getNombre());
//			jsonObj.put("Nombre",usuario.getNombre());
//			jsonObj.put("Apellido",usuario.getApellido());
//			jsonObj.put("Edad",usuario.getEdad());
//			jsonObj.put("Cedula",usuario.getCedula());
//			jsonObj.put("Password", usuario.getPassword());
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		 
//		 
//		 String json = jsonObj.toString(); 
//		 return Response.ok(json).build();
//	
//		
//	}
	

}
