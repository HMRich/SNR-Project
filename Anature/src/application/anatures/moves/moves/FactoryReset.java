package application.anatures.moves.moves;

import application.anatures.moves.Move;
import application.enums.StatusEffects;
import application.interfaces.IAnature;

public class FactoryReset extends Move
{
	public void activateMove(IAnature source, IAnature target)
	{
		source.getStats().resetTempStats();
		source.setStatus(StatusEffects.None);
	}
}
