package com.sgem.controladores;

import java.util.List;

import javax.ejb.Local;

import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import com.sgem.datatypes.DataDeportista;
import com.sgem.dominio.Deportista;
import com.sgem.dominio.Imagen;
import com.sgem.seguridad.excepciones.AplicacionException;

@Local
public interface IDeportistaController {

	boolean guardarDeportista(DataDeportista dataDeportista) throws AplicacionException;

	public List<DataDeportista> listarDeportistas(int tenantID, String nombreDeporte, String sexo, String nombreDisciplina);

	public List<Deportista> listarDeportistas(List<DataDeportista> deportistas);

	public Imagen subirImagenDeportista(MultipartFormDataInput input) throws AplicacionException;

	public List<DataDeportista> listarDeportistasPorComite(int tenantID, int comiteID) throws AplicacionException;
}