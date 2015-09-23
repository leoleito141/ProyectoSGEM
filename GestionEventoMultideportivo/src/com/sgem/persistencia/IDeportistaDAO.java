package com.sgem.persistencia;

import javax.ejb.Local;

import com.sgem.datatypes.DataUsuario;
import com.sgem.dominio.Admin;
import com.sgem.dominio.Deportista;
import com.sgem.dominio.Organizador;
import com.sgem.dominio.Usuario;

@Local
public interface IDeportistaDAO {

	public boolean guardarDeportista(Deportista d);
	
	
		
	
	
}
