package com.project.utils;

import java.util.ArrayList;

public class Tablas {

	public ArrayList<ArrayList<Object>> receivParamsPares(Integer prodTotal,
			Integer prodCap, Object countLineas, Double nDias,
			Integer cantLineas) {

		// ** VERIFICAR VAR nDias
		nDias = 6.0;

		ArrayList<ArrayList<Object>> arrayCompleto = new ArrayList<ArrayList<Object>>();
		double diasLab = 0;
		Integer cp = 0;

		cp = prodCap * Integer.parseInt(countLineas.toString()) * cantLineas;
		diasLab = prodTotal.doubleValue() / cp.doubleValue() * nDias;

		// System.out.println("1- PARAMETROS PARA TRABAJAR CON LA CLASE TABLAS");
		// System.out.println("1- OrdenTotal: " + prodTotal);
		// System.out.println("1- Capacidad por tipo linea: " + cp);
		// System.out.println("1- Dias a laborar: " + diasLab);

		FragmentNumber abc = new FragmentNumber();
		arrayCompleto = abc.Number(cp, diasLab, nDias, prodTotal);

		return arrayCompleto;
	}
}
