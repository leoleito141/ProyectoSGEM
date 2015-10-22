package com.sgem.persistencia;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import com.sgem.dominio.ComiteOlimpico;
import com.sgem.dominio.EventoDeportivo;
import com.sgem.dominio.EventoMultideportivo;
import com.sgem.dominio.Usuario;


@Stateless
public class EventoDeportivoDAO implements IEventoDeportivoDAO {
		
	@PersistenceContext(unitName = "GestionEventoMultideportivo")
	private EntityManager em;
	
	

	
	public List<String> listarDeportes(int tenantID, String sexo) {
		
		List<String> deportes = null;
		
		try {
			
			 deportes = em.createQuery("SELECT distinct ed.nombreDeporte FROM EventoDeportivo ed WHERE ed.tenantId = "+tenantID+" AND ed.sexo = '"+sexo+"'", String.class).getResultList();;
			 
			 return deportes;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	
	public List<String> listarDisciplinas(int tenantID, String sexo, String nombreDeporte) {
			
		List<String> disciplinas = null;
		
		try {
			
			disciplinas = em.createQuery("SELECT ed.disciplina FROM EventoDeportivo ed WHERE ed.tenantId = "+tenantID+" AND ed.sexo = '"+sexo+"' AND ed.nombreDeporte = '"+nombreDeporte+"'", String.class).getResultList();;
			 
			 return disciplinas;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public boolean guardarEventoDeportivo(EventoDeportivo eventoDeportivo, EventoMultideportivo emd) {
		
	try {
			
			eventoDeportivo.setEventoMultideportivo(emd);			
			em.merge(eventoDeportivo);			
			return true;

		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return false;
	}



	@Override
	public EventoDeportivo traerEventoDeportivo(Integer idEventoDep) {
		EventoDeportivo ed = null;
		
		
		try{
			
			 
			ed= em.find(EventoDeportivo.class,idEventoDep);
			 
			 return ed;
		
			
		}catch(NoResultException e){
			e.printStackTrace();
			return null;
		}
		
	}



	@Override
	public Integer traerIDEventoDeportivo(Integer tenantId, String deporte, String disciplina, String sexo) {
		
		
		List<Integer> idEventoDeportivo = null;
		
		try {
			
			idEventoDeportivo = em.createQuery("SELECT ed.EventoDepId FROM EventoDeportivo ed WHERE ed.tenantId = "+tenantId+" AND ed.sexo = '"+sexo+"' AND ed.nombreDeporte = '"+deporte+"'AND ed.disciplina = '"+disciplina+"'", Integer.class).getResultList();;
			 
			if (idEventoDeportivo.isEmpty()) {
			    return null; // handle no-results case
			} else {
			    return idEventoDeportivo.get(0);
			}
			
			 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
		
		
		
	


}
