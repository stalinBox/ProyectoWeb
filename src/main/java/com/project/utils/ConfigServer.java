package com.project.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigServer {
	private static String ipServer;

	public static void main(String[] args) throws Exception {

		Properties propiedades = new Properties();
		InputStream entrada = null;

		// GET IP ADDRESS OF THE SERVER
		try {
			File file = new File(ConfigServer.class.getResource(
					"/configuration.properties").getFile());
			System.out.println(file.toString());
			entrada = new FileInputStream(file);

			// CARGAMOS EL ARCHIVO DE PROPIEDADES
			propiedades.load(entrada);

			ipServer = propiedades.getProperty("IpServer");
			// OBTENEMOS LAS PROPIEDADES Y LAS IMPRIMIMOS
			System.out.println(propiedades.getProperty("IpServer"));
			System.out.println(propiedades.getProperty("user"));
			System.out.println(propiedades.getProperty("passwd"));
			System.out.println("Esta es la IP: " + ipServer);

		} catch (IOException e) {
			e.printStackTrace();

		} finally {
			if (entrada != null) {
				try {
					entrada.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}