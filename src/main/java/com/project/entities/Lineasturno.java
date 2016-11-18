package com.project.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the lineasturnos database table.
 * 
 */
@Entity
@Table(name="lineasturnos")
@NamedQuery(name="Lineasturno.findAll", query="SELECT l FROM Lineasturno l")
public class Lineasturno implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer ltcodigo;

	//bi-directional many-to-one association to Lineasprod
	@ManyToOne
	@JoinColumn(name="lineapro_codigo")
	private Lineasprod lineasprod;

	//bi-directional many-to-one association to Turno
	@ManyToOne
	@JoinColumn(name="turno_codigo")
	private Turno turno;

	//bi-directional many-to-one association to Parametro
	@OneToMany(mappedBy="lineasturno")
	private List<Parametro> parametros;

	public Lineasturno() {
	}

	public Integer getLtcodigo() {
		return this.ltcodigo;
	}

	public void setLtcodigo(Integer ltcodigo) {
		this.ltcodigo = ltcodigo;
	}

	public Lineasprod getLineasprod() {
		return this.lineasprod;
	}

	public void setLineasprod(Lineasprod lineasprod) {
		this.lineasprod = lineasprod;
	}

	public Turno getTurno() {
		return this.turno;
	}

	public void setTurno(Turno turno) {
		this.turno = turno;
	}

	public List<Parametro> getParametros() {
		return this.parametros;
	}

	public void setParametros(List<Parametro> parametros) {
		this.parametros = parametros;
	}

	public Parametro addParametro(Parametro parametro) {
		getParametros().add(parametro);
		parametro.setLineasturno(this);

		return parametro;
	}

	public Parametro removeParametro(Parametro parametro) {
		getParametros().remove(parametro);
		parametro.setLineasturno(null);

		return parametro;
	}

}