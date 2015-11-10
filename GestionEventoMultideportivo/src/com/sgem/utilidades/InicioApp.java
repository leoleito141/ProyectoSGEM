package com.sgem.utilidades;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
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
import com.sgem.dominio.EventoDeportivo;
import com.sgem.dominio.EventoMultideportivo;
import com.sgem.dominio.HistorialLogin;
import com.sgem.dominio.Juez;
import com.sgem.dominio.Organizador;
import com.sgem.dominio.Pais;
import com.sgem.dominio.Ronda;
import com.sgem.dominio.TenantHandler;
import com.sgem.dominio.Usuario;
import com.sgem.dominio.UsuarioComun;
import com.sgem.enums.Tipo;
import com.sgem.persistencia.ICompetenciaDAO;
import com.sgem.persistencia.IDeportistaDAO;
import com.sgem.persistencia.IEntradaDAO;
import com.sgem.persistencia.IEventoDeportivoDAO;
import com.sgem.persistencia.IEventoMultiDAO;
import com.sgem.persistencia.IHistorialLoginDAO;
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
			
		comite1 = new ComiteOlimpico();
		((ComiteOlimpico)comite1).setPais("Uruguay"); // para evitar esto, se declara a usuario2 como: 
													   //ComiteOlimpico usuario 2 = new ComiteOlimpico();
		((ComiteOlimpico)comite1).setCodigo("COU");
		comite1.setEmail("cou@gmail.com");
		comite1.setCanalYoutube("uruguay");
		comite1.setTwitter("uruguay");
		comite1.setFacebook("Comit� Ol�mpico Uruguayo");
		comite1.setPassword("cou123");
		comite1.setTenantID(1);			
		
		comite2 = new ComiteOlimpico();
		((ComiteOlimpico)comite2).setPais("Argentina"); 
		((ComiteOlimpico)comite2).setCodigo("COA");
		comite2.setEmail("coa@gmail.com");
		comite2.setCanalYoutube("uruguay");
		comite2.setTwitter("uruguay");
		comite2.setFacebook("Comite Olimpico Argentino");
		comite2.setPassword("coa123");
		comite2.setTenantID(1);			
		
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
		UsuarioDAO.guardarUsuario(comite1);	
		UsuarioDAO.guardarUsuario(comite2);	
//			UsuarioDAO.guardarUsuario(usuario3); 
	//	UsuarioDAO.guardarUsuario(usuario4); 
		
		
		TenantHandler th = new TenantHandler();
		Pais p = new Pais("Rusia","Sochi");	
		EventoMultideportivo evento = new EventoMultideportivo("SOCHI", p , "logo.jpg", new Date(), new Date(), "facebook/sochi","Instagram/sochi", "#sochi", "youtube/sochi", "sochi.css");
		List<EventoMultideportivo> listevento = new ArrayList<EventoMultideportivo>();
		
		
		
		

	//	Organizador org =(Organizador)UsuarioDAO.buscarUsuario(1,"dsa4");
		
		Organizador usuario4 = new Organizador();
		usuario4.setEmail("organizador@gmail.com");
		usuario4.setPassword("123");
		usuario4.setTenantID(1);
		usuario4.setEvento(evento);
		
		evento.setOrganizador(usuario4);
		evento.setTenant(th);
		listevento.add(evento);
		th.setEventos(listevento);
		
		
		
		EventoMultiDAO.guardarTenant(th);

		EventoDeportivo futbol = new EventoDeportivo(th.getTenantID(), "Futbol", null, new Date(), new Date(), "Masculino",  null, null,"colectivo");
		EventoDeportivoDAO.guardarEventoDeportivo(futbol,evento);
		
		EventoDeportivo basket = new EventoDeportivo(th.getTenantID(), "Basket", null, new Date(), new Date(), "Masculino",  null, null,"colectivo");
		EventoDeportivoDAO.guardarEventoDeportivo(basket,evento);
		
		EventoDeportivo basketF = new EventoDeportivo(th.getTenantID(), "Basket", null, new Date(), new Date(), "Femenino",  null, null,"colectivo");
		EventoDeportivoDAO.guardarEventoDeportivo(basketF,evento);
		
		EventoDeportivo Natacion1 = new EventoDeportivo(th.getTenantID(), "Natacion", "100M Libres", new Date(), new Date(), "Femenino",  null, null,"individual");
		EventoDeportivoDAO.guardarEventoDeportivo(Natacion1,evento);
		
		EventoDeportivo Natacion2 = new EventoDeportivo(th.getTenantID(), "Natacion", "200M Mariposa", new Date(), new Date(), "Masculino", null, null,"individual");
		EventoDeportivoDAO.guardarEventoDeportivo(Natacion2,evento);
		
		EventoDeportivo Natacion3 = new EventoDeportivo(th.getTenantID(), "Natacion", "Posta 4x100", new Date(), new Date(), "Femenino",  null, null,"individual");
		EventoDeportivoDAO.guardarEventoDeportivo(Natacion3,evento);
		
		EventoDeportivo Natacion4 = new EventoDeportivo(th.getTenantID(), "Natacion", "Posta 4x100", new Date(), new Date(), "Masculino",  null, null,"individual");
		EventoDeportivoDAO.guardarEventoDeportivo(Natacion4,evento);
		
		EventoDeportivo f	= EventoDeportivoDAO.traerEventoDeportivo(futbol);

		Ronda r = new Ronda();
		
		r.setNumeroRonda(1);
		r.setTenantId(f.getTenantId());
		r.setEventoDeportivo(f);
		f.addRonda(r);
		
		RondaDAO.guardarRonda(r);
		
		EventoDeportivo eventoDep	= EventoDeportivoDAO.traerEventoDeportivo(Natacion2);
	
		for (int i = 0; i < 4; i++) {
			
			int j = i+1;
			r = new Ronda();
			
			r.setNumeroRonda(j);
			r.setTenantId(eventoDep.getTenantId());
//			r.setEventoDepId(eventoDep.getEventoDepId());
			r.setEventoDeportivo(eventoDep);
			eventoDep.addRonda(r);
			
			RondaDAO.guardarRonda(r);
			
			
		}
		
   
		System.out.println("Alta de 4 usuarios completa");
			
		System.out.println("Obtengo el usuario dsa2");			
		ComiteOlimpico comiteUruguayo = (ComiteOlimpico) UsuarioDAO.buscarUsuario(1,"cou@gmail.com");
		ComiteOlimpico comiteArgentino = (ComiteOlimpico) UsuarioDAO.buscarUsuario(1,"coa@gmail.com");
		System.out.println("obtuve el usuario "+comiteUruguayo.getEmail()+" "+comiteUruguayo.getId());
		System.out.println(" y es "+comiteUruguayo.soy());
		
		Deportista du = new Deportista(1, "Negro", "Jefe",new Date(), "Masculino",new HashSet<EventoDeportivo>(),new HashSet<Competencia>(),null, null);			
		Deportista du1 = new Deportista(1, "Diego", "Forlan",new Date(), "Masculino",new HashSet<EventoDeportivo>(),new HashSet<Competencia>(),null, null);		
		Deportista du2 = new Deportista(1, "Luis", "Suarez",new Date(), "Masculino",new HashSet<EventoDeportivo>(),new HashSet<Competencia>(),null, null);			
		Deportista du3 = new Deportista(1, "Martin", "Caceres",new Date(), "Masculino",new HashSet<EventoDeportivo>(),new HashSet<Competencia>(),null, null);	
		Deportista du4 = new Deportista(1, "Debo", "Rodriguez",new Date(), "Femenino",new HashSet<EventoDeportivo>(),new HashSet<Competencia>(),null, null);	
		
		Deportista da = new Deportista(1, "Diego Armando", "Maradona",new Date(), "Masculino",new HashSet<EventoDeportivo>(),new HashSet<Competencia>(),null, null);			
		Deportista da1 = new Deportista(1, "La Brujita", "Veron",new Date(), "Masculino",new HashSet<EventoDeportivo>(),new HashSet<Competencia>(),null, null);		
		Deportista da2 = new Deportista(1, "El raton", "Ayala",new Date(), "Masculino",new HashSet<EventoDeportivo>(),new HashSet<Competencia>(),null, null);	
		Deportista da3 = new Deportista(1, "Javier", "Mascherano",new Date(), "Masculino",new HashSet<EventoDeportivo>(),new HashSet<Competencia>(),null, null);	
		Deportista da4 = new Deportista(1, "Lionel", "Messi",new Date(), "Femenino",new HashSet<EventoDeportivo>(),new HashSet<Competencia>(),null, null);		

		
		du.addEventoDeportivo(eventoDep);
		du1.addEventoDeportivo(eventoDep);
		du2.addEventoDeportivo(eventoDep);		
		
		du.setComiteOlimpico(comiteUruguayo);		
		du1.setComiteOlimpico(comiteUruguayo);
		du2.setComiteOlimpico(comiteUruguayo);
		du3.setComiteOlimpico(comiteUruguayo);
		du4.setComiteOlimpico(comiteUruguayo);
		
		da.setComiteOlimpico(comiteArgentino);
		da1.setComiteOlimpico(comiteArgentino);
		da2.setComiteOlimpico(comiteArgentino);
		da3.setComiteOlimpico(comiteArgentino);
		da4.setComiteOlimpico(comiteArgentino);
		
//		comiteUruguayo.agregarDeportista(du);
//		comiteUruguayo.agregarDeportista(du1);
//		comiteUruguayo.agregarDeportista(du2);
//		comiteUruguayo.agregarDeportista(du3);
//		comiteUruguayo.agregarDeportista(du4);
//		
//		comiteArgentino.agregarDeportista(da);
//		comiteArgentino.agregarDeportista(da1);
//		comiteArgentino.agregarDeportista(da2);
//		comiteArgentino.agregarDeportista(da3);
//		comiteArgentino.agregarDeportista(da4);
//		
		
	
		
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
			
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		
		/**** Competencias ***/
		
		UsuarioComun u = new UsuarioComun();
		u.setEmail("leo@gmail.com");
		u.setPassword("123");
		u.setTenantID(1);
		UsuarioDAO.guardarUsuario(u);
		
		Date datec1 = null,datec2 = null;
		try {			
			datec1 = formatter.parse("28/10/2015 14:30:00");
			datec2 = formatter.parse("30/10/2015 19:45:00");
		} catch (ParseException e) {
			e.printStackTrace();
		}
				
		Set<Deportista> deportistas = new HashSet<Deportista>();		

		deportistas.add(du);
		deportistas.add(du1);
		deportistas.add(du2);
		deportistas.add(du3);
		
		deportistas.add(da);
		deportistas.add(da1);
		deportistas.add(da2);
		deportistas.add(da3);
		

				
		 Iterator<Ronda> it = f.getRonda().iterator();
		 Ronda r2 = new Ronda();
		 if(it.hasNext()){
			 r2 = it.next();
		 }
		 
	
		//Colectivo
		Competencia c1 = new Competencia(1, datec1, "Estadio Centenario", 1500.0F, false, 76609, f, j, r2, null, 0, deportistas, null);		
		CompetenciaDAO.guardarCompetencia(c1);
		
		du.addCompetencia(c1);	
		du1.addCompetencia(c1);
		du2.addCompetencia(c1);
		du3.addCompetencia(c1);
		
		da.addCompetencia(c1);
		da1.addCompetencia(c1);
		da2.addCompetencia(c1);
		da3.addCompetencia(c1);
				
		
		Set<Entrada> entradas = new HashSet<Entrada>();
		
		Competencia comp = CompetenciaDAO.buscarCompetencia(1, 1);
		
		Entrada e1 = new Entrada(1, datec1, 1500.0F, 1, false, comp, u);
		Entrada e2 = new Entrada(1, datec1, 1500.0F, 1, false, comp, u);
		Entrada e3 = new Entrada(1, datec1, 1500.0F, 1, false, comp, u);
		Entrada e4 = new Entrada(1, datec1, 1500.0F, 1, false, comp, u);
		Entrada e5 = new Entrada(1, datec1, 1500.0F, 1, false, comp, u);
		
		EntradaDAO.guardarEntrada(e1);
		EntradaDAO.guardarEntrada(e2);
		EntradaDAO.guardarEntrada(e3);
		EntradaDAO.guardarEntrada(e4);
		EntradaDAO.guardarEntrada(e5);
		
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
		
		DeportistaDAO.guardarDeportista(du);
		DeportistaDAO.guardarDeportista(du1);
		DeportistaDAO.guardarDeportista(du2);
		DeportistaDAO.guardarDeportista(du3);
		DeportistaDAO.guardarDeportista(du4);

		DeportistaDAO.guardarDeportista(da);
		DeportistaDAO.guardarDeportista(da1);
		DeportistaDAO.guardarDeportista(da2);
		DeportistaDAO.guardarDeportista(da3);
		DeportistaDAO.guardarDeportista(da4);
		
		
		comp.setRonda(r2);
		comp.addDeportista(du);
		comp.addDeportista(du1);
		comp.addDeportista(du2);
		comp.addDeportista(du3);
		
		comp.addDeportista(da);
		comp.addDeportista(da1);
		comp.addDeportista(da2);
		comp.addDeportista(da3);
		
		CompetenciaDAO.modificarCompetencia(comp);	
		
//		DeportistaDAO.modificarDeportista(du);
//		DeportistaDAO.modificarDeportista(du1);
//		DeportistaDAO.modificarDeportista(du2);
//		DeportistaDAO.modificarDeportista(du3);
//		
//		DeportistaDAO.modificarDeportista(da);
//		DeportistaDAO.modificarDeportista(da1);
//		DeportistaDAO.modificarDeportista(da2);
//		DeportistaDAO.modificarDeportista(da3);
		
		deportistas = new HashSet<Deportista>();
		entradas = new HashSet<Entrada>();
		
		Iterator<Ronda> it2 = eventoDep.getRonda().iterator();
		Ronda r3 = new Ronda();
		if(it2.hasNext()){
			r3 = it2.next();			
		}
		
		//Inidividual		
		Competencia c2 = new Competencia(1, datec2, "Antel Arena", 2500.0F, false, 50000, eventoDep, j, r3, null, 0, deportistas, null);
		CompetenciaDAO.guardarCompetencia(c2);
		
		Competencia comp2 = CompetenciaDAO.buscarCompetencia(1, 2);
		
		e1 = new Entrada(1, datec2, 1500.0F, 1, false, comp2, u);
		e2 = new Entrada(1, datec2, 1500.0F, 1, false, comp2, u);
		e3 = new Entrada(1, datec2, 1500.0F, 1, false, comp2, u);
		e4 = new Entrada(1, datec2, 1500.0F, 1, false, comp2, u);
		e5 = new Entrada(1, datec2, 1500.0F, 1, false, comp2, u);
		
		EntradaDAO.guardarEntrada(e1);
		EntradaDAO.guardarEntrada(e2);
		EntradaDAO.guardarEntrada(e3);
		EntradaDAO.guardarEntrada(e4);
		EntradaDAO.guardarEntrada(e5);
		
		comps = new HashSet<Competencia>();
		comps.add(comp2);
		
//		Ronda r2 = new Ronda(1, 1, eventoDep,comps);		
//		RondaDAO.guardarRonda(r2);
		
		comp2.setRonda(r3);		
		
		r3.setCompetencia(comps);
		
		RondaDAO.modificarRonda(r3);
		
		du.addCompetencia(comp2);	
		du3.addCompetencia(comp2);
		
		da.addCompetencia(comp2);
		da1.addCompetencia(comp2);
		da3.addCompetencia(comp2);
		
		DeportistaDAO.modificarDeportista(du);
		DeportistaDAO.modificarDeportista(du3);
		
		DeportistaDAO.modificarDeportista(da);
		DeportistaDAO.modificarDeportista(da1);
		DeportistaDAO.modificarDeportista(da3);
				
		comp2.addDeportista(du);
		comp2.addDeportista(du3);
		
		comp2.addDeportista(da);
		comp2.addDeportista(da1);
		comp2.addDeportista(da3);
		
		CompetenciaDAO.modificarCompetencia(comp2);	
		
		

		
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
		
		HistorialLoginDAO.guardarHistorial(new HistorialLogin(1, date1, u,Tipo.LOGIN));
		HistorialLoginDAO.guardarHistorial(new HistorialLogin(1, date2, u,Tipo.LOGIN));
		HistorialLoginDAO.guardarHistorial(new HistorialLogin(1, date3, u,Tipo.LOGIN));
		HistorialLoginDAO.guardarHistorial(new HistorialLogin(1, date4, u,Tipo.LOGIN));
		HistorialLoginDAO.guardarHistorial(new HistorialLogin(1, date5, u,Tipo.LOGIN));
		HistorialLoginDAO.guardarHistorial(new HistorialLogin(1, date6, u,Tipo.LOGIN));
		HistorialLoginDAO.guardarHistorial(new HistorialLogin(1, date7, u,Tipo.LOGIN));
		HistorialLoginDAO.guardarHistorial(new HistorialLogin(1, date8, u,Tipo.LOGIN));

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