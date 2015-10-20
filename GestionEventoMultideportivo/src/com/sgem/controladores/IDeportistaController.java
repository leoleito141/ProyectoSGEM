package com.sgem.controladores;

import javax.ejb.Local;

import com.sgem.datatypes.DataDeportista;

@Local
public interface IDeportistaController {

	boolean guardarDeportista(DataDeportista dataDeportista);


}