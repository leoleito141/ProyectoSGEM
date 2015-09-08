package com.sgem.persistencia;

import javax.ejb.Local;

import com.sgem.dominio.Usuario;

@Local
public interface IUsuarioDAO {

	public boolean guardarUsuario(Usuario usuario);	
	public Usuario buscarUsuario(String nombre);
	
}
