package utils;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import core.Base;
import core.Universe;

public class TestsParser {
	
	private static String baseFilename = "resources/data/Scenarios\\Liste Bases\\Liste Bases1.txt"; 
	private static String enterpriseFilename = "resources/data/Scenarios\\Liste Entreprises\\Liste Ent1.txt";
	private static String baseBase = "resources/data/Bases\\Base 1.txt";

	@Test
	public void test_parseBase() throws IOException {
		Base base = Parser.parseBase(baseBase);
		assertTrue(base.getCost() == 87);
		assertTrue(base.getEnterprises().length == 29);
		assertEquals("AIRIAL CONSEIL", base.getEnterprises()[0]);
		assertEquals("ALCANET INTERNATIONAL", base.getEnterprises()[1]);
	}

	@Test
	public void test_parse() throws IOException {
		Universe universe = Parser.parse(baseFilename, enterpriseFilename);
		assertTrue(universe.getEnterpriseScenario().length == 10);
		assertEquals("FRANCE TELECOM EXPERTISE ET SERVICE", universe.getEnterpriseScenario()[0]);
		assertTrue(universe.getListBase().size() == 23);
	}
}
