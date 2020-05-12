package application.interfaces.stats;

public interface IStatsLevel extends IStatsTemp
{
	public int getLevelHitPoints();

	public int getLevelAttack();

	public int getLevelDefense();

	public int getLevelSpecialAttack();

	public int getLevelSpecialDefense();

	public int getLevelSpeed();
}
