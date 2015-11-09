package com.sgem.dominio;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="ronda")
public class Ronda implements Serializable{
	
	private static final long serialVersionUID = -6321506554036389023L;
	
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int rondaId;
	
	@Column(name = "tenant_ID", nullable = true)
	private int tenantId;
	
//	@Column(name = "EventoDepId", nullable = true)
//	private int EventoDepId;
	
	@Column(name = "numeroRonda", nullable = true)
	private int numeroRonda;
	
	@ManyToOne	
	private EventoDeportivo eventoDeportivo;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy = "ronda") 
	private Set<Competencia> Competencia= new HashSet<Competencia>();

	public Ronda(){}
	
	public Ronda(int tenantId, int numeroRonda,
			EventoDeportivo eventoDeportivo,
			Set<com.sgem.dominio.Competencia> competencia) {
		this.tenantId = tenantId;
//		this.EventoDepId = eventoDepId;
		this.numeroRonda = numeroRonda;
		this.eventoDeportivo = eventoDeportivo;
		this.Competencia = competencia;
	}

	public int getRondaId() {
		return rondaId;
	}

	public void setRondaId(int rondaId) {
		this.rondaId = rondaId;
	}

	public int getTenantId() {
		return tenantId;
	}

	public void setTenantId(int tenantId) {
		this.tenantId = tenantId;
	}

//	public int getEventoDepId() {
//		return EventoDepId;
//	}
//
//	public void setEventoDepId(int eventoDepId) {
//		EventoDepId = eventoDepId;
//	}

	public int getNumeroRonda() {
		return numeroRonda;
	}

	public void setNumeroRonda(int numeroRonda) {
		this.numeroRonda = numeroRonda;
	}

	public EventoDeportivo getEventoDeportivo() {
		return eventoDeportivo;
	}

	public void setEventoDeportivo(EventoDeportivo eventoDeportivo) {
		this.eventoDeportivo = eventoDeportivo;
	}

	public Set<Competencia> getCompetencia() {
		return Competencia;
	}

	public void setCompetencia(Set<Competencia> competencia) {
		Competencia = competencia;
	}
	
}

	