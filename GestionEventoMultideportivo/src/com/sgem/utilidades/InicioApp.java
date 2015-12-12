package com.sgem.utilidades;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.sgem.dominio.Admin;
import com.sgem.dominio.ComiteOlimpico;
import com.sgem.dominio.Competencia;
import com.sgem.dominio.Deportista;
import com.sgem.dominio.Entrada;
import com.sgem.dominio.Estadistica;
import com.sgem.dominio.EventoDeportivo;
import com.sgem.dominio.EventoMultideportivo;
import com.sgem.dominio.HistorialLogin;
import com.sgem.dominio.Imagen;
import com.sgem.dominio.Juez;
import com.sgem.dominio.Novedad;
import com.sgem.dominio.Organizador;
import com.sgem.dominio.Pais;
import com.sgem.dominio.Ronda;
import com.sgem.dominio.TenantHandler;
import com.sgem.dominio.Usuario;
import com.sgem.dominio.UsuarioComun;
import com.sgem.persistencia.ICompetenciaDAO;
import com.sgem.persistencia.IDeportistaDAO;
import com.sgem.persistencia.IEntradaDAO;
import com.sgem.persistencia.IEventoDeportivoDAO;
import com.sgem.persistencia.IEventoMultiDAO;
import com.sgem.persistencia.IHistorialLoginDAO;
import com.sgem.persistencia.IImagenDAO;
import com.sgem.persistencia.INovedadDAO;
import com.sgem.persistencia.IRondaDAO;
import com.sgem.persistencia.IUsuarioDAO;


@WebListener
public class InicioApp implements ServletContextListener {
   
	@EJB
	private IUsuarioDAO  UsuarioDAO;
	
	@EJB
	private IDeportistaDAO  DeportistaDAO;
	
	@EJB
	private IEventoMultiDAO  EventoMultiDAO;
	
	@EJB
	private IEventoDeportivoDAO  EventoDeportivoDAO;
	
	@EJB
	private IHistorialLoginDAO  HistorialLoginDAO;
	
	@EJB
	private ICompetenciaDAO  CompetenciaDAO;
	
	@EJB
	private IRondaDAO RondaDAO;
	
	@EJB
	private IEntradaDAO EntradaDAO;
	
	@EJB
	private IImagenDAO ImagenDAO;
	
	@EJB
	private INovedadDAO NovedadDAO;
	
		
    @Override
    public void contextDestroyed(ServletContextEvent arg0) {}

    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        System.out.println("");
        System.out.println("Iniciando aplicacion--------Ver: com.sgem.utilidades.InicioApp");
        System.out.println("*****************************");
        System.out.println("******* Test mensajes *******");
        System.out.println("*****************************");
        
        System.out.println("*****************************");
        System.out.println("*****Comienza Verificacion ip EventosSGEM*****");
//        
        String miIp = IpUtil.getPublicIpAddress();
		System.out.println("Mi ip: " + miIp);
		
		File file = new File("C:\\Windows\\System32\\drivers\\etc\\hosts");
//		System.out.println("Path : " + file.getAbsolutePath());
				
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
        Usuario comite1 = null;
        Usuario comite2 = null;
		Usuario comite3 = null;
		Usuario comite4 = null;

		admin = new Admin();
		admin.setEmail("admin@gmail.com");
		admin.setCanalYoutube("admin");
		admin.setTwitter("admin");
		admin.setFacebook("admin");
		admin.setPassword("123");
		admin.setTenantID(0);			
			
		comite1 = new ComiteOlimpico();
		Pais p = new Pais("Uruguay","Montevideo");
		((ComiteOlimpico)comite1).setPais(p); // para evitar esto, se declara a usuario2 como: 
													   //ComiteOlimpico usuario 2 = new ComiteOlimpico();
		((ComiteOlimpico)comite1).setCodigo("URU");
		comite1.setEmail("cou@gmail.com");
		comite1.setCanalYoutube("uruguay");
		comite1.setTwitter("https://twitter.com/llamaceleste");
		comite1.setFacebook("https://www.facebook.com/pages/Uruguayan-Olympic-Committee/223236431139272?fref=ts&rf=215368038668221");
		comite1.setPassword("cou123");
		((ComiteOlimpico)comite1).setPaypal("N28JFDC8R7MEQ");
		comite1.setTenantID(1);			
		
		Imagen i1 = new Imagen("image/jpeg", "C:\\Users\\USUARIO\\git\\EventosSGEM\\EventosSGEM\\WebContent\\resources\\defecto\\img\\Tenant1\\comite_olimpico2\\cou.jpg", 1);
		ImagenDAO.guardarImagen(i1);
		
		((ComiteOlimpico)comite1).setLogo(i1);		
		
		comite2 = new ComiteOlimpico();
		Pais p2 = new Pais("Argentina","Buenos Aires");
		((ComiteOlimpico)comite2).setPais(p2); 
		((ComiteOlimpico)comite2).setCodigo("ARG");
		comite2.setEmail("coa@gmail.com");
		comite2.setCanalYoutube("Argentina");
		comite2.setTwitter("https://twitter.com/PrensaCOA");
		comite2.setFacebook("https://www.facebook.com/Comit%C3%A9-Ol%C3%ADmpico-Argentino-331659723531812/");
		comite2.setPassword("coa123");
		comite2.setTenantID(1);			
		
		Imagen i2 = new Imagen("image/jpeg", "C:\\Users\\USUARIO\\git\\EventosSGEM\\EventosSGEM\\WebContent\\resources\\defecto\\img\\Tenant1\\comite_olimpico3\\coa.jpg", 1);
		ImagenDAO.guardarImagen(i2);
		
		((ComiteOlimpico)comite2).setLogo(i2);
		
		
		comite3 = new ComiteOlimpico();
		Pais p3 = new Pais("Brasil","Rio de Janeiro");
		((ComiteOlimpico)comite3).setPais(p3); 
		((ComiteOlimpico)comite3).setCodigo("BRA");
		comite3.setEmail("cobra@gmail.com");
		comite3.setCanalYoutube("brasil");
		comite3.setTwitter("https://twitter.com/timebrasil");
		comite3.setFacebook("https://www.facebook.com/timebrasil");
		comite3.setPassword("123");
		comite3.setTenantID(1);			
		
		Imagen i3 = new Imagen("image/jpeg", "C:\\Users\\USUARIO\\git\\EventosSGEM\\EventosSGEM\\WebContent\\resources\\defecto\\img\\Tenant1\\comite_olimpico8\\cobra.jpg", 1);
		ImagenDAO.guardarImagen(i3);
		
		((ComiteOlimpico)comite3).setLogo(i3);
		
		comite4 = new ComiteOlimpico();
		Pais p4 = new Pais("España","Madrid");
		((ComiteOlimpico)comite4).setPais(p4); 
		((ComiteOlimpico)comite4).setCodigo("ESP");
		comite4.setEmail("coesp@gmail.com");
		comite4.setCanalYoutube("españa");
		comite4.setTwitter("https://twitter.com/COE_es");
		comite4.setFacebook("https://www.facebook.com/ComiteOlimpico");
		comite4.setPassword("123");
		comite4.setTenantID(1);			
		
		Imagen i4 = new Imagen("image/jpeg", "C:\\Users\\USUARIO\\git\\EventosSGEM\\EventosSGEM\\WebContent\\resources\\defecto\\img\\Tenant1\\comite_olimpico9\\coesp.jpg", 1);
		ImagenDAO.guardarImagen(i4);
		
		((ComiteOlimpico)comite4).setLogo(i4);
		
		
////////Se guardan comites y usuarios////////////		
		UsuarioDAO.guardarUsuario(admin);  
		UsuarioDAO.guardarUsuario(comite1);	
		UsuarioDAO.guardarUsuario(comite2);	
		UsuarioDAO.guardarUsuario(comite3);	
        UsuarioDAO.guardarUsuario(comite4); 
 ////////////////////////////////////////////
		
		
		TenantHandler th = new TenantHandler();
		Pais p5 = new Pais("Brasil","Rio de Janeiro");	
		Date fechaFin = new Date();
		fechaFin.setDate(20);
		fechaFin.setYear(2016);
		fechaFin.setMonth(1);
		EventoMultideportivo evento = new EventoMultideportivo("Rio2016", p5 , "logo.jpg", new Date(), fechaFin, "https://www.facebook.com/rio2016","https://www.instagram.com/rio2016/", "#Rio2016", "https://www.youtube.com/rio2016", "Rio2016.css");
		evento.setTwitter("https://twitter.com/rio2016");
		List<EventoMultideportivo> listevento = new ArrayList<EventoMultideportivo>();
		
		
		
		Organizador usuario4 = new Organizador();
		usuario4.setEmail("organizador@gmail.com");
		usuario4.setPassword("123");
		usuario4.setTenantID(1);
		usuario4.setEvento(evento);
		
		evento.setOrganizador(usuario4);
		evento.setTenantHandler(th);
		listevento.add(evento);
		th.setEventos(listevento);
		EventoMultiDAO.guardarTenant(th);
		
		///////////////////// Imagenes Eventos Deportivos
		
		Imagen basketImg = new Imagen("image/jpeg", "C:\\Users\\USUARIO\\git\\EventosSGEM\\EventosSGEM\\WebContent\\resources\\defecto\\img\\Tenant1\\deportes\\basquetebol.png", 1);
		ImagenDAO.guardarImagen(basketImg);
		
		Imagen tenisImg = new Imagen("image/jpeg", "C:\\Users\\USUARIO\\git\\EventosSGEM\\EventosSGEM\\WebContent\\resources\\defecto\\img\\Tenant1\\deportes\\tenisSingles.png", 1);
		ImagenDAO.guardarImagen(tenisImg);
		
		Imagen tenisFImg = new Imagen("image/jpeg", "C:\\Users\\USUARIO\\git\\EventosSGEM\\EventosSGEM\\WebContent\\resources\\defecto\\img\\Tenant1\\deportes\\tenisSingles.png", 1);
		ImagenDAO.guardarImagen(tenisFImg);
		
		Imagen tenisMdoblesImg = new Imagen("image/jpeg", "C:\\Users\\USUARIO\\git\\EventosSGEM\\EventosSGEM\\WebContent\\resources\\defecto\\img\\Tenant1\\deportes\\tenisDobles.png", 1);
		ImagenDAO.guardarImagen(tenisMdoblesImg);
		
		Imagen Natacion1Img = new Imagen("image/jpeg", "C:\\Users\\USUARIO\\git\\EventosSGEM\\EventosSGEM\\WebContent\\resources\\defecto\\img\\Tenant1\\deportes\\natacion100mariposa.png", 1);
		ImagenDAO.guardarImagen(Natacion1Img);
		
		Imagen Natacion2Img = new Imagen("image/jpeg", "C:\\Users\\USUARIO\\git\\EventosSGEM\\EventosSGEM\\WebContent\\resources\\defecto\\img\\Tenant1\\deportes\\natacion100mariposa.png", 1);
		ImagenDAO.guardarImagen(Natacion2Img);
		
		Imagen Natacion3Img = new Imagen("image/jpeg", "C:\\Users\\USUARIO\\git\\EventosSGEM\\EventosSGEM\\WebContent\\resources\\defecto\\img\\Tenant1\\deportes\\natacion200Libres.png", 1);
		ImagenDAO.guardarImagen(Natacion3Img);
		
		Imagen Natacion4Img = new Imagen("image/jpeg", "C:\\Users\\USUARIO\\git\\EventosSGEM\\EventosSGEM\\WebContent\\resources\\defecto\\img\\Tenant1\\deportes\\natacion200Libres.png", 1);
		ImagenDAO.guardarImagen(Natacion4Img);
		
		Imagen ciclismoImg = new Imagen("image/jpeg", "C:\\Users\\USUARIO\\git\\EventosSGEM\\EventosSGEM\\WebContent\\resources\\defecto\\img\\Tenant1\\deportes\\ciclismoRuta.png", 1);
		ImagenDAO.guardarImagen(ciclismoImg);

		
///////////////////////Finaliza guardado de evento con configuracion inicial/////////////////		
		EventoDeportivo basket = new EventoDeportivo(th.getTenantID(), "Basket", "3 x 3", new Date(), new Date(), "Masculino",  null, null,"colectivo",basketImg);
		EventoDeportivoDAO.guardarEventoDeportivo(basket,evento);
		
		EventoDeportivo tenis = new EventoDeportivo(th.getTenantID(), "Tenis", "Singles", new Date(), new Date(), "Masculino",  null, null,"individual",tenisImg);
		EventoDeportivoDAO.guardarEventoDeportivo(tenis,evento);
		
		EventoDeportivo tenisF = new EventoDeportivo(th.getTenantID(), "Tenis", "Singles", new Date(), new Date(), "Femenino",  null, null,"individual",tenisFImg);
		EventoDeportivoDAO.guardarEventoDeportivo(tenisF,evento);
		
		EventoDeportivo tenisMdobles = new EventoDeportivo(th.getTenantID(), "Tenis", "Dobles", new Date(), new Date(), "Masculino",  null, null,"colectivo",tenisMdoblesImg);
		EventoDeportivoDAO.guardarEventoDeportivo(tenisMdobles,evento);
		
		EventoDeportivo Natacion1 = new EventoDeportivo(th.getTenantID(), "Natacion", "100M Mariposa", new Date(), new Date(), "Femenino",  null, null,"individual",Natacion1Img);
		EventoDeportivoDAO.guardarEventoDeportivo(Natacion1,evento);
		
		EventoDeportivo Natacion2 = new EventoDeportivo(th.getTenantID(), "Natacion", "100M Mariposa", new Date(), new Date(), "Masculino", null, null,"individual",Natacion2Img);
		EventoDeportivoDAO.guardarEventoDeportivo(Natacion2,evento);
		
		EventoDeportivo Natacion3 = new EventoDeportivo(th.getTenantID(), "Natacion", "200M Libres", new Date(), new Date(), "Femenino",  null, null,"individual",Natacion3Img);
		EventoDeportivoDAO.guardarEventoDeportivo(Natacion3,evento);
		
		EventoDeportivo Natacion4 = new EventoDeportivo(th.getTenantID(), "Natacion", "200M Libres", new Date(), new Date(), "Masculino",  null, null,"individual",Natacion4Img);
		EventoDeportivoDAO.guardarEventoDeportivo(Natacion4,evento);
		
		EventoDeportivo ciclismo = new EventoDeportivo(th.getTenantID(), "Ciclismo", "Ruta", new Date(), new Date(), "Masculino",  null, null,"individual",ciclismoImg);
		EventoDeportivoDAO.guardarEventoDeportivo(ciclismo,evento);
		
		EventoDeportivo basketRonda	= EventoDeportivoDAO.traerEventoDeportivo(basket);

		
		for (int i = 0; i < 4; i++) {
			int j = i+1;
			Ronda r = new Ronda();
			
			r.setNumeroRonda(j);
			r.setTenantId(1);
//			r.setEventoDepId(eventoDep.getEventoDepId());
			r.setEventoDeportivo(basketRonda);
			basketRonda.addRonda(r);
			
			RondaDAO.guardarRonda(r);
			
			
		}
		
		EventoDeportivo tenisM	= EventoDeportivoDAO.traerEventoDeportivo(tenis);
		for (int i = 0; i < 3; i++) {
			int j = i+1;
			Ronda r = new Ronda();
			
			r.setNumeroRonda(j);
			r.setTenantId(1);
//			r.setEventoDepId(eventoDep.getEventoDepId());
			r.setEventoDeportivo(tenisM);
			tenisM.addRonda(r);
			
			RondaDAO.guardarRonda(r);	
			
		}
		
		
		EventoDeportivo tenisFem	= EventoDeportivoDAO.traerEventoDeportivo(tenisF);
		for (int i = 0; i < 2; i++) {
			int j = i+1;
			Ronda r = new Ronda();
			
			r.setNumeroRonda(j);
			r.setTenantId(1);
//			r.setEventoDepId(eventoDep.getEventoDepId());
			r.setEventoDeportivo(tenisFem);
			tenisFem.addRonda(r);
			
			RondaDAO.guardarRonda(r);	
			
		}
		
		EventoDeportivo tenisDobles	= EventoDeportivoDAO.traerEventoDeportivo(tenisMdobles);
		for (int i = 0; i < 3; i++) {
			int j = i+1;
			Ronda r = new Ronda();
			
			r.setNumeroRonda(j);
			r.setTenantId(1);
//			r.setEventoDepId(eventoDep.getEventoDepId());
			r.setEventoDeportivo(tenisDobles);
			tenisDobles.addRonda(r);
			
			RondaDAO.guardarRonda(r);	
			
		}
		
		EventoDeportivo Natacion1Fem	= EventoDeportivoDAO.traerEventoDeportivo(Natacion1);
		for (int i = 0; i < 4; i++) {
			int j = i+1;
			Ronda r = new Ronda();
			
			r.setNumeroRonda(j);
			r.setTenantId(1);
//			r.setEventoDepId(eventoDep.getEventoDepId());
			r.setEventoDeportivo(Natacion1Fem);
			Natacion1Fem.addRonda(r);
			
			RondaDAO.guardarRonda(r);	
			
		}
		
		EventoDeportivo Natacion2M	= EventoDeportivoDAO.traerEventoDeportivo(Natacion2);
		for (int i = 0; i < 4; i++) {
			int j = i+1;
			Ronda r = new Ronda();
			
			r.setNumeroRonda(j);
			r.setTenantId(1);
//			r.setEventoDepId(eventoDep.getEventoDepId());
			r.setEventoDeportivo(Natacion2M);
			Natacion2M.addRonda(r);
			
			RondaDAO.guardarRonda(r);	
			
		}
		
		EventoDeportivo Natacion3Fem	= EventoDeportivoDAO.traerEventoDeportivo(Natacion3);
		for (int i = 0; i < 4; i++) {
			int j = i+1;
			Ronda r = new Ronda();
			
			r.setNumeroRonda(j);
			r.setTenantId(1);
//			r.setEventoDepId(eventoDep.getEventoDepId());
			r.setEventoDeportivo(Natacion3Fem);
			Natacion3Fem.addRonda(r);
			
			RondaDAO.guardarRonda(r);	
			
		}
		
		EventoDeportivo Natacion4M	= EventoDeportivoDAO.traerEventoDeportivo(Natacion4);
		for (int i = 0; i < 5; i++) {
			int j = i+1;
			Ronda r = new Ronda();
			
			r.setNumeroRonda(j);
			r.setTenantId(1);
//			r.setEventoDepId(eventoDep.getEventoDepId());
			r.setEventoDeportivo(Natacion4M);
			Natacion4M.addRonda(r);
			
			RondaDAO.guardarRonda(r);	
			
		}
		
		EventoDeportivo ciclismo1	= EventoDeportivoDAO.traerEventoDeportivo(ciclismo);
		for (int i = 0; i < 1; i++) {
			int j = i+1;
			Ronda r = new Ronda();
			
			r.setNumeroRonda(j);
			r.setTenantId(1);
//			r.setEventoDepId(eventoDep.getEventoDepId());
			r.setEventoDeportivo(ciclismo1);
			ciclismo1.addRonda(r);
			
			RondaDAO.guardarRonda(r);	
			
		}

		Date fechaNac = null, fechaNac1 = null, fechaNac2= null, fechaNac3 = null, fechaNac4 = null, fechaNac5 = null, fechaNac6 = null, fechaNac7 = null, fechaNac8 = null, fechaNac9 = null, fechaNac10 = null, fechaNac11 = null, fechaNac12 = null, fechaNac13 = null, fechaNac14 = null, fechaNac15 = null, fechaNac16 = null, fechaNac17 = null, fechaNac18 = null, fechaNac19 = null,fechaNac20 = null;
		
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		try {			
			fechaNac = formato.parse("28/11/1985");
			fechaNac1 = formato.parse("25/05/1983");
			fechaNac2 = formato.parse("13/09/1982");
			fechaNac3 = formato.parse("30/12/1987");
			fechaNac4 = formato.parse("10/11/1987");
			fechaNac5 = formato.parse("28/07/1977");
			fechaNac6 = formato.parse("30/11/1979");
			fechaNac7 = formato.parse("17/05/1977");
			fechaNac8 = formato.parse("29/03/1984");
			fechaNac9 = formato.parse("23/09/1988");
			fechaNac10 = formato.parse("26/05/1990");
			fechaNac11= formato.parse("22/06/1978");
			fechaNac12 = formato.parse("04/02/1965");
			fechaNac13 = formato.parse("13/06/1980");
			fechaNac14 = formato.parse("29/01/1985");
			fechaNac15 = formato.parse("06/07/1980");
			fechaNac16 = formato.parse("03/06/1986");
			fechaNac17 = formato.parse("01/01/1986");
			fechaNac18 = formato.parse("14/01/1992");
			fechaNac19 = formato.parse("29/03/1972");
			fechaNac20 = formato.parse("07/07/1987");
			
			
			
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
   
		System.out.println("Alta de 4 usuarios completa");
			
		System.out.println("Obtengo el usuario dsa2");			
		ComiteOlimpico comiteUruguayo = (ComiteOlimpico) UsuarioDAO.buscarUsuario(1,"cou@gmail.com");
		ComiteOlimpico comiteArgentino = (ComiteOlimpico) UsuarioDAO.buscarUsuario(1,"coa@gmail.com");
		ComiteOlimpico comiteBrasilero = (ComiteOlimpico) UsuarioDAO.buscarUsuario(1,"cobra@gmail.com");
		ComiteOlimpico comiteEspaniol = (ComiteOlimpico) UsuarioDAO.buscarUsuario(1,"coesp@gmail.com");
		System.out.println("obtuve el usuario "+comiteUruguayo.getEmail()+" "+comiteUruguayo.getId());
		System.out.println(" y es "+comiteUruguayo.soy());
		
		Deportista db = new Deportista(1, "Leandrinho", "Barbosa",fechaNac, "Masculino",new HashSet<EventoDeportivo>(),new HashSet<Competencia>(),null,new HashSet<Estadistica>(), null);			
		Deportista db1 = new Deportista(1, "Marcelinho ", "Huertas",fechaNac1, "Masculino",new HashSet<EventoDeportivo>(),new HashSet<Competencia>(),null,new HashSet<Estadistica>(), null);		
		Deportista db2 = new Deportista(1, "Nene", "Hilario",fechaNac3, "Masculino",new HashSet<EventoDeportivo>(),new HashSet<Competencia>(),null,new HashSet<Estadistica>(), null);			
		Deportista db3 = new Deportista(1, "Thomaz", "Bellucci",fechaNac4, "Masculino",new HashSet<EventoDeportivo>(),new HashSet<Competencia>(),null,new HashSet<Estadistica>(), null);	
		Deportista db4 = new Deportista(1, "Teliana", "Pereira",fechaNac, "Femenino",new HashSet<EventoDeportivo>(),new HashSet<Competencia>(),null,new HashSet<Estadistica>(), null);	
		Deportista db5 = new Deportista(1, "Gabriela", "Ce",fechaNac3, "Femenino",new HashSet<EventoDeportivo>(),new HashSet<Competencia>(),null,new HashSet<Estadistica>(), null);	
		Deportista db6 = new Deportista(1, "Augusto", "Cielo",fechaNac4, "Masculino",new HashSet<EventoDeportivo>(),new HashSet<Competencia>(),null,new HashSet<Estadistica>(), null);	
		Deportista db7 = new Deportista(1, "Sara", "Correa",fechaNac8, "Femenino",new HashSet<EventoDeportivo>(),new HashSet<Competencia>(),null,new HashSet<Estadistica>(), null);	
		
		
		Deportista da = new Deportista(1, "Emanuel", "Ginobili",fechaNac5, "Masculino",new HashSet<EventoDeportivo>(),new HashSet<Competencia>(),null,new HashSet<Estadistica>(), null);			
		Deportista da1 = new Deportista(1, "Andres", "Nocioni",fechaNac6, "Masculino",new HashSet<EventoDeportivo>(),new HashSet<Competencia>(),null,new HashSet<Estadistica>(), null);		
		Deportista da2 = new Deportista(1, "Pablo", "Prigioni",fechaNac7, "Masculino",new HashSet<EventoDeportivo>(),new HashSet<Competencia>(),null,new HashSet<Estadistica>(), null);	
		Deportista da3 = new Deportista(1, "Juan", "Monaco",fechaNac8, "Masculino",new HashSet<EventoDeportivo>(),new HashSet<Competencia>(),null,new HashSet<Estadistica>(), null);	
		Deportista da4 = new Deportista(1, "Juan Martin", "Del Potro",fechaNac9, "Masculino",new HashSet<EventoDeportivo>(),new HashSet<Competencia>(),null,new HashSet<Estadistica>(), null);	
		Deportista da5 = new Deportista(1, "Maria", "Irigoyen",fechaNac6, "Femenino",new HashSet<EventoDeportivo>(),new HashSet<Competencia>(),null,new HashSet<Estadistica>(), null);	
		Deportista da6 = new Deportista(1, "Paula", "Ormaechea",fechaNac15, "Femenino",new HashSet<EventoDeportivo>(),new HashSet<Competencia>(),null,new HashSet<Estadistica>(), null);	
		Deportista da7 = new Deportista(1, "Federico", "Grabich",fechaNac10, "Masculino",new HashSet<EventoDeportivo>(),new HashSet<Competencia>(),null,new HashSet<Estadistica>(), null);	
		Deportista da8 = new Deportista(1, "Jose", "Meolans",fechaNac11, "Masculino",new HashSet<EventoDeportivo>(),new HashSet<Competencia>(),null,new HashSet<Estadistica>(), null);	
		Deportista da9 = new Deportista(1, "Walter", "Perez",fechaNac18, "Masculino",new HashSet<EventoDeportivo>(),new HashSet<Competencia>(),null,new HashSet<Estadistica>(), null);	
		Deportista da10 = new Deportista(1, "Juan", "Curuchet",fechaNac12, "Masculino",new HashSet<EventoDeportivo>(),new HashSet<Competencia>(),null,new HashSet<Estadistica>(), null);	
		
		
		Deportista de = new Deportista(1, "Juan Carlos", "Navarro",fechaNac13, "Masculino",new HashSet<EventoDeportivo>(),new HashSet<Competencia>(),null,new HashSet<Estadistica>(), null);			
		Deportista de1 = new Deportista(1, "Pau", "Gasol",fechaNac14, "Masculino",new HashSet<EventoDeportivo>(),new HashSet<Competencia>(),null,new HashSet<Estadistica>(), null);		
		Deportista de2 = new Deportista(1, "Marc", "Gasol",fechaNac15, "Masculino",new HashSet<EventoDeportivo>(),new HashSet<Competencia>(),null,new HashSet<Estadistica>(), null);	
		Deportista de3 = new Deportista(1, "Rafael", "Nadal",fechaNac16, "Masculino",new HashSet<EventoDeportivo>(),new HashSet<Competencia>(),null,new HashSet<Estadistica>(), null);	
		Deportista de4 = new Deportista(1, "Carla", "Suarez",fechaNac6, "Femenino",new HashSet<EventoDeportivo>(),new HashSet<Competencia>(),null,new HashSet<Estadistica>(), null);	
		Deportista de5 = new Deportista(1, "Javier", "Martinez",fechaNac12, "Masculino",new HashSet<EventoDeportivo>(),new HashSet<Competencia>(),null,new HashSet<Estadistica>(), null);	
		Deportista de6 = new Deportista(1, "Nicolas", "Fernandez",fechaNac19, "Masculino",new HashSet<EventoDeportivo>(),new HashSet<Competencia>(),null,new HashSet<Estadistica>(), null);	
		Deportista de7 = new Deportista(1, "Fernanda", "Gonzalez",fechaNac20, "Femenino",new HashSet<EventoDeportivo>(),new HashSet<Competencia>(),null,new HashSet<Estadistica>(), null);	
		Deportista de8 = new Deportista(1, "Alberto", "Contador",fechaNac11, "Masculino",new HashSet<EventoDeportivo>(),new HashSet<Competencia>(),null,new HashSet<Estadistica>(), null);	
		Deportista de9 = new Deportista(1, "Alejandro", "Valverde",fechaNac4, "Masculino",new HashSet<EventoDeportivo>(),new HashSet<Competencia>(),null,new HashSet<Estadistica>(), null);	
		
		
		
		Deportista du = new Deportista(1, "Pablo", "Cuevas",fechaNac17, "Masculino",new HashSet<EventoDeportivo>(),new HashSet<Competencia>(),null,new HashSet<Estadistica>(), null);			
		Deportista du1 = new Deportista(1, "Martin", "Cuevas",fechaNac18, "Masculino",new HashSet<EventoDeportivo>(),new HashSet<Competencia>(),null,new HashSet<Estadistica>(), null);		
		Deportista du2 = new Deportista(1, "Gabriel", "Melconian",fechaNac20, "Masculino",new HashSet<EventoDeportivo>(),new HashSet<Competencia>(),null,new HashSet<Estadistica>(), null);	
		Deportista du3 = new Deportista(1, "Ines", "Remersaro",fechaNac15, "Femenino",new HashSet<EventoDeportivo>(),new HashSet<Competencia>(),null,new HashSet<Estadistica>(), null);	
		Deportista du4 = new Deportista(1, "Noelia", "Petti",fechaNac, "Femenino",new HashSet<EventoDeportivo>(),new HashSet<Competencia>(),null,new HashSet<Estadistica>(), null);	
		Deportista du5 = new Deportista(1, "Milton", "Wynants",fechaNac19, "Masculino",new HashSet<EventoDeportivo>(),new HashSet<Competencia>(),null,new HashSet<Estadistica>(), null);	
		Deportista du6 = new Deportista(1, "Fabricio", "Ferrari",fechaNac14, "Masculino",new HashSet<EventoDeportivo>(),new HashSet<Competencia>(),null,new HashSet<Estadistica>(), null);	
		
		
		///////////////////////////////////// Subir imagen de deportistas //////////////////////////
		
		
		/////////////////////////// Brasileros/////////////////////////////
		
		Imagen idb = new Imagen("image/jpeg", "C:\\Users\\USUARIO\\git\\EventosSGEM\\EventosSGEM\\WebContent\\resources\\defecto\\img\\Tenant1\\comite_olimpico8\\deportistas\\barbosa.jpg", 1);
		ImagenDAO.guardarImagen(idb);
		db.setFoto(idb);
		
		Imagen idb1 = new Imagen("image/jpeg", "C:\\Users\\USUARIO\\git\\EventosSGEM\\EventosSGEM\\WebContent\\resources\\defecto\\img\\Tenant1\\comite_olimpico8\\deportistas\\huertas.jpg", 1);
		ImagenDAO.guardarImagen(idb1);
		db1.setFoto(idb1);
		
		Imagen idb2 = new Imagen("image/jpeg", "C:\\Users\\USUARIO\\git\\EventosSGEM\\EventosSGEM\\WebContent\\resources\\defecto\\img\\Tenant1\\comite_olimpico8\\deportistas\\hilario.jpg", 1);
		ImagenDAO.guardarImagen(idb2);
		db2.setFoto(idb2);
		
		Imagen idb3 = new Imagen("image/jpeg", "C:\\Users\\USUARIO\\git\\EventosSGEM\\EventosSGEM\\WebContent\\resources\\defecto\\img\\Tenant1\\comite_olimpico8\\deportistas\\bellucci.jpg", 1);
		ImagenDAO.guardarImagen(idb3);
		db3.setFoto(idb3);
		
		Imagen idb4 = new Imagen("image/jpeg", "C:\\Users\\USUARIO\\git\\EventosSGEM\\EventosSGEM\\WebContent\\resources\\defecto\\img\\Tenant1\\comite_olimpico8\\deportistas\\pereira.jpg", 1);
		ImagenDAO.guardarImagen(idb4);
		db4.setFoto(idb4);
		
		Imagen idb5 = new Imagen("image/jpeg", "C:\\Users\\USUARIO\\git\\EventosSGEM\\EventosSGEM\\WebContent\\resources\\defecto\\img\\Tenant1\\comite_olimpico8\\deportistas\\ce.jpg", 1);
		ImagenDAO.guardarImagen(idb5);
		db5.setFoto(idb5);
		
		Imagen idb6 = new Imagen("image/jpeg", "C:\\Users\\USUARIO\\git\\EventosSGEM\\EventosSGEM\\WebContent\\resources\\defecto\\img\\Tenant1\\comite_olimpico8\\deportistas\\cielo.jpg", 1);
		ImagenDAO.guardarImagen(idb6);
		db6.setFoto(idb6);
		
		Imagen idb7 = new Imagen("image/jpeg", "C:\\Users\\USUARIO\\git\\EventosSGEM\\EventosSGEM\\WebContent\\resources\\defecto\\img\\Tenant1\\comite_olimpico8\\deportistas\\correa.jpg", 1);
		ImagenDAO.guardarImagen(idb7);
		db7.setFoto(idb7);
		
		////////////////////////////////////////////// Argentinos /////////////////////////////////
		Imagen ida = new Imagen("image/jpeg", "C:\\Users\\USUARIO\\git\\EventosSGEM\\EventosSGEM\\WebContent\\resources\\defecto\\img\\Tenant1\\comite_olimpico3\\deportistas\\ginobili.jpg", 1);
		ImagenDAO.guardarImagen(ida);
		da.setFoto(ida);
		
		Imagen ida1 = new Imagen("image/jpeg", "C:\\Users\\USUARIO\\git\\EventosSGEM\\EventosSGEM\\WebContent\\resources\\defecto\\img\\Tenant1\\comite_olimpico3\\deportistas\\nocioni.jpg", 1);
		ImagenDAO.guardarImagen(ida1);
		da1.setFoto(ida1);
		
		Imagen ida2 = new Imagen("image/jpeg", "C:\\Users\\USUARIO\\git\\EventosSGEM\\EventosSGEM\\WebContent\\resources\\defecto\\img\\Tenant1\\comite_olimpico3\\deportistas\\prigioni.jpg", 1);
		ImagenDAO.guardarImagen(ida2);
		da2.setFoto(ida2);
		
		Imagen ida3 = new Imagen("image/jpeg", "C:\\Users\\USUARIO\\git\\EventosSGEM\\EventosSGEM\\WebContent\\resources\\defecto\\img\\Tenant1\\comite_olimpico3\\deportistas\\monaco.jpg", 1);
		ImagenDAO.guardarImagen(ida3);
		da3.setFoto(ida3);
		
		Imagen ida4 = new Imagen("image/jpeg", "C:\\Users\\USUARIO\\git\\EventosSGEM\\EventosSGEM\\WebContent\\resources\\defecto\\img\\Tenant1\\comite_olimpico3\\deportistas\\delpotro.jpg", 1);
		ImagenDAO.guardarImagen(ida4);
		da4.setFoto(ida4);
		
		Imagen ida5 = new Imagen("image/jpeg", "C:\\Users\\USUARIO\\git\\EventosSGEM\\EventosSGEM\\WebContent\\resources\\defecto\\img\\Tenant1\\comite_olimpico3\\deportistas\\irigoyen.jpg", 1);
		ImagenDAO.guardarImagen(ida5);
		da5.setFoto(ida5);
		
		Imagen ida6 = new Imagen("image/jpeg", "C:\\Users\\USUARIO\\git\\EventosSGEM\\EventosSGEM\\WebContent\\resources\\defecto\\img\\Tenant1\\comite_olimpico3\\deportistas\\ormaechea.jpg", 1);
		ImagenDAO.guardarImagen(ida6);
		da6.setFoto(ida6);
		
		Imagen ida7 = new Imagen("image/jpeg", "C:\\Users\\USUARIO\\git\\EventosSGEM\\EventosSGEM\\WebContent\\resources\\defecto\\img\\Tenant1\\comite_olimpico3\\deportistas\\grabich.jpg", 1);
		ImagenDAO.guardarImagen(ida7);
		da7.setFoto(ida7);
		
		Imagen ida8 = new Imagen("image/jpeg", "C:\\Users\\USUARIO\\git\\EventosSGEM\\EventosSGEM\\WebContent\\resources\\defecto\\img\\Tenant1\\comite_olimpico3\\deportistas\\meolans.jpg", 1);
		ImagenDAO.guardarImagen(ida8);
		da8.setFoto(ida8);
		
		
		Imagen ida9 = new Imagen("image/jpeg", "C:\\Users\\USUARIO\\git\\EventosSGEM\\EventosSGEM\\WebContent\\resources\\defecto\\img\\Tenant1\\comite_olimpico3\\deportistas\\perez.jpg", 1);
		ImagenDAO.guardarImagen(ida9);
		da9.setFoto(ida9);
		
		
		Imagen ida10 = new Imagen("image/jpeg", "C:\\Users\\USUARIO\\git\\EventosSGEM\\EventosSGEM\\WebContent\\resources\\defecto\\img\\Tenant1\\comite_olimpico3\\deportistas\\curuchet.jpg", 1);
		ImagenDAO.guardarImagen(ida10);
		da10.setFoto(ida10);
		
		/////////////////////////////////////////////////////Españoles//////////////////////
		
		Imagen ide = new Imagen("image/jpeg", "C:\\Users\\USUARIO\\git\\EventosSGEM\\EventosSGEM\\WebContent\\resources\\defecto\\img\\Tenant1\\comite_olimpico9\\deportistas\\navarro.jpg", 1);
		ImagenDAO.guardarImagen(ide);
		de.setFoto(ide);
		
		Imagen ide1 = new Imagen("image/jpeg", "C:\\Users\\USUARIO\\git\\EventosSGEM\\EventosSGEM\\WebContent\\resources\\defecto\\img\\Tenant1\\comite_olimpico9\\deportistas\\pau.jpg", 1);
		ImagenDAO.guardarImagen(ide1);
		de1.setFoto(ide1);
		
		Imagen ide2 = new Imagen("image/jpeg", "C:\\Users\\USUARIO\\git\\EventosSGEM\\EventosSGEM\\WebContent\\resources\\defecto\\img\\Tenant1\\comite_olimpico9\\deportistas\\marc.jpg", 1);
		ImagenDAO.guardarImagen(ide2);
		de2.setFoto(ide2);
		
		Imagen ide3 = new Imagen("image/jpeg", "C:\\Users\\USUARIO\\git\\EventosSGEM\\EventosSGEM\\WebContent\\resources\\defecto\\img\\Tenant1\\comite_olimpico9\\deportistas\\nadal.jpg", 1);
		ImagenDAO.guardarImagen(ide3);
		de3.setFoto(ide3);
		
		Imagen ide4 = new Imagen("image/jpeg", "C:\\Users\\USUARIO\\git\\EventosSGEM\\EventosSGEM\\WebContent\\resources\\defecto\\img\\Tenant1\\comite_olimpico9\\deportistas\\suarez.jpg", 1);
		ImagenDAO.guardarImagen(ide4);
		de4.setFoto(ide4);
		
		Imagen ide5 = new Imagen("image/jpeg", "C:\\Users\\USUARIO\\git\\EventosSGEM\\EventosSGEM\\WebContent\\resources\\defecto\\img\\Tenant1\\comite_olimpico9\\deportistas\\martinez.jpg", 1);
		ImagenDAO.guardarImagen(ide5);
		de5.setFoto(ide5);
		
		Imagen ide6 = new Imagen("image/jpeg", "C:\\Users\\USUARIO\\git\\EventosSGEM\\EventosSGEM\\WebContent\\resources\\defecto\\img\\Tenant1\\comite_olimpico9\\deportistas\\fernandez.jpg", 1);
		ImagenDAO.guardarImagen(ide6);
		de6.setFoto(ide6);
		
		Imagen ide7 = new Imagen("image/jpeg", "C:\\Users\\USUARIO\\git\\EventosSGEM\\EventosSGEM\\WebContent\\resources\\defecto\\img\\Tenant1\\comite_olimpico9\\deportistas\\gonzalez.jpg", 1);
		ImagenDAO.guardarImagen(ide7);
		de7.setFoto(ide7);
		
		Imagen ide8 = new Imagen("image/jpeg", "C:\\Users\\USUARIO\\git\\EventosSGEM\\EventosSGEM\\WebContent\\resources\\defecto\\img\\Tenant1\\comite_olimpico9\\deportistas\\contador.jpg", 1);
		ImagenDAO.guardarImagen(ide8);
		de8.setFoto(ide8);
		
		Imagen ide9 = new Imagen("image/jpeg", "C:\\Users\\USUARIO\\git\\EventosSGEM\\EventosSGEM\\WebContent\\resources\\defecto\\img\\Tenant1\\comite_olimpico9\\deportistas\\valverde.jpg", 1);
		ImagenDAO.guardarImagen(ide9);
		de9.setFoto(ide9);
		
		
		//////////////////////////////////////////////// Uruguayos//////////////////////////////
		
		Imagen idu = new Imagen("image/jpeg", "C:\\Users\\USUARIO\\git\\EventosSGEM\\EventosSGEM\\WebContent\\resources\\defecto\\img\\Tenant1\\comite_olimpico2\\deportistas\\pcuevas.jpg", 1);
		ImagenDAO.guardarImagen(idu);
		du.setFoto(idu);
		
		Imagen idu1 = new Imagen("image/jpeg", "C:\\Users\\USUARIO\\git\\EventosSGEM\\EventosSGEM\\WebContent\\resources\\defecto\\img\\Tenant1\\comite_olimpico2\\deportistas\\mcuevas.jpg", 1);
		ImagenDAO.guardarImagen(idu1);
		du1.setFoto(idu1);
		
		Imagen idu2 = new Imagen("image/jpeg", "C:\\Users\\USUARIO\\git\\EventosSGEM\\EventosSGEM\\WebContent\\resources\\defecto\\img\\Tenant1\\comite_olimpico2\\deportistas\\melconian.jpg", 1);
		ImagenDAO.guardarImagen(idu2);
		du2.setFoto(idu2);
		
		Imagen idu3 = new Imagen("image/jpeg", "C:\\Users\\USUARIO\\git\\EventosSGEM\\EventosSGEM\\WebContent\\resources\\defecto\\img\\Tenant1\\comite_olimpico2\\deportistas\\remersaro.jpg", 1);
		ImagenDAO.guardarImagen(idu3);
		du3.setFoto(idu3);
		
		Imagen idu4 = new Imagen("image/jpeg", "C:\\Users\\USUARIO\\git\\EventosSGEM\\EventosSGEM\\WebContent\\resources\\defecto\\img\\Tenant1\\comite_olimpico2\\deportistas\\petti.jpg", 1);
		ImagenDAO.guardarImagen(idu4);
		du4.setFoto(idu4);
		
		Imagen idu5 = new Imagen("image/jpeg", "C:\\Users\\USUARIO\\git\\EventosSGEM\\EventosSGEM\\WebContent\\resources\\defecto\\img\\Tenant1\\comite_olimpico2\\deportistas\\wynants.jpg", 1);
		ImagenDAO.guardarImagen(idu5);
		du5.setFoto(idu5);
		
		Imagen idu6 = new Imagen("image/jpeg", "C:\\Users\\USUARIO\\git\\EventosSGEM\\EventosSGEM\\WebContent\\resources\\defecto\\img\\Tenant1\\comite_olimpico2\\deportistas\\ferrari.jpg", 1);
		ImagenDAO.guardarImagen(idu6);
		du6.setFoto(idu6);
		
		db.addEventoDeportivo(basketRonda);
		db1.addEventoDeportivo(basketRonda);
		db2.addEventoDeportivo(basketRonda);		
		db3.addEventoDeportivo(tenisM);	
		db4.addEventoDeportivo(tenisFem);
		db5.addEventoDeportivo(tenisFem);
		db6.addEventoDeportivo(Natacion2M);
		db6.addEventoDeportivo(Natacion4M);
		db7.addEventoDeportivo(Natacion1Fem);
		db7.addEventoDeportivo(Natacion3Fem);
		
		
		
		
		db.setComiteOlimpico(comiteBrasilero);		
		db1.setComiteOlimpico(comiteBrasilero);
		db2.setComiteOlimpico(comiteBrasilero);
		db3.setComiteOlimpico(comiteBrasilero);
		db4.setComiteOlimpico(comiteBrasilero);
		db5.setComiteOlimpico(comiteBrasilero);
		db6.setComiteOlimpico(comiteBrasilero);
		db7.setComiteOlimpico(comiteBrasilero);
		
		
		
		da.addEventoDeportivo(basketRonda);
		da1.addEventoDeportivo(basketRonda);
		da2.addEventoDeportivo(basketRonda);
		da3.addEventoDeportivo(tenisDobles);
		da4.addEventoDeportivo(tenisDobles);
		da3.addEventoDeportivo(tenisM);
		da4.addEventoDeportivo(tenisM);
		da5.addEventoDeportivo(tenisFem);
		da6.addEventoDeportivo(tenisFem);
		da7.addEventoDeportivo(Natacion2M);
		da7.addEventoDeportivo(Natacion4M);
		da8.addEventoDeportivo(Natacion4M);
		da9.addEventoDeportivo(ciclismo1);
		da10.addEventoDeportivo(ciclismo1);
		
		
		
		da.setComiteOlimpico(comiteArgentino);
		da1.setComiteOlimpico(comiteArgentino);
		da2.setComiteOlimpico(comiteArgentino);
		da3.setComiteOlimpico(comiteArgentino);
		da4.setComiteOlimpico(comiteArgentino);
		da5.setComiteOlimpico(comiteArgentino);
		da6.setComiteOlimpico(comiteArgentino);
		da7.setComiteOlimpico(comiteArgentino);
		da8.setComiteOlimpico(comiteArgentino);
		da9.setComiteOlimpico(comiteArgentino);
		da10.setComiteOlimpico(comiteArgentino);
		
		
		de.addEventoDeportivo(basketRonda);
		de1.addEventoDeportivo(basketRonda);
		de2.addEventoDeportivo(basketRonda);
		de3.addEventoDeportivo(tenisM);
		de4.addEventoDeportivo(tenisFem);
		de5.addEventoDeportivo(Natacion2M);
		de5.addEventoDeportivo(Natacion4M);
		de6.addEventoDeportivo(Natacion4M);
		de7.addEventoDeportivo(Natacion1Fem);
		de7.addEventoDeportivo(Natacion3Fem);
		de8.addEventoDeportivo(ciclismo1);
		de9.addEventoDeportivo(ciclismo1);
		
		
		
		de.setComiteOlimpico(comiteEspaniol);
		de1.setComiteOlimpico(comiteEspaniol);
		de2.setComiteOlimpico(comiteEspaniol);
		de3.setComiteOlimpico(comiteEspaniol);
		de4.setComiteOlimpico(comiteEspaniol);
		de5.setComiteOlimpico(comiteEspaniol);
		de6.setComiteOlimpico(comiteEspaniol);
		de7.setComiteOlimpico(comiteEspaniol);
		de8.setComiteOlimpico(comiteEspaniol);
		de9.setComiteOlimpico(comiteEspaniol);
		
		
		
		du.addEventoDeportivo(tenisDobles);
		du1.addEventoDeportivo(tenisDobles);
		du.addEventoDeportivo(tenisM);
		du1.addEventoDeportivo(tenisM);
		du2.addEventoDeportivo(Natacion2M);
		du2.addEventoDeportivo(Natacion4M);
		du3.addEventoDeportivo(Natacion1Fem);
		du3.addEventoDeportivo(Natacion3Fem);
		du4.addEventoDeportivo(Natacion1Fem);
		du4.addEventoDeportivo(Natacion3Fem);
		du5.addEventoDeportivo(ciclismo1);
		du6.addEventoDeportivo(ciclismo1);
		
		
		du.setComiteOlimpico(comiteUruguayo);
		du1.setComiteOlimpico(comiteUruguayo);
		du2.setComiteOlimpico(comiteUruguayo);
		du3.setComiteOlimpico(comiteUruguayo);
		du4.setComiteOlimpico(comiteUruguayo);
		du5.setComiteOlimpico(comiteUruguayo);
		du6.setComiteOlimpico(comiteUruguayo);

		
		//////////////////////////// Guardo los deportistas que aun no tienen competencias/////////////////////////////////
		
		/// Uruguayos//
		
		DeportistaDAO.guardarDeportista(du1);
		DeportistaDAO.guardarDeportista(du2);
		DeportistaDAO.guardarDeportista(du3);
		DeportistaDAO.guardarDeportista(du4);
		
		//// Argentinos /// 
		
		DeportistaDAO.guardarDeportista(da4);
		DeportistaDAO.guardarDeportista(da5);
		DeportistaDAO.guardarDeportista(da6);
		DeportistaDAO.guardarDeportista(da7);
		DeportistaDAO.guardarDeportista(da8);
		
		//// Españoles /// 
		
		DeportistaDAO.guardarDeportista(de3);
		DeportistaDAO.guardarDeportista(de4);
		DeportistaDAO.guardarDeportista(de5);
		DeportistaDAO.guardarDeportista(de6);
		DeportistaDAO.guardarDeportista(de7);
		
	//// Españoles /// 
		
		DeportistaDAO.guardarDeportista(db3);
		DeportistaDAO.guardarDeportista(db4);
		DeportistaDAO.guardarDeportista(db5);
		DeportistaDAO.guardarDeportista(db6);
		DeportistaDAO.guardarDeportista(db7);
		
		
		
		
		
		//////////////////////////////////////////////////////////////////////
		
		
		
		
		Juez j = new Juez();
		
		j.setEmail("juez1@gmail.com");
		j.setPassword("123");
		j.setTenantID(1);
		j.setApellido("Ricchi");
		j.setNombre("Fernando");
		
		UsuarioDAO.guardarUsuario(j);
		
		Juez j1 = new Juez();
		
		j1.setEmail("juez2@gmail.com");
		j1.setPassword("123");
		j1.setTenantID(1);
		j1.setApellido("Larrionda");
		j1.setNombre("Jorge");
		
		UsuarioDAO.guardarUsuario(j1);
			
		
		
		
		/**** Competencias ***/
		
		UsuarioComun u = new UsuarioComun();
		u.setEmail("leo@gmail.com");
		u.setPassword("123");
		u.setTenantID(1);
		UsuarioDAO.guardarUsuario(u);
		
		UsuarioComun u2 = new UsuarioComun();
		u2.setEmail("juanma@gmail.com");
		u2.setPassword("123");
		u2.setTenantID(1);
		UsuarioDAO.guardarUsuario(u2);
		
		Date datec1 = null,datec2 = null;
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		try {			
			datec1 = formatter.parse("28/10/2015 14:30:00");
			datec2 = formatter.parse("30/10/2015 19:45:00");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		////////////////////////////////////////////////////////// se crea la primer competencia de basket 3x3
		
		Set<Deportista> deportistasCompBasquet = new HashSet<Deportista>();		

		deportistasCompBasquet.add(db);
		deportistasCompBasquet.add(db1);
		deportistasCompBasquet.add(db2);
		
		deportistasCompBasquet.add(da);
		deportistasCompBasquet.add(da1);
		deportistasCompBasquet.add(da2);
		
		Set<Deportista> deportistasCompBasquet2 = new HashSet<Deportista>();		

		deportistasCompBasquet2.add(de);
		deportistasCompBasquet2.add(de1);
		deportistasCompBasquet2.add(de2);
		
		deportistasCompBasquet2.add(da);
		deportistasCompBasquet2.add(da1);
		deportistasCompBasquet2.add(da2);
		
		

 
		 Set<Ronda> setRondas = basketRonda.getRonda();
		 Ronda r2 = new Ronda();
		 for(Ronda i : setRondas){
			 System.out.println(i.getNumeroRonda());
			 if(i.getNumeroRonda()==1){
				 r2 = i;
			 }
			 
		 }
		     
		 
	
		//Colectivo
		Competencia c1 = new Competencia(1, datec1, "Estadio Basquet", 1500.0F, false, 20, basketRonda, j, r2, null, 0, deportistasCompBasquet, null,0);		
		CompetenciaDAO.guardarCompetencia(c1);
		

		
		
		
		Set<Entrada> entradas = new HashSet<Entrada>();
		
		Competencia comp = CompetenciaDAO.buscarCompetencia(1, 1);
		
		for(int i = 1; i< (comp.getCantEntradas()+1); i++){
			
			Entrada e = new Entrada();
			e.setCompetencia(comp);
			e.setTenantId(comp.getTenantId());
			e.setFecha(comp.getFecha());
			e.setNumeroAsiento(i);
			e.setPrecioEntrada(comp.getPrecioEntrada());
			e.setVendida(false);
			
			EntradaDAO.guardarEntrada(e);
			
			comp.addEntrada(e);
			
			} 
		
//		entradas.add(e1);
//		entradas.add(e2);
//		entradas.add(e3);
//		entradas.add(e4);
//		entradas.add(e5);
		
		Set<Competencia> comps = new HashSet<Competencia>();
		comps.add(comp);
		
//		Ronda ronda = RondaDAO.buscarRonda(1, r.getRondaId());

	 
		r2.setCompetencia(comps);
		
		RondaDAO.modificarRonda(r2);
		
		DeportistaDAO.guardarDeportista(db);
		DeportistaDAO.guardarDeportista(db1);
		DeportistaDAO.guardarDeportista(db2);
		DeportistaDAO.guardarDeportista(da);
		DeportistaDAO.guardarDeportista(da1);
		DeportistaDAO.guardarDeportista(da2);
		
		
		comp.setRonda(r2);
		comp.addDeportista(da);
		comp.addDeportista(da1);
		comp.addDeportista(da2);
		
		comp.addDeportista(db);
		comp.addDeportista(db1);
		comp.addDeportista(db2);
	
		CompetenciaDAO.modificarCompetencia(comp);	
		

		//////////////////////////////////////////////////////////////////////////// Se crea la segunda competencia de basket 3x3
		
		entradas = new HashSet<Entrada>();
		
		
		
		Competencia c2 = new Competencia(1, datec2, "Estadio Basquet", 500.0F, false, 30, basketRonda, j1, r2, null, 0, deportistasCompBasquet2, null,0);		
		CompetenciaDAO.guardarCompetencia(c2);
			
		
		Competencia comp2 = CompetenciaDAO.buscarCompetencia(1, 2);
		
		for(int i = 1; i< (comp2.getCantEntradas()+1); i++){
			
			Entrada e = new Entrada();
			e.setCompetencia(comp2);
			e.setTenantId(comp2.getTenantId());
			e.setFecha(comp2.getFecha());
			e.setNumeroAsiento(i);
			e.setPrecioEntrada(comp2.getPrecioEntrada());
			e.setVendida(false);
			
			EntradaDAO.guardarEntrada(e);
			
			comp2.addEntrada(e);
			
			} 
		
		
		comps = new HashSet<Competencia>();
		comps.add(comp2);
		
		
		comp2.setRonda(r2);		
		
		r2.setCompetencia(comps);
		
		RondaDAO.modificarRonda(r2);
		
		DeportistaDAO.guardarDeportista(de);
		DeportistaDAO.guardarDeportista(de1);
		DeportistaDAO.guardarDeportista(de2);
		
		DeportistaDAO.modificarDeportista(da);
		DeportistaDAO.modificarDeportista(da1);
		DeportistaDAO.modificarDeportista(da2);
				
		comp2.addDeportista(de);
		comp2.addDeportista(de1);
		comp2.addDeportista(de2);
		
		comp2.addDeportista(da);
		comp2.addDeportista(da1);
		comp2.addDeportista(da2);
		
		CompetenciaDAO.modificarCompetencia(comp2);	
		
		
////////////////////////////////////////////////////////////// Se crea competencia de tenis singles ///////////////////////////////
	
		Set<Deportista> deportistasCompTenis = new HashSet<Deportista>();		

		deportistasCompTenis.add(du);
		deportistasCompTenis.add(da3);
		

		 
		 Set<Ronda> setRondasTenism = tenisM.getRonda();
		 Ronda r3 = new Ronda();
		 for(Ronda i : setRondasTenism){
			 if(i.getNumeroRonda()==1){
				 r3 = i;
			 }
			 
		 }
		 
	 
	
		Competencia c3 = new Competencia(1, datec1, "Rio Tennis Stadium", 250.0F, false, 20, tenisM, j, r3, null, 0, deportistasCompTenis, null,0);		
		CompetenciaDAO.guardarCompetencia(c3);
		


		Competencia comp3 = CompetenciaDAO.buscarCompetencia(1, 3);
		
		for(int i = 1; i< (comp3.getCantEntradas()+1); i++){
			
			Entrada e = new Entrada();
			e.setCompetencia(comp3);
			e.setTenantId(comp3.getTenantId());
			e.setFecha(comp3.getFecha());
			e.setNumeroAsiento(i);
			e.setPrecioEntrada(comp3.getPrecioEntrada());
			e.setVendida(false);
			
			EntradaDAO.guardarEntrada(e);
			
			comp3.addEntrada(e);
			
			} 
		
		Set<Competencia> comps1 = new HashSet<Competencia>();
		comps1.add(comp3);
		r3.setCompetencia(comps1);
		RondaDAO.modificarRonda(r3);
			
		
		comp3.setRonda(r3);
		DeportistaDAO.guardarDeportista(du);
		DeportistaDAO.guardarDeportista(da3);
		
		
		
		
		comp3.addDeportista(du);
		comp3.addDeportista(da3);
		
		CompetenciaDAO.modificarCompetencia(comp3);	
		
		
	
	///////////////////////////////////////////////////////////////////////////////// Se crea la primer competencia de ciclismo	
	
		
		Set<Deportista> deportistasCiclismo = new HashSet<Deportista>();		

		deportistasCiclismo.add(du5);
		deportistasCiclismo.add(da6);
		deportistasCiclismo.add(da6);
		deportistasCiclismo.add(de8);
		deportistasCiclismo.add(de9);
		deportistasCiclismo.add(da9);
		deportistasCiclismo.add(da10);
		

		 
		 Set<Ronda> setRondasCiclismo = ciclismo1.getRonda();
		 Ronda r4 = new Ronda();
		 for(Ronda i : setRondasCiclismo){
			 if(i.getNumeroRonda()==1){
				 r4 = i;
			 }
			 
		 }
		 
		 
	
		//Colectivo
		Competencia c4 = new Competencia(1, datec1, "Circuito callejero", 250.0F, false, 15, ciclismo1, j1, r4, null, 0, deportistasCiclismo, null,1);		
		CompetenciaDAO.guardarCompetencia(c4);
		

		
		
		
		
		
		Competencia comp4 = CompetenciaDAO.buscarCompetencia(1, 4);
		
		for(int i = 1; i< (comp4.getCantEntradas()+1); i++){
			
			Entrada e = new Entrada();
			e.setCompetencia(comp4);
			e.setTenantId(comp4.getTenantId());
			e.setFecha(comp4.getFecha());
			e.setNumeroAsiento(i);
			e.setPrecioEntrada(comp4.getPrecioEntrada());
			e.setVendida(false);
			
			EntradaDAO.guardarEntrada(e);
			
			comp4.addEntrada(e);
			
			} 
		

		
	
		comps.add(comp4);
		
 
		r4.setCompetencia(comps);
		
		RondaDAO.modificarRonda(r4);
		
		DeportistaDAO.guardarDeportista(du5);
		DeportistaDAO.guardarDeportista(du6);
		DeportistaDAO.guardarDeportista(de8);
		DeportistaDAO.guardarDeportista(de9);
		DeportistaDAO.guardarDeportista(da9);
		DeportistaDAO.guardarDeportista(da10);
		
		
		
		comp4.setRonda(r4);
		comp4.addDeportista(du5);
		comp4.addDeportista(du6);
		comp4.addDeportista(de8);
		comp4.addDeportista(de9);
		comp4.addDeportista(da9);
		comp4.addDeportista(da10);
		
	
		CompetenciaDAO.modificarCompetencia(comp4);	
		
		///////////////////////////////////////////////////// Alta novedades ////////////////////
	
		Novedad n = new Novedad();
		
		n.setDescripcion("Manu Ginóbili dejó la tranquilidad familiar en San Antonio y bajó hasta Ciudad de México para vivir el partido clave del seleccionado frente a México. El bahiense sufrió todo el partido, al lado del presidente de la CABB, Federico Susbielles. Se tironeó la gorra, se agarró la cabeza, hizo comentarios y vivió la tensión como si jugará el pasaporte a Río 2016. Ah, sobre eso, ¿qué vas a hacer ahora Manu después de la temporada NBA? ");
		n.setColumna(1);
		n.setComite_olimpico(comiteArgentino);
		n.setTitulo("¿Estas listo para Rio?");
		n.setTenantID(1);
		
		Imagen ni = new Imagen();
		ni.setMime("image/jpeg");
		ni.setRuta("C:\\Users\\USUARIO\\git\\EventosSGEM\\EventosSGEM\\WebContent\\resources\\defecto\\img\\Tenant1\\novedades\\manuychapu.jpg");
		ni.setTenantId(1);
		ImagenDAO.guardarImagen(ni);
		n.setImagen(ni);
		
		NovedadDAO.guardarNovedad(n);
		
		
	Novedad n1 = new Novedad();
		
		n1.setDescripcion("El nadador santafesino Federico Grabich, ganador de una medalla de oro y una de plata en los Juegos Panamericanos de Toronto 2015, regresó al país tras su histórico tercer puesto en el Mundial de pileta larga de Kazan, Rusia, y aseguró que quiere llegar de la mejor manera a los Juegos Olímpicos de Río de Janeiro 2016."+

"Estamos muy bien a un año de Río, es una doble motivación. Nos entrenamos muy duro para los dos torneos, los Panamericanos y el Mundial, y salió mucho mejor de lo que pensábamos, sobre todo por las marcas, aunque las medallas también son lindas, reconoció Grabich, de 25 años, luego de aterrizar en el Aeropuerto de Ezeiza."+

"El deportista bajó sus propios récords, ganó en los Panamericanos la medalla de oro en los 100 metros libres y la de plata en los 200 metros libres, y se subió al tercer escalón del podio en el Mundial de Kazan, algo que nunca había conseguido un nadador argentino en un Mundial en pileta de 50 metros."+

"En cuanto a sus chances a futuro, el oriundo de Casilda sostuvo: Creo que puedo nadar con mejores marcas, pero es cuestión de seguir entrenándome y limando detalles para bajar más de medio segundo. Mi objetivo es bajar los 48 segundos (en los 100 libres), pero no me pongo tiempos. Quiero llegar de la mejor manera a Río, no tengo una final y menos una medalla asegurada en los Juegos"+

"Grabich se sorprendió del recibimiento en Ezeiza, tras estar 40 días fuera de la Argentina. Es una alegría muy grande, hay mucha gente en el aeropuerto, pero me lo tomo con calma, aseguró, en declaraciones realizadas en TyC Sports.");
		n1.setColumna(1);
		n1.setComite_olimpico(comiteArgentino);
		n1.setTitulo("Federico Grabich: Quiero llegar de la mejor manera a los Juegos Olímpicos");
		n1.setTenantID(1);
		
		Imagen n1i = new Imagen();
		n1i.setMime("image/jpeg");
		n1i.setRuta("C:\\Users\\USUARIO\\git\\EventosSGEM\\EventosSGEM\\WebContent\\resources\\defecto\\img\\Tenant1\\novedades\\novedadGrabich.jpg");
		n1i.setTenantId(1);
		ImagenDAO.guardarImagen(n1i);
		n1.setImagen(n1i);
		
		NovedadDAO.guardarNovedad(n1);
	
		
		Novedad n2 = new Novedad();
		
		n2.setDescripcion("Según publica el diario La República, a los 40 años de edad, Milton Wynants, que obtuvo la medalla de plata en los Juegos Olímpicos de Sidney 2000, volverá a competir oficialmente en ciclismo y quiere cerrar su carrera con la competencia que se realizará en Rio 2016. El Gorra, que hace dos temporadas que no compite, lo hará con Estudiantes de Colonia, quiere volver a estar en la Rutas de América y en la Vuelta Ciclista del Uruguay"+
        "El sanducero que ganó cinco medallas en juegos Panamericanos, dos de oro en 2003, una de plata en 1995 y dos de bronce en Río 2007 y Winnipeg 1999, sabe que está en un equipo en formación y que debe ir entrenando lentamente."+
        "Omnium es a donde apunta Wynants para el 2016, en esta categoría los participantes compiten en seis disciplinas: velocidad y fuerza, kilómetros contra reloj, scratch, prueba por puntos, keirin y Madison.");
		n2.setColumna(2);
		n2.setComite_olimpico(comiteUruguayo);
		n2.setTitulo("Wynants quiere estar en Rio 2016");
		n2.setTenantID(1);
		
		Imagen n2i = new Imagen();
		n2i.setMime("image/jpeg");
		n2i.setRuta("C:\\Users\\USUARIO\\git\\EventosSGEM\\EventosSGEM\\WebContent\\resources\\defecto\\img\\Tenant1\\novedades\\novedadMilton.jpg");
		n2i.setTenantId(1);
		ImagenDAO.guardarImagen(n2i);
		n2.setImagen(n2i);
		
		NovedadDAO.guardarNovedad(n2);
	
		
		
		System.out.println("Guarde deportistas");
		
		
		/***** Historial *****/
		
		Date date1 = null,date2 = null,date3 = null,date4 = null,date5 = null,date6 = null,date7 = null,date8 = null;
		
		try {
			date1 = formatter.parse("28/10/2015 14:30:00");
			date2 = formatter.parse("30/10/2015 17:40:00");
			date3 = formatter.parse("05/11/2015 14:30:00");
			date4 = formatter.parse("15/11/2015 13:30:00");
			date5 = formatter.parse("30/11/2015 14:00:00");
			date6 = formatter.parse("02/12/2015 08:30:00");
			date7 = formatter.parse("15/12/2015 10:00:00");
			date8 = formatter.parse("25/12/2015 20:30:00");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		HistorialLoginDAO.guardarHistorial(new HistorialLogin(1, date1, u));
		HistorialLoginDAO.guardarHistorial(new HistorialLogin(1, date2, u));
		HistorialLoginDAO.guardarHistorial(new HistorialLogin(1, date3, u));
		HistorialLoginDAO.guardarHistorial(new HistorialLogin(1, date4, u));
		HistorialLoginDAO.guardarHistorial(new HistorialLogin(1, date5, u));
		HistorialLoginDAO.guardarHistorial(new HistorialLogin(1, date6, u));
		HistorialLoginDAO.guardarHistorial(new HistorialLogin(1, date7, u));
		HistorialLoginDAO.guardarHistorial(new HistorialLogin(1, date8, u));

//		Token jwt = JWTUtil.generarToken( new DataUsuario(1, "mail@gmail.com", "f", "t", "y", "usuario", "data", 1,12345678, "123", "UsuarioComun"));
//	
//		String[] partes = (jwt.getToken()).split("\\.");
//		
//		try {
//			String payload = new String(Base64.decode(partes[1]));
//			System.out.println(payload);
//			JsonObject newObj = new JsonParser().parse(payload).getAsJsonObject();
			

//		Map<String, Object> body = new HashMap<String, Object>();
//		
//		body = JWTUtil.getBody(jwt.getToken());
//		String iss = (String) body.get(JWTUtil.BODY_ISS);
//		Integer exp = (Integer) body.get(JWTUtil.BODY_EXP);
		
    }
    
    
    
}