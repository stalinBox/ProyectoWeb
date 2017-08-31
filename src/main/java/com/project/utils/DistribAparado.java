package com.project.utils;

import java.util.ArrayList;

/**
 * DISTRIBUIR MATRIZ APARADO
 * */
public class DistribAparado {
	public ArrayList<Items5> generateDistribDiasAparado(
			ArrayList<Items3> result3, ArrayList<MallObject> objectMal) {

		// VARIABLES
		ArrayList<ArrayList<Items4>> ObjectOfObject = new ArrayList<ArrayList<Items4>>();
		ArrayList<ArrayList<Object>> mProcesos = new ArrayList<ArrayList<Object>>();
		ArrayList<Items4> result4 = new ArrayList<Items4>();
		ArrayList<Items5> result5 = new ArrayList<Items5>();
		Tablas tablas = new Tablas();

		// CUERPO DE LA CLASE
		System.out.println("CLASE DISTRIB-APARADO");

		// COPIAR EL OBJETO RECIBIDO A OTRO DEL MISMO VALOR
		for (Items3 i : result3) {
			Items4 soloItems = new Items4(i.getCodProceso(), i.getCodLinea(),
					i.getCodParam(), i.getCodMod(), i.getStandar(),
					i.getmProcesos());
			result4.add(soloItems);
		}

		/**
		 * CONVERTIR EL OBJETO RECIBIDO DE (TROQUELADO A APARADO) LOS VALORES DE
		 * (CODPRO, CODTPL)
		 */
		ObjectFromTrqToAparado fromToApa = new ObjectFromTrqToAparado();
		result4 = fromToApa.fromToTrqToApa(objectMal, result4);

		/**
		 * GUARDAR EL OBJECTO RESULT 3 POR LINEAS EN UN NUEVO OBJETO DE OBJETO
		 * FORMAR UN OBJECTO 3D
		 * */
		DistribObjectXLineas distribXLineas = new DistribObjectXLineas();
		ObjectOfObject = distribXLineas.distribXLineas(result4);

		// PRUEBAS VISUALIZACION ITEMS4
		System.out.println("----ANTES DE:----");
		Integer ca14 = 0;
		for (Items4 k : result4) {
			System.out.println("*ITEMS4*: indice: " + ca14 + " CodModelo: "
					+ k.getCodMod() + " CodParam: " + k.getCodParam()
					+ " CodProceso: " + k.getCodProceso() + " CodLinea: "
					+ k.getCodLinea() + " Standar: " + k.getStandar()
					+ " Matriz proceso: " + k.getmProcesos());
			ca14++;
		}
		// FIN PRUEBAS VISUALIZACION ITEMS4

		// INSTACIACION CLASE TAMMAXOBJECTMATRIZ
		TamMaxObjectMatriz tamMax = new TamMaxObjectMatriz();

		Integer itamMaxRow = 0;
		Integer mayorColumn = result4.size();

		for (ArrayList<Items4> i : ObjectOfObject) {
			// OBTENER EL TAMAÑO MAYOR PARA EL CICLO CONSECUENTE.
			itamMaxRow = tamMax.tamMaximo(i);

			// RECORRER EL OBJETO RESULT4 POR COLUMNAS
			for (int ii = 0; ii < itamMaxRow; ii++) {
				// System.out.println("//");
				for (int j = 0; j < mayorColumn; j++) {

					try {
						for (MallObject oj : objectMal) {
							if (i.get(j).getCodLinea() == oj.getCodTpl()
									&& i.get(j).getCodProceso() == oj
											.getCodPro()
									&& i.get(j).getCodMod()
											.equals(oj.getCodMod())) {
								// System.out.println("Elemento: "
								// + i.get(j).getmProcesos().get(0)
								// .get(ii));
								mProcesos = tablas.receivParamsPares(
										(Integer) i.get(j).getmProcesos()
												.get(0).get(ii), i.get(j)
												.getStandar(), oj
												.getCantLineas(), 0.0, oj
												.getCountLineas());
								Items5 soloItems = new Items5(i.get(j)
										.getCodProceso(), i.get(j)
										.getCodLinea(), oj.getCodParam(),
										oj.getCodMod(), oj.getStand(),
										mProcesos);
								result5.add(soloItems);
							} else {
								continue;
							}
						}
					} catch (IndexOutOfBoundsException e) {
						continue;
					}
				}
			}
		}

		System.out.println("----RESULTADO DE:----");
		// PRUEBAS VISUALIZACION ITEMS4
		Integer ca11 = 0;
		for (Items5 k : result5) {
			System.out.println("*ITEMS5*: indice: " + ca11 + " CodModelo: "
					+ k.getCodMod() + " CodParam: " + k.getCodParam()
					+ " CodProceso: " + k.getCodProceso() + " CodLinea: "
					+ k.getCodLinea() + " Standar: " + k.getStandar()
					+ " Matriz proceso: " + k.getmProcesos());
			ca11++;
		}
		// FIN PRUEBAS VISUALIZACION ITEMS4
		return result5;
	}
}