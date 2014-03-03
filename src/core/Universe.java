package core;

import java.util.ArrayList;

public class Universe {
	private String enterpriseScenario [];
	private ArrayList<Base> listBases;
	
	public Universe(String[] enterpriseScenario,
			ArrayList<Base> listBase) {
		super();
		this.enterpriseScenario = enterpriseScenario;
		this.listBases = listBase;
	}
	
	public String[] getEnterpriseScenario() {
		return enterpriseScenario;
	}
	public void setEnterpriseScenario(String[] enterpriseScenario) {
		this.enterpriseScenario = enterpriseScenario;
	}
	public ArrayList<Base> getListBases() {
		return listBases;
	}
	public void setListBases(ArrayList<Base> listBase) {
		this.listBases = listBase;
	}
	
	public boolean enterpriseScenarioContains(String ent){
		for (int i = 0 ; i < enterpriseScenario.length ; i++){
			if (enterpriseScenario[i].equals(ent)){
				return true;
			}
		}
		return false;
	}
	
	

}
