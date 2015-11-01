package com.sgem.persistencia;

import java.util.List;

import javax.ejb.Local;

import com.sgem.datatypes.DataCompetencia;
import com.sgem.dominio.Competencia;



@Local
public interface ICompetenciaDAO {

	public boolean guardarCompetencia(Competencia c);
	public List<Competencia> listarCompetenciasPorRonda(int tenantID, int idEventoDeportivo,int ronda);


	
}
