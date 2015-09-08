package com.sgem.dominio;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonProperty;

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {
	
private static final long serialVersionUID = 1L; // Mapping JPA

@Id	
@Column(name = "Nombre", nullable = false)
@JsonProperty("Nombre")
private String nombre;

@Column(name = "Apellido", nullable = false)
@JsonProperty("Apellido")
private String apellido;

@Column(name = "Edad", nullable = false)
@JsonProperty("Edad")
private Integer edad;


@Column(name = "Cedula", nullable = false)
@JsonProperty("Cedula")
private Integer cedula;

@Column(name = "Password", nullable = false)
@JsonProperty("Password")
private String password;

public Usuario() {

}

public Usuario(String nombre, String apellido, Integer edad, Integer cedula) {
	this.setNombre(nombre);
	this.setApellido(apellido);
	this.setEdad(edad);
	this.setCedula(cedula);
}


public Usuario(Usuario p) {
	
	this.nombre=p.getNombre();
	this.apellido=p.getApellido();
	this.edad=p.getEdad();
	this.cedula=p.getCedula();
	
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

public Integer getEdad() {
	return edad;
}

public void setEdad(Integer edad) {
	this.edad = edad;
}

public Integer getCedula() {
	return cedula;
}

public void setCedula(Integer cedula) {
	this.cedula = cedula;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}




}
