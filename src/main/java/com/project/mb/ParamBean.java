package com.project.mb;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.project.dao.ParamDao;
import com.project.dao.ParamDaoImpl;
import com.project.dao.ProcesoDao;
import com.project.dao.ProcesoDaoImpl;
import com.project.dao.TipoLineaDao;
import com.project.dao.TipoLineaDaoImpl;
import com.project.entities.Ordenprod;
import com.project.entities.Parametro;
import com.project.entities.Proceso;
import com.project.entities.TipLinea;
import com.project.utils.ItemCodOrden;
import com.project.utils.ItemsParams;
import com.project.utils.MyUtil;

@ManagedBean
@ViewScoped
public class ParamBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<ItemsParams> Ctiempos;
	private Parametro selectedParam;
	private Integer codDetaOrden;
	//
	private Integer codProceso;
	private Integer codTpLinea;

	@PostConstruct
	public void init() {
		this.codDetaOrden = ItemCodOrden.getCodOrden();
		this.selectedParam = new Parametro();
		this.selectedParam.setOrdenprod(new Ordenprod());
		this.selectedParam.setProceso(new Proceso());
		this.selectedParam.setTipLinea(new TipLinea());

		Ctiempos = DistribDetalleBean.getOrderlistparams();

	}

	public void btnSave() {
		ParamDao paramDao = new ParamDaoImpl();

		String msg = "";
		Ordenprod ordenCodigo = new Ordenprod();
		ordenCodigo.setOrdenprodCodigo(codDetaOrden);
		this.selectedParam.setOrdenprod(ordenCodigo);

		Proceso proceso = new Proceso();
		TipLinea tplinea = new TipLinea();

		for (ItemsParams i : Ctiempos) {

			ProcesoDao procesoDao = new ProcesoDaoImpl();
			codProceso = procesoDao.findByNameProceso(i.getProceso());
			proceso.setProCodigo(codProceso);

			TipoLineaDao tpLineaDao = new TipoLineaDaoImpl();
			codTpLinea = tpLineaDao.findByNombre(i.getTipoLinea());
			tplinea.setCodigoTiplinea(codTpLinea);

			this.selectedParam.setProceso(proceso);
			this.selectedParam.setTipLinea(tplinea);
			this.selectedParam.setStandar(i.getCpPonderado());

			if (paramDao.create(this.selectedParam)) {
				msg = "Se ha añadido la lista de estandares";
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_INFO, msg, null);
				FacesContext.getCurrentInstance().addMessage(null, message);

			} else {
				msg = "Error al añadir la lista de estandares";
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_ERROR, msg, null);
				FacesContext.getCurrentInstance().addMessage(null, message);
			}
		}

		// REDIRECCIONAR A PARAMA.JSF
		String ruta = null;
		ruta = MyUtil.calzadoPath() + "parametrizacion/lineasTurns.jsf";
		try {
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect(ruta);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// SETTERS AND GETTERS

	public List<ItemsParams> getCtiempos() {
		return Ctiempos;
	}

	public Integer getCodProceso() {
		return codProceso;
	}

	public void setCodProceso(Integer codProceso) {
		this.codProceso = codProceso;
	}

	public Integer getCodTpLinea() {
		return codTpLinea;
	}

	public void setCodTpLinea(Integer codTpLinea) {
		this.codTpLinea = codTpLinea;
	}

	public void setCtiempos(List<ItemsParams> ctiempos) {
		Ctiempos = ctiempos;
	}

	public Parametro getSelectedParam() {
		return selectedParam;
	}

	public void setSelectedParam(Parametro selectedParam) {
		this.selectedParam = selectedParam;
	}

}
