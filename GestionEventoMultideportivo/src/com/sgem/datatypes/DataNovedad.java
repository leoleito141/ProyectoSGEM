package com.sgem.datatypes;

public class DataNovedad {

	private String titulo;
	private String descripcion;
	private int columna;
	private int tenantId;
	private String emailComiteOlimpico;
	
	// private DataImagen imagen;

	public DataNovedad() {}

	public DataNovedad(String titulo, String descripcion, int columna,
			int tenantId, String emailComiteOlimpico) {
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.columna = columna;
		this.tenantId = tenantId;
		this.emailComiteOlimpico = emailComiteOlimpico;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getColumna() {
		return columna;
	}

	public void setColumna(int columna) {
		this.columna = columna;
	}

	public int getTenantId() {
		return tenantId;
	}

	public void setTenantId(int tenantId) {
		this.tenantId = tenantId;
	}

	public String getEmailComiteOlimpico() {
		return emailComiteOlimpico;
	}

	public void setEmailComiteOlimpico(String emailComiteOlimpico) {
		this.emailComiteOlimpico = emailComiteOlimpico;
	}
	
}
