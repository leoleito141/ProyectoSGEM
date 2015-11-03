package com.sgem.persistencia;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.sgem.datatypes.DataCompetencia;
import com.sgem.dominio.Competencia;
import com.sgem.dominio.Juez;
import com.sgem.dominio.Usuario;
import com.sgem.dominio.UsuarioComun;

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
			
			competencias = em.createQuery("SELECT c FROM Competencia c, Ronda r WHERE r.tenantId = c.tenantId AND r.rondaId = c.ronda AND c.finalizada='"+false+"' AND r.numeroRonda = '"+ronda+"' AND r.EventoDepId = '"+idEventoDep+"'", Competencia.class).getResultList();;
			 
			 return competencias;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public float obtenerPrecio(int tenantID, int idCompetencia) {
		List<Competencia> c = new ArrayList<Competencia>();
		float precio = 0; 
		
		try{
		
		c = em.createQuery("SELECT c FROM Competencia c WHERE c.tenantId = '"+tenantID+"' AND c.CompetenciaId='"+idCompetencia+"' AND c.finalizada ='"+false+"'", Competencia.class).getResultList();			
		
		return precio = c.get(0).getPrecioEntrada();
		
		
		}catch(Exception e){
			e.printStackTrace();
		}
		return precio;
		
	}

	
	@Override
	public Competencia buscarCompetencia(int tenantID, int idCompetencia) {
		Competencia c = new Competencia ();
			
		try{
		
		return c = em.createQuery("SELECT c FROM Competencia c WHERE c.tenantId = '"+tenantID+"' AND c.CompetenciaId='"+idCompetencia+"'", Competencia.class).getSingleResult();			
	
		}catch(Exception e){
			e.printStackTrace();
		}
		return c;
		
	}

	
	


}
