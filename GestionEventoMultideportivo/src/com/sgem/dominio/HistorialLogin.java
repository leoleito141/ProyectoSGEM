package com.sgem.dominio;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.sgem.enums.Tipo;

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
	
	private Tipo tipo;

	public HistorialLogin(){}
	
//	public HistorialLogin(int tenantId, Date date, Usuario usuario) {
//		this.tenantId = tenantId;
//		this.fecha = date;
//		this.usuario = usuario;
//	}
	
	public HistorialLogin(int tenantId, Date fecha, Usuario usuario, Tipo tipo) {
		super();
		this.tenantId = tenantId;
		this.fecha = fecha;
		this.usuario = usuario;
		this.tipo = tipo;
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

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

}
