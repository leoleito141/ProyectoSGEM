package com.sgem.persistencia;

import javax.ejb.Local;

import com.sgem.dominio.Resultado;

@Local
public interface IResultadoDAO {

	public boolean guardarResultado(Resultado r);
	public Resultado buscarResultado(int tenantID, int idResultado);
	public boolean modificarResultado(Resultado e);
	public Resultado traerResultado(int tenantID, int competenciaID);
}
