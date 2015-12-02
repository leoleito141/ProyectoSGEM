package com.sgem.datatypes;

public class DataComite {

	private String email;

	private String password;

	private String codigo;

	private DataPais pais;

	private String facebook;

	private String twitter;
	
	private String paypal;
	
	private String tipoUsuario;
	
	private DataImagen logo;
	
	private int tenantId;
	private int comiteId;
	
	private int puesto1;	
	private int puesto2;	
	private int puesto3;

	public DataComite() {}

	public DataComite(String email, String password, String codigo,
			DataPais pais, String facebook, String twitter, String paypal,int tenantId,int comiteId, String tipoUsuario,
			int puesto1, int puesto2, int puesto3) {
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
		this.puesto1 = puesto1;
		this.puesto2 = puesto2;
		this.puesto3 = puesto3;
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

	public DataPais getPais() {
		return pais;
	}

	public void setPais(DataPais pais) {
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

	public int getPuesto1() {
		return puesto1;
	}

	public void setPuesto1(int puesto1) {
		this.puesto1 = puesto1;
	}

	public int getPuesto2() {
		return puesto2;
	}

	public void setPuesto2(int puesto2) {
		this.puesto2 = puesto2;
	}

	public int getPuesto3() {
		return puesto3;
	}

	public void setPuesto3(int puesto3) {
		this.puesto3 = puesto3;
	}
	
}