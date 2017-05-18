package com.project.utils;

import java.util.ArrayList;

public class Tablas {

	public ArrayList<ArrayList<Object>> receivParamsPares(Integer prodTotal,
			Integer capProd, Object countLineas, Double nDias,
			Integer cantLineas) {

		ArrayList<ArrayList<Object>> arrayCompleto = new ArrayList<ArrayList<Object>>();
		double diasLab = 0;
		Integer cp = 0;

		cp = capProd * Integer.parseInt(countLineas.toString()) * cantLineas;
		diasLab = prodTotal.doubleValue() / cp.doubleValue();

		// System.out.println("1- PARAMETROS PARA TRABAJAR CON LA CLASE TABLAS");
		// System.out.println("2- OrdenTotal: " + prodTotal);
		// System.out.println("3- Capacidad por tipo linea: " + cp);
		// System.out.println("4- Dias a laborar: " + diasLab);
		// System.out.println("5- Cantidad turnos: " + cantLineas);
		// System.out.println("6- Cantidad turnos: " + diasLab);

		FragmentNumber abc = new FragmentNumber();
		arrayCompleto = abc.Number(cp, diasLab, prodTotal);

		return arrayCompleto;
	}
}
