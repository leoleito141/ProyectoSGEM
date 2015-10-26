package com.sgem.datatypes;

public class DataImagen {

	private String mime;
	private String ruta;
	private int tenantId;
	
	public DataImagen(){}
	
	public DataImagen(String mime, String ruta, int tenantId) {
		this.mime = mime;
		this.ruta = ruta;
		this.tenantId = tenantId;
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

	public int getTenantId() {
		return tenantId;
	}

	public void setTenantId(int tenantId) {
		this.tenantId = tenantId;
	}
	
}
