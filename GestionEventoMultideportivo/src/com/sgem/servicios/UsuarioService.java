package com.sgem.servicios;


import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import com.sgem.controladores.IUsuarioController;
import com.sgem.datatypes.DataComite;
import com.sgem.datatypes.DataJuez;
import com.sgem.datatypes.DataNovedad;
import com.sgem.datatypes.DataUsuario;
import com.sgem.seguridad.excepciones.AplicacionException;
import com.sgem.seguridad.excepciones.UsuarioNoEncontradoException;
import com.sgem.seguridad.excepciones.UsuarioYaExisteException;

@Stateless
public class UsuarioService implements IUsuarioService{
 
	@EJB
	private IUsuarioController iuc;
	
	@Override
	public Response getStatus() { // localhost:8080/GestionEventoMultideportivo/rest/ServicioUsuario/status/
		return Response
				.ok("{\"status\":\"El servicio de los usuarios esta funcionando...\"}")
				.build();
	}
	
	@Override
	public Response loginAdmin(DataUsuario dataUsuario) {
		try{		
			return Response.ok(iuc.loginAdmin(dataUsuario)).build();
		}catch(UsuarioNoEncontradoException e){
			return Response.status(Status.NOT_FOUND).build();	
		} catch (AplicacionException e) {
			return Response.serverError().build();
		}
	}
		
	@Override
	public Response loginAndroid(DataUsuario dataUsuario) {
		try{
			return Response.ok(iuc.loginAndroid(dataUsuario)).build();
		}catch(UsuarioNoEncontradoException e){
			return Response.status(Status.NOT_FOUND).build();	
		} catch (AplicacionException e) {
			return Response.serverError().build();
		}	
	}

	@Override
	public Response loginIonic(DataJuez dataJuez) {
		try{
			return Response.ok(iuc.loginIonic(dataJuez)).build();
		}catch(UsuarioNoEncontradoException e){
			return Response.status(Status.NOT_FOUND).build();	
		} catch (AplicacionException e) {
			return Response.serverError().build();
		}	
	}
	
	@Override
	public Response loginUsuario(DataUsuario dataUsuario) {
		try{
			return Response.ok(iuc.loginUsuario(dataUsuario)).build();
		}catch(UsuarioNoEncontradoException e){
			return Response.status(Status.NOT_FOUND).build();	
		} catch (AplicacionException e) {
			return Response.serverError().build();
		}	
	}	
	
	@Override
	public Response guardarUsuario(DataUsuario dataUsuario) {
		try {						
			return Response.ok(iuc.guardarUsuario(dataUsuario)).build();				
		} catch (UsuarioYaExisteException e) {
			return Response.status(Status.FOUND).entity(new Boolean(false)).build();
		} catch (AplicacionException e) {
			return Response.serverError().build();
		}
	}
	
	@Override
	public Response altaComite(DataComite dataComite) {		
		try {
			return Response.ok(iuc.guardarComite(dataComite)).build();
		} catch (UsuarioYaExisteException e) {
			return Response.status(Status.FOUND).entity(new Boolean(false)).build();
		} catch (AplicacionException e) {
			return Response.serverError().build();
		}		
	}	
	
	@Override
	public Response subirImagenComite(MultipartFormDataInput input) {
		try {						
			return Response.ok(iuc.subirImagenComite(input)).build();	
		} catch (AplicacionException e) {
			return Response.serverError().build();
		}	
	}

	@Override
	public Response guardarNovedad(DataNovedad dataNovedad) {
		try {						
			return Response.ok(iuc.guardarNovedad(dataNovedad)).build();				
		} catch (UsuarioNoEncontradoException e) {
			return Response.status(Status.NOT_FOUND).build();	
		} catch (AplicacionException e) {
			return Response.serverError().build();
		}
	}
	
	@Override
	public Response subirImagenNovedad(MultipartFormDataInput input) {		
		try {						
			return Response.ok(iuc.subirImagenNovedad(input)).build();	
		} catch (AplicacionException e) {
			return Response.serverError().build();
		}	
	}

//	@Override
//	public Response guardarEstado(DataHistorialLogin hl) {
//		try {						
//			return Response.ok(iuc.guardarEstado(hl)).build();	
//		} catch (AplicacionException e) {
//			return Response.serverError().build();
//		} catch (UsuarioNoEncontradoException e) {
//			return Response.status(Status.NOT_FOUND).build();	
//		}	
//	}

	@Override
	public Response obtenerHistorial(String tenantId) {
		try {						
			return Response.ok(iuc.obtenerHistorial(Integer.parseInt(tenantId))).build();	
		} catch (AplicacionException e) {
			return Response.serverError().build();
		}
	}

	@Override
	public Response obtenerCantidadRegistrados(String tenantId) {
		try {						
			return Response.ok(iuc.cantidadRegistrados(Integer.parseInt(tenantId))).build();	
		} catch (AplicacionException e) {
			return Response.serverError().build();
		}
	}

	@Override
	public Response altaJuez(DataUsuario Usuario) {
		try {
			return Response.ok(iuc.guardarJuez(Usuario)).build();

		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;
	}

	@Override
	public Response listarJueces(Integer tenantId) {
		try {
			return Response.ok(iuc.listarJueces(tenantId)).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}



	

	
}
