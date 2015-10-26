
package com.sgem.controladores;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;

import org.apache.commons.io.IOUtils;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import org.jboss.resteasy.util.Base64;

import com.sgem.datatypes.DataComite;
import com.sgem.datatypes.DataNovedad;
import com.sgem.datatypes.DataUsuario;
import com.sgem.dominio.Admin;
import com.sgem.dominio.ComiteOlimpico;
import com.sgem.dominio.Imagen;
import com.sgem.dominio.Juez;
import com.sgem.dominio.Novedad;
import com.sgem.dominio.Organizador;
import com.sgem.dominio.Usuario;
import com.sgem.dominio.UsuarioComun;
import com.sgem.persistencia.IImagenDAO;
import com.sgem.persistencia.INovedadDAO;
import com.sgem.persistencia.IUsuarioDAO;
import com.sgem.seguridad.excepciones.AplicacionException;
import com.sgem.seguridad.excepciones.UsuarioNoEncontradoException;
import com.sgem.seguridad.excepciones.UsuarioYaExisteException;
import com.sgem.seguridad.jwt.JWTUtil;
import com.sgem.seguridad.jwt.Token;
import com.sgem.utilidades.Correo;
import com.sgem.utilidades.ImagenUtil;

@Stateless
public class UsuarioController implements IUsuarioController {
 
	public static final String USUARIO_ADMINISTRADOR = Admin.class.getSimpleName();
	public static final String USUARIO_COMUN =  UsuarioComun.class.getSimpleName();
	public static final String USUARIO_COMITE =  ComiteOlimpico.class.getSimpleName();
	public static final String USUARIO_ORGANIZADOR =  Organizador.class.getSimpleName();
	public static final String USUARIO_JUEZ =  Juez.class.getSimpleName();
	
	@EJB
	private IUsuarioDAO UsuarioDAO;
	
	@EJB
	private INovedadDAO NovedadDAO;
	
	@EJB
	private IImagenDAO ImagenDAO;
	
	@Override
	public boolean guardarUsuario(DataUsuario dataUsuario) throws UsuarioYaExisteException, AplicacionException {
		Usuario usuario = null;
		String pass = "";
		boolean guardo = false;

		usuario = new UsuarioComun();
		if(UsuarioDAO.buscarUsuario(dataUsuario.getTenantId(),dataUsuario.getEmail(),USUARIO_COMUN) == null ){
			
			usuario.setTenantID(dataUsuario.getTenantId());			
			usuario.setEmail(dataUsuario.getEmail());

			try {
				pass = new String(Base64.decode(dataUsuario.getPassword()));
			} catch (IOException e) {
				throw new AplicacionException("Error al guardar usuario");
			}
			usuario.setPassword(pass);

			guardo = UsuarioDAO.guardarUsuario(usuario);
		}else{
			throw new UsuarioYaExisteException("El usuario con email: "+dataUsuario.getEmail()+ " ya existe.");
		}

		return guardo;
	}
	
	@Override
	public boolean guardarComite(DataComite dataComite) {
		try {
			boolean enviado = false;
			boolean guardo = false;
			ComiteOlimpico co = null;
			
			boolean existeCodigoCO = UsuarioDAO.existeCodigoCO(dataComite.getTenantId(),dataComite.getCodigo());
			System.out.println(existeCodigoCO);
			boolean existePais = UsuarioDAO.existePais(dataComite.getTenantId(),dataComite.getPais());
			
			
			// ANTES DE DAR DE ALTA, FIJARSE EN EL dataComite que viene, si ya existe uno con ese cod y ese pais 
			// en ese tenant. 
			
			if((existeCodigoCO == false)&&(existePais==false)){
			
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
			}else{ // ya existe CO con es codigo y pais no se guarda
				
				return false;
			}		
					
					
			}
		 catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public Token loginAdmin(DataUsuario dataUsuario) throws UsuarioNoEncontradoException, AplicacionException {	
		Token jwt = null;
		String pass = "";
		
		Usuario u =	UsuarioDAO.buscarAdmin(dataUsuario.getEmail(),USUARIO_ADMINISTRADOR);				
	
		try {
			pass = new String(Base64.decode(dataUsuario.getPassword()));
		} catch (IOException e) {
			throw new AplicacionException("Error al obtener contrasenia del usuario");
		}		
		
		if( u != null && (u.getPassword().equalsIgnoreCase(pass)) ){// genero json web token.
			DataUsuario du = convertir(u);
			du.setTipoUsuario(USUARIO_ADMINISTRADOR);
			jwt = JWTUtil.generarToken(du);
		}else{
			throw new UsuarioNoEncontradoException("No se encuentra usuario con dichas credenciales");
		}
						
		return jwt;
	}

	@Override
	public List<ComiteOlimpico> buscarComiteporPais(String pais, int tenantID) {
		
		try{
			return UsuarioDAO.buscarComiteporPais(pais, tenantID);
		}catch(Exception e){
			e.printStackTrace();
			
		}
		return null;
	}


	@Override
	public Token loginUsuario(DataUsuario dataUsuario) throws UsuarioNoEncontradoException, AplicacionException {
		Token jwt = null;
		String pass = "";
		
		Usuario u =	UsuarioDAO.buscarUsuario(dataUsuario.getTenantId(), dataUsuario.getEmail());				
	
		try {
			pass = new String(Base64.decode(dataUsuario.getPassword()));
		} catch (IOException e) {
			throw new AplicacionException("Error al obtener contrasenia del usuario");
		}		
		
		if( u != null && (u.getPassword().equalsIgnoreCase(pass)) ){// genero json web token.
			
			String tipoUsuario = u instanceof UsuarioComun ? USUARIO_COMUN : u instanceof ComiteOlimpico ? USUARIO_COMITE : u instanceof Organizador ? USUARIO_ORGANIZADOR : USUARIO_JUEZ;
			DataUsuario du = convertir(u);
			du.setTipoUsuario(tipoUsuario);
			jwt = JWTUtil.generarToken(du);
		}else{
			throw new UsuarioNoEncontradoException("No se encuentra usuario con dichas credenciales");
		}
						
		return jwt;
	}

	@Override
	public boolean guardarNovedad(DataNovedad dataNovedad) throws UsuarioNoEncontradoException, AplicacionException {
		
		boolean guardo = false;
		ComiteOlimpico comite = (ComiteOlimpico)UsuarioDAO.buscarUsuario(dataNovedad.getTenantId(), dataNovedad.getEmailComiteOlimpico(), USUARIO_COMITE);		
		
		if(comite != null){
		
			Imagen i = new Imagen(dataNovedad.getImagen().getMime(), dataNovedad.getImagen().getRuta(), dataNovedad.getImagen().getTenantId());
			
			if(ImagenDAO.guardarImagen(i)){			
		
				Novedad n = new Novedad(dataNovedad.getTitulo(), dataNovedad.getDescripcion(), dataNovedad.getColumna(), comite,i);
				guardo = NovedadDAO.guardarNovedad(n);

				if(!guardo){
					try {
						ImagenUtil.borrarImagen(dataNovedad.getImagen().getRuta());
					} catch (IOException e) {
						throw new AplicacionException("Error al guardar novedad. Tampoco se pudo borrar la imagen para dicha novedad.");
					}					
					throw new AplicacionException("Error al guardar novedad");
				}
				
			}else{
				try {
					ImagenUtil.borrarImagen(dataNovedad.getImagen().getRuta());
				} catch (IOException e) {
					throw new AplicacionException("Error al guardar novedad. No se pudo borrar la imagen para dicha novedad.");
				}				
				throw new AplicacionException("Error al guardar imagen");
			}
			
		}else{
			try {
				ImagenUtil.borrarImagen(dataNovedad.getImagen().getRuta());
			} catch (IOException e) {
				throw new AplicacionException("Error al guardar novedad. No se pudo borrar la imagen para dicha novedad.");
			}			
			throw new UsuarioNoEncontradoException("No se encuentra el comite olimpico '"+dataNovedad.getEmailComiteOlimpico()+"'");			
		}
		
		return guardo;
	}

	private DataUsuario convertir(Usuario u){
		DataUsuario du = new DataUsuario();
		
		du.setCanalYoutube(u.getCanalYoutube());
		du.setEmail(u.getEmail());
		du.setFacebook(u.getFacebook());		
		du.setTenantId(u.getTenantID());
		du.setTwitter(u.getTwitter());
		
		return du;
	}

	@Override
	public Imagen subirImagen(MultipartFormDataInput input) throws AplicacionException{
		
		String fileName = "";
		String tenantId = "";
		File f = null;
		
		try {
			Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
			List<InputPart> inputParts = uploadForm.get("file");
		
			tenantId = uploadForm.get("tenantId").get(0).getBodyAsString();		
		
			for (InputPart inputPart : inputParts) {		
	
				MultivaluedMap<String, String> header = inputPart.getHeaders();
				
				// Ojo con esto, puede que subam 2 noticias y la foto si se llama igual va a hacer cualquiera, quizas renombrarla..
				fileName = ImagenUtil.getNovedadFilePath(header,tenantId);
	
				InputStream inputStream;
			
				inputStream = inputPart.getBody(InputStream.class,null);				
	
				byte [] bytes = IOUtils.toByteArray(inputStream);
				
				String dir = ImagenUtil.getNovedadDirectoryName(tenantId);
				
				f = ImagenUtil.writeFile(bytes,fileName,dir);			
			}
		} catch (IOException e) {
			throw new AplicacionException("Error al subir la imagen");
		}			
		
		return (new Imagen(ImagenUtil.getMimeType(f), fileName,Integer.parseInt(tenantId)));
		
	}	

}

