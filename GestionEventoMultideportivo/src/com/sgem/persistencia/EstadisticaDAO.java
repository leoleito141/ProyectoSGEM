package com.sgem.persistencia;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.sgem.dominio.Estadistica;


@Stateless
public class EstadisticaDAO implements IEstadisticaDAO {
		
	@PersistenceContext(unitName = "GestionEventoMultideportivo")
	private EntityManager em;

	@Override
	public boolean guardarEstadistica(Estadistica e) {
		try {
			em.persist(e);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}		
	}

	@Override
	public Estadistica buscarEstadistica(int tenantID, int idEstadistica) {		
		try {		
			return em.find(Estadistica.class, idEstadistica);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}		
	}
	
	@Override
	public boolean modificarEstadistica(Estadistica e) {
		try {
			em.merge(e);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}		
	}

	@Override
	public Estadistica buscarEstadisticaPorPais(int tenantID, int competenciaID, int comiteID) {
		Estadistica estadistica = null;
		try{
			String sql = "SELECT e. *" + 
					     "FROM competencia c, competencia_deportista cd, deportista d, resultado r, estadistica e " +
					 	 "WHERE c.CompetenciaId = "+competenciaID+" AND r.competencia_CompetenciaId = c.CompetenciaId " +
						 "AND d.comiteOlimpico_id = "+comiteID+" AND cd.deportistas_deportistaID = d.deportistaID AND " +
						 "c.CompetenciaId = cd.competencia_CompetenciaId AND e.deportista_deportistaID = d.deportistaID " +
						 "AND e.resultado_resultadoId = r.resultadoId AND e.tenant_ID = "+tenantID+" AND c.tenant_ID = "+tenantID + " " + 
						 "AND d.TenantID = "+tenantID+" " +
						 "AND r.tenant_ID = "+tenantID+" " +
						 "LIMIT 1";
			
			estadistica = (Estadistica) em.createNativeQuery(sql,Estadistica.class).getSingleResult();
		}catch(Exception e){
			e.printStackTrace();
			return estadistica;
		}
		return estadistica;
	}
}
