package com.sgem.persistencia;

import javax.ejb.Stateless;

import com.sgem.dominio.Usuario;

@Stateless
public class UsuarioDAO implements IUsuarioDAO {
	
	
	@javax.persistence.PersistenceContext(unitName = "GestionEventoMultideportivo")
	private javax.persistence.EntityManager em;

	public boolean guardarUsuario(Usuario usuario) {

		try {
			em.persist(usuario);
			return true;

		} catch (Exception e) {

			e.printStackTrace();

		}

		return false;

	}
	
	
	public Usuario buscarUsuario(String nombre) {
		
		return em.find(Usuario.class, nombre);
	}

}
