package algo;

import java.util.ArrayList;

import core.Base;
import core.Node;
import core.Universe;

public class BranchAndBound {
	
	protected Universe multiverse;
	private Node root = new Node();
	
	public BranchAndBound(Universe theVerse) {
		this.multiverse = theVerse;
	}
	
	protected void removeUselessBases(){
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

	public void thisIsAMotherFuckinBranchAndBoundAlgorithm(Node currentNode){
		int bestCost = -1;
		Node bestNode = null;
		for (Base base : multiverse.getListBases()) {
			ArrayList<Base> history = new ArrayList<Base>(currentNode.getHistory());
			history.add(base);
			ArrayList<String> alreadyFound = new ArrayList<String>(currentNode.getAlreadyFound());//TODO update that thing properly
			int cost = base.getCost() + currentNode.getCost();
			ArrayList<Node> sons = new ArrayList<Node>();
			
			Node node = new Node(cost, history, alreadyFound, currentNode, sons);
			currentNode.addSon(node);
			
			
			
			if (areWeDone(alreadyFound)) {
				if(bestCost < 0 || cost < bestCost){
					bestCost = cost;
					bestNode = node;
				}
				break;
			}
		}
	}

	private boolean areWeDone(ArrayList<String> alreadyFound) {
		return alreadyFound.size() == multiverse.getEnterpriseScenario().length;
	}
	
}
