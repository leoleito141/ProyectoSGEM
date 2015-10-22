package com.sgem.servicios;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;





import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;




import com.ibm.wsdl.util.IOUtils;
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
		System.out.println("Entre alta evento" + datosEvento);		 
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
//		//el json de abajo podr�a ser un Data por nosotros que tenga todo lo del tenant asi ya transforma
//		//el json, va a ser enorme.. jaja, despues vemos. Deber�a ir contra la BD, y si no existe
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

	@Override
	public Response subirImagen(MultipartFormDataInput input) {
		System.out.println("Entre alta subirImagen" + input.toString());
		
//		String fileName = "";
//		
//		Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
//		List<InputPart> inputParts = uploadForm.get("uploadedFile");
//
//		for (InputPart inputPart : inputParts) {
//
//		 try {
//
//			MultivaluedMap<String, String> header = inputPart.getHeaders();
//			fileName = getFileName(header);
//
//			//convert the uploaded file to inputstream
//			InputStream inputStream = inputPart.getBody(InputStream.class,null);
//
//			byte [] bytes = IOUtils.toByteArray(inputStream);
//				
//			//constructs upload file path
//			fileName = UPLOADED_FILE_PATH + fileName;
//				
//			writeFile(bytes,fileName);
//				
//			System.out.println("Done");
//
//		  } catch (IOException e) {
//			e.printStackTrace();
//		  }
//
//		}
//
		return Response.status(200)
		    .entity("uploadFile is called, Uploaded file ").build();
		
	}
	
	
	/**
	 * header sample
	 * {
	 * 	Content-Type=[image/png], 
	 * 	Content-Disposition=[form-data; name="file"; filename="filename.extension"]
	 * }
	 **/
	//get uploaded filename, is there a easy way in RESTEasy?
	private String getFileName(MultivaluedMap<String, String> header) {

		String[] contentDisposition = header.getFirst("Content-Disposition").split(";");
		
		for (String filename : contentDisposition) {
			if ((filename.trim().startsWith("filename"))) {

				String[] name = filename.split("=");
				
				String finalFileName = name[1].trim().replaceAll("\"", "");
				return finalFileName;
			}
		}
		return "unknown";
	}

	//save to somewhere
	private void writeFile(byte[] content, String filename) throws IOException {

		File file = new File(filename);

		if (!file.exists()) {
			file.createNewFile();
		}

		FileOutputStream fop = new FileOutputStream(file);

		fop.write(content);
		fop.flush();
		fop.close();

	}
	
	

}
