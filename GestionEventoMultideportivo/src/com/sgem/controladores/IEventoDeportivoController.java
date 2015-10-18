package com.sgem.controladores;

import java.util.List;

import javax.ejb.Local;

import com.sgem.datatypes.DataEventoDeportivo;

@Local
public interface IEventoDeportivoController {

	boolean guardarEventoDeportivo(DataEventoDeportivo dataEventoDeportivo);

	List<String> listarDeportes(int tenantID, String sexo);

	List<String> listarDisciplinas(int tenantID, String nombreDeporte, String sexo);

}
