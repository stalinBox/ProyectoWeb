package com.project.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the graficas database table.
 * 
 */
@Entity
@Table(name="graficas")
@NamedQuery(name="Grafica.findAll", query="SELECT g FROM Grafica g")
public class Grafica implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private Integer cantidad;

	private Timestamp fecha;

	@Column(name="lineapro_codigo")
	private Integer lineaproCodigo;

	@Column(name="ordenprod_codigo")
	private Integer ordenprodCodigo;

	@Column(name="tpr_codigo")
	private Integer tprCodigo;

	@Column(name="turno_codigo")
	private Integer turnoCodigo;

	public Grafica() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Timestamp getFecha() {
		return this.fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public Integer getLineaproCodigo() {
		return this.lineaproCodigo;
	}

	public void setLineaproCodigo(Integer lineaproCodigo) {
		this.lineaproCodigo = lineaproCodigo;
	}

	public Integer getOrdenprodCodigo() {
		return this.ordenprodCodigo;
	}

	public void setOrdenprodCodigo(Integer ordenprodCodigo) {
		this.ordenprodCodigo = ordenprodCodigo;
	}

	public Integer getTprCodigo() {
		return this.tprCodigo;
	}

	public void setTprCodigo(Integer tprCodigo) {
		this.tprCodigo = tprCodigo;
	}

	public Integer getTurnoCodigo() {
		return this.turnoCodigo;
	}

	public void setTurnoCodigo(Integer turnoCodigo) {
		this.turnoCodigo = turnoCodigo;
	}

}