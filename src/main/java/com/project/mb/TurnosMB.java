package com.project.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import com.project.dao.TurnosDao;
import com.project.dao.TurnosDaoImpl;
import com.project.entities.Turno;

@ManagedBean
@RequestScoped
public class TurnosMB implements Serializable {
	private static final long serialVersionUID = 1L;

	// VARIABLES
	private Turno selectedTurno;
	private List<SelectItem> selectItemsTurnos;
	private List<Turno> turnos;

	@PostConstruct
	public void init() {
		selectedTurno = new Turno();
	}

	// SETTERS AND GETTERS
	public Turno getSelectedTurno() {
		return selectedTurno;
	}

	public void setSelectedTurno(Turno selectedTurno) {
		this.selectedTurno = selectedTurno;
	}

	public List<SelectItem> getSelectItemsTurnos() {
		this.selectItemsTurnos = new ArrayList<SelectItem>();
		TurnosDao turnosDao = new TurnosDaoImpl();
		List<Turno> turno = turnosDao.findAll();
		for (Turno turn : turno) {
			SelectItem selectItem = new SelectItem(turn.getTurnoCodigo(),
					turn.getNombturno());
			this.selectItemsTurnos.add(selectItem);
		}
		return selectItemsTurnos;
	}

	public void setSelectItemsTurnos(List<SelectItem> selectItemsTurnos) {
		this.selectItemsTurnos = selectItemsTurnos;
	}

	public List<Turno> getTurnos() {
		TurnosDao turnosDao = new TurnosDaoImpl();
		this.turnos = turnosDao.findAll();
		return turnos;
	}

	public void setTurnos(List<Turno> turnos) {
		this.turnos = turnos;
	}

	// METODOS
	public void btnCreateTurno(ActionEvent actionEvent) {
		String msg = "";
		TurnosDao turnoDao = new TurnosDaoImpl();

		if (turnoDao.create(this.selectedTurno)) {
			msg = "Se ha añadido un nuevo turno";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		} else {
			msg = "Error al momento de añadir un turno";
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	public void btnUpdateTurno(ActionEvent actionEvent) {
		String msg;
		TurnosDao turnoDao = new TurnosDaoImpl();
		if (turnoDao.update(this.selectedTurno)) {
			msg = "Se ha modificado el turno";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		} else {
			msg = "Error al modificar el turno";
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
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
}
