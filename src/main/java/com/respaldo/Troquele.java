package com.respaldo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the troqueles database table.
 * 
 */
@Entity
@Table(name="troqueles")
@NamedQuery(name="Troquele.findAll", query="SELECT t FROM Troquele t")
public class Troquele implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="trq_codigo")
	private Integer trqCodigo;

	@Column(name="trq_nombre")
	private String trqNombre;

	//bi-directional many-to-one association to ModTrqTal
	@OneToMany(mappedBy="troquele")
	private List<ModTrqTal> modTrqTals;

	//bi-directional many-to-one association to TTalla
	@OneToMany(mappedBy="troquele")
	private List<TTalla> TTallas;

	public Troquele() {
	}

	public Integer getTrqCodigo() {
		return this.trqCodigo;
	}

	public void setTrqCodigo(Integer trqCodigo) {
		this.trqCodigo = trqCodigo;
	}

	public String getTrqNombre() {
		return this.trqNombre;
	}

	public void setTrqNombre(String trqNombre) {
		this.trqNombre = trqNombre;
	}

	public List<ModTrqTal> getModTrqTals() {
		return this.modTrqTals;
	}

	public void setModTrqTals(List<ModTrqTal> modTrqTals) {
		this.modTrqTals = modTrqTals;
	}

	public ModTrqTal addModTrqTal(ModTrqTal modTrqTal) {
		getModTrqTals().add(modTrqTal);
		modTrqTal.setTroquele(this);

		return modTrqTal;
	}

	public ModTrqTal removeModTrqTal(ModTrqTal modTrqTal) {
		getModTrqTals().remove(modTrqTal);
		modTrqTal.setTroquele(null);

		return modTrqTal;
	}

	public List<TTalla> getTTallas() {
		return this.TTallas;
	}

	public void setTTallas(List<TTalla> TTallas) {
		this.TTallas = TTallas;
	}

	public TTalla addTTalla(TTalla TTalla) {
		getTTallas().add(TTalla);
		TTalla.setTroquele(this);

		return TTalla;
	}

	public TTalla removeTTalla(TTalla TTalla) {
		getTTallas().remove(TTalla);
		TTalla.setTroquele(null);

		return TTalla;
	}

}