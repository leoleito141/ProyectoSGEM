package com.sgem.persistencia;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.sgem.dominio.Novedad;

@Stateless
public class NovedadDAO implements INovedadDAO {
		
	@PersistenceContext(unitName = "GestionEventoMultideportivo")
	private EntityManager em;

	@Override
	public boolean guardarNovedad(Novedad n) {
		try {
			em.persist(n);
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	@Override
	public List<Novedad> getNovedades(int tenantID) {
		try {
			
			List<Novedad> novedades= new ArrayList<Novedad>();
			novedades =  em.createQuery("SELECT e FROM Novedad e WHERE e.tenantID = '"+tenantID+"'", Novedad.class).getResultList();
			
			return novedades;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		
	}
	
	@Override
	public List<Novedad> getNovedadesComite(Integer tenantID, Integer comiteID) {		
		List<Novedad> novedades= new ArrayList<Novedad>();
		try {			
			novedades =  em.createQuery("SELECT e FROM Novedad e WHERE e.tenantID = '"+tenantID+"' AND comite_olimpico = "+comiteID, Novedad.class).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return novedades;
	}

	
	@Override
	public Novedad getNovedad(int idnovedad) {
	try {
				
		
		Novedad novedad = em.find(Novedad.class, idnovedad);
		
		return novedad;

	} catch (Exception e) {
		e.printStackTrace();
		return null;
	}
	}
	
}
