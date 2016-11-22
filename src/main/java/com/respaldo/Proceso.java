package com.respaldo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the procesos database table.
 * 
 */
@Entity
@Table(name="procesos")
@NamedQuery(name="Proceso.findAll", query="SELECT p FROM Proceso p")
public class Proceso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pro_codigo")
	private Integer proCodigo;

	@Column(name="pro_auto")
	private String proAuto;

	@Column(name="pro_cap")
	private Integer proCap;

	@Column(name="pro_descrip")
	private String proDescrip;

	@Column(name="pro_duracion")
	private double proDuracion;

	@Column(name="pro_manobra")
	private double proManobra;

	@Column(name="pro_manreal")
	private double proManreal;

	@Column(name="pro_tbase")
	private double proTbase;

	@Column(name="pro_tmano")
	private double proTmano;

	@Column(name="pro_tmaq")
	private double proTmaq;

	@Column(name="pro_ts")
	private double proTs;

	//bi-directional many-to-one association to Parametro
	@OneToMany(mappedBy="proceso")
	private List<Parametro> parametros;

	//bi-directional many-to-one association to Modelo
	@ManyToOne
	@JoinColumn(name="mod_codigo")
	private Modelo modelo;

	//bi-directional many-to-one association to Proceso
	@ManyToOne
	@JoinColumn(name="pro_padre")
	private Proceso proceso;

	//bi-directional many-to-one association to Proceso
	@OneToMany(mappedBy="proceso")
	private List<Proceso> procesos;

	//bi-directional many-to-one association to TipoProceso
	@ManyToOne
	@JoinColumn(name="tpr_codigo")
	private TipoProceso tipoProceso;

	//bi-directional many-to-one association to Procesosop
	@OneToMany(mappedBy="proceso")
	private List<Procesosop> procesosops;

	public Proceso() {
	}

	public Integer getProCodigo() {
		return this.proCodigo;
	}

	public void setProCodigo(Integer proCodigo) {
		this.proCodigo = proCodigo;
	}

	public String getProAuto() {
		return this.proAuto;
	}

	public void setProAuto(String proAuto) {
		this.proAuto = proAuto;
	}

	public Integer getProCap() {
		return this.proCap;
	}

	public void setProCap(Integer proCap) {
		this.proCap = proCap;
	}

	public String getProDescrip() {
		return this.proDescrip;
	}

	public void setProDescrip(String proDescrip) {
		this.proDescrip = proDescrip;
	}

	public double getProDuracion() {
		return this.proDuracion;
	}

	public void setProDuracion(double proDuracion) {
		this.proDuracion = proDuracion;
	}

	public double getProManobra() {
		return this.proManobra;
	}

	public void setProManobra(double proManobra) {
		this.proManobra = proManobra;
	}

	public double getProManreal() {
		return this.proManreal;
	}

	public void setProManreal(double proManreal) {
		this.proManreal = proManreal;
	}

	public double getProTbase() {
		return this.proTbase;
	}

	public void setProTbase(double proTbase) {
		this.proTbase = proTbase;
	}

	public double getProTmano() {
		return this.proTmano;
	}

	public void setProTmano(double proTmano) {
		this.proTmano = proTmano;
	}

	public double getProTmaq() {
		return this.proTmaq;
	}

	public void setProTmaq(double proTmaq) {
		this.proTmaq = proTmaq;
	}

	public double getProTs() {
		return this.proTs;
	}

	public void setProTs(double proTs) {
		this.proTs = proTs;
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

}