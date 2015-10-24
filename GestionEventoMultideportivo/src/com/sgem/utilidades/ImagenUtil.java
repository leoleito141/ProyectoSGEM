package com.sgem.utilidades;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.apache.commons.io.IOUtils;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

public class ImagenUtil {
	
	private static final String FILE_PATH = "C:\\Users\\USUARIO\\git\\ProyectoSGEM\\GestionEventoMultideportivo\\WebContent\\resources\\defecto\\img\\";
	
	public static String getFileName(MultivaluedMap<String, String> header, int tenantId) {

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

	public static String getDirectoryName(int tenantId) {
		return FILE_PATH +"Tenant"+tenantId;
	}
	
	public static void writeFile(byte[] content, String filename, String dir ) throws IOException {

		File directorio = new File(dir);
		File file = new File(filename);
	

		if (!directorio.exists()) {
			if (directorio.mkdir()) {
				file.createNewFile();
				System.out.println("Directory is created!");
			} else {
				System.out.println("Failed to create directory!");
			}
			//file.createNewFile();
		}

		FileOutputStream fop = new FileOutputStream(file);

		fop.write(content);
		fop.flush();
		fop.close();

	}

	
	// falta la de borrar imagen..
}
