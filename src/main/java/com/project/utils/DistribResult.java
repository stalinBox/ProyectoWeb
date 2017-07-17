package com.project.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class DistribResult {

	public ArrayList<Items3> generateDistribDias(ArrayList<Items2> orderList22) {

		Collections.sort(orderList22, new Comparator<Items2>() {
			@Override
			public int compare(Items2 p1, Items2 p2) {
				return p1.getCodProceso() + p2.getCodLinea();

			}

		});
		// ORDENA DESCENDENTEMENTE
		// Collections.reverse(orderList22);

		// Integer ca1 = 0;
		// for (Items2 k : orderList22) {
		// System.out.println("*BASE 1*: indice: " + ca1 + " CodModelo: "
		// + k.getCodMod() + " CodParam: " + k.getCodParam()
		// + " CodProceso: " + k.getCodProceso() + " CodLinea: "
		// + k.getCodLinea() + " Standar: " + k.getStandar()
		// + " Matriz proceso: " + k.getmProcesos());
		// ca1++;
		// }

		// VARIABLES
		ArrayList<Items3> orderListFinal = new ArrayList<Items3>();
		ArrayList<ArrayList<Object>> mProcesosFinal = new ArrayList<ArrayList<Object>>();

		Integer codProceso = 0;
		Integer codTpLinea = 0;
		Object lastElem = null;
		Object lastElem2 = null;
		Object lastElem3 = null;

		orderListFinal.clear();
		Integer items3 = 0;

		int j = 0;

		for (Items2 i : orderList22) {
			Object newMatriz = null;
			Object newMatriz2 = null;
			mProcesosFinal.clear();
			ArrayList<Object> dias = new ArrayList<Object>();
			ArrayList<Object> horas = new ArrayList<Object>();

			if (j == 0) {
				// PRIMER PROCESO

				Items3 orderitemFinal = new Items3(i.getCodProceso(),
						i.getCodLinea(), i.getCodParam(), i.getCodMod(),
						i.getStandar(), i.getmProcesos());
				orderListFinal.add(orderitemFinal);

				codProceso = i.getCodProceso();
				codTpLinea = i.getCodLinea();

				lastElem2 = i.getmProcesos().get(0)
						.get(i.getmProcesos().get(0).size() - 1);

			} else if (i.getCodProceso().equals(codProceso)
					&& i.getCodLinea().equals(codTpLinea)) {
				// PROCESOS IGUALES

				if (Integer.parseInt(lastElem2.toString()) < Integer.parseInt(i
						.getStandar().toString())) {
					newMatriz = Integer.parseInt(i.getStandar().toString())
							- Integer.parseInt(lastElem2.toString());
				} else {
					newMatriz = Integer.parseInt(i.getStandar().toString());
				}

				dias.add(Math.abs(Integer.parseInt(newMatriz.toString())));

				for (int ij = 0; ij < i.getmProcesos().get(0).size(); ij++) {
					if (ij == i.getmProcesos().get(0).size() - 1) {
						break;
					} else {
						dias.add(i.getmProcesos().get(0).get(ij));
					}
				}
				newMatriz2 = Integer.parseInt(i.getmProcesos().get(0)
						.get(i.getmProcesos().get(1).size() - 1).toString())
						- Integer.parseInt(newMatriz.toString());
				dias.add(Math.abs(Integer.parseInt(newMatriz2.toString())));

				FragmentNumber2 mDias = new FragmentNumber2();
				horas = mDias.Number(i.getStandar(), dias);
				mProcesosFinal.add(dias);
				mProcesosFinal.add(horas);

				Items3 orderitemFinal = new Items3(i.getCodProceso(),
						i.getCodLinea(), i.getCodParam(), i.getCodMod(),
						i.getStandar(), new ArrayList<ArrayList<Object>>(
								mProcesosFinal));
				orderListFinal.add(orderitemFinal);

				codProceso = i.getCodProceso();
				codTpLinea = i.getCodLinea();

				lastElem2 = lastElem3;

				// FIN PROCESOS IGUALES
			} else {
				// PROCESOS DIFERENTES

				lastElem3 = i.getmProcesos().get(0)
						.get(i.getmProcesos().get(0).size() - 1);

				Items3 orderitemFinal = new Items3(i.getCodProceso(),
						i.getCodLinea(), i.getCodParam(), i.getCodMod(),
						i.getStandar(), i.getmProcesos());

				orderListFinal.add(orderitemFinal);

				codProceso = i.getCodProceso();
				codTpLinea = i.getCodLinea();

				lastElem2 = lastElem3;
				// FIN PROCESOS DIFERENTES
			}
			j++;

		}

		// int j = 0;
		//
		// for (Items2 i : orderList22) {
		// Object newMatriz = null;
		// Object newMatriz2 = null;
		// mProcesosFinal.clear();
		// ArrayList<Object> dias = new ArrayList<Object>();
		// ArrayList<Object> horas = new ArrayList<Object>();
		//
		// if (j == 0) {
		// // PRIMER PROCESO
		//
		// Items3 orderitemFinal = new Items3(i.getCodProceso(),
		// i.getCodLinea(), i.getCodParam(), i.getCodMod(),
		// i.getStandar(), i.getmProcesos());
		// orderListFinal.add(orderitemFinal);
		//
		// codProceso = i.getCodProceso();
		// codTpLinea = i.getCodLinea();
		//
		// lastElem2 = i.getmProcesos().get(0)
		// .get(i.getmProcesos().get(0).size() - 1);
		//
		// } else if (i.getCodProceso().equals(codProceso)
		// && i.getCodLinea().equals(codTpLinea)) {
		// // PROCESOS IGUALES
		//
		// if (Integer.parseInt(lastElem2.toString()) < Integer.parseInt(i
		// .getStandar().toString())) {
		// newMatriz = Integer.parseInt(i.getStandar().toString())
		// - Integer.parseInt(lastElem2.toString());
		// } else {
		// newMatriz = Integer.parseInt(i.getStandar().toString());
		// }
		//
		// dias.add(Math.abs(Integer.parseInt(newMatriz.toString())));
		//
		// for (int ij = 0; ij < i.getmProcesos().get(0).size(); ij++) {
		// if (ij == i.getmProcesos().get(0).size() - 1) {
		// break;
		// } else {
		// dias.add(i.getmProcesos().get(0).get(ij));
		// }
		// }
		// newMatriz2 = Integer.parseInt(i.getmProcesos().get(0)
		// .get(i.getmProcesos().get(1).size() - 1).toString())
		// - Integer.parseInt(newMatriz.toString());
		// dias.add(Math.abs(Integer.parseInt(newMatriz2.toString())));
		//
		// FragmentNumber2 mDias = new FragmentNumber2();
		// horas = mDias.Number(i.getStandar(), dias);
		// mProcesosFinal.add(dias);
		// mProcesosFinal.add(horas);
		//
		// Items3 orderitemFinal = new Items3(i.getCodProceso(),
		// i.getCodLinea(), i.getCodParam(), i.getCodMod(),
		// i.getStandar(), new ArrayList<ArrayList<Object>>(
		// mProcesosFinal));
		// orderListFinal.add(orderitemFinal);
		//
		// codProceso = i.getCodProceso();
		// codTpLinea = i.getCodLinea();
		//
		// lastElem2 = lastElem3;
		//
		// // FIN PROCESOS IGUALES
		// } else {
		// // PROCESOS DIFERENTES
		//
		// lastElem3 = i.getmProcesos().get(0)
		// .get(i.getmProcesos().get(0).size() - 1);
		//
		// Items3 orderitemFinal = new Items3(i.getCodProceso(),
		// i.getCodLinea(), i.getCodParam(), i.getCodMod(),
		// i.getStandar(), i.getmProcesos());
		//
		// orderListFinal.add(orderitemFinal);
		//
		// codProceso = i.getCodProceso();
		// codTpLinea = i.getCodLinea();
		//
		// lastElem2 = lastElem3;
		// // FIN PROCESOS DIFERENTES
		// }
		// j++;
		//
		// }

		// Integer citems = 0;
		// for (Items3 k : orderListFinal) {
		// System.out.println("*RESULTANTE*: indice: " + citems
		// + " CodModelo: " + k.getCodMod() + " CodParam: "
		// + k.getCodParam() + " CodProceso: " + k.getCodProceso()
		// + " CodLinea: " + k.getCodLinea() + " Standar: "
		// + k.getStandar() + " Matriz proceso: " + k.getmProcesos());
		// citems++;
		// }
		return orderListFinal;
	}
}