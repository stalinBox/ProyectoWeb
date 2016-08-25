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

import org.primefaces.context.RequestContext;

import com.project.dao.MttDao;
import com.project.dao.MttDaoImpl;
import com.project.entities.ModTrqTal;
import com.project.entities.Modelo;
import com.project.entities.TTalla;
import com.project.entities.Talla;
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
	private String codMod;
	private List<TTalla> selectedMtt;
	private ModTrqTal mtt;

	// PRUEBA
	private int value1;
	private int value2;

	// INICIALIZADORES
	@PostConstruct
	public void init() {
		mtt = new ModTrqTal();
	}

	public MttBean() {
		this.selectedMtt = new ArrayList<TTalla>();
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

	public List<TTalla> getSelectedMtt() {
		return selectedMtt;
	}

	public void setSelectedMtt(List<TTalla> selectedMtt) {
		this.selectedMtt = selectedMtt;
	}

	public ModTrqTal getMtt() {
		return mtt;
	}

	public void setMtt(ModTrqTal mtt) {
		this.mtt = mtt;
	}

	public String getCodMod() {
		return codMod;
	}

	public void setCodMod(String codMod) {
		this.codMod = codMod;
	}

	// DMLS
	public void btnSaveMtt() {
		String msg = "";
		MttDao mttDao = new MttDaoImpl();
		Modelo m = new Modelo();
		Troquele t = new Troquele();
		Talla tl = new Talla();

		for (TTalla i : this.selectedMtt) {
			Integer pk = (Integer) i.getId().getTalCodigo();
			t.setTrqCodigo(Integer.parseInt(this.codTrq));
			m.setModCodigo(Integer.parseInt(this.codMod));

			tl.setTalCodigo(pk);

			this.mtt.setTalla(tl);
			this.mtt.setTroquele(t);
			this.mtt.setModelo(m);
			this.mtt.setDisponibilidad("1");
			if (mttDao.create(this.mtt)) {
				msg = "Se ha añadido un nuevo mtt";
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_INFO, msg, null);
				FacesContext.getCurrentInstance().addMessage(null, message);
			} else {
				msg = "Error al momento de añadir un mtt";
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_ERROR, msg, null);
				FacesContext.getCurrentInstance().addMessage(null, message);
			}
		}
	}

	public int getValue1() {
		return value1;
	}

	public void setValue1(int value1) {
		this.value1 = value1;
	}

	public int getValue2() {
		return value2;
	}

	public void setValue2(int value2) {
		this.value2 = value2;
	}

	// FUNCIONES
	public void login() {
		try {
			FacesContext contex = FacesContext.getCurrentInstance();
			contex.getExternalContext().redirect(
					MyUtil.next() + "proceso/proceso.xhtml");
		} catch (Exception e) {
			System.out
					.println("Me voy al carajo, no funciona esta redireccion");
		}
		RequestContext context = RequestContext.getCurrentInstance();
		String ruta;
		ruta = MyUtil.next() + "proceso/proceso.xhtml";
		context.addCallbackParam("ruta", ruta);
	}

	public String resetDT() {
		System.out.println("CLEANNN");
		codTrq = "";
		return null;
	}

}
