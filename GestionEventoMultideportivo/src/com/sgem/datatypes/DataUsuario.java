package com.sgem.datatypes;


public class DataUsuario{
	
//	Ver bien desp si crear un data por rol.
	
//	@Expose(serialize = false, deserialize = false)
//	@SerializedName(value="Email")
	private String email;
	
//	@SerializedName(value="Facebook")
	private String facebook;
	
//	@SerializedName(value="Twitter")
	private String twitter;
	
//	@SerializedName(value="CanalYoutube")
	private String canalYoutube;
	
//	@SerializedName(value="Nombre")
	private String nombre;
	
//	@SerializedName(value="Apellido")
	private String apellido;
	
//	@SerializedName(value="Edad")
	private Integer edad;	
	
//	@SerializedName(value="Cedula")
	private Integer cedula;
	
//	@SerializedName(value="Password")
	private String password;

	private String rol;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getCanalYoutube() {
		return canalYoutube;
	}

	public void setCanalYoutube(String canalYoutube) {
		this.canalYoutube = canalYoutube;
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

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public Integer getCedula() {
		return cedula;
	}

	public void setCedula(Integer cedula) {
		this.cedula = cedula;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	@Override
	public String toString() {
		return "DataUsuario [email=" + email + ", facebook=" + facebook
				+ ", twitter=" + twitter + ", canalYoutube=" + canalYoutube
				+ ", nombre=" + nombre + ", apellido=" + apellido + ", edad="
				+ edad + ", cedula=" + cedula + ", password=" + password
				+ ", rol=" + rol + "]";
	}
	
	
	
}
