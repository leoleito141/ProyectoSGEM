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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="competencia")
public class Competencia implements Serializable{
	
	private static final long serialVersionUID = -6321506554036389023L;
	
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int CompetenciaId;
	
	@Column(name = "tenant_ID", nullable = true)
	private int tenantId;
	
	@Column(name = "fecha", nullable = false)
	private Date fecha;
	
	@Column(name = "precioEntrada", nullable = false)
	private float precioEntrada;
	
	@Column(name = "cantEntradas", nullable = false)
	private int cantEntradas;
	
	
	@ManyToOne	
	private EventoDeportivo eventoDeportivo;

	@ManyToOne	
	private Juez juez;
	
	
	@ManyToOne	
	private Ronda ronda;
	
	@OneToOne(targetEntity=Resultado.class,cascade = CascadeType.ALL)
	private Resultado resultado;
	
	@ManyToMany( fetch = FetchType.EAGER)
	private Set <Deportista> deportistas;

	@OneToMany(fetch = FetchType.EAGER,mappedBy = "competencia") 
	private Set<Entrada> entradas = new HashSet<Entrada>();

	
	
	
	public Competencia(){
		this.deportistas = new HashSet<Deportista>();
		this.entradas = new HashSet<Entrada>();
	}
	
	
	public Competencia(int tenantId, Date fecha, float precioEntrada, int cantEntradas,
			EventoDeportivo eventoDeportivo, Juez juez, Ronda ronda, Resultado resultado,
			Set<Deportista> deportistas, Set<Entrada> entradas) {
		
		//super();
		
		this.tenantId = tenantId;
		this.fecha = fecha;
		this.precioEntrada = precioEntrada;
		this.cantEntradas = cantEntradas;
		this.eventoDeportivo = eventoDeportivo;
		this.juez = juez;
		this.ronda = ronda;
		this.resultado = resultado;
		this.deportistas = new HashSet<Deportista>();
		this.entradas = new HashSet<Entrada>();
	}

	public int getCompetenciaId() {
		return CompetenciaId;
	}

	public void setCompetenciaId(int competenciaId) {
		CompetenciaId = competenciaId;
	}

	public int getTenantId() {
		return tenantId;
	}

	public void setTenantId(int tenantId) {
		this.tenantId = tenantId;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public float getPrecioEntrada() {
		return precioEntrada;
	}

	public void setPrecioEntrada(float precioEntrada) {
		this.precioEntrada = precioEntrada;
	}

	public int getCantEntradas() {
		return cantEntradas;
	}

	public void setCantEntradas(int cantEntradas) {
		this.cantEntradas = cantEntradas;
	}

	public EventoDeportivo getEventoDeportivo() {
		return eventoDeportivo;
	}

	public void setEventoDeportivo(EventoDeportivo eventoDeportivo) {
		this.eventoDeportivo = eventoDeportivo;
	}

	public Juez getJuez() {
		return juez;
	}

	public void setJuez(Juez juez) {
		this.juez = juez;
	}

	

	public Ronda getRonda() {
		return ronda;
	}

	public void setRonda(Ronda ronda) {
		this.ronda = ronda;
	}

	public Resultado getResultado() {
		return resultado;
	}

	public void setResultado(Resultado resultado) {
		this.resultado = resultado;
	}

	public Set<Deportista> getDeportistas() {
		return deportistas;
	}

	public void setDeportistas(Set<Deportista> deportistas) {
		this.deportistas = deportistas;
	}
	
	public void addDeportista(Deportista deportista) {
		this.deportistas.add(deportista);
		
	}

	public Set<Entrada> getEntradas() {
		return entradas;
	}

	public void setEntradas(Set<Entrada> entradas) {
		this.entradas = entradas;
	}
	
	
	public void addEntrada(Entrada entrada) {
		this.entradas.add(entrada);
		
	}
	

	
}
