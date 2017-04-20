package com.project.mb;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.project.utils.ItemsParams;

@ManagedBean
@ViewScoped
public class ParamBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<ItemsParams> selectedParams;

	@PostConstruct
	public void init() {

	}

	public void btnEject() {
		System.out.println("PROCESO: " + ItemsParams.getProceso());
		System.out.println("TIPO LINEA:" + ItemsParams.getTipoLinea());
		System.out.println("CAPACIDAD: " + ItemsParams.getCpPonderado());
		this.selectedParams = ItemsParams;
		for (ItemsParams p : ItemsParams) {

		}
	}

	// SETTERS AND GETTERS
	public List<ItemsParams> getSelectedParams() {
		return selectedParams;
	}

	public void setSelectedParams(List<ItemsParams> selectedParams) {
		this.selectedParams = selectedParams;
	}

}
