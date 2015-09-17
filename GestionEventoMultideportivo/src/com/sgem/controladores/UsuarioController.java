
package com.sgem.controladores;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.sgem.datatypes.DataUsuario;
import com.sgem.dominio.Admin;
import com.sgem.dominio.Juez;
import com.sgem.dominio.Organizador;
import com.sgem.dominio.Usuario;
import com.sgem.persistencia.IUsuarioDAO;
import com.sgem.seguridad.JWTUtil;
import com.sgem.seguridad.Token;

@Stateless
public class UsuarioController implements IUsuarioController {

	public static final String ROL_ADMIN = "Administrador"; // faltan mas roles...
	
	@EJB
	private IUsuarioDAO UsuarioDAO;
	
	@Override
	public boolean guardarUsuario(DataUsuario dataUsuario) {
		try {

			Usuario usuario = null;
		

			if (dataUsuario.getRol().equalsIgnoreCase(ROL_ADMIN)) {
				
				// Cambiar la condicion a alguna parte del data para ver el tipo de usuario que estoy creando
				usuario = (dataUsuario.getEmail()=="")? (new Organizador()):( new Juez());
				
				if(usuario != null){
					
					usuario = new Organizador();
					usuario.setNombre(dataUsuario.getNombre());
					usuario.setApellido(dataUsuario.getApellido());
					usuario.setEdad(dataUsuario.getEdad());
					usuario.setEmail(dataUsuario.getEmail());
					usuario.setCanalYoutube(dataUsuario.getCanalYoutube());
					usuario.setTwitter(dataUsuario.getTwitter());
					usuario.setFacebook(dataUsuario.getFacebook());
					usuario.setCedula(dataUsuario.getCedula());
					usuario.setPassword(dataUsuario.getPassword());
					
					return UsuarioDAO.guardarUsuario(usuario);
					
				}
				
			}else{

				return false;
			}

			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}
	
	public Usuario buscarUsuario(String email) {
		
		try{
			return UsuarioDAO.buscarUsuario(email);
		}catch(Exception e){
			e.printStackTrace();
			
		}
		return null;
	}

	public Token loginUsuario(DataUsuario dataUsuario) {
		
		Token jwt;
		Usuario u =	this.buscarUsuario(dataUsuario.getEmail());
			
		if (u == null || !(u.getPassword().equalsIgnoreCase(dataUsuario.getPassword()))) {		
			return null; // deso vemos como manejar esto.
			
		} else { // genero json web token.
		
			jwt = JWTUtil.generarToken(u);
		
		}
		
		return jwt;
	}


}

