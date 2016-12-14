package com.project.entities;

import java.io.Serializable;
import javax.persistence.*;
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

	@Column(name = "pro_activo")
	private Boolean proActivo;

	@Column(name = "pro_auto")
	private Boolean proAuto;

	@Column(name = "pro_cap")
	private Integer proCap;

	@Column(name = "pro_cifpre")
	private double proCifpre;

	@Column(name = "pro_cifref")
	private double proCifref;

	@Column(name = "pro_costmanobra")
	private double proCostmanobra;

	@Column(name = "pro_costmanreal")
	private double proCostmanreal;

	@Column(name = "pro_descrip")
	private String proDescrip;

	@Column(name = "pro_duracion")
	private double proDuracion;

	@Column(name = "pro_num_trab")
	private Integer proNumTrab;

	@Column(name = "pro_tmano")
	private double proTmano;

	@Column(name = "pro_ts")
	private double proTs;

	// bi-directional many-to-one association to Dispositivo
	@OneToMany(mappedBy = "proceso")
	private List<Dispositivo> dispositivos;

	// bi-directional many-to-one association to Parametro
	@OneToMany(mappedBy = "proceso")
	private List<Parametro> parametros;

	// bi-directional many-to-one association to ProcAlerta
	@OneToMany(mappedBy = "proceso")
	private List<ProcAlerta> procAlertas;

	// bi-directional many-to-one association to Modelo
	@ManyToOne
	@JoinColumn(name = "mod_codigo", insertable = false, updatable = false, nullable = false)
	private Modelo modelo;

	// bi-directional many-to-one association to Proceso
	@ManyToOne
	@JoinColumn(name = "pro_padre", insertable = false, updatable = false, nullable = false)
	private Proceso proceso;

	// bi-directional many-to-one association to Proceso
	@OneToMany(mappedBy = "proceso")
	private List<Proceso> procesos;

	// bi-directional many-to-one association to TipoProceso
	@ManyToOne
	@JoinColumn(name = "tpr_codigo", insertable = false, updatable = false, nullable = false)
	private TipoProceso tipoProceso;

	// bi-directional many-to-one association to Procesosop
	@OneToMany(mappedBy = "proceso")
	private List<Procesosop> procesosops;

	public Proceso() {
	}

	public Integer getProCodigo() {
		return this.proCodigo;
	}

	public void setProCodigo(Integer proCodigo) {
		this.proCodigo = proCodigo;
	}

	public Boolean getProActivo() {
		return this.proActivo;
	}

	public void setProActivo(Boolean proActivo) {
		this.proActivo = proActivo;
	}

	public Boolean getProAuto() {
		return this.proAuto;
	}

	public void setProAuto(Boolean proAuto) {
		this.proAuto = proAuto;
	}

	public Integer getProCap() {
		return this.proCap;
	}

	public void setProCap(Integer proCap) {
		this.proCap = proCap;
	}

	public double getProCifpre() {
		return this.proCifpre;
	}

	public void setProCifpre(double proCifpre) {
		this.proCifpre = proCifpre;
	}

	public double getProCifref() {
		return this.proCifref;
	}

	public void setProCifref(double proCifref) {
		this.proCifref = proCifref;
	}

	public double getProCostmanobra() {
		return this.proCostmanobra;
	}

	public void setProCostmanobra(double proCostmanobra) {
		this.proCostmanobra = proCostmanobra;
	}

	public double getProCostmanreal() {
		return this.proCostmanreal;
	}

	public void setProCostmanreal(double proCostmanreal) {
		this.proCostmanreal = proCostmanreal;
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

	public Integer getProNumTrab() {
		return this.proNumTrab;
	}

	public void setProNumTrab(Integer proNumTrab) {
		this.proNumTrab = proNumTrab;
	}

	public double getProTmano() {
		return this.proTmano;
	}

	public void setProTmano(double proTmano) {
		this.proTmano = proTmano;
	}

	public double getProTs() {
		return this.proTs;
	}

	public void setProTs(double proTs) {
		this.proTs = proTs;
	}

	public List<Dispositivo> getDispositivos() {
		return this.dispositivos;
	}

	public void setDispositivos(List<Dispositivo> dispositivos) {
		this.dispositivos = dispositivos;
	}

	public Dispositivo addDispositivo(Dispositivo dispositivo) {
		getDispositivos().add(dispositivo);
		dispositivo.setProceso(this);

		return dispositivo;
	}

	public Dispositivo removeDispositivo(Dispositivo dispositivo) {
		getDispositivos().remove(dispositivo);
		dispositivo.setProceso(null);

		return dispositivo;
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

	public List<ProcAlerta> getProcAlertas() {
		return this.procAlertas;
	}

	public void setProcAlertas(List<ProcAlerta> procAlertas) {
		this.procAlertas = procAlertas;
	}

	public ProcAlerta addProcAlerta(ProcAlerta procAlerta) {
		getProcAlertas().add(procAlerta);
		procAlerta.setProceso(this);

		return procAlerta;
	}

	public ProcAlerta removeProcAlerta(ProcAlerta procAlerta) {
		getProcAlertas().remove(procAlerta);
		procAlerta.setProceso(null);

		return procAlerta;
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