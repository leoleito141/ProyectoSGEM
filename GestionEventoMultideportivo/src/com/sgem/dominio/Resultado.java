package com.sgem.dominio;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="resultado")
public class Resultado implements Serializable{
	
private static final long serialVersionUID = -6321506554036389023L;
	
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int resultadoId;
	
	@Column(name = "tenant_ID", nullable = true)
	private int tenantId;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy = "resultado") 
	private Set<Estadistica> Estadisticas= new HashSet<Estadistica>();
	
	@OneToOne(targetEntity=Competencia.class,cascade = CascadeType.ALL)
	private Competencia competencia;
	
	
	
	

	public Resultado() {
		this.Estadisticas = new HashSet<Estadistica>();
		
	}

	public Resultado(int tenantId, Set<Estadistica> estadisticas, Competencia competencia) {
		super();
		this.tenantId = tenantId;
		this.Estadisticas = new HashSet<Estadistica>();
		this.competencia = competencia;
	}

	public int getResultadoId() {
		return resultadoId;
	}

	public void setResultadoId(int resultadoId) {
		this.resultadoId = resultadoId;
	}

	public int getTenantId() {
		return tenantId;
	}

	public void setTenantId(int tenantId) {
		this.tenantId = tenantId;
	}

	public Set<Estadistica> getEstadisticas() {
		return Estadisticas;
	}

	public void setEstadisticas(Set<Estadistica> estadisticas) {
		Estadisticas = estadisticas;
	}

	public void addEstadistica(Estadistica estadistica) {
		this.Estadisticas.add(estadistica);
		
	}
	
	public Competencia getCompetencia() {
		return competencia;
	}

	public void setCompetencia(Competencia competencia) {
		this.competencia = competencia;
	}
	
	
}
