package application.anatures.moves.moves;

import application.anatures.moves.Move;
import application.enums.StatusEffects;
import application.interfaces.IAnature;

public class Restore extends Move
{
	@Override
	public void activateMove(IAnature source, IAnature target)
	{
		source.updateStatus(StatusEffects.Sleep);
		source.restore();
	}
}