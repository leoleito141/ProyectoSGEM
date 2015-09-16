package com.sgem.dominio;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="organizador")
public class Organizador extends Usuario {

	private static final long serialVersionUID = -5284790408432089498L;

	public Organizador() {}

	public Organizador( String email, String facebook, String twitter, String canalYoutube, String nombre, 
			String apellido, Integer edad, Integer cedula, String password,int tenantid) {
		super(email, facebook, twitter, canalYoutube, nombre, apellido, edad, cedula, password,tenantid);
	}
public  String soy(){
		
		return"organizador";
	}
}
