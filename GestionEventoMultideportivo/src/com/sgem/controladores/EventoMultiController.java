package com.sgem.controladores;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.sgem.datatypes.DataEvento;
import com.sgem.dominio.EventoMultideportivo;
import com.sgem.dominio.TenantHandler;
import com.sgem.persistencia.IEventoMultiDAO;

@Stateless
public class EventoMultiController implements IEventoMultiController {
	
	@EJB
	IEventoMultiDAO  EventoMultiDAO;


	@Override
	public boolean guardarEventoMultideportivo(DataEvento dataEvento) {
		System.out.println("Entre alta eventoController :" +dataEvento.toString());

		
		try {
			TenantHandler th = new TenantHandler();
			EventoMultideportivo evento = new EventoMultideportivo(dataEvento.getNombre(),dataEvento.getLugar(),dataEvento.getLogo(),dataEvento.getFechaInicio(),dataEvento.getFechaFin(),dataEvento.getFacebook(),
					dataEvento.getHashtag(),dataEvento.getCanalYoutube(),dataEvento.getCss());
			System.out.println("Entre alta eventoController :" +evento.toString());

			List<EventoMultideportivo> listevento = new ArrayList<EventoMultideportivo>();
			evento.setTenant(th);
			listevento.add(evento);
			th.setEventos(listevento);
			EventoMultiDAO.guardarTenant(th);
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return true;
	}
	
	
	

}
