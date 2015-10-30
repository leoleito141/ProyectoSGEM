package com.sgem.servicios;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.Response;

import com.sgem.controladores.ICompetenciaController;
import com.sgem.datatypes.DataCompetencia;



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

	
	






}

