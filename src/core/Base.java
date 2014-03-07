package core;

/**
 * Base object containing an array of enterprise names and a cost. Bases are comparable by cost.
 * @author Sara Tari & Adrien Droguet
 *
 */
public class Base implements Comparable<Base> {
	
	private int cost;
	private String [] enterprises;
	private String name;
	
	public Base(int cost, String[] enterprises, String name) {
		super();
		this.cost = cost;
		this.enterprises = enterprises;
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	
	/**
	 * 
	 * @param name
	 * @return True if the base contains the specified enterprise.
	 */
	public boolean contains(String name){
		for (int i = 0; i < enterprises.length ; i++){
			if (enterprises[i].equals(name)){
				return true;
			}
		}
		return false;
	}

	@Override
	public int compareTo(Base base) {
		return this.cost - base.cost;
	}

	@Override
	public String toString() {
		return "Base [cost=" + cost + ", name=" + name + "]";
	}

}
