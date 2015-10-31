package com.sgem.persistencia;

import java.util.List;

import javax.ejb.Local;

import com.sgem.datatypes.DataDeportista;
import com.sgem.datatypes.DataUsuario;
import com.sgem.dominio.Admin;
import com.sgem.dominio.Deportista;
import com.sgem.dominio.Organizador;
import com.sgem.dominio.Usuario;

@Local
public interface IDeportistaDAO {

	public boolean guardarDeportista(Deportista d);

	public List<Deportista> listarDeportistas(int tenantID, String nombreDeporte, String sexo,
			String nombreDisciplina);

	public Deportista buscarDeportista(int idDeportista);
	
	
		
	
	
}
