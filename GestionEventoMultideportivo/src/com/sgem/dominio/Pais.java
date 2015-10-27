package com.sgem.dominio;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="pais")
public class Pais implements Serializable {
	

	private static final long serialVersionUID = 5270460734941459893L;

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int paisID;
	
	@Column(name = "pais", nullable = false)
	private String pais;

	@Column(name = "ciudad")
	private String ciudad;
	
	@OneToOne(targetEntity=EventoMultideportivo.class,mappedBy="pais",cascade = CascadeType.ALL)
	private EventoMultideportivo evento;
	
	public Pais(){}
	
	public Pais(String pais,String ciudad){
		this.pais=pais;
		this.ciudad=ciudad;
	}

	public int getPaisID() {
		return paisID;
	}

	public void setPaisID(int paisID) {
		this.paisID = paisID;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	
	
	

}
