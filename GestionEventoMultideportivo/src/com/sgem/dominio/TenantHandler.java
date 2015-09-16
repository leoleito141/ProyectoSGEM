package com.sgem.dominio;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="tenantHandler")
public class TenantHandler implements Serializable {
	
	private static final long serialVersionUID = -5284790408432089498L;
	
	private String evento;
	
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int tenantID;

	public TenantHandler(){}
	public TenantHandler(String evento,int tenantid){
		this.evento= evento ;
		this.tenantID=tenantid;
		
	}
	
	public String getEvento() {
		return evento;
	}
	public void setEvento(String evento) {
		this.evento = evento;
	}
	public int getTenantID() {
		return tenantID;
	}
	public void setTenantID(int tenantID) {
		this.tenantID = tenantID;
	}
	
	
}
