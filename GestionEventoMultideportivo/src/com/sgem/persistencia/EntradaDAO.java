package com.sgem.persistencia;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import com.sgem.dominio.Competencia;
import com.sgem.dominio.Deportista;
import com.sgem.dominio.Entrada;
import com.sgem.dominio.UsuarioComun;


@Stateless
public class EntradaDAO implements IEntradaDAO {
		
	@PersistenceContext(unitName = "GestionEventoMultideportivo")
	private EntityManager em;

	public void guardarEntrada(Entrada entrada) {

		try {
			em.persist(entrada);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
	}

	@Override
	public List<Entrada> listarEntradas(int tenantId, int idCompetencia) {
		try{
			
			
			List<Entrada> entradas = em.createNativeQuery("SELECT e.* FROM Entrada e WHERE e.tenant_ID = '"+tenantId+"' AND e.competencia_CompetenciaId='"+idCompetencia+"' AND e.vendida ='"+false+"'", Entrada.class).getResultList();			
			
					

			return entradas;
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
		
		
	}
	
	@Override
	public boolean guardarCompra(UsuarioComun u, Competencia c) {
try {
			
						
			em.merge(u);
			em.merge(c);
			return true;

		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return false;
	}
	
}
