package com.project.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the capacidades database table.
 * 
 */
@Entity
@Table(name="capacidades")
@NamedQuery(name="Capacidade.findAll", query="SELECT c FROM Capacidade c")
public class Capacidade implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cap_codigo")
	private Integer capCodigo;

	private Integer standar;

	//bi-directional many-to-one association to Distribdetalle
	@ManyToOne
	@JoinColumn(name="distrib_codigo")
	private Distribdetalle distribdetalle;

	//bi-directional many-to-one association to Ordenprod
	@ManyToOne
	@JoinColumn(name="ordenprod_codigo")
	private Ordenprod ordenprod;

	//bi-directional many-to-one association to Lineasturno
	@OneToMany(mappedBy="capacidade")
	private List<Lineasturno> lineasturnos;

	//bi-directional many-to-one association to Programdia
	@OneToMany(mappedBy="capacidade")
	private List<Programdia> programdias;

	public Capacidade() {
	}

	public Integer getCapCodigo() {
		return this.capCodigo;
	}

	public void setCapCodigo(Integer capCodigo) {
		this.capCodigo = capCodigo;
	}

	public Integer getStandar() {
		return this.standar;
	}

	public void setStandar(Integer standar) {
		this.standar = standar;
	}

	public Distribdetalle getDistribdetalle() {
		return this.distribdetalle;
	}

	public void setDistribdetalle(Distribdetalle distribdetalle) {
		this.distribdetalle = distribdetalle;
	}

	public Ordenprod getOrdenprod() {
		return this.ordenprod;
	}

	public void setOrdenprod(Ordenprod ordenprod) {
		this.ordenprod = ordenprod;
	}

	public List<Lineasturno> getLineasturnos() {
		return this.lineasturnos;
	}

	public void setLineasturnos(List<Lineasturno> lineasturnos) {
		this.lineasturnos = lineasturnos;
	}

	public Lineasturno addLineasturno(Lineasturno lineasturno) {
		getLineasturnos().add(lineasturno);
		lineasturno.setCapacidade(this);

		return lineasturno;
	}

	public Lineasturno removeLineasturno(Lineasturno lineasturno) {
		getLineasturnos().remove(lineasturno);
		lineasturno.setCapacidade(null);

		return lineasturno;
	}

	public List<Programdia> getProgramdias() {
		return this.programdias;
	}

	public void setProgramdias(List<Programdia> programdias) {
		this.programdias = programdias;
	}

	public Programdia addProgramdia(Programdia programdia) {
		getProgramdias().add(programdia);
		programdia.setCapacidade(this);

		return programdia;
	}

	public Programdia removeProgramdia(Programdia programdia) {
		getProgramdias().remove(programdia);
		programdia.setCapacidade(null);

		return programdia;
	}

}