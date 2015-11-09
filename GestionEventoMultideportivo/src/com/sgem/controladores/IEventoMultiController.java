package com.sgem.controladores;

import javax.ejb.Local;

import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import com.sgem.datatypes.DataEvento;
import com.sgem.datatypes.DataTenant;
import com.sgem.dominio.EventoMultideportivo;
import com.sgem.dominio.Imagen;
import com.sgem.seguridad.excepciones.AplicacionException;

@Local
public interface IEventoMultiController {
	
	public boolean guardarEventoMultideportivo(DataEvento dataEvento);
	public DataTenant obtenerDataTenant(String tenant);
	public EventoMultideportivo obtenerEventoMultideportivoXTenantId(int tenantId);
	public String obtenerProximoTenant();
	public Imagen subirImagenBanner(MultipartFormDataInput input) throws AplicacionException;

}
