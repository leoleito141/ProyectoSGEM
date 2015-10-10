package com.sgem.datatypes;

public class DataTenant {

	private int tenantId;
	private String login_back_img;
	private String registro_back_img;

	public DataTenant(int tenantId, String login_back_img,
			String registro_back_img) {
		this.tenantId = tenantId;
		this.login_back_img = login_back_img;
		this.registro_back_img = registro_back_img;
	}

	public int getTenantId() {
		return tenantId;
	}

	public void setTenantId(int tenantId) {
		this.tenantId = tenantId;
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
		return "DataTenant [tenantId=" + tenantId + ", login_back_img="
				+ login_back_img + ", registro_back_img=" + registro_back_img
				+ "]";
	}

}
