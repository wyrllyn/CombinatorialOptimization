package core;

public class Base {
	private int cost;
	private String [] enterprises;
	
	public Base(int cost, String[] enterprises) {
		super();
		this.cost = cost;
		this.enterprises = enterprises;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public String[] getEnterprises() {
		return enterprises;
	}
	public void setEnterprises(String[] enterprises) {
		this.enterprises = enterprises;
	}
	
	
	public boolean contains(String name){
		for (int i = 0; i < enterprises.length ; i++){
			if (enterprises[i].equals(name)){
				return true;
			}
		}
		return false;
	}

}
