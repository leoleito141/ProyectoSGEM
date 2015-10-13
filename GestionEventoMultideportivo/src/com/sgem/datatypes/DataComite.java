package com.sgem.datatypes;


public class DataComite{
	
//	Ver bien desp si crear un data por rol.
	
//	@Expose(serialize = false, deserialize = false)
//	@SerializedName(value="Email")
	private String email;
	
//	@SerializedName(value="Password")
	private String password;
	
	
//	@SerializedName(value="codigo")
	private String codigo;
	
//	@SerializedName(value="pais")
	private String pais;
	
	
//	@SerializedName(value="Facebook")
	private String facebook;
	
//	@SerializedName(value="Twitter")
	private String twitter;

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
	
	public String toString() {
		return "DataUsuario [email=" + email + ", facebook=" + facebook
				+ ", twitter=" + twitter + ", pais=" + pais
				+ ", codigo=" + codigo + ",  password=" + password
				+  "]";
	}
	
}