package application;

import application.enums.*;

public class PICKNAMEmon
{
	private String mName, mOwner;
	private int mLevel, mCurrentXp;
	private Gender mGender;
	private MoveSet mMoves;
	private Type mPrimaryType, mSecondaryType;
	private Species mSpecies;
	private boolean mIsShiny;
	private Ability mAbility;
	private int mAttack, mSpecialAttack, mDefense, mSpecialDefense, mHp;
	
	
	
	public PICKNAMEmon(String name, String owner, int level, int currentXp, Gender gender, MoveSet moves, Type[] types,
					   Species species, boolean isShiny, Ability ability, int attack, int specialAttack,
					   int defense, int specialDefense, int hp)
	{
		if(name == null || owner == null || moves == null || ability == null || types == null)
			throw new IllegalArgumentException("Null parameter.");
		
		else if(attack < 0 || specialAttack < 0 || defense < 0 || specialDefense < 0 || hp < 0 || level <= 0 || currentXp < 0)
			throw new IllegalArgumentException("Invalid int values.");
		
		else if(types.length > 2 || types.length == 0)
			throw new IllegalArgumentException("Invalid types.");
		
		this.mName = name;
		this.mOwner = owner;
		this.mLevel = level;
		this.mCurrentXp = currentXp;
		this.mGender = gender;
		this.mMoves = moves;
		this.mPrimaryType = types[0];
		
		if(types.length == 2)
			this.mSecondaryType = types[1];
		
		this.mSpecies = species;
		this.mIsShiny = isShiny;
		this.mAbility = ability;
		this.mAttack = attack;
		this.mSpecialAttack = specialAttack;
		this.mDefense = defense;
		this.mSpecialDefense = specialDefense;
		this.mHp = hp;
	}

	public MoveSet getMoves()
	{
		return mMoves;
	}

	public String getmName()
	{
		return mName;
	}

	public String getmOwner()
	{
		return mOwner;
	}

	public int getmLevel()
	{
		return mLevel;
	}

	public int getmCurrentXp()
	{
		return mCurrentXp;
	}

	public Gender getmGender()
	{
		return mGender;
	}

	public Type getmPrimaryType()
	{
		return mPrimaryType;
	}

	public Type getmSecondaryType()
	{
		return mSecondaryType;
	}

	public Species getmSpecies()
	{
		return mSpecies;
	}

	public boolean ismIsShiny()
	{
		return mIsShiny;
	}

	public Ability getmAbility()
	{
		return mAbility;
	}

	public int getmAttack()
	{
		return mAttack;
	}

	public int getmSpecialAttack()
	{
		return mSpecialAttack;
	}

	public int getmDefense()
	{
		return mDefense;
	}

	public int getmSpecialDefense()
	{
		return mSpecialDefense;
	}

	public int getmHp()
	{
		return mHp;
	}
	
	
}
