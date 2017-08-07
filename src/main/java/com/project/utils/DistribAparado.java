package com.project.utils;

import java.util.ArrayList;

/**
 * DISTRIBUIR MATRIZ APARADO
 * */
public class DistribAparado {
	public ArrayList<Items3> generateDistribDiasAparado(
			ArrayList<Items3> result3, ArrayList<MallObject> objectMal) {

		// VARIABLES
		ArrayList<ArrayList<Items3>> nuevaMatriz = new ArrayList<ArrayList<Items3>>();

		// CUERPO DE LA CLASE
		System.out.println("CLASE DISTRIB-APARADO");

		// PRUEBAS DE VISUALIZACION
		int h1 = 0;
		for (Items3 i : result3) {
			System.out.println("ITEMS 3.1 indice: " + h1 + " CodParam: "
					+ i.getCodParam() + " CodProceso: " + i.getCodProceso()
					+ " codLinea: " + i.getCodLinea() + " codModelo: "
					+ i.getCodMod() + " Stand: " + i.getStandar()
					+ " Matriz proceso: " + i.getmProcesos());
			h1++;
		}
		// FIN PRUEBAS DE VISUALIZACION
		/**
		 * CONVERTIR EL OBJETO RECIBIDO DE TROQUELADO A APARADO LOS VALORES DE
		 * (CODPRO, CODTPL)
		 */
		ObjectFromTrqToAparado fromToApa = new ObjectFromTrqToAparado();
		result3 = fromToApa.fromToTrqToApa(objectMal, result3);

		/**
		 * GUARDAR EL OBJECTO RESULT 3 POR LINEAS EN UN NUEVO OBJETO DE OBJETO
		 * */
		DistribObjectXLineas distribXLineas = new DistribObjectXLineas();
		nuevaMatriz = distribXLineas.distribXLineas(result3);

		// IMPRESION PRUEBAS
		// System.out.println("---OTRA IMPRESION RESULT 3---");
		// for (int i = 0; i < nuevaMatriz.size(); i++) {
		// System.out.println("Columna 2.0: " + i);
		// for (int j = 0; j < nuevaMatriz.get(i).size(); j++) {
		// System.out.println("Fila 2.0: "
		// + nuevaMatriz.get(i).get(j).getmProcesos());
		// }
		// }

		// FIN IMPRESION PRUEBAS
		TamMaxObjectMatriz tamMax = new TamMaxObjectMatriz();
		ObjectRowToColumns rowToCol = new ObjectRowToColumns(); // sin ocupar

		Integer itamMaxRow = 0;
		Integer mayorColumn = result3.size();
		for (ArrayList<Items3> i : nuevaMatriz) {
			System.out.println("/****** Sig. Object ********/");

			// OBTENER EL TAMAÑO MAYOR PARA EL CICLO CONSECUENTE.
			itamMaxRow = tamMax.tamMaximo(i);

			// RECORRER EL OBJETO RESULT3 POR COLUMNAS
			for (int ii = 0; ii < itamMaxRow; ii++) {
				for (int j = 0; j < mayorColumn; j++) {
					try {
						System.out.println("Elemento: "
								+ i.get(j).getmProcesos().get(0).get(ii));
					} catch (IndexOutOfBoundsException e) {
						continue;
					}
				}
				System.out.println("//");
			}
		}

		return null;
	}
}
// RECORRIDO DE UN ARRAY BIDIMIMENCIONAL
// for(i=0;i<array.size();i++){ //para cada alumno (para cada fila)
// System.out.print("Alumno " + i + ": ");
// for(j=0;j<array.get(i).size();j++){ //se recorre todas la columnas de
// la
// fila
// System.out.print(array.get(i).get(j) + " "); //se obtiene el elemento
// i,j
// }
// }