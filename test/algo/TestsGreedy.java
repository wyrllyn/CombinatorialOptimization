package algo;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import utils.Parser;
import utils.TestsParser;

import core.Base;
import core.Node;
import core.Universe;

public class TestsGreedy {

	// Copy pasted from TestsBranchAndBound (mostly)
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
	
	private Greedy greedo;
	private ArrayList<Base> baseList;
	
	private ArrayList<Base> history;
	private Base base_a;
	private Base base_b;
	private Base base_c;
	private Base base_d;
	
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
		base_a = new Base(60, enterprises_inBase, "test base a");
		base_b = new Base(90, enterprises_inBase, "test base b");
		base_c = new Base(70, enterprises_inBase, "test base c");
		base_d = new Base(50, enterprises_inBase, "test base d");
		
		enterprises_inBase2 = new String[] {
			"hulk",
			"fnu"
		};
		base2 = new Base(40, enterprises_inBase2, "test base 2");
		
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
		baseList.add(base_a);
		baseList.add(base_b);
		baseList.add(base_c);
		baseList.add(base_d);
		
		verse = new Universe(enterprises_target, baseList);
		greedo = new Greedy(verse);
		
		history = new ArrayList<Base>();
		history.add(base);
	}
	// End of copy-pasta
	
	@Test
	public void test_pickABase() {
		Node node = new Node();
		Base result = greedo.pickABase(node);
		assertNotNull(result);
		assertTrue(result.getCost() == 40);
	}
	
	@Test
	public void test_randomBase() {
		Node node = new Node(0, history, found, null, new ArrayList<Node>());
		for (int i = 0; i < 30; i++) {
			Base result = greedo.randomBase(node);
			assertNotNull(result);
			assertFalse(history.contains(result));
		}
	}
	
	@Test
	public void test_setFirstNode() {
		Node node = new Node();
		Node result = greedo.setFirstNodeWithRandom(node);
		assertNotNull(result);
		assertFalse(result.getCost() == 0);
	}
	
	@Test
	public void test_pickABase2(){
		Node node = new Node();
		Base result = greedo.pickABase2(node);
		assertNotNull(result);
	}
	
	@Test
	public void test_startGreedy() throws IOException {
		verse = Parser.parse("resources/data/Scenarios\\Liste Bases\\Liste Bases1.txt", TestsParser.enterpriseFilename);
		greedo = new Greedy(verse);
		
		List<Node> results = new ArrayList<Node>();
		List<Integer> costs = new ArrayList<Integer>();
		for (int i = 0; i < 30; i++) {
			// code from startAlgo()
			greedo.removeUselessBases();
			
			Node currentNode = new Node();
			Node result = new Node();
			result = greedo.recursiveGreedyAlgorithm(greedo.setFirstNodeWithRandom(currentNode));
			results.add(result);
			costs.add(result.getCost());
		}
		for (Node node : results) {
			System.out.println(node.getCost() + "\t" + node.getHistory());
		}
		Collections.sort(costs);
		
		System.out.println("best cost is " + costs.get(0));
	}
}
