package application.anatures;

import java.util.ArrayList;

import application.anatures.abillities.NullAbility;
import application.enums.AbilityIds;
import application.enums.Gender;
import application.enums.Species;
import application.enums.Type;
import application.interfaces.IAbility;
import application.pools.AbilityPool;
import application.enums.StatusEffects;
import javafx.scene.image.Image;

public class Anature
{
	private String mName, mOwnerName;
	private boolean mIsShiny;
	private Species mSpecies;
	private Gender mGender;
	private Type mPrimaryType, mSecondaryType;
	private MoveSet mMoveSet;
	private IAbility mAbility;
	private StatusEffects mStatus;
	private int mIndexNumber;
	private int mLevel, mCurrentExperiencePoints;
	private int mTotalHitPoints, mCurrentHitPoints;
	private int mAttack, mDefense, mSpecialAttack, mSpecialDefense, mSpeed, mAccuracy;
	private int mTempAttack, mTempDefense, mTempSpecialAttack, mTempSpecialDefense, mTempSpeed, mTempAccuracy;

	Anature()
	{
		mName = "";
		mOwnerName = "";
		mIsShiny = false;
		mSpecies = Species.NotSet;
		mGender = Gender.NotSet;
		mPrimaryType = Type.NotSet;
		mSecondaryType = Type.NotSet;
		mMoveSet = NullMoveSet.getNullMoveSet();
		mAbility = NullAbility.getNullAbility();
		mStatus = StatusEffects.NotSet;
		mLevel = -1;
		mCurrentExperiencePoints = -1;
		mTotalHitPoints = -1;
		mCurrentHitPoints = -1;
		mIndexNumber = -1;
		mAttack = -1;
		mDefense = -1;
		mSpecialAttack = -1;
		mSpecialDefense = -1;
		mSpeed = -1;
		mAccuracy = -1;
		resetTempStats();
	}

	/*
	 * PACKAGE SETS
	 */

	void setName(String name)
	{
		if(name == null)
		{
			throw new IllegalArgumentException("Passed value \"name\" was null.");
		}

		if(name.trim()
				.isEmpty())
		{
			throw new IllegalArgumentException("Passed value \"name\" was an empty string.");
		}

		mName = name;
	}

	void setOwnerName(String ownerName)
	{
		if(ownerName == null)
		{
			throw new IllegalArgumentException("Passed value \"ownerName\" was null.");
		}

		if(ownerName.trim()
				.isEmpty())
		{
			throw new IllegalArgumentException("Passed value \"ownerName\" was an empty string.");
		}

		mOwnerName = ownerName;
	}

	void setIsShiny(boolean isShiny)
	{
		mIsShiny = isShiny;
	}

	void setSpecies(Species species)
	{
		if(species == null)
		{
			throw new IllegalArgumentException("Passed value \"species\" was null.");
		}

		if(species.equals(Species.NotSet))
		{
			throw new IllegalArgumentException("Passed value \"species\" was equal to " + species.toString() + ".");
		}

		mSpecies = species;
	}

	void setGender(Gender gender)
	{
		if(gender == null)
		{
			throw new IllegalArgumentException("Passed value \"gender\" was null.");
		}

		if(gender.equals(Gender.NotSet))
		{
			throw new IllegalArgumentException("Passed value \"gender\" was equal to " + gender.toString() + ".");
		}

		mGender = gender;
	}

	void setPrimaryType(Type primaryType)
	{
		if(primaryType == null)
		{
			throw new IllegalArgumentException("Passed value \"primaryType\" was null.");
		}

		if(primaryType.equals(Type.NotSet))
		{
			throw new IllegalArgumentException("Passed value \"primaryType\" was equal to " + primaryType.toString() + ".");
		}

		mPrimaryType = primaryType;
	}

	void setSecondaryType(Type secondaryType)
	{
		if(secondaryType == null)
		{
			throw new IllegalArgumentException("Passed value \"secondaryType\" was null.");
		}

		mSecondaryType = secondaryType;
	}

	void setMoveSet(MoveSet moveSet)
	{
		if(moveSet == null)
		{
			throw new IllegalArgumentException("Passed value \"moveSet\" was null.");
		}

		// TODO Should we check for the NullMoveSet object here? And how would we do
		// that

		mMoveSet = moveSet;
	}

	void setAbility(IAbility iAbility)
	{
		if(iAbility == null)
		{
			throw new IllegalArgumentException("Passed value \"ability\" was null.");
		}

		// TODO Should we add the ability to adding the NullAbility to the Anature
		if(iAbility.equals(AbilityPool.getAbility(AbilityIds.NullAbility)))
		{
			throw new IllegalArgumentException("Passed value \"ability\" was equal to the NullAbility ability.");
		}

		mAbility = iAbility;
	}

	void setStatus(StatusEffects statusEffect)
	{
		if(statusEffect == null)
		{
			throw new IllegalArgumentException("Passed value \"statusEffect\" was null.");
		}

		if(statusEffect.equals(StatusEffects.NotSet))
		{
			throw new IllegalArgumentException("Passed value \"statusEffect\" was equal to " + statusEffect.toString() + ".");
		}

		mStatus = statusEffect;
	}

	void setIndexNumber(int indexNumber)
	{
		if(indexNumber < 0)
		{
			throw new IllegalArgumentException("Passed value \"indexNumber\" was below 0.");
		}

		mIndexNumber = indexNumber;
	}

	void setLevel(int level)
	{
		// TODO Should we have a upper level limit?
		if(level < 0)
		{
			throw new IllegalArgumentException("Passed value \"level\" was below 0.");
		}

		mLevel = level;
	}

	void setCurrentExperiencePoints(int currentExperiencePoints)
	{
		if(currentExperiencePoints < 0)
		{
			throw new IllegalArgumentException("Passed value \"currentExperience\" was below 0.");
		}

		mCurrentExperiencePoints = currentExperiencePoints;
	}

	void setTotalHitPoints(int totalHitPoints)
	{
		// TODO Should we add a minimum cap? So lowest possible HP is 100?
		if(totalHitPoints <= 0)
		{
			throw new IllegalArgumentException("Passed value \"totalHitPoints\" was 0 or below.");
		}

		mTotalHitPoints = totalHitPoints;
	}

	void setCurrentHitPoints(int currentHitPoints)
	{
		if(currentHitPoints < 0)
		{
			throw new IllegalArgumentException("Passed value \"totalHitPoints\" was 0 or below.");
		}

		mCurrentHitPoints = currentHitPoints;
	}

	void setAttack(int attack)
	{
		if(attack < 0)
		{
			throw new IllegalArgumentException("Passed value \"attack\" was below 0.");
		}

		mAttack = attack;
	}

	void setDefense(int defense)
	{
		if(defense < 0)
		{
			throw new IllegalArgumentException("Passed value \"defense\" was below 0.");
		}

		mDefense = defense;
	}

	void setSpecialAttack(int specialAttack)
	{
		if(specialAttack < 0)
		{
			throw new IllegalArgumentException("Passed value \"specialAttack\" was below 0.");
		}

		mSpecialAttack = specialAttack;
	}

	void setSpecialDefense(int specialDefense)
	{
		if(specialDefense < 0)
		{
			throw new IllegalArgumentException("Passed value \"specialDefense\" was below 0.");
		}

		mSpecialDefense = specialDefense;
	}

	void setSpeed(int speed)
	{
		if(speed < 0)
		{
			throw new IllegalArgumentException("Passed value \"speed\" was below 0.");
		}

		mSpeed = speed;
	}

	void setAccuracy(int accuracy)
	{
		if(accuracy < 0)
		{
			throw new IllegalArgumentException("Passed value \"accuracy\" was below 0.");
		}

		mAccuracy = accuracy;
	}

	/*
	 * PUBLIC GETS
	 */

	public String getName()
	{
		return mName;
	}

	public String getOwner()
	{
		return mOwnerName;
	}

	public boolean isShiny()
	{
		return mIsShiny;
	}

	public Species getSpecies()
	{
		return mSpecies;
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

	public MoveSet getMoveSet()
	{
		return mMoveSet;
	}

	public IAbility getAbility()
	{
		return mAbility;
	}

	public StatusEffects getStatus()
	{
		return mStatus;
	}

	public int getIndexNumber()
	{
		return mIndexNumber;
	}

	public int getLevel()
	{
		return mLevel;
	}

	public int getCurrentExpereincePoints()
	{
		return mCurrentExperiencePoints;
	}

	public int getTotalHitPoints()
	{
		return mTotalHitPoints;
	}

	public int getCurrentHitPoints()
	{
		return mCurrentHitPoints;
	}

	public int getAttack()
	{
		return mAttack + mTempAttack;
	}

	public int getDefense()
	{
		return mDefense + mTempDefense;
	}

	public int getSpecialAttack()
	{
		return mSpecialAttack + mTempSpecialAttack;
	}

	public int getSpecialDefense()
	{
		return mSpecialDefense + mTempSpecialDefense;
	}

	public int getSpeed()
	{
		return mSpeed + mTempSpeed;
	}

	public int getAccuracy()
	{
		return mAccuracy + mTempAccuracy;
	}

	/*
	 * PUBLIC METHODS
	 */

	public void adjustTempAttack(double tempAttackAdjustment)
	{
		if(tempAttackAdjustment < -1 || tempAttackAdjustment > 1)
		{
			throw new IllegalArgumentException("Passed value \"tempAttackAdjustment\" was below -1 or above 1.");
		}

		int adjustment = (int) (tempAttackAdjustment * mAttack);

		mTempAttack = mTempAttack + adjustment;
	}

	public void adjustTempDefense(double tempDefenseAdjustment)
	{
		if(tempDefenseAdjustment < -1 || tempDefenseAdjustment > 1)
		{
			throw new IllegalArgumentException("Passed value \"tempDefenseAdjustment\" was below -1 or above 1.");
		}

		int adjustment = (int) (tempDefenseAdjustment * mDefense);

		mTempDefense = mTempDefense + adjustment;
	}

	public void adjustTempSpecialAttack(double tempSpecialAttackAdjustment)
	{
		if(tempSpecialAttackAdjustment < -1 || tempSpecialAttackAdjustment > 1)
		{
			throw new IllegalArgumentException("Passed value \"tempSpecialAttackAdjustment\" was below -1 or above 1.");
		}

		int adjustment = (int) (tempSpecialAttackAdjustment * mSpecialAttack);

		mTempSpecialAttack = mTempSpecialAttack + adjustment;
	}

	public void adjustTempSpecialDefense(double tempSpecialDefenseAdjustment)
	{
		if(tempSpecialDefenseAdjustment < -1 || tempSpecialDefenseAdjustment > 1)
		{
			throw new IllegalArgumentException("Passed value \"tempSpecialDefenseAdjustment\" was below -1 or above 1.");
		}

		int adjustment = (int) (tempSpecialDefenseAdjustment * mSpecialDefense);

		mTempSpecialDefense = mTempSpecialDefense + adjustment;
	}

	public void adjustTempSpeed(double tempSpeedAdjustment)
	{
		if(tempSpeedAdjustment < -1 || tempSpeedAdjustment > 1)
		{
			throw new IllegalArgumentException("Passed value \"tempSpeedAdjustment\" was below -1 or above 1.");
		}

		int adjustment = (int) (tempSpeedAdjustment * mSpeed);

		mTempSpeed = mTempSpeed + adjustment;
	}

	public void adjustTempAccuracy(double accuracyAdjustment)
	{
		if(accuracyAdjustment < -1 || accuracyAdjustment > 1)
		{
			throw new IllegalArgumentException("Passed value \"accuracyAdjustment\" was below -1 or above 1.");
		}

		int adjustment = (int) (accuracyAdjustment * mAccuracy);

		mTempAccuracy = mTempAccuracy + adjustment;
	}

	public void updateName(String name)
	{
		setName(name);
	}

	public void updateStatus(StatusEffects status)
	{
		setStatus(status);
	}

	public void updateCurrentHitPoints(int hitPoints)
	{
		setCurrentHitPoints(hitPoints);
	}

	public ArrayList<Type> getTypes()
	{
		ArrayList<Type> types = new ArrayList<Type>();
		if(mPrimaryType != null)
		{
			types.add(mPrimaryType);
		}
		if(mSecondaryType != null)
		{
			types.add(mSecondaryType);
		}
		return types;
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
		mCurrentHitPoints -= damage;

		if(mCurrentHitPoints < 0)
			mCurrentHitPoints = 0;
	}

	public String healAnature(int healAmount)
	{
		int hitPointsAfterHeal = getCurrentHitPoints() + healAmount;
		if(healAmount == Integer.MAX_VALUE || hitPointsAfterHeal > getTotalHitPoints())
		{
			setCurrentHitPoints(getTotalHitPoints());
			return " was healed completely!";
		}

		setCurrentHitPoints(getCurrentHitPoints() + healAmount);
		return " was healed " + healAmount + " hp.";
	}

	public double getHpPercent()
	{
		return mCurrentHitPoints / mTotalHitPoints;
	}

	public Anature getClone()
	{
		return new AnatureBuilder().setName(mName)
				.setOwnerName(mOwnerName)
				.setIsShiny(mIsShiny)
				.setSpecies(mSpecies)
				.setGender(mGender)
				.setPrimaryType(mPrimaryType)
				.setSecondaryType(mSecondaryType)
				.setMoveSet(mMoveSet)
				.setAbility(mAbility)
				.setStatus(mStatus)
				.setIndexNumber(mIndexNumber)
				.setLevel(mLevel)
				.setCurrentExperiencePoints(mCurrentExperiencePoints)
				.setTotalHitPoints(mTotalHitPoints)
				.setCurrentHitPoints(mCurrentHitPoints)
				.setAttack(mAttack)
				.setDefense(mDefense)
				.setSpecialAttack(mSpecialAttack)
				.setDefense(mSpecialDefense)
				.setSpeed(mSpeed)
				.setAccuracy(mAccuracy)
				.create();
	}

	public Image getFrontSprite()
	{
		return new Image(getClass().getResource("/resources/images/anatures/" + mSpecies.toString() + "_Front.png")
				.toExternalForm(), 1000.0, 1000.0, true, false);
	}

	public Image getBackSprite()
	{
		return new Image(getClass().getResource("/resources/images/anatures/" + mSpecies.toString() + "_Back.png")
				.toExternalForm(), 1000.0, 1000.0, true, false);
	}

	/*
	 * PACKAGE METHODS
	 */

	boolean canCreate()
	{
		if(mName.isEmpty())
		{
			throw new IllegalStateException("The \"name\" variable was never set during construction.");
		}

		// TODO Is this a problem for wild anatures?
		if(mOwnerName.isEmpty())
		{
			throw new IllegalStateException("The \"ownerName\" variable was never set during construction.");
		}

		if(mSpecies.equals(Species.NotSet))
		{
			throw new IllegalStateException("The \"species\" variable was never set during construction.");
		}

		if(mGender.equals(Gender.NotSet))
		{
			throw new IllegalStateException("The \"gender\" variable was never set during construction.");
		}

		if(mPrimaryType.equals(Type.NotSet))
		{
			throw new IllegalStateException("The \"primaryType\" variable was never set during construction.");
		}

		if(mMoveSet.equals(NullMoveSet.getNullMoveSet()))
		{
			throw new IllegalStateException("The \"moveSet\" variable was never set during construction.");
		}

		if(mAbility.equals(NullAbility.getNullAbility()))
		{
			throw new IllegalStateException("The \"ability\" variable was never set during construction.");
		}

		if(mStatus.equals(StatusEffects.NotSet))
		{
			throw new IllegalStateException("The \"status\" variable was never set during construction.");
		}

		if(mLevel == -1)
		{
			throw new IllegalStateException("The \"\" variable was never set during construction.");
		}

		if(mCurrentExperiencePoints == -1)
		{
			throw new IllegalStateException("The \"currentExperience\" variable was never set during construction.");
		}

		if(mTotalHitPoints == -1)
		{
			throw new IllegalStateException("The \"totalHitPoints\" variable was never set during construction.");
		}

		if(mCurrentHitPoints == -1)
		{
			throw new IllegalStateException("The \"currentHitPoints\" variable was never set during construction.");
		}

		if(mIndexNumber == -1)
		{
			throw new IllegalStateException("The \"indexNumber\" variable was never set during construction.");
		}

		if(mAttack == -1)
		{
			throw new IllegalStateException("The \"attack\" variable was never set during construction.");
		}

		if(mDefense == -1)
		{
			throw new IllegalStateException("The \"defense\" variable was never set during construction.");
		}

		if(mSpecialAttack == -1)
		{
			throw new IllegalStateException("The \"specialAttack\" variable was never set during construction.");
		}

		if(mSpecialDefense == -1)
		{
			throw new IllegalStateException("The \"specialDefense\" variable was never set during construction.");
		}

		if(mSpeed == -1)
		{
			throw new IllegalStateException("The \"speed\" variable was never set during construction.");
		}

		if(mAccuracy == -1)
		{
			throw new IllegalStateException("The \"accuracy\" variable was never set during construction.");
		}

		return true;
	}

}