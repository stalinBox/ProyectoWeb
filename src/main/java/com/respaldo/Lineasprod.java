package com.respaldo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the lineasprod database table.
 * 
 */
@Entity
@NamedQuery(name="Lineasprod.findAll", query="SELECT l FROM Lineasprod l")
public class Lineasprod implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="lineapro_codigo")
	private Integer lineaproCodigo;

	@Column(name="linea_desc")
	private String lineaDesc;

	private String lineaaut;

	private String nomlinea;

	private Integer nummaq;

	//bi-directional many-to-one association to Lineasturno
	@OneToMany(mappedBy="lineasprod")
	private List<Lineasturno> lineasturnos;

	//bi-directional many-to-one association to Maquina
	@OneToMany(mappedBy="lineasprod")
	private List<Maquina> maquinas;

	public Lineasprod() {
	}

	public Integer getLineaproCodigo() {
		return this.lineaproCodigo;
	}

	public void setLineaproCodigo(Integer lineaproCodigo) {
		this.lineaproCodigo = lineaproCodigo;
	}

	public String getLineaDesc() {
		return this.lineaDesc;
	}

	public void setLineaDesc(String lineaDesc) {
		this.lineaDesc = lineaDesc;
	}

	public String getLineaaut() {
		return this.lineaaut;
	}

	public void setLineaaut(String lineaaut) {
		this.lineaaut = lineaaut;
	}

	public String getNomlinea() {
		return this.nomlinea;
	}

	public void setNomlinea(String nomlinea) {
		this.nomlinea = nomlinea;
	}

	public Integer getNummaq() {
		return this.nummaq;
	}

	public void setNummaq(Integer nummaq) {
		this.nummaq = nummaq;
	}

	public List<Lineasturno> getLineasturnos() {
		return this.lineasturnos;
	}

	public void setLineasturnos(List<Lineasturno> lineasturnos) {
		this.lineasturnos = lineasturnos;
	}

	public Lineasturno addLineasturno(Lineasturno lineasturno) {
		getLineasturnos().add(lineasturno);
		lineasturno.setLineasprod(this);

		return lineasturno;
	}

	public Lineasturno removeLineasturno(Lineasturno lineasturno) {
		getLineasturnos().remove(lineasturno);
		lineasturno.setLineasprod(null);

		return lineasturno;
	}

	public List<Maquina> getMaquinas() {
		return this.maquinas;
	}

	public void setMaquinas(List<Maquina> maquinas) {
		this.maquinas = maquinas;
	}

	public Maquina addMaquina(Maquina maquina) {
		getMaquinas().add(maquina);
		maquina.setLineasprod(this);

		return maquina;
	}

	public Maquina removeMaquina(Maquina maquina) {
		getMaquinas().remove(maquina);
		maquina.setLineasprod(null);

		return maquina;
	}

}