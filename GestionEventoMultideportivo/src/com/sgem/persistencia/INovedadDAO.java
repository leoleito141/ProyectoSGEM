package com.sgem.persistencia;

import java.util.List;

import javax.ejb.Local;

import com.sgem.datatypes.DataUsuario;
import com.sgem.dominio.Admin;
import com.sgem.dominio.Deportista;
import com.sgem.dominio.Novedad;
import com.sgem.dominio.Organizador;
import com.sgem.dominio.Usuario;

@Local
public interface INovedadDAO {

	public boolean guardarNovedad(Novedad n);
	public List<Novedad> getNovedades(int tenantID);
	public Novedad getNovedad(int idnovedad);
}
