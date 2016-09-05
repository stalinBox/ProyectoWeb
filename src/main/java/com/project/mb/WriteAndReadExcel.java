package com.project.mb;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@ManagedBean
@ViewScoped
public class WriteAndReadExcel implements Serializable {

	private static final long serialVersionUID = 1L;

	// METODOS
	public void SendPathFile() throws IOException, InvalidFormatException {
		String fileName = "contentExcel/ExcelMacro/caso1Real.xlsm";
		String pathFile = "";
		String pathLocationFile = "";
		ClassLoader classLoader = this.getClass().getClassLoader();
		File configFile = new File(classLoader.getResource(fileName).getFile());
		if (configFile.exists()) {
			pathFile = configFile.getPath();
			pathLocationFile = configFile.getParent();
		}
		System.out.println("GetParent: " + pathLocationFile);
		System.out.println("GetPath:   " + pathFile);
		WritingExcelXLSM(pathFile, pathLocationFile);
	}

	public void WritingExcelXLSM(String pathFile, String pathLocationFile)
			throws InvalidFormatException, IOException {
		/**
		 * Abre un archivo cargado en la memoria de JBOSS y lo guarda en la
		 * misma direccion con otro nombre manteniendo la macro
		 **/
		Workbook workbook;
		workbook = new XSSFWorkbook(OPCPackage.open(pathFile));

		// Obtener el Sheet(Hoja) en la que hay que insertar la orden
		Sheet sheet = workbook.getSheetAt(0);

		// Generar los datos para ser escritos en la hoja de excel(Se cargara a
		// traves de la BD)
		Map<String, Object[]> data = new TreeMap<String, Object[]>();
		data.put("1", new Object[] { "Identificador", "NOMBRE", "APELLIDO" });
		data.put("2", new Object[] { 1, "Kim", "Dotcom" });
		data.put("3", new Object[] { 2, "carlitos", "Vargar" });
		data.put("4", new Object[] { 3, "pepe", "cevallos" });
		data.put("5", new Object[] { 4, "Lorenzo", "Lamas" });
		data.put("6", new Object[] { 5, "F", "G" });

		// Iterate over data and write to sheet
		Set<String> keyset = data.keySet();
		int rownum = 0;
		for (String key : keyset) {
			Row row = sheet.createRow(rownum++);
			Object[] objArr = data.get(key);
			int cellnum = 0;
			for (Object obj : objArr) {
				Cell cell = row.createCell(cellnum++);
				if (obj instanceof String)
					cell.setCellValue((String) obj);
				else if (obj instanceof Integer)
					cell.setCellValue((Integer) obj);
			}
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
	}

	public void ReadingExcelXLSM(String path) {
		/* Lee todas las celdas y hojas de un archivo archivo xlsm */
		try {
			// Create a file from the xlsx/xls file
			File f = new File(path);

			// Create Workbook instance holding reference to .xlsx or .xlsmfile
			org.apache.poi.ss.usermodel.Workbook workbook = WorkbookFactory
					.create(f);
			System.out.println("Nombre del libro excel: " + workbook);

			// printing number of sheet avilable in workbook
			int numberOfSheets = workbook.getNumberOfSheets();
			System.out.println("Numero de hojas: " + numberOfSheets);
			org.apache.poi.ss.usermodel.Sheet sheet = null;
			// Get the sheet in the xlsx file
			for (int i = 0; i < numberOfSheets; i++) {

				sheet = workbook.getSheetAt(i);
				System.out.println("\n" + sheet.getSheetName());

				// Iterate through each rows one by one
				Iterator<Row> rowIterator = sheet.iterator();
				while (rowIterator.hasNext()) {
					Row row = rowIterator.next();
					// For each row, iterate through all the columns
					Iterator<Cell> cellIterator = row.cellIterator();

					while (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();
						// Check the cell type and format accordingly
						switch (cell.getCellType()) {
						case Cell.CELL_TYPE_NUMERIC:
							System.out.print((int) cell.getNumericCellValue()
									+ " ");
							break;
						case Cell.CELL_TYPE_STRING:
							System.out.print(cell.getStringCellValue() + " ");
							break;
						case Cell.CELL_TYPE_BLANK:
							System.out.print(cell.getRow()); // getStringCellValue()
																// + " ");
							break;
						}
					}

				}
			}
			System.out.println("\n terminado");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
