package test;

import application.anatures.AnatureBuilder;
import application.anatures.abillities.NullAbility;
import application.anatures.moves.moves.Tackle;
import application.anatures.movesets.MoveSet;
import application.anatures.stats.StatsBuilder;
import application.enums.Gender;
import application.enums.Species;
import application.enums.StatusEffects;
import application.enums.Type;
import application.enums.stats.LevelingSpeed;
import application.enums.stats.Natures;
import application.interfaces.IAnature;
import application.interfaces.IMove;
import application.interfaces.stats.IStats;

public class TestObjects
{
	private static IMove mTackle = new Tackle();
	
	private static MoveSet mMoveSet = new MoveSet(mTackle, null, null, null);
	
	private static IStats mStats = new StatsBuilder().atLevel(1)
			.withLevlingSpeed(LevelingSpeed.Normal)
			.withNature(Natures.Adamant)
			.withBaseExperience(0)
			.withBaseHitPoints(50)
			.withBaseAttack(50)
			.withBaseDefense(50)
			.withBaseSpecialAttack(50)
			.withBaseSpecialDefense(50)
			.withBaseSpeed(50)
			.withBaseAccuracy(50)
			.withBaseEvasion(50)
			.withIVAttack(0)
			.withIVDefense(0)
			.withIVHitPoints(0)
			.withIVSpecialAttack(0)
			.withIVSpecialDefense(0)
			.withIVSpeed(0)
			.create();
	
	private static IAnature mAnature = new AnatureBuilder().withName("Test Anature")
			.withOwnerName("Test Owner")
			.isShiny(false)
			.withSpecies(Species.Null)
			.withGender(Gender.Trans)
			.withPrimaryType(Type.Normal)
			.withSecondaryType(Type.NotSet)
			.withMoveSet(mMoveSet)
			.withAbility(new NullAbility())
			.withStatus(StatusEffects.None)
			.withStats(mStats)
			.withIndexNumber(20200529)
			.create();
	
	/*
	 * PUBLIC STATIC GETS
	 */
	
	public static IMove getTackle()
	{
		return mTackle;
	}
	
	public static MoveSet getMoveSet()
	{
		return mMoveSet;
	}
	
	public static IStats getStats()
	{
		return mStats;
	}
	
	public static IAnature getAnature()
	{
		return mAnature;
	}
}
