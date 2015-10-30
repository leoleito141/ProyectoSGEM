package com.sgem.dominio;

import java.io.Serializable;
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
import javax.persistence.Table;

@Entity
@Table(name="estadistica")
public class Estadistica implements Serializable{
	
private static final long serialVersionUID = -6321506554036389023L;
	
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int estadisticaId;
	
	@Column(name = "tenant_ID", nullable = true)
	private int tenantId;
	
	@Column(name = "posicion", nullable = true)
	private int posicion;
	
	@Column(name = "participante", nullable = true)
	private int participante;
	
	@Column(name = "datoInformativo", nullable = true)
	private int datoInformativo;
	
	
	@ManyToOne	
	private Resultado resultado;


	
	
	
	public Estadistica() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Estadistica(int tenantId, int posicion, int participante, int datoInformativo, Resultado resultado) {
		super();
		this.tenantId = tenantId;
		this.posicion = posicion;
		this.participante = participante;
		this.datoInformativo = datoInformativo;
		this.resultado = resultado;
	}


	public int getEstadisticaId() {
		return estadisticaId;
	}


	public void setEstadisticaId(int estadisticaId) {
		this.estadisticaId = estadisticaId;
	}


	public int getTenantId() {
		return tenantId;
	}


	public void setTenantId(int tenantId) {
		this.tenantId = tenantId;
	}


	public int getPosicion() {
		return posicion;
	}


	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}


	public int getParticipante() {
		return participante;
	}


	public void setParticipante(int participante) {
		this.participante = participante;
	}


	public int getDatoInformativo() {
		return datoInformativo;
	}


	public void setDatoInformativo(int datoInformativo) {
		this.datoInformativo = datoInformativo;
	}


	public Resultado getResultado() {
		return resultado;
	}


	public void setResultado(Resultado resultado) {
		this.resultado = resultado;
	}
	
	
	

}