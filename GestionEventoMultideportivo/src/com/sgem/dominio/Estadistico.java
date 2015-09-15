package com.sgem.dominio;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="estadistico")
public class Estadistico extends Usuario {

	private static final long serialVersionUID = -6606497889596182410L;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "estadistico", fetch = FetchType.LAZY)
	private List<Estadistica> estadisticas;

	public Estadistico() {
		estadisticas = new ArrayList<Estadistica>();
	}
	
	public Estadistico(String email, String facebook, String twitter, String canalYoutube, String nombre,
			String apellido, Integer edad, Integer cedula, String password) {
		super( email, facebook, twitter, canalYoutube, nombre, apellido, edad, cedula, password);	
		
		estadisticas = new ArrayList<Estadistica>();	
	}
	
	public Estadistico(String email, String facebook, String twitter, String canalYoutube, String nombre,
			String apellido, Integer edad, Integer cedula, String password,List<Estadistica> estadisticas) {
		super( email, facebook, twitter, canalYoutube, nombre, apellido, edad,
				cedula, password);	
		
		this.estadisticas = estadisticas;	
	
	}
	
}
