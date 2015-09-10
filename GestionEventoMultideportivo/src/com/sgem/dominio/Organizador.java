package com.sgem.dominio;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("ORGANIZADOR")
@Table(name="organizador")
public class Organizador extends Usuario {

	private static final long serialVersionUID = -5284790408432089498L;

	public Organizador() {}

	public Organizador( String email, String facebook, String twitter, String canalYoutube, String nombre, 
			String apellido, Integer edad, Integer cedula, String password) {
		super(email, facebook, twitter, canalYoutube, nombre, apellido, edad, cedula, password);
	}

}
