package algo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import core.Base;
import core.Node;
import core.Universe;

public class BranchAndBound extends Algo{
	
	public BranchAndBound(Universe theVerse) {
		super(theVerse);
	}

	public Node thisIsAMotherFuckinBranchAndBoundAlgorithm(Node currentNode, int bestCost, Node bestNode){
		System.out.println("New BnB call");
		for (Base base : multiverse.getListBases()) {
			if (currentNode.getHistory().contains(base)) {
				continue;
			}

			ArrayList<Base> history = new ArrayList<Base>(currentNode.getHistory());
			history.add(base);
			ArrayList<String> alreadyFound = new ArrayList<String>(currentNode.getAlreadyFound());
			ArrayList<Node> sons = new ArrayList<Node>();
			
			int cost = base.getCost() + currentNode.getCost();
			updateAlreadyFound(alreadyFound, base, multiverse.getEnterpriseScenario());

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
				bestNode = thisIsAMotherFuckinBranchAndBoundAlgorithm(node, bestCost, bestNode);
				bestCost = bestNode.getCost();
			}
		}
		return bestNode;
	}

	@Override
	public void startAlgo() {
		Date startDate = new Date();
		removeUselessBases();
		Node root = new Node();
		Node result = thisIsAMotherFuckinBranchAndBoundAlgorithm(root, -1, null);
		Date endDate = new Date();
		//TODO: exec time
		System.out.println("Final cost = " + result.getCost()
				+ "\tNumber of nodes = " + root.getTreeSize()
				+ "\tRunning time = ");
	}
	
}
