package com.sgem.controladores;

import javax.ejb.Local;

import com.sgem.datatypes.DataEvento;
import com.sgem.datatypes.DataTenant;
import com.sgem.dominio.EventoMultideportivo;

@Local
public interface IEventoMultiController {
	
	public boolean guardarEventoMultideportivo(DataEvento dataEvento);
	public DataTenant obtenerDataTenant(String tenant);
	public EventoMultideportivo obtenerEventoMultideportivoXTenantId(int tenantId);
	public String obtenerProximoTenant();

}
