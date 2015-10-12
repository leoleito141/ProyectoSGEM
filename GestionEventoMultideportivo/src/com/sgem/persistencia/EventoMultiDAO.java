package com.sgem.persistencia;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.sgem.dominio.EventoMultideportivo;
import com.sgem.dominio.TenantHandler;

@Stateless
public class EventoMultiDAO implements IEventoMultiDAO {
	
	@PersistenceContext(unitName = "GestionEventoMultideportivo")
	private EntityManager em;

	public boolean guardarEvento(EventoMultideportivo evento) {

		try {
			em.persist(evento);
			return true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}
		
	public EventoMultideportivo buscarEvento(String nombre) {
		try{
			EventoMultideportivo evento = em.createQuery("SELECT u FROM EventoMultideportivo u WHERE u.nombre = '"+nombre+"'", EventoMultideportivo.class).getSingleResult();
			return evento;
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}

	}
	
	public boolean guardarTenant(TenantHandler tenant) {

		try {
			em.persist(tenant);
			return true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}

	

}
