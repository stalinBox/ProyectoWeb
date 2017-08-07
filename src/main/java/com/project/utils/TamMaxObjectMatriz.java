package com.project.utils;

import java.util.ArrayList;

/**
 * OBTENER EL TAMAÑO MAYOR PARA EL CICLO CONSECUENTE.
 * */
public class TamMaxObjectMatriz {
	public Integer tamMaximo(ArrayList<Items3> result3) {
		Integer mayorFila = 0;

		for (Items3 i : result3) {
			if (i.getmProcesos().get(0).size() > mayorFila) {
				mayorFila = i.getmProcesos().get(0).size();
			}
		}
		return mayorFila;
	}
}
