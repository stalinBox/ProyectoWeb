package com.project.utils;

import java.util.ArrayList;

/**
 * OBTENER EL TAMAÑO MAYOR PARA EL CICLO CONSECUENTE.
 * */
public class TamMaxObjectMatriz {
	public Integer tamMaximo(ArrayList<Items4> result3) {
		Integer mayorFila = 0;

		for (Items4 i : result3) {
			if (i.getmProcesos().get(0).size() > mayorFila) {
				mayorFila = i.getmProcesos().get(0).size();
			}
		}
		return mayorFila;
	}
}
