package com.sgem.utilidades;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Correo
{     
    public boolean enviarMensajeConAuth(String host, Integer puerto, String origen, String destino, String password,
    		String asunto, String mensaje) throws AddressException, MessagingException
    {
    	
    	boolean modifico = false;
		try{
			    	
	    	Properties props = new Properties();
	        
	        props.setProperty("mail.smtp.host", host); //"smtp.gmail.com"
	        props.setProperty("mail.smtp.starttls.enable", "true");
	        props.setProperty("mail.smtp.port",puerto.toString()); //"587"
	        props.setProperty("mail.smtp.user", origen); //"ejemplo@gmail.com"
	        props.setProperty("mail.smtp.auth", "true");
	        
	        Session session = Session.getDefaultInstance(props);
	            
	        MimeMessage message = new MimeMessage(session);
	        message.setFrom(new InternetAddress(origen));
	        message.addRecipient(Message.RecipientType.TO, new InternetAddress(destino));
	        message.setSubject(asunto);
	        message.setText(mensaje);
	        Transport t = session.getTransport("smtp");
	        t.connect(origen,password);
	        t.sendMessage(message,message.getAllRecipients());       
	        t.close();
	        
	        modifico = true;
	        
		}catch(Exception e){
			e.printStackTrace();			
		}
		
		return modifico;
    }
    
}