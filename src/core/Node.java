package core;

import java.util.ArrayList;

public class Node {
	
	private int cost;
	private ArrayList<Base> history;
	private ArrayList<String> alreadyFound;
	private Node father;
	private ArrayList<Node> sons;
	
	public Node(int cost, ArrayList<Base> history,
			ArrayList<String> alreadyFound, Node father, ArrayList<Node> sons) {
		super();
		this.cost = cost;
		this.history = history;
		this.alreadyFound = alreadyFound;
		this.father = father;
		this.sons = sons;
	}	
	
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

	public ArrayList<Base> getHistory() {
		return history;
	}

	public void setHistory(ArrayList<Base> history) {
		this.history = history;
	}
	
	public void addHistory(Base base){
		this.history.add(base);
	}
	
	public ArrayList<String> getAlreadyFound() {
		return alreadyFound;
	}
	public void setAlreadyFound(ArrayList<String> alreadyFound) {
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
	public ArrayList<Node> getSons() {
		return sons;
	}
	public void setSons(ArrayList<Node> sons) {
		this.sons = sons;
	}
	
	public void addSon(Node son){
		this.sons.add(son);
	}

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
