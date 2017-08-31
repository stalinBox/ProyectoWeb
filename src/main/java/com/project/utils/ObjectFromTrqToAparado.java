package com.project.utils;

import java.util.ArrayList;

/**
 * CONVERTIR EL OBJETO RECIBIDO DE TROQUELADO A APARADO LOS VALORES DE (CODPRO,
 * CODTPL)
 */
public class ObjectFromTrqToAparado {
	public ArrayList<Items4> fromToTrqToApa(ArrayList<MallObject> objectMal,
			ArrayList<Items4> result3) {

		// RECORRER EL OBJETO MALLOBJECT PARA CONVERTIR LOS VALORES A APARADO
		for (MallObject j : objectMal) {
			for (Items4 k : result3) {
				if (j.getCodPro().equals(2)
						&& k.getCodMod().equals(j.getCodMod())) {
					k.setCodProceso(2);
					k.setCodLinea(j.getCodTpl());
					k.setStandar(j.getStand());
				}
			}
		}
		return result3;
	}
}
