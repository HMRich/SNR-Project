package application.anatures.stats;

import application.enums.TempStatsStages;
import application.enums.stats.LevelingSpeed;
import application.enums.stats.Natures;
import application.interfaces.stats.IStats;

public class NullStats implements IStats
{
	private static IStats mNullStats = new NullStats();

	public static IStats getNullStats()
	{
		return mNullStats;
	}

	@Override
	public int getBaseExperience()
	{
		return -1;
	}

	@Override
	public int getBaseHitPoints()
	{
		return -1;
	}

	@Override
	public int getBaseAttack()
	{
		return -1;
	}

	@Override
	public int getBaseDefense()
	{
		return -1;
	}

	@Override
	public int getBaseSpecialAttack()
	{
		return -1;
	}

	@Override
	public int getBaseSpecialDefense()
	{
		return -1;
	}

	@Override
	public int getBaseSpeed()
	{
		return -1;
	}

	@Override
	public int getBaseAccuracy()
	{
		return -1;
	}

	@Override
	public int getBaseEvasion()
	{
		return -1;
	}

	@Override
	public int getIVHitPoints()
	{
		return -1;
	}

	@Override
	public int getIVAttack()
	{
		return -1;
	}

	@Override
	public int getIVDefense()
	{
		return -1;
	}

	@Override
	public int getIVSpecialAttack()
	{
		return -1;
	}

	@Override
	public int getIVSpecialDefense()
	{
		return -1;
	}

	@Override
	public int getIVSpeed()
	{
		return -1;
	}

	@Override
	public int getEVHitPoints()
	{
		return -1;
	}

	@Override
	public int getEVAttack()
	{
		return -1;
	}

	@Override
	public int getEVDefense()
	{
		return -1;
	}

	@Override
	public int getEVSpecialAttack()
	{
		return -1;
	}

	@Override
	public int getEVSpecialDefense()
	{
		return -1;
	}

	@Override
	public int getEVSpeed()
	{
		return -1;
	}

	@Override
	public int getEVHitPointsReduced()
	{
		return -1;
	}

	@Override
	public int getEVAttackReduced()
	{
		return -1;
	}

	@Override
	public int getEVDefenseReduced()
	{
		return -1;
	}

	@Override
	public int getEVSpecialAttackReduced()
	{
		return -1;
	}

	@Override
	public int getEVSpecialDefenseReduced()
	{
		return -1;
	}

	@Override
	public int getEVSpeedReduced()
	{
		return -1;
	}

	@Override
	public int getLevelHitPoints()
	{
		return -1;
	}

	@Override
	public int getLevelAttack()
	{
		return -1;
	}

	@Override
	public int getLevelDefense()
	{
		return -1;
	}

	@Override
	public int getLevelSpecialAttack()
	{
		return -1;
	}

	@Override
	public int getLevelSpecialDefense()
	{
		return -1;
	}

	@Override
	public int getLevelSpeed()
	{
		return -1;
	}

	@Override
	public TempStatsStages getTempAttack()
	{
		return TempStatsStages.zero;
	}

	@Override
	public TempStatsStages getTempDefense()
	{
		return TempStatsStages.zero;
	}

	@Override
	public TempStatsStages getTempSpecialAttack()
	{
		return TempStatsStages.zero;
	}

	@Override
	public TempStatsStages getTempSpecialDefense()
	{
		return TempStatsStages.zero;
	}

	@Override
	public TempStatsStages getTempSpeed()
	{
		return TempStatsStages.zero;
	}

	@Override
	public TempStatsStages getTempAccuracy()
	{
		return TempStatsStages.zero;
	}

	@Override
	public TempStatsStages getTempEvasion()
	{
		return TempStatsStages.zero;
	}

	@Override
	public void resetTempStats()
	{

	}

	@Override
	public int getLevel()
	{
		return -1;
	}

	@Override
	public int getCurrentHitPoints()
	{
		return -1;
	}

	@Override
	public LevelingSpeed getLevelingSpeed()
	{
		return null;
	}

	@Override
	public Natures getNature()
	{
		return null;
	}

	@Override
	public int getTotalHitPoints()
	{
		return -1;
	}

	@Override
	public int getTotalAttack()
	{
		return -1;
	}

	@Override
	public int getTotalDefense()
	{
		return -1;
	}

	@Override
	public int getTotalSpecialAttack()
	{
		return -1;
	}

	@Override
	public int getTotalSpecialDefense()
	{
		return -1;
	}

	@Override
	public int getTotalSpeed()
	{
		return -1;
	}

	@Override
	public int getTotalAccuracy()
	{
		return -1;
	}

	@Override
	public int getTotalEvasion()
	{
		return -1;
	}

	@Override
	public void setCurrentHitPoints(int hitPoints)
	{

	}

	@Override
	public int addExperience(int expeienceGain)
	{
		return -1;
	}

	@Override
	public int getTotalExperiencePoints()
	{
		return -1;
	}

	@Override
	public int getExperienceProgression()
	{
		return -1;
	}

	@Override
	public int getRequiredExperience()
	{
		return -1;
	}

	@Override
	public void increaseTempAttack()
	{

	}

	@Override
	public void decreaseTempAttack()
	{

	}

	@Override
	public void increaseTempDefense()
	{

	}

	@Override
	public void decreaseTempDefense()
	{

	}

	@Override
	public void increaseTempSpecialAttack()
	{

	}

	@Override
	public void decreaseTempSpecialAttack()
	{

	}

	@Override
	public void increaseTempSpecialDefense()
	{

	}

	@Override
	public void decreaseTempSpecialDefense()
	{

	}

	@Override
	public void increaseTempSpeed()
	{

	}

	@Override
	public void decreaseTempSpeed()
	{

	}

	@Override
	public void increaseTempAccuracy()
	{

	}

	@Override
	public void decreaseTempAccuracy()
	{

	}

	@Override
	public void increaseTempEvasion()
	{

	}

	@Override
	public void decreaseTempEvaion()
	{

	}

	@Override
	public int healAnature(int healAmount)
	{
		return -1;
	}

}
