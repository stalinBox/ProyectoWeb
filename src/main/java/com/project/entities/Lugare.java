package com.project.entities;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;


/**
 * The persistent class for the lugares database table.
 * 
 */
@Entity
@Table(name="lugares")
@NamedQuery(name="Lugare.findAll", query="SELECT l FROM Lugare l")
public class Lugare implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="lugar_codigo")
	private Integer lugarCodigo;

	private String nomlugar;

	//bi-directional many-to-one association to Detalleorden
	@OneToMany(mappedBy="lugare1", fetch=FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Detalleorden> detalleordens1;

	//bi-directional many-to-one association to Detalleorden
	@OneToMany(mappedBy="lugare2", fetch=FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Detalleorden> detalleordens2;

	//bi-directional many-to-one association to Ordenprod
	@OneToMany(mappedBy="lugare", fetch=FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Ordenprod> ordenprods;

	public Lugare() {
	}

	public Integer getLugarCodigo() {
		return this.lugarCodigo;
	}

	public void setLugarCodigo(Integer lugarCodigo) {
		this.lugarCodigo = lugarCodigo;
	}

	public String getNomlugar() {
		return this.nomlugar;
	}

	public void setNomlugar(String nomlugar) {
		this.nomlugar = nomlugar;
	}

	public List<Detalleorden> getDetalleordens1() {
		return this.detalleordens1;
	}

	public void setDetalleordens1(List<Detalleorden> detalleordens1) {
		this.detalleordens1 = detalleordens1;
	}

	public Detalleorden addDetalleordens1(Detalleorden detalleordens1) {
		getDetalleordens1().add(detalleordens1);
		detalleordens1.setLugare1(this);

		return detalleordens1;
	}

	public Detalleorden removeDetalleordens1(Detalleorden detalleordens1) {
		getDetalleordens1().remove(detalleordens1);
		detalleordens1.setLugare1(null);

		return detalleordens1;
	}

	public List<Detalleorden> getDetalleordens2() {
		return this.detalleordens2;
	}

	public void setDetalleordens2(List<Detalleorden> detalleordens2) {
		this.detalleordens2 = detalleordens2;
	}

	public Detalleorden addDetalleordens2(Detalleorden detalleordens2) {
		getDetalleordens2().add(detalleordens2);
		detalleordens2.setLugare2(this);

		return detalleordens2;
	}

	public Detalleorden removeDetalleordens2(Detalleorden detalleordens2) {
		getDetalleordens2().remove(detalleordens2);
		detalleordens2.setLugare2(null);

		return detalleordens2;
	}

	public List<Ordenprod> getOrdenprods() {
		return this.ordenprods;
	}

	public void setOrdenprods(List<Ordenprod> ordenprods) {
		this.ordenprods = ordenprods;
	}

	public Ordenprod addOrdenprod(Ordenprod ordenprod) {
		getOrdenprods().add(ordenprod);
		ordenprod.setLugare(this);

		return ordenprod;
	}

	public Ordenprod removeOrdenprod(Ordenprod ordenprod) {
		getOrdenprods().remove(ordenprod);
		ordenprod.setLugare(null);

		return ordenprod;
	}

}