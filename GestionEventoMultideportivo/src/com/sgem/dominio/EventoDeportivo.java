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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="eventoDeportivo")
public class EventoDeportivo implements Serializable{
	
	private static final long serialVersionUID = -6321506554036389023L;
	
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int EventoDepId;
	
	@Column(name = "tenant_ID", nullable = true)
	private int tenantId;
	
	@Column(name = "EventoId", nullable = true)
	private int EventoMultiId;
	
	@Column(name = "nombreDeporte", nullable = true)
	private String nombreDeporte;
	
	@Column(name = "disciplina", nullable = true)
	private String disciplina;
	
	@Column(name = "fecha_inicio", nullable = true)
	private Date fechaInicio;
	
	@Column(name = "fecha_fin", nullable = true)
	private Date fechaFin;
	
	@Column(name = "sexo", nullable = false)
	private String sexo;
	
	@ManyToOne	
	private EventoMultideportivo eventoMultideportivo;

	@OneToMany(fetch = FetchType.EAGER,mappedBy = "eventoDeportivo") 
	private Set<Competencia> Competencia= new HashSet<Competencia>();
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy = "eventoDeportivo") 
	private Set<Ronda> ronda= new HashSet<Ronda>();
	
	
	public EventoDeportivo(){}
	
	public EventoDeportivo(int eventoDepId, int tenantId, int eventoMultiId, String nombreDeporte, String disciplina,
			Date fechaInicio, Date fechaFin, String sexo, EventoMultideportivo eventoMultideportivo,
			Set<com.sgem.dominio.Competencia> competencia, Set<Ronda> ronda) {
		super();
		this.EventoDepId = eventoDepId;
		this.tenantId = tenantId;
		this.EventoMultiId = eventoMultiId;
		this.nombreDeporte = nombreDeporte;
		this.disciplina = disciplina;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.sexo = sexo;
		this.eventoMultideportivo = eventoMultideportivo;
		this.Competencia = competencia;
		this.ronda = ronda;
	}

	public Set<Competencia> getCompetencia() {
		return Competencia;
	}

	public void setCompetencia(Set<Competencia> competencia) {
		Competencia = competencia;
	}

	public Set<Ronda> getRonda() {
		return ronda;
	}

	public void setRonda(Set<Ronda> ronda) {
		this.ronda = ronda;
	}

	

	public int getEventoDepId() {
		return EventoDepId;
	}

	public void setEventoDepId(int eventoDepId) {
		EventoDepId = eventoDepId;
	}

	public int getTenantId() {
		return tenantId;
	}

	public void setTenantId(int tenantId) {
		this.tenantId = tenantId;
	}

	public int getEventoMultiId() {
		return EventoMultiId;
	}

	public void setEventoMultiId(int eventoMultiId) {
		EventoMultiId = eventoMultiId;
	}

	public String getNombreDeporte() {
		return nombreDeporte;
	}

	public void setNombreDeporte(String nombreDeporte) {
		this.nombreDeporte = nombreDeporte;
	}

	public String getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(String disciplina) {
		this.disciplina = disciplina;
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

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public EventoMultideportivo getEventoMultideportivo() {
		return eventoMultideportivo;
	}

	public void setEventoMultideportivo(EventoMultideportivo eventoMultideportivo) {
		this.eventoMultideportivo = eventoMultideportivo;
	}
	

	
}
