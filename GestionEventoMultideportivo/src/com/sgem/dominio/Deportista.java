package com.sgem.dominio;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Deportista")
public class Deportista implements Serializable{
	
	private static final long serialVersionUID = -2107719096745478082L;
	
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int deportistaID;
	
	@Column(name = "TenantID", nullable = false)
	private int tenantID;
	
	@Column(name = "Nombre", nullable = false)
	private String nombre;
	
	@Column(name = "FechaNac", nullable = false)
	private String fechaNac;
	
	@Column(name = "Sexo", nullable = false)
	private String Sexo;
	
	@ManyToOne	
	private ComiteOlimpico comiteOlimpico;
	
	public Deportista(){}

	public Deportista(int deportistaID, int tenantID, String nombre,
			String fechaNac, String sexo) {
		
		this.deportistaID = deportistaID;
		this.tenantID = tenantID;
		this.nombre = nombre;
		this.fechaNac = fechaNac;
		this.Sexo = sexo;
	}

	public int getDeportistaID() {
		return deportistaID;
	}

	public void setDeportistaID(int deportistaID) {
		this.deportistaID = deportistaID;
	}

	public int getTenantID() {
		return tenantID;
	}

	public void setTenantID(int tenantID) {
		this.tenantID = tenantID;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(String fechaNac) {
		this.fechaNac = fechaNac;
	}

	public String getSexo() {
		return Sexo;
	}

	public void setSexo(String sexo) {
		Sexo = sexo;
	}

	public ComiteOlimpico getComiteOlimpico() {
		return comiteOlimpico;
	}

	public void setComiteOlimpico(ComiteOlimpico comiteOlimpico) {
		this.comiteOlimpico = comiteOlimpico;
	}
	
	
	
}
