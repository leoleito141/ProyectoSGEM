package com.sgem.datatypes;

public class DataPais {
	private String pais;
	private String ciudad;
	
	public DataPais(){}
	
	public DataPais(String pais, String ciudad){
		this.pais=pais;
		this.ciudad= ciudad;		
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	
	public String toString() {
		return "DataPais [pais=" + pais + ", ciudad=" + ciudad + "]";
	}

}
