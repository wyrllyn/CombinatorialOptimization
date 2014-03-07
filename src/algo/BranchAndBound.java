package algo;

import java.util.ArrayList;
import java.util.Date;

import core.Base;
import core.Node;
import core.Universe;

/**
 * Branch and bound algorithm implementation.
 * @author Sara Tari & Adrien Droguet
 *
 */
public class BranchAndBound extends Algo {
	
	/**
	 * Constructs a branch and bound object.
	 * @param theVerse
	 */
	public BranchAndBound(Universe theVerse) {
		super(theVerse);
	}

	/**
	 * Runs the Branch and Bound algorithm recursively.
	 * @param currentNode
	 * @param bestCost
	 * @param bestNode
	 * @return Final Node.
	 */
	public Node recursiveBranchAndBoundAlgorithm(Node currentNode, int bestCost, Node bestNode){
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
				bestNode = recursiveBranchAndBoundAlgorithm(node, bestCost, bestNode);
				bestCost = bestNode.getCost();
			}
		}
		return bestNode;
	}

	@Override
	public void startAlgo(int n) {
		super.startAlgo(n);
		Date startDate = new Date();
		Node root = new Node();
		Node result = recursiveBranchAndBoundAlgorithm(root, -1, null);
		Date endDate = new Date();
		long time = endDate.getTime() - startDate.getTime();
		
		System.out.println("Final cost = " + result.getCost()
				+ "\tNumber of nodes = " + root.getTreeSize()
				+ "\tRunning time = " + (time / 1000) + " s");
	}


	
}
