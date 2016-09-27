package com.project.mb;

import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class Myservice implements Serializable {

	private static final long serialVersionUID = 1L;

	private ArrayList<ArrayList<Integer>> cols;
	private Integer prodTotal = 614;
	private Integer prodCap = 361;
	ArrayList<Integer> distribPares = new ArrayList<Integer>();
	ArrayList<Integer> horas = new ArrayList<Integer>();

	public ArrayList<ArrayList<Integer>> getCols() {
		return cols;
	}

	public void setCols(ArrayList<ArrayList<Integer>> cols) {
		this.cols = cols;
	}

	public void DistribucionPares() {

		System.out.println("Total a producir: " + prodTotal);
		System.out.println("Capacidad de produccion: " + prodCap);

		do {
			prodTotal = prodTotal - prodCap;

			if (prodTotal < 0) {
				prodTotal = prodTotal + prodCap;
				distribPares.add(prodTotal);
				break;
			}
			distribPares.add(prodCap);

		} while (prodTotal >= 0);
		System.out.println(distribPares);
	}

	public void DistribucionHoras() {

	}
}
