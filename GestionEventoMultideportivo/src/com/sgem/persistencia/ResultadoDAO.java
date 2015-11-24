package com.sgem.persistencia;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.sgem.dominio.ComiteOlimpico;
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

	@Override
	public Resultado traerResultado(int tenantID, int competenciaID) {
		List<Resultado> r = new ArrayList<Resultado>();
		
		try{
			r = em.createQuery("SELECT r FROM Resultado r, Competencia c WHERE r.competencia = '"+competenciaID+"'AND c.CompetenciaId = '"+competenciaID+"' AND c.tenantId = '"+tenantID+"' AND r.tenantId = "+tenantID, Resultado.class).getResultList();			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}		
		return (r.isEmpty() ? null : r.get(0));		
	}
}
