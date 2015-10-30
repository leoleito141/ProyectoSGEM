package com.sgem.controladores;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.sgem.datatypes.DataCompetencia;
import com.sgem.datatypes.DataDeportista;
import com.sgem.dominio.ComiteOlimpico;
import com.sgem.dominio.Deportista;
import com.sgem.dominio.EventoDeportivo;
import com.sgem.persistencia.ICompetenciaDAO;
import com.sgem.persistencia.IDeportistaDAO;

@Stateless
public class CompetenciaController implements ICompetenciaController {

	@EJB
	private ICompetenciaDAO CompetenciaDAO;

	@Override
	public Boolean guardarCompetencia(DataCompetencia dataCompetencia) {
		
		return null;
	}
	
	
	

	
}

