package com.respaldo;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.sql.Time;
import java.util.List;

/**
 * The persistent class for the turnos database table.
 * 
 */
@Entity
@Table(name = "turnos")
@NamedQuery(name = "Turno.findAll", query = "SELECT t FROM Turno t")
public class Turno implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "turno_codigo")
	private Integer turnoCodigo;

	@Column(name = "h_fin")
	private Time hFin;

	@Column(name = "h_inicio")
	private Time hInicio;

	private String nombturno;

	// bi-directional many-to-one association to Lineasprod
	@OneToMany(mappedBy = "turno", fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Lineasprod> lineasprods;

	// bi-directional many-to-one association to Parametro
	@OneToMany(mappedBy = "turno", fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Parametro> parametros;

	// bi-directional many-to-one association to Programturno
	@OneToMany(mappedBy = "turno", fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Programturno> programturnos;

	public Turno() {
	}

	public Integer getTurnoCodigo() {
		return this.turnoCodigo;
	}

	public void setTurnoCodigo(Integer turnoCodigo) {
		this.turnoCodigo = turnoCodigo;
	}

	public Time getHFin() {
		return this.hFin;
	}

	public void setHFin(Time hFin) {
		this.hFin = hFin;
	}

	public Time getHInicio() {
		return this.hInicio;
	}

	public void setHInicio(Time hInicio) {
		this.hInicio = hInicio;
	}

	public String getNombturno() {
		return this.nombturno;
	}

	public void setNombturno(String nombturno) {
		this.nombturno = nombturno;
	}

	public List<Lineasprod> getLineasprods() {
		return this.lineasprods;
	}

	public void setLineasprods(List<Lineasprod> lineasprods) {
		this.lineasprods = lineasprods;
	}

	public Lineasprod addLineasprod(Lineasprod lineasprod) {
		getLineasprods().add(lineasprod);
		lineasprod.setTurno(this);

		return lineasprod;
	}

	public Lineasprod removeLineasprod(Lineasprod lineasprod) {
		getLineasprods().remove(lineasprod);
		lineasprod.setTurno(null);

		return lineasprod;
	}

	public List<Parametro> getParametros() {
		return this.parametros;
	}

	public void setParametros(List<Parametro> parametros) {
		this.parametros = parametros;
	}

	public Parametro addParametro(Parametro parametro) {
		getParametros().add(parametro);
		parametro.setTurno(this);

		return parametro;
	}

	public Parametro removeParametro(Parametro parametro) {
		getParametros().remove(parametro);
		parametro.setTurno(null);

		return parametro;
	}

	public List<Programturno> getProgramturnos() {
		return this.programturnos;
	}

	public void setProgramturnos(List<Programturno> programturnos) {
		this.programturnos = programturnos;
	}

	public Programturno addProgramturno(Programturno programturno) {
		getProgramturnos().add(programturno);
		programturno.setTurno(this);

		return programturno;
	}

	public Programturno removeProgramturno(Programturno programturno) {
		getProgramturnos().remove(programturno);
		programturno.setTurno(null);

		return programturno;
	}

}