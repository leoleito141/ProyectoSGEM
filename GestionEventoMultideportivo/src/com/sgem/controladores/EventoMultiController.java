package com.sgem.controladores;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.sgem.datatypes.DataEvento;
import com.sgem.datatypes.DataTenant;
import com.sgem.dominio.EventoMultideportivo;
import com.sgem.dominio.Organizador;
import com.sgem.dominio.TenantHandler;
import com.sgem.persistencia.IEventoMultiDAO;
import com.sgem.persistencia.IUsuarioDAO;

@Stateless
public class EventoMultiController implements IEventoMultiController {
	
	@EJB
	IEventoMultiDAO  EventoMultiDAO;
    IUsuarioDAO usuarioDAO;

	@Override
	public boolean guardarEventoMultideportivo(DataEvento dataEvento) {
		System.out.println("Entre alta eventoController :" +dataEvento.toString());

		
		try {
			TenantHandler th = new TenantHandler();
			EventoMultideportivo evento = new EventoMultideportivo(dataEvento.getNombre(),dataEvento.getLugar(),dataEvento.getLogo(),dataEvento.getFechaInicio(),dataEvento.getFechaFin(),dataEvento.getFacebook(),
					dataEvento.getHashtag(),dataEvento.getCanalYoutube(),dataEvento.getCss());
			System.out.println("Entre alta eventoController :" +evento.toString());
			
			List<EventoMultideportivo> listevento = new ArrayList<EventoMultideportivo>();
			
			Organizador org = new Organizador();
			org.setEmail(dataEvento.getEmailOrganizador());
			org.setPassword(dataEvento.getPasswordOrganizador());
//			org.setTenantID(eventoGuardado.getTenant().getTenantID());
			org.setEvento(evento);
			evento.setOrganizador(org);
			evento.setTenant(th);
			listevento.add(evento);
			th.setEventos(listevento);
			
			
			
			EventoMultiDAO.guardarTenant(th);
			
//			EventoMultideportivo eventoGuardado = EventoMultiDAO.buscarEvento(evento.getNombre());
			
			
//			usuarioDAO.guardarUsuario(org);
//			EventoMultiDAO.guardarEvento(eventoGuardado);
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return true;
	}


	@Override
	public DataTenant obtenerDataTenant(String tenant) {

		DataTenant dt = null;
		EventoMultideportivo e;
		try {
			e = EventoMultiDAO.obtenerDataTenant(tenant);
			
			dt = new DataTenant();
			dt.setNombre_url(e.getNombre());
			dt.setTenantId(e.getTenant().getTenantID());
			dt.setLogin_back_img(e.getLogo());
			dt.setRegistro_back_img(e.getLogo());
						
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return dt;

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

}
