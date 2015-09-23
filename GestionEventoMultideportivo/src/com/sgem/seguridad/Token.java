package com.sgem.seguridad;

public class Token {
	private String token;
	private Integer tenantId;

	public Token() {
	}

	public Token(String token, Integer tenantId) {
		this.token = token;
		this.tenantId = tenantId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Integer getTenantId() {
		return tenantId;
	}

	public void setTenantId(Integer tenantId) {
		this.tenantId = tenantId;
	}

}