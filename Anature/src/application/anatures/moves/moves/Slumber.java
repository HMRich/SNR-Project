package application.anatures.moves.moves;

import application.anatures.moves.Move;
import application.enums.StatusEffects;
import application.interfaces.IAnature;

public class Slumber extends Move
{
	private static final long serialVersionUID = -6854912723960123165L;

	@Override
	public void activateMove(IAnature source, IAnature target)
	{
		target.updateStatus(StatusEffects.Sleep);
	}
}