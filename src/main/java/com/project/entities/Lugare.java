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
@Table(name = "lugares")
@NamedQuery(name = "Lugare.findAll", query = "SELECT l FROM Lugare l")
public class Lugare implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "lugar_codigo")
	private Integer lugarCodigo;

	private String nomlugar;

	// bi-directional many-to-one association to Ordenprod
	@OneToMany(mappedBy = "lugare", fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Ordenprod> ordenprods;

	// bi-directional many-to-one association to Procesosop
	@OneToMany(mappedBy = "lugare1", fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Procesosop> procesosops1;

	// bi-directional many-to-one association to Procesosop
	@OneToMany(mappedBy = "lugare2", fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Procesosop> procesosops2;

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

	public List<Procesosop> getProcesosops1() {
		return this.procesosops1;
	}

	public void setProcesosops1(List<Procesosop> procesosops1) {
		this.procesosops1 = procesosops1;
	}

	public Procesosop addProcesosops1(Procesosop procesosops1) {
		getProcesosops1().add(procesosops1);
		procesosops1.setLugare1(this);

		return procesosops1;
	}

	public Procesosop removeProcesosops1(Procesosop procesosops1) {
		getProcesosops1().remove(procesosops1);
		procesosops1.setLugare1(null);

		return procesosops1;
	}

	public List<Procesosop> getProcesosops2() {
		return this.procesosops2;
	}

	public void setProcesosops2(List<Procesosop> procesosops2) {
		this.procesosops2 = procesosops2;
	}

	public Procesosop addProcesosops2(Procesosop procesosops2) {
		getProcesosops2().add(procesosops2);
		procesosops2.setLugare2(this);

		return procesosops2;
	}

	public Procesosop removeProcesosops2(Procesosop procesosops2) {
		getProcesosops2().remove(procesosops2);
		procesosops2.setLugare2(null);

		return procesosops2;
	}

}