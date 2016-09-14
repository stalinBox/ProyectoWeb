package com.project.mb;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

@ManagedBean
@ViewScoped
public class PrintSolveMB implements Serializable {
	private static final long serialVersionUID = 1L;

	//
	// public void excecuteFunctionMacro() throws FileNotFoundException {
	// String file = "D:\\caso1.xlsm";
	// openFile(file);
	// }
	//
	// public boolean openFile(String file) {
	// try {
	// final String macroName = "!macro001";
	//
	// File fichero = new File(file);
	//
	// if (fichero.exists()) {
	// System.out.println("Si existe el archivo");
	// executeMacro(fichero, macroName);
	// } else {
	// System.out.println("No se encuentra el archivo");
	// }
	//
	// return true;
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// return true;
	// }

	@SuppressWarnings("finally")
	public boolean executeMacro(File file, String macroName) {
		ComThread.InitSTA();

		final ActiveXComponent excel = new ActiveXComponent("Excel.Application");

		try {

			final Dispatch workbooks = excel.getProperty("Workbooks")
					.toDispatch();
			final Dispatch workBook = Dispatch.call(workbooks, "Open",
					file.getAbsolutePath()).toDispatch();

			final Variant result = Dispatch.call(excel, "Run",
					new Variant(file.getName() + macroName));

			Dispatch.call(workBook, "Save");

			com.jacob.com.Variant f = new com.jacob.com.Variant(true);
			Dispatch.call(workBook, "Close", f);
			System.out.println("Macro ejecutada..");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			excel.invoke("Quit", new Variant[0]);
			ComThread.Release();
			return true;
		}
	}
}
