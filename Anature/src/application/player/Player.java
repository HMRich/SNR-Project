package application.player;

import java.util.ArrayList;

import application.enums.Gender;
import application.interfaces.IAnature;
import javafx.scene.image.Image;

public class Player
{
	private String mName;
	private Gender mGender;
	private int mTokens;
	private boolean[] mBadges;
	private Backpack mBackpack;
	private Image mSprite;
	private ArrayList<IAnature> mPartyAnature;
	private int mSelectedIndex;

	public Player(Image sprite)
	{
		mName = "Example_Player";
		mGender = Gender.Trans;
		mTokens = 0;
		mBadges = new boolean[10];
		mBackpack = new Backpack();
		mSprite = sprite;
		mPartyAnature = new ArrayList<IAnature>();
		mSelectedIndex = 0;
	}

	public String getName()
	{
		return mName;
	}

	public void setName(String name)
	{
		mName = name;
	}

	public Gender getGender()
	{
		return mGender;
	}

	public void setGender(Gender gender)
	{
		mGender = gender;
	}

	public int getTokens()
	{
		return mTokens;
	}

	public void setTokens(int tokens)
	{
		mTokens = tokens;
	}

	public int getNumberOfBadges()
	{
		int badges = 0;
		for(boolean badge : mBadges)
		{
			if(badge)
				badges++;
		}

		return badges;
	}

	public void setBadge(int badgePosition)
	{
		if(badgePosition >= mBadges.length)
		{
			throw new IllegalArgumentException("The badgePosition does not exsist.");
		}

		mBadges[badgePosition] = true;
	}

	public Backpack getBackpack()
	{
		return mBackpack;
	}

	public Image getSprite()
	{
		return mSprite;
	}

	public ArrayList<IAnature> getAnatures()
	{
		return mPartyAnature;
	}

	public void setAnature(int position, IAnature anature)
	{
		mPartyAnature.set(position, anature);
	}

	public void addAnatures(IAnature toAdd)
	{
		if(toAdd == null)
			throw new IllegalArgumentException("toAdd was Null.");

		else if(mPartyAnature.size() == 6)
			throw new IllegalArgumentException("The Player's Anature Party already has 6.");

		mPartyAnature.add(toAdd);
	}

	public int getSelectedIndex()
	{
		return mSelectedIndex;
	}

	public void setSelectedIndex(int selectedIndex)
	{
		if(selectedIndex > 5 || selectedIndex < 0)
			throw new IllegalArgumentException("Invalid Selected Index");

		mSelectedIndex = selectedIndex;
	}

	public boolean canBattle()
	{
		boolean canBattle = false;

		for(IAnature anatureBase : mPartyAnature)
		{
			if(anatureBase.getCurrentHitPoints() > 0)
			{
				canBattle = true;
				break;
			}
		}

		return canBattle;
	}
}