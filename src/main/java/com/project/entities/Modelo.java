package com.project.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the modelos database table.
 * 
 */
@Entity
@Table(name = "modelos")
@NamedQuery(name = "Modelo.findAll", query = "SELECT m FROM Modelo m")
public class Modelo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "mod_codigo")
	private Integer modCodigo;

	@Column(name = "mod_nombre")
	private String modNombre;

	@Column(name = "mod_piezas")
	private Integer modPiezas;

	// bi-directional many-to-one association to ModTrqTal
	@OneToMany(mappedBy = "modelo")
	private List<ModTrqTal> modTrqTals;

	// bi-directional many-to-one association to Proceso
	@OneToMany(mappedBy = "modelo")
	private List<Proceso> procesos;

	public Modelo() {
	}

	public Integer getModCodigo() {
		return this.modCodigo;
	}

	public void setModCodigo(Integer modCodigo) {
		this.modCodigo = modCodigo;
	}

	public String getModNombre() {
		return this.modNombre;
	}

	public void setModNombre(String modNombre) {
		this.modNombre = modNombre;
	}

	public Integer getModPiezas() {
		return this.modPiezas;
	}

	public void setModPiezas(Integer modPiezas) {
		this.modPiezas = modPiezas;
	}

	public List<ModTrqTal> getModTrqTals() {
		return this.modTrqTals;
	}

	public void setModTrqTals(List<ModTrqTal> modTrqTals) {
		this.modTrqTals = modTrqTals;
	}

	public ModTrqTal addModTrqTal(ModTrqTal modTrqTal) {
		getModTrqTals().add(modTrqTal);
		modTrqTal.setModelo(this);

		return modTrqTal;
	}

	public ModTrqTal removeModTrqTal(ModTrqTal modTrqTal) {
		getModTrqTals().remove(modTrqTal);
		modTrqTal.setModelo(null);

		return modTrqTal;
	}

	public List<Proceso> getProcesos() {
		return this.procesos;
	}

	public void setProcesos(List<Proceso> procesos) {
		this.procesos = procesos;
	}

	public Proceso addProceso(Proceso proceso) {
		getProcesos().add(proceso);
		proceso.setModelo(this);

		return proceso;
	}

	public Proceso removeProceso(Proceso proceso) {
		getProcesos().remove(proceso);
		proceso.setModelo(null);

		return proceso;
	}

}