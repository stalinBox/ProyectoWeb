package com.project.utils;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class FragmentNumber {

	public ArrayList<ArrayList<Object>> Number(Integer cp, double nDias) {
		// VARIABLES
		ArrayList<Object> arrayDiasPares = new ArrayList<Object>();
		ArrayList<Object> arrDiasHoras = new ArrayList<Object>();
		ArrayList<ArrayList<Object>> arrayComplet = new ArrayList<ArrayList<Object>>();

		@SuppressWarnings("unused")
		DecimalFormat formateador = new DecimalFormat("####.###");
		// System.out.println("Clase FragmentNumero: ");
		// System.out.println("Valor cp: " + cp);
		// System.out.println("Valor Ndias: " + formateador.format(nDias));

		double a = nDias % 1;

		int b = (int) (nDias);

		// System.out.println("Parte entera: " + b);
		// System.out.println("Parte decimal: " + formateador.format(a));

		for (int i = 0; i < b; i++) {
			int j = cp / 5;
			arrayDiasPares.add(j);
		}

		// calculo independiente para la capacidad por dia
		double k = cp * a / 5;
		arrayDiasPares.add((int) Math.round(k));

		double totHoras = 0;
		for (Object z : arrayDiasPares) {
			totHoras = (Double.parseDouble(z.toString()) / cp) * 8 * 5;
			arrDiasHoras.add(totHoras);
		}
		// System.out.println("Pares: " + arrayDiasPares);
		// System.out.println("Horas: " + arrDiasHoras);

		arrayComplet.add(arrayDiasPares);
		arrayComplet.add(arrDiasHoras);

		// System.out.println("Array Completo: " + arrayComplet);
		return arrayComplet;
	}
}
