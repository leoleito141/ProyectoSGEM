package com.sgem.controladores;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import com.sgem.datatypes.DataEvento;
import com.sgem.datatypes.DataImagen;
import com.sgem.datatypes.DataPais;
import com.sgem.dominio.EventoMultideportivo;
import com.sgem.dominio.Imagen;
import com.sgem.dominio.Organizador;
import com.sgem.dominio.Pais;
import com.sgem.dominio.TenantHandler;
import com.sgem.persistencia.IEventoMultiDAO;
import com.sgem.persistencia.IImagenDAO;
import com.sgem.persistencia.IUsuarioDAO;
import com.sgem.seguridad.excepciones.AplicacionException;
import com.sgem.seguridad.excepciones.UsuarioNoEncontradoException;
import com.sgem.utilidades.ImagenUtil;

@Stateless
public class EventoMultiController implements IEventoMultiController {
	
	@EJB
	private IEventoMultiDAO  EventoMultiDAO;
	
	@EJB
	private IUsuarioDAO usuarioDAO;
	
	@EJB
	private IImagenDAO imagenDAO;

	@Override
	public boolean guardarEventoMultideportivo(DataEvento dataEvento) {
		System.out.println("Entre alta eventoController :" +dataEvento.toString());

		
		try {
			TenantHandler th = new TenantHandler();

			Pais p = new Pais();
			p.setPais(dataEvento.getPais().getPais());
			p.setCiudad(dataEvento.getPais().getCiudad());
			
			EventoMultideportivo evento = new EventoMultideportivo(dataEvento.getNombre(),p,dataEvento.getLogo(),dataEvento.getFechaInicio(),dataEvento.getFechaFin(),dataEvento.getFacebook(),
				dataEvento.getInstagram(),dataEvento.getTwitter(),dataEvento.getCanalYoutube(),dataEvento.getCss());
			
			List<EventoMultideportivo> listevento = new ArrayList<EventoMultideportivo>();
			
			Organizador org = new Organizador();
			org.setEmail(dataEvento.getEmailOrganizador());
			org.setPassword(dataEvento.getPasswordOrganizador());
			org.setTenantID(EventoMultiDAO.obtenerMaximoTenant() + 1);
			org.setEvento(evento);
			evento.setOrganizador(org);
			evento.setTenantHandler(th);
			listevento.add(evento);
			th.setEventos(listevento);			
			EventoMultiDAO.guardarTenant(th);

			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return false;
	}


	@Override
	public DataEvento obtenerDataTenant(String tenant) {

		DataEvento dt = null;
		EventoMultideportivo e;
		try {
			e = EventoMultiDAO.obtenerDataTenant(tenant);
			
			dt = new DataEvento();			
			dt.setTenantId(e.getTenantHandler().getTenantID());
			
			
			dt.setFacebook(e.getFacebook());
			dt.setCanalYoutube(e.getCanalYoutube());
			dt.setInstagram(e.getInstagram());
			dt.setTwitter(e.getTwitter());
			
			
			dt.setWidgetFacebook(e.getWidget_facebook());
			dt.setWidgetInstagram(e.getWidget_instagram());
			dt.setWidgetTwitter(e.getWidget_twitter());
			dt.setWidgetYoutube(e.getWidget_Youtube());
			
			
			dt.setNombre_url(e.getNombre());
			DataImagen fotoBanner = e.getImagenBanner()!=null ? getDataImagen(e.getImagenBanner()): null;
			DataImagen fotoFondo = e.getImagenFondo() !=null ? getDataImagen(e.getImagenFondo()):null;
			DataImagen fotoLogo = e.getImagenPagina()!=null ? getDataImagen(e.getImagenPagina()):null;			
					
			dt.setBanner(fotoBanner);
			dt.setFondo(fotoFondo);
			dt.setPagina(fotoLogo);
			
			dt.setColorFondo(e.getColorFondo());
			dt.setColorNews(e.getColorNoticias());
			
			dt.setPais(new DataPais(e.getPais().getPaisID(), e.getPais().getPais(), e.getPais().getCiudad()));
			
			dt.setFechaInicio(e.getFechaInicio());
			dt.setFechaFin(e.getFechaFin());
			
						
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return dt;

	}

	private DataImagen getDataImagen(Imagen i){
		
		DataImagen di = new DataImagen(i.getMime(),i.getRuta(),i.getTenantId());
		return di ;
	}
	
	
	public int traeridEventoMultit(int tenantID) {

		int idEventoMulti=0;
		
		try {
			idEventoMulti = EventoMultiDAO.traeridEventoMulti(tenantID);
			
						
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return idEventoMulti;

	}

	@Override
	public EventoMultideportivo obtenerEventoMultideportivoXTenantId(int tenantId) {
		
	
		
		try {
			return EventoMultiDAO.traerEventoMulti(tenantId);
			
						
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
		
	}
	
	@Override
	public String obtenerProximoTenant() {

		try {
			
			return ((Integer) (EventoMultiDAO.obtenerMaximoTenant()+1)).toString();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
		
	}

	
	@Override
	public List<Imagen> subirImagenConfiguracion(MultipartFormDataInput input) throws AplicacionException {
	
		String tenantId = "";
		List<Imagen> listImagenes = new ArrayList<Imagen>();
		
		try {
			Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
			List<InputPart> inputPartsBanner = uploadForm.get("fileBanner");
			List<InputPart> inputPartsFondo  = uploadForm.get("fileFondo");
			List<InputPart> inputPartsPagina = uploadForm.get("filePagina");
		
			tenantId = uploadForm.get("tenantId").get(0).getBodyAsString();
			ImagenUtil.chequearTenant(tenantId);			
			Imagen banner = ImagenUtil.salvarImagen(inputPartsBanner,tenantId);
			Imagen fondo  = ImagenUtil.salvarImagen(inputPartsFondo,tenantId);
			Imagen pagina = ImagenUtil.salvarImagen(inputPartsPagina,tenantId);
			listImagenes.add(banner);
			listImagenes.add(fondo);
			listImagenes.add(pagina);
		} catch (IOException e) {
			throw new AplicacionException("Error al subir la imagen");
		}	
		
		
		return listImagenes;
	}


	@Override
	public boolean guardarConfiguracion(DataEvento datosEvento) throws AplicacionException {
		boolean guardo = false;
		EventoMultideportivo eventoMulti = (EventoMultideportivo)EventoMultiDAO.traerEventoMulti(datosEvento.getTenantId());		
		System.out.println(eventoMulti);
		if(eventoMulti != null){
		
			Imagen banner = new Imagen(datosEvento.getBanner().getMime(), datosEvento.getBanner().getRuta(), datosEvento.getBanner().getTenantId());
			Imagen fondo  = new Imagen(datosEvento.getFondo().getMime(), datosEvento.getFondo().getRuta(), datosEvento.getFondo().getTenantId());
			Imagen pagina = new Imagen(datosEvento.getPagina().getMime(), datosEvento.getPagina().getRuta(), datosEvento.getPagina().getTenantId());

			if(imagenDAO.guardarImagen(banner) && imagenDAO.guardarImagen(fondo) && imagenDAO.guardarImagen(pagina) ){			
				
				
				eventoMulti.setImagenBanner(banner);
				eventoMulti.setImagenFondo(fondo);
				eventoMulti.setImagenPagina(pagina);
				
				if(datosEvento.getColorFondo()!=null)
				eventoMulti.setColorFondo(datosEvento.getColorFondo());
				
				if(datosEvento.getColorNews()!=null)
				eventoMulti.setColorNoticias(datosEvento.getColorNews());
				
				if(datosEvento.getWidgetFacebook()!=null)
					eventoMulti.setWidget_facebook(datosEvento.getWidgetFacebook());
				
				if(datosEvento.getWidgetInstagram()!=null)
					eventoMulti.setWidget_instagram(datosEvento.getWidgetInstagram());
				
				if(datosEvento.getWidgetTwitter()!=null)
					eventoMulti.setWidget_twitter(datosEvento.getWidgetTwitter());
				
				if(datosEvento.getWidgetYoutube()!=null)
					eventoMulti.setWidget_Youtube(datosEvento.getWidgetYoutube());
				
				EventoMultiDAO.guardarConfiguracion(eventoMulti);

				guardo=true;
				
			}else{
				System.out.println("No se guardaron las imagenes");
				guardo=false;
			}
			
		}else{
			System.out.println("No hay evento");
			guardo=false;
		}
		
		return guardo;
	}


	@Override
	public List<DataEvento> listarEventosMulti() throws AplicacionException {
		List<DataEvento> dt = null;
		try {
			dt = convertir(EventoMultiDAO.listarEventosMulti());		
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return dt;
	}
	
	private List<DataEvento> convertir(List<EventoMultideportivo> eventos) {
		List<DataEvento> dataEvento = new ArrayList<DataEvento>();
		DataEvento de;
		
			try {
				for ( EventoMultideportivo ev : eventos) {
					
				de = new DataEvento();			
				de.setTenantId(ev.getTenantHandler().getTenantID());
				
				
//				de.setFacebook(ev.getFacebook());
//				de.setCanalYoutube(ev.getCanalYoutube());
//				de.setInstagram(ev.getInstagram());
//				de.setTwitter(ev.getTwitter());
				
				
//				de.setWidgetFacebook(ev.getWidget_facebook());
//				de.setWidgetInstagram(ev.getWidget_instagram());
//				de.setWidgetTwitter(ev.getWidget_twitter());
//				de.setWidgetYoutube(ev.getWidget_Youtube());
				
				
				de.setNombre_url(ev.getNombre());
				DataImagen fotoBanner = ev.getImagenBanner()!=null ? getDataImagen(ev.getImagenBanner()): null;
				DataImagen fotoFondo = ev.getImagenFondo() !=null ? getDataImagen(ev.getImagenFondo()):null;
				DataImagen fotoLogo = ev.getImagenPagina()!=null ? getDataImagen(ev.getImagenPagina()):null;			
						
				de.setBanner(fotoBanner);
				de.setFondo(fotoFondo);
				de.setPagina(fotoLogo);
				
//				de.setColorFondo(ev.getColorFondo());
//				de.setColorNews(ev.getColorNoticias());
				
				de.setPais(new DataPais(ev.getPais().getPaisID(), ev.getPais().getPais(), ev.getPais().getCiudad()));
				
				de.setFechaInicio(ev.getFechaInicio());
				de.setFechaFin(ev.getFechaFin());
				
				
				dataEvento.add(de);
			}
							
			} catch (Exception ex) {
				ex.printStackTrace();
			}
				
		

		return dataEvento;

	}	

	
}
