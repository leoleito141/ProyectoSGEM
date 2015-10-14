package com.sgem.controladores;

import javax.ejb.Local;

import com.sgem.datatypes.DataEvento;
import com.sgem.datatypes.DataTenant;

@Local
public interface IEventoMultiController {
	
	boolean guardarEventoMultideportivo(DataEvento dataEvento);
	public DataTenant obtenerDataTenant(String tenant);
	int traeridEventoMultit(int tenantId);

}
