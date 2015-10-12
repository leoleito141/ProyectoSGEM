package com.sgem.dominio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="tenantHandler")
public class TenantHandler implements Serializable {
	
	private static final long serialVersionUID = -5284790408432089498L;
		
	@OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="tenantHandler")
	private List<EventoMultideportivo> eventos = new ArrayList<EventoMultideportivo>();
	
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int tenantID;

	public TenantHandler(){}

	public List<EventoMultideportivo> getEventos() {
		return eventos;
	}
	
	public void setEventos(List<EventoMultideportivo> eventos) {
		this.eventos = eventos;
	}

	public int getTenantID() {
		return tenantID;
	}
	
	
	
}
