package com.project.utils;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class FragmentNumber {

	public ArrayList<ArrayList<Object>> Number(Integer cp, double diasLab,
			Double nDias, Integer prodTotal) {
		// VARIABLES
		ArrayList<Object> arrayDiasPares = new ArrayList<Object>();
		ArrayList<Object> arrDiasHoras = new ArrayList<Object>();
		ArrayList<ArrayList<Object>> arrayComplet = new ArrayList<ArrayList<Object>>();

		@SuppressWarnings("unused")
		DecimalFormat formateador = new DecimalFormat("####.###");
		// System.out.println("Clase FragmentNumero: ");
		// System.out.println("Valor cp: " + cp);
		// System.out.println("Valor Ndias: " + formateador.format(nDias));

		// double a = diasLab % 1;

		int b = (int) (diasLab);

		// System.out.println("2- Parte entera (b): " + b);
		// System.out.println("2- Parte decimal (a): " + a);

		int acum = 0;
		// Calculo para la parte entera de diasLab(b)
		for (int i = 0; i < b; i++) {
			int j = (int) (cp / nDias);
			acum += j;
			System.out.println("parte entera var j: " + j);
			arrayDiasPares.add(j);
		}

		// Calculo para la parte flotante de diasLab(a)
		if (acum != prodTotal) {
			arrayDiasPares.add((prodTotal - acum));
		}

		// Calculo de horas
		double totHoras = 0;
		for (Object z : arrayDiasPares) {
			totHoras = (Double.parseDouble(z.toString()) / cp) * 8 * nDias;
			arrDiasHoras.add(totHoras);
		}
		System.out.println("2- Pares: " + arrayDiasPares);
		System.out.println("2- Horas: " + arrDiasHoras);

		arrayComplet.add(arrayDiasPares);
		arrayComplet.add(arrDiasHoras);

		return arrayComplet;
	}
}
