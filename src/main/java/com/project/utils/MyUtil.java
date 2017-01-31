package com.project.utils;

/**
 * @author Stalin Ramírez Clase para navegacion entre las paginas
 */
public class MyUtil {
	public static String ip = DireccionesIP.baseIP();

	public static String baseurl() {
		// System.out.println("1 url: " + "http://" + ip +
		// ":8080/ProyectoWeb/");
		return "http://" + ip + ":8080/ProyectoWeb/";
	}

	public static String basepathlogin() {
		// System.out.println("2 url: " + "http://" + ip +
		// ":8080/ProyectoWeb/");
		return "http://" + ip + ":8080/ProyectoWeb/";
	}

	public static String basePath() {
		// System.out.println("3 url: " + "http://" + ip +
		// ":8080/ProyectoWeb/");
		return "http://" + ip + ":8080/ProyectoWeb/";
	}

	public static String calzadoPath() {
		// System.out.println("4 url: " + "http://" + ip +
		// ":8080/ProyectoWeb/");
		return "http://" + ip + ":8080/ProyectoWeb/calzado/";
	}

	public static String themePath() {
		return "http://" + ip + ":8080/ProyectoWeb/themes/";
	}

	public static String messages() {
		return "http://" + ip + ":8080/ProyectoWeb/messages/";
	}

	public static String next() {
		return "http://" + ip + ":8080/ProyectoWeb/calzado/";
	}

	public static String rutaExcel() {
		return "http://" + ip + ":8080/ProyectoWeb/TextExcel/";
	}

	public static String andonPath() {
		return "http://" + ip + ":8080/ProyectoWeb/andon/";
	}

	public static String costosIndirestos() {
		return "http://" + ip + ":8080/ProyectoWeb/costos/";
	}

}
