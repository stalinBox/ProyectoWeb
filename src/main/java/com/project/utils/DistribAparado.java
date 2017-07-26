package com.project.utils;

import java.util.ArrayList;

public class DistribAparado {
	public ArrayList<Items4> generateDistribDiasAparado(
			ArrayList<Items4> result4) {
		/**
		 * DISTRIBUIR MATRIZ APARADO
		 * */

		// VARIABLES
		Integer codMod = 0;

		int h1 = 0;
		for (Items4 i : result4) {
			System.out.println("ITEMS 4.1 indice: " + h1 + " CodParam: "
					+ i.getCodParam() + " CodProceso: " + i.getCodProceso()
					+ " codLinea: " + i.getCodLinea() + " codModelo: "
					+ i.getCodMod() + " Stand: " + i.getStandar()
					+ " Matriz proceso: " + i.getmProcesos());

			h1++;
		}

		int h2 = 0;
		for (Items4 i : result4) {
			if (h2 == 0) {
				// PRIMERA VUELTA
				System.out.println("**1**" + h2 + " CodParam: "
						+ i.getCodParam() + " CodProceso: " + i.getCodProceso()
						+ " codLinea: " + i.getCodLinea() + " codModelo: "
						+ i.getCodMod() + " Stand: " + i.getStandar()
						+ " Matriz proceso: " + i.getmProcesos());
				codMod = i.getCodMod();
			} else if (!(i.getCodMod().equals(codMod))) {
				System.out.println("**2**" + h2 + " CodParam: "
						+ i.getCodParam() + " CodProceso: " + i.getCodProceso()
						+ " codLinea: " + i.getCodLinea() + " codModelo: "
						+ i.getCodMod() + " Stand: " + i.getStandar()
						+ " Matriz proceso: " + i.getmProcesos());
				codMod = i.getCodMod();
			}
			h2++;
		}
		return null;
	}
}
