package com.sgem.utilidades;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.sgem.dominio.Admin;
import com.sgem.dominio.ComiteOlimpico;
import com.sgem.dominio.Deportista;
import com.sgem.dominio.EventoDeportivo;
import com.sgem.dominio.EventoMultideportivo;
import com.sgem.dominio.Organizador;
import com.sgem.dominio.TenantHandler;
import com.sgem.dominio.Usuario;
import com.sgem.persistencia.IDeportistaDAO;
import com.sgem.persistencia.IEventoDeportivoDAO;
import com.sgem.persistencia.IEventoMultiDAO;
import com.sgem.persistencia.IUsuarioDAO;


@WebListener
public class InicioApp implements ServletContextListener {
   
@EJB
IUsuarioDAO  UsuarioDAO;

@EJB
IDeportistaDAO  DeportistaDAO;

@EJB
IEventoMultiDAO  EventoMultiDAO;

@EJB
IEventoDeportivoDAO  EventoDeportivoDAO;


   
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
        
        System.out.println("*****************************");
        System.out.println("*****Comienza Verificacion ip EventosSGEM*****");
//        
        String miIp = IpUtil.getPublicIpAddress();
		System.out.println("Mi ip: " + miIp);
		
		File file = new File("C:\\Windows\\System32\\drivers\\etc\\hosts");
		System.out.println("Path : " + file.getAbsolutePath());
				
		String ipVieja = IpUtil.verificarIp(file,miIp);
		if(!ipVieja.isEmpty()){
			System.out.println("Su ip cambio, se modificara ip en hosts!");
			System.out.println("De "+ipVieja+ " a "+miIp);
			
			if(IpUtil.modificarIp(file, ipVieja,miIp))
				System.out.println("Se modifico la ip con exito!");
			
		}else{
			System.out.println("Ip esta bien, no hay que modificar!");
		}
        
        System.out.println("*****Finaliza Verificacion ip EventosSGEM*****");        
        
        System.out.println("********* Usuarios *********");
        System.out.println("*****************************");
        System.out.println("*****************************");
        System.out.println("Creando usuario userTest (si no existe)...");
        
        Usuario admin = null;
        Usuario comite = null;
//		Usuario usuario3 = null;
//		Usuario usuario4 = null;

		admin = new Admin();
	//	admin.setNombre("Admin");
	//	admin.setApellido("Admin");
	//	admin.setEdad(0);
		admin.setEmail("admin@gmail.com");
		admin.setCanalYoutube("admin");
		admin.setTwitter("admin");
		admin.setFacebook("admin");
//		admin.setCedula(0);
		admin.setPassword("123");
		admin.setTenantID(0);			
			
		comite = new ComiteOlimpico();
		((ComiteOlimpico)comite).setPais("Uruguay"); // para evitar esto, se declara a usuario2 como: 
													   //ComiteOlimpico usuario 2 = new ComiteOlimpico();
		((ComiteOlimpico)comite).setCodigo("COU");
	//	comite.setNombre("Comit� Ol�mpico Uruguayo");
	//	comite.setApellido("Rep�blica Oriental del Uruguay");
	//	comite.setEdad(123);
		comite.setEmail("cou@gmail.com");
		comite.setCanalYoutube("uruguay");
		comite.setTwitter("uruguay");
		comite.setFacebook("Comit� Ol�mpico Uruguayo");
	//	comite.setCedula(0);
		comite.setPassword("cou123");
		comite.setTenantID(5);			
		
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
		
			
		//	usuario4.setNombre("dsa4");
		//	usuario4.setApellido("dsa4");
		//	usuario4.setEdad(321);
		//	usuario4.setEmail("dsa4");
		//	usuario4.setCanalYoutube("dsa4");
		//	usuario4.setTwitter("dsa4");
		//	usuario4.setFacebook("dsa4");
	//		usuario4.setCedula(312312);
		//	usuario4.setPassword("dsa4");
		//	usuario4.setTenantID(1);			
		
		UsuarioDAO.guardarUsuario(admin);  
		UsuarioDAO.guardarUsuario(comite);			
//			UsuarioDAO.guardarUsuario(usuario3); 
	//	UsuarioDAO.guardarUsuario(usuario4); 
   
		System.out.println("Alta de 4 usuarios completa");
			
		System.out.println("Obtengo el usuario dsa2");			
		ComiteOlimpico u2 = (ComiteOlimpico) UsuarioDAO.buscarUsuario(5,"cou@gmail.com");
		System.out.println("obtuve el usuario "+u2.getEmail()+" "+u2.getId());
		System.out.println(" y es "+u2.soy());
		
		Deportista d = new Deportista(1, "Juan", "Perez",new Date(), "Masulino",null,null);			
		Deportista d1 = new Deportista(1, "Jose", "Lopez",new Date(), "Masulino",null,null);		
		Deportista d2 = new Deportista(1, "Pedro", "Fernandez",new Date(), "Masulino",null,null);			
		Deportista d3 = new Deportista(1, "Debo", "Rodriguez",new Date(), "Femenino",null,null);			

		

		
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
		
		co = (ComiteOlimpico) UsuarioDAO.buscarUsuario(5,"cou@gmail.com");
		
		if(co != null){
			for (Deportista dep : co.getDeportistas()) {
				System.out.println(dep.getNombre());
			}
		}else{
			System.out.println("No se encontro al usuario con email: "+co.getEmail());
		}
		
		TenantHandler th = new TenantHandler();
		EventoMultideportivo evento = new EventoMultideportivo("SOCHI", "sochi", "logo.jpg", new Date(), new Date(), "facebook/sochi", "#sochi", "youtube/sochi", "sochi.css");
		List<EventoMultideportivo> listevento = new ArrayList<EventoMultideportivo>();
		
		
		
		

	//	Organizador org =(Organizador)UsuarioDAO.buscarUsuario(1,"dsa4");
		
		Organizador usuario4 = new Organizador();
		usuario4.setEmail("organizador@gmail");
		usuario4.setPassword("123");
		usuario4.setTenantID(5);
		usuario4.setEvento(evento);
		
		evento.setOrganizador(usuario4);
		evento.setTenant(th);
		listevento.add(evento);
		th.setEventos(listevento);
		
		
		
		EventoMultiDAO.guardarTenant(th);

		EventoDeportivo futbol = new EventoDeportivo(5, "Futbol", null, new Date(), new Date(), "Masculino", null, null, null);
		EventoDeportivoDAO.guardarEventoDeportivo(futbol,evento);
		
		EventoDeportivo basket = new EventoDeportivo(5, "Basket", null, new Date(), new Date(), "Masculino", null, null, null);
		EventoDeportivoDAO.guardarEventoDeportivo(basket,evento);
		
		EventoDeportivo basketF = new EventoDeportivo(5, "Basket", null, new Date(), new Date(), "Femenino", null, null, null);
		EventoDeportivoDAO.guardarEventoDeportivo(basketF,evento);
		
		EventoDeportivo Natacion1 = new EventoDeportivo(5, "Natacion", "100M Libres", new Date(), new Date(), "Femenino", null, null, null);
		EventoDeportivoDAO.guardarEventoDeportivo(Natacion1,evento);
		
		EventoDeportivo Natacion2 = new EventoDeportivo(5, "Natacion", "200M Mariposa", new Date(), new Date(), "Femenino", null, null, null);
		EventoDeportivoDAO.guardarEventoDeportivo(Natacion2,evento);
		
		EventoDeportivo Natacion3 = new EventoDeportivo(5, "Natacion", "Posta 4x100", new Date(), new Date(), "Femenino", null, null, null);
		EventoDeportivoDAO.guardarEventoDeportivo(Natacion3,evento);
	
    }
    
    
    
    

}