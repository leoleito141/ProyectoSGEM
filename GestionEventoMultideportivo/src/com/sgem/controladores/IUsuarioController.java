package com.sgem.controladores;

import java.util.List;

import javax.ejb.Local;

import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import com.sgem.datatypes.DataComite;
import com.sgem.datatypes.DataHistorialLogin;
import com.sgem.datatypes.DataJuez;
import com.sgem.datatypes.DataNovedad;
import com.sgem.datatypes.DataUsuario;
import com.sgem.dominio.ComiteOlimpico;
import com.sgem.dominio.HistorialLogin;
import com.sgem.dominio.Imagen;
import com.sgem.dominio.Juez;
import com.sgem.dominio.Usuario;
import com.sgem.seguridad.excepciones.AplicacionException;
import com.sgem.seguridad.excepciones.UsuarioNoEncontradoException;
import com.sgem.seguridad.excepciones.UsuarioYaExisteException;
import com.sgem.seguridad.jwt.Token;

@Local
public interface IUsuarioController {
	
	public boolean guardarUsuario(DataUsuario dataUsuario) throws UsuarioYaExisteException, AplicacionException;
	public Token loginAdmin(DataUsuario dataUsuario) throws UsuarioNoEncontradoException, AplicacionException;
	public Token loginAndroid(DataUsuario dataUsuario) throws UsuarioNoEncontradoException, AplicacionException;
	public Token loginIonic(DataJuez dataJuez) throws UsuarioNoEncontradoException, AplicacionException;
	public Token loginUsuario(DataUsuario dataUsuario) throws UsuarioNoEncontradoException, AplicacionException;
	public boolean guardarComite(DataComite dataComite) throws UsuarioYaExisteException, AplicacionException;
	public Imagen subirImagenComite(MultipartFormDataInput input) throws AplicacionException;
	public List<ComiteOlimpico> buscarComiteporPais(String pais, int tenantID);
	public boolean guardarNovedad(DataNovedad dataNovedad) throws UsuarioNoEncontradoException, AplicacionException;
	public Imagen subirImagenNovedad(MultipartFormDataInput input) throws AplicacionException;
	public boolean guardarEstado(DataHistorialLogin hl) throws UsuarioNoEncontradoException, AplicacionException;
	public List<DataHistorialLogin> obtenerHistorial(Integer tenantId) throws AplicacionException;
	public boolean guardarJuez(DataUsuario usuario);
	public Integer cantidadRegistrados(Integer tenantId) throws AplicacionException;
	public List<DataJuez> listarJueces(Integer tenantId);
	
}
