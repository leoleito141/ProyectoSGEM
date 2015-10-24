package com.sgem.persistencia;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.sgem.dominio.Novedad;

@Stateless
public class NovedadDAO implements INovedadDAO {
		
	@PersistenceContext(unitName = "GestionEventoMultideportivo")
	private EntityManager em;

	@Override
	public boolean guardarNovedadDAO(Novedad n) {
		try {
			em.persist(n);
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	

}
