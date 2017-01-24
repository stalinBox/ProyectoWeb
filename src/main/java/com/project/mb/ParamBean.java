package com.project.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import com.project.dao.ParametrizacionDao;
import com.project.dao.ParametrizacionDaoImpl;
import com.project.dao.ProcesoDao;
import com.project.dao.ProcesoDaoImpl;
import com.project.entities.Ordenprod;
import com.project.entities.Parametro;
import com.project.entities.Proceso;
import com.project.entities.Usuario;
import com.project.utils.ContentParam;
import com.project.utils.MyUtil;

@ManagedBean
@ViewScoped
public class ParamBean implements Serializable {
	private static final long serialVersionUID = 1L;

	// VARIABLES
	private List<Parametro> parametrizacion;
	private Parametro selectedParametrizacion;
	private List<SelectItem> selectItemsParamOrden;
	private List<SelectItem> selectedItemsProceso;
	private Integer stdConv;
	private Integer stdAut;
	private Integer totOrden;
	private Integer stdMan;

	ContentParam cparam = new ContentParam();

	// INICIALIZADOR
	@PostConstruct
	public void init() {

		this.selectedParametrizacion = new Parametro();
		this.selectedParametrizacion.setProceso(new Proceso());
		this.selectedParametrizacion.setUsuario(new Usuario());
		this.totOrden = ContentParam.getTotalOrden();
	}

	// METODOS
	public void btnConfLineas() {
		String ruta = "";
		ruta = MyUtil.calzadoPath() + "parametrizacion/lineasTurns.jsf";
		try {
			// LineasTurnosBean ac = new LineasTurnosBean();
			// ac.setnOrden(ContentParam.getCodOrden());
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect(ruta);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void btnCreateParametrizacion(ActionEvent actionEvent) {
		String msg = "";
		ParametrizacionDao paramDao = new ParametrizacionDaoImpl();

		Ordenprod ordenpro = new Ordenprod();
		ordenpro.setOrdenprodCodigo(ContentParam.getCodOrden());
		this.selectedParametrizacion.setOrdenprod(ordenpro);
		this.selectedParametrizacion.setStandconv(this.stdConv);
		this.selectedParametrizacion.setStandauto(this.stdAut);
		this.selectedParametrizacion.setStandman(this.stdMan);

		if (paramDao.create(this.selectedParametrizacion)) {
			msg = "Se ha añadido un nuevo parametrizacion";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		} else {
			msg = "Error al añadir un cliente";
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	public void btnUpdateParametrizacion(ActionEvent actionEvent) {
		String msg;
		ParametrizacionDao paramDao = new ParametrizacionDaoImpl();
		if (paramDao.update(this.selectedParametrizacion)) {
			msg = "Se ha modificado un parametro";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		} else {
			msg = "Error al modificar un parametro";
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	public void btnDeleteParametrizacion(ActionEvent actionEvent) {
		String msg;
		ParametrizacionDao paramDao = new ParametrizacionDaoImpl();
		if (paramDao.delete(this.selectedParametrizacion.getParamCodigo())) {
			msg = "Se eliminó una parametrizacion";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		} else {
			msg = "Error al eliminar una parametrizacion";
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	public void onChangeProcesos(ActionEvent actionEvent) {
		Integer a = null;
		a = this.selectedParametrizacion.getProceso().getProCodigo();

		if (a == 1) {
			this.stdConv = ContentParam.getStandConvMontaje();
			this.stdAut = ContentParam.getStandAutMontaje();
			this.stdMan = 0;
		} else if (a == 2) {
			this.stdConv = ContentParam.getStandConvAparado();
			this.stdAut = ContentParam.getStandAutAparado();
			this.stdMan = 0;
		} else {
			this.stdConv = ContentParam.getStandConvTroquelado();
			this.stdAut = ContentParam.getStandAutTroquelado();
			this.stdMan = ContentParam.getStandTroqueladoTroquel();
		}
	}

	// SETTERS AND GETTERS
	public List<SelectItem> getSelectedItemsProceso() {
		this.selectedItemsProceso = new ArrayList<SelectItem>();
		ProcesoDao procesoDao = new ProcesoDaoImpl();
		List<Proceso> proceso = procesoDao
				.findByOrdenProdNotInParam(ContentParam.getCodOrden());
		for (Proceso pro : proceso) {
			SelectItem selectItem = new SelectItem(pro.getProCodigo(), pro
					.getTipoProceso().getTprNombre());
			this.selectedItemsProceso.add(selectItem);
		}
		return selectedItemsProceso;
	}

	public void setSelectedItemsProceso(List<SelectItem> selectedItemsProceso) {
		this.selectedItemsProceso = selectedItemsProceso;
	}

	public List<Parametro> getParametrizacion() {
		ParametrizacionDao pDao = new ParametrizacionDaoImpl();
		this.parametrizacion = pDao.findByOrdenProd(ContentParam.getCodOrden());
		return parametrizacion;
	}

	public List<SelectItem> getSelectItemsParamOrden() {
		this.selectItemsParamOrden = new ArrayList<SelectItem>();
		ParametrizacionDao pDao = new ParametrizacionDaoImpl();
		List<Parametro> param = pDao
				.findByOrdenProd(ContentParam.getCodOrden());
		for (Parametro p : param) {
			SelectItem selectItem = new SelectItem(p.getParamCodigo(), p
					.getProceso().getTipoProceso().getTprNombre());
			this.selectItemsParamOrden.add(selectItem);
		}
		return selectItemsParamOrden;
	}

	public void setSelectItemsParamOrden(List<SelectItem> selectItemsParamOrden) {
		this.selectItemsParamOrden = selectItemsParamOrden;
	}

	public Integer getStdConv() {
		return stdConv;
	}

	public void setStdConv(Integer stdConv) {
		this.stdConv = stdConv;
	}

	public Integer getStdAut() {
		return stdAut;
	}

	public void setStdAut(Integer stdAut) {
		this.stdAut = stdAut;
	}

	public Integer getTotOrden() {
		return totOrden;
	}

	public void setTotOrden(Integer totOrden) {
		this.totOrden = totOrden;
	}

	public void setParametrizacion(List<Parametro> parametrizacion) {
		this.parametrizacion = parametrizacion;
	}

	public Parametro getSelectedParametrizacion() {
		return selectedParametrizacion;
	}

	public void setSelectedParametrizacion(Parametro selectedParametrizacion) {
		this.selectedParametrizacion = selectedParametrizacion;
	}

	public Integer getStdMan() {
		return stdMan;
	}

	public void setStdMan(Integer stdMan) {
		this.stdMan = stdMan;
	}

}
