package com.sgem.persistencia;

import javax.ejb.Local;

import com.sgem.dominio.Estadistica;

@Local
public interface IEstadisticaDAO {

	public boolean guardarEstadistica(Estadistica e);
	public Estadistica buscarEstadistica(int tenantID, int idEstadistica);
	public boolean modificarEstadistica(Estadistica e);
}
