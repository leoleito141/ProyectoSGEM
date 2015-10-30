package com.sgem.dominio;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="entrada")
public class Entrada implements Serializable{
		
		private static final long serialVersionUID = -6321506554036389023L;
		
		@Id 
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private int entradaId;
		
		@Column(name = "tenant_ID", nullable = true)
		private int tenantId;
		
		@Column(name = "fecha", nullable = false)
		private Date fecha;
		
		@Column(name = "precioEntrada", nullable = false)
		private float precioEntrada;
		
		@Column(name = "numeroAsiento", nullable = false)
		private int numeroAsiento;
		
		@Column(name = "vendida", nullable = false)
		private boolean vendida;
		
		@ManyToOne	
		private Competencia competencia;
		
		@ManyToOne	
		private UsuarioComun UsuarioComun;

		
		
		
		
		
		public Entrada() {}
		
		

		public Entrada(int tenantId, Date fecha, float precioEntrada, int numeroAsiento, boolean vendida, Competencia competencia,
				UsuarioComun usuarioComun) {
			super();
			this.tenantId = tenantId;
			this.fecha = fecha;
			this.precioEntrada = precioEntrada;
			this.numeroAsiento = numeroAsiento;
			this.competencia = competencia;
			this.vendida = vendida;
			UsuarioComun = usuarioComun;
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

		public Competencia getCompetencia() {
			return competencia;
		}

		public void setCompetencia(Competencia competencia) {
			this.competencia = competencia;
		}

		public UsuarioComun getUsuarioComun() {
			return UsuarioComun;
		}

		public void setUsuarioComun(UsuarioComun usuarioComun) {
			UsuarioComun = usuarioComun;
		}



		public boolean isVendida() {
			return vendida;
		}



		public void setVendida(boolean vendida) {
			this.vendida = vendida;
		}
		
		
	

}
