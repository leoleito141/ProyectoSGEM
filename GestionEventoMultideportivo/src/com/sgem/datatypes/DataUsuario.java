package com.sgem.datatypes;

public class DataUsuario {

	private Integer tenantId;
	
	private int usuarioId;

	private String email;

	private String facebook;

	private String twitter;

	private String canalYoutube;

	private String nombre;

	private String apellido;

	private Integer edad;

	private Integer cedula;

	private String password;

	private String tipoUsuario;

	public DataUsuario(){}
	
	public DataUsuario(Integer tenantId, String email, String facebook,
			String twitter, String canalYoutube, String nombre,
			String apellido, Integer edad, Integer cedula, String password,
			String tipoUsuario) {
		this.tenantId = tenantId;
		this.email = email;
		this.facebook = facebook;
		this.twitter = twitter;
		this.canalYoutube = canalYoutube;
		this.nombre = nombre;
		this.apellido = apellido;
		this.edad = edad;
		this.cedula = cedula;
		this.password = password;
		this.tipoUsuario = tipoUsuario;
	}

	public Integer getTenantId() {
		return tenantId;
	}

	public void setTenantId(Integer tenantId) {
		this.tenantId = tenantId;
	}

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

	@Override
	public String toString() {
		return "DataUsuario [tenantId=" + tenantId + ", email=" + email
				+ ", facebook=" + facebook + ", twitter=" + twitter
				+ ", canalYoutube=" + canalYoutube + ", nombre=" + nombre
				+ ", apellido=" + apellido + ", edad=" + edad + ", cedula="
				+ cedula + ", password=" + password + ", tipoUsuario="
				+ tipoUsuario + "]";
	}

}
