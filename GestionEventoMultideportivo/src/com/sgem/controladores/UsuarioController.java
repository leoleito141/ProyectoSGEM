
package com.sgem.controladores;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.jboss.resteasy.util.Base64;

import com.sgem.datatypes.DataComite;
import com.sgem.datatypes.DataUsuario;
import com.sgem.dominio.ComiteOlimpico;
import com.sgem.dominio.Juez;
import com.sgem.dominio.Organizador;
import com.sgem.dominio.Usuario;
import com.sgem.dominio.UsuarioComun;
import com.sgem.persistencia.IUsuarioDAO;
import com.sgem.seguridad.JWTUtil;
import com.sgem.seguridad.Token;
import com.sgem.utilidades.Correo;

@Stateless
public class UsuarioController implements IUsuarioController {

	public static final String USUARIO_COMUN = "Comun";
	
	@EJB
	private IUsuarioDAO UsuarioDAO;
	
	@Override
	public boolean guardarUsuario(DataUsuario dataUsuario) {
		try {

			Usuario usuario = null;
			String pass = "";
			
			if (dataUsuario.getTipoUsuario().equalsIgnoreCase(USUARIO_COMUN)) {
					usuario = new UsuarioComun();
				
					usuario.setTenantID(dataUsuario.getTenantId());
					usuario.setEmail(dataUsuario.getEmail());
					usuario.setCanalYoutube(dataUsuario.getCanalYoutube());
					usuario.setTwitter(dataUsuario.getTwitter());
					usuario.setFacebook(dataUsuario.getFacebook());
					
					try {
						pass = new String(Base64.decode(dataUsuario.getPassword()));
					} catch (IOException e) {
						e.printStackTrace();
					}
					usuario.setPassword(pass);
					
					return UsuarioDAO.guardarUsuario(usuario);
				
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
			boolean enviado = false;
			boolean guardo = false;
			ComiteOlimpico co = null;
			
			// ANTES DE DAR DE ALTA, FIJARSE EN EL dataComite que viene, si ya existe uno con ese cod y ese pais 
			// en ese tenant. 
					co = new ComiteOlimpico();
				
					co.setEmail(dataComite.getEmail());
					co.setTwitter(dataComite.getTwitter());
					co.setFacebook(dataComite.getFacebook());
					co.setPassword(dataComite.getPassword());
					co.setPais(dataComite.getPais());
					co.setPassword(dataComite.getPassword());
					co.setCodigo(dataComite.getCodigo());
					co.setTenantID(dataComite.getTenantId());
					
					guardo = UsuarioDAO.guardarUsuario(co);
		
					if(guardo){
						// Se debería enviar luego del guardar Usuario.. porque devuelve un booleano, si se pudo guardar enviar correo, sino no.
						enviado = Correo.enviarMensajeConAuth("smtp.gmail.com", 587,"inmogrupo13@gmail.com", co.getEmail(),"inmobiliaria13", "Notificacion de contraseña", "Estimado Comite Olimpico Nacional de "+co.getPais()+":Su contraseña es:"+co.getPassword()+"");
						
					}else{
						// controlar esto..
					}
					return guardo;
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

