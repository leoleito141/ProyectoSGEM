package com.sgem.dominio;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.sgem.persistencia.IUsuarioDAO;


@WebListener
public class InicioApp implements ServletContextListener {
   
@EJB
IUsuarioDAO  UsuarioDAO;


   
    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        System.out.println("");
        System.out.println("Iniciando aplicacion--------Ver: utiles.InicioApp");
        System.out.println("*****************************");
        System.out.println("******* Test mensajes *******");
        System.out.println("*****************************");
        System.out.println("Creando usuario userTest (si no existe)...");
        
        Usuario usuario = null;
		Usuario usuario2 = null;
		Usuario usuario3 = null;
		Usuario usuario4 = null;

			usuario = new Admin();
			usuario.setNombre("dsa");
			usuario.setApellido("dsa");
			usuario.setEdad(321);
			usuario.setEmail("dsa");
			usuario.setCanalYoutube("dsa");
			usuario.setTwitter("dsa");
			usuario.setFacebook("dsa");
			usuario.setCedula(312312);
			usuario.setPassword("dsa");
			
			usuario2 = new Organizador();
			usuario2.setNombre("dsa2");
			usuario2.setApellido("dsa2");
			usuario2.setEdad(123);
			usuario2.setEmail("dsa2");
			usuario2.setCanalYoutube("dsa2");
			usuario2.setTwitter("dsa2");
			usuario2.setFacebook("dsa2");
			usuario2.setCedula(321321);
			usuario2.setPassword("dsa2");
			
			usuario3 = new Admin();
			usuario3.setNombre("dsa3");
			usuario3.setApellido("dsa3");
			usuario3.setEdad(321);
			usuario3.setEmail("dsa3");
			usuario3.setCanalYoutube("dsa3");
			usuario3.setTwitter("dsa3");
			usuario3.setFacebook("dsa3");
			usuario3.setCedula(312312);
			usuario3.setPassword("dsa3");
			
			usuario4 = new Organizador();
			usuario4.setNombre("dsa4");
			usuario4.setApellido("dsa4");
			usuario4.setEdad(321);
			usuario4.setEmail("dsa4");
			usuario4.setCanalYoutube("dsa4");
			usuario4.setTwitter("dsa4");
			usuario4.setFacebook("dsa4");
			usuario4.setCedula(312312);
			usuario4.setPassword("dsa4");
			
			UsuarioDAO.guardarUsuario(usuario2);			
			UsuarioDAO.guardarUsuario(usuario);  
			UsuarioDAO.guardarUsuario(usuario3); 
			UsuarioDAO.guardarUsuario(usuario4); 
       
			System.out.println("Alta de 4 usuarios completa");
			
			System.out.println("Obtengo el usuario dsa3");			
			Usuario u = UsuarioDAO.buscarUsuario("dsa3");
			System.out.println("obtuve el usuario "+u.getEmail()+" "+u.getId());
			System.out.println(" y es "+u.soy());
			
			System.out.println("Obtengo el usuario dsa4");			
			Usuario u2 = UsuarioDAO.buscarUsuario("dsa4");
			System.out.println("obtuve el usuario "+u2.getEmail()+" "+u2.getId());
			System.out.println(" y es "+u2.soy());
			
    }

}