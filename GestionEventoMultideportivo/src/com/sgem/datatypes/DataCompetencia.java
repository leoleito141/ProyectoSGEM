package com.sgem.datatypes;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.sgem.dominio.Deportista;
import com.sgem.dominio.Entrada;
import com.sgem.dominio.EventoDeportivo;
import com.sgem.dominio.Juez;
import com.sgem.dominio.Resultado;
import com.sgem.dominio.Ronda;

public class DataCompetencia {
	
	private int tenantId;
	
	private Date fecha;
	
	private String estadio;
	
	private float precioEntrada;
	
	private int cantEntradas;
	
	private String nombreDeporte;
	
	private String nombreDisciplina;
	
	private String sexo;

	private DataJuez juez;
	
	private int ronda;
	
	private List<DataDeportista> deportistas;
	
	public DataCompetencia(){}
	
	
	

	public DataCompetencia(int tenantId, Date fecha, String estadio, float precioEntrada, int cantEntradas,
			String nombreDeporte, String nombreDisciplina, String sexo, DataJuez juez, int ronda,
			List<DataDeportista> deportistas) {
		super();
		this.tenantId = tenantId;
		this.fecha = fecha;
		this.estadio = estadio;
		this.precioEntrada = precioEntrada;
		this.cantEntradas = cantEntradas;
		this.nombreDeporte = nombreDeporte;
		this.nombreDisciplina = nombreDisciplina;
		this.sexo = sexo;
		this.juez = juez;
		this.ronda = ronda;
		this.deportistas = deportistas;
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

	public String getEstadio() {
		return estadio;
	}

	public void setEstadio(String estadio) {
		this.estadio = estadio;
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

	public String getNombreDeporte() {
		return nombreDeporte;
	}

	public void setNombreDeporte(String nombreDeporte) {
		this.nombreDeporte = nombreDeporte;
	}

	public String getNombreDisciplina() {
		return nombreDisciplina;
	}

	public void setNombreDisciplina(String nombreDisciplina) {
		this.nombreDisciplina = nombreDisciplina;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public DataJuez getJuez() {
		return juez;
	}

	public void setJuez(DataJuez juez) {
		this.juez = juez;
	}

	public int getRonda() {
		return ronda;
	}

	public void setRonda(int ronda) {
		this.ronda = ronda;
	}

	public List<DataDeportista> getDeportistas() {
		return deportistas;
	}

	public void setDeportistas(List<DataDeportista> deportistas) {
		this.deportistas = deportistas;
	}




	@Override
	public String toString() {
		return "DataCompetencia [tenantId=" + tenantId + ", fecha=" + fecha + ", estadio=" + estadio
				+ ", precioEntrada=" + precioEntrada + ", cantEntradas=" + cantEntradas + ", nombreDeporte="
				+ nombreDeporte + ", nombreDisciplina=" + nombreDisciplina + ", sexo=" + sexo + ", juez=" + juez
				+ ", ronda=" + ronda + ", deportistas=" + deportistas + "]";
	}
	
	
	

}
