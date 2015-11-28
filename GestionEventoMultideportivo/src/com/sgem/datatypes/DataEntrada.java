package com.sgem.datatypes;

import java.util.Date;

import com.sgem.dominio.Competencia;
import com.sgem.dominio.UsuarioComun;

public class DataEntrada {

	private int entradaId;
	
	
	private int tenantId;
	
	
	private Date fecha;
	
	
	private float precioEntrada;
	
	
	private int numeroAsiento;
	
	
	private boolean vendida;
	
	
	private DataCompetencia competencia;
	
	
	private DataUsuario UsuarioComun;

	
	
	
	
	
	public DataEntrada() {}
	
	

	public DataEntrada(int tenantId, Date fecha, float precioEntrada, int numeroAsiento, boolean vendida) {
		super();
		this.tenantId = tenantId;
		this.fecha = fecha;
		this.precioEntrada = precioEntrada;
		this.numeroAsiento = numeroAsiento;
		//this.competencia = competencia;
		this.vendida = vendida;
		//UsuarioComun = usuarioComun;
	}



	public int getEntradaId() {
		return entradaId;
	}

	public void setEntradaId(int entradaId) {
		this.entradaId = entradaId;
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

	public int getNumeroAsiento() {
		return numeroAsiento;
	}

	public void setNumeroAsiento(int numeroAsiento) {
		this.numeroAsiento = numeroAsiento;
	}

	


	public DataCompetencia getCompetencia() {
		return competencia;
	}



	public void setCompetencia(DataCompetencia competencia) {
		this.competencia = competencia;
	}



	public DataUsuario getUsuarioComun() {
		return UsuarioComun;
	}



	public void setUsuarioComun(DataUsuario usuarioComun) {
		UsuarioComun = usuarioComun;
	}



	public boolean isVendida() {
		return vendida;
	}



	public void setVendida(boolean vendida) {
		this.vendida = vendida;
	}



	@Override
	public String toString() {
		return "DataEntrada [entradaId=" + entradaId + ", tenantId=" + tenantId + ", fecha=" + fecha
				+ ", precioEntrada=" + precioEntrada + ", numeroAsiento=" + numeroAsiento + ", vendida=" + vendida
				+ ", competencia=" + competencia + ", UsuarioComun=" + UsuarioComun + "]";
	}
	
	


}
