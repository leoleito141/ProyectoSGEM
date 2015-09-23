package com.sgem.persistencia;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.sgem.dominio.Admin;
import com.sgem.dominio.Deportista;
import com.sgem.dominio.Organizador;
import com.sgem.dominio.Usuario;

@Stateless
public class DeportistaDAO implements IDeportistaDAO {
		
	@PersistenceContext(unitName = "GestionEventoMultideportivo")
	private EntityManager em;

	@Override
	public boolean guardarDeportista(Deportista d) {
		try {
			em.persist(d);
			return true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	

}
