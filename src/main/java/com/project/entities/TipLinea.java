package com.project.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tip_linea database table.
 * 
 */
@Entity
@Table(name="tip_linea")
@NamedQuery(name="TipLinea.findAll", query="SELECT t FROM TipLinea t")
public class TipLinea implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="codigo_tiplinea")
	private Integer codigoTiplinea;

	private String desctiplinea;

	private String tipolinea;

	//bi-directional many-to-one association to Lineasprod
	@OneToMany(mappedBy="tipLinea")
	private List<Lineasprod> lineasprods;

	public TipLinea() {
	}

	public Integer getCodigoTiplinea() {
		return this.codigoTiplinea;
	}

	public void setCodigoTiplinea(Integer codigoTiplinea) {
		this.codigoTiplinea = codigoTiplinea;
	}

	public String getDesctiplinea() {
		return this.desctiplinea;
	}

	public void setDesctiplinea(String desctiplinea) {
		this.desctiplinea = desctiplinea;
	}

	public String getTipolinea() {
		return this.tipolinea;
	}

	public void setTipolinea(String tipolinea) {
		this.tipolinea = tipolinea;
	}

	public List<Lineasprod> getLineasprods() {
		return this.lineasprods;
	}

	public void setLineasprods(List<Lineasprod> lineasprods) {
		this.lineasprods = lineasprods;
	}

	public Lineasprod addLineasprod(Lineasprod lineasprod) {
		getLineasprods().add(lineasprod);
		lineasprod.setTipLinea(this);

		return lineasprod;
	}

	public Lineasprod removeLineasprod(Lineasprod lineasprod) {
		getLineasprods().remove(lineasprod);
		lineasprod.setTipLinea(null);

		return lineasprod;
	}

}