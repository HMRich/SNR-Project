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
		mName = "Demo Player!";
		mGender = Gender.Trans;
		mBadges = new ArrayList<Boolean>();
		mBackpack = new Backpack();
		mSprite = sprite;
		mPartyAnature = new ArrayList<Anature>();
	}
	
	public String getPlayerName()
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

}