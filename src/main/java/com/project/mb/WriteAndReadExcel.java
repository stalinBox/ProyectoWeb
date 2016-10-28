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
	// OBTIENE LA ORDEN DE PRODUCCION DE BEAN Y MAPEA HACIA UN MAP
	public ArrayList<Integer> getOrder(ArrayList<Items> orderList)
			throws InvalidFormatException, IOException {
		ArrayList<Integer> cpStands = new ArrayList<Integer>();
		Integer k = 1;
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
		cpStands = SendPathFile(orderList);
		System.out.println("capacidad de montaje,aparado,troquelado: "
				+ cpStands);
		return cpStands;
	}

	// OBTIENE EL DIRECTORIO Y LA UBICACION DEL ARCHIVO EXCEL
	public ArrayList<Integer> SendPathFile(ArrayList<Items> orderList)
			throws IOException, InvalidFormatException {
		ArrayList<Integer> cpStands = new ArrayList<Integer>();
		// NOMBRE ESTATICO
		String fileName = "contentExcel/ExcelMacro/caso1Real.xlsm";
		String pathFile = "";
		String pathLocationFile = "";
		String dirNewLocationFile = "";
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
				orderList);
		File pathNewFile = new File(dirNewLocationFile);
		if (ExecuteMacro(pathNewFile) == true) {
			cpStands = ReadingExcelXLSM(dirNewLocationFile);
		}
		return cpStands;
	}

	// ESCRIBE EN EXCEL LA ORDEN DE PRODUCCION
	public String WritingExcelXLSM(String pathFile, String pathLocationFile,
			ArrayList<Items> orderList) throws InvalidFormatException,
			IOException {

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

		// INGRESA LOS MODELOS EN LA COLUMNA VALORES UNICOS
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
		// INGRESAR DE CAPACIDAD DE PRODUCCION MONTAJE
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

		// INGRESAR LA CAPACIDAD DE PRODUCCION APARADO
		ArrayList<Integer> pp3 = new ArrayList<Integer>();
		Sheet sheetApa = workbook.getSheetAt(1);
		pp3.add(460);
		pp3.add(510);
		pp3.add(333);
		Integer m1 = 1;
		for (Integer key : pp3) {
			Row r = sheetApa.getRow(m1);
			if (r == null) {
				r = sheetApa.createRow(m1);
			}

			Cell c = r.getCell(0);
			if (c == null) {
				c = r.createCell(0, Cell.CELL_TYPE_NUMERIC);
			}

			c.setCellValue(key);
			m1++;
		}
		// INGRESAR LA CAPACIDAD DE PRODUCCION DE TROQUELADO
		ArrayList<Integer> pp4 = new ArrayList<Integer>();
		Sheet sheetTrq = workbook.getSheetAt(2);
		pp4.add(334);
		pp4.add(693);
		pp4.add(265);
		Integer m2 = 1;
		for (Integer key : pp4) {
			Row r = sheetTrq.getRow(m2);
			if (r == null) {
				r = sheetTrq.createRow(m2);
			}

			Cell c = r.getCell(0);
			if (c == null) {
				c = r.createCell(0, Cell.CELL_TYPE_NUMERIC);
			}
			c.setCellValue(key);
			m2++;
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
	public ArrayList<Integer> ReadingExcelXLSM(String path) throws IOException {
		ArrayList<Integer> standaresProcesos = new ArrayList<Integer>();
		// PARA MONTAJE
		Double cpDoubleMontaje = null;
		Integer cpInt = null;
		// PARA APARADO
		Double cpDoubleApa = null;
		Integer cpIntApa = null;
		// PARA TROQUELADO
		Double cpDoubleTrq = null;
		Integer cpIntTrq = null;

		FileInputStream fis = new FileInputStream(path);
		Workbook wb = new XSSFWorkbook(fis);
		Sheet sheet = wb.getSheetAt(0);
		Sheet sheetApa = wb.getSheetAt(1);
		Sheet sheetTrq = wb.getSheetAt(2);

		FormulaEvaluator evaluator = wb.getCreationHelper()
				.createFormulaEvaluator();

		CellReference cellReference = new CellReference("N2");
		CellReference cellReference1 = new CellReference("G2");

		Row row = sheet.getRow(cellReference.getRow());
		Row rowApa = sheetApa.getRow(cellReference1.getRow());
		Row rowTrq = sheetTrq.getRow(cellReference1.getRow());

		if (row != null && rowApa != null && rowTrq != null) {
			Cell c = row.getCell(cellReference.getCol());
			Cell c1 = rowApa.getCell(cellReference1.getCol());
			Cell c2 = rowTrq.getCell(cellReference1.getCol());
			// VALOR MONTAJE
			switch (evaluator.evaluateFormulaCell(c)) {
			case Cell.CELL_TYPE_NUMERIC:
				cpDoubleMontaje = c.getNumericCellValue();
				cpInt = (int) Math.round(cpDoubleMontaje);
				break;
			case Cell.CELL_TYPE_FORMULA:
				break;
			}
			// VALOR APARADO
			switch (evaluator.evaluateFormulaCell(c1)) {
			case Cell.CELL_TYPE_NUMERIC:
				cpDoubleApa = c1.getNumericCellValue();
				cpIntApa = (int) Math.round(cpDoubleApa);
				break;
			case Cell.CELL_TYPE_FORMULA:
				break;
			}
			// VALOR TROQUELADO
			switch (evaluator.evaluateFormulaCell(c2)) {
			case Cell.CELL_TYPE_NUMERIC:
				cpDoubleTrq = c2.getNumericCellValue();
				cpIntTrq = (int) Math.round(cpDoubleTrq);
				break;
			case Cell.CELL_TYPE_FORMULA:
				break;
			}
		}
		standaresProcesos.add(cpInt);
		standaresProcesos.add(cpIntApa);
		standaresProcesos.add(cpIntTrq);

		System.out.println("Valor para ocupar en double MONTAJE: "
				+ cpDoubleMontaje);
		System.out.println("Valor para ocupar en double APARADO: "
				+ cpDoubleApa);
		System.out.println("Valor para ocupar en double TROQUELADO: "
				+ cpDoubleTrq);
		return standaresProcesos;
	}

	// **SIN UTILZAR** PONE EN BLANCO LAS CELDAS DE UNA HOJA EN EXCEL
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
		// VARIABLE CON EL NOMBRE DE LA MACRO
		String macroNameMontaje = "!calculoMontaje";
		String macroNameAparado = "!calculoAparado";
		String macroNameTroquelado = "!calculoTroquelado";
		PrintSolveMB execute = new PrintSolveMB();
		try {
			if (execute.executeMacro(pathFile, macroNameMontaje) == true
					&& execute.executeMacro(pathFile, macroNameAparado) == true
					&& execute.executeMacro(pathFile, macroNameTroquelado) == true) {
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
}
