package com.sgem.persistencia;

import java.util.List;

import javax.ejb.Local;

import com.sgem.datatypes.DataComite;
import com.sgem.datatypes.DataUsuario;
import com.sgem.dominio.Admin;
import com.sgem.dominio.ComiteOlimpico;
import com.sgem.dominio.Entrada;
import com.sgem.dominio.Juez;
import com.sgem.dominio.Organizador;
import com.sgem.dominio.Usuario;
import com.sgem.dominio.UsuarioComun;

@Local
public interface IUsuarioDAO {
	
	public boolean guardarUsuario(Usuario usuario);
	public Usuario buscarUsuario(int tenantId, String email, String clase);
	public Usuario buscarUsuario(int tenantId, String email);
	public Usuario buscarAdmin(String email, String clase);
	public boolean guardarOrganizador(Organizador org);
	public boolean existeCodigoCO(int tenantId, String codigo);
	public boolean existePais(int paisID,int tenantId);
	public List<ComiteOlimpico> buscarComiteporPais(int paisID, int tenantID);
	public boolean existeJuez(Integer tenantId, String email);
	public List<Juez> listarJueces(Integer tenantId);
	public List<ComiteOlimpico> listarComitesOlimpicos(Integer tenantID);
	public int obtenerMaximoComite();
	public ComiteOlimpico obtenerComite(int tenantid, int usuarioID);
	public List<Entrada> listarEntradasCompradasUsuario(Integer tenantId, Integer usuarioId);
	
	
	
}
