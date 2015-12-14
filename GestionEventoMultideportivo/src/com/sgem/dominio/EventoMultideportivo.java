package com.sgem.dominio;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="eventoMultideportivo")
public class EventoMultideportivo implements Serializable{
	
	private static final long serialVersionUID = -6321506554036389023L;
	
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int EventoId;
	
//	@Column(name = "tenant_ID", nullable = true)
//	private int tenantId;
	
	@Column(name = "nombre", nullable = true)
	private String nombre;
	
	@OneToOne(targetEntity=Pais.class,cascade = CascadeType.ALL)
	private Pais pais;
	
	@OneToOne(fetch=FetchType.LAZY)
	private Imagen imagenBanner;
	
	@OneToOne(fetch=FetchType.LAZY)
	private Imagen imagenFondo;
	
	@OneToOne(fetch=FetchType.LAZY)
	private Imagen imagenPagina;

	@Column(name = "logo", nullable = true) ///// Borrarlo!
	private String logo;
	
	@Column(name = "fecha_inicio", nullable = true)
	private Date fechaInicio;
	
	@Column(name = "fecha_fin", nullable = true)
	private Date fechaFin;
	
	@Column(name = "facebook", nullable = true)
	private String facebook;
	
	@Column(name = "widget_facebook", nullable = true)
	private String widget_facebook;
	
	@Column(name = "instagram", nullable = true)
	private String instagram;
	
	@Column(name = "widget_instagram", nullable = true)
	private String widget_instagram;
	
	@Column(name = "twitter", nullable = true)
	private String twitter;
		
	@Column(name = "hashtag", nullable = true)
	private String Hashtag;
	
	@Column(name = "canalYoutube", nullable = true)
	private String canalYoutube;
	
	@Column(name = "widget_Youtube", nullable = true)
	private String widget_Youtube;
	
	@Column(name = "widget_twitter", nullable = true)
	private String widget_twitter;	
	
	@Column(name = "css", nullable = true) ////// BORRAR!
	private String Css;
	
	@Column(name = "colorFondo", nullable = true)
	private String colorFondo;
	
	@Column(name = "colorNoticias", nullable = true)
	private String colorNoticias;
	
	
	@OneToOne(targetEntity=Organizador.class,mappedBy="evento",cascade = CascadeType.ALL)
	private Organizador organizador;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy = "eventoMultideportivo") 
	private Set<EventoDeportivo> deportes= new HashSet<EventoDeportivo>();
	
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="tenantHandler_id")
    private TenantHandler tenantHandler;
	
	public EventoMultideportivo(){}

	public EventoMultideportivo( String nombre, Pais pais,
			String logo, Date date, Date date2, String facebook,String instagram,
			String hashtag, String canalYoutube, String css) {
		
		
		this.nombre = nombre;
		this.pais = pais;
		this.logo = logo;
		this.fechaInicio = date;
		this.fechaFin = date2;
		this.facebook = facebook;
		this.instagram = instagram;
		this.twitter = hashtag;
		this.canalYoutube = canalYoutube;
		this.Css = css;

	}

	public int getEventoId() {
		return EventoId;
	}

	public void setEventoId(int eventoId) {
		EventoId = eventoId;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public Imagen getImagenBanner() {
		return imagenBanner;
	}

	public void setImagenBanner(Imagen imagenBanner) {
		this.imagenBanner = imagenBanner;
	}

	public Imagen getImagenFondo() {
		return imagenFondo;
	}

	public void setImagenFondo(Imagen imagenFondo) {
		this.imagenFondo = imagenFondo;
	}

	public Imagen getImagenPagina() {
		return imagenPagina;
	}

	public void setImagenPagina(Imagen imagenPagina) {
		this.imagenPagina = imagenPagina;
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

	public String getWidget_facebook() {
		return widget_facebook;
	}

	public void setWidget_facebook(String widget_facebook) {
		this.widget_facebook = widget_facebook;
	}

	public String getInstagram() {
		return instagram;
	}

	public void setInstagram(String instagram) {
		this.instagram = instagram;
	}

	public String getWidget_instagram() {
		return widget_instagram;
	}

	public void setWidget_instagram(String widget_instagram) {
		this.widget_instagram = widget_instagram;
	}

	public String getTwitter() {
		return twitter;
	}

	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}

	public String getHashtag() {
		return Hashtag;
	}

	public void setHashtag(String hashtag) {
		Hashtag = hashtag;
	}

	public String getCanalYoutube() {
		return canalYoutube;
	}

	public void setCanalYoutube(String canalYoutube) {
		this.canalYoutube = canalYoutube;
	}

	public String getWidget_twitter() {
		return widget_twitter;
	}

	public void setWidget_twitter(String widget_twitter) {
		this.widget_twitter = widget_twitter;
	}

	public String getColorFondo() {
		return colorFondo;
	}

	public void setColorFondo(String colorFondo) {
		this.colorFondo = colorFondo;
	}

	public String getColorNoticias() {
		return colorNoticias;
	}

	public void setColorNoticias(String colorNoticias) {
		this.colorNoticias = colorNoticias;
	}

	public Organizador getOrganizador() {
		return organizador;
	}

	public void setOrganizador(Organizador organizador) {
		this.organizador = organizador;
	}

	public Set<EventoDeportivo> getDeportes() {
		return deportes;
	}

	public void setDeportes(Set<EventoDeportivo> deportes) {
		this.deportes = deportes;
	}

	public TenantHandler getTenantHandler() {
		return tenantHandler;
	}

	public void setTenantHandler(TenantHandler tenantHandler) {
		this.tenantHandler = tenantHandler;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getWidget_Youtube() {
		return widget_Youtube;
	}

	public void setWidget_Youtube(String widget_Youtube) {
		this.widget_Youtube = widget_Youtube;
	}

	public String getCss() {
		return Css;
	}

	public void setCss(String css) {
		Css = css;
	}
		
	
}
