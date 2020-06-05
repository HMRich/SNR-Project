package application.anatures.moves.moves;

import application.anatures.Anature;
import application.anatures.moves.Move;
import application.enums.StatusEffects;

public class FactoryReset extends Move
{
	public void activateMove(Anature source, Anature target)
	{
		source.getStats().resetTempStats();
		source.setStatus(StatusEffects.None);
	}
}
