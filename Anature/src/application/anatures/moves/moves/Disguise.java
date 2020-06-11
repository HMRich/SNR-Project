package application.anatures.moves.moves;

import application.anatures.Anature;
import application.anatures.moves.Move;

public class Disguise extends Move
{
	private static final long serialVersionUID = -1344454455290804300L;

	@Override
	public void activateMove(Anature source, Anature target)
	{
		source.getStats().increaseTempEvasion();
	}
}
