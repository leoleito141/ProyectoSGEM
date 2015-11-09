package com.sgem.persistencia;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.sgem.dominio.Estadistica;


@Stateless
public class EstadisticaDAO implements IEstadisticaDAO {
		
	@PersistenceContext(unitName = "GestionEventoMultideportivo")
	private EntityManager em;

	@Override
	public boolean guardarEstadistica(Estadistica e) {
		try {
			em.persist(e);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}		
	}

	@Override
	public Estadistica buscarEstadistica(int tenantID, int idEstadistica) {		
		try {		
			return em.find(Estadistica.class, idEstadistica);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}		
	}
	
	@Override
	public boolean modificarEstadistica(Estadistica e) {
		try {
			em.merge(e);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}		
	}
}
