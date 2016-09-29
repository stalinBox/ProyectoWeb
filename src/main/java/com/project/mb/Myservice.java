package com.project.mb;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class Myservice implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer prodTotal = 614;
	private Integer prodCap = 361;
	private Integer numlineas = 2;
	private Integer numDias = 5;
	ArrayList<Integer> numTurnos = new ArrayList<Integer>();
	ArrayList<Integer> distribPares = new ArrayList<Integer>();
	ArrayList<Object> distribhoras = new ArrayList<Object>();

	public Myservice() {
		this.numTurnos.add(1);
		this.numTurnos.add(2);
	}

	// **** Distribucion Dynamic **** //
	public void distribDynamic() {
		ArrayList<ArrayList<Integer>> array = new ArrayList<ArrayList<Integer>>();
		System.out.println("Turnos Display: " + numTurnos);

		int d = 1;
		for (int i = 0; i < this.numlineas; i++) {
			array.add(new ArrayList<Integer>());
			d = 0;
			while (d <= this.numDias) {
				array.get(i).add(d);
				d++;
			}
		}

		// Mostrar Dinamyc Array
		for (int i = 0; i < array.size(); i++) {
			System.out.println("Linea " + i + ": ");
			for (int j = 0; j < array.get(i).size(); j++) {
				System.out.println(array.get(i).get(j));
			}
		}

	}

	// **** Distribucion Normal **** //
	public void DistribucionPares() {
		System.out.println("Capacidad de produccion: " + this.prodCap);
		System.out.println("Total a producir: " + this.prodTotal);
		disribPares(this.prodTotal, this.prodCap);
	}

	public void disribPares(Integer pTotal, Integer pCap) {
		this.distribPares.clear();
		do {
			pTotal = pTotal - pCap;
			if (pTotal < 0) {
				pTotal = pTotal + pCap;
				this.distribPares.add(pTotal);
				break;
			}
			this.distribPares.add(pCap);
		} while (pTotal >= 0);
		System.out.println(this.distribPares);
		DistribucionHoras(this.distribPares, (float) pCap);
	}

	public void DistribucionHoras(ArrayList<Integer> distribPares, float prodCap) {
		float a;
		for (Integer i : distribPares) {
			DecimalFormat formato = new DecimalFormat("##.00");
			a = 0;
			a = ((i / (float) prodCap) * 8);
			this.distribhoras.add(formato.format(a));
		}
		System.out.println(this.distribhoras);
	}

}
