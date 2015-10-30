package com.sgem.dominio;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="UsuarioComun")
public class UsuarioComun extends Usuario {
	
	private static final long serialVersionUID = -5284790408432089498L;
	private static final String TIPO = "UsuarioComun";
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy = "UsuarioComun") 
	private Set<Entrada> entradas= new HashSet<Entrada>();
	
	public UsuarioComun(){
		this.entradas = new HashSet<Entrada>();
	}
	
	
	
	
	public UsuarioComun(Set<Entrada> entradas) {
		super();
		this.entradas = entradas;
	}




	public Set<Entrada> getEntradas() {
		return entradas;
	}




	public void setEntradas(Set<Entrada> entradas) {
		this.entradas = entradas;
	}


	public void addEntrada(Entrada entrada) {
		this.entradas.add(entrada);
		
	}

	@Override
	public  String soy(){			
		return TIPO;
	}
	

}