package com.sgem.persistencia;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.sgem.dominio.Competencia;

@Stateless
public class CompetenciaDAO implements ICompetenciaDAO{

	@PersistenceContext(unitName = "GestionEventoMultideportivo")
	private EntityManager em;
	
	@Override
	public boolean guardarCompetencia(Competencia c) {
		boolean guardo = false;
		
		try {
			em.persist(c);
			guardo = true;
		} catch (Exception e) {
			e.printStackTrace();
			return guardo; 
		}
		return guardo;

	}


}
