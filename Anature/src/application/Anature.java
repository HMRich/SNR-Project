package application;

import application.abillities.Ability;
import application.enums.Gender;
import application.enums.Species;
import application.enums.Type;

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
	private int mIndexNum;
	private int mAttack, mSpecialAttack, mDefense, mSpecialDefense, mTotalHp, mSpeed, mAccuracy;
	private int mTempAttack, mTempSpecialAttack, mTempDefense, mTempSpecialDefense, mTempSpeed, mTempAccuracy;

	public Anature(String name, String owner, int level, int currentXp, Gender gender, MoveSet moves, Type[] types, Species species, boolean isShiny,
			int indexNum, Ability ability, int attack, int specialAttack, int defense, int specialDefense, int totalHp, int speed, int accuracy)
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
		{
			mSecondaryType = types[1];
		}

		mSpecies = species;
		mIsShiny = isShiny;
		mIndexNum = indexNum;
		mAbility = ability;
		mAttack = attack;
		mSpecialAttack = specialAttack;
		mDefense = defense;
		mSpecialDefense = specialDefense;
		mTotalHp = totalHp;
		mAccuracy = accuracy;
		resetTempStats();
	}

	public MoveSet getMoves()
	{
		return mMoves;
	}

	public String getName()
	{
		return mName;
	}

	public void setName(String name)
	{
		mName = name;
	}

	public String getOwner()
	{
		return mOwner;
	}

	public void setOwner(String owner)
	{
		mOwner = owner;
	}

	public int getLevel()
	{
		return mLevel;
	}

	public void setLevel(int level)
	{
		mLevel = level;
	}

	public int getCurrentXp()
	{
		return mCurrentXp;
	}

	public void setCurrentXp(int currentXp)
	{
		mCurrentXp = currentXp;
	}

	public int getCurrHp()
	{
		return mCurrHp;
	}

	public void setCurrHp(int currHp)
	{
		mCurrHp = currHp;
	}

	public Gender getGender()
	{
		return mGender;
	}

	public void setGender(Gender gender)
	{
		mGender = gender;
	}

	public Type getPrimaryType()
	{
		return mPrimaryType;
	}

	public void setPrimaryType(Type primaryType)
	{
		mPrimaryType = primaryType;
	}

	public Type getSecondaryType()
	{
		return mSecondaryType;
	}

	public void setSecondaryType(Type secondaryType)
	{
		mSecondaryType = secondaryType;
	}

	public Species getSpecies()
	{
		return mSpecies;
	}

	public void setSpecies(Species species)
	{
		mSpecies = species;
	}

	public boolean isShiny()
	{
		return mIsShiny;
	}

	public void setShiny(boolean isShiny)
	{
		mIsShiny = isShiny;
	}

	public Ability getAbility()
	{
		return mAbility;
	}

	public void setAbility(Ability ability)
	{
		mAbility = ability;
	}

	public int getAttack()
	{
		return mAttack;
	}

	public void setAttack(int attack)
	{
		mAttack = attack;
	}

	public int getSpecialAttack()
	{
		return mSpecialAttack;
	}

	public void setSpecialAttack(int specialAttack)
	{
		mSpecialAttack = specialAttack;
	}

	public int getDefense()
	{
		return mDefense;
	}

	public void setDefense(int defense)
	{
		mDefense = defense;
	}

	public int getSpecialDefense()
	{
		return mSpecialDefense;
	}

	public void setSpecialDefense(int specialDefense)
	{
		mSpecialDefense = specialDefense;
	}

	public int getTotalHp()
	{
		return mTotalHp;
	}

	public void setTotalHp(int totalHp)
	{
		mTotalHp = totalHp;
	}

	public int getSpeed()
	{
		return mSpeed;
	}

	public void setSpeed(int speed)
	{
		mSpeed = speed;
	}

	public int getTempAttack()
	{
		return mTempAttack;
	}

	public void setTempAttack(int tempAttack)
	{
		mTempAttack = tempAttack;
	}

	public int getTempSpecialAttack()
	{
		return mTempSpecialAttack;
	}

	public void setTempSpecialAttack(int tempSpecialAttack)
	{
		mTempSpecialAttack = tempSpecialAttack;
	}

	public int getTempDefense()
	{
		return mTempDefense;
	}

	public void setTempDefense(int tempDefense)
	{
		mTempDefense = tempDefense;
	}

	public int getTempSpecialDefense()
	{
		return mTempSpecialDefense;
	}

	public void setTempSpecialDefense(int tempSpecialDefense)
	{
		mTempSpecialDefense = tempSpecialDefense;
	}

	public int getTempSpeed()
	{
		return mTempSpeed;
	}

	public void setTempSpeed(int tempSpeed)
	{
		mTempSpeed = tempSpeed;
	}

	public int getIndexNum()
	{
		return mIndexNum;
	}

	public int getTempAccuracy()
	{
		return mTempAccuracy;
	}

	public void setTempAccuracy(int accuracy)
	{
		mTempAccuracy = accuracy;
	}

	public int getAccuracy()
	{
		return mAccuracy;
	}

	public void setAccuracy(int mAccuracy)
	{
		this.mAccuracy = mAccuracy;
	}

	public void resetTempStats()
	{
		mTempAttack = 0;
		mTempSpecialAttack = 0;
		mTempDefense = 0;
		mTempSpecialDefense = 0;
		mTempSpeed = 0;
		mTempAccuracy = 0;
	}

	public void takeDamage(double damage)
	{
		mCurrHp -= damage;

		if(mCurrHp < 0)
			mCurrHp = 0;
	}

	public String healAnature(int healAmount)
	{
		String result = " was healed " + healAmount + " hp.";

		mCurrHp += healAmount;

		if(mCurrHp > mTotalHp)
		{
			mCurrHp = mTotalHp;
			result = " was healed completely!";
		}

		return mName + result;
	}
	
	public double getHpPercent()
	{
		return mCurrHp / mTotalHp;
	}

}