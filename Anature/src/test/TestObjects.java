package test;

import application.anatures.AnatureBuilder;
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
import application.interfaces.IAnature;
import application.interfaces.IMove;
import application.interfaces.stats.IStats;
import application.pools.AbilityPool;

public class TestObjects
{
	/*
	 * TEST OBJECT IMove
	 */

	private static IMove mTackle = new Tackle();

	public static IMove getDefaultTackle()
	{
		return mTackle;
	}

	/*
	 * TEST OBJECT MoveSet
	 */

	private static MoveSet mMoveSet = new MoveSet(getDefaultTackle(), null, null, null);

	public static MoveSet getDefaultMoveSet()
	{
		return mMoveSet;
	}

	/*
	 * TEST OBJECT IAbility
	 */

	private static IAbility mSpiky = AbilityPool.getAbility(AbilityIds.Spiky);

	public static IAbility getDefaultSpiky()
	{
		return mSpiky;
	}

	/*
	 * TEST OBJECT IStats
	 */

	private static int mDefaultLevel = 1;
	private static int mDefaultBaseStat = 50;
	private static int mDefaultBaseNonStat = 0;
	private static LevelingSpeed mDefaultLevelingSpeed = LevelingSpeed.Normal;
	private static Natures mDefaultNaure = Natures.Adamant;

	private static IStats mStats = new StatsBuilder().atLevel(getDefaultLevel())
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
	private static IAbility mDefaultAbility = new NullAbility();
	private static StatusEffects mDefaultStatusEffect = StatusEffects.None;
	private static int mDefaultIndexNumber = 20200529;

	private static IAnature mAnature = new AnatureBuilder().withName(getDefaultAnatureName())
			.withOwnerName(getDefaultOwnerName())
			.isShiny(getDefaultShinyValue())
			.withSpecies(getDefaultSpecies())
			.withGender(getDefaultGender())
			.withPrimaryType(getDefaultPrimaryType())
			.withSecondaryType(getDefaultSecondaryType())
			.withMoveSet(getDefaultMoveSet())
			.withAbility(getDefaultAbility())
			.withStatus(getDefaultStatusEffect())
			.withStats(getDefaultStats())
			.withIndexNumber(getDefaultIndexNumber())
			.create();

	public static IAnature getAnature()
	{
		return mAnature;
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

}
