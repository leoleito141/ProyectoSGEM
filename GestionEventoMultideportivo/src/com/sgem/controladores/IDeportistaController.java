package com.sgem.controladores;

import java.util.List;

import javax.ejb.Local;

import com.sgem.datatypes.DataDeportista;
import com.sgem.dominio.Deportista;

@Local
public interface IDeportistaController {

	boolean guardarDeportista(DataDeportista dataDeportista);

	List<DataDeportista> listarDeportistas(int tenantID, String nombreDeporte, String sexo, String nombreDisciplina);


}