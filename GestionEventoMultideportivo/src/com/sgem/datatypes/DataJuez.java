package com.sgem.datatypes;

import java.util.Date;
import java.util.List;

public class DataJuez {
	
	
	
	private String email;
	
//	@SerializedName(value="Password")
	private String password;
	
	
//	@SerializedName(value="codigo")
	private String nombre;
	
//	@SerializedName(value="pais")
	private String apellido;
	
	
	private int tenantId;

	
	public DataJuez(){}

	public DataJuez(Integer tenantId, String nombre, String apellido, String password, String email) {
		super();
		this.tenantId = tenantId;
		this.nombre = nombre;
		this.apellido = apellido;
		this.password = password;
		this.email = email;
		
	}

	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
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


	public int getTenantId() {
		return tenantId;
	}


	public void setTenantId(int tenantId) {
		this.tenantId = tenantId;
	}
	
	public String toString() {
		return "DataUsuario [email=" + email + ", nombre=" + nombre
				+ ", tenantId=" + tenantId
				+ ", apellido=" + apellido + "]";
	}

}
