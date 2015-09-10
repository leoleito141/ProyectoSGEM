package com.sgem.controladores;

import javax.ejb.Local;

import com.sgem.dominio.Usuario;

@Local
public interface IUsuarioController {
	
	public boolean guardarUsuario(Usuario usuario);	
	public Usuario buscarUsuario(String nombre);

}
