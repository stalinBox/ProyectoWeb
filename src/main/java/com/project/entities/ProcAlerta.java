package com.project.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the proc_alerta database table.
 * 
 */
@Entity
@Table(name="proc_alerta")
@NamedQuery(name="ProcAlerta.findAll", query="SELECT p FROM ProcAlerta p")
public class ProcAlerta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="proc_ale_codigo")
	private Integer procAleCodigo;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	private String observacion;

	@Column(name="time_llegada")
	private String timeLlegada;

	@Column(name="time_solucion")
	private String timeSolucion;

	//bi-directional many-to-one association to Alerta
	@ManyToOne
	@JoinColumn(name="alerta_codigo")
	private Alerta alerta;

	//bi-directional many-to-one association to Dispositivo
	@ManyToOne
	@JoinColumn(name="id_dispositivo")
	private Dispositivo dispositivo;

	//bi-directional many-to-one association to EstadosAlt
	@ManyToOne
	@JoinColumn(name="estado_codigo")
	private EstadosAlt estadosAlt;

	//bi-directional many-to-one association to Proceso
	@ManyToOne
	@JoinColumn(name="pro_codigo")
	private Proceso proceso;

	public ProcAlerta() {
	}

	public Integer getProcAleCodigo() {
		return this.procAleCodigo;
	}

	public void setProcAleCodigo(Integer procAleCodigo) {
		this.procAleCodigo = procAleCodigo;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getObservacion() {
		return this.observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public String getTimeLlegada() {
		return this.timeLlegada;
	}

	public void setTimeLlegada(String timeLlegada) {
		this.timeLlegada = timeLlegada;
	}

	public String getTimeSolucion() {
		return this.timeSolucion;
	}

	public void setTimeSolucion(String timeSolucion) {
		this.timeSolucion = timeSolucion;
	}

	public Alerta getAlerta() {
		return this.alerta;
	}

	public void setAlerta(Alerta alerta) {
		this.alerta = alerta;
	}

	public Dispositivo getDispositivo() {
		return this.dispositivo;
	}

	public void setDispositivo(Dispositivo dispositivo) {
		this.dispositivo = dispositivo;
	}

	public EstadosAlt getEstadosAlt() {
		return this.estadosAlt;
	}

	public void setEstadosAlt(EstadosAlt estadosAlt) {
		this.estadosAlt = estadosAlt;
	}

	public Proceso getProceso() {
		return this.proceso;
	}

	public void setProceso(Proceso proceso) {
		this.proceso = proceso;
	}

}