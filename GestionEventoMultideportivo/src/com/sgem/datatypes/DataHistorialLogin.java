package com.sgem.datatypes;

import java.util.Date;

public class DataHistorialLogin {

	private int tenantId;	
	private Date fecha;
	private String emailUsuario;
	
	public DataHistorialLogin(){}

	public DataHistorialLogin(int tenantId, Date fecha, String emailUsuario) {
		this.tenantId = tenantId;
		this.fecha = fecha;
		this.emailUsuario = emailUsuario;
	}

	public int getTenantId() {
		return tenantId;
	}

	public void setTenantId(int tenantId) {
		this.tenantId = tenantId;
	}

	public String getEmailUsuario() {
		return emailUsuario;
	}

	public void setEmailUsuario(String emailUsuario) {
		this.emailUsuario = emailUsuario;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

		
}
