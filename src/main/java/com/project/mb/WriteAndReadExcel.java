package com.project.mb;

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

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@ManagedBean(name = "WriteAndReadExcel")
@ViewScoped
public class WriteAndReadExcel implements Serializable {

	private static final long serialVersionUID = 1L;

	// VARIABLES
	private Map<String, Object[]> dataOrderList = new TreeMap<String, Object[]>();

	// SETTERS AND GETTERS
	public Map<String, Object[]> getDataOrderList() {
		return dataOrderList;
	}

	public void setDataOrderList(Map<String, Object[]> dataOrderList) {
		this.dataOrderList = dataOrderList;
	}

	// ***************METODOS**************
	// Obtiene la orden de produccion de Bean y mapea hacia un map
	public Integer getOrder(ArrayList<Items> orderList)
			throws InvalidFormatException, IOException {
		Integer k = 1;
		Integer CapacidadProd = null;
		for (Items i : orderList) {
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
		CapacidadProd = SendPathFile(orderList);
		System.out.println("capacidad de montaje: " + CapacidadProd);
		return CapacidadProd;
	}

	// Obtiene el directorio y la ubicacion del archivo excel
	public Integer SendPathFile(ArrayList<Items> orderList) throws IOException,
			InvalidFormatException {
		// Nombre estatico
		String fileName = "contentExcel/ExcelMacro/caso1Real.xlsm";
		String pathFile = "";
		String pathLocationFile = "";
		String dirNewLocationFile = "";
		Integer cpVal = null;
		ClassLoader classLoader = this.getClass().getClassLoader();
		File configFile = new File(classLoader.getResource(fileName).getFile());
		if (configFile.exists()) {
			pathFile = configFile.getPath();
			pathLocationFile = configFile.getParent();
		}
		System.out.println("GetParent: " + pathLocationFile);
		System.out.println("GetPath:   " + pathFile);
		dirNewLocationFile = WritingExcelXLSM(pathFile, pathLocationFile,
				orderList);
		File pathNewFile = new File(dirNewLocationFile);
		if (ExecuteMacro(pathNewFile) == true) {
			cpVal = ReadingExcelXLSM(dirNewLocationFile);
		}
		return cpVal;
	}

	// Escribe en excel la Orden de produccion
	public String WritingExcelXLSM(String pathFile, String pathLocationFile,
			ArrayList<Items> orderList) throws InvalidFormatException,
			IOException {

		Workbook workbook;
		workbook = new XSSFWorkbook(OPCPackage.open(pathFile));
		// Obtener el Sheet(Hoja) en la que hay que insertar la orden
		Sheet sheet = workbook.getSheetAt(0);

		// Ingresa la orden de produccion en excel (4 primeras columnas)
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

		// Ingresa los modelos en la columna valores unicos
		ArrayList<String> pp = new ArrayList<String>();
		pp = ModelosUnicos(orderList);
		Integer p = 1;
		for (String key : pp) {
			Row r = sheet.getRow(p);
			if (r == null) {
				r = sheet.createRow(p);
			}

			Cell c = r.getCell(4);
			if (c == null) {
				c = r.createCell(4, Cell.CELL_TYPE_STRING);
			}

			c.setCellValue(key);
			p++;
		}
		// Ingreso de CAPACIDAD DE PRODUCCION
		ArrayList<Integer> pp2 = new ArrayList<Integer>();
		pp2.add(480);
		pp2.add(650);
		pp2.add(295);
		Integer m = 1;
		for (Integer key : pp2) {
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

		// Guarda el archivo con otro nombre en la misma direccion
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

	// Retorna el valor resultante del solver
	public Integer ReadingExcelXLSM(String path) throws IOException {
		Double cpDouble = null;
		Integer cpInt = null;
		FileInputStream fis = new FileInputStream(path);
		Workbook wb = new XSSFWorkbook(fis);
		Sheet sheet = wb.getSheetAt(0);

		FormulaEvaluator evaluator = wb.getCreationHelper()
				.createFormulaEvaluator();

		CellReference cellReference = new CellReference("N2");
		Row row = sheet.getRow(cellReference.getRow());
		if (row != null) {
			Cell c = row.getCell(cellReference.getCol());
			switch (evaluator.evaluateFormulaCell(c)) {
			case Cell.CELL_TYPE_NUMERIC:
				cpDouble = c.getNumericCellValue();
				cpInt = (int) Math.round(cpDouble);
				break;

			// CELL_TYPE_FORMULA will never occur
			case Cell.CELL_TYPE_FORMULA:
				break;
			}
		}
		System.out.println("Valor para ocupar en double: " + cpDouble);
		return cpInt;
	}

	// **SIN UTILZAR** Pone en blanco las celdas de una hoja en excel
	public boolean deleteContentSheet(String pathFile, String pathLocationFile)
			throws IOException, InvalidFormatException {

		Workbook workbook;
		workbook = new XSSFWorkbook(OPCPackage.open(pathFile));
		boolean isRowEmpty = false;
		Sheet sheet = workbook.getSheetAt(0);

		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			if (sheet.getRow(i) == null) {
				isRowEmpty = true;
				sheet.shiftRows(i + 1, sheet.getLastRowNum(), -1);
				i--;
				continue;
			}
			for (int j = 0; j < sheet.getRow(i).getLastCellNum(); j++) {
				if (sheet.getRow(i).getCell(j).toString().trim().equals("")) {
					isRowEmpty = false;
				} else {
					isRowEmpty = true;
					break;
				}
			}
			if (isRowEmpty == true) {
				sheet.shiftRows(i + 1, sheet.getLastRowNum(), -1);
				i--;
			}
		}
		try {
			FileOutputStream out = new FileOutputStream(new File(
					pathLocationFile + "\\caso2Real.xlsm"));
			workbook.write(out);
			out.close();
			System.out.println("Contenido del sheet eliminado ... ");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	// RETORNA VALORES SIN REPETIR
	public ArrayList<String> ModelosUnicos(ArrayList<Items> orderListModUnique) {
		ArrayList<String> arrMod = new ArrayList<String>();
		ArrayList<Integer> arrCanti = new ArrayList<Integer>();
		Set<String> hs = new HashSet<>();

		for (Items i : orderListModUnique) {
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
		// variable con el nombre de la macro
		String macroName = "!Subtotales";
		PrintSolveMB execute = new PrintSolveMB();
		if (execute.executeMacro(pathFile, macroName) == true) {
			System.out.println("MACRO SUCCESFULL");
			return true;
		} else {
			return false;
		}
	}
}
