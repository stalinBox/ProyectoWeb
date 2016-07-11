package com.project.mb;

import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import com.project.dao.TtallasDao;
import com.project.dao.TtallasDaoImpl;
import com.project.entities.TTalla;
import com.project.entities.TTallaPK;
import com.project.entities.Talla;
import com.project.entities.Troquele;

@ManagedBean
@ViewScoped
public class TtallaBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<TTalla> ttallas;
	private TTalla selectedTtalla;

	private String[] selectedTalla;
	private List<SelectItem> selectItemsTroquel;
	private List<SelectItem> selectItemsExcludeByTalla;
	private String codTrq;
	private String codTT;

	@PostConstruct
	public void init() {
		selectedTtalla = new TTalla();
	}

	public TtallaBean() {
		this.ttallas = new ArrayList<TTalla>();
	}

	public List<TTalla> getTtallas() {
		TtallasDao ttallasDao = new TtallasDaoImpl();
		this.ttallas = ttallasDao.findAll();
		return ttallas;
	}

	public List<SelectItem> getSelectItemsExcludeByTalla() {
		if (this.codTrq != null && !this.codTrq.equals("")
				&& this.codTrq != "0") {
			
			this.selectItemsExcludeByTalla = new ArrayList<SelectItem>();
			TtallasDao TallasTtDao = new TtallasDaoImpl();
			List<Talla> tttalla = TallasTtDao.excludeByTalla(Integer
					.parseInt(codTrq));
			selectItemsExcludeByTalla.clear();
			for (Talla tttall : tttalla) {
				SelectItem selectItem = new SelectItem(tttall.getTalCodigo(),
						tttall.getTalNumero().toString());
				this.selectItemsExcludeByTalla.add(selectItem);
			}
			return selectItemsExcludeByTalla;
		
		} else {
			this.selectItemsExcludeByTalla = new ArrayList<SelectItem>();
			return selectItemsExcludeByTalla;
		}
	}

	public List<SelectItem> getSelectItemsTroquel() {
		this.selectItemsTroquel = new ArrayList<SelectItem>();
		TtallasDao TroquelTTDao = new TtallasDaoImpl();
		List<Troquele> Ttroquel = TroquelTTDao.selectItems();
		selectItemsTroquel.clear();
		for (Troquele ttro : Ttroquel) {
			SelectItem selectItem = new SelectItem(ttro.getTrqCodigo(),
					ttro.getTrqNombre());
			this.selectItemsTroquel.add(selectItem);
		}
		return selectItemsTroquel;
	}

	public void btnCreateTtallas() {
		TtallasDao ttallasDao = new TtallasDaoImpl();
		TTallaPK ttpk = new TTallaPK();
		String msg = "";
		for (String i : selectedTalla) {
			ttpk.setTalCodigo(Integer.parseInt(i));
			ttpk.setTrqCodigo(Integer.parseInt(this.codTrq));
			this.selectedTtalla.setId(ttpk);
			this.selectedTtalla.setCantidad(0);
			if (ttallasDao.create(this.selectedTtalla)) {
				msg = "Se ha añadido una ttalla";
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_INFO, msg, null);
				FacesContext.getCurrentInstance().addMessage(null, message);
			} else {
				msg = "Error al momento de añadir una ttalla";
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_ERROR, msg, null);
				FacesContext.getCurrentInstance().addMessage(null, message);
			}
		}
	}

	public void btnUpdateTtallas(ActionEvent actionEvent) {
		String msg;
		TtallasDao ttallasDao = new TtallasDaoImpl();
		if (ttallasDao.update(this.selectedTtalla.getId(), this.selectedTtalla )) {
			msg = "Se ha modificado la ttalla";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		} else {
			msg = "Error al modificar la ttalla";
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	public void btnDeleteTtallas(ActionEvent actionEvent) {
		String msg;
		TtallasDao ttallasDao = new TtallasDaoImpl();
		if (ttallasDao.delete(this.selectedTtalla.getId(), this.selectedTtalla)) {
			msg = "Se eliminó el TTALLAS de calzado exitosamente";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		} else {
			msg = "Error al eliminar el modelo de calzado";
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}

	}

	public String getCodTT() {
		return codTT;
	}

	public void setCodTT(String codTT) {
		this.codTT = codTT;
	}

	public void setTtallas(List<TTalla> ttallas) {
		this.ttallas = ttallas;
	}

	public void setSelectItemsExcludeByTalla(
			List<SelectItem> selectItemsExcludeByTalla) {
		this.selectItemsExcludeByTalla = selectItemsExcludeByTalla;
	}

	public String getCodTrq() {
		return codTrq;
	}

	public void setCodTrq(String codTrq) {
		this.codTrq = codTrq;
	}

	public void setSelectItemsTroquel(List<SelectItem> selectItemsTroquel) {
		this.selectItemsTroquel = selectItemsTroquel;
	}

	public String[] getSelectedTalla() {
		return selectedTalla;
	}

	public void setSelectedTalla(String[] selectedTalla) {
		this.selectedTalla = selectedTalla;
	}

	public TTalla getSelectedTtalla() {
		return selectedTtalla;
	}

	public void setSelectedTtalla(TTalla selectedTtalla) {
		this.selectedTtalla = selectedTtalla;
	}
}
