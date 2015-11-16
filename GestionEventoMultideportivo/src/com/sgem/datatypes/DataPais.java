package com.sgem.datatypes;

public class DataPais {
	private int paisID;
	private String pais;
	private String ciudad;
	
	public DataPais(){}
	
	public DataPais(int paisID,String pais, String ciudad){
		this.paisID = paisID;
		this.pais=pais;
		this.ciudad= ciudad;		
	}
	
	public int getPaisID() {
		return paisID;
	}

	public void setPaisID(int paisID) {
		this.paisID = paisID;
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
