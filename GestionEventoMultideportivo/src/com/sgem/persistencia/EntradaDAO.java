package com.sgem.persistencia;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import com.sgem.dominio.Entrada;


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
}
