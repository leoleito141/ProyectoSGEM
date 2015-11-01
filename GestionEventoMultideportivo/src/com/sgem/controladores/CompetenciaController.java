package com.sgem.controladores;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.sgem.datatypes.DataCompetencia;
import com.sgem.datatypes.DataDeportista;
import com.sgem.datatypes.DataJuez;
import com.sgem.dominio.ComiteOlimpico;
import com.sgem.dominio.Competencia;
import com.sgem.dominio.Deportista;
import com.sgem.dominio.Entrada;
import com.sgem.dominio.EventoDeportivo;
import com.sgem.dominio.Juez;
import com.sgem.dominio.Ronda;
import com.sgem.dominio.Usuario;
import com.sgem.persistencia.ICompetenciaDAO;
import com.sgem.persistencia.IDeportistaDAO;
import com.sgem.persistencia.IEntradaDAO;
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
	
	@EJB
	private IEntradaDAO EntradaDAO;
	
	@EJB
	private IDeportistaController  idc;

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
		
				
		
		int idEventoDep = EventosDeportivosDAO.traerIDEventoDeportivo(tenantID, nombreDeporte, nombreDisciplina, sexo);
		
		EventoDeportivo ed = EventosDeportivosDAO.traerEventoDeportivo(idEventoDep);
		
		Ronda r = EventosDeportivosDAO.traerRonda(tenantID,idEventoDep,numeroRonda);
		
		Juez j = (Juez) UsuarioDAO.buscarUsuario(tenantidJuez, mailJuez, "Juez");
		
		List<Deportista> deportistas = idc.listarDeportistas(listaDeportistas);
		

		
		Competencia competencia = new Competencia();
	
		competencia.setTenantId(dataCompetencia.getTenantId());
		competencia.setCantEntradas(dataCompetencia.getCantEntradas());
		competencia.setEstadio(dataCompetencia.getEstadio());
		competencia.setFecha(dataCompetencia.getFecha());
		competencia.setPrecioEntrada(dataCompetencia.getPrecioEntrada());
		competencia.setJuez(j);
		competencia.setRonda(r);
		competencia.setEventoDeportivo(ed);
		
		
			for(int i = 1; i< (dataCompetencia.getCantEntradas()+1); i++){
			
			Entrada e = new Entrada();
			e.setCompetencia(competencia);
			e.setTenantId(dataCompetencia.getTenantId());
			e.setFecha(dataCompetencia.getFecha());
			e.setNumeroAsiento(i);
			e.setPrecioEntrada(dataCompetencia.getPrecioEntrada());
			e.setVendida(false);
			
			EntradaDAO.guardarEntrada(e);
			
			competencia.addEntrada(e);
			
			} 
			
			for(int i = 0; i< deportistas.size(); i++){
				
				
				
				competencia.addDeportista(deportistas.get(i));
				
				} 
	
		
		return CompetenciaDAO.guardarCompetencia(competencia);
		
			
		
		
	}

	@Override
	public List<DataCompetencia> listarCompetenciasPorRonda(int tenantID, String nombreDeporte, String sexo,String nombreDisciplina, int ronda) {
		try {
			
			int idEventoDep = EventosDeportivosDAO.traerIDEventoDeportivo(tenantID, nombreDeporte, nombreDisciplina, sexo);
			
			return convertirListaCompetencias(CompetenciaDAO.listarCompetenciasPorRonda(tenantID, idEventoDep , ronda));
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private List<DataCompetencia> convertirListaCompetencias(List<Competencia> competencia) {
		
		
			List<DataCompetencia> dataCompetencia = new ArrayList<DataCompetencia>();
			
			for(int i = 0; i< competencia.size(); i++){
				DataCompetencia c = new DataCompetencia();
						
				c.setTenantId(competencia.get(i).getTenantId());
				c.setEstadio(competencia.get(i).getEstadio());
				c.setCantEntradas(competencia.get(i).getCantEntradas());
				c.setFecha(competencia.get(i).getFecha());
				c.setPrecioEntrada(competencia.get(i).getPrecioEntrada());
				

				dataCompetencia.add(c);			
			}
		
		
		return dataCompetencia;
	}
	
	
	

	
}

