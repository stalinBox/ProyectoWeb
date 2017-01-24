package com.project.entities;

import java.io.Serializable;
import javax.persistence.*;
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

	private Integer standauto;

	private Integer standconv;

	private Integer standman;

	// bi-directional many-to-one association to Lineasturno
	@OneToMany(mappedBy = "parametro")
	private List<Lineasturno> lineasturnos;

	// bi-directional many-to-one association to Ordenprod
	@ManyToOne
	@JoinColumn(name = "ordenprod_codigo")
	private Ordenprod ordenprod;

	// bi-directional many-to-one association to Proceso
	@ManyToOne
	@JoinColumn(name = "pro_codigo")
	private Proceso proceso;

	// bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name = "user_id_resp")
	private Usuario usuario;

	// bi-directional many-to-one association to Programdia
	@OneToMany(mappedBy = "parametro")
	private List<Programdia> programdias;

	public Parametro() {
	}

	public Integer getParamCodigo() {
		return this.paramCodigo;
	}

	public void setParamCodigo(Integer paramCodigo) {
		this.paramCodigo = paramCodigo;
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

	public Integer getStandman() {
		return standman;
	}

	public void setStandman(Integer standman) {
		this.standman = standman;
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

	public Ordenprod getOrdenprod() {
		return this.ordenprod;
	}

	public void setOrdenprod(Ordenprod ordenprod) {
		this.ordenprod = ordenprod;
	}

	public Proceso getProceso() {
		return this.proceso;
	}

	public void setProceso(Proceso proceso) {
		this.proceso = proceso;
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