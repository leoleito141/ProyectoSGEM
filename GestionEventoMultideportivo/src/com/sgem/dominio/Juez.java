package com.sgem.dominio;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="juez")
public class Juez extends Usuario {
	
	private static final long serialVersionUID = -5284790408432089498L;
	private static final String TIPO = "JUEZ";
	
	
	@Column(name = "nombre", nullable = false)
	private String nombre;	
	
	@Column(name = "apellido", nullable = false)
	private String apellido;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy = "juez") 
	private Set<Competencia> Competencia= new HashSet<Competencia>();
	
	
	public Juez(){
		
		this.Competencia = new HashSet<Competencia>();
	}
	public Juez(String email, String facebook, String twitter, String canalYoutube, String nombre, String apellido, Integer edad, Integer cedula, String password,int tenantid, Set<Competencia> competencias) 
	{
		
		super(email, facebook, twitter, canalYoutube, password,tenantid);
		this.nombre = nombre;
		this.apellido = apellido;
		this.Competencia = new HashSet<Competencia>();
				
	}
	
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public Set<Competencia> getCompetencia() {
		return Competencia;
	}
	public void setCompetencia(Set<Competencia> competencia) {
		Competencia = competencia;
	}
	
	public void addCompetencia(Competencia comp) {
		this.Competencia.add(comp);
		
	}
	
	@Override
	public  String soy(){			
		return TIPO;
	}


}
