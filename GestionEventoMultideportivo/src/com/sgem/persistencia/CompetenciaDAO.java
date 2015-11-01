package com.sgem.persistencia;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.sgem.datatypes.DataCompetencia;
import com.sgem.dominio.Competencia;
import com.sgem.dominio.Juez;

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

	@Override
	public List<Competencia> listarCompetenciasPorRonda(int tenantID, int idEventoDep, int ronda) {
		
		List<Competencia> competencias = null;
		
		try {
			
			competencias = em.createQuery("SELECT c FROM Competencia c, Ronda r WHERE r.tenantId = c.tenantId AND r.rondaId = c.ronda AND r.numeroRonda = '"+ronda+"' AND r.EventoDepId = '"+idEventoDep+"'", Competencia.class).getResultList();;
			 
			 return competencias;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	


}
