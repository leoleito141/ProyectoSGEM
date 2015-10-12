package com.sgem.persistencia;

import javax.ejb.Local;

import com.sgem.dominio.EventoDeportivo;
import com.sgem.dominio.EventoMultideportivo;

import com.sgem.dominio.Usuario;

@Local
public interface IEventoDeportivoDAO {

	public boolean guardarEventoDeportivo(EventoDeportivo eventoDeportivo);
	
}
