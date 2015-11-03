package com.sgem.persistencia;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import com.sgem.dominio.ComiteOlimpico;
import com.sgem.dominio.Juez;
import com.sgem.dominio.Organizador;
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
	public Usuario buscarUsuario(int tenantId, String email, String clase) {
		List<Usuario> usuarios = null;
		
		try{
			usuarios = em.createQuery("SELECT u FROM Usuario u, "+clase+" cu WHERE u.id = cu.id AND u.tenantID = '"+tenantId+"' AND u.email = '"+email+"'", Usuario.class).getResultList();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
		return (usuarios.isEmpty() ? null : usuarios.get(0));
	}

	@Override
	public Usuario buscarUsuario(int tenantId, String email) {
		List<Usuario> u = new ArrayList<Usuario>();
		
		try{
			u = em.createQuery("SELECT u FROM Usuario u WHERE u.email = '"+email+"' AND u.tenantID != 0 AND u.tenantID ="+tenantId, Usuario.class).getResultList();			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return (u.isEmpty() ? null : u.get(0));
	}
	
	@Override
	public Usuario buscarAdmin(String email,String clase) {
		List<Usuario> usuarios = null;
		
		try{
			usuarios = em.createQuery("SELECT u FROM Usuario u, "+clase+" cu WHERE u.id = cu.id AND u.tenantID = 0 AND u.email = '"+email+"'", Usuario.class).getResultList();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
		return (usuarios.isEmpty() ? null : usuarios.get(0));
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
	
	public boolean guardarOrganizador(Organizador organizador) {

		try {
			em.merge(organizador);
			return true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}

	@Override
	public boolean existeJuez(Integer tenantId, String email) {
		List<Usuario> usuarios = null;
		try{
			usuarios = em.createQuery("SELECT u FROM Usuario u, Juez j WHERE u.id = j.id AND u.tenantID = '"+tenantId+"' AND u.email = '"+email+"'", Usuario.class).getResultList();
			
			
		if( usuarios.isEmpty()){
			
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
	public List<Juez> listarJueces(Integer tenantId) {
		
		
	List<Juez> jueces = null;
		
		try {
			
			jueces = em.createQuery("SELECT j FROM Usuario u, Juez j WHERE u.id = j.id AND u.tenantID = '"+tenantId+"'", Juez.class).getResultList();;
			 
			 return jueces;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int obtenerMaximoComite() {
		int maxComite = 0;		
		try{
			maxComite = ((BigInteger) em.createNativeQuery("SELECT GEN_VAL from id_gen").getSingleResult()).intValue();
		}catch(Exception e){
			e.printStackTrace();
		}
		return maxComite;
	}

	

}
