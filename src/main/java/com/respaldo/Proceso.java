package com.respaldo;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * The persistent class for the procesos database table.
 * 
 */
@Entity
@Table(name = "procesos")
@NamedQuery(name = "Proceso.findAll", query = "SELECT p FROM Proceso p")
public class Proceso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pro_codigo")
	private Integer proCodigo;

	@Column(name = "pro_cap")
	private Integer proCap;

	@Column(name = "pro_duracion")
	private BigDecimal proDuracion;

	// bi-directional many-to-one association to Lineasprod
	@OneToMany(mappedBy = "proceso")
	private List<Lineasprod> lineasprods;

	// bi-directional many-to-one association to Parametro
	@OneToMany(mappedBy = "proceso")
	private List<Parametro> parametros;

	// bi-directional many-to-one association to Modelo
	@ManyToOne
	@JoinColumn(name = "mod_codigo")
	private Modelo modelo;

	// bi-directional many-to-one association to Proceso
	@ManyToOne
	@JoinColumn(name = "pro_padre")
	private Proceso proceso;

	// bi-directional many-to-one association to Proceso
	@OneToMany(mappedBy = "proceso")
	private List<Proceso> procesos;

	// bi-directional many-to-one association to TipoProceso
	@ManyToOne
	@JoinColumn(name = "tpr_codigo")
	private TipoProceso tipoProceso;

	// bi-directional many-to-one association to Procesosop
	@OneToMany(mappedBy = "proceso")
	private List<Procesosop> procesosops;

	// bi-directional many-to-one association to Programturno
	@OneToMany(mappedBy = "proceso")
	private List<Programturno> programturnos;

	public Proceso() {
	}

	public Integer getProCodigo() {
		return this.proCodigo;
	}

	public void setProCodigo(Integer proCodigo) {
		this.proCodigo = proCodigo;
	}

	public Integer getProCap() {
		return this.proCap;
	}

	public void setProCap(Integer proCap) {
		this.proCap = proCap;
	}

	public BigDecimal getProDuracion() {
		return this.proDuracion;
	}

	public void setProDuracion(BigDecimal proDuracion) {
		this.proDuracion = proDuracion;
	}

	public List<Lineasprod> getLineasprods() {
		return this.lineasprods;
	}

	public void setLineasprods(List<Lineasprod> lineasprods) {
		this.lineasprods = lineasprods;
	}

	public Lineasprod addLineasprod(Lineasprod lineasprod) {
		getLineasprods().add(lineasprod);
		lineasprod.setProceso(this);

		return lineasprod;
	}

	public Lineasprod removeLineasprod(Lineasprod lineasprod) {
		getLineasprods().remove(lineasprod);
		lineasprod.setProceso(null);

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
		parametro.setProceso(this);

		return parametro;
	}

	public Parametro removeParametro(Parametro parametro) {
		getParametros().remove(parametro);
		parametro.setProceso(null);

		return parametro;
	}

	public Modelo getModelo() {
		return this.modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	public Proceso getProceso() {
		return this.proceso;
	}

	public void setProceso(Proceso proceso) {
		this.proceso = proceso;
	}

	public List<Proceso> getProcesos() {
		return this.procesos;
	}

	public void setProcesos(List<Proceso> procesos) {
		this.procesos = procesos;
	}

	public Proceso addProceso(Proceso proceso) {
		getProcesos().add(proceso);
		proceso.setProceso(this);

		return proceso;
	}

	public Proceso removeProceso(Proceso proceso) {
		getProcesos().remove(proceso);
		proceso.setProceso(null);

		return proceso;
	}

	public TipoProceso getTipoProceso() {
		return this.tipoProceso;
	}

	public void setTipoProceso(TipoProceso tipoProceso) {
		this.tipoProceso = tipoProceso;
	}

	public List<Procesosop> getProcesosops() {
		return this.procesosops;
	}

	public void setProcesosops(List<Procesosop> procesosops) {
		this.procesosops = procesosops;
	}

	public Procesosop addProcesosop(Procesosop procesosop) {
		getProcesosops().add(procesosop);
		procesosop.setProceso(this);

		return procesosop;
	}

	public Procesosop removeProcesosop(Procesosop procesosop) {
		getProcesosops().remove(procesosop);
		procesosop.setProceso(null);

		return procesosop;
	}

	public List<Programturno> getProgramturnos() {
		return this.programturnos;
	}

	public void setProgramturnos(List<Programturno> programturnos) {
		this.programturnos = programturnos;
	}

	public Programturno addProgramturno(Programturno programturno) {
		getProgramturnos().add(programturno);
		programturno.setProceso(this);

		return programturno;
	}

	public Programturno removeProgramturno(Programturno programturno) {
		getProgramturnos().remove(programturno);
		programturno.setProceso(null);

		return programturno;
	}

}