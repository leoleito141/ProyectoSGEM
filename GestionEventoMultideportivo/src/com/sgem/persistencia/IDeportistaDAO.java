package com.sgem.persistencia;

import java.util.List;
import java.util.Set;

import javax.ejb.Local;

import com.sgem.dominio.Competencia;
import com.sgem.dominio.Deportista;
import com.sgem.dominio.EventoDeportivo;

@Local
public interface IDeportistaDAO {

	public boolean guardarDeportista(Deportista d);

	public List<Deportista> listarDeportistas(int tenantID, String nombreDeporte, String sexo, String nombreDisciplina);

	public Deportista buscarDeportista(int idDeportista);

	public boolean modificarDeportista(Deportista deportista);

	public List<Deportista> listarDeportistasPorComite(int tenantID, int comiteID);
	
	public List<EventoDeportivo> listarEventoDeportivo(int tenantID,String sexo);
	
	public List<String> listarPaises(int tenantID);
	
	public List<Deportista> listarDeportistas(int tenantId,String nombreDeportista, String deporte, String pais, String sexo);

	public List<String> listarDeportes(int tenantID, String sexo);
	
	public List<Deportista> listarDeportistasPorEventoDeportivo(int tenantID, String nombreDeporte);

	public Set<Competencia> traerCompetenciasDelDeportista(int idDeportista, int tenantid);

	

}
