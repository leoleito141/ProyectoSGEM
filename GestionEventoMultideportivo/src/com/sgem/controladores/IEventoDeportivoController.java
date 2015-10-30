package com.sgem.controladores;

import java.util.List;

import javax.ejb.Local;

import com.sgem.datatypes.DataEventoDeportivo;
import com.sgem.dominio.EventoDeportivo;

@Local
public interface IEventoDeportivoController {

	boolean guardarEventoDeportivo(DataEventoDeportivo dataEventoDeportivo);

	List<String> listarDeportes(int tenantID, String sexo);

	List<String> listarDisciplinas(int tenantID, String nombreDeporte, String sexo);

	List<EventoDeportivo> buscarEventosDeportivos(Integer tenantId, String deporte, List<String> disciplinas, String sexo);

	List <Integer> listarRondas(int tenantID, String nombreDeporte, String sexo, String nombreDisciplina);

}
