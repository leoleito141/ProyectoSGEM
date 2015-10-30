package com.sgem.persistencia;

import java.util.List;

import javax.ejb.Local;

import com.sgem.dominio.HistorialLogin;

@Local
public interface IHistorialLoginDAO {

	public boolean guardarHistorial(HistorialLogin hl);
	public List<Object> recuperarHistorial(int tenantId);
	public Integer obtenerCantidadRegistrados(Integer tenantId);
}
