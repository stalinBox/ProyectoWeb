package com.respaldo;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the procesosop database table.
 * 
 */
@Embeddable
public class ProcesosopPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="pro_codigo", insertable=false, updatable=false)
	private Integer proCodigo;

	@Column(name="ordenprod_codigo", insertable=false, updatable=false)
	private String ordenprodCodigo;

	public ProcesosopPK() {
	}
	public Integer getProCodigo() {
		return this.proCodigo;
	}
	public void setProCodigo(Integer proCodigo) {
		this.proCodigo = proCodigo;
	}
	public String getOrdenprodCodigo() {
		return this.ordenprodCodigo;
	}
	public void setOrdenprodCodigo(String ordenprodCodigo) {
		this.ordenprodCodigo = ordenprodCodigo;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ProcesosopPK)) {
			return false;
		}
		ProcesosopPK castOther = (ProcesosopPK)other;
		return 
			this.proCodigo.equals(castOther.proCodigo)
			&& this.ordenprodCodigo.equals(castOther.ordenprodCodigo);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.proCodigo.hashCode();
		hash = hash * prime + this.ordenprodCodigo.hashCode();
		
		return hash;
	}
}