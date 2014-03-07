package core;

import java.util.ArrayList;
import java.util.List;

/**
 * Our Node object. It has a cost, an history of bases and found enterprises, a father and several sons.
 * @author Sara Tari & Adrien Droguet
 *
 */
public class Node {
	
	private int cost;
	private List<Base> history;
	private List<String> alreadyFound;
	private Node father;
	private List<Node> sons;
	
	/**
	 * Constructs a Node.
	 * @param cost
	 * @param history
	 * @param alreadyFound
	 * @param father
	 * @param sons
	 */
	public Node(int cost, List<Base> history,
			List<String> alreadyFound, Node father, List<Node> sons) {
		super();
		this.cost = cost;
		this.history = history;
		this.alreadyFound = alreadyFound;
		this.father = father;
		this.sons = sons;
	}	
	
	/**
	 * Constructs an empty Node.
	 */
	public Node() {
		this.cost = 0;
		this.history = new ArrayList<>();
		this.alreadyFound = new ArrayList<>();
		this.father = null;
		this.sons = new ArrayList<>();
	}

	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}

	public List<Base> getHistory() {
		return history;
	}

	public void setHistory(List<Base> history) {
		this.history = history;
	}
	
	public void addHistory(Base base){
		this.history.add(base);
	}
	
	public List<String> getAlreadyFound() {
		return alreadyFound;
	}
	public void setAlreadyFound(List<String> alreadyFound) {
		this.alreadyFound = alreadyFound;
	}
	
	public void addAlreadyFound(String found){
		this.alreadyFound.add(found);
	}
	
	public Node getFather() {
		return father;
	}
	public void setFather(Node father) {
		this.father = father;
	}
	public List<Node> getSons() {
		return sons;
	}
	public void setSons(List<Node> sons) {
		this.sons = sons;
	}
	
	/**
	 * Adds a Node to the sons list.
	 * @param son
	 */
	public void addSon(Node son){
		this.sons.add(son);
	}

	/**
	 * Calculates the size of the tree.
	 * @return 1 + the size of its sons.
	 */
	public int getTreeSize() {
		int total = 1;
		if (sons.size() == 0) {
			return total;
		}
		for (Node node : sons) {
			total += node.getTreeSize();
		}
		return total;
	}

}
