package com.project.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class KillProcessEXCEL {
	private static final String TASKLIST = "tasklist";
	private static final String KILL = "taskkill /F /IM ";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String processName = "EXCEL.EXE";
		// KILLING A PROCESS ON WINDOWS
		try {
			if (isProcessRunning(processName)) {
				killProcess(processName);
			}
		} catch (Exception e) {
			e.printStackTrace();
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
