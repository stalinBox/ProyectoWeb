package com.project.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigServer {
	private static String ipServer;

	public static void main(String[] args) {
		Properties propiedades = new Properties();
		InputStream entrada = null;

		try {
			File file = new File(ConfigServer.class.getResource(
					"/configuration.properties").getFile());
			System.out.println(file.toString());
			entrada = new FileInputStream(file);

			// cargamos el archivo de propiedades
			propiedades.load(entrada);

			ipServer = propiedades.getProperty("IpServer");
			// obtenemos las propiedades y las imprimimos
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

	// SETTERS AND GETTERS
	public String getIpServer() {
		return ipServer;
	}

	public void setIpServer(String ipServer) {
		this.ipServer = ipServer;
	}

}