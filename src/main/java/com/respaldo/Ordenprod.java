package com.respaldo;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the ordenprod database table.
 * 
 */
@Entity
@NamedQuery(name="Ordenprod.findAll", query="SELECT o FROM Ordenprod o")
public class Ordenprod implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ordenprod_codigo")
	private Integer ordenprodCodigo;

	@Temporal(TemporalType.DATE)
	@Column(name="f_actual")
	private Date fActual;

	@Temporal(TemporalType.DATE)
	@Column(name="f_estim")
	private Date fEstim;

	@Temporal(TemporalType.DATE)
	@Column(name="f_final")
	private Date fFinal;

	private Integer nombordprod;

	//bi-directional many-to-one association to Detalleorden
	@OneToMany(mappedBy="ordenprod", fetch=FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Detalleorden> detalleordens;

	//bi-directional many-to-one association to Lugare
	@ManyToOne
	@JoinColumn(name="lugar_codigo_dest")
	private Lugare lugare;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="user_id_resp")
	private Usuario usuario1;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="user_id_soli")
	private Usuario usuario2;

	public Ordenprod() {
	}

	public Integer getOrdenprodCodigo() {
		return this.ordenprodCodigo;
	}

	public void setOrdenprodCodigo(Integer ordenprodCodigo) {
		this.ordenprodCodigo = ordenprodCodigo;
	}

	public Date getFActual() {
		return this.fActual;
	}

	public void setFActual(Date fActual) {
		this.fActual = fActual;
	}

	public Date getFEstim() {
		return this.fEstim;
	}

	public void setFEstim(Date fEstim) {
		this.fEstim = fEstim;
	}

	public Date getFFinal() {
		return this.fFinal;
	}

	public void setFFinal(Date fFinal) {
		this.fFinal = fFinal;
	}

	public Integer getNombordprod() {
		return this.nombordprod;
	}

	public void setNombordprod(Integer nombordprod) {
		this.nombordprod = nombordprod;
	}

	public List<Detalleorden> getDetalleordens() {
		return this.detalleordens;
	}

	public void setDetalleordens(List<Detalleorden> detalleordens) {
		this.detalleordens = detalleordens;
	}

	public Detalleorden addDetalleorden(Detalleorden detalleorden) {
		getDetalleordens().add(detalleorden);
		detalleorden.setOrdenprod(this);

		return detalleorden;
	}

	public Detalleorden removeDetalleorden(Detalleorden detalleorden) {
		getDetalleordens().remove(detalleorden);
		detalleorden.setOrdenprod(null);

		return detalleorden;
	}

	public Lugare getLugare() {
		return this.lugare;
	}

	public void setLugare(Lugare lugare) {
		this.lugare = lugare;
	}

	public Usuario getUsuario1() {
		return this.usuario1;
	}

	public void setUsuario1(Usuario usuario1) {
		this.usuario1 = usuario1;
	}

	public Usuario getUsuario2() {
		return this.usuario2;
	}

	public void setUsuario2(Usuario usuario2) {
		this.usuario2 = usuario2;
	}

}