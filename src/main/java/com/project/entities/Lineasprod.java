package com.project.entities;

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

	@Column(name="linea_numtrab")
	private Integer lineaNumtrab;

	private String nomlinea;

	//bi-directional many-to-one association to Proceso
	@ManyToOne
	@JoinColumn(name="pro_codigo")
	private Proceso proceso;

	//bi-directional many-to-one association to TipLinea
	@ManyToOne
	@JoinColumn(name="codigo_tiplinea")
	private TipLinea tipLinea;

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

	public Integer getLineaNumtrab() {
		return this.lineaNumtrab;
	}

	public void setLineaNumtrab(Integer lineaNumtrab) {
		this.lineaNumtrab = lineaNumtrab;
	}

	public String getNomlinea() {
		return this.nomlinea;
	}

	public void setNomlinea(String nomlinea) {
		this.nomlinea = nomlinea;
	}

	public Proceso getProceso() {
		return this.proceso;
	}

	public void setProceso(Proceso proceso) {
		this.proceso = proceso;
	}

	public TipLinea getTipLinea() {
		return this.tipLinea;
	}

	public void setTipLinea(TipLinea tipLinea) {
		this.tipLinea = tipLinea;
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