package test;

import application.anatures.Anature;
import application.anatures.AnatureVariables;
import application.anatures.abillities.NullAbility;
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

	private static final IMove mTackle = new Tackle();

	public static final IMove getDefaultTackle()
	{
		return mTackle;
	}

	/*
	 * TEST OBJECT MoveSet
	 */

	private static final MoveSet mMoveSet = new MoveSet(getDefaultTackle(), getDefaultTackle(), getDefaultTackle(), getDefaultTackle());

	public static final MoveSet getDefaultMoveSet()
	{
		return mMoveSet;
	}

	/*
	 * TEST OBJECT IAbility
	 */

	private static final IAbility mSpiky = AbilityPool.getAbility(AbilityIds.Spiky);

	public static final IAbility getDefaultSpiky()
	{
		return mSpiky;
	}

	/*
	 * TEST OBJECT IStats
	 */

	private static final int mDefaultLevel = 1;
	private static final int mDefaultBaseStat = 50;
	private static final int mDefaultBaseNonStat = 0;
	private static final LevelingSpeed mDefaultLevelingSpeed = LevelingSpeed.Normal;
	private static final Natures mDefaultNaure = Natures.Hardy;

	private static final IStats mStats = new StatsBuilder().atLevel(getDefaultLevel())
			.withLevlingSpeed(getDefaultLevelingSpeed())
			.withNature(getDefaultNature())
			.withBaseExperience(getDefaultBaseNonStat())
			.withBaseHitPoints(getDefaultBaseStat())
			.withBaseAttack(getDefaultBaseStat())
			.withBaseDefense(getDefaultBaseStat())
			.withBaseSpecialAttack(getDefaultBaseStat())
			.withBaseSpecialDefense(getDefaultBaseStat())
			.withBaseSpeed(getDefaultBaseStat())
			.withBaseAccuracy(getDefaultBaseStat())
			.withBaseEvasion(getDefaultBaseStat())
			.withIVAttack(getDefaultBaseNonStat())
			.withIVDefense(getDefaultBaseNonStat())
			.withIVHitPoints(getDefaultBaseNonStat())
			.withIVSpecialAttack(getDefaultBaseNonStat())
			.withIVSpecialDefense(getDefaultBaseNonStat())
			.withIVSpeed(getDefaultBaseNonStat())
			.create();

	public static final IStats getDefaultStats()
	{
		return mStats.getClone()
				.create();
	}

	public static final int getDefaultLevel()
	{
		return mDefaultLevel;
	}

	public static final int getDefaultBaseStat()
	{
		return mDefaultBaseStat;
	}

	public static final int getDefaultBaseNonStat()
	{
		return mDefaultBaseNonStat;
	}

	public static final LevelingSpeed getDefaultLevelingSpeed()
	{
		return mDefaultLevelingSpeed;
	}

	public static final Natures getDefaultNature()
	{
		return mDefaultNaure;
	}

	/*
	 * TEST OBJECT Anature
	 */

	private static final String mDefaultAnatureName = "Test Anature";
	private static final String mDefaultOwnerName = "Test Owner";
	private static final boolean mDefaultShinyValue = false;
	private static final Species mDefaultSpecies = Species.Null;
	private static final Gender mDefaultGender = Gender.Trans;
	private static final Type mDefaultPrimaryType = Type.Normal;
	private static final Type mDefaultSecondaryType = Type.NotSet;
	private static final IAbility mDefaultAbility = new NullAbility();
	private static final StatusEffects mDefaultStatusEffect = StatusEffects.None;
	private static final int mDefaultIndexNumber = 20200529;
	private static final int mDefaultCatchRate = 255;

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

	public static final Anature getAnature()
	{
		return new Anature(getDefaultAnatureVariables());
	}

	public static final String getDefaultAnatureName()
	{
		return mDefaultAnatureName;
	}

	public static final String getDefaultOwnerName()
	{
		return mDefaultOwnerName;
	}

	public static final boolean getDefaultShinyValue()
	{
		return mDefaultShinyValue;
	}

	public static final Species getDefaultSpecies()
	{
		return mDefaultSpecies;
	}

	public static final Gender getDefaultGender()
	{
		return mDefaultGender;
	}

	public static final Type getDefaultPrimaryType()
	{
		return mDefaultPrimaryType;
	}

	public static final Type getDefaultSecondaryType()
	{
		return mDefaultSecondaryType;
	}

	public static final IAbility getDefaultAbility()
	{
		return mDefaultAbility;
	}

	public static final StatusEffects getDefaultStatusEffect()
	{
		return mDefaultStatusEffect;
	}

	public static final int getDefaultIndexNumber()
	{
		return mDefaultIndexNumber;
	}

	public static final int getDefaultCatchRate()
	{
		return mDefaultCatchRate;
	}
}
