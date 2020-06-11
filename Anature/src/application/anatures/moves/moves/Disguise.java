package application.anatures.moves.moves;

import application.anatures.moves.Move;
import application.interfaces.IAnature;

public class Disguise extends Move
{
	private static final long serialVersionUID = -1344454455290804300L;

	@Override
	public void activateMove(IAnature source, IAnature target)
	{
		source.getStats().increaseTempEvasion();
	}
}
