package com.sgem.servicios;


import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.io.IOUtils;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import com.sgem.controladores.IUsuarioController;
import com.sgem.datatypes.DataComite;
import com.sgem.datatypes.DataNovedad;
import com.sgem.datatypes.DataUsuario;
import com.sgem.seguridad.excepciones.UsuarioNoEncontradoException;
import com.sgem.seguridad.excepciones.UsuarioYaExisteException;
import com.sgem.seguridad.jwt.Token;
import com.sgem.utilidades.ImagenUtil;

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

		Token jwt = null;
		try{		
			jwt = iuc.loginAdmin(dataUsuario);
		}catch(UsuarioNoEncontradoException e){
//			e.printStackTrace();	// esto lo sacamos desp.
			return Response.status(Status.NOT_FOUND).build();	
		}
		
		return Response.ok(jwt).build();
	}
	
	@Override
	public Response guardarUsuario(DataUsuario dataUsuario) {
		
		try {						
			return Response.ok(iuc.guardarUsuario(dataUsuario)).build();				
		} catch (UsuarioYaExisteException e) {
//			e.printStackTrace();
			return Response.status(Status.FOUND).entity(new Boolean(false)).build();
		}

	}
	
	@Override
	public Response loginUsuario(DataUsuario dataUsuario) {
			
		Token jwt = null;
		try{		
			jwt = iuc.loginUsuario(dataUsuario);
		}catch(UsuarioNoEncontradoException e){
//			e.printStackTrace();	// esto lo sacamos desp.
			return Response.status(Status.NOT_FOUND).build();	
		}
		
		return Response.ok(jwt).build();
	}	
	
	@Override
	public Response altaComite(DataComite dataComite) {

		try {
			return Response.ok(iuc.guardarComite(dataComite)).build();

		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;

	}	

	@Override
	public Response guardarNovedad(DataNovedad dataNovedad) {
		try {						
			return Response.ok(iuc.guardarNovedad(dataNovedad)).build();				
		} catch (UsuarioNoEncontradoException e) {
			return Response.status(Status.NOT_FOUND).build();	
		} catch (Exception e) {
			return Response.serverError().build();
		}

	}
	
	@Override
	public Response subirImagen(MultipartFormDataInput input) {
		
		String fileName = "";
		
		Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
		List<InputPart> inputParts = uploadForm.get("file");

		for (InputPart inputPart : inputParts) {

		 try {

			MultivaluedMap<String, String> header = inputPart.getHeaders();
			
			// hago de cuenta que 1 es el tenant id...
			fileName = ImagenUtil.getFileName(header,1);

			//convert the uploaded file to inputstream
			InputStream inputStream = inputPart.getBody(InputStream.class,null);

			byte [] bytes = IOUtils.toByteArray(inputStream);
				
//			String proxTenant = iemc.obtenerProximoTenant();
			
			String dir = ImagenUtil.getDirectoryName(1);
			
			System.out.println(" nombre file:   "+ fileName);
			ImagenUtil.writeFile(bytes,fileName,dir);
				
			System.out.println("Done");

		  } catch (IOException e) {
			e.printStackTrace();
		  }

		}

		return Response.status(200)
		    .entity("uploadFile is called, Uploaded file ").build();
		
	}
}
