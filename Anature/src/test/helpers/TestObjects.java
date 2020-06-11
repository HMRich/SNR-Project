package test.helpers;

import application.anatures.Anature;
import application.anatures.AnatureVariables;
import application.anatures.abillities.NullAbility;
import application.anatures.moves.moves.NullMove;
import application.anatures.moves.moves.Tackle;
import application.anatures.movesets.MoveSet;
import application.anatures.stats.StatsBuilder;
import application.enums.AbilityIds;
import application.enums.Gender;
import application.enums.Species;
import application.enums.StatusEffects;
import application.enums.Type;
import application.enums.stats.LevelingSpeed;
import application.enums.stats.Natures;
import application.interfaces.IAbility;
import application.interfaces.IMove;
import application.interfaces.stats.IStats;
import application.pools.AbilityPool;

public class TestObjects
{
	/*
	 * TEST OBJECT IMove
	 */

	private static IMove mTackle;

	public static IMove getDefaultTackle()
	{
		if(mTackle == null)
		{
			mTackle = new Tackle();
		}

		return mTackle;
	}

	/*
	 * TEST OBJECT MoveSet
	 */

	private static MoveSet mMoveSet;

	public static MoveSet getDefaultMoveSet()
	{
		if(mMoveSet == null)
		{
			mMoveSet = new MoveSet(getDefaultTackle(), NullMove.getNullMove(), NullMove.getNullMove(), NullMove.getNullMove());
		}

		return mMoveSet;
	}

	/*
	 * TEST OBJECT IAbility
	 */

	private static IAbility mSpiky;

	public static IAbility getDefaultSpiky()
	{
		if(mSpiky == null)
		{
			mSpiky = AbilityPool.getAbility(AbilityIds.Spiky);
		}

		return mSpiky;
	}

	/*
	 * TEST OBJECT IStats
	 */

	private static int mDefaultLevel = 1;
	private static int mDefaultBaseStat = 50;
	private static int mDefaultBaseNonStat = 0;
	private static LevelingSpeed mDefaultLevelingSpeed = LevelingSpeed.Normal;
	private static Natures mDefaultNaure = Natures.Hardy;

	private static IStats mStats = new StatsBuilder().atLevel(getDefaultLevel()).withLevlingSpeed(getDefaultLevelingSpeed()).withNature(getDefaultNature())
			.withBaseExperience(getDefaultBaseNonStat()).withBaseHitPoints(getDefaultBaseStat()).withBaseAttack(getDefaultBaseStat())
			.withBaseDefense(getDefaultBaseStat()).withBaseSpecialAttack(getDefaultBaseStat()).withBaseSpecialDefense(getDefaultBaseStat())
			.withBaseSpeed(getDefaultBaseStat()).withBaseAccuracy(getDefaultBaseStat()).withBaseEvasion(getDefaultBaseStat())
			.withIVAttack(getDefaultBaseNonStat()).withIVDefense(getDefaultBaseNonStat()).withIVHitPoints(getDefaultBaseNonStat())
			.withIVSpecialAttack(getDefaultBaseNonStat()).withIVSpecialDefense(getDefaultBaseNonStat()).withIVSpeed(getDefaultBaseNonStat()).create();

	public static IStats getDefaultStats()
	{
		return mStats;
	}

	public static int getDefaultLevel()
	{
		return mDefaultLevel;
	}

	public static int getDefaultBaseStat()
	{
		return mDefaultBaseStat;
	}

	public static int getDefaultBaseNonStat()
	{
		return mDefaultBaseNonStat;
	}

	public static LevelingSpeed getDefaultLevelingSpeed()
	{
		return mDefaultLevelingSpeed;
	}

	public static Natures getDefaultNature()
	{
		return mDefaultNaure;
	}

	/*
	 * TEST OBJECT IAnature
	 */

	private static String mDefaultAnatureName = "Test Anature";
	private static String mDefaultOwnerName = "Test Owner";
	private static boolean mDefaultShinyValue = false;
	private static Species mDefaultSpecies = Species.Null;
	private static Gender mDefaultGender = Gender.Trans;
	private static Type mDefaultPrimaryType = Type.Normal;
	private static Type mDefaultSecondaryType = Type.NotSet;
	private static IAbility mDefaultAbility;
	private static StatusEffects mDefaultStatusEffect = StatusEffects.None;
	private static int mDefaultIndexNumber = 20200529;
	private static int mDefaultCatchRate = 255;

	private static Anature mAnature = new Anature(getDefaultAnatureVariables());

	public static Anature getAnature()
	{
		return mAnature.getClone();
	}

	public static AnatureVariables getDefaultAnatureVariables()
	{
		return new AnatureVariables()
		{
			@Override
			public void getVariables()
			{
				anatureName = getDefaultAnatureName();
				anatureOwnerName = getDefaultOwnerName();
				anatureIsShiny = getDefaultShinyValue();
				anatureSpecies = getDefaultSpecies();
				anatureGender = getDefaultGender();
				anaturePrimaryType = getDefaultPrimaryType();
				anatureSecondaryType = getDefaultSecondaryType();
				anatureMoveSet = getDefaultMoveSet().getClone();
				anatureAbility = getDefaultAbility();
				anatureStatus = getDefaultStatusEffect();
				anatureStats = getDefaultStats().getClone().create();
				anatureIndexNumber = getDefaultIndexNumber();
				anatureCatchRate = getDefaultCatchRate();
			}
		};
	}

	public static String getDefaultAnatureName()
	{
		return mDefaultAnatureName;
	}

	public static String getDefaultOwnerName()
	{
		return mDefaultOwnerName;
	}

	public static boolean getDefaultShinyValue()
	{
		return mDefaultShinyValue;
	}

	public static Species getDefaultSpecies()
	{
		return mDefaultSpecies;
	}

	public static Gender getDefaultGender()
	{
		return mDefaultGender;
	}

	public static Type getDefaultPrimaryType()
	{
		return mDefaultPrimaryType;
	}

	public static Type getDefaultSecondaryType()
	{
		return mDefaultSecondaryType;
	}

	public static IAbility getDefaultAbility()
	{
		if(mDefaultAbility == null)
		{
			mDefaultAbility = new NullAbility();
		}

		return mDefaultAbility;
	}

	public static StatusEffects getDefaultStatusEffect()
	{
		return mDefaultStatusEffect;
	}

	public static int getDefaultIndexNumber()
	{
		return mDefaultIndexNumber;
	}

	public static int getDefaultCatchRate()
	{
		return mDefaultCatchRate;
	}

}
