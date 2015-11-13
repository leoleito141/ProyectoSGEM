package com.sgem.datatypes;


public class DataEstadistica {

	private int estadisticaId;
	
	private int tenantId;
	
	private int posicion;
	
	private DataDeportista deportista;
	
	private String datoInformativo;
		
	private int resultadoId;

	public DataEstadistica(){}
	
	public DataEstadistica(int estadisticaId, int tenantId, int posicion,
			DataDeportista deportista, String datoInformativo, int resultadoId) {
		this.estadisticaId = estadisticaId;
		this.tenantId = tenantId;
		this.posicion = posicion;
		this.deportista = deportista;
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

	public DataDeportista getDeportista() {
		return deportista;
	}

	public void setDeportista(DataDeportista deportista) {
		this.deportista = deportista;
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
