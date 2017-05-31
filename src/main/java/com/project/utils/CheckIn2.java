package com.project.utils;

import java.util.ArrayList;

public class CheckIn2 {
	public static boolean estaEnArray(ArrayList<Integer> permitidos, int numero) {
		// System.out.println("Permitidos: " + permitidos);
		// System.out.println("numero: " + numero);
		return (permitidos).contains(numero);
	}
}
