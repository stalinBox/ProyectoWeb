package com.project.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

public class UtilUploadImage {

	public static String saveFileToTemp(byte[] bytes, String nomFile) {
		String pathFile = null;
		ServletContext servletContext = (ServletContext) FacesContext
				.getCurrentInstance().getExternalContext().getContext();

		String path = servletContext.getRealPath("") + File.separator
				+ "resources" + File.separatorChar + "img" + File.separatorChar
				+ "tmp" + File.separatorChar + nomFile;

		File f = null;
		InputStream in = null;

		try {
			f = new File(path);
			in = new ByteArrayInputStream(bytes);
			FileOutputStream out = new FileOutputStream(f.getAbsolutePath());
			int c = 0;
			while ((c = in.read()) >= 0) {
				out.write(c);
			}
			out.flush();
			out.close();
			pathFile = "../resources/img/tmp" + nomFile;
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("No se pudo cargar la imagen");
		}
		return nomFile;
	}
}
