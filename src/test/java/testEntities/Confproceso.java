package testEntities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the confproceso database table.
 * 
 */
@Entity
@NamedQuery(name = "Confproceso.findAll", query = "SELECT c FROM Confproceso c")
public class Confproceso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "confpro_codigo")
	private Integer confproCodigo;

	@Column(name = "tiempo_ts")
	private double tiempoTs;

	// bi-directional many-to-one association to Modelo
	@ManyToOne
	@JoinColumn(name = "mod_codigo", insertable = false, updatable = false)
	private Modelo modelo;

	// bi-directional many-to-one association to Proceso
	@ManyToOne
	@JoinColumn(name = "pro_codigo")
	private Proceso proceso1;

	// bi-directional many-to-one association to Proceso
	@ManyToOne
	@JoinColumn(name = "sub_pro")
	private Proceso proceso2;

	public Confproceso() {
	}

	public Integer getConfproCodigo() {
		return this.confproCodigo;
	}

	public void setConfproCodigo(Integer confproCodigo) {
		this.confproCodigo = confproCodigo;
	}

	public double getTiempoTs() {
		return this.tiempoTs;
	}

	public void setTiempoTs(double tiempoTs) {
		this.tiempoTs = tiempoTs;
	}

	public Modelo getModelo() {
		return this.modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	public Proceso getProceso1() {
		return this.proceso1;
	}

	public void setProceso1(Proceso proceso1) {
		this.proceso1 = proceso1;
	}

	public Proceso getProceso2() {
		return this.proceso2;
	}

	public void setProceso2(Proceso proceso2) {
		this.proceso2 = proceso2;
	}

}