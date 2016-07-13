package com.project.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the tallas database table.
 * 
 */
@Entity
@Table(name = "tallas")
@NamedQuery(name = "Talla.findAll", query = "SELECT t FROM Talla t")
public class Talla implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tal_codigo")
	private Integer talCodigo;

	@Column(name = "tal_numero")
	private Integer talNumero;

	// bi-directional many-to-one association to ModTrqTal
	@OneToMany(mappedBy = "talla")
	private List<ModTrqTal> modTrqTals;

	// bi-directional many-to-one association to TTalla
	@OneToMany(mappedBy = "talla")
	private List<TTalla> TTallas;

	public Talla() {
	}

	public Integer getTalCodigo() {
		return this.talCodigo;
	}

	public void setTalCodigo(Integer talCodigo) {
		this.talCodigo = talCodigo;
	}

	public Integer getTalNumero() {
		return this.talNumero;
	}

	public void setTalNumero(Integer talNumero) {
		this.talNumero = talNumero;
	}

	public List<ModTrqTal> getModTrqTals() {
		return this.modTrqTals;
	}

	public void setModTrqTals(List<ModTrqTal> modTrqTals) {
		this.modTrqTals = modTrqTals;
	}

	public ModTrqTal addModTrqTal(ModTrqTal modTrqTal) {
		getModTrqTals().add(modTrqTal);
		modTrqTal.setTalla(this);

		return modTrqTal;
	}

	public ModTrqTal removeModTrqTal(ModTrqTal modTrqTal) {
		getModTrqTals().remove(modTrqTal);
		modTrqTal.setTalla(null);

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
		TTalla.setTalla(this);

		return TTalla;
	}

	public TTalla removeTTalla(TTalla TTalla) {
		getTTallas().remove(TTalla);
		TTalla.setTalla(null);

		return TTalla;
	}

}