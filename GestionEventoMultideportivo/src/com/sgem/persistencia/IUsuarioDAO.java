package com.sgem.persistencia;

import java.util.List;

import javax.ejb.Local;

import com.sgem.datatypes.DataUsuario;
import com.sgem.dominio.Admin;
import com.sgem.dominio.ComiteOlimpico;
import com.sgem.dominio.Organizador;
import com.sgem.dominio.Usuario;

@Local
public interface IUsuarioDAO {

	public 
	boolean guardarUsuario(Usuario usuario);
	public boolean existeEmail(int tenantId, String pais);
	public Usuario buscarUsuario(String email);
	public boolean existeCodigoCO(int tenantId, String codigo);
	public boolean existePais(int tenantId, String pais);
	public List<ComiteOlimpico> buscarComiteporPais(String pais, int tenantID);
	public Usuario buscarAdmin(String email);
	
		
	
	
}
