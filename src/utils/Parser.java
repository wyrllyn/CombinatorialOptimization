package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import core.Base;
import core.Universe;

public class Parser {
	
	public static Universe parse(String baseFilename, String enterpriseFilename) throws IOException{
		/////////////////////////
		// Parse the base file //
		/////////////////////////
		BufferedReader buffer = getBufferedReader(baseFilename);
		String line = buffer.readLine();
		int numberOfBases = Integer.parseInt(line);
		ArrayList<Base> listBase = new ArrayList<>(numberOfBases);
		for (int i = 0; i < numberOfBases; i++) {
			line = buffer.readLine();
			Base tempBase = parseBase(getPrefix(baseFilename) + "Bases\\" + line);
			listBase.add(tempBase);
		}
		
		///////////////////////////////
		// Parse the enterprise file //
		///////////////////////////////
		buffer = getBufferedReader(enterpriseFilename);
		line = buffer.readLine();
		int numberOfEnterprises = Integer.parseInt(line);
		String enterpriseScenario[] = new String[numberOfEnterprises];
		for (int i = 0; i < numberOfEnterprises; i++) {
			line = buffer.readLine();
			enterpriseScenario[i] = line;
		}
		
		return new Universe(enterpriseScenario, listBase);
	}

	public static String getPrefix(String baseFilename) {
		return baseFilename.substring(0, baseFilename.lastIndexOf('/') + 1);
	}

	public static BufferedReader getBufferedReader(String baseFilename)
			throws FileNotFoundException {
		File file = new File(baseFilename);
		FileInputStream fist = new FileInputStream(file);
		InputStreamReader isr = new InputStreamReader(fist);
		BufferedReader buffer = new BufferedReader(isr);
		return buffer;
	}

	public static Base parseBase(String fileName) throws IOException {
		BufferedReader buffer = getBufferedReader(fileName);
		String line = buffer.readLine();
		int cost = Integer.parseInt(line);
		line = buffer.readLine();
		int numberOfEnterprises = Integer.parseInt(line);
		String[] enterprises = new String[numberOfEnterprises];
		for (int i = 0; i < numberOfEnterprises; i++) {
			line = buffer.readLine();
			enterprises[i] = line;
		}
		
		return new Base(cost, enterprises);
	}

}
