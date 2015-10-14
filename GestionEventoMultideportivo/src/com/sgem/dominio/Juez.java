package com.sgem.dominio;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="juez")
public class Juez extends Usuario {
	
	private static final long serialVersionUID = -5284790408432089498L;
	private static final String TIPO = "JUEZ";
	
	public Juez(){}
	public Juez(String email, String facebook, String twitter, String canalYoutube, String nombre, String apellido, Integer edad, Integer cedula, String password,int tenantid) 
	{
		
		super(email, facebook, twitter, canalYoutube, password,tenantid);
				
	}
	
	@Override
	public  String soy(){			
		return TIPO;
	}


}
