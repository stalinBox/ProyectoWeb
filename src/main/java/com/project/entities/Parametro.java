package com.project.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the parametros database table.
 * 
 */
@Entity
@Table(name="parametros")
@NamedQuery(name="Parametro.findAll", query="SELECT p FROM Parametro p")
public class Parametro implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="param_codigo")
	private Integer paramCodigo;

	private Integer standar;

	//bi-directional many-to-one association to Lineasturno
	@OneToMany(mappedBy="parametro")
	private List<Lineasturno> lineasturnos;

	//bi-directional many-to-one association to Distribdetalle
	@ManyToOne
	@JoinColumn(name="distrib_codigo")
	private Distribdetalle distribdetalle;

	//bi-directional many-to-one association to Ordenprod
	@ManyToOne
	@JoinColumn(name="ordenprod_codigo")
	private Ordenprod ordenprod;

	//bi-directional many-to-one association to Programdia
	@OneToMany(mappedBy="parametro")
	private List<Programdia> programdias;

	public Parametro() {
	}

	public Integer getParamCodigo() {
		return this.paramCodigo;
	}

	public void setParamCodigo(Integer paramCodigo) {
		this.paramCodigo = paramCodigo;
	}

	public Integer getStandar() {
		return this.standar;
	}

	public void setStandar(Integer standar) {
		this.standar = standar;
	}

	public List<Lineasturno> getLineasturnos() {
		return this.lineasturnos;
	}

	public void setLineasturnos(List<Lineasturno> lineasturnos) {
		this.lineasturnos = lineasturnos;
	}

	public Lineasturno addLineasturno(Lineasturno lineasturno) {
		getLineasturnos().add(lineasturno);
		lineasturno.setParametro(this);

		return lineasturno;
	}

	public Lineasturno removeLineasturno(Lineasturno lineasturno) {
		getLineasturnos().remove(lineasturno);
		lineasturno.setParametro(null);

		return lineasturno;
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

	public List<Programdia> getProgramdias() {
		return this.programdias;
	}

	public void setProgramdias(List<Programdia> programdias) {
		this.programdias = programdias;
	}

	public Programdia addProgramdia(Programdia programdia) {
		getProgramdias().add(programdia);
		programdia.setParametro(this);

		return programdia;
	}

	public Programdia removeProgramdia(Programdia programdia) {
		getProgramdias().remove(programdia);
		programdia.setParametro(null);

		return programdia;
	}

}