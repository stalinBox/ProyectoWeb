package com.project.mb;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.project.dao.TurnosDao;
import com.project.dao.TurnosDaoImpl;
import com.project.entities.Turno;

@ManagedBean
@RequestScoped
public class TurnosMB implements Serializable {
	private static final long serialVersionUID = 1L;

	// VARIABLES
	private List<Turno> turnos;
	private Turno selectedTurno;

	private Date horaTurnosI;
	private Date horaTurnosF;
	private String turnosDesc;
	private String nombTurno;

	@PostConstruct
	public void init() {
		selectedTurno = new Turno();
	}

	public TurnosMB() {
		this.turnos = new ArrayList<Turno>();
	}

	// METODOS
	public void btnCreateTurno(ActionEvent actionEvent) throws ParseException {
		System.out.println("Entro al crear");
		String msg = "";
		TurnosDao turnoDao = new TurnosDaoImpl();

		SimpleDateFormat simpDate = new SimpleDateFormat("HH:mm:ss");
		String hi = simpDate.format(this.horaTurnosI);
		String hf = simpDate.format(this.horaTurnosF);

		try {
			SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
			java.util.Date d1 = (java.util.Date) format.parse(hi);
			java.util.Date d2 = (java.util.Date) format.parse(hf);

			java.sql.Time ppstime1 = new java.sql.Time(d1.getTime());
			java.sql.Time ppstime2 = new java.sql.Time(d2.getTime());

			this.selectedTurno.setHInicio(ppstime1);
			this.selectedTurno.setHFin(ppstime2);
			this.selectedTurno.setTurnoDesc(this.turnosDesc);

			if (turnoDao.create(this.selectedTurno)) {
				msg = "Se ha añadido un nuevo turno";
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_INFO, msg, null);
				FacesContext.getCurrentInstance().addMessage(null, message);
			} else {
				msg = "Error al momento de añadir un turno";
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_ERROR, msg, null);
				FacesContext.getCurrentInstance().addMessage(null, message);
			}

		} catch (Exception e) {

			System.out.println("Exception is " + e.toString());
		}
	}

	public void btnUpdateTurno(ActionEvent actionEvent) {
		String msg = "";
		TurnosDao turnoDao = new TurnosDaoImpl();

		SimpleDateFormat simpDate = new SimpleDateFormat("HH:mm:ss");

		String hi = simpDate.format(this.horaTurnosI);
		String hf = simpDate.format(this.horaTurnosF);

		try {
			SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
			java.util.Date d1 = (java.util.Date) format.parse(hi);
			java.util.Date d2 = (java.util.Date) format.parse(hf);

			java.sql.Time ppstime1 = new java.sql.Time(d1.getTime());
			java.sql.Time ppstime2 = new java.sql.Time(d2.getTime());

			this.selectedTurno.setHInicio(ppstime1);
			this.selectedTurno.setHFin(ppstime2);
			this.selectedTurno.setTurnoDesc(this.turnosDesc);

			if (turnoDao.update(this.selectedTurno)) {
				msg = "Se ha modificado el turno";
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_INFO, msg, null);
				FacesContext.getCurrentInstance().addMessage(null, message);
			} else {
				msg = "Error al momento de modificar un turno";
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_ERROR, msg, null);
				FacesContext.getCurrentInstance().addMessage(null, message);
			}

		} catch (Exception e) {

			System.out.println("Exception is " + e.toString());
		}
	}

	public void btnDeleteTurno(ActionEvent actionEvent) {
		String msg;
		TurnosDao turnoDao = new TurnosDaoImpl();
		if (turnoDao.delete(this.selectedTurno.getTurnoCodigo())) {
			msg = "Se eliminó el turno exitosamente";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		} else {
			msg = "Error al eliminar el turno";
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	// SETTERS AND GETTERS
	public List<Turno> getTurnos() {
		TurnosDao turnoDao = new TurnosDaoImpl();
		this.turnos = turnoDao.findAll();
		return turnos;
	}

	public String getNombTurno() {
		return nombTurno;
	}

	public void setNombTurno(String nombTurno) {
		this.nombTurno = nombTurno;
	}

	public String getTurnosDesc() {
		return turnosDesc;
	}

	public void setTurnosDesc(String turnosDesc) {
		this.turnosDesc = turnosDesc;
	}

	public void setTurnos(List<Turno> turnos) {
		this.turnos = turnos;
	}

	public Turno getSelectedTurno() {
		return selectedTurno;
	}

	public void setSelectedTurno(Turno selectedTurno) {
		this.selectedTurno = selectedTurno;
	}

	public Date getHoraTurnosI() {
		return horaTurnosI;
	}

	public void setHoraTurnosI(Date horaTurnosI) {
		this.horaTurnosI = horaTurnosI;
	}

	public Date getHoraTurnosF() {
		return horaTurnosF;
	}

	public void setHoraTurnosF(Date horaTurnosF) {
		this.horaTurnosF = horaTurnosF;
	}
}
