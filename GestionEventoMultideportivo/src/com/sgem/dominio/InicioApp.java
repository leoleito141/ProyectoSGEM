package com.sgem.dominio;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.sgem.persistencia.IDeportistaDAO;
import com.sgem.persistencia.IEventoDAO;
import com.sgem.persistencia.IUsuarioDAO;


@WebListener
public class InicioApp implements ServletContextListener {
   
@EJB
IUsuarioDAO  UsuarioDAO;

@EJB
IDeportistaDAO  DeportistaDAO;

@EJB
IEventoDAO  EventoDAO;


   
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
        
        Usuario admin = null;
        Usuario comite = null;
//		Usuario usuario3 = null;
		Usuario usuario4 = null;

		admin = new Admin();
		admin.setNombre("Admin");
		admin.setApellido("Admin");
		admin.setEdad(0);
		admin.setEmail("admin@gmail.com");
		admin.setCanalYoutube("admin");
		admin.setTwitter("admin");
		admin.setFacebook("admin");
		admin.setCedula(0);
		admin.setPassword("123");
		admin.setTenantID(0);			
			
		comite = new ComiteOlimpico();
		((ComiteOlimpico)comite).setPais("Uruguay"); // para evitar esto, se declara a usuario2 como: 
													   //ComiteOlimpico usuario 2 = new ComiteOlimpico();
		((ComiteOlimpico)comite).setCodigo("COU");
		((ComiteOlimpico)comite).setPaginaWeb("http://www.cou.org.uy/cou/es/");
		comite.setNombre("Comit� Ol�mpico Uruguayo");
		comite.setApellido("Rep�blica Oriental del Uruguay");
		comite.setEdad(123);
		comite.setEmail("cou@gmail.com");
		comite.setCanalYoutube("uruguay");
		comite.setTwitter("uruguay");
		comite.setFacebook("Comit� Ol�mpico Uruguayo");
		comite.setCedula(0);
		comite.setPassword("cou123");
		comite.setTenantID(1);			
		
//			usuario3 = new Admin();
//			usuario3.setNombre("dsa3");
//			usuario3.setApellido("dsa3");
//			usuario3.setEdad(321);
//			usuario3.setEmail("dsa3");
//			usuario3.setCanalYoutube("dsa3");
//			usuario3.setTwitter("dsa3");
//			usuario3.setFacebook("dsa3");
//			usuario3.setCedula(312312);
//			usuario3.setPassword("dsa3");
		
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
		usuario4.setTenantID(1);			
		
		UsuarioDAO.guardarUsuario(admin);  
		UsuarioDAO.guardarUsuario(comite);			
//			UsuarioDAO.guardarUsuario(usuario3); 
		UsuarioDAO.guardarUsuario(usuario4); 
   
		System.out.println("Alta de 4 usuarios completa");
			
		System.out.println("Obtengo el usuario dsa2");			
		ComiteOlimpico u2 = (ComiteOlimpico) UsuarioDAO.buscarUsuario("cou@gmail.com");
		System.out.println("obtuve el usuario "+u2.getEmail()+" "+u2.getId());
		System.out.println(" y es "+u2.soy());
		
		Deportista d = new Deportista(0,0,"Dep1","1212","M");			
		Deportista d1 = new Deportista(0,0,"Dep2","1212","F");			
		Deportista d2 = new Deportista(0,0,"Dep3","1212","M");			
		Deportista d3 = new Deportista(0,0,"Dep4","1212","F");
		
		d.setComiteOlimpico(u2);
		d1.setComiteOlimpico(u2);
		d2.setComiteOlimpico(u2);
		d3.setComiteOlimpico(u2);
		
		u2.agregarDeportista(d);
		u2.agregarDeportista(d1);
		u2.agregarDeportista(d2);
		u2.agregarDeportista(d3);
		
		DeportistaDAO.guardarDeportista(d);
		DeportistaDAO.guardarDeportista(d1);
		DeportistaDAO.guardarDeportista(d2);
		DeportistaDAO.guardarDeportista(d3);
		
		System.out.println("Guarde deportistas");

		 
		System.out.println("BuscoLista");
		ComiteOlimpico co = null;
		
		co = (ComiteOlimpico) UsuarioDAO.buscarUsuario("cou@gmail.com");
		
		if(co != null){
			for (Deportista dep : co.getDeportistas()) {
				System.out.println(dep.getNombre());
			}
		}else{
			System.out.println("No se encontro al usuario con email: "+co.getEmail());
		}
		EventoMultideportivo evento = new EventoMultideportivo("SOCHI", "sochi", "logo.jpg", new Date(), new Date(), "facebook/sochi", "#sochi", "youtube/sochi", "sochi.css",1);
		
		Organizador org =(Organizador)UsuarioDAO.buscarUsuario("dsa4");
		
		evento.setOrganizador(org);
		EventoDAO.guardarEvento(evento);
		
		org.setEvento(evento);
		//UsuarioDAO.guardarUsuario(usuario4);
    }

}