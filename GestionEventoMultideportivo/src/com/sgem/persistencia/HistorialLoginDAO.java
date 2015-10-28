package com.sgem.persistencia;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.sgem.dominio.HistorialLogin;

@Stateless
public class HistorialLoginDAO implements IHistorialLoginDAO {
		
	@PersistenceContext(unitName = "GestionEventoMultideportivo")
	private EntityManager em;

	@Override
	public boolean guardarHistorial(HistorialLogin hl) {
		try {
			em.persist(hl);
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public List<HistorialLogin> recuperarHistorial(int tenantId) {
		List<HistorialLogin> historial = new ArrayList<HistorialLogin>();
		try {
			
			historial = em.createQuery("SELECT hl FROM HistorialLogin hl WHERE hl.tenantId = "+tenantId, HistorialLogin.class).getResultList();
			
		} catch (Exception e) {
			e.printStackTrace();
			return historial;
		}
		
		return historial;
	}

}
