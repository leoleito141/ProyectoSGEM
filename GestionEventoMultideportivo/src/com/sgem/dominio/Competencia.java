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
@Table(name="competencia")
public class Competencia implements Serializable{
	
	private static final long serialVersionUID = -6321506554036389023L;
	
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int CompetenciaId;
	
	@Column(name = "tenant_ID", nullable = true)
	private int tenantId;
	
	
	
	@ManyToOne	
	private EventoDeportivo eventoDeportivo;




	public int getTenantId() {
		return tenantId;
	}



	public void setTenantId(int tenantId) {
		this.tenantId = tenantId;
	}



	public EventoDeportivo getEventoDeportivo() {
		return eventoDeportivo;
	}



	public void setEventoDeportivo(EventoDeportivo eventoDeportivo) {
		this.eventoDeportivo = eventoDeportivo;
	}


	




	
}
