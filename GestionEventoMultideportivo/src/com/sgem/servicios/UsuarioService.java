package com.sgem.servicios;


import java.io.IOException;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.sgem.controladores.IUsuarioController;
import com.sgem.datatypes.DataUsuario;
import com.sgem.dominio.Usuario;

@Stateless
public class UsuarioService implements IUsuarioService{
 
@EJB
private IUsuarioController iuc;

	// localhost:8080/GestionEventoMultideportivo/rest/ServicioUsuario/status/
	public Response getStatus() {
		return Response
				.ok("{\"status\":\"El servicio de los usuarios esta funcionando...\"}")
				.build();
	}

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
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (JsonMappingException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//	
//		if(guardado){
//			return Response.ok("{\"status\":\"Usuario guardado\"}").build();
//		}else{
//			return Response.notModified("{\"status\":\"Usuario no guardado\"}").build();
//		} 
//	   
//	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/usuarioPrueba/{nombre}")
	public Response darUsuario(@PathParam("nombre") String nombre)
	{
		System.out.println("Entre get Usuario " + nombre);
		 JSONObject jsonObj = new JSONObject();

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
		 
		 
		 String json = jsonObj.toString(); 
		 return Response.ok(json).build();
	
		
	}
	
	public Response login(String datos) {

		String respuesta;
		System.out.println("Entre Login ");
		System.out.println("Usuario - " + datos);
		
		JSONObject jsonObj = new JSONObject();
		
		try {
			jsonObj.put("Login","true");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
			
		}

		respuesta=jsonObj.toString();
		return Response.ok().entity(respuesta).build();

	
	}
	

	public Response guardarUsuario(DataUsuario dataUsuario) {

		try {
			return Response.ok(iuc.guardarUsuario(dataUsuario)).build();

		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;

	}

	public Response obtenerPorId(long id) {
		try {
			iuc.buscarUsuario(id);

		} catch (Exception e) {
			e.printStackTrace();

		}

		return null;
	}

	
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
