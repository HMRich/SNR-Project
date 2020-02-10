package application;
import java.util.ArrayList;
import java.util.Random;
import application.enums.*;

public class BaseAI {
	public BaseAI() {
		
	}
	
	public final String useItem(ArrayList<Item> items, Anature currentAnature, int healthThreshold) {
		if(items.isEmpty()) {
			return "Cannot use Item because there are none";
		} else if(currentAnature.getHp() < healthThreshold) {
			return "Item Consumed";
		} else {
			return "";
		}
		
		 
	}
	
	public String switchAnature(ArrayList<Anature> anatures, Type[] types, int switchThreshold, Anature currentAnature) {
		if(!anatures.isEmpty() && isAnatureAtTypeDisadvantage(types) && willSwitch(switchThreshold)) {
			return "Switch Anature"; 
		}
		
		return ""; 
	}
	
	public String chooseMove() {
		double random = Math.random();
		if(random < 0.25) {
			return "Move at Index 0 was executed"; 
		} else if(random > 0.25 && random <= 0.50) {
			return "Move at Index 1 was executed"; 
		} else if(random > 0.50 && random <= 0.75) {
			return "Move at Index 2 was executed"; 
		} else {
			return "Move at Index 3 was executed"; 
		}
	}
	

	private final boolean willSwitch(int switchThreshold) {
		return isAnatureAtThreshold(switchThreshold); 
	}

	private boolean isAnatureAtTypeDisadvantage(Type[] types) {
		System.out.println("Checking to see if Anature is at a Type Disadvantage"); 
		return false;
	}

	
	private boolean isAnatureAtThreshold(int switchThreshold) {
		if(switchThreshold == 0) return false;
		
		Random r = new Random();
		return r.nextInt(101) <= switchThreshold; 
	}
	
	
}
