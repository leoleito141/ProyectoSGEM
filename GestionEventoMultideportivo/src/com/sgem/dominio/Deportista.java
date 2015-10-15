package com.sgem.dominio;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
	
	@Column(name = "Apellido", nullable = false)
	private String apellido;
	
	@Column(name = "FechaNac", nullable = false)
	private Date fechaNac;
	
	@Column(name = "Sexo", nullable = false)
	private String Sexo;
	
	@ManyToOne	
	private ComiteOlimpico comiteOlimpico;
	
	@ManyToMany( fetch = FetchType.EAGER)
	private Set <EventoDeportivo> eventoDep;
	
	/*
	@ManyToMany
	@JoinTable(
	      name="Depor-EventoDep",
	      joinColumns={@JoinColumn(name="DepId", referencedColumnName="deportistaID")},
	      inverseJoinColumns={@JoinColumn(name="EventoDepId", referencedColumnName="EventoDepId")})
	  private Set <EventoDeportivo> eventoDep;
	
	*/
	public Deportista(){}

	public Deportista(int tenantID, String nombre, String apellido,
			Date fechaNac, String sexo, Set <EventoDeportivo> eventoDep, ComiteOlimpico comiteOlimpico ) {
	//	super ();
		
		this.tenantID = tenantID;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNac = fechaNac;
		this.Sexo = sexo;
		this.comiteOlimpico = comiteOlimpico;
		this.eventoDep = eventoDep;
		
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

	public Date getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(Date fechaNac) {
		this.fechaNac = fechaNac;
	}

	public String getSexo() {
		return Sexo;
	}

	public void setSexo(String sexo) {
		Sexo = sexo;
	}
	
	

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public ComiteOlimpico getComiteOlimpico() {
		return comiteOlimpico;
	}

	public void setComiteOlimpico(ComiteOlimpico comiteOlimpico) {
		this.comiteOlimpico = comiteOlimpico;
	}

	public Set<EventoDeportivo> getEventoDep() {
		return eventoDep;
	}

	public void setEventoDep(Set<EventoDeportivo> eventoDep) {
		this.eventoDep = eventoDep;
	}
	
	public void addEventoDeportivo(EventoDeportivo edep) {
		this.eventoDep.add(edep);
		
	}

}
