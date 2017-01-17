package com.project.utils;

import java.util.ArrayList;

public class ConvertMatrizTranspuesta {

	public ArrayList<Integer> converMatrizTranspuesta(Object[][] array2) {

		Object[][] matrizT1 = new Object[array2[0].length][array2.length];
		ArrayList<Integer> a = new ArrayList<Integer>();
		for (int x = 0; x < array2.length; x++) {
			for (int y = 0; y < array2[x].length; y++) {
				matrizT1[y][x] = array2[x][y];
				a.add((Integer) matrizT1[y][x]);
			}
		}
		System.out.println("MATRIZ TRANS: " + a);
		return a;
	}
}
