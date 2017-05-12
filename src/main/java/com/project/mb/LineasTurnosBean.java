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
import com.project.dao.ParamDao;
import com.project.dao.ParamDaoImpl;
import com.project.dao.ProcesoDao;
import com.project.dao.ProcesoDaoImpl;
import com.project.dao.TurnosDao;
import com.project.dao.TurnosDaoImpl;
import com.project.entities.Lineasprod;
import com.project.entities.Lineasturno;
import com.project.entities.Parametro;
import com.project.entities.Turno;
import com.project.utils.ItemCodOrden;
import com.project.utils.RefreshPage;

/**
 * @author Stalin Ramírez
 *
 */
@ManagedBean
@ViewScoped
public class LineasTurnosBean implements Serializable {

	private static final long serialVersionUID = 1L;

	// VARIABLES
	private List<Lineasturno> lineaTurno;
	private Lineasturno selectedLineaTurn;
	private List<SelectItem> selectItemsProcesos;
	private List<SelectItem> selectItemsLineas;
	private List<SelectItem> selectItemsTurnos;

	// VERIFICAR QUE PASE EL CODIGO DE LA ORDEN POR ESTA
	// VARIABLE*************************************************************************
	private Integer codDetaOrden;

	// INICIALIZADORES
	@PostConstruct
	public void init() {
		this.selectedLineaTurn = new Lineasturno();
		this.selectedLineaTurn.setLineasprod(new Lineasprod());
		this.selectedLineaTurn.setTurno(new Turno());
		this.selectedLineaTurn.setParametro(new Parametro());

		this.codDetaOrden = ItemCodOrden.getCodOrden();
		System.out.println("Codigo Orden: " + this.codDetaOrden);
	}

	// METODOS

	public void cleanProceso() {
		this.selectItemsProcesos = new ArrayList<SelectItem>();
	}

	public void cleanLineas() {
		this.selectItemsLineas = new ArrayList<SelectItem>();
		cleanTurnos();
	}

	public void cleanTurnos() {
		this.selectItemsTurnos = new ArrayList<SelectItem>();
	}

	public void cleanAllDropDown() {
		cleanProceso();
		cleanLineas();
		cleanTurnos();
	}

	public void btnCreateLT(ActionListener actionListener) {
		String msg = "";
		LineasTurnosDao lineasTurnosDao = new LineasTurnosDaoImpl();
		if (lineasTurnosDao.create(this.selectedLineaTurn)) {
			msg = "Se ha configurado una nueva linea";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);

			RefreshPage oo = new RefreshPage();
			oo.refresh();
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

		LineasTurnosDao lineasTurnosDao = new LineasTurnosDaoImpl();
		this.lineaTurno = lineasTurnosDao.findByDETALLE(codDetaOrden);
		return lineaTurno;
	}

	public Integer getCodDetaOrden() {
		return codDetaOrden;
	}

	public void setCodDetaOrden(Integer codDetaOrden) {
		this.codDetaOrden = codDetaOrden;
	}

	public List<SelectItem> getSelectItemsProcesos() {
		this.selectItemsProcesos = new ArrayList<SelectItem>();
		ProcesoDao procesoDao = new ProcesoDaoImpl();

		List<Parametro> proceso = procesoDao.findByOrdenParam(codDetaOrden);

		for (Parametro p : proceso) {
			SelectItem selectItem = new SelectItem(p.getParamCodigo(), p
					.getProceso().getTipoProceso().getTprNombre()
					+ " - " + p.getTipLinea().getTipolinea());
			this.selectItemsProcesos.add(selectItem);
		}
		return selectItemsProcesos;
	}

	public void setSelectItemsProcesos(List<SelectItem> selectItemsProcesos) {
		this.selectItemsProcesos = selectItemsProcesos;
	}

	public List<SelectItem> getSelectItemsLineas() {

		ParamDao paramDao = new ParamDaoImpl();
		Parametro pp1 = (Parametro) paramDao
				.findbyCodParam(this.selectedLineaTurn.getParametro()
						.getParamCodigo());

		if (this.selectedLineaTurn.getParametro().getParamCodigo() != null) {
			this.selectItemsLineas = new ArrayList<SelectItem>();
			LineasProdDao lineasDao = new LineasProdDaoImpl();

			List<Lineasprod> linea = lineasDao.findByParam(pp1.getProceso()
					.getProCodigo(), pp1.getTipLinea().getCodigoTiplinea(),
					this.codDetaOrden);

			for (Lineasprod li : linea) {
				SelectItem selectItem = new SelectItem(li.getLineaproCodigo(),
						li.getNomlinea());
				this.selectItemsLineas.add(selectItem);
			}
			return selectItemsLineas;
		} else {
			this.selectItemsLineas = new ArrayList<SelectItem>();
			return selectItemsLineas;
		}

	}

	public void setSelectItemsLineas(List<SelectItem> selectItemsLineas) {
		this.selectItemsLineas = selectItemsLineas;
	}

	public List<SelectItem> getSelectItemsTurnos() {
		this.selectItemsTurnos = new ArrayList<SelectItem>();
		TurnosDao turnosDao = new TurnosDaoImpl();
		List<Turno> turno = turnosDao.findByLineasByParam(
				this.selectedLineaTurn.getParametro().getParamCodigo(),
				this.selectedLineaTurn.getLineasprod().getLineaproCodigo());
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
