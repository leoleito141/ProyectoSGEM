package com.sgem.controladores;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.sgem.datatypes.DataDeportista;
import com.sgem.datatypes.DataHistorialLogin;
import com.sgem.dominio.ComiteOlimpico;
import com.sgem.dominio.Deportista;
import com.sgem.dominio.EventoDeportivo;
import com.sgem.dominio.HistorialLogin;
import com.sgem.persistencia.IDeportistaDAO;



@Stateless
public class DeportistaController implements IDeportistaController {

	@EJB
	private IDeportistaDAO DeportistaDAO;
	
	@EJB
	private IUsuarioController  iuc;
	
	@EJB
	private IEventoDeportivoController  iedc;
	
	@Override
	public boolean guardarDeportista(DataDeportista dataDeportista) {
		try {

		
					Deportista deportista = new Deportista();
			
				deportista.setNombre(dataDeportista.getNombre());
				deportista.setApellido(dataDeportista.getApellido());
				deportista.setFechaNac(dataDeportista.getFechaNac());
				deportista.setSexo(dataDeportista.getSexo());
				deportista.setTenantID(dataDeportista.getTenantId());
				
				List<ComiteOlimpico>  comiteOlimpico = iuc.buscarComiteporPais(dataDeportista.getPais(),dataDeportista.getTenantId());
				
				deportista.setComiteOlimpico(comiteOlimpico.get(0));
				
				List<EventoDeportivo> eventosDep = iedc.buscarEventosDeportivos(dataDeportista.getTenantId(),dataDeportista.getDeporte(),dataDeportista.getDisciplinas(),dataDeportista.getSexo());
				
				
				
				for (int i = 0; i < eventosDep.size(); i++) {
					
					EventoDeportivo dep = eventosDep.get(i);
					
					deportista.addEventoDeportivo(dep);
					
				}	
				
					return DeportistaDAO.guardarDeportista(deportista);
					
				
				
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<DataDeportista> listarDeportistas(int tenantID, String nombreDeporte, String sexo, String nombreDisciplina) {
		try {
					
				return convertir(DeportistaDAO.listarDeportistas(tenantID, nombreDeporte, sexo, nombreDisciplina));
				
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			return null;
	}
	
	
	
	public List<DataDeportista> convertir(List<Deportista> deportista){
		List<DataDeportista> dataDeportista = new ArrayList<DataDeportista>();
		
		for(int i = 0; i< deportista.size(); i++){
			DataDeportista ddep = new DataDeportista();
					
			ddep.setNombre(deportista.get(i).getNombre());
			ddep.setApellido(deportista.get(i).getApellido());
			ddep.setPais(deportista.get(i).getComiteOlimpico().getPais());

			dataDeportista.add(ddep);			
		}
		
		return dataDeportista;		
		
	}
	
	
	
	
}
