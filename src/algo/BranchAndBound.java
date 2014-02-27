package algo;

import java.util.ArrayList;

import core.Base;
import core.Node;
import core.Universe;

public class BranchAndBound {
	
	protected Universe multiverse;
	
	public BranchAndBound(Universe theVerse) {
		this.multiverse = theVerse;
	}
	
	public void removeUselessBases(){
		ArrayList<Base> bases = multiverse.getListBases();
		String [] enterprises = multiverse.getEnterpriseScenario();
		ArrayList<Base> toKeep = new ArrayList<Base>();	
		
		for (Base base : bases){
			for (String ent : enterprises){
				if (base.contains(ent)){
					toKeep.add(base);
					break;
				}
			}			
		}
		multiverse.setListBases(toKeep);
	}

	public Node thisIsAMotherFuckinBranchAndBoundAlgorithm(Node currentNode){
		int bestCost = -1;
		Node bestNode = null;
		System.out.println("New BnB call");
		for (Base base : multiverse.getListBases()) {
			if (currentNode.getHistory().contains(base)) {
				continue;
			}
			
			System.out.println("\tCurrent base:" + base.getName());
			System.out.println("\tCurrent found list:");
			for (String found : currentNode.getAlreadyFound()) {
				System.out.println("\t\t" + found);
			}
			ArrayList<Base> history = new ArrayList<Base>(currentNode.getHistory());
			history.add(base);
			ArrayList<String> alreadyFound = new ArrayList<String>(currentNode.getAlreadyFound());
			ArrayList<Node> sons = new ArrayList<Node>();
			
			int cost = base.getCost() + currentNode.getCost();
			updateAlreadyFound(alreadyFound, base, multiverse.getEnterpriseScenario());
			
			System.out.println("\tto find");
			for (String ent : multiverse.getEnterpriseScenario()){
				System.out.println("\t\t "+ent);
			}
			
			System.out.println("\talreadyfound");
			for (String found : alreadyFound){
				System.out.println("\t\t " + found);
			}
			
			Node node = new Node(cost, history, alreadyFound, currentNode, sons);
			currentNode.addSon(node);
			
			if (areWeDone(alreadyFound)) {
				System.out.println("We are done");
				if(bestCost < 0 || cost < bestCost){
					bestCost = cost;
					bestNode = node;
					System.out.println("Saving new best cost: " + bestCost);
				}
				break;
			}
			
			if ((bestCost > 0 && cost < bestCost) || (bestCost < 0)){
				bestNode = thisIsAMotherFuckinBranchAndBoundAlgorithm(node);
			}
		}
		return bestNode;
	}

	public static void updateAlreadyFound(ArrayList<String> alreadyFound, Base base,
			String[] enterpriseScenario) {
		for (String enterprise: enterpriseScenario) {
			//System.out.println(" update method, current : " + enterprise);
			if (!alreadyFound.contains(enterprise) && base.contains(enterprise)) {
				//System.out.println("added");
				alreadyFound.add(enterprise);
			}
		}
	}

	public boolean areWeDone(ArrayList<String> alreadyFound) {
		return alreadyFound.size() == multiverse.getEnterpriseScenario().length;
	}
	
}
