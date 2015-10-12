package com.sgem.controladores;

import javax.ejb.Local;

import com.sgem.datatypes.DataEventoDeportivo;

@Local
public interface IEventoDeportivoController {

	boolean guardarEventoDeportivo(DataEventoDeportivo dataEventoDeportivo);

}
