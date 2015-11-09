package com.sgem.datatypes;

import java.util.ArrayList;
import java.util.List;

public class DataResultado {

	private int resultadoId;
	
	private int tenantId;
	
	private List<DataEstadistica> estadisticas;
	
	private DataCompetencia competencia;

	public DataResultado(){
		this.estadisticas = new ArrayList<DataEstadistica>();
	}
	
	public DataResultado(int resultadoId, int tenantId,
			List<DataEstadistica> estadisticas, DataCompetencia competencia) {
		this.resultadoId = resultadoId;
		this.tenantId = tenantId;
		this.estadisticas = estadisticas;
		this.competencia = competencia;
	}

	public int getResultadoId() {
		return resultadoId;
	}

	public void setResultadoId(int resultadoId) {
		this.resultadoId = resultadoId;
	}

	public int getTenantId() {
		return tenantId;
	}

	public void setTenantId(int tenantId) {
		this.tenantId = tenantId;
	}

	public List<DataEstadistica> getEstadisticas() {
		return estadisticas;
	}

	public void setEstadisticas(List<DataEstadistica> estadisticas) {
		this.estadisticas = estadisticas;
	}

	public DataCompetencia getCompetencia() {
		return competencia;
	}

	public void setCompetencia(DataCompetencia competencia) {
		this.competencia = competencia;
	}
		
}
