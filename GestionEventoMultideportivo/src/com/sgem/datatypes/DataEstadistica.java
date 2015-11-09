package com.sgem.datatypes;


public class DataEstadistica {

	private int estadisticaId;
	
	private int tenantId;
	
	private int posicion;
	
	private String participante;
	
	private String datoInformativo;
		
	private int resultadoId;

	public DataEstadistica(){}
	
	public DataEstadistica(int estadisticaId, int tenantId, int posicion,
			String participante, String datoInformativo, int resultadoId) {
		this.estadisticaId = estadisticaId;
		this.tenantId = tenantId;
		this.posicion = posicion;
		this.participante = participante;
		this.datoInformativo = datoInformativo;
		this.resultadoId = resultadoId;
	}

	public int getEstadisticaId() {
		return estadisticaId;
	}

	public void setEstadisticaId(int estadisticaId) {
		this.estadisticaId = estadisticaId;
	}

	public int getTenantId() {
		return tenantId;
	}

	public void setTenantId(int tenantId) {
		this.tenantId = tenantId;
	}

	public int getPosicion() {
		return posicion;
	}

	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}

	public String getParticipante() {
		return participante;
	}

	public void setParticipante(String participante) {
		this.participante = participante;
	}

	public String getDatoInformativo() {
		return datoInformativo;
	}

	public void setDatoInformativo(String datoInformativo) {
		this.datoInformativo = datoInformativo;
	}

	public int getResultadoId() {
		return resultadoId;
	}

	public void setResultadoId(int resultadoId) {
		this.resultadoId = resultadoId;
	}
	
}
