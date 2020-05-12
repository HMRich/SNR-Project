package application.interfaces.stats;

public interface IStatsTemp
{
	/*
	 * PUBLIC GETS
	 */
	
	public int getTempAttack();

	public int getTempDefense();

	public int getTempSpecialAttack();

	public int getTempSpecialDefense();

	public int getTempSpeed();

	public int getTempAccuracy();

	public int getTempEvasion();
	
	/*
	 * PUBLIC METHODS
	 */
	
	public void resetTempStats();
}
