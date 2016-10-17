package com.project.utils;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Stalin Ramírez Clase para navegacion entre las paginas
 */
public class MyUtil {
	private String ipAddress;

	@PostConstruct
	public void init() {
		HttpServletRequest request = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		String ipAddress = request.getHeader("X-FORWARDED-FOR");
		if (ipAddress == null) {
			ipAddress = request.getRemoteAddr();
		}
		System.out.println("AQUI **************** ipAddress: " + ipAddress);
	}

	public static String baseurl() {
		return "http://localhost:8080/ProyectoWeb/";
	}

	public static String basepathlogin() {
		return "http://localhost:8080/ProyectoWeb/";
	}

	public static String basePath() {
		return "http://localhost:8080/ProyectoWeb/";
	}

	public static String calzadoPath() {
		return "http://localhost:8080/ProyectoWeb/calzado/";
	}

	public static String themePath() {
		return "http://localhost:8080/ProyectoWeb/themes/";
	}

	public static String messages() {
		return "http://localhost:8080/ProyectoWeb/messages/";
	}

	public static String next() {
		return "http://localhost:8080/ProyectoWeb/calzado/";
	}

	public static String rutaExcel() {
		return "http://localhost:8080/ProyectoWeb/TextExcel/";
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

}
