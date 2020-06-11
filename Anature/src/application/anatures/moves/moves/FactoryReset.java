package application.anatures.moves.moves;

import application.anatures.moves.Move;
import application.enums.StatusEffects;
import application.interfaces.IAnature;

public class FactoryReset extends Move
{
	private static final long serialVersionUID = 4892000699183882732L;

	@Override
	public void activateMove(IAnature source, IAnature target)
	{
		source.getStats().resetTempStats();
		source.updateStatus(StatusEffects.None);
	}
}
