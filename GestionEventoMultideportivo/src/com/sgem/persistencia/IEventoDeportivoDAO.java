package com.sgem.persistencia;

import java.util.List;

import javax.ejb.Local;

import com.sgem.dominio.EventoDeportivo;
import com.sgem.dominio.EventoMultideportivo;
import com.sgem.dominio.Ronda;
import com.sgem.dominio.Usuario;

@Local
public interface IEventoDeportivoDAO {

	public boolean guardarEventoDeportivo(EventoDeportivo eventoDeportivo, EventoMultideportivo emd);
	public List<String> listarDeportes(int tenantID, String sexo);
	public List<String> listarDisciplinas(int tenantID, String nombreDeporte, String sexo);
	public EventoDeportivo traerEventoDeportivo(Integer idEventoDep);
	public Integer traerIDEventoDeportivo(Integer tenantId, String deporte, String disciplina, String sexo);
	public EventoDeportivo traerEventoDeportivo(EventoDeportivo eventoDeportivo);
	public boolean guardarRondas(Ronda ronda);
	public List<Integer> listarRondas(int tenantID, String nombreDeporte, String sexo, String nombreDisciplina);
	
}
