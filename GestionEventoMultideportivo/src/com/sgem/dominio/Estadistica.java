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
	private String participante;
	
	@Column(name = "datoInformativo", nullable = true)
	private String datoInformativo;
		
	@ManyToOne	
	private Resultado resultado;

		
	public Estadistica() {}
	
	public Estadistica(int tenantId, int posicion,
			String participante, String datoInformativo, Resultado resultado) {
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

	public String getParticipante() {
		return participante;
	}

	public void setParticipante(String participante) {
		this.participante = participante;
	}

	public String getDatoInformativo() {
		return datoInformativo;
	}

	public void setDatoInformativo(String datoInformativo) {
		this.datoInformativo = datoInformativo;
	}

	public Resultado getResultado() {
		return resultado;
	}

	public void setResultado(Resultado resultado) {
		this.resultado = resultado;
	}

}