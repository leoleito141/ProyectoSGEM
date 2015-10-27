package com.sgem.dominio;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="imagen")
public class Imagen implements Serializable{

	private static final long serialVersionUID = -3550280516717762121L;

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int imagenID;

	@Column(name = "mime", nullable = false)
	private String mime;

	@Column(name = "ruta", nullable = false)
	private String ruta;
	
	@Column(name = "tenantId", nullable = false)
	private int tenantId;
	
	public Imagen() {}

	public Imagen(String mime, String ruta, int tenantId) {
		this.mime = mime;
		this.ruta = ruta;
		this.tenantId = tenantId;
	}

	public int getImagenID() {
		return imagenID;
	}

	public void setImagenID(int imagenID) {
		this.imagenID = imagenID;
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
