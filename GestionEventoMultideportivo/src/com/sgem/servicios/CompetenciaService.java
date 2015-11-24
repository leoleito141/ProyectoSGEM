package com.sgem.servicios;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.Response;

import com.sgem.controladores.ICompetenciaController;
import com.sgem.datatypes.DataCompetencia;
import com.sgem.datatypes.DataCompraEntrada;
import com.sgem.datatypes.DataResultado;
import com.sgem.seguridad.excepciones.AplicacionException;



@Stateless
public class CompetenciaService implements ICompetenciaService{
 
	@EJB
	private ICompetenciaController icc;
	
	
	
	@Override
	public Response guardarCompetencia(DataCompetencia dataCompetencia) {
		try {
			return Response.ok(icc.guardarCompetencia(dataCompetencia)).build();
	
		} catch (Exception e) {
			e.printStackTrace();
	
		}
		return null;
	}
	
		
	@Override
	public Response listarCompetenciasPorRonda(int tenantID, String sexo, String nombreDeporte, String nombreDisciplina,int ronda) {
		try {
			return Response.ok(icc.listarCompetenciasPorRonda(tenantID,nombreDeporte,sexo,nombreDisciplina,ronda)).build();
	
		} catch (Exception e) {
			e.printStackTrace();
	
		}
		return null;
	}
	
	
	@Override
	public Response obtenerPrecio(int tenantID, int idCompetencia) {
		try {
			return Response.ok(icc.obtenerPrecio(tenantID,idCompetencia)).build();
	
		} catch (Exception e) {
			e.printStackTrace();
	
		}
		return null;
	}
	
	
	@Override
	public Response comprarEntradas(DataCompraEntrada datos) {
		
		try {
			return Response.ok(icc.comprarEntradas(datos)).build();
	
		} catch (Exception e) {
			e.printStackTrace();
	
		}
		return null;
		
	}
	
	
	@Override
	public Response listarCompetenciasPendientes(int tenantID, int juezID) {
		try {						
			return Response.ok(icc.listarCompetenciasPendientes(tenantID,  juezID)).build();	
		} catch (AplicacionException e) {
			return Response.serverError().build();
		}
	}
	

	@Override
	public Response guardarResultado(DataResultado resultado) {
		try {						
			return Response.ok(icc.guardarResultado(resultado)).build();	
		} catch (AplicacionException e) {
			return Response.serverError().build();
		}
	}


	@Override
	public Response listarCompetenciasPorDisciplina(int tenantID, String nombreDeporte, String nombreDisciplina, String sexo) {
		try {						
			return Response.ok(icc.listarCompetenciasPorDisciplina(tenantID,nombreDeporte,nombreDisciplina,sexo)).build();	
		} catch (AplicacionException e) {
			return Response.serverError().build();
		}
	}


	@Override
	public Response listarResultadosCompetencia(int tenantID, int competenciaID) {
		try {
		return Response.ok(icc.listarResultadosCompetencia(tenantID,competenciaID)).build();
		} catch (Exception e) {
			e.printStackTrace();
	
		}
		return null;
		}
	
}

