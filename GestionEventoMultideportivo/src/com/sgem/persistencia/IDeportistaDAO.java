package com.sgem.persistencia;

import java.util.List;

import javax.ejb.Local;

import com.sgem.dominio.Deportista;

@Local
public interface IDeportistaDAO {

	public boolean guardarDeportista(Deportista d);

	public List<Deportista> listarDeportistas(int tenantID, String nombreDeporte, String sexo, String nombreDisciplina);

	public Deportista buscarDeportista(int idDeportista);

	public boolean modificarDeportista(Deportista deportista);

	public List<Deportista> listarDeportistasPorComite(int tenantID, int comiteID);

}
