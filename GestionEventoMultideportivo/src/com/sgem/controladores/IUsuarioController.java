package com.sgem.controladores;

import com.sgem.dominio.Usuario;

public interface IUsuarioController {
	
	public boolean guardarUsuario(Usuario usuario);	
	public Usuario buscarUsuario(String nombre);

}
