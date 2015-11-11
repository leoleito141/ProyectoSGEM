package com.sgem.dominio;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="novedad")
public class Novedad implements Serializable{
	
	private static final long serialVersionUID = 1771315983993274410L;

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int novedadID;

	@Column(name = "titulo", nullable = false)
	private String titulo;
	
	@Column(name = "tenantID", nullable = false)
	private int tenantID;

	

	@Column(name = "descripcion", nullable = false)
	private String descripcion;
	
	@Column(name = "columna", nullable = false)
	private int columna;

	@ManyToOne	
	private ComiteOlimpico comite_olimpico;

	@OneToOne(fetch=FetchType.LAZY)
	private Imagen imagen;
	
//	@ManyToMany(fetch = FetchType.LAZY)
//	private Set<Comentario> comentarios;
	
	public Novedad(){}
	
	public Novedad(String titulo, String descripcion, int columna,
			ComiteOlimpico comite_olimpico,Imagen imagen,int tenantID) {
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.columna = columna;
		this.comite_olimpico = comite_olimpico;
		this.imagen = imagen;
		this.tenantID = tenantID;
	}

	public int getNovedadID() {
		return novedadID;
	}

	public void setNovedadID(int novedadID) {
		this.novedadID = novedadID;
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

	public ComiteOlimpico getComite_olimpico() {
		return comite_olimpico;
	}

	public void setComite_olimpico(ComiteOlimpico comite_olimpico) {
		this.comite_olimpico = comite_olimpico;
	}

	public Imagen getImagen() {
		return imagen;
	}

	public void setImagen(Imagen imagen) {
		this.imagen = imagen;
	}

	public int getTenantID() {
		return tenantID;
	}

	public void setTenantID(int tenantID) {
		this.tenantID = tenantID;
	}

}
