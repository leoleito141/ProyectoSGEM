package com.sgem.dominio;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="admin")
public class Admin extends Usuario {

	private static final long serialVersionUID = -2107719096745478082L;

	public Admin() {}

	public Admin( String email, String facebook, String twitter,String canalYoutube, String nombre, 
			String apellido, Integer edad, Integer cedula, String password) {
		super(email, facebook, twitter, canalYoutube, nombre, apellido, edad,cedula, password);
	}



}
