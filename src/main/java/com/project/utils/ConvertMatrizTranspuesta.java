package com.project.utils;

public class ConvertMatrizTranspuesta {

	public Object[][] converMatrizTranspuesta(Object[][] array2) {
		Object[][] matrizT1 = new Object[array2[0].length][array2.length];
		for (int x = 0; x < array2.length; x++) {
			for (int y = 0; y < array2[x].length; y++) {
				matrizT1[y][x] = array2[x][y];
			}
		}
		return matrizT1;
	}
}
