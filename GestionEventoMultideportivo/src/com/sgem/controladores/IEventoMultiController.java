package com.sgem.controladores;

import java.util.List;

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
	public DataEvento obtenerDataTenant(String tenant);
	public EventoMultideportivo obtenerEventoMultideportivoXTenantId(int tenantId);
	public String obtenerProximoTenant();
	public List<Imagen> subirImagenConfiguracion(MultipartFormDataInput input) throws AplicacionException;
	public boolean guardarConfiguracion(DataEvento datosEvento) throws AplicacionException;

}
