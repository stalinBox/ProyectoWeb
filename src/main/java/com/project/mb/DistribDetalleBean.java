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

import com.project.dao.DetaOrdenDao;
import com.project.dao.DetaOrdenDaoImpl;
import com.project.dao.DistribDetaDao;
import com.project.dao.DistribDetaDaoImpl;
import com.project.dao.ProcesoDao;
import com.project.dao.ProcesoDaoImpl;
import com.project.dao.TipoLineaDao;
import com.project.dao.TipoLineaDaoImpl;
import com.project.entities.Detalleorden;
import com.project.entities.Distribdetalle;
import com.project.entities.Proceso;
import com.project.entities.TipLinea;
import com.project.utils.ItemCodOrden;
import com.project.utils.MyUtil;

@ManagedBean
@ViewScoped
public class DistribDetalleBean implements Serializable {
	private static final long serialVersionUID = 1L;

	// VARIABLES
	private List<Distribdetalle> distribDetalle;
	private Distribdetalle selectedDT;

	private List<Detalleorden> detalleOrden;
	private List<Detalleorden> selectedDeta;
	private Detalleorden selectedDetalleorden;

	private Integer codDetaOrden;
	private List<SelectItem> selectedItemsProceso;
	private List<SelectItem> selectedItemsTipLinea;
	private Integer nDias;

	// INICIALIZADORES
	@PostConstruct
	public void init() {
		this.selectedDT = new Distribdetalle();
		this.selectedDT.setProceso(new Proceso());
		this.selectedDT.setTipLinea(new TipLinea());
		this.codDetaOrden = ItemCodOrden.getCodOrden();
	}

	// DML
	public void btnProcesar(ActionEvent actionEvent) {
		System.out.println("Procesando..");
		ProcesoDao proDao = new ProcesoDaoImpl();
		List<Proceso> process = proDao.findProcesosDistribByOrden(codDetaOrden);

		TipoLineaDao tipLineaDao = new TipoLineaDaoImpl();
		List<TipLinea> tipo = tipLineaDao.findTpLineaByDistrib(codDetaOrden);

		DistribDetaDao distribDao = new DistribDetaDaoImpl();
		List<Distribdetalle> distro = null;

		ItemsGen items = new ItemsGen();
		ArrayList<ItemsGen> listaItems = new ArrayList<ItemsGen>();

		for (Proceso p : process) {
			for (TipLinea tp : tipo) {
				items = new ItemsGen(p.getProCodigo(), tp.getCodigoTiplinea());
				listaItems.add(items);
				// distro = distribDao.findByOrderByProByTL(codDetaOrden,
				// p.getProCodigo(), tp.getCodigoTiplinea());
				// for (Distribdetalle d : distro) {
				// System.out.println("Nada: ");
				// // System.out.println("--p: "
				// // + d.getProceso().getTipoProceso().getTprNombre());
				// // System.out.println("-- tp: "
				// // + d.getTipLinea().getTipolinea());
				// }
			}
		}
		for (ItemsGen i : listaItems) {
			System.out.println("Proceso: " + i.getCodProceso() + " TLinea: "
					+ i.getCodTlinea());
			distro = distribDao.findByOrderByProByTL(codDetaOrden,
					i.getCodProceso(), i.getCodTlinea());
			for (Distribdetalle d : distro) {
				System.out.println("nada");
			}
		}

	}

	public void btnReprocesar(ActionEvent actionEvent) {
		String ruta = "";
		ruta = MyUtil.calzadoPath() + "ordenesProd/insertOrder.jsf";
		try {
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect(ruta);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void btnCrear() {
		String msg = "";
		DistribDetaDao distribDao = new DistribDetaDaoImpl();
		for (Detalleorden i : selectedDeta) {
			Detalleorden dt = new Detalleorden();
			dt.setDetaordenCodigo(i.getDetaordenCodigo());
			this.selectedDT.setDetalleorden(dt);

			if (distribDao.create(this.selectedDT)) {
				msg = "Se ha añadido un ITEM";
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_INFO, msg, null);
				FacesContext.getCurrentInstance().addMessage(null, message);
			} else {
				msg = "Error al momento de añadir un ITEM";
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_ERROR, msg, null);
				FacesContext.getCurrentInstance().addMessage(null, message);
			}
		}
	}

	public void btnDeleteDistrib(ActionEvent actionEvent) {
		String msg;
		DistribDetaDao distribDao = new DistribDetaDaoImpl();
		if (distribDao.delete(this.selectedDT.getDistribCodigo())) {
			msg = "Se eliminó el item";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		} else {
			msg = "Error al eliminar el item";
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	// SETTERS AND GETTERS

	public List<SelectItem> getSelectedItemsTipLinea() {
		this.selectedItemsTipLinea = new ArrayList<SelectItem>();
		TipoLineaDao tpDao = new TipoLineaDaoImpl();
		List<TipLinea> tp = tpDao.findbyProceso(this.selectedDT.getProceso()
				.getProCodigo());
		for (TipLinea t : tp) {
			SelectItem selectItem = new SelectItem(t.getCodigoTiplinea(),
					t.getTipolinea());
			this.selectedItemsTipLinea.add(selectItem);
		}
		return selectedItemsTipLinea;

	}

	public List<Detalleorden> getSelectedDeta() {
		return selectedDeta;
	}

	public void setSelectedDeta(List<Detalleorden> selectedDeta) {
		this.selectedDeta = selectedDeta;
	}

	public List<Detalleorden> getDetalleOrden() {
		DetaOrdenDao detaDao = new DetaOrdenDaoImpl();
		this.detalleOrden = detaDao.findByCCO(codDetaOrden, this.selectedDT
				.getProceso().getProCodigo(), this.selectedDT.getTipLinea()
				.getCodigoTiplinea());
		return detalleOrden;
	}

	public void setDetalleOrden(List<Detalleorden> detalleOrden) {
		this.detalleOrden = detalleOrden;
	}

	public Detalleorden getSelectedDetalleorden() {
		return selectedDetalleorden;
	}

	public void setSelectedDetalleorden(Detalleorden selectedDetalleorden) {
		this.selectedDetalleorden = selectedDetalleorden;
	}

	public List<Distribdetalle> getDistribDetalle() {
		DistribDetaDao distribDao = new DistribDetaDaoImpl();
		this.distribDetalle = distribDao.findByOrder(this.codDetaOrden);
		return distribDetalle;
	}

	public void setDistribDetalle(List<Distribdetalle> distribDetalle) {
		this.distribDetalle = distribDetalle;
	}

	public Distribdetalle getSelectedDT() {
		return selectedDT;
	}

	public void setSelectedDT(Distribdetalle selectedDT) {
		this.selectedDT = selectedDT;
	}

	public void setSelectedItemsTipLinea(List<SelectItem> selectedItemsTipLinea) {
		this.selectedItemsTipLinea = selectedItemsTipLinea;
	}

	public Integer getnDias() {
		return nDias;
	}

	public void setnDias(Integer nDias) {
		this.nDias = nDias;
	}

	public Integer getCodDetaOrden() {
		return codDetaOrden;
	}

	public void setCodDetaOrden(Integer codDetaOrden) {
		this.codDetaOrden = codDetaOrden;
	}

	public List<SelectItem> getSelectedItemsProceso() {
		this.selectedItemsProceso = new ArrayList<SelectItem>();
		ProcesoDao procesoDao = new ProcesoDaoImpl();
		List<Proceso> proceso = procesoDao.findPadre();
		for (Proceso p : proceso) {
			SelectItem selectItem = new SelectItem(p.getProCodigo(), p
					.getTipoProceso().getTprNombre());
			this.selectedItemsProceso.add(selectItem);
		}
		return selectedItemsProceso;
	}

	public void setSelectedItemsProceso(List<SelectItem> selectedItemsProceso) {
		this.selectedItemsProceso = selectedItemsProceso;
	}

	// CLASE
	public class ItemsGen implements Serializable {
		private static final long serialVersionUID = 1L;
		private Integer codProceso;
		private Integer codTlinea;

		public ItemsGen(Integer codProceso, Integer codTlinea) {
			this.codProceso = codProceso;
			this.codTlinea = codTlinea;
		}

		public ItemsGen() {
		}

		public Integer getCodProceso() {
			return codProceso;
		}

		public void setCodProceso(Integer codProceso) {
			this.codProceso = codProceso;
		}

		public Integer getCodTlinea() {
			return codTlinea;
		}

		public void setCodTlinea(Integer codTlinea) {
			this.codTlinea = codTlinea;
		}

	}
}
