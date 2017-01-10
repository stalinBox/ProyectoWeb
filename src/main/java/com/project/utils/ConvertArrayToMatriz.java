package com.project.utils;

import java.util.ArrayList;

public class ConvertArrayToMatriz {

	public Object[][] convertArray(ArrayList<ArrayList<Object>> array0) {
		Object[][] arrayPrincipal = new Object[array0.size()][];
		for (int i = 0; i < array0.size(); i++) {
			ArrayList<Object> row = array0.get(i);
			arrayPrincipal[i] = row.toArray(new Object[row.size()]);
		}

		return arrayPrincipal;
	}

}
