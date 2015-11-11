package com.sgem.datatypes;

import java.util.Date;

public class DataEvento {
	
	private int tenantId;
	
	private String nombre;
	
	private int anio;

	private String logo;
	
	private Date fechaInicio;
	
	private Date fechaFin;
	
	private String facebook;
	
	private String instagram;

	private String twitter;

	private String canalYoutube;

	private String hashtag;

	private String css;
	
	private String emailOrganizador;
	
	private String passwordOrganizador;
	
	private DataPais pais;
	
	private DataImagen banner;
	
	private DataImagen fondo;
	
	private DataImagen pagina;
	
	public int getTenantId() {
		return tenantId;
	}

	public void setTenantId(int tenantId) {
		this.tenantId = tenantId;
	}


	public String getNombre() {
		return nombre;
	}




	public void setNombre(String nombre) {
		this.nombre = nombre;
	}




	public DataPais getDataPais() {
		return pais;
	}




	public void setDataPais(DataPais pais ) {
		this.pais = pais;
	}




	public int getAnio() {
		return anio;
	}




	public void setAnio(int anio) {
		this.anio = anio;
	}




	public String getLogo() {
		return logo;
	}




	public void setLogo(String logo) {
		this.logo = logo;
	}




	public Date getFechaInicio() {
		return fechaInicio;
	}




	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}




	public Date getFechaFin() {
		return fechaFin;
	}




	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}



	public String getFacebook() {
		return facebook;
	}




	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}
	
	public String getInstagram() {
		return instagram;
	}


	public void setInstagram(String instagram) {
		this.instagram = instagram;
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




	public String getHashtag() {
		return hashtag;
	}




	public void setHashtag(String hashtag) {
		this.hashtag = hashtag;
	}




	public String getCss() {
		return css;
	}

	public void setCss(String css) {
		this.css = css;
	}
	
	


	public String getEmailOrganizador() {
		return emailOrganizador;
	}




	public void setEmailOrganizador(String emailOrganizador) {
		this.emailOrganizador = emailOrganizador;
	}




	public String getPasswordOrganizador() {
		return passwordOrganizador;
	}

	public void setPasswordOrganizador(String passwordOrganizador) {
		this.passwordOrganizador = passwordOrganizador;
	}
	
	public DataImagen getBanner() {
		return banner;
	}

	public void setBanner(DataImagen banner) {
		this.banner = banner;
	}

	public DataImagen getFondo() {
		return fondo;
	}

	public void setFondo(DataImagen fondo) {
		this.fondo = fondo;
	}

	public DataImagen getPagina() {
		return pagina;
	}

	public void setPagina(DataImagen pagina) {
		this.pagina = pagina;
	}


	@Override
	public String toString() {
		return "DataEvento [nombre=" + nombre + ", lugar=" + pais
				+ ", año=" + anio + ", logo=" + logo
				+ ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", facebook="
				+ facebook +",instagram="+ instagram + ", twitter=" + twitter + ", canalYoutube=" + canalYoutube
				+ ", hashtag=" + hashtag + ", css="+ css + ", emailOrganizador=" + emailOrganizador
				+ ", passwordOrganizador=" + passwordOrganizador + ", imagenBanner="+ banner+ ", imagenFondo="+ fondo +", imagenPagina="+pagina + "]";
	}
	
	
	
	

}
