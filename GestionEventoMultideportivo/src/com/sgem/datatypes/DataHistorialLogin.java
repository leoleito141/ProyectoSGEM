package com.sgem.datatypes;


public class DataHistorialLogin {

	private int tenantId;		
	private int mes;
	private int anio;
	private int cant_comunes;
	private int cant_comites;
	private int cant_jueces;
	
	public DataHistorialLogin(){}

	public DataHistorialLogin(int tenantId, int mes, int anio,
			int cant_comunes, int cant_comites, int cant_jueces) {
		super();
		this.tenantId = tenantId;
		this.mes = mes;
		this.anio = anio;
		this.cant_comunes = cant_comunes;
		this.cant_comites = cant_comites;
		this.cant_jueces = cant_jueces;
	}

	public int getTenantId() {
		return tenantId;
	}

	public void setTenantId(int tenantId) {
		this.tenantId = tenantId;
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	public int getCant_comunes() {
		return cant_comunes;
	}

	public void setCant_comunes(int cant_comunes) {
		this.cant_comunes = cant_comunes;
	}

	public int getCant_comites() {
		return cant_comites;
	}

	public void setCant_comites(int cant_comites) {
		this.cant_comites = cant_comites;
	}

	public int getCant_jueces() {
		return cant_jueces;
	}

	public void setCant_jueces(int cant_jueces) {
		this.cant_jueces = cant_jueces;
	}

}
