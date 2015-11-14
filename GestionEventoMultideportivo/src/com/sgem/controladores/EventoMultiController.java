package com.sgem.controladores;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;

import org.apache.commons.io.IOUtils;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import com.sgem.datatypes.DataEvento;
import com.sgem.datatypes.DataImagen;
import com.sgem.datatypes.DataTenant;
import com.sgem.dominio.ComiteOlimpico;
import com.sgem.dominio.EventoMultideportivo;
import com.sgem.dominio.Imagen;
import com.sgem.dominio.Novedad;
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
			p.setPais(dataEvento.getDataPais().getPais());
			p.setCiudad(dataEvento.getDataPais().getCiudad());
			
			EventoMultideportivo evento = new EventoMultideportivo(dataEvento.getNombre(),p,dataEvento.getLogo(),dataEvento.getFechaInicio(),dataEvento.getFechaFin(),dataEvento.getFacebook(),
				dataEvento.getInstagram(),dataEvento.getHashtag(),dataEvento.getCanalYoutube(),dataEvento.getCss());
			
			List<EventoMultideportivo> listevento = new ArrayList<EventoMultideportivo>();
			
			Organizador org = new Organizador();
			org.setEmail(dataEvento.getEmailOrganizador());
			org.setPassword(dataEvento.getPasswordOrganizador());
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
			
			dt.setNombre_url(e.getNombre());
			DataImagen fotoBanner = e.getImagenBanner()!=null ? getDataImagen(e.getImagenBanner()): null;
			DataImagen fotoFondo = e.getImagenFondo() !=null ? getDataImagen(e.getImagenFondo()):null;
			DataImagen fotoLogo = e.getImagenPagina()!=null ? getDataImagen(e.getImagenPagina()):null;			
					
			dt.setBanner(fotoBanner);
			dt.setFondo(fotoFondo);
			dt.setPagina(fotoLogo);
			
			dt.setColorFondo(e.getColorFondo());
			dt.setColorNews(e.getColorNoticias());
			
						
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
		

		System.out.println(datosEvento);
	

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

	
}
