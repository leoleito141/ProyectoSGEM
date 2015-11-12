package com.sgem.datatypes;

public class DataComite {

	private String email;

	private String password;

	private String codigo;

	private String pais;

	private String facebook;

	private String twitter;
	
	private String paypal;
	
	private String tipoUsuario;
	
	private DataImagen logo;
	
	private int tenantId;
	private int comiteId;

	public DataComite() {}

	public DataComite(String email, String password, String codigo,
			String pais, String facebook, String twitter, String paypal,int tenantId,int comiteId, String tipoUsuario) {
		super();
		this.email = email;
		this.password = password;
		this.codigo = codigo;
		this.pais = pais;
		this.facebook = facebook;
		this.twitter = twitter;
		this.tenantId = tenantId;
		this.comiteId = comiteId;
		this.tipoUsuario = tipoUsuario;
		this.paypal = paypal;
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

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public DataImagen getLogo() {
		return logo;
	}

	public void setLogo(DataImagen logo) {
		this.logo = logo;
	}

	public int getTenantId() {
		return tenantId;
	}

	public void setTenantId(int tenantId) {
		this.tenantId = tenantId;
	}

	public int getComiteId() {
		return comiteId;
	}

	public void setComiteId(int comiteId) {
		this.comiteId = comiteId;
	}

	public String getPaypal() {
		return paypal;
	}

	public void setPaypal(String paypal) {
		this.paypal = paypal;
	}
	
}