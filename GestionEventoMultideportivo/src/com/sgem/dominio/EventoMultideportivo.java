package com.sgem.dominio;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int EventoId;
	
//	@Column(name = "tenant_ID", nullable = true)
//	private int tenantId;
	
	@Column(name = "nombre", nullable = true)
	private String nombre;
	
	@Column(name = "lugar", nullable = true)
	private String lugar;
	
	@Column(name = "logo", nullable = true)
	private String logo;
	
	@Column(name = "fecha_inicio", nullable = true)
	private Date fechaInicio;
	
	@Column(name = "fecha_fin", nullable = true)
	private Date fechaFin;
	
	@Column(name = "facebook", nullable = true)
	private String facebook;
	
	@Column(name = "hashtag", nullable = true)
	private String Hashtag;
	
	@Column(name = "canal_youtube", nullable = true)
	private String canalYoutube;
	
	@Column(name = "css", nullable = true)
	private String Css;
	
	@OneToOne(targetEntity=Organizador.class,mappedBy="evento")
	private Organizador organizador;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy = "eventoMultideportivo") 
	private Set<EventoDeportivo> deportes= new HashSet<EventoDeportivo>();
	
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="tenantHandler_id")
    private TenantHandler tenantHandler;
	
	public EventoMultideportivo(){}

	public EventoMultideportivo( String nombre, String lugar,
			String logo, Date date, Date date2, String facebook,
			String hashtag, String canalYoutube, String css) {
		
		
		this.nombre = nombre;
		this.lugar = lugar;
		this.logo = logo;
		this.fechaInicio = date;
		this.fechaFin = date2;
		this.facebook = facebook;
		this.Hashtag = hashtag;
		this.canalYoutube = canalYoutube;
		this.Css = css;

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

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
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
	
	
}
