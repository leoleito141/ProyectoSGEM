package com.sgem.persistencia;

import javax.ejb.Local;

import com.sgem.datatypes.DataUsuario;
import com.sgem.dominio.Admin;
import com.sgem.dominio.Organizador;
import com.sgem.dominio.Usuario;

@Local
public interface IUsuarioDAO {

	public boolean guardarUsuario(Usuario usuario);
	public Usuario buscarUsuario(String email);
	public boolean existeCodigoCO(int tenantId, String codigo);
	public boolean existePais(int tenantId, String pais);
	
		
	
	
}
