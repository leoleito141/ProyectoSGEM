package com.sgem.datatypes;

import java.util.Date;
import java.util.List;

public class DataCompetencia {

	private int tenantId;

	private int idCompetencia;

	private Date fecha;

	private String estadio;

	private float precioEntrada;

	private int cantEntradas;

	private int entradasVendidas;

	private String nombreDeporte;

	private String nombreDisciplina;

	private String tipoDeporte;

	private String sexo;

	private DataJuez juez;

	private int ronda;

	private List<DataDeportista> deportistas;
	
	private boolean finalizada;

	public DataCompetencia() {}

	public DataCompetencia(int tenantId, Date fecha, String estadio,float precioEntrada, int cantEntradas, String nombreDeporte,
			String nombreDisciplina, String tipoDeporte, String sexo,DataJuez juez, int ronda, int entradasVendidas,boolean finalizada,
			List<DataDeportista> deportistas) {
		this.tenantId = tenantId;
		this.fecha = fecha;
		this.estadio = estadio;
		this.precioEntrada = precioEntrada;
		this.cantEntradas = cantEntradas;
		this.nombreDeporte = nombreDeporte;
		this.nombreDisciplina = nombreDisciplina;
		this.tipoDeporte = tipoDeporte;
		this.sexo = sexo;
		this.juez = juez;
		this.entradasVendidas = entradasVendidas;
		this.ronda = ronda;
		this.finalizada = finalizada;
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

	public String getTipoDeporte() {
		return tipoDeporte;
	}

	public void setTipoDeporte(String tipoDeporte) {
		this.tipoDeporte = tipoDeporte;
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
	
	public boolean isFinalizada() {
		return finalizada;
	}

	public void setFinalizada(boolean finalizada) {
		this.finalizada = finalizada;
	}

	public List<DataDeportista> getDeportistas() {
		return deportistas;
	}

	public void setDeportistas(List<DataDeportista> deportistas) {
		this.deportistas = deportistas;
	}

	public int getIdCompetencia() {
		return idCompetencia;
	}

	public void setIdCompetencia(int idCompetencia) {
		this.idCompetencia = idCompetencia;
	}

	public int getEntradasVendidas() {
		return entradasVendidas;
	}

	public void setEntradasVendidas(int entradasVendidas) {
		this.entradasVendidas = entradasVendidas;
	}

	@Override
	public String toString() {
		return "DataCompetencia [tenantId=" + tenantId + ", idCompetencia="
				+ idCompetencia + ", fecha=" + fecha + ", estadio=" + estadio
				+ ", precioEntrada=" + precioEntrada + ", cantEntradas="
				+ cantEntradas + ", entradasVendidas=" + entradasVendidas
				+ ", nombreDeporte=" + nombreDeporte + ", nombreDisciplina="
				+ nombreDisciplina + ", tipoDeporte=" + tipoDeporte + ", sexo="
				+ sexo + ", juez=" + juez + ", ronda=" + ronda
				+ ", deportistas=" + deportistas + ", finalizada=" + finalizada
				+ "]";
	}

}
