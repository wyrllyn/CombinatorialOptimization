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

/**
 * Parser class. Self explanatory.
 * @author Sara Tari & Adrien Droguet
 *
 */
public class Parser {
	
	/**
	 * Parses various files in order to create a Universe object.
	 * @param baseFilename File containing a list of base files.
	 * @param enterpriseFilename File containing a list of enterprises.
	 * @return Universe object.
	 * @throws IOException
	 */
	public static Universe parse(String baseFilename, String enterpriseFilename) throws IOException {
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

	public static String getPrefix(String filename) {
		return filename.substring(0, filename.lastIndexOf('/') + 1);
	}

	/**
	 * 
	 * @param filename
	 * @return Opened BufferedReader for the specified file.
	 * @throws FileNotFoundException
	 */
	public static BufferedReader getBufferedReader(String filename)
			throws FileNotFoundException {
		File file = new File(filename);
		FileInputStream fist = new FileInputStream(file);
		InputStreamReader isr = new InputStreamReader(fist);
		BufferedReader buffer = new BufferedReader(isr);
		return buffer;
	}

	/**
	 * Parses a base file.
	 * @param fileName
	 * @return Base object.
	 * @throws IOException
	 */
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
		
		return new Base(cost, enterprises, fileName);
	}

}
