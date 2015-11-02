package com.sgem.datatypes;

public class DataComite {

	private String email;

	private String password;

	private String codigo;

	private String pais;

	private String facebook;

	private String twitter;
	
	private String tipoUsuario;

	public DataComite() {}

	public DataComite(String email, String password, String codigo, String pais, String facebook, String twitter,
			int tenantId) {
		super();
		this.email = email;
		this.password = password;
		this.codigo = codigo;
		this.pais = pais;
		this.facebook = facebook;
		this.twitter = twitter;
		this.tenantId = tenantId;
	}

	private int tenantId;

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

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public String getTwitter() {
		return twitter;
	}

	public void setTwitter(String twitter) {
		this.twitter = twitter;
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

	@Override
	public String toString() {
		return "DataComite [email=" + email + ", password=" + password + ", codigo=" + codigo + ", pais=" + pais
				+ ", facebook=" + facebook + ", twitter=" + twitter + ", tipoUsuario=" + tipoUsuario + ", tenantId="
				+ tenantId + "]";
	}

}