package application.anatures.moves.moves;

import application.anatures.Anature;
import application.anatures.moves.Move;
import application.interfaces.stats.IStats;

public class Upgrade extends Move
{
	private static final long serialVersionUID = -6248044979167831318L;

	@Override
	public void activateMove(Anature source, Anature target)
	{
		IStats stats = source.getStats();
		double maxStageValue = calculateMaxStageValue(stats);

		if(stats.getTempAttack().getModifier() < maxStageValue)
		{
			stats.increaseTempAttack();
		}

		if(stats.getTempDefense().getModifier() < maxStageValue)
		{
			stats.increaseTempDefense();
		}

		if(stats.getTempSpecialAttack().getModifier() < maxStageValue)
		{
			stats.increaseTempSpecialAttack();
		}

		if(stats.getTempSpecialDefense().getModifier() < maxStageValue)
		{
			stats.increaseTempSpecialDefense();
		}
	}

	private double calculateMaxStageValue(IStats stats)
	{
		int level = stats.getLevel();

		if(level < 20)
		{
			return 2.0;
		}

		else if(level < 40)
		{
			return 2.5;
		}

		else if(level < 80)
		{
			return 3.5;
		}

		return 4.0;
	}
}
