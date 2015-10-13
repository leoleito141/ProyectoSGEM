package com.sgem.dominio;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="organizador")
public class Organizador extends Usuario {

	private static final long serialVersionUID = -5284790408432089498L;

	@OneToOne(targetEntity=EventoMultideportivo.class)
	private EventoMultideportivo evento;
	
	public Organizador() {}

	public Organizador( String email, String facebook, String twitter, String canalYoutube, String nombre, 
			String apellido, Integer edad, Integer cedula, String password,int tenantid) {
		super(email, facebook, twitter, canalYoutube, password,tenantid);
	}
	
	
	public EventoMultideportivo getEvento() {
		return evento;
	}

	public void setEvento(EventoMultideportivo evento) {
		this.evento = evento;
	}

public  String soy(){
		
		return"organizador";
	}
}
