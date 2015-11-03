package com.sgem.persistencia;

import java.util.List;

import javax.ejb.Local;

import com.sgem.datatypes.DataCompetencia;
import com.sgem.dominio.Competencia;
import com.sgem.dominio.UsuarioComun;



@Local
public interface ICompetenciaDAO {

	public boolean guardarCompetencia(Competencia c);
	public List<Competencia> listarCompetenciasPorRonda(int tenantID, int idEventoDeportivo,int ronda);
	public float obtenerPrecio(int tenantID, int idCompetencia);
	public Competencia buscarCompetencia(int tenantID, int idCompetencia);
	
	


	
}
