package core;

import java.util.ArrayList;

public class Node {
	
	private int cost;
	private ArrayList<Base> history;
	private ArrayList<String> alreadyFound;
	private Node father;
	private ArrayList<Node> sons;
	
	
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

}
