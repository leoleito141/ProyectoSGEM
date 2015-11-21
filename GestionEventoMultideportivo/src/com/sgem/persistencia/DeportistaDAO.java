package com.sgem.persistencia;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.sgem.dominio.Deportista;

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
			List<Deportista> deportistas = em.createNativeQuery("SELECT d.* FROM EventoDeportivo ed, Deportista d, deportista_eventodeportivo de WHERE ed.tenant_ID = '"+tenantID+"' AND d.tenantId = '"+tenantID+"' AND ed.sexo = '"+sexo+"' AND ed.nombreDeporte = '"+nombreDeporte+"' AND  ed.disciplina = '"+nombreDisciplina+"' AND de.Deportista_deportistaID = d.deportistaID AND de.eventoDep_EventoDepId = ed.EventoDepId AND d.Sexo = '"+sexo+"';",Deportista.class).getResultList();;
			

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

	

}
