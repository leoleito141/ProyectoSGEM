package com.sgem.dominio;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ComiteOlimpico")
public class ComiteOlimpico extends Usuario {

	private static final long serialVersionUID = -5284790408432089498L;

	@OneToOne(targetEntity = Pais.class, cascade = CascadeType.ALL)
	private Pais pais;

	@Column(name = "cod_comite_olimpico", nullable = false)
	private String codigo;

	@Column(name = "paypal")
	private String paypal;

	@OneToOne(fetch = FetchType.LAZY)
	private Imagen logo;

	@Column(name = "puesto1")
	private int puesto1;

	@Column(name = "puesto2")
	private int puesto2;

	@Column(name = "puesto3")
	private int puesto3;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "comiteOlimpico")
	private Set<Deportista> Deportistas = new HashSet<Deportista>();

	public ComiteOlimpico() {}

	public ComiteOlimpico(Pais pais, String codigo, String paypal, Imagen logo,
			int puesto1, int puesto2, int puesto3, Set<Deportista> deportistas) {
		this.pais = pais;
		this.codigo = codigo;
		this.paypal = paypal;
		this.logo = logo;
		this.puesto1 = puesto1;
		this.puesto2 = puesto2;
		this.puesto3 = puesto3;
		Deportistas = deportistas;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getPaypal() {
		return paypal;
	}

	public void setPaypal(String paypal) {
		this.paypal = paypal;
	}

	public Imagen getLogo() {
		return logo;
	}

	public void setLogo(Imagen logo) {
		this.logo = logo;
	}

	public int getPuesto1() {
		return puesto1;
	}

	public void setPuesto1(int puesto1) {
		this.puesto1 = puesto1;
	}

	public int getPuesto2() {
		return puesto2;
	}

	public void setPuesto2(int puesto2) {
		this.puesto2 = puesto2;
	}

	public int getPuesto3() {
		return puesto3;
	}

	public void setPuesto3(int puesto3) {
		this.puesto3 = puesto3;
	}

	public Set<Deportista> getDeportistas() {
		return Deportistas;
	}

	public void setDeportistas(Set<Deportista> deportistas) {
		Deportistas = deportistas;
	}

	public void agregarDeportista(Deportista d) {

		if (this.Deportistas != null)
			this.Deportistas = new HashSet<Deportista>();

		this.Deportistas.add(d);

	}

	@Override
	public String soy() {
		return "Comite Olimpico";
	}
}
