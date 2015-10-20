package com.sgem.persistencia;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import com.sgem.dominio.Admin;
import com.sgem.dominio.ComiteOlimpico;
import com.sgem.dominio.Organizador;
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
		Usuario u = null;
		try{
			u = em.createQuery("SELECT u FROM Usuario u WHERE u.email = '"+email+"'", Usuario.class).getSingleResult();
			
		}catch(NoResultException e){
			e.printStackTrace();
			return null;
		}
		return u;
	}

	@Override
	public boolean existeCodigoCO(int tenantId, String codigo) {
		
		List<Usuario> usuarios = null;
		try{
			usuarios = em.createQuery("SELECT u FROM Usuario u, ComiteOlimpico co WHERE u.id = co.id AND u.tenantID = '"+tenantId+"' AND co.codigo = '"+codigo+"'", Usuario.class).getResultList();;
			
			System.out.println(usuarios);
			
		if( usuarios.isEmpty()){
			System.out.println("Fue null la consulta");
			return false;
		}else{
			
			return true;
		}	
			
		}catch(NoResultException e){
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public boolean existePais(int tenantId, String pais) {
		List<Usuario> usuarios = null;
		try{
			usuarios = em.createQuery("SELECT u FROM Usuario u, ComiteOlimpico co WHERE u.id = co.id AND u.tenantID = '"+tenantId+"' AND co.pais = '"+pais+"'", Usuario.class).getResultList();;
			
			System.out.println(usuarios);
			
		if( usuarios.isEmpty()){
			System.out.println("Fue null la consulta");
			return false;
		}else{
			
			return true;
		}	
			
		}catch(NoResultException e){
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<ComiteOlimpico> buscarComiteporPais(String pais, int TenantID) {
		List<ComiteOlimpico> co = null;
		try{
			co = em.createQuery("SELECT co FROM Usuario u, ComiteOlimpico co WHERE u.id = co.id AND u.tenantID = '"+TenantID+"' AND co.pais = '"+pais+"'", ComiteOlimpico.class).getResultList();;
			
			
			
			
		}catch(NoResultException e){
			e.printStackTrace();
			return null;
		}
		return co;
	}


}
