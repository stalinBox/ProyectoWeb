package com.project.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the dispositivos database table.
 * 
 */
@Entity
@Table(name="dispositivos")
@NamedQuery(name="Dispositivo.findAll", query="SELECT d FROM Dispositivo d")
public class Dispositivo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_dispositivo")
	private Integer idDispositivo;

	@Column(name="mac_dispositivo")
	private String macDispositivo;

	@Column(name="nombre_dis")
	private String nombreDis;

	//bi-directional many-to-one association to Maquina
	@ManyToOne
	@JoinColumn(name="id_maquinas_ref")
	private Maquina maquina;

	//bi-directional many-to-one association to Proceso
	@ManyToOne
	@JoinColumn(name="pro_codigo_ref")
	private Proceso proceso;

	//bi-directional many-to-one association to ProcAlerta
	@OneToMany(mappedBy="dispositivo")
	private List<ProcAlerta> procAlertas;

	public Dispositivo() {
	}

	public Integer getIdDispositivo() {
		return this.idDispositivo;
	}

	public void setIdDispositivo(Integer idDispositivo) {
		this.idDispositivo = idDispositivo;
	}

	public String getMacDispositivo() {
		return this.macDispositivo;
	}

	public void setMacDispositivo(String macDispositivo) {
		this.macDispositivo = macDispositivo;
	}

	public String getNombreDis() {
		return this.nombreDis;
	}

	public void setNombreDis(String nombreDis) {
		this.nombreDis = nombreDis;
	}

	public Maquina getMaquina() {
		return this.maquina;
	}

	public void setMaquina(Maquina maquina) {
		this.maquina = maquina;
	}

	public Proceso getProceso() {
		return this.proceso;
	}

	public void setProceso(Proceso proceso) {
		this.proceso = proceso;
	}

	public List<ProcAlerta> getProcAlertas() {
		return this.procAlertas;
	}

	public void setProcAlertas(List<ProcAlerta> procAlertas) {
		this.procAlertas = procAlertas;
	}

	public ProcAlerta addProcAlerta(ProcAlerta procAlerta) {
		getProcAlertas().add(procAlerta);
		procAlerta.setDispositivo(this);

		return procAlerta;
	}

	public ProcAlerta removeProcAlerta(ProcAlerta procAlerta) {
		getProcAlertas().remove(procAlerta);
		procAlerta.setDispositivo(null);

		return procAlerta;
	}

}