package application;

import application.enums.*;

public class Anature
{
	private String mName, mOwner;
	private int mLevel, mCurrentXp, mCurrHp;
	private Gender mGender;
	private MoveSet mMoves;
	private Type mPrimaryType, mSecondaryType;
	private Species mSpecies;
	private boolean mIsShiny;
	private Ability mAbility;
	private int mAttack, mSpecialAttack, mDefense, mSpecialDefense, mTotalHp, mSpeed;

	public Anature(String name, String owner, int level, int currentXp, Gender gender, MoveSet moves, Type[] types,
			Species species, boolean isShiny, Ability ability, int attack, int specialAttack, int defense, int specialDefense,
			int totalHp, int speed)
	{
		if(name == null || owner == null || moves == null || ability == null || types == null)
			throw new IllegalArgumentException("Null parameter.");

		else if(attack < 0 || specialAttack < 0 || defense < 0 || specialDefense < 0 || totalHp < 0 || level <= 0 || currentXp < 0)
			throw new IllegalArgumentException("Invalid int values.");

		else if(types.length > 2 || types.length == 0)
			throw new IllegalArgumentException("Invalid types.");

		mName = name;
		mOwner = owner;
		mLevel = level;
		mCurrentXp = currentXp;
		mCurrHp = totalHp;
		mGender = gender;
		mMoves = moves;
		mPrimaryType = types[0];

		if(types.length == 2)
			mSecondaryType = types[1];

		mSpecies = species;
		mIsShiny = isShiny;
		mAbility = ability;
		mAttack = attack;
		mSpecialAttack = specialAttack;
		mDefense = defense;
		mSpecialDefense = specialDefense;
		mTotalHp = totalHp;
	}

	public MoveSet getMoves()
	{
		return mMoves;
	}

	public String getName()
	{
		return mName;
	}

	public String getOwner()
	{
		return mOwner;
	}

	public int getLevel()
	{
		return mLevel;
	}

	public int getCurrentXp()
	{
		return mCurrentXp;
	}

	public int getCurrHp()
	{
		return mCurrHp;
	}

	public Gender getGender()
	{
		return mGender;
	}

	public Type getPrimaryType()
	{
		return mPrimaryType;
	}

	public Type getSecondaryType()
	{
		return mSecondaryType;
	}

	public Species getmSpecies()
	{
		return mSpecies;
	}

	public boolean isShiny()
	{
		return mIsShiny;
	}

	public Ability getAbility()
	{
		return mAbility;
	}

	public int getAttack()
	{
		return mAttack;
	}

	public int getSpecialAttack()
	{
		return mSpecialAttack;
	}

	public int getDefense()
	{
		return mDefense;
	}

	public int getSpecialDefense()
	{
		return mSpecialDefense;
	}

	public int getTotalHp()
	{
		return mTotalHp;
	}

	public int getSpeed()
	{
		return mSpeed;
	}
	
	public void takeDamage(double damage)
	{
		mCurrHp -= damage;
		
		if(mCurrHp < 0)
			mCurrHp = 0;
	}
}
