package com.sgem.persistencia;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.sgem.datatypes.DataDeportista;
import com.sgem.dominio.Admin;
import com.sgem.dominio.Deportista;
import com.sgem.dominio.Organizador;
import com.sgem.dominio.Usuario;

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

	

}
