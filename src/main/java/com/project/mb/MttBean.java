package com.project.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import com.project.dao.MttDao;
import com.project.dao.MttDaoImpl;
import com.project.entities.ModTrqTal;
import com.project.entities.Modelo;
import com.project.entities.TTalla;
import com.project.entities.Troquele;
import com.project.utils.MyUtil;

@ManagedBean
@ViewScoped
public class MttBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<SelectItem> selectItemsTroquel;
	private List<SelectItem> selectItemsModelo;
	private List<TTalla> tallas;
	private String codTrq;
	// pruebas
	private Boolean SiNo;
	private String btn;
	private String[] mmtttt;
	private String codMod;

	private List<ModTrqTal> selectedMtt;
	private ModTrqTal mtt;

	// INICIALIZADORES
	@PostConstruct
	public void init() {
		mtt = new ModTrqTal();
	}

	public MttBean() {
		this.selectedMtt = new ArrayList<ModTrqTal>();
	}

	// SETTERS AND GETTERS
	public List<SelectItem> getSelectItemsTroquel() {
		this.selectItemsTroquel = new ArrayList<SelectItem>();
		MttDao TroquelMtt = new MttDaoImpl();
		List<Troquele> Ttroquel = TroquelMtt.selectItemsTroqueles();
		selectItemsTroquel.clear();
		for (Troquele ttro : Ttroquel) {
			SelectItem selectItem = new SelectItem(ttro.getTrqCodigo(),
					ttro.getTrqNombre());
			this.selectItemsTroquel.add(selectItem);
		}
		return selectItemsTroquel;
	}

	public void setSelectItemsTroquel(List<SelectItem> selectItemsTroquel) {
		this.selectItemsTroquel = selectItemsTroquel;
	}

	public List<SelectItem> getSelectItemsModelo() {
		this.selectItemsModelo = new ArrayList<SelectItem>();
		MttDao ModeloTTDao = new MttDaoImpl();
		List<Modelo> mttModelo = ModeloTTDao.selectItemsModelos();
		selectItemsModelo.clear();
		for (Modelo mttMod : mttModelo) {
			SelectItem selectItem = new SelectItem(mttMod.getModCodigo(),
					mttMod.getModNombre());
			this.selectItemsModelo.add(selectItem);
		}
		return selectItemsModelo;
	}

	public void setSelectItemsModelo(List<SelectItem> selectItemsModelo) {
		this.selectItemsModelo = selectItemsModelo;
	}

	public List<TTalla> getTallas() {
		if (this.codTrq != null && !this.codTrq.equals("")
				&& this.codTrq != "0") {
			MttDao tallasDao = new MttDaoImpl();
			this.tallas = tallasDao.selectItemsTallas(Integer
					.parseInt(this.codTrq));
			return tallas;
		} else {
			this.tallas = new ArrayList<TTalla>();
			return tallas;
		}
	}

	public void setTallas(List<TTalla> tallas) {
		this.tallas = tallas;
	}

	public String getCodTrq() {
		return codTrq;
	}

	public void setCodTrq(String codTrq) {
		this.codTrq = codTrq;
	}

	// pruebas

	public String getBtn() {
		return btn;
	}

	public List<ModTrqTal> getSelectedMtt() {
		return selectedMtt;
	}

	public void setSelectedMtt(List<ModTrqTal> selectedMtt) {
		this.selectedMtt = selectedMtt;
	}

	public ModTrqTal getMtt() {
		return mtt;
	}

	public void setMtt(ModTrqTal mtt) {
		this.mtt = mtt;
	}

	public Boolean getSiNo() {
		return SiNo;
	}

	public void setSiNo(Boolean siNo) {
		SiNo = siNo;
	}

	public void setBtn(String btn) {
		this.btn = btn;
	}

	public String[] getMmtttt() {
		return mmtttt;
	}

	public void setMmtttt(String[] mmtttt) {
		this.mmtttt = mmtttt;
	}

	public String getCodMod() {
		return codMod;
	}

	public void setCodMod(String codMod) {
		this.codMod = codMod;
	}

	// PRUEBA
	public String messagesPath() {
		return MyUtil.messages();
	}

	// DMLS

	public void btnSaveMtt() {
		String msg = "";
		MttDao mttDao = new MttDaoImpl();
		this.mtt.setDisponibilidad(true);
//		this.mtt.setModelo(this.codMod);
//		this.mtt.setTalla(talla);
//		this.mtt.setTroquele(troquele);

		if (mttDao.create(this.mtt)) {
			msg = "Se ha añadido un nuevo mtt";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		} else {
			msg = "Error al momento de añadir un mtt";
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, msg, null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}

		String a = this.codTrq.toString();
		String b = this.codMod.toString();
		System.out.println("Datos guardados TROQUEL..." + a);
		System.out.println("Datos guardados MODELO..." + b);
		for (Object i : this.selectedMtt) {
			System.out.println("Tallas: " + i.toString());
		}
	}
}
