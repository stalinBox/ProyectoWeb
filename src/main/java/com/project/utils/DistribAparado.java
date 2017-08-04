package com.project.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DistribAparado {
	public ArrayList<Items3> generateDistribDiasAparado(
			ArrayList<Items3> result3) {
		/**
		 * DISTRIBUIR MATRIZ APARADO
		 * */

		// VARIABLES
		Integer codMod = 0;

		ArrayList<ArrayList<Items3>> nuevaMatriz = new ArrayList<ArrayList<Items3>>();

		// CUERPO DE LA CLASE
		System.out.println("CLASE DISTRIB-APARADO");

		// PRUEBAS DE VISUALIZACION
		int h1 = 0;
		for (Items3 i : result3) {
			System.out.println("ITEMS 3.1 indice: " + h1 + " CodParam: "
					+ i.getCodParam() + " CodProceso: " + i.getCodProceso()
					+ " codLinea: " + i.getCodLinea() + " codModelo: "
					+ i.getCodMod() + " Stand: " + i.getStandar()
					+ " Matriz proceso: " + i.getmProcesos());

			h1++;
		}

		// FIN PRUEBAS DE VISUALIZACION

		// ARMAR LA NUEVA MATRIZ 2D DE OBJETO RESULT3

		nuevaMatriz.add(new ArrayList<Items3>());
		nuevaMatriz.get(0).add(result3.get(0));
		nuevaMatriz.get(0).add(result3.get(1));
		nuevaMatriz.get(0).add(result3.get(2));
		nuevaMatriz.add(new ArrayList<Items3>());
		nuevaMatriz.get(1).add(result3.get(3));

		for (Items3 i : result3) {

		}

		for (int i = 0; i < nuevaMatriz.size(); i++) {
			System.out.println("Columna: " + i);
			for (int j = 0; j < nuevaMatriz.get(i).size(); j++) {
				System.out.println("Fila: "
						+ nuevaMatriz.get(i).get(j).getmProcesos());
			}
		}

		// RECORRIDO DE UN ARRAY BIDIMIMENCIONAL
		// for(i=0;i<array.size();i++){ //para cada alumno (para cada fila)
		// System.out.print("Alumno " + i + ": ");
		// for(j=0;j<array.get(i).size();j++){ //se recorre todas la columnas de
		// la
		// fila
		// System.out.print(array.get(i).get(j) + " "); //se obtiene el elemento
		// i,j
		// }
		// }

		// OBTENER EL TAMAÑO MAYOR PARA EL CICLO CONSECUENTE.
		// Integer mayorFila = 0;
		// Integer mayorColumn = result3.size();
		// for (Items3 i : result3) {
		// if (i.getmProcesos().get(0).size() > mayorFila) {
		// mayorFila = i.getmProcesos().get(0).size();
		// }
		// }

		// RECORRER EL OBJETO RESULT3 POR COLUMNAS
		// for (int i = 0; i < mayorFila; i++) {
		// for (int j = 0; j < mayorColumn; j++) {
		// try {
		// System.out.println("Elemento: "
		// + result3.get(j).getmProcesos().get(0).get(i));
		//
		// } catch (IndexOutOfBoundsException e) {
		// continue;
		// }
		// }
		// System.out.println("//");
		// }

		return null;
	}
}
