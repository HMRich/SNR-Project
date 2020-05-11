package application.interfaces;

import application.enums.LevelingSpeed;
import application.enums.Natures;

public interface IStats
{
	/*
	 * StatsCore METHoDS
	 */

	public int getLevel();

	public int getExperiencePoints();

	public LevelingSpeed getLevelingSpeed();

	public Natures getNature();

	public int getTotalAttack();

	public int getTotalDefense();

	public int getTotalSpecialAttack();

	public int getTotalSpecialDefense();

	public int getTotalSpeed();

	public int getTotalAccuracy();

	public int getTotalEvasion();

	public void addExperience(int expeienceGain);

	/*
	 * StatsBase METHODS
	 */

	public int getBaseHitPoints();

	public int getBaseAttack();

	public int getBaseDefense();

	public int getBaseSpecialAttack();

	public int getBaseSpecialDefense();

	public int getBaseSpeed();

	public int getBaseAccuracy();

	public int getBaseEvasion();

	/*
	 * StatsIV METHODS
	 */

	public int getIVHitPoints();

	public int getIVAttack();

	public int getIVDefense();

	public int getIVSpecialAttack();

	public int getIVSpecialDefnese();

	public int getIVSpeed();

	/*
	 * StatsEV METHODS
	 */

	public int getEVHitPoints();

	public int getEVAttack();

	public int getEVDefense();

	public int getEVSpecialAttack();

	public int getEVSpecialDefense();

	public int getEVSpeed();

	public int getEVHitPointsReduced();

	public int getEVAttackReduced();

	public int getEVDefenseReduced();

	public int getEVSpecialAttackReduced();

	public int getEVSpecialDefenseReduced();

	public int getEVSpeedReduced();

	/*
	 * StatsLevel METHODS
	 */

	public int getLevelHitPoints();

	public int getLevelAttack();

	public int getLevelDefense();

	public int getLevelSpecialAttack();

	public int getLevelSpecialDefense();

	public int getLevelSpeed();

	/*
	 * StatsTemp METHODS
	 */

	public int getTempAttack();

	public int getTempDefense();

	public int getTempSpecialAttack();

	public int getTempSpecialDefense();

	public int getTempSpeed();

	public int getTempAccuracy();

	public int getTempEvasion();
}
