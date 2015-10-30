package com.sgem.persistencia;

import java.util.List;

import javax.ejb.Local;

import com.sgem.dominio.Competencia;



@Local
public interface ICompetenciaDAO {

	public boolean guardarCompetencia(Competencia c);


	
}
