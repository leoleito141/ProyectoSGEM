package com.sgem.controladores;

import java.util.List;

import javax.ejb.Local;

import com.sgem.datatypes.DataCompetencia;
import com.sgem.datatypes.DataCompraEntrada;
import com.sgem.datatypes.DataEstadistica;
import com.sgem.datatypes.DataResultado;
import com.sgem.dominio.Competencia;
import com.sgem.seguridad.excepciones.AplicacionException;

@Local
public interface ICompetenciaController {

	public Boolean guardarCompetencia(DataCompetencia dataCompetencia);

	public List<DataCompetencia> listarCompetenciasPorRonda(int tenantID, String nombreDeporte, String sexo, String nombreDisciplina, int ronda);

	public float obtenerPrecio(int tenantID, int idCompetencia);

	public boolean comprarEntradas(DataCompraEntrada datos);

	public List<DataCompetencia> listarCompetenciasPendientes(int tenantID, int juezID) throws AplicacionException;

	public boolean guardarResultado(DataResultado resultado) throws AplicacionException;

	public List<DataCompetencia> listarCompetenciasPorDisciplina(int tenantID, String nombreDeporte, String nombreDisciplina, String sexo) throws AplicacionException;

	public DataResultado listarResultadosCompetencia(int tenantID, int competenciaID);

	public DataEstadistica listarEstadisticaPorPais(int tenantID, int competenciaID, int comiteID) throws AplicacionException;

	public DataCompetencia convertirCompetencia(Competencia competencia);

	public DataCompetencia getCompetenciaPorEstadistica(int tenantID, int estadisticaID) throws AplicacionException;
}

