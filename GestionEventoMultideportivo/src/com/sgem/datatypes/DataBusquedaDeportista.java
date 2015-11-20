package com.sgem.datatypes;


import java.util.List;

public class DataBusquedaDeportista {
	
	private int tenantId;
	
	private String nombreDeportista;
	
	private String deporte;
	
	private String disciplina;
	
	private String pais;
	
	private String sexo;

	private List<String> nombreDeporte;
	
	private List<String> nombreDisciplina;
	
	private List<String> nombrePais;
	
	
	
	public DataBusquedaDeportista() {}
	
	public DataBusquedaDeportista(int tenantId,String nombreDeportista,String deporte,String disciplina,String pais,String sexo,List<String> nombreDeporte,List<String> nombreDisciplina,List<String> nombrePais) {
		
		this.tenantId = tenantId;
		this.nombreDeportista = nombreDeportista;
		this.deporte = deporte;
		this.disciplina = disciplina;
		this.pais = pais;
		this.sexo = sexo;
		this.nombreDeporte = nombreDeporte;
		this.nombreDisciplina = nombreDisciplina;
		this.nombrePais = nombrePais;
		
		
	}
	
	public String getNombreDeportista() {
		return nombreDeportista;
	}

	public void setNombreDeportista(String nombreDeportista) {
		this.nombreDeportista = nombreDeportista;
	}

	public String getDeporte() {
		return deporte;
	}

	public void setDeporte(String deporte) {
		this.deporte = deporte;
	}

	public String getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(String disciplina) {
		this.disciplina = disciplina;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public List<String> getNombrePais() {
		return nombrePais;
	}

	public void setNombrePais(List<String> nombrePais) {
		this.nombrePais = nombrePais;
	}

	public int getTenantId() {
		return tenantId;
	}

	public void setTenantId(int tenantId) {
		this.tenantId = tenantId;
	}	
	
	public List<String> getNombreDeporte() {
		return nombreDeporte;
	}

	public void setNombreDeporte(List<String> nombreDeporte) {
		this.nombreDeporte = nombreDeporte;
	}

	public List<String> getNombreDisciplina() {
		return nombreDisciplina;
	}

	public void setNombreDisciplina(List<String> nombreDisciplina) {
		this.nombreDisciplina = nombreDisciplina;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}




}
