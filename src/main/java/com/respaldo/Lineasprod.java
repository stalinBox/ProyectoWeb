package com.respaldo;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

/**
 * The persistent class for the lineasprod database table.
 * 
 */
@Entity
@NamedQuery(name = "Lineasprod.findAll", query = "SELECT l FROM Lineasprod l")
public class Lineasprod implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "lineapro_codigo")
	private Integer lineaproCodigo;

	private String nomlinea;

	// bi-directional many-to-one association to Proceso
	@ManyToOne
	@JoinColumn(name = "pro_codigo", nullable = false, insertable = false, updatable = false)
	private Proceso proceso;

	// bi-directional many-to-one association to Turno
	@ManyToOne
	@JoinColumn(name = "turno_codigo", nullable = false, insertable = false, updatable = false)
	private Turno turno;

	// bi-directional many-to-one association to Parametro
	@OneToMany(mappedBy = "lineasprod", fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Parametro> parametros;

	// bi-directional many-to-one association to Programdia
	@OneToMany(mappedBy = "lineasprod", fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Programdia> programdias;

	// bi-directional many-to-one association to Programturno
	@OneToMany(mappedBy = "lineasprod", fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Programturno> programturnos;

	public Lineasprod() {
	}

	public Integer getLineaproCodigo() {
		return this.lineaproCodigo;
	}

	public void setLineaproCodigo(Integer lineaproCodigo) {
		this.lineaproCodigo = lineaproCodigo;
	}

	public String getNomlinea() {
		return this.nomlinea;
	}

	public void setNomlinea(String nomlinea) {
		this.nomlinea = nomlinea;
	}

	public Proceso getProceso() {
		return this.proceso;
	}

	public void setProceso(Proceso proceso) {
		this.proceso = proceso;
	}

	public Turno getTurno() {
		return this.turno;
	}

	public void setTurno(Turno turno) {
		this.turno = turno;
	}

	public List<Parametro> getParametros() {
		return this.parametros;
	}

	public void setParametros(List<Parametro> parametros) {
		this.parametros = parametros;
	}

	public Parametro addParametro(Parametro parametro) {
		getParametros().add(parametro);
		parametro.setLineasprod(this);

		return parametro;
	}

	public Parametro removeParametro(Parametro parametro) {
		getParametros().remove(parametro);
		parametro.setLineasprod(null);

		return parametro;
	}

	public List<Programdia> getProgramdias() {
		return this.programdias;
	}

	public void setProgramdias(List<Programdia> programdias) {
		this.programdias = programdias;
	}

	public Programdia addProgramdia(Programdia programdia) {
		getProgramdias().add(programdia);
		programdia.setLineasprod(this);

		return programdia;
	}

	public Programdia removeProgramdia(Programdia programdia) {
		getProgramdias().remove(programdia);
		programdia.setLineasprod(null);

		return programdia;
	}

	public List<Programturno> getProgramturnos() {
		return this.programturnos;
	}

	public void setProgramturnos(List<Programturno> programturnos) {
		this.programturnos = programturnos;
	}

	public Programturno addProgramturno(Programturno programturno) {
		getProgramturnos().add(programturno);
		programturno.setLineasprod(this);

		return programturno;
	}

	public Programturno removeProgramturno(Programturno programturno) {
		getProgramturnos().remove(programturno);
		programturno.setLineasprod(null);

		return programturno;
	}

}