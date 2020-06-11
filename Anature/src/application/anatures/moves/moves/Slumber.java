package application.anatures.moves.moves;

import application.anatures.Anature;
import application.anatures.moves.Move;
import application.enums.StatusEffects;

public class Slumber extends Move
{
	private static final long serialVersionUID = -6854912723960123165L;

	@Override
	public void activateMove(Anature source, Anature target)
	{
		target.updateStatus(StatusEffects.Sleep);
	}
}