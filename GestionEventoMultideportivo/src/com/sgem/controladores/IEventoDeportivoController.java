package com.sgem.controladores;

import java.util.List;
import java.util.Set;

import javax.ejb.Local;

import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import com.sgem.datatypes.DataEventoDeportivo;
import com.sgem.dominio.EventoDeportivo;
import com.sgem.dominio.Imagen;
import com.sgem.seguridad.excepciones.AplicacionException;
import com.sgem.seguridad.excepciones.UsuarioYaExisteException;

@Local
public interface IEventoDeportivoController {

	boolean guardarEventoDeportivo(DataEventoDeportivo dataEventoDeportivo);

	public List<String> listarDeportes(int tenantID, String sexo);

	public List<String> listarDisciplinas(int tenantID, String nombreDeporte, String sexo);

	public List<EventoDeportivo> buscarEventosDeportivos(Integer tenantId, String deporte, List<String> disciplinas, String sexo);

	public List <Integer> listarRondas(int tenantID, String nombreDeporte, String sexo, String nombreDisciplina);

	public List<DataEventoDeportivo> listarDeportes(int tenantID) throws AplicacionException;

	public List<EventoDeportivo> buscarEventosDeportivos(Set<DataEventoDeportivo> listeventodeportivo);

	public List<DataEventoDeportivo> listarDisciplinasEventoDeportivo(int tenantID, String nombreDeporte) throws AplicacionException;

	public Imagen subirImagenEventoDeportivo(MultipartFormDataInput input) throws AplicacionException,UsuarioYaExisteException;

}
