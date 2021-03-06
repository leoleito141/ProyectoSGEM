package com.sgem.datatypes;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class DataDeportista {

	private Integer tenantId;

	private Integer deportistaID;

	private String nombre;

	private String apellido;

	private String sexo;

	private Date fechaNac;

	private DataComite comite;
	
	private Set<DataCompetencia> listcompetencias;

	private Set<DataEstadistica> listestadisticas;

	private Set<DataEventoDeportivo> listeventodeportivo;

	private DataImagen foto;

	public DataDeportista() {
		
		this.listcompetencias = new HashSet<DataCompetencia>();

		this.listestadisticas = new HashSet<DataEstadistica>();

		this.listeventodeportivo = new HashSet<DataEventoDeportivo>();

	}

	public DataDeportista(Integer tenantId, Integer deportistaID,
			String nombre, String apellido, String sexo, Date fechaNac,
			DataComite comite, DataImagen foto) {
		
		this.tenantId = tenantId;
		this.deportistaID = deportistaID;
		this.nombre = nombre;
		this.apellido = apellido;
		this.sexo = sexo;
		this.fechaNac = fechaNac;
		this.comite = comite;
		this.foto = foto;
	}

	public Integer getTenantId() {
		return tenantId;
	}

	public void setTenantId(Integer tenantId) {
		this.tenantId = tenantId;
	}

	public Integer getDeportistaID() {
		return deportistaID;
	}

	public void setDeportistaID(Integer deportistaID) {
		this.deportistaID = deportistaID;
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

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Date getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(Date fechaNac) {
		this.fechaNac = fechaNac;
	}

	public DataComite getComite() {
		return comite;
	}

	public void setComite(DataComite comite) {
		this.comite = comite;
	}

	public Set<DataCompetencia> getListcompetencias() {
		return listcompetencias;
	}

	public void setListcompetencias(Set<DataCompetencia> listcompetencias) {
		this.listcompetencias = listcompetencias;
	}

	public Set<DataEstadistica> getListestadisticas() {
		return listestadisticas;
	}

	public void setListestadisticas(Set<DataEstadistica> listestadisticas) {
		this.listestadisticas = listestadisticas;
	}

	public Set<DataEventoDeportivo> getListeventodeportivo() {
		return listeventodeportivo;
	}

	public void setListeventodeportivo(Set<DataEventoDeportivo> listeventodeportivo) {
		this.listeventodeportivo = listeventodeportivo;
	}

	public DataImagen getFoto() {
		return foto;
	}

	public void setFoto(DataImagen foto) {
		this.foto = foto;
	}

	@Override
	public String toString() {
		return "DataDeportista [tenantId=" + tenantId + ", deportistaID=" + deportistaID + ", nombre=" + nombre
				+ ", apellido=" + apellido + ", sexo=" + sexo + ", fechaNac=" + fechaNac + ", comite=" + comite
				+ ", Listdeporte=" + listeventodeportivo + ", Listestadisticas=" + listestadisticas + ", foto=" + foto + "]";
	}

	
}