package com.sgem.datatypes;

public class DataNovedad {

	private String titulo;

	private String descripcion;

	private int columna;

	public DataNovedad() {}

	public DataNovedad(String titulo, String descripcion, int columna) {
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.columna = columna;
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

}
