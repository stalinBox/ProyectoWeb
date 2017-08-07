package com.project.utils;

import java.util.ArrayList;

/**
 * CONVERTIR EL OBJETO RECIBIDO DE TROQUELADO A APARADO LOS VALORES DE (CODPRO,
 * CODTPL)
 */
public class ObjectFromTrqToAparado {
	public ArrayList<Items3> fromToTrqToApa(ArrayList<MallObject> objectMal,
			ArrayList<Items3> result3) {

		// PROCESO
		for (MallObject j : objectMal) {
			for (Items3 k : result3) {
				if (j.getCodPro().equals(2)
						&& k.getCodMod().equals(j.getCodMod())) {
					k.setCodProceso(2);
					k.setCodLinea(j.getCodTpl());
				}
			}
		}
		return result3;
	}
}
