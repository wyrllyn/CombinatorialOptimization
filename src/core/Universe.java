package core;

import java.util.ArrayList;

public class Universe {
	private String baseScenario [];
	private String enterpriseScenario [];
	private ArrayList<Base> listBase;
	
	public Universe(String[] baseScenario, String[] enterpriseScenario,
			ArrayList<Base> listBase) {
		super();
		this.baseScenario = baseScenario;
		this.enterpriseScenario = enterpriseScenario;
		this.listBase = listBase;
	}
	public String[] getBaseScenario() {
		return baseScenario;
	}
	public void setBaseScenario(String[] baseScenario) {
		this.baseScenario = baseScenario;
	}
	public String[] getEnterpriseScenario() {
		return enterpriseScenario;
	}
	public void setEnterpriseScenario(String[] enterpriseScenario) {
		this.enterpriseScenario = enterpriseScenario;
	}
	public ArrayList<Base> getListBase() {
		return listBase;
	}
	public void setListBase(ArrayList<Base> listBase) {
		this.listBase = listBase;
	}
	
	

}
