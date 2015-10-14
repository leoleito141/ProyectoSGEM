package com.sgem.datatypes;

public class DataTenant {

	private int tenantId;
	private String nombre_url;
	private String login_back_img;
	private String registro_back_img;
	
	public DataTenant(){}
	
	public DataTenant(int tenantId, String nombre_url, String login_back_img, String registro_back_img) {
		this.tenantId = tenantId;
		this.nombre_url = nombre_url;
		this.login_back_img = login_back_img;
		this.registro_back_img = registro_back_img;
	}
	public int getTenantId() {
		return tenantId;
	}
	public void setTenantId(int tenantId) {
		this.tenantId = tenantId;
	}
	public String getNombre_url() {
		return nombre_url;
	}
	public void setNombre_url(String nombre_url) {
		this.nombre_url = nombre_url;
	}
	public String getLogin_back_img() {
		return login_back_img;
	}
	public void setLogin_back_img(String login_back_img) {
		this.login_back_img = login_back_img;
	}
	public String getRegistro_back_img() {
		return registro_back_img;
	}
	public void setRegistro_back_img(String registro_back_img) {
		this.registro_back_img = registro_back_img;
	}
	@Override
	public String toString() {
		return "DataTenant [tenantId=" + tenantId + ", nombre_url=" + nombre_url + ", login_back_img=" + login_back_img
				+ ", registro_back_img=" + registro_back_img + "]";
	}

	
	
}
