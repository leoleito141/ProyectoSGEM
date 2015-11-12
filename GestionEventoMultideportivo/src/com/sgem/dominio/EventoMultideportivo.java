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

	@Column(name = "logo", nullable = true)
	private String logo;
	
	@Column(name = "fecha_inicio", nullable = true)
	private Date fechaInicio;
	
	@Column(name = "fecha_fin", nullable = true)
	private Date fechaFin;
	
	@Column(name = "facebook", nullable = true)
	private String facebook;
	
	@Column(name = "instagram", nullable = true)
	private String instagram;
	
	@Column(name = "hashtag", nullable = true)
	private String Hashtag;
	
	@Column(name = "canal_youtube", nullable = true)
	private String canalYoutube;
	
	@Column(name = "css", nullable = true)
	private String Css;
	
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
		this.Hashtag = hashtag;
		this.canalYoutube = canalYoutube;
		this.Css = css;

	}
	
	
	
	public String getInstagram() {
		return instagram;
	}

	public void setInstagram(String instagram) {
		this.instagram = instagram;
	}

	public TenantHandler getTenant() {
		return tenantHandler;
	}

	
	public void setTenant(TenantHandler tenant) {
		    this.tenantHandler = tenant;
		
    }

	public int getEventoId() {
		return EventoId;
	}

	public void setEventoId(int eventoId) {
		this.EventoId = eventoId;
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
	
	public Imagen getBanner() {
		return imagenBanner;
	}

	public void setBanner(Imagen banner) {
		this.imagenBanner = banner;
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

	public String getCss() {
		return Css;
	}

	public void setCss(String css) {
		Css = css;
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

	public void setDeportes(Set<EventoDeportivo> deportes) {
		this.deportes = deportes;
	}
	
	public void agregarEventos(EventoDeportivo ed) {

		if (this.deportes == null)
			this.deportes = new HashSet<EventoDeportivo>();

		this.deportes.add(ed);

	}
	
	
}
