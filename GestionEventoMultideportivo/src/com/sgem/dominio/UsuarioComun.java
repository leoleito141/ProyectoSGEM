package com.sgem.dominio;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="UsuarioComun")
public class UsuarioComun extends Usuario {
	
	private static final long serialVersionUID = -5284790408432089498L;
	private static final String TIPO = "UsuarioComun";
	
	public UsuarioComun(){}

	@Override
	public  String soy(){			
		return TIPO;
	}


}