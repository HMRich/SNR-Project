package application.anatures;

import java.util.ArrayList;

import application.anatures.abillities.NullAbility;
import application.anatures.movesets.MoveSet;
import application.anatures.movesets.NullMoveSet;
import application.anatures.stats.NullStats;
import application.enums.BattleAnimationType;
import application.enums.Gender;
import application.enums.Species;
import application.enums.StatusEffects;
import application.enums.Type;
import application.interfaces.IAbility;
import application.interfaces.IAnature;
import application.interfaces.stats.IStats;
import javafx.scene.image.Image;

public class AnatureClass
{
	public static AnatureClass Classes = new AnatureClass();

	public abstract class AnatureVariables
	{
		public String anatureName = "";
		public String anatureOwnerName = "";
		public boolean anatureIsShiny = false;
		public Species anatureSpecies = Species.NotSet;
		public Gender anatureGender = Gender.NotSet;
		public Type anaturePrimaryType = Type.NotSet;
		public Type anatureSecondaryType = Type.NotSet;
		public MoveSet anatureMoveSet = NullMoveSet.getNullMoveSet();
		public IAbility anatureAbility = NullAbility.getNullAbility();
		public StatusEffects anatureStatus = StatusEffects.None;
		public IStats anatureStats = NullStats.getNullStats();
		public int anatureIndexNumber = -1;
		public int anatureCatchRate = -1;

		public abstract void getContext();
	}

	class Anature implements IAnature
	{
		private String mName;
		private String mOwnerName;
		private boolean mIsShiny;
		private Species mSpecies;
		private Gender mGender;
		private Type mPrimaryType;
		private Type mSecondaryType;
		private MoveSet mMoveSet;
		private IAbility mAbility;
		private StatusEffects mStatus;
		private IStats mStats;
		private int mIndexNumber;
		private int mCatchRate;

		/*
		 * CONSTRUCTOR
		 */

		Anature(AnatureVariables anatureVariables)
		{
			anatureVariables.getContext();

			setName(anatureVariables.anatureName);
			setOwnerName(anatureVariables.anatureOwnerName);
			setIsShiny(anatureVariables.anatureIsShiny);
			setSpecies(anatureVariables.anatureSpecies);
			setGender(anatureVariables.anatureGender);
			setPrimaryType(anatureVariables.anaturePrimaryType);
			setSecondaryType(anatureVariables.anatureSecondaryType);
			setMoveSet(anatureVariables.anatureMoveSet);
			setAbility(anatureVariables.anatureAbility);
			setStatus(anatureVariables.anatureStatus);
			setStats(anatureVariables.anatureStats);
			setIndexNumber(anatureVariables.anatureIndexNumber);
			setCatchRate(anatureVariables.anatureCatchRate);
		}

		/*
		 * PACKAGE SETS
		 */

		public IAnature setName(String name)
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

			return this;
		}

		public IAnature setOwnerName(String ownerName)
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

			return this;
		}

		public IAnature setIsShiny(boolean isShiny)
		{
			mIsShiny = isShiny;

			return this;
		}

		public IAnature setSpecies(Species species)
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

			return this;
		}

		public IAnature setGender(Gender gender)
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

			return this;
		}

		public IAnature setPrimaryType(Type primaryType)
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

			return this;
		}

		public IAnature setSecondaryType(Type secondaryType)
		{
			if(secondaryType == null)
			{
				throw new IllegalArgumentException("Passed value \"secondaryType\" was null.");
			}

			mSecondaryType = secondaryType;

			return this;
		}

		public IAnature setMoveSet(MoveSet moveSet)
		{
			if(moveSet == null)
			{
				throw new IllegalArgumentException("Passed value \"moveSet\" was null.");
			}

			if(moveSet.equals(NullMoveSet.getNullMoveSet()))
			{
				throw new IllegalArgumentException("Passed value \"moveSet\" was equivalent to global NullMoveSet please use a clone instead.");
			}

			mMoveSet = moveSet;

			return this;
		}

		public IAnature setAbility(IAbility iAbility)
		{
			if(iAbility == null)
			{
				throw new IllegalArgumentException("Passed value \"ability\" was null.");
			}

			if(iAbility.equals(NullAbility.getNullAbility()))
			{
				throw new IllegalArgumentException("Passed value \"ability\" was equal to the NullAbility ability.");
			}

			mAbility = iAbility;

			return this;
		}

		public IAnature setStatus(StatusEffects statusEffect)
		{
			if(statusEffect == null)
			{
				throw new IllegalArgumentException("Passed value \"statusEffect\" was null.");
			}

			mStatus = statusEffect;

			return this;
		}

		public IAnature setStats(IStats stats)
		{
			if(stats == null)
			{
				throw new IllegalArgumentException("Passed value \"stats\" was null.");
			}

			if(stats.equals(NullStats.getNullStats()))
			{
				throw new IllegalArgumentException("Passed value \"stats\" was equal to the NullStats stats.");
			}

			mStats = stats;

			return this;
		}

		public IAnature setIndexNumber(int indexNumber)
		{
			if(indexNumber < 0)
			{
				throw new IllegalArgumentException("Passed value \"indexNumber\" was below 0.");
			}

			mIndexNumber = indexNumber;

			return this;
		}

		public IAnature setCatchRate(int catchRate)
		{
			if(catchRate < 1
					|| catchRate > 255)
			{
				throw new IllegalArgumentException("Passed value \"catchRate\" was below 1 or above 255.");
			}

			mCatchRate = catchRate;

			return this;
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

		public IStats getStats()
		{
			return mStats;
		}

		public int getIndexNumber()
		{
			return mIndexNumber;
		}

		public int getCatchRate()
		{
			return mCatchRate;
		}

		/*
		 * PUBLIC METHODS
		 */

		public ArrayList<Type> getTypes()
		{
			ArrayList<Type> types = new ArrayList<Type>();

			if(!getPrimaryType().equals(Type.NotSet))
			{
				types.add(getPrimaryType());
			}
			if(!getSecondaryType().equals(Type.NotSet))
			{
				types.add(getSecondaryType());
			}

			return types;
		}

		public void resetTempStats()
		{
			getStats().resetTempStats();
		}

		public void applyDamage(int damage)
		{
			getStats().applyDamage(damage);
		}

		public String applyHeal(int healAmount)
		{
			return getName() + getStats().applyHeal(healAmount);
		}

		public void restore()
		{
			getStats().applyHeal(Integer.MAX_VALUE);
			getMoveSet().refreshAllMovePoints();
		}

		public double getHitPointsPercent()
		{
			return getStats().getHitPointsPercent();
		}

		public IAnature getClone()
		{
			return new Anature(new AnatureVariables()
			{
				@Override
				public void getContext()
				{
					anatureName = getName();
					anatureOwnerName = getOwner();
					anatureIsShiny = isShiny();
					anatureSpecies = getSpecies();
					anatureGender = getGender();
					anaturePrimaryType = getPrimaryType();
					anatureSecondaryType = getSecondaryType();
					anatureMoveSet = getMoveSet().getClone();
					anatureAbility = getAbility();
					anatureStatus = getStatus();
					anatureStats = getStats().getClone()
							.create();
					anatureIndexNumber = getIndexNumber();
					anatureCatchRate = getCatchRate();
				}
			});
		}

		public Image getFrontSprite()
		{
			return new Image(getClass().getResource("/resources/images/anatures/" + getSpecies().toString() + "_Front.png")
					.toExternalForm(), 1000.0, 1000.0, true, false);
		}

		public Image getBackSprite()
		{
			return new Image(getClass().getResource("/resources/images/anatures/" + getSpecies().toString() + "_Back.png")
					.toExternalForm(), 1000.0, 1000.0, true, false);
		}

		public BattleAnimationType getMoveAnimationType(int moveIndex)
		{
			return getMoveSet().getMoveAnimationType(moveIndex);
		}
	}

	public class AnatureExport extends Anature
	{
		public AnatureExport(AnatureVariables anatureVariables)
		{
			Classes.super(anatureVariables);
		}
	}
}
