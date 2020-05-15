package application.interfaces.stats;

import application.enums.TempStatsStages;

public interface IStatsTemp
{
	/*
	 * PUBLIC GETS
	 */

	public TempStatsStages getTempAttack();

	public TempStatsStages getTempDefense();

	public TempStatsStages getTempSpecialAttack();

	public TempStatsStages getTempSpecialDefense();

	public TempStatsStages getTempSpeed();

	public TempStatsStages getTempAccuracy();

	public TempStatsStages getTempEvasion();

	/*
	 * PUBLIC METHODS
	 */

	public void resetTempStats();

	public void increaseTempAttack();

	public void decreaseTempAttack();

	public void increaseTempDefense();

	public void decreaseTempDefense();

	public void increaseTempSpecialAttack();

	public void decreaseTempSpecialAttack();

	public void increaseTempSpecialDefense();

	public void decreaseTempSpecialDefense();

	public void increaseTempSpeed();

	public void decreaseTempSpeed();

	public void increaseTempAccuracy();

	public void decreaseTempAccuracy();

	public void increaseTempEvaion();

	public void decreaseTempEvaion();

}
