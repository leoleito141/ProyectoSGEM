package com.sgem.controladores;

import java.util.List;

import javax.ejb.Local;

import com.sgem.datatypes.DataComite;
import com.sgem.datatypes.DataNovedad;
import com.sgem.datatypes.DataUsuario;
import com.sgem.dominio.ComiteOlimpico;
import com.sgem.dominio.Usuario;
import com.sgem.seguridad.excepciones.UsuarioNoEncontradoException;
import com.sgem.seguridad.excepciones.UsuarioYaExisteException;
import com.sgem.seguridad.jwt.Token;

@Local
public interface IUsuarioController {
	
	public boolean guardarUsuario(DataUsuario dataUsuario) throws UsuarioYaExisteException;
	public Token loginAdmin(DataUsuario dataUsuario) throws UsuarioNoEncontradoException;
	public Token loginUsuario(DataUsuario dataUsuario) throws UsuarioNoEncontradoException;
	public boolean guardarComite(DataComite dataComite);
	public List<ComiteOlimpico> buscarComiteporPais(String pais, int tenantID);
	public boolean guardarNovedad(DataNovedad dataNovedad) throws UsuarioNoEncontradoException;

}
