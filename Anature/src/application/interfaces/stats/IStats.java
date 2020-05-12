package application.interfaces.stats;

import application.enums.stats.LevelingSpeed;
import application.enums.stats.Natures;

public interface IStats extends IStatsBase
{
	/*
	 * PUBLIC GETS
	 */
	
	public int getLevel();

	public int getExperiencePoints();
	
	public int getCurrentHitPoints();

	public LevelingSpeed getLevelingSpeed();

	public Natures getNature();
	
	public int getTotalHitPoints();

	public int getTotalAttack();

	public int getTotalDefense();

	public int getTotalSpecialAttack();

	public int getTotalSpecialDefense();

	public int getTotalSpeed();

	public int getTotalAccuracy();

	public int getTotalEvasion();
	
	/*
	 * PUBLIC SETS
	 */
	
	public void setCurrentHitPoints(int hitPoints);
	
	/*
	 * PUBLIC METHODS
	 */

	public void addExperience(int expeienceGain);
	
	public void adjustAttack(double attackAdjustment);
	
	public void adjustDefense(double attackAdjustment);
	
	public void adjustSpecialAttack(double attackAdjustment);
	
	public void adjustSpecialDefense(double attackAdjustment);
	
	public void adjustSpeed(double attackAdjustment);
	
	public void adjustAccuracy(double attackAdjustment);
	
	public void adjustEvasion(double attackAdjustment);
}
