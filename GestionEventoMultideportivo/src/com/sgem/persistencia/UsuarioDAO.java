package com.sgem.persistencia;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.sgem.dominio.Usuario;

@Stateless
public class UsuarioDAO implements IUsuarioDAO {
		
	@PersistenceContext(unitName = "GestionEventoMultideportivo")
	private EntityManager em;

	public boolean guardarUsuario(Usuario usuario) {

		try {
			em.persist(usuario);
			return true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}
	
	
	public Usuario buscarUsuario(String email) {
		Usuario u;
		try{
			return u = em.createQuery("SELECT u FROM Usuario u WHERE u.email = '"+email+"'", Usuario.class).getSingleResult();
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}

	}

}
