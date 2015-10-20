package com.sgem.servicios;


import java.io.IOException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.Response;

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
		
			jwt = iuc.loginUsuario(dataUsuario);
		
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
			return Response.ok(iuc.guardarUsuario(dataUsuario)).build();

		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;

	}
	
	
	public Response altaComite(DataComite dataComite) {

		try {
			return Response.ok(iuc.guardarComite(dataComite)).build();

		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;

	}
	

 
//	public Response obtenerPorId(long id) {
//		try {
//			iuc.buscarUsuario(id);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//
//		}
//
//		return null;
//	}

	
//	@RolesAllowed("ADMIN")
//	@POST
//	@Produces(MediaType.APPLICATION_JSON)
//	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//	@Path("/usuario")
//	public Response guardarUsuario(String datos) {
//		
//		System.out.println("Entre  Usuario Service ");
//		System.out.println("Usuario - " + datos);
//		
//		ObjectMapper mapper = new ObjectMapper();
//		boolean guardado = false;
//		
//			try {
//				Usuario usuario = mapper.readValue(datos, Usuario.class);
//				guardado = iuc.guardarUsuario(usuario);
//			} catch (JsonParseException e) {
//				e.printStackTrace();
//				return Response.serverError().build();
//			} catch (JsonMappingException e) {
//				e.printStackTrace();
//				return Response.serverError().build();
//			} catch (IOException e) {
//				e.printStackTrace();
//				return Response.serverError().build();
//			} catch (ExcepcionSistema e) {
//				e.printStackTrace();
//				return Response.status(500).entity("Error - "+ e.getMensajeError()).build();
//			}
//	
//		if(guardado){
//			return Response.ok("{\"status\":\"Usuario guardado\"}").build();
//		}else{
//			return Response.notModified("{\"status\":\"Usuario no guardado\"}").build();
//		} 
//	   
//	}
	
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
