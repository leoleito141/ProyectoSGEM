package com.sgem.datatypes;

import java.util.Date;

public class DataEventoDeportivo{
	
	private int tenantId;

	private String nombreDeporte;
	
	private String nombreDisciplina;
	
	private String sexo;
	
	private Date FechaInicio;
	
	private Date FechaFin;
	
	private int cantRondas;
	
	private String tipo;
	
	public String getNombreDeporte() {
		return nombreDeporte;
	}

	public void setNombreDeporte(String nombreDeporte) {
		this.nombreDeporte = nombreDeporte;
	}

	public String getNombreDisciplina() {
		return nombreDisciplina;
	}

	public void setNombreDisciplina(String nombreDisciplina) {
		this.nombreDisciplina = nombreDisciplina;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Date getFechaInicio() {
		return FechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		FechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return FechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		FechaFin = fechaFin;
	}
	
	public int getTenantId() {
		return tenantId;
	}

	public void setTenantId(int tenantId) {
		this.tenantId = tenantId;
	}
	
	public int getCantRondas() {
		return cantRondas;
	}

	public void setCantRondas(int cantRondas) {
		this.cantRondas = cantRondas;
	}
	
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "DataEventoDeportivo [tenantId=" + tenantId + ", nombreDeporte=" + nombreDeporte + ", nombreDisciplina="
				+ nombreDisciplina + ", sexo=" + sexo + ", FechaInicio=" + FechaInicio + ", FechaFin=" + FechaFin
				+ ", cantRondas=" + cantRondas + ", tipo=" + tipo + "]";
	}

	
	
}