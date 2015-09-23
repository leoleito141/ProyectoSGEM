package com.sgem.dominio;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="ComiteOlimpico")
public class ComiteOlimpico extends Usuario{
	
	private static final long serialVersionUID = -5284790408432089498L;
	
	private String pais;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy = "comiteOlimpico") 
	 private Set<Deportista> Deportistas= new HashSet<Deportista>();
	
	public ComiteOlimpico(){}
	public ComiteOlimpico(String email, String facebook, String twitter, String canalYoutube, String nombre, String apellido, Integer edad, Integer cedula, String password,int tenantid) 
	{
		
		super(email, facebook, twitter, canalYoutube, nombre, apellido, edad, cedula, password,tenantid);
				
	}
	public  String soy(){
			
			return"ComiteOlimpico";
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
	public void agregarDeportista(Deportista d){
		
		if (this.Deportistas !=null)
			this.Deportistas= new HashSet<Deportista>();
		
		this.Deportistas.add(d);
		
		
	}
}
