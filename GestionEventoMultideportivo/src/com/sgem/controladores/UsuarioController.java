package com.sgem.controladores;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.sgem.datatypes.DataUsuario;
import com.sgem.dominio.Admin;
import com.sgem.dominio.Usuario;
import com.sgem.persistencia.IUsuarioDAO;

@Stateless
public class UsuarioController implements IUsuarioController {

	private static final String ROL_ADMIN = "Administrador";
	@EJB
	private IUsuarioDAO UsuarioDAO;
	
	@Override
	public boolean guardarUsuario(DataUsuario dataUsuario) {
		try {

			Usuario usuario = null;

			if (dataUsuario.getRol().equalsIgnoreCase(ROL_ADMIN)) {
				usuario = new Admin();
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
			}else{
				return false;
			}

			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}
	
	public Usuario buscarUsuario(long id) {
		try{
			return UsuarioDAO.buscarUsuario(id);
			}catch(Exception e){
				e.printStackTrace();
				
			}
			return null;
	}


}
