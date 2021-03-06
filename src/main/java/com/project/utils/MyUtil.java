package com.project.utils;

/**
 * @author Stalin Ramírez Clase para navegacion entre las paginas
 */
public class MyUtil {
	public static String ip = "localhost";
	public static String ip2 = "172.21.123.24";

	public static String andonMonitor() {
		// return "http://172.21.123.197/PantallaAndon/Prueba.aspx";
		return "http://" + ip2 + "/PantallaAndon/Prueba.aspx";
	}

	public static String andonReportes() {
		// return "http://172.21.123.197/PantallaAndon/Reportes.aspx";
		return "http://" + ip2 + "/PantallaAndon/Reportes.aspx";
	}

	public static String baseurl() {
		return "http://" + ip + ":8080/ProyectoWeb/";
	}

	public static String basepathlogin() {
		return "http://" + ip + ":8080/ProyectoWeb/";
	}

	public static String basePath() {
		return "http://" + ip + ":8080/ProyectoWeb/";
	}

	public static String calzadoPath() {
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
