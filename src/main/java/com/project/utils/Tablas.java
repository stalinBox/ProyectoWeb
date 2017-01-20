package com.project.utils;

import java.util.ArrayList;
import java.util.Map;

public class Tablas {

	public Map<Integer, ArrayList<Integer>> receivParamsPares(
			Integer prodTotal, Integer prodCap,
			Map<Integer, Object> LineasTurnos) {

		ArrayList<Integer> array = new ArrayList<Integer>();
		double diasLab = 0;
		Integer cp = 0;

		cp = prodCap * LineasTurnos.size();
		diasLab = prodTotal.doubleValue() / cp.doubleValue() * 5;

		System.out.println("PARAMETROS PARA TRABAJAR");
		System.out.println("OrdenTotal: " + prodTotal);
		System.out.println("Capacidad por proceso: " + cp);
		System.out.println("Dias a laborar: " + diasLab);

		FragmentNumber abc = new FragmentNumber();
		array = abc.Number(cp, diasLab);
		System.out.println("array: " + array);

		int a = 0;
		for (Integer i : array) {
			a += i;
		}
		System.out.println("Total Suma: " + a);
		return null;
	}
}
