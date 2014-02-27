package algo;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import utils.Parser;
import utils.TestsParser;

import core.Base;
import core.Node;
import core.Universe;

public class TestsBranchAndBound {
	
	public static String testBaseFilename = "resources/data/Scenarios\\Liste Bases\\Liste Bases Test.txt"; 

	private Universe verse;
	private Base base;
	private Base base2;
	private Base uselessBase;
	private String[] enterprises_target;
	private String[] enterprises_inBase;
	private String[] enterprises_inBase2;
	private String[] enterprises_uselessBase;
	private int cost;
	private ArrayList<String> found;
	private ArrayList<String> allFound;
	
	private BranchAndBound bnb;
	private ArrayList<Base> baseList;
	
	@Before
	public void setup() {
		cost = 80;
		enterprises_inBase = new String[] {
				"plop",
				"blob",
				"zorg",
				"bukkake",
				"hjvo"
		};
		base = new Base(cost, enterprises_inBase, "test base");
		
		enterprises_inBase2 = new String[] {
			"hulk",
			"fnu"
		};
		base2 = new Base(cost, enterprises_inBase2, "test base 2");
		
		enterprises_uselessBase = new String[] {
				"pony",
				"unicorn"
		};
		uselessBase = new Base(cost, enterprises_uselessBase, "useless base");
		
		enterprises_target = new String[] {
				"bukkake",
				"hjvo",
				"hulk",
				"fnu"
		};
		
		found = new ArrayList<String>();
		found.add("bukkake");
		
		allFound = new ArrayList<String>();
		allFound.add("bukkake");
		allFound.add("hjvo");
		allFound.add("hulk");
		allFound.add("fnu");
		
		baseList = new ArrayList<Base>();
		baseList.add(base);
		baseList.add(base2);
		baseList.add(uselessBase);
		
		verse = new Universe(enterprises_target, baseList);
		bnb = new BranchAndBound(verse);
	}
	
	@Test
	public void test_removeUselessBases() {
		bnb.removeUselessBases();
		ArrayList<Base> bnbBaseList = verse.getListBases();
		assertFalse(bnbBaseList.contains(uselessBase));
		assertTrue(bnbBaseList.contains(base));
		assertTrue(bnbBaseList.contains(base2));
	}
	
	@Test
	public void test_updateAlreadyFound() {
		assertFalse(found.contains("hjvo"));
		BranchAndBound.updateAlreadyFound(found, base, enterprises_target);
		assertTrue(found.contains("hjvo"));
		assertFalse(found.contains("blob"));
		assertFalse(found.contains("plop"));
		assertFalse(found.contains("zorg"));
		assertTrue(found.contains("bukkake"));
	}
	
	@Test
	public void test_areWeDone_KO() {
		assertFalse(bnb.areWeDone(found));
	}
	
	@Test
	public void test_areWeDone_OK() {
		assertTrue(bnb.areWeDone(allFound));
	}
	
	@Test
	public void test_big() {
		bnb.removeUselessBases();
		Node root = new Node();
		Node result = bnb.thisIsAMotherFuckinBranchAndBoundAlgorithm(root, -1, null);
		assertNotNull(result);
		assertTrue(result.getCost() == cost * 2);
		assertTrue(result.getHistory().contains(base));
		assertTrue(result.getHistory().contains(base2));
	}
	
	
	
	@Test
	public void test_bigger() throws IOException {
		verse = Parser.parse(testBaseFilename, TestsParser.enterpriseFilename);
		bnb = new BranchAndBound(verse);
		bnb.removeUselessBases();
		Node root = new Node();
		Node result = bnb.thisIsAMotherFuckinBranchAndBoundAlgorithm(root, -1, null);
		assertNotNull(result);
	}
	
	@Test
	public void test_even_bigger() throws IOException {
		verse = Parser.parse(TestsParser.baseFilename, TestsParser.enterpriseFilename);
		bnb = new BranchAndBound(verse);
		bnb.removeUselessBases();
		Node root = new Node();
		Node result = bnb.thisIsAMotherFuckinBranchAndBoundAlgorithm(root, -1, null);
		assertNotNull(result);
		System.out.println("final cost:" + result.getCost());
	}
}
