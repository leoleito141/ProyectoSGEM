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
			List<Deportista> deportistas = em.createNativeQuery("SELECT d.Nombre, d.Apellido FROM EventoDeportivo ed, Deportista d, deportista_eventodeportivo de WHERE ed.tenant_ID = '1' AND d.tenantId = '1' AND ed.sexo = 'Masculino' AND ed.nombreDeporte = 'Natacion' AND  ed.disciplina = '200M Mariposa' AND de.Deportista_deportistaID = d.deportistaID AND de.eventoDep_EventoDepId = ed.EventoDepIdAND d.Sexo = 'Masculino' ;",Deportista.class).getResultList();;
			

			return deportistas;
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
		
	}

	

}
