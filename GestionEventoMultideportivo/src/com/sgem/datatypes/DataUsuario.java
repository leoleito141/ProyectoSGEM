package com.sgem.datatypes;

import com.google.gson.annotations.SerializedName;

public class DataUsuario{
	
//	Ver bien desp si crear un data por rol.
	
//	@Expose(serialize = false, deserialize = false)
	@SerializedName(value="Email")
	private String email;
	
	@SerializedName(value="Facebook")
	private String facebook;
	
	@SerializedName(value="Twitter")
	private String twitter;
	
	@SerializedName(value="CanalYoutube")
	private String canalYoutube;
	
	@SerializedName(value="Nombre")
	private String nombre;
	
	@SerializedName(value="Apellido")
	private String apellido;
	
	@SerializedName(value="Edad")
	private Integer edad;	
	
	@SerializedName(value="Cedula")
	private Integer cedula;
	
	@SerializedName(value="Password")
	private String password;
	
}
