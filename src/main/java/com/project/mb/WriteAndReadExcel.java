package com.project.mb;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@ManagedBean
@ViewScoped
public class WriteAndReadExcel implements Serializable {
	private static final long serialVersionUID = 1L;

	// METODOS
	public void SendPathFile() throws IOException {
		String fileName = "contentExcel/caso1.xlsx";
		String pathFile = GetResourcePathFileExcel(fileName);
		System.out
				.println("Metodo SendFile del metodo GetResourcePathFileExcel: "
						+ pathFile);
		WritingExcel(pathFile);
	}

	public String GetResourcePathFileExcel(String fileName) {
		String pathFile = "";
		try {
			ClassLoader classLoader = this.getClass().getClassLoader();
			File configFile = new File(classLoader.getResource(fileName)
					.getFile());
			if (configFile.exists()) {
				System.out.println("Si Existe");
				pathFile = configFile.getPath().toString();
			} else {
				System.out.println("No Existe");
			}

		} catch (Exception e) {
			System.out.println("Error Archivo no encontrado: " + e.toString());
		}
		return pathFile;
	}

	public void WritingExcel(String path) {
		// Blank workbook
		XSSFWorkbook workbook = new XSSFWorkbook();

		// Create a blank sheet
		XSSFSheet sheet = workbook.createSheet("Hoja1");

		// This data needs to be written (Object[])
		Map<String, Object[]> data = new TreeMap<String, Object[]>();
		data.put("1", new Object[] { "Identificador", "NOMBRE", "APELLIDO" });
		data.put("2", new Object[] { 1, "Kim", "Dotcom" });
		data.put("3", new Object[] { 2, "carlitos", "Vargar" });
		data.put("4", new Object[] { 3, "pepe", "cevallos" });
		data.put("5", new Object[] { 4, "Lorenzo", "Lamas" });

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
		try {
			// Write the workbook in file system
			FileOutputStream out = new FileOutputStream(new File(path));
			workbook.write(out);
			out.close();

			System.out.println("casi1.xlsx written successfully on disk.");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void ReadingExcel() {
		// try {
		// FileInputStream file = new FileInputStream(
		// new File(
		// "C:\\Documents and Settings\\admin\\Desktop\\imp data\\howtodoinjava_demo.xlsx"));
		//
		// // Create Workbook instance holding reference to .xlsx file
		// HSSFWorkbook workbook = new HSSFWorkbook(file);
		//
		// // Get first/desired sheet from the workbook
		// HSSFSheet sheet = workbook.getSheetAt(0);
		//
		// // Iterate through each rows one by one
		// Iterator<Row> rowIterator = sheet.iterator();
		// while (rowIterator.hasNext()) {
		// Row row = rowIterator.next();
		// // For each row, iterate through all the columns
		// Iterator<Cell> cellIterator = row.cellIterator();
		//
		// while (cellIterator.hasNext()) {
		// Cell cell = cellIterator.next();
		// // Check the cell type and format accordingly
		// switch (cell.getCellType()) {
		// case Cell.CELL_TYPE_NUMERIC:
		// System.out.print(cell.getNumericCellValue() + "\t");
		// break;
		// case Cell.CELL_TYPE_STRING:
		// System.out.print(cell.getStringCellValue() + "\t");
		// break;
		// }
		// }
		// System.out.println("");
		// }
		// file.close();
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
	}

}
