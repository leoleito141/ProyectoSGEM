package com.sgem.datatypes;


public class DataCompraEntrada {
	
	private int tenantId;
	
	private int idCompetencia;
	
	private int cantEntradas;
	
	private String mail;

	
	
	public DataCompraEntrada() {}



	public DataCompraEntrada(int tenantId, int idCompetencia, int cantEntradas, String mail) {
		super();
		this.tenantId = tenantId;
		this.idCompetencia = idCompetencia;
		this.cantEntradas = cantEntradas;
		this.mail = mail;
	}



	public int getTenantId() {
		return tenantId;
	}



	public void setTenantId(int tenantId) {
		this.tenantId = tenantId;
	}



	public int getIdCompetencia() {
		return idCompetencia;
	}



	public void setIdCompetencia(int idCompetencia) {
		this.idCompetencia = idCompetencia;
	}



	public int getCantEntradas() {
		return cantEntradas;
	}



	public void setCantEntradas(int cantEntradas) {
		this.cantEntradas = cantEntradas;
	}



	public String getMail() {
		return mail;
	}



	public void setMail(String mail) {
		this.mail = mail;
	}



	@Override
	public String toString() {
		return "DataCompraEntrada [tenantId=" + tenantId + ", idCompetencia=" + idCompetencia + ", cantEntradas="
				+ cantEntradas + ", mail=" + mail + "]";
	}
	
	
	

}
