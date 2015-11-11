package com.sgem.utilidades;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.activation.MimetypesFileTypeMap;
import javax.ws.rs.core.MultivaluedMap;

import org.apache.commons.io.IOUtils;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;

import com.sgem.dominio.Imagen;
import com.sgem.seguridad.excepciones.AplicacionException;

public class ImagenUtil {
	

	private static final String FILE_PATH = System.getProperties().getProperty("img.folder").trim();

	private static final String NOVEDADES_DIR = "novedades";
	private static final String COMITE_DIR = "comite_olimpico";
	private static final String DEPORTISTAS_DIR = "deportistas";
	private static final String CONFIGURACION_DIR = "configuracion";
	
	public static String getFileName(MultivaluedMap<String, String> header, String tenantId) {

		String[] contentDisposition = header.getFirst("Content-Disposition").split(";");
		
		for (String filename : contentDisposition) {
			if ((filename.trim().startsWith("filename"))) {

				String[] name = filename.split("=");
				
				String finalFileName = name[1].trim().replaceAll("\"", "");
				return FILE_PATH +"Tenant"+tenantId+"\\"+finalFileName;
			}
		}
		return "unknown";
	}
	
	public static String getNovedadFilePath(MultivaluedMap<String, String> header, String tenantId) {

		String[] contentDisposition = header.getFirst("Content-Disposition").split(";");
		
		for (String filename : contentDisposition) {
			if ((filename.trim().startsWith("filename"))) {

				String[] name = filename.split("=");
				
				String finalFileName = name[1].trim().replaceAll("\"", "");
				return FILE_PATH +"Tenant"+tenantId+"\\"+NOVEDADES_DIR+"\\"+finalFileName;
			}
		}
		return "unknown";
	}
	
	public static String getComiteFilePath(MultivaluedMap<String, String> header, String tenantId, String comiteId) {
		
		String[] contentDisposition = header.getFirst("Content-Disposition").split(";");
		
		for (String filename : contentDisposition) {
			if ((filename.trim().startsWith("filename"))) {

				String[] name = filename.split("=");
				
				String finalFileName = name[1].trim().replaceAll("\"", "");
				return FILE_PATH +"Tenant"+tenantId+"\\"+COMITE_DIR+comiteId+"\\"+finalFileName;
			}
		}
		return "unknown";
	}

	public static String getDeportistaFilePath(MultivaluedMap<String, String> header, String tenantId, String comiteId) {
		String[] contentDisposition = header.getFirst("Content-Disposition").split(";");
		
		for (String filename : contentDisposition) {
			if ((filename.trim().startsWith("filename"))) {

				String[] name = filename.split("=");
				
				String finalFileName = name[1].trim().replaceAll("\"", "");
				return FILE_PATH +"Tenant"+tenantId+"\\"+COMITE_DIR+comiteId+"\\"+DEPORTISTAS_DIR+"\\"+finalFileName;
			}
		}
		return "unknown";
	}
		
	public static String getConfigFilePath(MultivaluedMap<String, String> header, String tenantId) {
		String[] contentDisposition = header.getFirst("Content-Disposition").split(";");
		
		for (String filename : contentDisposition) {
			if ((filename.trim().startsWith("filename"))) {

				String[] name = filename.split("=");
				
				String finalFileName = name[1].trim().replaceAll("\"", "");
				return FILE_PATH +"Tenant"+tenantId+"\\"+CONFIGURACION_DIR+"\\"+finalFileName;
			}
		}
		return "unknown";
	}
	
	public static String getDirectoryName(String tenantId) {
		return FILE_PATH +"Tenant"+tenantId;
	}
	
	public static String getNovedadDirectoryName(String tenantId) {
		return FILE_PATH +"Tenant"+tenantId+"\\"+NOVEDADES_DIR+"\\";
	}
	
	public static String getComiteDirectoryName(String tenantId, String comiteId) {
		return FILE_PATH +"Tenant"+tenantId+"\\"+COMITE_DIR+comiteId+"\\";
	}
	
	public static String getDeportistaDirectoryName(String tenantId, String comiteId) {
		return FILE_PATH +"Tenant"+tenantId+"\\"+COMITE_DIR+comiteId+"\\"+DEPORTISTAS_DIR +"\\";
	}
	
	public static String getConfigDirectoryName(String tenantId) {
		return FILE_PATH +"Tenant"+tenantId+"\\"+CONFIGURACION_DIR+"\\";
	}
	
	public static File writeFile(byte[] content, String filename, String dir ) throws IOException {

		File directorio = new File(dir);
		File file = new File(filename);	

		if (!directorio.exists()) {
			if (directorio.mkdir()) {
				file.createNewFile();
				System.out.println("Directory is created!");
			} else {
				System.out.println("Failed to create directory!");
			}
		}

		FileOutputStream fop = new FileOutputStream(file);

		fop.write(content);
		fop.flush();
		fop.close();
		
		return file;		
	}

	public static String getMimeType(File f){ 
		return (new MimetypesFileTypeMap().getContentType(f));
	}
	
	public static boolean borrarImagen(String path) throws IOException {
		
		File file = new File(path);
		return file.delete();	
		
	}

	public static Imagen salvarImagen(List<InputPart> inputParts, String tenantId) throws AplicacionException{
		String fileName ="";
		File f = null;
		try {
				
			for (InputPart inputPart : inputParts) {		
				
				MultivaluedMap<String, String> header = inputPart.getHeaders();
				
				fileName = ImagenUtil.getConfigFilePath(header,tenantId);
	
				InputStream inputStream = inputPart.getBody(InputStream.class,null);				
	
				byte [] bytes = IOUtils.toByteArray(inputStream);
				
				String dir = ImagenUtil.getConfigDirectoryName(tenantId);
				
				f = writeFile(bytes,fileName,dir);			
			}
		}catch (IOException e) {
			throw new AplicacionException("Error al subir la imagen");
		}			
		
		Imagen image = new Imagen(getMimeType(f), fileName,Integer.parseInt(tenantId)); 
		
		return image;
	} 


	

}
