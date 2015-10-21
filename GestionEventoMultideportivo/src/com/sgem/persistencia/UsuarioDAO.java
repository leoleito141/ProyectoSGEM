package com.sgem.persistencia;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import com.sgem.dominio.ComiteOlimpico;
import com.sgem.dominio.Usuario;

@Stateless
public class UsuarioDAO implements IUsuarioDAO {
		
	@PersistenceContext(unitName = "GestionEventoMultideportivo")
	private EntityManager em;

	public boolean guardarUsuario(Usuario usuario) {
		boolean guardo = false;
		
		try {
			em.persist(usuario);
			guardo = true;
		} catch (Exception e) {
			e.printStackTrace();
			return guardo; 
		}
		return guardo;

	}
	
	@Override
	public Usuario buscarAdmin(String email) {
		List<Usuario> u = new ArrayList<Usuario>();

		try{
			u = em.createQuery("SELECT u FROM Usuario u WHERE u.email = '"+email+"' AND u.tenantID = 0", Usuario.class).getResultList();			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return (u.size() == 0 ? null : u.get(0));
	}

	@Override
	public boolean existeCodigoCO(int tenantId, String codigo) {
		
		List<Usuario> usuarios = null;
		try{
			usuarios = em.createQuery("SELECT u FROM Usuario u, ComiteOlimpico co WHERE u.id = co.id AND u.tenantID = '"+tenantId+"' AND co.codigo = '"+codigo+"'", Usuario.class).getResultList();
			
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
			usuarios = em.createQuery("SELECT u FROM Usuario u, ComiteOlimpico co WHERE u.id = co.id AND u.tenantID = '"+tenantId+"' AND co.pais = '"+pais+"'", Usuario.class).getResultList();
			
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

	@Override
	public Usuario buscarUsuario(String email) {
		List<Usuario> u = new ArrayList<Usuario>();

		try{
			u = em.createQuery("SELECT u FROM Usuario u WHERE u.email = '"+email+"' AND u.tenantID != 0", Usuario.class).getResultList();			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return (u.size() == 0 ? null : u.get(0));
	}

	@Override
	public boolean existeEmail(int tenantId, String email) {
		List<Usuario> usuarios = null;
		
		try{
			usuarios = em.createQuery("SELECT u FROM Usuario u, UsuarioComun uc WHERE u.id = uc.id AND u.tenantID = '"+tenantId+"' AND u.email = '"+email+"'", Usuario.class).getResultList();
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
		return (usuarios.isEmpty() ? false : true);
	}
	

}
