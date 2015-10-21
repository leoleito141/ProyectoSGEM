package com.sgem.controladores;

import java.util.List;

import javax.ejb.Local;

import com.sgem.datatypes.DataComite;
import com.sgem.datatypes.DataUsuario;
import com.sgem.dominio.ComiteOlimpico;
import com.sgem.dominio.Usuario;
import com.sgem.seguridad.Token;

@Local
public interface IUsuarioController {
	
	public boolean guardarUsuario(DataUsuario dataUsuario);
	public Usuario buscarUsuario(String email);
	public Usuario buscarAdmin(String email);
	public Token loginAdmin(DataUsuario dataUsuario);
	public Token loginUsuario(DataUsuario dataUsuario);
	public boolean guardarComite(DataComite dataComite);
	public List<ComiteOlimpico> buscarComiteporPais(String pais, int tenantID);

}
