package com.sgem.controladores;

import javax.ejb.Local;

import com.sgem.datatypes.DataUsuario;
import com.sgem.dominio.Usuario;
import com.sgem.seguridad.Token;

@Local
public interface IUsuarioController {
	
	public boolean guardarUsuario(DataUsuario dataUsuario);
	public Usuario buscarUsuario(String email);
	public Token loginUsuario(DataUsuario dataUsuario);

}
