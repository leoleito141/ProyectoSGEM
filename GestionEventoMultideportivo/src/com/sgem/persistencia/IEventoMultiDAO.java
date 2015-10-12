package com.sgem.persistencia;

import javax.ejb.Local;

import com.sgem.dominio.EventoMultideportivo;
import com.sgem.dominio.TenantHandler;

@Local
public interface IEventoMultiDAO {
	public boolean guardarEvento(EventoMultideportivo evento);
	public EventoMultideportivo buscarEvento(String nombre);
	public boolean guardarTenant(TenantHandler tenat); 

}
