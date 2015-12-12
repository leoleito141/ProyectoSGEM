package com.sgem.persistencia;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.sgem.dominio.EventoMultideportivo;
import com.sgem.dominio.TenantHandler;

@Stateless
public class EventoMultiDAO implements IEventoMultiDAO {
	
	@PersistenceContext(unitName = "GestionEventoMultideportivo")
	private EntityManager em;

	public boolean guardarEvento(EventoMultideportivo evento) {

		try {
			em.persist(evento);
			return true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}
	
	public boolean guardarConfiguracion(EventoMultideportivo evento) {

		try {
			em.merge(evento);
			return true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}
		
	public EventoMultideportivo buscarEvento(String nombre) {
		try{
			EventoMultideportivo evento = em.createQuery("SELECT u FROM EventoMultideportivo u WHERE u.nombre = '"+nombre+"'", EventoMultideportivo.class).getSingleResult();
			return evento;
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}

	}
	
	public boolean guardarTenant(TenantHandler tenant) {

		try {
			em.persist(tenant);
			return true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}

	@Override
	public EventoMultideportivo obtenerDataTenant(String tenant) {
		List<EventoMultideportivo> evento = new ArrayList<EventoMultideportivo>();
		
		try{
			evento = em.createQuery("SELECT e FROM EventoMultideportivo e WHERE e.nombre = '"+tenant+"'", EventoMultideportivo.class).getResultList();
		}catch(Exception e){
			e.printStackTrace();			
		}
		
		return evento.get(evento.size()-1);
	}

	@Override
	public int traeridEventoMulti(int tenantID) {
		int idEventoMulti = 0;
		
		try{
			idEventoMulti = (Integer) em.createQuery("SELECT e.EventoId FROM EventoMultideportivo e WHERE e.tenantHandler = '"+tenantID+"'").getSingleResult();
		}catch(Exception e){
			e.printStackTrace();			
		}
		return idEventoMulti;
	}

	@Override
	public EventoMultideportivo traerEventoMulti(int tenantId) {
		try{
			EventoMultideportivo evento = em.createQuery("SELECT u FROM EventoMultideportivo u WHERE u.tenantHandler = '"+tenantId+"'", EventoMultideportivo.class).getSingleResult();
			return evento;
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public int obtenerMaximoTenant() {
		int maxTenant = 0;
		
		try{
			maxTenant = (int) em.createQuery("SELECT max(th.tenantId) FROM TenantHandler th").getSingleResult();
		}catch(Exception e){
			e.printStackTrace();
		}
		return maxTenant;
	}

	@Override
	public List<EventoMultideportivo> listarEventosMulti() {
		try{
			
			return em.createQuery("SELECT e FROM EventoMultideportivo e ", EventoMultideportivo.class).getResultList();
			
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
		
}
