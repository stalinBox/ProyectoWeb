package com.project.utils;

import java.util.Arrays;

public class CheckIn {
	public static final int[] permitidos = { 1, 2, 3, 6, 7, 8, 56, 9, 86 };

	public static void main(String[] args) {
		estaEnArray(86);//  Devuelve true
		estaEnArray(13);//  Devuelve false

	}

	public static boolean estaEnArray(int numero) {
		return Arrays.asList(permitidos).contains(numero);
	}
}
