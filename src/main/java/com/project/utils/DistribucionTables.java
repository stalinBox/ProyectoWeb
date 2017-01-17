package com.project.utils;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class DistribucionTables {
	// TODO Clase que genera la matriz: distribución dias/horas

	private Integer prodTotal;
	private Integer prodCap;

	ArrayList<Integer> numTurnos = new ArrayList<Integer>();
	ArrayList<Object> distribPares = new ArrayList<Object>();
	ArrayList<Object> distribhoras = new ArrayList<Object>();

	public ArrayList<ArrayList<Object>> receivParamsPares(Integer prodTotal,
			Integer prodCap, ArrayList<Integer> ListaTurnos) {

		ArrayList<ArrayList<Object>> array1 = new ArrayList<ArrayList<Object>>();
		this.prodTotal = prodTotal;
		this.prodCap = prodCap;
		this.numTurnos = ListaTurnos;

		array1 = distribDynamic();
		return array1;
	}

	public ArrayList<ArrayList<Object>> receivParamsHoras(Integer prodTotal,
			Integer prodCap, ArrayList<Integer> ListaTurnos) {
		ArrayList<ArrayList<Object>> array3 = new ArrayList<ArrayList<Object>>();
		this.prodTotal = prodTotal;
		this.prodCap = prodCap;
		this.numTurnos = ListaTurnos;

		array3 = distribDynamic2();
		return array3;
	}

	// **** Distribution Dynamic **** //
	public ArrayList<ArrayList<Object>> distribDynamic() {

		ArrayList<ArrayList<Object>> array1 = new ArrayList<ArrayList<Object>>();
		ArrayList<Object> array2 = new ArrayList<Object>();
		ArrayList<ArrayList<Object>> array3 = new ArrayList<ArrayList<Object>>();
		ArrayList<Object> array4 = new ArrayList<Object>();

		int k = 0;
		for (Object o : this.numTurnos) {
			array1.add(new ArrayList<Object>());
			array3.add(new ArrayList<Object>());
			array2 = DistribucionP(o);
			array4 = DistribucionHoras(this.distribPares, (float) this.prodCap,
					o);
			for (Object q : array2) {
				array1.get(k).add(q);
			}
			for (Object q : array4) {
				array3.get(k).add(q);
			}
			k++;
			array2.clear();
			array4.clear();
		}
		return array1;
	}

	public ArrayList<ArrayList<Object>> distribDynamic2() {
		ArrayList<ArrayList<Object>> array1 = new ArrayList<ArrayList<Object>>();
		ArrayList<Object> array2 = new ArrayList<Object>();
		ArrayList<ArrayList<Object>> array3 = new ArrayList<ArrayList<Object>>();
		ArrayList<Object> array4 = new ArrayList<Object>();
		int k = 0;
		for (Integer o : this.numTurnos) {
			array1.add(new ArrayList<Object>());
			array3.add(new ArrayList<Object>());
			array2 = DistribucionP(o);
			array4 = DistribucionHoras(this.distribPares, (float) this.prodCap,
					o);
			for (Object q : array2) {
				array1.get(k).add(q);
			}
			for (Object q : array4) {
				array3.get(k).add(q);
			}
			k++;
			array2.clear();
			array4.clear();
		}
		return array3;
	}

	// **** FUNCION DISTRIBUCION PARES **** //
	public ArrayList<Object> DistribucionP(Object o) {
		ArrayList<Object> arrPares = new ArrayList<Object>();
		arrPares = DistribucionPares(this.prodTotal, this.prodCap, o);
		return arrPares;
	}

	public ArrayList<Object> DistribucionPares(Integer pTotal, Integer pCap,
			Object o) {
		this.distribPares.clear();
		int numT = 0;
		do {
			numT = pCap * Integer.parseInt(o.toString());
			pTotal = pTotal - numT;

			if (pTotal <= 0) {
				pTotal = pTotal + numT;
				this.distribPares.add(pTotal);
				break;
			}
			this.distribPares.add(numT);
		} while (pTotal >= 0);
		return this.distribPares;

	}

	public ArrayList<Object> DistribucionHoras(ArrayList<Object> distribPares,
			float prodCap, Object o) {
		this.distribhoras.clear();

		double a, pp;
		String epa = null;
		Integer t = 8, r;
		pp = prodCap * Integer.parseInt(o.toString());
		r = t * Integer.parseInt(o.toString());
		for (Object i : distribPares) {
			DecimalFormat formato = new DecimalFormat("##.00");
			a = 0;
			epa = i.toString();
			a = ((Double.valueOf(epa) / pp) * r);
			this.distribhoras.add(formato.format(a));
		}
		return this.distribhoras;
	}

	// RECORRIDO DE UN ARRAY BIDIMIMENCIONAL
	// for(i=0;i<array.size();i++){ //para cada alumno (para cada fila)
	// System.out.print("Alumno " + i + ": ");
	// for(j=0;j<array.get(i).size();j++){ //se recorre todas la columnas de la
	// fila
	// System.out.print(array.get(i).get(j) + " "); //se obtiene el elemento i,j
	// }
	// }

	// SETTERS AND GETTERS
	public Integer getProdTotal() {
		return prodTotal;
	}

	public void setProdTotal(Integer prodTotal) {
		this.prodTotal = prodTotal;
	}

	public Integer getProdCap() {
		return prodCap;
	}

	public void setProdCap(Integer prodCap) {
		this.prodCap = prodCap;
	}

}
