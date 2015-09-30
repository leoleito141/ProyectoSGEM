package com.sgem.persistencia;

import javax.ejb.Local;

import com.sgem.dominio.EventoDeportivo;
import com.sgem.dominio.EventoMultideportivo;

import com.sgem.dominio.Usuario;

@Local
public interface IEventoDAO {

	public boolean guardarEventoDeportivo(EventoDeportivo eventoDeportivo);
	
}
