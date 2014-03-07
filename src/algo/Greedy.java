package algo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import core.Base;
import core.Node;
import core.Universe;

/**
 * Greedy algorithm implementation.
 * @author Sara Tari & Adrien Droguet
 *
 */
public class Greedy extends Algo {

	/**
	 * Constructs a Greedy object and sort the specified Universe's list of Base in ascending order.
	 * @param theVerse
	 */
	public Greedy(Universe theVerse) {
		super(theVerse);
		Collections.sort(theVerse.getListBases());
	}
	
	/**
	 * Runs the Greedy algorithm recursively.
	 * @param currentNode
	 * @return Final node.
	 */
	public Node recursiveGreedyAlgorithm(Node currentNode){
		System.out.println("New Greedy call");
		
		Base base = pickABase2(currentNode);		
		System.out.println("name " + base.getName());
		
		ArrayList<Base> history = new ArrayList<Base>(currentNode.getHistory());
		history.add(base);
		ArrayList<String> alreadyFound = new ArrayList<String>(currentNode.getAlreadyFound());
		ArrayList<Node> sons = new ArrayList<Node>();
		
		int cost = base.getCost() + currentNode.getCost();
		updateAlreadyFound(alreadyFound, base, multiverse.getEnterpriseScenario());

		Node node = new Node(cost, history, alreadyFound, currentNode, sons);
		currentNode.addSon(node);
		
		if (areWeDone(alreadyFound)){
			return node;
		} else {
			return recursiveGreedyAlgorithm(node);				
		}
	
	}
	
	public Node setFirstNodeWithRandom(Node currentNode){
		Base base = randomBase(currentNode);
		System.out.println("base : " + base.getName() + " with cost : " + base.getCost());
		
		ArrayList<Base> history = new ArrayList<Base>(currentNode.getHistory());
		history.add(base);
		ArrayList<String> alreadyFound = new ArrayList<String>(currentNode.getAlreadyFound());
		ArrayList<Node> sons = new ArrayList<Node>();
		
		int cost = base.getCost() + currentNode.getCost();
		updateAlreadyFound(alreadyFound, base, multiverse.getEnterpriseScenario());

		Node node = new Node(cost, history, alreadyFound, currentNode, sons);
		currentNode.addSon(node);
		
		return node;
	}
	
	/**
	 * Picks a randomly chosen base.
	 * @param node
	 * @return Unused base.
	 */
	public Base randomBase(Node node){
		List<Base> toPickFrom = new ArrayList<Base>();
		for (Base base : multiverse.getListBases()) {
			if (node.getHistory().contains(base)) {
				continue;
			} else {
				toPickFrom.add(base);
			}
			if (toPickFrom.size() >= multiverse.getListBases().size()) {
				break;
			}
		}
		
		int rand = (int) (Math.random() * 100 % toPickFrom.size());
		
		return toPickFrom.get(rand);
	}
	
	/**
	 * Picks a base.
	 * @param node
	 * @return Unused base.
	 */
	public Base pickABase(Node node){
		for (Base base : multiverse.getListBases()){
			if (node.getHistory().contains(base)) {
				continue;
			} else {
				return base;
			}
		}
		
		return null;
	}

	/**
	 * Also takes into account the number of enterprises found in a base.
	 * @param node
	 * @return Unused base.
	 */
	public Base pickABase2(Node node){
		int bestTotal = -1;
		Base bestBase = null;
		
		for (Base base : multiverse.getListBases()) {
			if (node.getHistory().contains(base)){
				continue;
			}
			int total = 0;
			for (String enterprise: multiverse.getEnterpriseScenario()){
				if (node.getAlreadyFound().contains(enterprise)){
					continue;
				}
				if (multiverse.enterpriseScenarioContains(enterprise)){
					total++;
				}
			}
			if (bestTotal < total) {
				bestTotal = total;
				bestBase = base;
			}
		}
		
		return bestBase;
	}
	
	@Override
	public void startAlgo(int n) {
		super.startAlgo(n);
		
		List<Integer> costs = new ArrayList<Integer>(n);
		for (int i = 0; i < n; i++) {
			Node currentNode = new Node();
			Node result = new Node();
			result = recursiveGreedyAlgorithm(setFirstNodeWithRandom(currentNode));
			System.out.println("Final cost : " +result.getCost());
			costs.add(result.getCost());
		}
		Collections.sort(costs);
		System.out.println("\nBest cost = " + costs.get(0));
	}

}
