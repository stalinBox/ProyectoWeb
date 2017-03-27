package com.project.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Time;
import java.util.List;


/**
 * The persistent class for the turnos database table.
 * 
 */
@Entity
@Table(name="turnos")
@NamedQuery(name="Turno.findAll", query="SELECT t FROM Turno t")
public class Turno implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="turno_codigo")
	private Integer turnoCodigo;

	@Column(name="h_fin")
	private Time hFin;

	@Column(name="h_inicio")
	private Time hInicio;

	private String nombturno;

	@Column(name="turno_desc")
	private String turnoDesc;

	//bi-directional many-to-one association to Lineasturno
	@OneToMany(mappedBy="turno")
	private List<Lineasturno> lineasturnos;

	//bi-directional many-to-one association to Programturno
	@OneToMany(mappedBy="turno")
	private List<Programturno> programturnos;

	//bi-directional many-to-one association to Usuario
	@OneToMany(mappedBy="turno")
	private List<Usuario> usuarios;

	public Turno() {
	}

	public Integer getTurnoCodigo() {
		return this.turnoCodigo;
	}

	public void setTurnoCodigo(Integer turnoCodigo) {
		this.turnoCodigo = turnoCodigo;
	}

	public Time getHFin() {
		return this.hFin;
	}

	public void setHFin(Time hFin) {
		this.hFin = hFin;
	}

	public Time getHInicio() {
		return this.hInicio;
	}

	public void setHInicio(Time hInicio) {
		this.hInicio = hInicio;
	}

	public String getNombturno() {
		return this.nombturno;
	}

	public void setNombturno(String nombturno) {
		this.nombturno = nombturno;
	}

	public String getTurnoDesc() {
		return this.turnoDesc;
	}

	public void setTurnoDesc(String turnoDesc) {
		this.turnoDesc = turnoDesc;
	}

	public List<Lineasturno> getLineasturnos() {
		return this.lineasturnos;
	}

	public void setLineasturnos(List<Lineasturno> lineasturnos) {
		this.lineasturnos = lineasturnos;
	}

	public Lineasturno addLineasturno(Lineasturno lineasturno) {
		getLineasturnos().add(lineasturno);
		lineasturno.setTurno(this);

		return lineasturno;
	}

	public Lineasturno removeLineasturno(Lineasturno lineasturno) {
		getLineasturnos().remove(lineasturno);
		lineasturno.setTurno(null);

		return lineasturno;
	}

	public List<Programturno> getProgramturnos() {
		return this.programturnos;
	}

	public void setProgramturnos(List<Programturno> programturnos) {
		this.programturnos = programturnos;
	}

	public Programturno addProgramturno(Programturno programturno) {
		getProgramturnos().add(programturno);
		programturno.setTurno(this);

		return programturno;
	}

	public Programturno removeProgramturno(Programturno programturno) {
		getProgramturnos().remove(programturno);
		programturno.setTurno(null);

		return programturno;
	}

	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario addUsuario(Usuario usuario) {
		getUsuarios().add(usuario);
		usuario.setTurno(this);

		return usuario;
	}

	public Usuario removeUsuario(Usuario usuario) {
		getUsuarios().remove(usuario);
		usuario.setTurno(null);

		return usuario;
	}

}