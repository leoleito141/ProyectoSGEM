package com.sgem.persistencia;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.sgem.dominio.Admin;
import com.sgem.dominio.EventoMultideportivo;
import com.sgem.dominio.Organizador;
import com.sgem.dominio.Usuario;

@Stateless
public class EventoDAO implements IEventoDAO {
		
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
		EventoMultideportivo evento;
		try{
			return evento = em.createQuery("SELECT u FROM EventoMultideportivo u WHERE u.nombre = '"+nombre+"'", EventoMultideportivo.class).getSingleResult();
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}

	}


}
