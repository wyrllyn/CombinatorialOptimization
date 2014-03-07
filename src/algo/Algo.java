package algo;

import java.util.ArrayList;
import java.util.List;

import core.Base;
import core.Universe;

/**
 * Algorithm super class. Contains methods that can be used by any implementing subclass.
 * @author Sara Tari & Adrien Droguet
 *
 */
public abstract class Algo {
	
	/**
	 * Universe object our algorithm will be operating on.
	 */
	protected Universe multiverse;
	
	public Algo(Universe theVerse) {
		this.multiverse = theVerse;
	}
	
	/**
	 * Removes bases that don't contain any of the enterprises we are looking for.
	 */
	public void removeUselessBases(){
		List<Base> bases = multiverse.getListBases();
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
	
	/**
	 * Updates the list of enterprises that have been found. 
	 * @param alreadyFound What we have.
	 * @param base Where we are looking.
	 * @param enterpriseScenario What we are looking for.
	 */
	public static void updateAlreadyFound(ArrayList<String> alreadyFound, Base base,
			String[] enterpriseScenario) {
		for (String enterprise: enterpriseScenario) {
			if (!alreadyFound.contains(enterprise) && base.contains(enterprise)) {
				alreadyFound.add(enterprise);
			}
		}
	}

	/**
	 * 
	 * @param alreadyFound
	 * @return True if we are done.
	 */
	public boolean areWeDone(ArrayList<String> alreadyFound) {
		return alreadyFound.size() == multiverse.getEnterpriseScenario().length;
	}
	
	/**
	 * Starts the algorithm. Note that this method must be re-implemented by any subclasses.
	 * @param n
	 */
	public void startAlgo(int n) {
		removeUselessBases();
	}

}
