package com.project.utils;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class FragmentNumber {

	public ArrayList<Integer> Number(Integer cp, double nDias) {
		// VARIABLES
		ArrayList<Integer> array1 = new ArrayList<Integer>();
		// TODO Auto-generated method stub
		DecimalFormat formateador = new DecimalFormat("####.###");

		System.out.println("Clase FragmentarNumero: ");
		System.out.println("Valor cp: " + cp);
		System.out.println("Valor Ndias" + formateador.format(nDias));

		double a = nDias % 1;
		double b = Math.round(nDias);

		System.out.println("Parte entera: " + b);
		System.out.println("Parte decimal: " + formateador.format(a));

		for (int i = 0; i < b; i++) {
			int j = cp / 5;
			array1.add(j);
		}
		// calculo independiente para la capacidad por dia
		double k = cp * a / 5;
		array1.add((int) Math.round(k));
		return array1;
	}
}
