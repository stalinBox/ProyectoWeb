package com.project.mb;

import java.io.File;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class WriteAndReadExcel implements Serializable {
	private static final long serialVersionUID = 1L;

	// METODOS
	public void SendPathFile() {
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
