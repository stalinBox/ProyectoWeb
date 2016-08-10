package com.respaldo;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

/**
 * The persistent class for the parametros database table.
 * 
 */
@Entity
@Table(name = "parametros")
@NamedQuery(name = "Parametro.findAll", query = "SELECT p FROM Parametro p")
public class Parametro implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "param_codigo")
	private Integer paramCodigo;

	private Integer diaslab;

	private Integer numeromaq;

	private Integer standauto;

	private Integer standconv;

	// bi-directional many-to-one association to Lineasprod
	@ManyToOne
	@JoinColumn(name = "lineapro_codigo")
	private Lineasprod lineasprod;

	// bi-directional many-to-one association to Proceso
	@ManyToOne
	@JoinColumn(name = "pro_codigo")
	private Proceso proceso;

	// bi-directional many-to-one association to Turno
	@ManyToOne
	@JoinColumn(name = "turno_codigo")
	private Turno turno;

	// bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name = "user_id_resp")
	private Usuario usuario;

	// bi-directional many-to-one association to Programdia
	@OneToMany(mappedBy = "parametro", fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Programdia> programdias;

	public Parametro() {
	}

	public Integer getParamCodigo() {
		return this.paramCodigo;
	}

	public void setParamCodigo(Integer paramCodigo) {
		this.paramCodigo = paramCodigo;
	}

	public Integer getDiaslab() {
		return this.diaslab;
	}

	public void setDiaslab(Integer diaslab) {
		this.diaslab = diaslab;
	}

	public Integer getNumeromaq() {
		return this.numeromaq;
	}

	public void setNumeromaq(Integer numeromaq) {
		this.numeromaq = numeromaq;
	}

	public Integer getStandauto() {
		return this.standauto;
	}

	public void setStandauto(Integer standauto) {
		this.standauto = standauto;
	}

	public Integer getStandconv() {
		return this.standconv;
	}

	public void setStandconv(Integer standconv) {
		this.standconv = standconv;
	}

	public Lineasprod getLineasprod() {
		return this.lineasprod;
	}

	public void setLineasprod(Lineasprod lineasprod) {
		this.lineasprod = lineasprod;
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

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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