package com.project.mb;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionListener;

import com.project.dao.LineasTurnosDao;
import com.project.dao.LineasTurnosDaoImpl;
import com.project.entities.Lineasturno;

;

@ManagedBean
@ViewScoped
public class LineasTurnosBean implements Serializable {

	private static final long serialVersionUID = 1L;
	// VARIABLES
	private List<Lineasturno> lineaTurno;
	private Lineasturno selectedLineaTurn;

	// INICIALIZADORES
	@PostConstruct
	public void init() {

	}

	// METODOS
	public void btnCreateLT(ActionListener actionListener) {

	}

	public void btnUpdateLT(ActionListener actionListener) {
	}

	// SETTERS AND GETTERS
	public List<Lineasturno> getLineaTurno() {
		LineasTurnosDao lineasTurnosDao = new LineasTurnosDaoImpl();
		this.lineaTurno = lineasTurnosDao.findAll();
		return lineaTurno;
	}

	public void setLineaTurno(List<Lineasturno> lineaTurno) {
		this.lineaTurno = lineaTurno;
	}

	public Lineasturno getSelectedLineaTurn() {

		return selectedLineaTurn;
	}

	public void setSelectedLineaTurn(Lineasturno selectedLineaTurn) {
		this.selectedLineaTurn = selectedLineaTurn;
	}

}
