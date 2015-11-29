package com.sgem.utilidades;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import javax.activation.MimetypesFileTypeMap;
import javax.ws.rs.core.MultivaluedMap;

import org.apache.commons.io.IOUtils;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;

import com.sgem.dominio.Imagen;
import com.sgem.seguridad.excepciones.AplicacionException;

public class ImagenUtil {
	
	// -Dimg.folder=C:\Users\USUARIO\git\ProyectoSGEM\GestionEventoMultideportivo\WebContent\resources\defecto\img\\" program argument en servidor.

	private static final String FILE_PATH = System.getProperties().getProperty("img.folder").trim();

	private static final String TENANT = "Tenant";
	
	private static final String NOVEDAD_DIR = "novedades";
	private static final String COMITE_DIR = "comite_olimpico";
	private static final String DEPORTISTA_DIR = "deportistas";
	private static final String DEPORTE_DIR = "deportes";
	private static final String CONFIGURACION_DIR = "configuracion";
		
	public static String getNovedadFilePath(MultivaluedMap<String, String> header, String tenantId) {

		String[] contentDisposition = header.getFirst("Content-Disposition").split(";");
		
		for (String filename : contentDisposition) {
			if ((filename.trim().startsWith("filename"))) {
				String fileName = getFileName(filename);
				return FILE_PATH +TENANT+tenantId+File.separator+NOVEDAD_DIR+File.separator+fileName;
			}
		}
		return "unknown";
	}
	
	public static String getComiteFilePath(MultivaluedMap<String, String> header, String tenantId, String comiteId) {
		
		String[] contentDisposition = header.getFirst("Content-Disposition").split(";");
		
		for (String filename : contentDisposition) {
			if ((filename.trim().startsWith("filename"))) {
				String fileName = getFileName(filename);
				return FILE_PATH +TENANT+tenantId+File.separator+COMITE_DIR+comiteId+File.separator+fileName;
			}
		}
		return "unknown";
	}

	public static String getDeportistaFilePath(MultivaluedMap<String, String> header, String tenantId, String comiteId) {
		String[] contentDisposition = header.getFirst("Content-Disposition").split(";");
		
		for (String filename : contentDisposition) {
			if ((filename.trim().startsWith("filename"))) {
				String fileName = getFileName(filename);
				return FILE_PATH +TENANT+tenantId+File.separator+COMITE_DIR+comiteId+File.separator+DEPORTISTA_DIR+File.separator+fileName;
			}
		}
		return "unknown";
	}
	
	public static String getDeporteFilePath(MultivaluedMap<String, String> header, String tenantId, String eventoDeportivoID) {
		String[] contentDisposition = header.getFirst("Content-Disposition").split(";");
		
		for (String filename : contentDisposition) {
			if ((filename.trim().startsWith("filename"))) {
				String fileName = getFileName(filename);
				return FILE_PATH +TENANT+tenantId+File.separator+DEPORTE_DIR+File.separator+fileName;
			}
		}
		return "unknown";
	}
		
	public static String getConfigFilePath(MultivaluedMap<String, String> header, String tenantId) {
		String[] contentDisposition = header.getFirst("Content-Disposition").split(";");
		
		for (String filename : contentDisposition) {
			if ((filename.trim().startsWith("filename"))) {
				String fileName = getFileName(filename);
				return FILE_PATH +TENANT+tenantId+File.separator+CONFIGURACION_DIR+File.separator+fileName;
			}
		}
		return "unknown";
	}
	
	public static String getDirectoryName(String tenantId) {
		return FILE_PATH +TENANT+tenantId;
	}
	
	public static String getNovedadDirectoryName(String tenantId) {
		return FILE_PATH +TENANT+tenantId+File.separator+NOVEDAD_DIR+File.separator;
	}
	
	public static String getComiteDirectoryName(String tenantId, String comiteId) {
		return FILE_PATH +TENANT+tenantId+File.separator+COMITE_DIR+comiteId+File.separator;
	}
	
	public static String getDeportistaDirectoryName(String tenantId, String comiteId) {
		return FILE_PATH +TENANT+tenantId+File.separator+COMITE_DIR+comiteId+File.separator+DEPORTISTA_DIR +File.separator;
	}
	
	public static String getDeporteDirectoryName(String tenantId, String eventoDeportivoID) {
		return FILE_PATH +TENANT+tenantId+File.separator+DEPORTE_DIR+File.separator;
	}
	
	public static String getConfigDirectoryName(String tenantId) {
		return FILE_PATH +TENANT+tenantId+File.separator+CONFIGURACION_DIR+File.separator;
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

	private static String getFileName(String filename){
		String[] name = filename.split("=");
		String finalFileName = name[1].trim().replaceAll("\"", "");
		String ext = "."+(finalFileName.split("\\."))[1];
		
		return UUID.randomUUID().toString()+ext;
	}
	

}
