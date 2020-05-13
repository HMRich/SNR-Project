package application.anatures.stats;

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
	public int getIVSpecialDefnese()
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
	public int getTempAttack()
	{
		return -1;
	}

	@Override
	public int getTempDefense()
	{
		return -1;
	}

	@Override
	public int getTempSpecialAttack()
	{
		return -1;
	}

	@Override
	public int getTempSpecialDefense()
	{
		return -1;
	}

	@Override
	public int getTempSpeed()
	{
		return -1;
	}

	@Override
	public int getTempAccuracy()
	{
		return -1;
	}

	@Override
	public int getTempEvasion()
	{
		return -1;
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
	public void addExperience(int expeienceGain)
	{

	}

	@Override
	public void adjustAttack(double attackAdjustment)
	{

	}

	@Override
	public void adjustDefense(double attackAdjustment)
	{

	}

	@Override
	public void adjustSpecialAttack(double attackAdjustment)
	{

	}

	@Override
	public void adjustSpecialDefense(double attackAdjustment)
	{

	}

	@Override
	public void adjustSpeed(double attackAdjustment)
	{

	}

	@Override
	public void adjustAccuracy(double attackAdjustment)
	{

	}

	@Override
	public void adjustEvasion(double attackAdjustment)
	{

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

}
