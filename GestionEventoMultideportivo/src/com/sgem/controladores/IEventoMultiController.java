package com.sgem.controladores;

import javax.ejb.Local;

import com.sgem.datatypes.DataEvento;

@Local
public interface IEventoMultiController {
	
	boolean guardarEventoMultideportivo(DataEvento dataEvento);

}
