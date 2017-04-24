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

	private List<ItemsParams> cars;

	@PostConstruct
	public void init() {
		cars = DistribDetalleBean.getOrderlistparams();
		for (ItemsParams i : cars) {
			System.out.println("Proceso1: " + i.getProceso() + " TpLinea1: "
					+ i.getTipoLinea() + " Capacidad1: " + i.getCpPonderado());
		}
	}

	public void btnEject() {
	}

	// SETTERS AND GETTERS
	public List<ItemsParams> getSelectedParams() {
		return selectedParams;
	}

	public void setSelectedParams(List<ItemsParams> selectedParams) {
		this.selectedParams = selectedParams;
	}

	public List<ItemsParams> getCars() {
		return cars;
	}

	public void setCars(List<ItemsParams> cars) {
		this.cars = cars;
	}

}
