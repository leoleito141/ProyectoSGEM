package com.sgem.persistencia;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.sgem.datatypes.DataHistorialLogin;
import com.sgem.dominio.HistorialLogin;

@Stateless
public class HistorialLoginDAO implements IHistorialLoginDAO {
		
	@PersistenceContext(unitName = "GestionEventoMultideportivo")
	private EntityManager em;

	@Override
	public boolean guardarHistorial(HistorialLogin hl) {
		try {
			em.persist(hl);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public List<Object> recuperarHistorial(int tenantId) {
		List<Object> historial = new ArrayList<Object>();
		try {
			
			String sql ="SELECT month(fecha) AS mes, year(fecha) AS anio,";
			sql += "COUNT(uc.id) AS cantComunes,";
			sql += "COUNT(co.id) AS cantComites,";
			sql += "COUNT(j.id) AS cantJueces ";
			sql +="FROM historiallogin hl ";
			sql += "LEFT OUTER JOIN usuariocomun uc ON uc.id = hl.usuario_id ";
			sql += "LEFT OUTER JOIN comiteolimpico co ON co.id = hl.usuario_id ";
			sql += "LEFT OUTER JOIN juez j ON j.id = hl.usuario_id ";
			sql += "WHERE hl.tenantId = "+tenantId;
			sql += " GROUP BY month(hl.fecha)";
			
			historial = em.createNativeQuery(sql).getResultList();
			
		} catch (Exception e) {
			e.printStackTrace();
			return historial;
		}
		
		return historial;
	}

	@Override
	public Integer obtenerCantidadRegistrados(Integer tenantId) {
		Long cant = 0L;		
		try {		
			cant = em.createQuery("SELECT COUNT(u.id) AS cant FROM UsuarioComun u",Long.class).getSingleResult();			
		} catch (Exception e) {
			return cant.intValue();
		}
		return cant.intValue();
	}

}
