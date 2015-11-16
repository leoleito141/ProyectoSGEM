package com.sgem.datatypes;

import java.util.Date;
import java.util.List;

public class DataDeportista {

	private Integer tenantId;

	private Integer deportistaID;

	private String nombre;

	private String apellido;

	private String sexo;

	private Date fechaNac;

	private DataPais pais;

	private String deporte;

	private List<String> disciplinas;

	private DataImagen foto;

	public DataDeportista() {}

	public DataDeportista(Integer tenantId, Integer deportistaID,
			String nombre, String apellido, String sexo, Date fechaNac,
			DataPais pais, String deporte, List<String> disciplinas,
			DataImagen foto) {
		super();
		this.tenantId = tenantId;
		this.deportistaID = deportistaID;
		this.nombre = nombre;
		this.apellido = apellido;
		this.sexo = sexo;
		this.fechaNac = fechaNac;
		this.pais = pais;
		this.deporte = deporte;
		this.disciplinas = disciplinas;
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

	public DataPais getPais() {
		return pais;
	}

	public void setPais(DataPais pais) {
		this.pais = pais;
	}

	public String getDeporte() {
		return deporte;
	}

	public void setDeporte(String deporte) {
		this.deporte = deporte;
	}

	public List<String> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(List<String> disciplinas) {
		this.disciplinas = disciplinas;
	}

	public DataImagen getFoto() {
		return foto;
	}

	public void setFoto(DataImagen foto) {
		this.foto = foto;
	}

	@Override
	public String toString() {
		return "DataDeportista [tenantId=" + tenantId + ", deportistaID="
				+ deportistaID + ", nombre=" + nombre + ", apellido="
				+ apellido + ", sexo=" + sexo + ", fechaNac=" + fechaNac
				+ ", pais=" + pais + ", deporte=" + deporte + ", disciplinas="
				+ disciplinas + ", foto=" + foto + "]";
	}

}