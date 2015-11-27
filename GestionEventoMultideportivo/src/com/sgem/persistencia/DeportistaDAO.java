package com.sgem.persistencia;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.sgem.dominio.Competencia;
import com.sgem.dominio.Deportista;
import com.sgem.dominio.EventoDeportivo;

@Stateless
public class DeportistaDAO implements IDeportistaDAO {
		
	@PersistenceContext(unitName = "GestionEventoMultideportivo")
	private EntityManager em;

	@Override
	public boolean guardarDeportista(Deportista d) {
		try {
			em.persist(d);
			return true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Deportista> listarDeportistas(int tenantID, String nombreDeporte, String sexo, String nombreDisciplina) {
		
		try{
			List<Deportista> deportistas = em.createNativeQuery("SELECT d.* FROM EventoDeportivo ed, Deportista d, deportista_eventodeportivo de WHERE ed.tenant_ID = '"+tenantID+"' AND d.tenantId = '"+tenantID+"' AND ed.sexo = '"+sexo+"' AND ed.nombreDeporte = '"+nombreDeporte+"' AND  ed.disciplina = '"+nombreDisciplina+"' AND de.Deportista_deportistaID = d.deportistaID AND de.eventoDep_EventoDepId = ed.EventoDepId AND d.Sexo = '"+sexo+"';",Deportista.class).getResultList();
			return deportistas;
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
		
	}

	@Override
	public Deportista buscarDeportista(int idDeportista) {
		
		
		try {
			
			return em.find(Deportista.class, idDeportista);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean modificarDeportista(Deportista deportista) {
		try {
			em.merge(deportista);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}		
	}

	@Override
	public List<Deportista> listarDeportistasPorComite(int tenantID, int comiteID) {
		List<Deportista> deportistas = new ArrayList<Deportista>();
		try{
			deportistas = em.createQuery("SELECT d FROM Deportista d WHERE d.tenantID ="+tenantID+" AND d.comiteOlimpico = "+comiteID,Deportista.class).getResultList();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		return deportistas;
	}
	
	@Override
	public List<EventoDeportivo> listarEventoDeportivo(int tenantID,String sexo) {
		List<EventoDeportivo> eventosDeportivos = null;

		
		try {
			
			eventosDeportivos = em.createQuery("SELECT ed FROM EventoDeportivo ed WHERE ed.tenantId = "+tenantID+" AND ed.sexo = '"+sexo+"'", EventoDeportivo.class).getResultList();
			 
			 return eventosDeportivos;
			
		} catch (Exception e) {
			e.printStackTrace();

		}

		return null;
	}
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



	@Override
	public List<String> listarPaises(int tenantID) {
		List<String> paises = null;
		
		try {
			
			paises = em.createQuery("SELECT pais.pais FROM Usuario u, Pais pais, ComiteOlimpico co WHERE u.id = co.id AND u.tenantID = '"+tenantID+"' AND co.pais = pais.paisID ", String.class).getResultList();
		
			 return paises;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<Deportista> listarDeportistas(int tenantId,
			String nombreDeportista, String deporte, String pais, String sexo) {
		
		List<Deportista> dep = null;
		try {
			
			if(nombreDeportista != null){
				
				dep = em.createNativeQuery("SELECT distinct d.* FROM EventoDeportivo ed, Deportista d, "+
						" deportista_eventodeportivo de, ComiteOlimpico co, Pais p "+
						" WHERE ed.tenant_ID = "+tenantId+
						" AND d.tenantId = "+tenantId+" AND ed.sexo = '"+sexo+
						"' AND ed.nombreDeporte = '"   + deporte   +
						"' AND de.Deportista_deportistaID = d.deportistaID "+
					    "AND de.eventoDep_EventoDepId = ed.EventoDepId AND d.Sexo = '"+sexo+
						"' AND co.id = d.comiteOlimpico_id AND co.pais_paisID = p.paisID "+
						" AND p.pais = '"+ pais+"' AND (LOWER(d.Nombre) LIKE :nombreDeportista "+
						" OR LOWER(d.Apellido) LIKE :nombreDeportista) "+
						";",Deportista.class).setParameter("nombreDeportista", nombreDeportista.toLowerCase()+"%")
						.getResultList();
			}else{
				dep = em.createNativeQuery("SELECT distinct d.* FROM EventoDeportivo ed, Deportista d, "+
						" deportista_eventodeportivo de, ComiteOlimpico co, Pais p "+
						" WHERE ed.tenant_ID = "+tenantId+
						" AND d.tenantId = "+tenantId+" AND ed.sexo = '"+sexo+
						"' AND ed.nombreDeporte = '"   + deporte   +
						"' AND de.Deportista_deportistaID = d.deportistaID "+
					    "AND de.eventoDep_EventoDepId = ed.EventoDepId AND d.Sexo = '"+sexo+
						"' AND co.id = d.comiteOlimpico_id AND co.pais_paisID = p.paisID "+
						" AND p.pais = '"+ pais+"'"  +
						";",Deportista.class).getResultList();
				
				
				
			}
			 
			
			
			return dep;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Deportista> listarDeportistasPorEventoDeportivo(int tenantID, String nombreDeporte) {
		List<Deportista> deportistas = null;
		try{
			deportistas = em.createNativeQuery("SELECT Distinct d.* FROM EventoDeportivo ed, Deportista d, deportista_eventodeportivo de WHERE ed.tenant_ID = '"+tenantID+"' AND d.tenantId = '"+tenantID+"' AND ed.nombreDeporte = '"+nombreDeporte+"' AND de.Deportista_deportistaID = d.deportistaID AND de.eventoDep_EventoDepId = ed.EventoDepId;",Deportista.class).getResultList();	
		}catch(Exception e){
			e.printStackTrace();
			return deportistas;
		}		
		return deportistas;
	}

	@Override
	public Set<Competencia> traerCompetenciasDelDeportista(int deportistId, int tenantID) {
		
		List<Competencia> competencias = null;
		 Set<Competencia> comp = null;
		
		try{
			competencias = (List<Competencia>) em.createNativeQuery("SELECT c.* FROM Competencia c, Deportista d, competencia_deportista cd WHERE c.tenant_ID = '"+tenantID+"' AND d.tenantId = '"+tenantID+"' AND cd.competencia_CompetenciaId = c.CompetenciaId  AND cd.deportistas_deportistaID = d.deportistaID AND d.deportistaID = '"+deportistId+"'",Competencia.class).getResultList();	
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}		
		return  comp = new HashSet<Competencia>(competencias);
		
	}

	

}
