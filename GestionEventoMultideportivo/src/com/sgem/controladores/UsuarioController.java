
package com.sgem.controladores;

import java.io.IOException;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.jboss.resteasy.util.Base64;

import com.sgem.datatypes.DataComite;
import com.sgem.datatypes.DataUsuario;
import com.sgem.dominio.ComiteOlimpico;
import com.sgem.dominio.Juez;
import com.sgem.dominio.Organizador;
import com.sgem.dominio.Usuario;
import com.sgem.persistencia.IUsuarioDAO;
import com.sgem.seguridad.JWTUtil;
import com.sgem.seguridad.Token;
import com.sgem.utilidades.Correo;

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
				//	usuario.setNombre(dataUsuario.getNombre());
				//	usuario.setApellido(dataUsuario.getApellido());
				//	usuario.setEdad(dataUsuario.getEdad());
					usuario.setEmail(dataUsuario.getEmail());
					usuario.setCanalYoutube(dataUsuario.getCanalYoutube());
					usuario.setTwitter(dataUsuario.getTwitter());
					usuario.setFacebook(dataUsuario.getFacebook());
			//		usuario.setCedula(dataUsuario.getCedula());
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
	
	
	public boolean guardarComite(DataComite dataComite) {
		try {
			boolean creado = false;
			ComiteOlimpico co = null;
			
					co = new ComiteOlimpico();
				
					co.setEmail(dataComite.getEmail());
					co.setTwitter(dataComite.getTwitter());
					co.setFacebook(dataComite.getFacebook());
					co.setPassword(dataComite.getPassword());
					co.setPais(dataComite.getPais());
					co.setPassword(dataComite.getPassword());
					co.setCodigo(dataComite.getCodigo());
					co.setTenantID(dataComite.getTenantId());
					
					creado = Correo.enviarMensajeConAuth("smtp.gmail.com", 587,"inmogrupo13@gmail.com", co.getEmail(),"inmobiliaria13", "Notificacion de contraseña", "Estimado Comite Olimpico Nacional de "+co.getPais()+":Su contraseña es:"+co.getPassword()+"");
					
					return UsuarioDAO.guardarUsuario(co);
		
			}
		 catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	

	public Token loginUsuario(DataUsuario dataUsuario) {	// String url){
		
		Token jwt;
		String pass = null;
		
		Usuario u =	buscarUsuario(dataUsuario.getEmail());
		
		try {
			pass = new String(Base64.decode(dataUsuario.getPassword()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(u != null || (u.getPassword().equalsIgnoreCase(pass))){// genero json web token.
	
			// tenantId = this.buscarTenantId(url); BUSCAR TENANTID, 	
			//	jwt.setTenantId(tenantId);
			jwt = JWTUtil.generarToken(u);
			
		}else { 
			return null; // deso vemos como manejar esto.
		}
			
		return jwt;
	}
	
	public Usuario buscarUsuario(String email) {
		
		try{
			return UsuarioDAO.buscarUsuario(email);
		}catch(Exception e){
			e.printStackTrace();
			
		}
		return null;
	}


}

