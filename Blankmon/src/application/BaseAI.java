package application;
import java.util.ArrayList;

public class BaseAI {
	
	ArrayList<Anature> anatures;
	ArrayList<Item> items; 
	Anature currentAnature; 
	private int healthThreshold; 
	private int switchThreshold;
	private boolean prefersDamage; 
	
	public BaseAI(ArrayList<Anature> anatureList, ArrayList<Item> itemList, Anature currentAnature, int healthThreshold,
			int switchThreshold, boolean prefersDamage) {
		
		this.anatures = anatureList;
		this.items = itemList;
		this.currentAnature = currentAnature;
		this.healthThreshold = healthThreshold;
		this.switchThreshold = switchThreshold;
		this.prefersDamage = prefersDamage;
	}
	
	public final void useItem() {
		if(isEmpty()) {
			System.out.println("Cannot use Item because there are none");
		} else if(isAnatureAtThreshold()) {
			consumeItem();
		}
	}
	
	public final void switchAnature() {
		if(hasOtherAnatures()) {
			if(isAnatureAtTypeDisadvantage()) {
				System.out.println("Type Disadvantage, Switch Anature"); 
			} else if(willSwitch()) {
				System.out.println("Health Threshold was met, Switch Anature"); 
			}
		}
	}
	
	public void chooseMove() {
		double random = Math.random();
		if(random < 0.25) {
			System.out.println("Move at Index 0 was executed"); 
		} else if(random > 0.25 && random <= 0.50) {
			System.out.println("Move at Index 1 was executed"); 
		} else if(random > 0.50 && random <= 0.75) {
			System.out.println("Move at Index 2 was executed"); 
		} else {
			System.out.println("Move at Index 3 was executed"); 
		}
	}
	

	public boolean willSwitch() {
		if(isAnatureAtThreshold()) {
			return true;
		}
		return false;
	}

	private boolean isAnatureAtTypeDisadvantage() {
		System.out.println("Checking to see if Anature is at a Type Disadvantage"); 
		return false;
	}

	private boolean hasOtherAnatures() {
		System.out.println("Checking for any viable Anatures for switching"); 
		return false;
	}

	
	
	private boolean isEmpty() {
		return items.isEmpty();
	}
	
	private boolean isAnatureAtThreshold() {
		return currentAnature.getHp() < healthThreshold; 
	}
	
	private void consumeItem() {
		System.out.println("Item was consumed"); 
	}
}
