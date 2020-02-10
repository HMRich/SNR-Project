package application;

import java.util.ArrayList;
import application.enums.*;

public abstract class Trainer {
	
	private ArrayList<Anature> mAnatures; 
	private ArrayList<Item> mItems; 
	private Anature mCurrentAnature;
	private BaseAI mBaseAI; 
	private int mHealthThreshold;
	private int mSwitchThreshold; 
	public Trainer(ArrayList<Anature> mAnatures, ArrayList<Item> mItems, Anature mCurrentAnature, BaseAI mBaseAI) {
		
		this.mAnatures = mAnatures;
		this.mItems = mItems;
		this.mCurrentAnature = mCurrentAnature;
		this.mBaseAI = mBaseAI;
	}
	
	
	public void switchAnature() {
		mBaseAI.switchAnature(mAnatures, new Type[] {mCurrentAnature.getPrimaryType(), mCurrentAnature.getSecondaryType()}, 25, mCurrentAnature);
	}; 
	public void useTurn() {
		// use thresholds
	}; 
	
}
