package com.project.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionListener;
import javax.faces.model.SelectItem;

import com.project.dao.LineasProdDao;
import com.project.dao.LineasProdDaoImpl;
import com.project.dao.LineasTurnosDao;
import com.project.dao.LineasTurnosDaoImpl;
import com.project.dao.TurnosDao;
import com.project.dao.TurnosDaoImpl;
import com.project.entities.Lineasprod;
import com.project.entities.Lineasturno;
import com.project.entities.Parametro;
import com.project.entities.Turno;
import com.project.utils.ContentParam;

@ManagedBean
@ViewScoped
public class LineasTurnosBean implements Serializable {

	private static final long serialVersionUID = 1L;

	// VARIABLES
	private List<Lineasturno> lineaTurno;
	private Lineasturno selectedLineaTurn;
	private List<SelectItem> selectItemsLineas;
	private List<SelectItem> selectItemsTurnos;

	// INICIALIZADORES
	@PostConstruct
	public void init() {
		this.selectedLineaTurn = new Lineasturno();
		this.selectedLineaTurn.setLineasprod(new Lineasprod());
		this.selectedLineaTurn.setTurno(new Turno());
		this.selectedLineaTurn.setParametro(new Parametro());
	}

	// METODOS
	public void btnCreateLT(ActionListener actionListener) {
		String msg = "";
		LineasTurnosDao lineasTurnosDao = new LineasTurnosDaoImpl();
		if (lineasTurnosDao.create(this.selectedLineaTurn)) {
			msg = "Se ha configurado una nueva linea";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		} else {
			msg = "Error al configurar la linea";
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	public void btnDeleteLT(ActionListener actionListener) {
		String msg;
		LineasTurnosDao lineasTurnosDao = new LineasTurnosDaoImpl();
		if (lineasTurnosDao.delete(this.selectedLineaTurn.getLtcodigo())) {
			msg = "Se eliminó la configuracion";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		} else {
			msg = "Error al eliminar la configuracion LT";
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	public void btnUpdateLT(ActionListener actionListener) {
	}

	// SETTERS AND GETTERS

	public List<Lineasturno> getLineaTurno() {
		System.out.println("NUMERO DE ORDEN: " + ContentParam.getCodOrden());

		LineasTurnosDao lineasTurnosDao = new LineasTurnosDaoImpl();
		this.lineaTurno = lineasTurnosDao.findByOrden(ContentParam
				.getCodOrden());
		return lineaTurno;
	}

	public List<SelectItem> getSelectItemsLineas() {
		this.selectItemsLineas = new ArrayList<SelectItem>();
		LineasProdDao lineasDao = new LineasProdDaoImpl();
		List<Lineasprod> linea = lineasDao.findAll();
		for (Lineasprod li : linea) {
			SelectItem selectItem = new SelectItem(li.getLineaproCodigo(),
					li.getNomlinea());
			this.selectItemsLineas.add(selectItem);
		}
		return selectItemsLineas;
	}

	public void setSelectItemsLineas(List<SelectItem> selectItemsLineas) {
		this.selectItemsLineas = selectItemsLineas;
	}

	public List<SelectItem> getSelectItemsTurnos() {
		this.selectItemsTurnos = new ArrayList<SelectItem>();
		TurnosDao turnosDao = new TurnosDaoImpl();
		List<Turno> turno = turnosDao.findAll();
		for (Turno tur : turno) {
			SelectItem selectItem = new SelectItem(tur.getTurnoCodigo(),
					tur.getNombturno());
			this.selectItemsTurnos.add(selectItem);
		}
		return selectItemsTurnos;
	}

	public void setSelectItemsTurnos(List<SelectItem> selectItemsTurnos) {
		this.selectItemsTurnos = selectItemsTurnos;
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
