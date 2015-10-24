package com.sgem.datatypes;

public class DataImagen {

	private String mime;
	private String ruta;
	
	public DataImagen(){}
	
	public DataImagen(String mime, String ruta) {
		this.mime = mime;
		this.ruta = ruta;
	}

	public String getMime() {
		return mime;
	}

	public void setMime(String mime) {
		this.mime = mime;
	}

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}
	
}
