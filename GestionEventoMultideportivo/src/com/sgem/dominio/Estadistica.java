package com.sgem.dominio;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="estadistica")
public class Estadistica implements Serializable{
	
	private static final long serialVersionUID = -6321506554036389023L;

	@Id
	@Column(name = "id", nullable = false)
	private long id;
	
	@ManyToOne(optional=true)
	@JoinColumn(name="Estadistico", nullable = true)
	private Estadistico estadistico;
	
	@Column(name = "Contenido", nullable = false)
	private String contenido;
	
	public Estadistica() {}

	public Estadistica(String contenido) {
		this.contenido = contenido;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
