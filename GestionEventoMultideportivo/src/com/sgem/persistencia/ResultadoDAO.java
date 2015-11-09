package com.sgem.persistencia;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.sgem.dominio.Resultado;


@Stateless
public class ResultadoDAO implements IResultadoDAO {
		
	@PersistenceContext(unitName = "GestionEventoMultideportivo")
	private EntityManager em;

	@Override
	public boolean guardarResultado(Resultado r) {
		try {
			em.persist(r);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}		
	}

	@Override
	public Resultado buscarResultado(int tenantID, int idResultado) {		
		try {		
			return em.find(Resultado.class, idResultado);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}		
	}
	
	@Override
	public boolean modificarResultado(Resultado r) {
		try {
			em.merge(r);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}		
	}
}
