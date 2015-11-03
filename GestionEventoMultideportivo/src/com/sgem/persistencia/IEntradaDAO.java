package com.sgem.persistencia;

import java.util.List;

import com.sgem.dominio.Competencia;
import com.sgem.dominio.Entrada;
import com.sgem.dominio.UsuarioComun;

public interface IEntradaDAO {
	
	
	public void guardarEntrada(Entrada entrada);

	public List<Entrada> listarEntradas(int tenantId, int idCompetencia);

	boolean guardarCompra(UsuarioComun u, Competencia c);

}
