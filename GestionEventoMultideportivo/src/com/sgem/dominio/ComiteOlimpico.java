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
@Table(name="ComiteOlimpico")
public class ComiteOlimpico extends Usuario{
	
	private static final long serialVersionUID = -5284790408432089498L;
	
	@Column(name = "pais", nullable = false)
	private String pais;	
	
	@Column(name = "cod_comite_olimpico", nullable = false)
	private String codigo;
	
	@OneToOne(fetch=FetchType.LAZY)
	private Imagen logo;	
		
	@OneToMany(fetch = FetchType.EAGER,mappedBy = "comiteOlimpico") 
	 private Set<Deportista> Deportistas= new HashSet<Deportista>();
	
	public ComiteOlimpico(){}
	public ComiteOlimpico(String email, String facebook, String codigo, String pais,String twitter, String canalYoutube,String password,int tenantid,Imagen logo) 
	{		
		super(email, facebook, twitter, canalYoutube, password,tenantid);
		this.codigo = codigo;
		this.pais = pais;
		this.logo = logo;
	}

	public String soy() {

		return "ComiteOlimpico";
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public Set<Deportista> getDeportistas() {
		return Deportistas;
	}

	public void setDeportistas(Set<Deportista> deportistas) {
		Deportistas = deportistas;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public Imagen getLogo() {
		return logo;
	}
	public void setLogo(Imagen logo) {
		this.logo = logo;
	}
	public void agregarDeportista(Deportista d) {

		if (this.Deportistas != null)
			this.Deportistas = new HashSet<Deportista>();

		this.Deportistas.add(d);

	}
}
