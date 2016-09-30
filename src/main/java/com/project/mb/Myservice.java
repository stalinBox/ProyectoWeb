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

		int d = 0;

		for (int i = 0; i < this.numlineas; i++) {
			System.out.println("INICIO CICLO");
			System.out.println("***************** vuelta numero: " + i);
			array1.add(new ArrayList<Integer>());
			d = 0;
			for (Integer o : this.numTurnos) {
				array2 = DistribucionPares(o);
				while (d < this.numDias) {
					array1.get(i).add(d);
					d++;
				}
			}
			System.out.println("FIN CICLO");
		}

		// Mostrar Dinamyc Array1
		for (int i = 0; i < array1.size(); i++) {
			System.out.println("Linea " + i + ": ");
			for (int j = 0; j < array1.get(i).size(); j++) {
				System.out.println(array1.get(i).get(j));
			}
		}
	}

	// **** FUNCION DISTRIBUCION PARES **** //
	public ArrayList<Integer> DistribucionPares(Integer numT) {
		ArrayList<Integer> arrPares = new ArrayList<Integer>();
		arrPares = disribPares(this.prodTotal, this.prodCap, numT);

		return arrPares;
	}

	public ArrayList<Integer> disribPares(Integer pTotal, Integer pCap,
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
		// DistribucionHoras(this.distribPares, (float) numT, numTurn);
		System.out.println("Distribucion PARES: " + this.distribPares);
		return this.distribPares;

	}

	public void DistribucionHoras(ArrayList<Integer> distribPares,
			float prodCap, Integer numTurn) {
		float a;
		int t = 8, r;
		r = t * numTurn;
		for (Integer i : distribPares) {
			DecimalFormat formato = new DecimalFormat("##.00");
			a = 0;
			a = ((i / (float) prodCap) * r);
			this.distribhoras.add(formato.format(a));
		}
		System.out.println("Distribucion HORAS: " + this.distribhoras);
	}

}
