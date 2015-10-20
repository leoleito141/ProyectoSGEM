package com.sgem.datatypes;

import java.util.Date;
import java.util.List;


public class DataDeportista {

	private Integer tenantId;

	private String nombre;

	private String apellido;

    private String sexo;
    
    private Date fechaNac;
    
    private String pais;
    
    private String deporte;
    
    private List<String> disciplinas;
    

	public DataDeportista(){}

	
	
	
	
	
	

	public DataDeportista(Integer tenantId, String nombre, String apellido, String sexo, Date fechaNac, String pais,
			String deporte, List<String> disciplinas) {
		super();
		this.tenantId = tenantId;
		this.nombre = nombre;
		this.apellido = apellido;
		this.sexo = sexo;
		this.fechaNac = fechaNac;
		this.pais = pais;
		this.deporte = deporte;
		this.disciplinas = disciplinas;
	}








	public Integer getTenantId() {
		return tenantId;
	}


	public void setTenantId(Integer tenantId) {
		this.tenantId = tenantId;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellido() {
		return apellido;
	}


	public void setApellido(String apellido) {
		this.apellido = apellido;
	}


	public String getSexo() {
		return sexo;
	}


	public void setSexo(String sexo) {
		this.sexo = sexo;
	}


	public Date getFechaNac() {
		return fechaNac;
	}


	public void setFechaNac(Date fechaNac) {
		this.fechaNac = fechaNac;
	}


	public String getPais() {
		return pais;
	}


	public void setPais(String pais) {
		this.pais = pais;
	}


	public String getDeporte() {
		return deporte;
	}


	public void setDeporte(String deporte) {
		this.deporte = deporte;
	}


	public List<String> getDisciplinas() {
		return disciplinas;
	}


	public void setDisciplinas(List<String> disciplinas) {
		this.disciplinas = disciplinas;
	}
	
	public String toString() {
		return "DataDeportista [deporte=" + deporte + ", disciplina=" + disciplinas
				+ ", tenantId=" + tenantId
				+ ", sexo=" + sexo + ", fechaNac=" + fechaNac
				+ ", nombre=" + nombre + ",  apellido=" + apellido + ",  pais=" + pais + "]";
	}
	
	
}