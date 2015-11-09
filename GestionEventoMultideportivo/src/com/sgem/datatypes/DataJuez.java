package com.sgem.datatypes;


public class DataJuez {	
		
	private String email;
	
	private String password;
	
	private String nombre;
	
	private String apellido;
	
	private String tipoUsuario;
	
	private int tenantId;
	
	private int usuarioId;

	public DataJuez(){}

	public DataJuez(Integer tenantId, String nombre, String apellido, String password, String email,String tipoUsuario, int usuarioId) {
		super();
		this.tenantId = tenantId;
		this.nombre = nombre;
		this.apellido = apellido;
		this.password = password;
		this.email = email;
		this.tipoUsuario = tipoUsuario;
		this.usuarioId = usuarioId;
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

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public int getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(int usuarioId) {
		this.usuarioId = usuarioId;
	}
	
	

}
