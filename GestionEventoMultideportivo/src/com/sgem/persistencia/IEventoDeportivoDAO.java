package com.sgem.persistencia;

import java.util.List;

import javax.ejb.Local;

import com.sgem.dominio.EventoDeportivo;
import com.sgem.dominio.EventoMultideportivo;

import com.sgem.dominio.Usuario;

@Local
public interface IEventoDeportivoDAO {

	public boolean guardarEventoDeportivo(EventoDeportivo eventoDeportivo);
	public List<String> listarDeportes(int tenantID, String sexo);
	public List<String> listarDisciplinas(int tenantID, String nombreDeporte, String sexo);
	
}
