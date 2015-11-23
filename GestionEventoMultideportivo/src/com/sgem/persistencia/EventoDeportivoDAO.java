package com.sgem.persistencia;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import com.sgem.dominio.EventoDeportivo;
import com.sgem.dominio.EventoMultideportivo;


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



	@Override
	public EventoDeportivo traerEventoDeportivo(EventoDeportivo eventoDeportivo) {
		
					EventoDeportivo ed = null;
				
				
				try{
					
					String sql = "SELECT ed "
				 				+ "FROM EventoDeportivo ed "
				 				+ "WHERE ed.tenantId = "+eventoDeportivo.getTenantId()+" AND ed.sexo = '"+eventoDeportivo.getSexo()+
				 				"' AND ed.nombreDeporte = '"+eventoDeportivo.getNombreDeporte()+"'";
					sql+= eventoDeportivo.getDisciplina() != null ? "AND ed.disciplina = '"+eventoDeportivo.getDisciplina()+"'" : "";
					 
					ed= em.createQuery(sql, EventoDeportivo.class).getSingleResult();
					 
					return ed;
				
					
				}catch(NoResultException e){
					e.printStackTrace();
					return null;
				}
		
		
		
	}

	@Override
	public List<Integer> listarRondas(int tenantID, String nombreDeporte, String sexo, String nombreDisciplina) {
		
		List<Integer> rondas = null;
		
		try {
			
			rondas = em.createQuery("SELECT r.numeroRonda FROM EventoDeportivo ed, Ronda r WHERE ed.tenantId = '"+tenantID+"' AND r.eventoDeportivo = ed.EventoDepId AND r.tenantId = '"+tenantID+"' AND ed.sexo = '"+sexo+"' AND ed.nombreDeporte = '"+nombreDeporte+"' AND ed.disciplina = '"+nombreDisciplina+"'", Integer.class).getResultList();;
			 
			 return rondas;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}



	@Override
	public List<EventoDeportivo> listarDeportes(int tenantID) {
		List<EventoDeportivo> deportes = null;		
		try {			
			 deportes = em.createQuery("SELECT ed FROM EventoDeportivo ed WHERE ed.tenantId = "+tenantID, EventoDeportivo.class).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return deportes;
		}
		return deportes;
	}



	@Override
	public List<EventoDeportivo> listarDisciplinasEventoDeportivo(int tenantID,	String nombreDeporte){		
		List<EventoDeportivo> deportes = null;		
		try {			
			 deportes = em.createQuery("SELECT ed FROM EventoDeportivo ed WHERE ed.tenantId = "+tenantID+" AND ed.nombreDeporte = '"+nombreDeporte+"'", EventoDeportivo.class).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return deportes;
		}
		return deportes;
	}




}
