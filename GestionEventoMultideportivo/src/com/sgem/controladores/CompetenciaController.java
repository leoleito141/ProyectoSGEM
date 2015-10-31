package com.sgem.controladores;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.sgem.datatypes.DataCompetencia;
import com.sgem.datatypes.DataDeportista;
import com.sgem.dominio.ComiteOlimpico;
import com.sgem.dominio.Deportista;
import com.sgem.dominio.EventoDeportivo;
import com.sgem.dominio.Juez;
import com.sgem.dominio.Ronda;
import com.sgem.dominio.Usuario;
import com.sgem.persistencia.ICompetenciaDAO;
import com.sgem.persistencia.IDeportistaDAO;
import com.sgem.persistencia.IEventoDeportivoDAO;
import com.sgem.persistencia.IUsuarioDAO;

@Stateless
public class CompetenciaController implements ICompetenciaController {

	@EJB
	private ICompetenciaDAO CompetenciaDAO;
	
	@EJB
	private IEventoDeportivoDAO EventosDeportivosDAO;
	
	@EJB
	private IUsuarioDAO UsuarioDAO;
	
	@EJB
	private IDeportistaDAO DeportistaDAO;

	@Override
	public Boolean guardarCompetencia(DataCompetencia dataCompetencia) {
		
		
		int tenantID = dataCompetencia.getTenantId();
		String nombreDeporte = dataCompetencia.getNombreDeporte();
		String nombreDisciplina = dataCompetencia.getNombreDisciplina();
		String sexo = dataCompetencia.getSexo();
		int numeroRonda = dataCompetencia.getRonda();
		int tenantidJuez = dataCompetencia.getJuez().getTenantId();
		String mailJuez =  dataCompetencia.getJuez().getEmail();
		List<DataDeportista> listaDeportistas = dataCompetencia.getDeportistas();
		
				
		
		int idEventoDep = EventosDeportivosDAO.traerIDEventoDeportivo(tenantID, nombreDeporte, nombreDisciplina, nombreDisciplina);
		
		EventoDeportivo ed = EventosDeportivosDAO.traerEventoDeportivo(idEventoDep);
		
		Ronda r = EventosDeportivosDAO.traerRonda(tenantID,idEventoDep,numeroRonda);
		
		Juez j = (Juez) UsuarioDAO.buscarUsuario(tenantidJuez, mailJuez, "Juez");
		
		
		
		
	
		
		
		
		
		return null;
	}
	
	
	

	
}

