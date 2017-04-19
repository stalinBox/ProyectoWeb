package com.project.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the programdias database table.
 * 
 */
@Entity
@Table(name="programdias")
@NamedQuery(name="Programdia.findAll", query="SELECT p FROM Programdia p")
public class Programdia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="progdias_codigo")
	private Integer progdiasCodigo;

	private double canthoras;

	private Integer cantpares;

	@Temporal(TemporalType.DATE)
	private Date ffin;

	@Temporal(TemporalType.DATE)
	private Date finicio;

	//bi-directional many-to-one association to Capacidade
	@ManyToOne
	@JoinColumn(name="cap_codigo")
	private Capacidade capacidade;

	//bi-directional many-to-one association to Programturno
	@OneToMany(mappedBy="programdia")
	private List<Programturno> programturnos;

	public Programdia() {
	}

	public Integer getProgdiasCodigo() {
		return this.progdiasCodigo;
	}

	public void setProgdiasCodigo(Integer progdiasCodigo) {
		this.progdiasCodigo = progdiasCodigo;
	}

	public double getCanthoras() {
		return this.canthoras;
	}

	public void setCanthoras(double canthoras) {
		this.canthoras = canthoras;
	}

	public Integer getCantpares() {
		return this.cantpares;
	}

	public void setCantpares(Integer cantpares) {
		this.cantpares = cantpares;
	}

	public Date getFfin() {
		return this.ffin;
	}

	public void setFfin(Date ffin) {
		this.ffin = ffin;
	}

	public Date getFinicio() {
		return this.finicio;
	}

	public void setFinicio(Date finicio) {
		this.finicio = finicio;
	}

	public Capacidade getCapacidade() {
		return this.capacidade;
	}

	public void setCapacidade(Capacidade capacidade) {
		this.capacidade = capacidade;
	}

	public List<Programturno> getProgramturnos() {
		return this.programturnos;
	}

	public void setProgramturnos(List<Programturno> programturnos) {
		this.programturnos = programturnos;
	}

	public Programturno addProgramturno(Programturno programturno) {
		getProgramturnos().add(programturno);
		programturno.setProgramdia(this);

		return programturno;
	}

	public Programturno removeProgramturno(Programturno programturno) {
		getProgramturnos().remove(programturno);
		programturno.setProgramdia(null);

		return programturno;
	}

}