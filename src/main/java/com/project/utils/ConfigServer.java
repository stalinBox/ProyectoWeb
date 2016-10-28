package com.project.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class ConfigServer {
	private static String ipServer;
	private static final String TASKLIST = "tasklist";
	private static final String KILL = "taskkill /F /IM ";

	public static void main(String[] args) throws Exception {
		Properties propiedades = new Properties();
		InputStream entrada = null;
		String processName = "EXCEL.EXE";

		// KILLING A PROCESS ON WINDOWS
		if (isProcessRunning(processName)) {
			killProcess(processName);
		}

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

	// LIST RUN PROCESS
	private static boolean isProcessRunning(String serviceName)
			throws Exception {
		Process p = Runtime.getRuntime().exec(TASKLIST);
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				p.getInputStream()));
		String line;
		while ((line = reader.readLine()) != null) {
			System.out.println(line);
			if (line.contains(serviceName)) {
				return true;
			}
		}
		return false;
	}

	// KILL A PROCESS
	public static void killProcess(String serviceName) throws Exception {
		Runtime.getRuntime().exec(KILL + serviceName);
	}
}