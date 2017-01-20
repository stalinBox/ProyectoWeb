package com.project.utils;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class JavaAppletTest {

	public static void main(String[] args) {
		// VARIABLES
		ArrayList<Integer> array1 = new ArrayList<Integer>();
		ArrayList<Double> array2 = new ArrayList<Double>();
		// TODO Auto-generated method stub
		DecimalFormat formateador = new DecimalFormat("####.###");

		System.out.println("Clase FragmentarNumero: ");
		double num = 1.127;
		Integer cp = 846;

		System.out.println("Numero a trabajar: " + formateador.format(num));

		double a = num % 1;
		double b = Math.round(num);

		System.out.println("Parte entera: " + b);
		System.out.println("Parte decimal: " + formateador.format(a));

		for (int i = 0; i < b; i++) {
			int j = cp / 5;
			array1.add(j);
		}
		// calculo independiente para la capacidad por dia
		double k = cp * a / 5;
		array1.add((int) Math.round(k));
		System.out.println("Array" + array1);

		double horas = 0;
		for (Integer i : array1) {
			horas = ((i / cp.doubleValue()) * 5 * 8);
			array2.add(horas);
		}
		System.out.println("Horas: " + array2);
	}
}
