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

	private Integer prodTotal = 2000;
	private Integer prodCap = 361;
	private Integer numlineas = 2;
	private Integer numDias = 6;
	ArrayList<Integer> numTurnos = new ArrayList<Integer>();
	ArrayList<Integer> distribPares = new ArrayList<Integer>();
	ArrayList<Object> distribhoras = new ArrayList<Object>();

	public Myservice() {
		this.numTurnos.add(1);
		this.numTurnos.add(2);
	}

	// **** Distribucion Dynamic **** //
	public void distribDynamic() {
		ArrayList<ArrayList<Integer>> array1 = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> array2 = new ArrayList<Integer>();
		ArrayList<ArrayList<Object>> array3 = new ArrayList<ArrayList<Object>>();
		ArrayList<Object> array4 = new ArrayList<Object>();
		int k = 0;
		for (Integer o : this.numTurnos) {
			array1.add(new ArrayList<Integer>());
			array3.add(new ArrayList<Object>());

			array2 = DistribucionP(o);

			array4 = DistribucionHoras(this.distribPares, (float) this.prodCap,
					o);
			for (Integer q : array2) {
				array1.get(k).add(q);
			}
			for (Object q : array4) {
				array3.get(k).add(q);
			}
			k++;
			array2.clear();
			array4.clear();
		}
		System.out.println("ARRAY PARES: " + array1);
		System.out.println("ARRAY HORAS: " + array3);
	}

	// **** FUNCION DISTRIBUCION PARES **** //
	public ArrayList<Integer> DistribucionP(Integer numT) {
		ArrayList<Integer> arrPares = new ArrayList<Integer>();
		arrPares = DistribucionPares(this.prodTotal, this.prodCap, numT);
		return arrPares;
	}

	public ArrayList<Integer> DistribucionPares(Integer pTotal, Integer pCap,
			Integer numTurn) {
		this.distribPares.clear();
		int numT = 0;
		do {
			numT = pCap * numTurn;
			pTotal = pTotal - numT;

			if (pTotal < 0) {
				pTotal = pTotal + numT;
				this.distribPares.add(pTotal);
				break;
			}
			this.distribPares.add(numT);
		} while (pTotal >= 0);
		System.out.println("Distribucion PARES: " + this.distribPares);
		return this.distribPares;

	}

	public ArrayList<Object> DistribucionHoras(ArrayList<Integer> distribPares,
			float prodCap, Integer numTurn) {
		this.distribhoras.clear();

		float a, pp;
		int t = 8, r;
		pp = prodCap * numTurn;
		r = t * numTurn;
		for (Integer i : distribPares) {
			DecimalFormat formato = new DecimalFormat("##.00");
			a = 0;
			a = ((i / pp) * r);
			this.distribhoras.add(formato.format(a));
		}
		System.out.println("Distribucion HORAS: " + this.distribhoras);
		return this.distribhoras;
	}

}
