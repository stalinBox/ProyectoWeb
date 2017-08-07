package com.project.utils;

import java.util.ArrayList;

/**
 * GUARDAR EL OBJECTO RESULT 3 POR LINEAS EN UN NUEVO OBJETO DE OBJETO
 * */
public class DistribObjectXLineas {
	/**
	 * GUARDAR EL OBJECTO RESULT 3 POR LINEAS EN UN NUEVO OBJETO DE OBJETO
	 * 
	 * @param result3
	 * @return
	 * */
	public ArrayList<ArrayList<Items3>> distribXLineas(ArrayList<Items3> result3) {

		// VARIABLES
		ArrayList<ArrayList<Items3>> nuevaMatriz = new ArrayList<ArrayList<Items3>>();

		// PROCESO
		int codTPL = 0;
		int iCount = 0;
		int iCount2 = 0;
		for (Items3 i : result3) {
			if (iCount2 == 0) {
				if (!(i.getCodLinea().equals(codTPL))) {
					nuevaMatriz.add(new ArrayList<Items3>());
					nuevaMatriz.get(iCount).add(i);
					codTPL = i.getCodLinea();
				}
			} else {
				if (i.getCodLinea().equals(codTPL)) {
					nuevaMatriz.get(iCount).add(i);
					codTPL = i.getCodLinea();
				} else {
					iCount++;
					nuevaMatriz.add(new ArrayList<Items3>());
					nuevaMatriz.get(iCount).add(i);
					codTPL = i.getCodLinea();
				}
			}
			iCount2++;
		}

		return nuevaMatriz;
	}
}
