package com.sgem.persistencia;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.sgem.dominio.EventoDeportivo;


@Stateless
public class EventoDAO implements IEventoDAO {
		
	@PersistenceContext(unitName = "GestionEventoMultideportivo")
	private EntityManager em;

	public boolean guardarEventoDeportivo(EventoDeportivo eventoDeportivo) {
		try {
			em.persist(eventoDeportivo);
			return true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}


}
