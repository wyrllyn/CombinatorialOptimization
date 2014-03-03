package algo;

import java.util.ArrayList;

import core.Base;
import core.Universe;

public abstract class Algo {
	
	protected Universe multiverse;
	
	public Algo(Universe theVerse) {
		this.multiverse = theVerse;
	}
	
	public void removeUselessBases(){
		ArrayList<Base> bases = multiverse.getListBases();
		String [] enterprises = multiverse.getEnterpriseScenario();
		ArrayList<Base> toKeep = new ArrayList<Base>();	
		
		for (Base base : bases){
			for (String ent : enterprises){
				if (base.contains(ent)){
					toKeep.add(base);
					break;
				}
			}			
		}
		multiverse.setListBases(toKeep);
	}
	
	public static void updateAlreadyFound(ArrayList<String> alreadyFound, Base base,
			String[] enterpriseScenario) {
		for (String enterprise: enterpriseScenario) {
			if (!alreadyFound.contains(enterprise) && base.contains(enterprise)) {
				alreadyFound.add(enterprise);
			}
		}
	}

	public boolean areWeDone(ArrayList<String> alreadyFound) {
		return alreadyFound.size() == multiverse.getEnterpriseScenario().length;
	}
	
	public void startAlgo(int n) {
		removeUselessBases();
	}

}
