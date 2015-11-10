package com.sgem.utilidades;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.Scanner;

public class IpUtil {
	
	public static String getPublicIpAddress() {
	    String res = null;
	    try {
	        String localhost = InetAddress.getLocalHost().getHostAddress();
	        res = localhost;
//	        Enumeration<NetworkInterface> e = NetworkInterface.getNetworkInterfaces();
//	        while (e.hasMoreElements()) {
//	            NetworkInterface ni = (NetworkInterface) e.nextElement();
//	            if(ni.isLoopback())
//	                continue;
//	            if(ni.isPointToPoint())
//	                continue;
//	            Enumeration<InetAddress> addresses = ni.getInetAddresses();
//	            while(addresses.hasMoreElements()) {
//	                InetAddress address = (InetAddress) addresses.nextElement();
//	                if(address instanceof Inet4Address) {
//	                    String ip = address.getHostAddress();
//	                    if(!ip.equals(localhost))
//	                    	res = ip;
////	                        System.out.println("Mi ip: " +(res = ip));
//	                }
//	            }
//	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return res;
	}
	
	
	public static String verificarIp(File file, String miIp){
		int lineNum = 0;
	    String ip = null;
	    
		try {
		    Scanner scanner = new Scanner(file);			   
		    while (scanner.hasNextLine()) {
		        String line = scanner.nextLine();
		        lineNum++;
		        if(line.contains("sgem-eventos.com")) { 
//		            System.out.println("Esta en la linea nro: " +lineNum);
//		            System.out.println(line.toString().trim());
		            
		            ip = (line.toString().trim().substring(0,line.toString().trim().indexOf(' ')+1)).trim();
		            String host = (line.toString().trim().substring(line.toString().trim().indexOf(' ')+1)).trim();
		           
//		            System.out.println(ip);
//		            System.out.println(host);
		        }
		    }			           
		    
		} catch(FileNotFoundException e) { 
		    return miIp;
		}
		
         return miIp.equals(ip) ? new String() : ip;	   
	}
	
	public static String leerArchivo(File file) throws IOException {

//	    File file = new File(pathname);
	    StringBuilder fileContents = new StringBuilder((int)file.length());
	    Scanner scanner = new Scanner(file);
	    String lineSeparator = System.getProperty("line.separator");

	    try {
	        while(scanner.hasNextLine()) {        
	            fileContents.append(scanner.nextLine() + lineSeparator);
	        }
	        return fileContents.toString();
	    } finally {
	        scanner.close();
	    }
	}
	
	public static boolean modificarIp(File file,String ipAnterior, String ipNueva){
		
		Path path = Paths.get(file.getPath());
		Charset charset = StandardCharsets.UTF_8;
		
        String content;
		try {
			content = new String(leerArchivo(file));			
            content = content.replaceAll(ipAnterior, ipNueva);
            Files.write(path, content.getBytes(charset));
            
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
}
