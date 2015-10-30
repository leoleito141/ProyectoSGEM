package com.sgem.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="juez")
public class Juez extends Usuario {
	
	private static final long serialVersionUID = -5284790408432089498L;
	private static final String TIPO = "JUEZ";
	
	
	@Column(name = "nombre", nullable = false)
	private String nombre;	
	
	@Column(name = "apellido", nullable = false)
	private String apellido;
	
	
	public Juez(){}
	public Juez(String email, String facebook, String twitter, String canalYoutube, String nombre, String apellido, Integer edad, Integer cedula, String password,int tenantid) 
	{
		
		super(email, facebook, twitter, canalYoutube, password,tenantid);
		this.nombre = nombre;
		this.apellido = apellido;
				
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
	@Override
	public  String soy(){			
		return TIPO;
	}


}
