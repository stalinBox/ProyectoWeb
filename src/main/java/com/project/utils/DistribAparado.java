package com.project.utils;

import java.util.ArrayList;

public class DistribAparado {
	public ArrayList<Items3> generateDistribDiasAparado(
			ArrayList<Items3> result3) {
		/**
		 * DISTRIBUIR MATRIZ APARADO
		 * */

		// VARIABLES
		Integer codMod = 0;

		System.out.println("CLASE DISTRIB-APARADO");

		// PRUEBAS DE VISUALIZACION
		int h1 = 0;
		for (Items3 i : result3) {
			System.out.println("ITEMS 4.1 indice: " + h1 + " CodParam: "
					+ i.getCodParam() + " CodProceso: " + i.getCodProceso()
					+ " codLinea: " + i.getCodLinea() + " codModelo: "
					+ i.getCodMod() + " Stand: " + i.getStandar()
					+ " Matriz proceso: " + i.getmProcesos());

			h1++;
		}
		// FIN PRUEBAS DE VISUALIZACION

		for (int i = 0; i < result3.size(); i++) {
			System.out.println(" :MATRIZ: " + result3.get(i).getmProcesos());
			for (int j = 0; j < result3.get(i).getmProcesos().get(0).size(); j++) {
				System.out.println("tamaño: "
						+ result3.get(i).getmProcesos().get(0).get(j));
			}
		}

		return null;
	}
}
