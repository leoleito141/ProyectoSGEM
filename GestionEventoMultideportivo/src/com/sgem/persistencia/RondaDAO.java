package com.sgem.persistencia;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import com.sgem.dominio.Ronda;


@Stateless
public class RondaDAO implements IRondaDAO {
		
	@PersistenceContext(unitName = "GestionEventoMultideportivo")
	private EntityManager em;

	@Override
	public boolean guardarRonda(Ronda ronda) {
		try {
			em.persist(ronda);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}		
	}

	@Override
	public Ronda buscarRonda(int tenantID, int idRonda) {		
		try {		
			return em.find(Ronda.class, idRonda);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}		
	}
	
	@Override
	public Ronda buscarRonda(int tenantID, int idEventoDep, int numeroRonda) {
		
		Ronda r = null;		
		try{	
			r = em.createQuery("SELECT r FROM Ronda r  WHERE r.tenantId = "+tenantID+" AND r.eventoDeportivo = '"+idEventoDep+"' AND r.numeroRonda = '"+numeroRonda+"'", Ronda.class).getSingleResult();
			return r;
		}catch(NoResultException e){
			e.printStackTrace();
			return null;
		}	
	}

	@Override
	public boolean modificarRonda(Ronda r) {
		try {
			em.merge(r);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}		
	}
}
