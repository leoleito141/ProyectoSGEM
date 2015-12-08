package com.sgem.dominio;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class HistorialLogin {

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idHistorialLogin;

	@Column(name = "tenantId", nullable = false)
	private int tenantId;
	
	@Column(name = "fecha", nullable = false)
	private Date fecha;
	
	@OneToOne
	private Usuario usuario;
	public HistorialLogin(){}
	
//	public HistorialLogin(int tenantId, Date date, Usuario usuario) {
//		this.tenantId = tenantId;
//		this.fecha = date;
//		this.usuario = usuario;
//	}
	
	public HistorialLogin(int tenantId, Date fecha, Usuario usuario) {
		super();
		this.tenantId = tenantId;
		this.fecha = fecha;
		this.usuario = usuario;
	}

	public int getIdHistorialLogin() {
		return idHistorialLogin;
	}

	public void setIdHistorialLogin(int idHistorialLogin) {
		this.idHistorialLogin = idHistorialLogin;
	}

	public int getTenantId() {
		return tenantId;
	}

	public void setTenantId(int tenantId) {
		this.tenantId = tenantId;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
