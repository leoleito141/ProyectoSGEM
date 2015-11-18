package com.sgem.controladores;

import java.util.List;

import javax.ejb.Local;

import com.sgem.datatypes.DataEventoDeportivo;
import com.sgem.dominio.EventoDeportivo;
import com.sgem.seguridad.excepciones.AplicacionException;

@Local
public interface IEventoDeportivoController {

	boolean guardarEventoDeportivo(DataEventoDeportivo dataEventoDeportivo);

	public List<String> listarDeportes(int tenantID, String sexo);

	public List<String> listarDisciplinas(int tenantID, String nombreDeporte, String sexo);

	public List<EventoDeportivo> buscarEventosDeportivos(Integer tenantId, String deporte, List<String> disciplinas, String sexo);

	public List <Integer> listarRondas(int tenantID, String nombreDeporte, String sexo, String nombreDisciplina);

	public List<DataEventoDeportivo> listarDeportes(int tenantID) throws AplicacionException;

}
