package com.project.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the logosfaps database table.
 * 
 */
@Entity
@Table(name="logosfaps")
@NamedQuery(name="Logosfap.findAll", query="SELECT l FROM Logosfap l")
public class Logosfap implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idlogos;

	private byte[] logos;

	private String nomlogo;

	public Logosfap() {
	}

	public Integer getIdlogos() {
		return this.idlogos;
	}

	public void setIdlogos(Integer idlogos) {
		this.idlogos = idlogos;
	}

	public byte[] getLogos() {
		return this.logos;
	}

	public void setLogos(byte[] logos) {
		this.logos = logos;
	}

	public String getNomlogo() {
		return this.nomlogo;
	}

	public void setNomlogo(String nomlogo) {
		this.nomlogo = nomlogo;
	}

}