package com.project.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.project.dao.SettingTimesDao;
import com.project.dao.SettingTimesDaoImpl;
import com.project.mb.DistribDetalleBean.ItemsDistrib;

public class WriteAndReadExcel implements Serializable {

	private static final long serialVersionUID = 1L;

	// VARIABLES
	private Map<String, Object[]> dataOrderList = new TreeMap<String, Object[]>();

	// ***************METODOS**************
	// OBTIENE LA ORDEN DE PRODUCCION DE BEAN Y MAPEA HACIA UN MAP
	public Integer GenerarEstandar(ArrayList<ItemsDistrib> orderList,
			Double nDias, Integer codPro, Integer codTLinea)
			throws InvalidFormatException, IOException {
		Integer cpStand = null;
		Integer k = 1;
		for (ItemsDistrib i : orderList) {
			k++;
			this.dataOrderList.put("1", new Object[] { "ID", "MODELO", "TALLA",
					"CANTIDAD", "MODELOS(UNICOS)", "SUBTOTAL",
					"TOTAL ORDEN DEMANDA", "CAPACIDAD MONTAJE(par/turno)",
					"TS (min/par)", "XPONDERADO", "COMODIN",
					"CAPACIDAD PONDERADA MONTAJE", "NUMERO SIN FORMULA",
					"TOTAL PROM. PONDERADO", "UTILIZACION TURNOS",
					"TOTAL UTILIZACION TURNOS", "CAL UTILIZACION", "ERROR" });

			this.dataOrderList.put(
					k.toString(),
					new Object[] { k - 1, i.getModelo(), i.getTalla(),
							i.getCantidad() });
		}
		cpStand = SendPathFile(orderList, nDias, codPro, codTLinea);
		System.out.println("Capacidad Ponderada en WriteAndExcel: " + cpStand);
		return cpStand;
	}

	// OBTIENE EL DIRECTORIO Y LA UBICACION DEL ARCHIVO EXCEL
	public Integer SendPathFile(ArrayList<ItemsDistrib> orderList,
			Double nDias, Integer codPro, Integer codTLinea)
			throws IOException, InvalidFormatException {
		Integer cpStands = 0;

		// NOMBRE ESTATICO
		String fileName = "contentExcel/ExcelMacro/caso1Real.xlsm";
		String pathFile = "";
		String pathLocationFile = "";
		String dirNewLocationFile = null;

		ClassLoader classLoader = this.getClass().getClassLoader();
		File configFile = new File(classLoader.getResource(fileName).getFile());
		if (configFile.exists()) {
			pathFile = configFile.getPath();
			pathLocationFile = configFile.getParent();
		}
		System.out.println("GetParent: " + pathLocationFile);
		System.out.println("GetPath:   " + pathFile);

		// METODO PARA ENVIAR LA ORDEN Y ESCRIBIR EN EXCEL
		dirNewLocationFile = WritingExcelXLSM(pathFile, pathLocationFile,
				orderList, nDias, codPro, codTLinea);

		File pathNewFile = new File(dirNewLocationFile);
		if (ExecuteMacro(pathNewFile) == true) {
			cpStands = ReadingExcelXLSM(dirNewLocationFile, orderList, nDias);
		}
		return cpStands;
	}

	// ESCRIBE EN EXCEL LA ORDEN DE PRODUCCION
	public String WritingExcelXLSM(String pathFile, String pathLocationFile,
			ArrayList<ItemsDistrib> orderList, Double nDias, Integer codPro,
			Integer codTLinea) throws InvalidFormatException, IOException {

		Workbook workbook;
		workbook = new XSSFWorkbook(OPCPackage.open(pathFile));

		// OBTENER EL SHEET(HOJA) EN LA QUE HAY QUE INSERTAR LA ORDEN
		Sheet sheet = workbook.getSheetAt(0);

		// INGRESA LA ORDEN DE PRODUCCION EN EXCEL (4 PRIMERAS COLUMNAS)
		Set<String> keyset = this.dataOrderList.keySet();
		int rownum = 0;
		for (String key : keyset) {
			Row row = sheet.createRow(rownum++);
			Object[] objArr = this.dataOrderList.get(key);
			int cellnum = 0;
			for (Object obj : objArr) {
				Cell cell = row.createCell(cellnum++);
				if (obj instanceof String)
					cell.setCellValue((String) obj);
				else if (obj instanceof Integer)
					cell.setCellValue((Integer) obj);
			}
		}

		// OBTIENE LOS MODELOS UNICOS
		ArrayList<String> pp = new ArrayList<String>();
		pp = ModelosUnicos(orderList);

		// LLENAR LA VARIABLE pp2, MEDIANTE LA CONSULTA A LA BD
		ArrayList<Double> pp2 = new ArrayList<Double>();
		for (String mNombre : pp) {
			// System.out.println("MODELOS A CONSULTAR: " + mNombre);
			Double ts = null;
			SettingTimesDao sttDao = new SettingTimesDaoImpl();
			ts = sttDao.findByTs(mNombre, codPro, codTLinea, nDias);
			// System.out.println("Capacidad por proceso en pp2: " + ts);
			pp2.add(ts);
		}

		// INGRESAR EN EL "EXCEL FILE" LAS CANTIDADES DE LA VARIABLE pp2
		Integer m = 1;
		for (Double key : pp2) {
			Row r = sheet.getRow(m);
			if (r == null) {
				r = sheet.createRow(m);
			}
			Cell c = r.getCell(7);
			if (c == null) {
				c = r.createCell(7, Cell.CELL_TYPE_NUMERIC);
			}
			c.setCellValue(key);
			m++;
		}

		// GUARDA EL ARCHIVO CON OTRO NOMBRE EN LA MISMA DIRECCION
		try {
			FileOutputStream out = new FileOutputStream(new File(
					pathLocationFile + "\\caso2Real.xlsm"));
			workbook.write(out);
			out.close();
			System.out.println("xlsm creado ... ");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		String dirFile = pathLocationFile + "\\caso2Real.xlsm";
		return dirFile;
	}

	// RETORNA EL VALOR RESULTANTE DEL SOLVER
	public Integer ReadingExcelXLSM(String path,
			ArrayList<ItemsDistrib> orderList, Double nDias) throws IOException {

		Integer standaresProcesos = null;

		Double cpDoubleMontaje = null;
		Integer cpInt = null;

		FileInputStream fis = new FileInputStream(path);
		Workbook wb = new XSSFWorkbook(fis);
		Sheet sheet = wb.getSheetAt(0);

		FormulaEvaluator evaluator = wb.getCreationHelper()
				.createFormulaEvaluator();

		// Devuelve Total orden produccion
		CellReference cellReference = new CellReference("N2");
		// Devuelve Total promedio ponderado
		// CellReference cellReference1 = new CellReference("G2");

		Row row = sheet.getRow(cellReference.getRow());

		if (row != null) {
			Cell c = row.getCell(cellReference.getCol());

			// VALOR CP PRODUCCION POR PROCESO, TIPO LINEA, MODELO
			switch (evaluator.evaluateFormulaCell(c)) {
			case Cell.CELL_TYPE_NUMERIC:
				cpDoubleMontaje = c.getNumericCellValue();
				cpInt = (int) Math.round(cpDoubleMontaje);
				break;
			case Cell.CELL_TYPE_FORMULA:
				break;
			}
		}
		return cpInt;
	}

	// RETORNA VALORES SIN REPETIR
	public ArrayList<String> ModelosUnicos(ArrayList<ItemsDistrib> orderList) {
		ArrayList<String> arrMod = new ArrayList<String>();
		ArrayList<Integer> arrCanti = new ArrayList<Integer>();
		Set<String> hs = new HashSet<>();
		for (ItemsDistrib i : orderList) {
			arrMod.add(i.getModelo());
			arrCanti.add(i.getCantidad());
		}
		hs.addAll(arrMod);
		arrMod.clear();
		arrMod.addAll(hs);
		Collections.sort(arrMod);

		return arrMod;
	}

	// EJECUTA LA MACRO EN EXCEL
	public boolean ExecuteMacro(File pathFile) {
		// VARIABLE CON EL NOMBRE DE LA MACRO
		String macroNameMontaje = "!MacroGeneral";// Ocupar esta macro pa todo
		PrintSolveMB execute = new PrintSolveMB();
		try {
			if (execute.executeMacro(pathFile, macroNameMontaje) == true) {
				System.out.println("MACROS SUCCESFULL");
				return true;
			} else {
				System.out.println("FALLO ALGO EN LAS MACRO");
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}

	// SETTERS AND GETTERS
	public Map<String, Object[]> getDataOrderList() {
		return dataOrderList;
	}

	public void setDataOrderList(Map<String, Object[]> dataOrderList) {
		this.dataOrderList = dataOrderList;
	}
}
