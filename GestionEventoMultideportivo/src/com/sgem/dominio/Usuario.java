package com.sgem.dominio;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Usuario implements Serializable {
	
	private static final long serialVersionUID = 3285309246756091060L;
	
	@TableGenerator(name = "USER_GEN", table = "ID_GEN", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL", allocationSize = 1)
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "USER_GEN")
	private long id;
	
	@Column(name = "tenant_ID", nullable = false)
	private int tenantID;
	
	@Column(name = "email", nullable = false)
	private String email;
	
	@Column(name = "facebook", nullable = true)
	private String facebook;
	
	@Column(name = "twitter", nullable = true)
	private String twitter;
	
	@Column(name = "canal_youtube", nullable = true)
	private String canalYoutube;
	
/*	@Column(name = "nombre", nullable = false)
	private String nombre;
	
	@Column(name = "apellido", nullable = false)
	private String apellido;
	
	@Column(name = "edad", nullable = false)
	private Integer edad;	
	
	@Column(name = "cedula", nullable = false)
	private Integer cedula;
	*/
	@Column(name = "password", nullable = false)
	private String password;

	public Usuario() {}

	public Usuario(String email, String facebook, String twitter,
			String canalYoutube, String password,int tenantid) {
		this.email = email;
		this.facebook = facebook;
		this.twitter = twitter;
		this.canalYoutube = canalYoutube;
	//	this.nombre = nombre;
	//	this.apellido = apellido;
	//	this.edad = edad;
	//	this.cedula = cedula;
		this.password = password;
		this.tenantID=tenantid;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public String getTwitter() {
		return twitter;
	}

	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}

	public String getCanalYoutube() {
		return canalYoutube;
	}

	public void setCanalYoutube(String canalYoutube) {
		this.canalYoutube = canalYoutube;
	}

/*	public String getNombre() {
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
*/
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getTenantID() {
		return tenantID;
	}

	public void setTenantID(int tenantID) {
		this.tenantID = tenantID;
	}
			
	public abstract String soy();
}
