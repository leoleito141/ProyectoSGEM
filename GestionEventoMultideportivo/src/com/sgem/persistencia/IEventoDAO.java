package com.sgem.persistencia;

import javax.ejb.Local;

import com.sgem.dominio.EventoMultideportivo;

import com.sgem.dominio.Usuario;

@Local
public interface IEventoDAO {

	public boolean guardarEvento(EventoMultideportivo evento);
	public EventoMultideportivo buscarEvento(String nombre);
	
		
	
	
}
