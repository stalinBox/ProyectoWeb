package com.project.entities;

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

	@Column(name="pro_activo")
	private Boolean proActivo;

	@Column(name="pro_descrip")
	private String proDescrip;

	//bi-directional many-to-one association to Confproceso
	@OneToMany(mappedBy="proceso1")
	private List<Confproceso> confprocesos1;

	//bi-directional many-to-one association to Confproceso
	@OneToMany(mappedBy="proceso2")
	private List<Confproceso> confprocesos2;

	//bi-directional many-to-one association to Costosindi
	@OneToMany(mappedBy="proceso")
	private List<Costosindi> costosindis;

	//bi-directional many-to-one association to Dispositivo
	@OneToMany(mappedBy="proceso")
	private List<Dispositivo> dispositivos;

	//bi-directional many-to-one association to Distribdetalle
	@OneToMany(mappedBy="proceso")
	private List<Distribdetalle> distribdetalles;

	//bi-directional many-to-one association to Lineasprod
	@OneToMany(mappedBy="proceso")
	private List<Lineasprod> lineasprods;

	//bi-directional many-to-one association to Parametro
	@OneToMany(mappedBy="proceso")
	private List<Parametro> parametros;

	//bi-directional many-to-one association to ProcAlerta
	@OneToMany(mappedBy="proceso")
	private List<ProcAlerta> procAlertas;

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

	//bi-directional many-to-one association to Usuario
	@OneToMany(mappedBy="proceso")
	private List<Usuario> usuarios;

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

	public String getProDescrip() {
		return this.proDescrip;
	}

	public void setProDescrip(String proDescrip) {
		this.proDescrip = proDescrip;
	}

	public List<Confproceso> getConfprocesos1() {
		return this.confprocesos1;
	}

	public void setConfprocesos1(List<Confproceso> confprocesos1) {
		this.confprocesos1 = confprocesos1;
	}

	public Confproceso addConfprocesos1(Confproceso confprocesos1) {
		getConfprocesos1().add(confprocesos1);
		confprocesos1.setProceso1(this);

		return confprocesos1;
	}

	public Confproceso removeConfprocesos1(Confproceso confprocesos1) {
		getConfprocesos1().remove(confprocesos1);
		confprocesos1.setProceso1(null);

		return confprocesos1;
	}

	public List<Confproceso> getConfprocesos2() {
		return this.confprocesos2;
	}

	public void setConfprocesos2(List<Confproceso> confprocesos2) {
		this.confprocesos2 = confprocesos2;
	}

	public Confproceso addConfprocesos2(Confproceso confprocesos2) {
		getConfprocesos2().add(confprocesos2);
		confprocesos2.setProceso2(this);

		return confprocesos2;
	}

	public Confproceso removeConfprocesos2(Confproceso confprocesos2) {
		getConfprocesos2().remove(confprocesos2);
		confprocesos2.setProceso2(null);

		return confprocesos2;
	}

	public List<Costosindi> getCostosindis() {
		return this.costosindis;
	}

	public void setCostosindis(List<Costosindi> costosindis) {
		this.costosindis = costosindis;
	}

	public Costosindi addCostosindi(Costosindi costosindi) {
		getCostosindis().add(costosindi);
		costosindi.setProceso(this);

		return costosindi;
	}

	public Costosindi removeCostosindi(Costosindi costosindi) {
		getCostosindis().remove(costosindi);
		costosindi.setProceso(null);

		return costosindi;
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

	public List<Distribdetalle> getDistribdetalles() {
		return this.distribdetalles;
	}

	public void setDistribdetalles(List<Distribdetalle> distribdetalles) {
		this.distribdetalles = distribdetalles;
	}

	public Distribdetalle addDistribdetalle(Distribdetalle distribdetalle) {
		getDistribdetalles().add(distribdetalle);
		distribdetalle.setProceso(this);

		return distribdetalle;
	}

	public Distribdetalle removeDistribdetalle(Distribdetalle distribdetalle) {
		getDistribdetalles().remove(distribdetalle);
		distribdetalle.setProceso(null);

		return distribdetalle;
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

	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario addUsuario(Usuario usuario) {
		getUsuarios().add(usuario);
		usuario.setProceso(this);

		return usuario;
	}

	public Usuario removeUsuario(Usuario usuario) {
		getUsuarios().remove(usuario);
		usuario.setProceso(null);

		return usuario;
	}

}