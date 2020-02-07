package application;

import java.util.ArrayList;

public abstract class Trainer {
	
	ArrayList<Anature> anatures; 
	ArrayList<Item> items; 
	Anature currentAnature;
	BaseAI baseAI; 
	public Trainer(ArrayList<Anature> anatures, ArrayList<Item> items, Anature currentAnature, BaseAI baseAI) {
		
		this.anatures = anatures;
		this.items = items;
		this.currentAnature = currentAnature;
		this.baseAI = baseAI;
	}
	
	
	abstract void switchAnature(); 
	abstract void useTurn(); 
	
}
