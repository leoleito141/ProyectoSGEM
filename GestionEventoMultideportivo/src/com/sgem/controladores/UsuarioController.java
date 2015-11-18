
package com.sgem.controladores;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.mail.MessagingException;
import javax.ws.rs.core.MultivaluedMap;

import org.apache.commons.io.IOUtils;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import org.jboss.resteasy.util.Base64;

import com.sgem.datatypes.DataComite;
import com.sgem.datatypes.DataHistorialLogin;
import com.sgem.datatypes.DataImagen;
import com.sgem.datatypes.DataJuez;
import com.sgem.datatypes.DataNovedad;
import com.sgem.datatypes.DataPais;
import com.sgem.datatypes.DataUsuario;
import com.sgem.dominio.Admin;
import com.sgem.dominio.ComiteOlimpico;
import com.sgem.dominio.HistorialLogin;
import com.sgem.dominio.Imagen;
import com.sgem.dominio.Juez;
import com.sgem.dominio.Novedad;
import com.sgem.dominio.Organizador;
import com.sgem.dominio.Pais;
import com.sgem.dominio.Usuario;
import com.sgem.dominio.UsuarioComun;
import com.sgem.enums.Tipo;
import com.sgem.persistencia.IHistorialLoginDAO;
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
	
	@EJB
	private IHistorialLoginDAO HistorialLoginDAO;
	
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
	public boolean guardarComite(DataComite dataComite) throws AplicacionException, UsuarioYaExisteException {
		
		boolean guardo = false;
		ComiteOlimpico co = null;		
		
		boolean existeCodigoCO = UsuarioDAO.existeCodigoCO(dataComite.getTenantId(),dataComite.getCodigo());
		boolean existePais = UsuarioDAO.existePais(dataComite.getPais().getPaisID(),dataComite.getTenantId());
		
		if((existeCodigoCO == false)&&(existePais==false)){
			
			
			Imagen logo = new Imagen(dataComite.getLogo().getMime(), dataComite.getLogo().getRuta(), dataComite.getLogo().getTenantId());
					
			if(ImagenDAO.guardarImagen(logo)){		
				Pais p = new Pais();
				p.setPais(dataComite.getPais().getPais());
				p.setCiudad(dataComite.getPais().getCiudad());
				co = new ComiteOlimpico();
			
				co.setEmail(dataComite.getEmail());
				co.setTwitter(dataComite.getTwitter());
				co.setFacebook(dataComite.getFacebook());
				co.setPassword(dataComite.getPassword());
				co.setPais(p);
				co.setPassword(dataComite.getPassword());
				co.setCodigo(dataComite.getCodigo());
				co.setTenantID(dataComite.getTenantId());
				co.setPaypal(dataComite.getPaypal());
				co.setLogo(logo);
				
				guardo = UsuarioDAO.guardarUsuario(co);
	
				if(guardo){
					try {
						if(Correo.enviarMensajeConAuth("smtp.gmail.com", 587,"inmogrupo13@gmail.com", co.getEmail(),"inmobiliaria13", "Notificacion de contrase�a", "Estimado Comite Olimpico Nacional de "+co.getPais()+":Su contrase�a es:"+co.getPassword()+"")){
							System.out.println("Correo enviado con exito!");
						}else{
							System.out.println("Error - Correo no enviado");
						}
					} catch (MessagingException e) {
						e.printStackTrace();
					}						
				}else{
					try {
						ImagenUtil.borrarImagen(dataComite.getLogo().getRuta());
					} catch (IOException e) {
						throw new AplicacionException("Error al guardar el comite olimpico. Tampoco se pudo borrar la imagen para dicho comite.");
					}					
					throw new AplicacionException("Error al guardar el comite olimpico.");
				}
				
			}else{
				try {
					ImagenUtil.borrarImagen(dataComite.getLogo().getRuta());
				} catch (IOException e) {
					throw new AplicacionException("Error al guardar Comite Olimpico. No se pudo borrar la imagen para dicho comite.");
				}	
				throw new AplicacionException("Error al guardar imagen");
			}
			
		}else{			
			try {
				ImagenUtil.borrarImagen(dataComite.getLogo().getRuta());
			} catch (IOException e) {
				throw new AplicacionException("Error al guardar Comite Olimpico. No se pudo borrar la imagen para dicho comite.");
			}				
			throw new UsuarioYaExisteException("El Comite Olimpico con codigo "+dataComite.getCodigo() +" ya existe.");
		}						
		
		return guardo;
	}
	
	@Override
	public Imagen subirImagenComite(MultipartFormDataInput input) throws AplicacionException {
		String fileName = "";
		String tenantId = "";
		File f = null;
		
		try {
			Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
			List<InputPart> inputParts = uploadForm.get("file");
		
			tenantId = uploadForm.get("tenantId").get(0).getBodyAsString();		
		
			for (InputPart inputPart : inputParts) {		
	
				MultivaluedMap<String, String> header = inputPart.getHeaders();
				
				String proxComite = String.valueOf(UsuarioDAO.obtenerMaximoComite());

				fileName = ImagenUtil.getComiteFilePath(header,tenantId,proxComite);
	
				InputStream inputStream;
			
				inputStream = inputPart.getBody(InputStream.class,null);				
	
				byte [] bytes = IOUtils.toByteArray(inputStream);
					
				String dir = ImagenUtil.getComiteDirectoryName(tenantId,proxComite);
				
				f = ImagenUtil.writeFile(bytes,fileName,dir);			
			}
		} catch (IOException e) {
			throw new AplicacionException("Error al subir la imagen");
		}catch(Exception e){
			throw new AplicacionException("Error al subir la imagen");
		}		
		
		return (new Imagen(ImagenUtil.getMimeType(f), fileName,Integer.parseInt(tenantId)));
		
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
	public Token loginAndroid(DataUsuario dataUsuario) throws UsuarioNoEncontradoException, AplicacionException {
		Token jwt = null;
		String pass = "";
		
		UsuarioComun u =	(UsuarioComun) UsuarioDAO.buscarUsuario(dataUsuario.getTenantId(), dataUsuario.getEmail(), UsuarioComun.class.getSimpleName());				
	
		try {
			pass = new String(Base64.decode(dataUsuario.getPassword()));
		} catch (IOException e) {
			throw new AplicacionException("Error al obtener contrasenia del usuario");
		}		
		
		if( u != null && (u.getPassword().equalsIgnoreCase(pass)) ){// genero json web token.
			DataUsuario du = convertir(u);
			du.setTipoUsuario(USUARIO_COMUN);
			jwt = JWTUtil.generarToken(du);		
		}else{
			throw new UsuarioNoEncontradoException("No se encuentra usuario con dichas credenciales");
		}

		return jwt;
	}

	@Override
	public Token loginIonic(DataJuez dataJuez) throws UsuarioNoEncontradoException, AplicacionException {
		Token jwt = null;
		String pass = "";
		
		Juez j = (Juez) UsuarioDAO.buscarUsuario(dataJuez.getTenantId(), dataJuez.getEmail(), Juez.class.getSimpleName());				
	
		try {
			pass = new String(Base64.decode(dataJuez.getPassword()));
		} catch (IOException e) {
			throw new AplicacionException("Error al obtener contrasenia del usuario");
		}		
		
		if( j != null && (j.getPassword().equalsIgnoreCase(pass)) ){// genero json web token.
			DataJuez dj = new DataJuez(j.getTenantID(),((Juez)j).getNombre(),((Juez)j).getApellido(),"",j.getEmail(),USUARIO_JUEZ,j.getId().intValue());;
			dj.setTipoUsuario(USUARIO_JUEZ);
			jwt = JWTUtil.generarToken(dj);		
		}else{
			throw new UsuarioNoEncontradoException("No se encuentra usuario con dichas credenciales");
		}

		return jwt;
	}
	
	@Override
	public Token loginUsuario(DataUsuario dataUsuario) throws UsuarioNoEncontradoException, AplicacionException {
		Token jwt = null;
		String pass = "";
		String tipoUsuario = "";
		
		Usuario u =	UsuarioDAO.buscarUsuario(dataUsuario.getTenantId(), dataUsuario.getEmail());				
	
		try {
			pass = new String(Base64.decode(dataUsuario.getPassword()));
		} catch (IOException e) {
			throw new AplicacionException("Error al obtener contrasenia del usuario");
		}		
		
		if( u != null && (u.getPassword().equalsIgnoreCase(pass)) ){// genero json web token.
			
			tipoUsuario = u instanceof UsuarioComun ? USUARIO_COMUN : u instanceof ComiteOlimpico ? USUARIO_COMITE : u instanceof Organizador ? USUARIO_ORGANIZADOR : USUARIO_JUEZ;
			
			if(tipoUsuario.equals(USUARIO_COMITE)){
				DataComite dc = new DataComite(u.getEmail(),"",((ComiteOlimpico)u).getCodigo(),new DataPais(((ComiteOlimpico)u).getPais().getPaisID(),((ComiteOlimpico)u).getPais().getPais(),((ComiteOlimpico)u).getPais().getCiudad()),u.getFacebook(),u.getTwitter(),((ComiteOlimpico)u).getPaypal(),u.getTenantID(),u.getId().intValue(),tipoUsuario);
				jwt = JWTUtil.generarToken(dc);

			} else if(tipoUsuario.equals(USUARIO_JUEZ)){				
				DataJuez dj = new DataJuez(u.getTenantID(),((Juez)u).getNombre(),((Juez)u).getApellido(),"",u.getEmail(),tipoUsuario,u.getId().intValue());
				jwt = JWTUtil.generarToken(dj);
			} else { 	// organizador es muy parecido por ahora...			
				DataUsuario du = convertir(u);
				du.setTipoUsuario(tipoUsuario);
				jwt = JWTUtil.generarToken(du);
			}
		}else{
			throw new UsuarioNoEncontradoException("No se encuentra usuario con dichas credenciales");
		}

		if(!tipoUsuario.equals(USUARIO_ORGANIZADOR)){
			try {
				HistorialLoginDAO.guardarHistorial(new HistorialLogin(u.getTenantID(), new Date(), u,Tipo.LOGIN));
			} catch (Exception e) {
				return jwt;
			}
		}
		
		return jwt;
	}

	@Override
	public List<ComiteOlimpico> buscarComiteporPais(int paisID, int tenantID) {		
		try{
			return UsuarioDAO.buscarComiteporPais(paisID, tenantID);
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public boolean guardarNovedad(DataNovedad dataNovedad) throws UsuarioNoEncontradoException, AplicacionException {
		
		boolean guardo = false;
		ComiteOlimpico comite = (ComiteOlimpico)UsuarioDAO.buscarUsuario(dataNovedad.getTenantId(), dataNovedad.getEmailComiteOlimpico(), USUARIO_COMITE);		
		
		if(comite != null){
		
			Imagen i = new Imagen(dataNovedad.getImagen().getMime(), dataNovedad.getImagen().getRuta(), dataNovedad.getImagen().getTenantId());
			
			if(ImagenDAO.guardarImagen(i)){			
		
				Novedad n = new Novedad(dataNovedad.getTitulo(), dataNovedad.getDescripcion(), dataNovedad.getColumna(), comite,i,dataNovedad.getImagen().getTenantId());
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
	public Imagen subirImagenNovedad(MultipartFormDataInput input) throws AplicacionException{
		
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
 
	@Override
	public boolean guardarEstado(DataHistorialLogin hl)	throws AplicacionException, UsuarioNoEncontradoException {
		boolean guardo = false;

//		Usuario u = UsuarioDAO.buscarUsuario(hl.getTenantId(),hl.getEmailUsuario(), USUARIO_COMUN);
//		if (u != null) {
//			guardo = HistorialLoginDAO.guardarHistorial(new HistorialLogin(hl.getTenantId(), new Date(), u, Tipo.CIERRE_SESION));
//		} else {
//			throw new UsuarioNoEncontradoException("No se encuentra el usuario con email '"+ hl.getEmailUsuario() + "'");
//		}
		return guardo;
	}

	@Override
	public List<DataHistorialLogin> obtenerHistorial(Integer tenantId) throws AplicacionException {
		try{
			return convertir(tenantId,HistorialLoginDAO.recuperarHistorial(tenantId));
		}catch(Exception e){
			e.printStackTrace();
			throw new AplicacionException("Error obteniendo historial.");
		}
	}
	
	private List<DataHistorialLogin> convertir(int tenantId, List<Object> historial) {
		List<DataHistorialLogin> dataHistorial = new ArrayList<DataHistorialLogin>();

		for (int i = 0; i < historial.size(); i++) {
				DataHistorialLogin dl = new DataHistorialLogin(
										tenantId,
										(Integer) ((Object[]) historial.get(i))[0], // mes
										(Integer) ((Object[]) historial.get(i))[1], // anio
										((BigInteger) ((Object[]) historial.get(i))[2]).intValue(), // comunes
										((BigInteger) ((Object[]) historial.get(i))[3]).intValue(), // comites
										((BigInteger) ((Object[]) historial.get(i))[4]).intValue()); // jueces
				
			dataHistorial.add(dl);
		}

		return dataHistorial;

	}	

	@Override
	public Integer cantidadRegistrados(Integer tenantId) throws AplicacionException {
		try{
			return HistorialLoginDAO.obtenerCantidadRegistrados(tenantId);
		}catch(Exception e){
			throw new AplicacionException("Error obteniendo cantidad de usuarios registrados.");
		}
	}

	@Override
	public boolean guardarJuez(DataUsuario usuario) {
		
		try {
			boolean enviado = false;
			boolean guardo = false;
			Juez j = null;
			
			boolean existeJuez = UsuarioDAO.existeJuez(usuario.getTenantId(),usuario.getEmail());
		
			
			// ANTES DE DAR DE ALTA, FIJARSE EN EL dataComite que viene, si ya existe uno con ese cod y ese pais 
			// en ese tenant. 
			
			if((existeJuez == false)){
			
					j = new Juez();
				
					j.setEmail(usuario.getEmail());
					j.setPassword(usuario.getPassword());
					j.setTenantID(usuario.getTenantId());
					j.setApellido(usuario.getApellido());
					j.setNombre(usuario.getNombre());
					
					guardo = UsuarioDAO.guardarUsuario(j);
		
					if(guardo){
						// Se deber�a enviar luego del guardar Usuario.. porque devuelve un booleano, si se pudo guardar enviar correo, sino no.
						enviado = Correo.enviarMensajeConAuth("smtp.gmail.com", 587,"inmogrupo13@gmail.com", j.getEmail(),"inmobiliaria13", "Notificacion de contrase�a", "Estimado Se�or Juez : 	Su contrase�a es:"+j.getPassword()+"");
						
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
	public List<DataJuez> listarJueces(Integer tenantId) {
		try {
				
			return convertirJueces(UsuarioDAO.listarJueces(tenantId));
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	public List<DataJuez> convertirJueces(List<Juez> usuario){
		List<DataJuez> dataJuez = new ArrayList<DataJuez>();
		
		for(int i = 0; i< usuario.size(); i++){
			DataJuez u = new DataJuez();
					
			u.setNombre(usuario.get(i).getNombre());
			u.setApellido(usuario.get(i).getApellido());
			u.setTenantId(usuario.get(i).getTenantID());
			u.setEmail(usuario.get(i).getEmail());
			

			dataJuez.add(u);			
		}
		
		return dataJuez;		
		
	}
	@Override

	public List<DataNovedad> getNovedadesPrincipales(int tenantid) {
		
		List<Novedad> novedades = NovedadDAO.getNovedades(tenantid);
		return convertirListaDataNovedad(novedades);
		
	}
	private List<DataNovedad> convertirListaDataNovedad(List<Novedad> aux){
		
		List<DataNovedad> nov = new ArrayList<DataNovedad>();
		if((aux != null)&&(!(aux.isEmpty()))){
			for (Novedad n : aux) {
				
				DataImagen di = getDataImagen( n.getImagen());
				DataNovedad dn = getDataNovedad(n,di);
				nov.add(dn);
			}		
			return nov;
		}else {
			return new ArrayList<DataNovedad>();
		}
		
	}
	private DataImagen getDataImagen(Imagen i){
		
		DataImagen di = new DataImagen(i.getMime(),i.getRuta(),i.getTenantId());
		return di ;
	}
	private DataNovedad getDataNovedad(Novedad n, DataImagen di){
		
		DataNovedad dn = new DataNovedad(n.getTitulo(),n.getDescripcion(),n.getColumna(),n.getTenantID(),"",di);
		return dn;
	}

	@Override
	public List<DataComite> listarComitesOlimpicos(Integer tenantID) throws AplicacionException {
		try {			
			return convertirComitesOlimpicos(UsuarioDAO.listarComitesOlimpicos(tenantID));		
		} catch(Exception e){
			e.printStackTrace();
			throw new AplicacionException("Error obteniendo comites olimpicos.");
		}
	}
	private List<DataComite> convertirComitesOlimpicos(List<ComiteOlimpico> comites){
		List<DataComite> listaComites = new ArrayList<DataComite>();
		
		for(int i = 0; i< comites.size(); i++){
			DataComite c = new DataComite();
			
			c.setCodigo(comites.get(i).getCodigo());
			c.setComiteId(comites.get(i).getId().intValue());
			c.setPais(new DataPais(comites.get(i).getPais().getPaisID(),comites.get(i).getPais().getPais(),comites.get(i).getPais().getCiudad()));
			c.setEmail(comites.get(i).getEmail());
			c.setFacebook(comites.get(i).getFacebook());
			c.setLogo(new DataImagen(comites.get(i).getLogo().getMime(),comites.get(i).getLogo().getRuta(),comites.get(i).getLogo().getTenantId()));
			c.setPassword("");
			c.setPaypal("");
			c.setTenantId(comites.get(i).getTenantID());
			c.setTipoUsuario(USUARIO_COMITE);
			c.setTwitter(comites.get(i).getTwitter());			

			listaComites.add(c);			
		}		
		return listaComites;		
	}

}

