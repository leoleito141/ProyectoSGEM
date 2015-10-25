package com.sgem.persistencia;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.sgem.dominio.Imagen;

@Stateless
public class ImagenDAO implements IImagenDAO {
		
	@PersistenceContext(unitName = "GestionEventoMultideportivo")
	private EntityManager em;

	@Override
	public boolean guardarImagen(Imagen i) {
		try {
			em.persist(i);
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	

}
