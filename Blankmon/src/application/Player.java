package application;

import java.util.ArrayList;
import application.enums.*;
import javafx.scene.image.Image;

public class Player {
	private String mName;
	private Gender mGender;
	private ArrayList<Boolean> mBadges; 
	private Backpack mBackpack; 
	private Image mSprite; 
	private ArrayList <Anature> mPartyAnature; 

	public Player(Image sprite) {
		mName = "Example_Player";
		mGender = Gender.Trans;
		mBadges = new ArrayList<Boolean>();
		mBackpack = new Backpack();
		mSprite = sprite;
		mPartyAnature = new ArrayList<Anature>();
	}
	
	public String getName()
	{
		return mName;
	}
	
	public Gender getGender()
	{
		return mGender;
	}
	
	public int getNumberOfBadges()
	{
		return mBadges.size();
	}
	
	public Backpack getBackpack()
	{
		return mBackpack;
	}
	
	public Image getSprite()
	{
		return mSprite;
	}
	
	public ArrayList<Anature> getAnatures()
	{
		return mPartyAnature;
	}

	public void addAnatures(Anature toAdd)
	{
		if(toAdd == null)
			throw new IllegalArgumentException("toAdd was Null.");
		
		else if(mPartyAnature.size() == 6)
			throw new IllegalArgumentException("The Player's Anature Party already has 6.");
		
		mPartyAnature.add(toAdd);
	}
}