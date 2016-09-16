package com.respaldo;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the t_tallas database table.
 * 
 */
@Embeddable
public class TTallaPK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name = "trq_codigo", insertable = false, updatable = false)
	private Integer trqCodigo;

	@Column(name = "tal_codigo", insertable = false, updatable = false)
	private Integer talCodigo;

	public TTallaPK() {
	}

	public Integer getTrqCodigo() {
		return this.trqCodigo;
	}

	public void setTrqCodigo(Integer trqCodigo) {
		this.trqCodigo = trqCodigo;
	}

	public Integer getTalCodigo() {
		return this.talCodigo;
	}

	public void setTalCodigo(Integer talCodigo) {
		this.talCodigo = talCodigo;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TTallaPK)) {
			return false;
		}
		TTallaPK castOther = (TTallaPK) other;
		return this.trqCodigo.equals(castOther.trqCodigo)
				&& this.talCodigo.equals(castOther.talCodigo);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.trqCodigo.hashCode();
		hash = hash * prime + this.talCodigo.hashCode();

		return hash;
	}
}