package com.sgem.persistencia;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.sgem.dominio.EventoDeportivo;
import com.sgem.dominio.Usuario;


@Stateless
public class EventoDeportivoDAO implements IEventoDeportivoDAO {
		
	@PersistenceContext(unitName = "GestionEventoMultideportivo")
	private EntityManager em;

	public boolean guardarEventoDeportivo(EventoDeportivo eventoDeportivo) {
		try {
			em.persist(eventoDeportivo);
			return true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	
	public List<String> listarDeportes(int tenantID, String sexo) {
		
		List<String> deportes = null;
		
		try {
			
			 deportes = em.createQuery("SELECT distinct ed.nombreDeporte FROM eventoDeportivo ed WHERE ed.tenantId = '"+tenantID+"' AND ed.sexo = '"+sexo+"'", String.class).getResultList();;
			 
			 return deportes;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	
	public List<String> listarDisciplinas(int tenantID, String nombreDeporte, String sexo) {
			
		List<String> disciplinas = null;
		
		try {
			
			disciplinas = em.createQuery("SELECT ed.disciplina FROM eventoDeportivo ed WHERE ed.tenantId = '"+tenantID+"' AND ed.sexo = '"+sexo+"' AND ed.nombreDeporte = '"+nombreDeporte+"'", String.class).getResultList();;
			 
			 return disciplinas;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


}
