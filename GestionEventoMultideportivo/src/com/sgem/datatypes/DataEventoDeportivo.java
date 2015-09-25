package com.sgem.datatypes;

import java.util.Date;

public class DataEventoDeportivo{
	

//	@SerializedName(value="nombreDeporte")
	private String nombreDeporte;
	
//	@SerializedName(value="nombreDisciplina")
	private String nombreDisciplina;
	
//	@SerializedName(value="sexo")
	private String sexo;
	
//	@SerializedName(value="FechaInicio")
	private Date FechaInicio;
	
//	@SerializedName(value="FechaFin")
	private Date FechaFin;

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
	
	public String toString() {
		return "DataUsuario [nombreDeporte=" + nombreDeporte + ", nombreDisciplina=" + nombreDisciplina
				+ ", sexo=" + sexo + ", FechaInicio=" + FechaInicio
				+ ", FechaFin=" + FechaFin +  "]";
	}
	
}