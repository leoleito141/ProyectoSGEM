package com.sgem.persistencia;

import javax.ejb.Local;

import com.sgem.dominio.Ronda;

@Local
public interface IRondaDAO {

	public boolean guardarRonda(Ronda r);
	public Ronda buscarRonda(int tenantID, int idRonda);
	public Ronda buscarRonda(int tenantID, int idEventoDep, int numeroRonda);
	public boolean modificarRonda(Ronda r);
}
