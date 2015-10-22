package com.sgem.persistencia;

import java.util.List;

import javax.ejb.Local;

import com.sgem.datatypes.DataUsuario;
import com.sgem.dominio.Admin;
import com.sgem.dominio.ComiteOlimpico;
import com.sgem.dominio.Organizador;
import com.sgem.dominio.Usuario;
import com.sgem.dominio.UsuarioComun;

@Local
public interface IUsuarioDAO {


	
	public boolean guardarOrganizador(Organizador org);
	public boolean guardarUsuario(Usuario usuario);
//	public boolean existeUsuario(int tenantId, String pais, String clase);
	public Usuario buscarUsuario(int tenantId, String email, String clase);
	public Usuario buscarUsuario(int tenantId, String email);
	public boolean existeCodigoCO(int tenantId, String codigo);
	public boolean existePais(int tenantId, String pais);
	public List<ComiteOlimpico> buscarComiteporPais(String pais, int tenantID);
	public Usuario buscarAdmin(String email);

	
		
	
	
}
