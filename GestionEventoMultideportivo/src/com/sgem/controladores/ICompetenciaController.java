package com.sgem.controladores;

import java.util.List;

import javax.ejb.Local;

import com.sgem.datatypes.DataCompetencia;
import com.sgem.datatypes.DataEventoDeportivo;
import com.sgem.dominio.EventoDeportivo;

@Local
public interface ICompetenciaController {

	Boolean guardarCompetencia(DataCompetencia dataCompetencia);

	List<DataCompetencia> listarCompetenciasPorRonda(int tenantID, String nombreDeporte, String sexo, String nombreDisciplina, int ronda);

	
}

