package application.anatures.moves.moves;

import application.anatures.Anature;
import application.anatures.moves.Move;
import application.enums.StatusEffects;

public class Restore extends Move
{
	private static final long serialVersionUID = 3220293979535283251L;

	@Override
	public void activateMove(Anature source, Anature target)
	{
		source.updateStatus(StatusEffects.Sleep);
		source.restore();
	}
}