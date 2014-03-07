package core;

import java.util.List;

/**
 * Universe object. Contains an array of enterprises and a list of base.
 * @author Sara Tari & Adrien Droguet
 *
 */
public class Universe {
	private String enterpriseScenario [];
	private List<Base> listBases;
	
	public Universe(String[] enterpriseScenario,
			List<Base> listBase) {
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
	public List<Base> getListBases() {
		return listBases;
	}
	public void setListBases(List<Base> listBase) {
		this.listBases = listBase;
	}
	
	/**
	 * 
	 * @param ent
	 * @return True if the specified scenario is contained in the Universe.
	 */
	public boolean enterpriseScenarioContains(String ent){
		for (int i = 0 ; i < enterpriseScenario.length ; i++){
			if (enterpriseScenario[i].equals(ent)){
				return true;
			}
		}
		return false;
	}
}
