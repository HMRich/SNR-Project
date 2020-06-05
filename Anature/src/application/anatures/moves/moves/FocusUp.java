package application.anatures.moves.moves;

import application.anatures.Anature;
import application.anatures.moves.Move;

public class FocusUp extends Move
{
	public void activateMove(Anature source, Anature target)
	{
		source.getStats().increaseTempAttack();
		source.getStats().increaseTempDefense();
	}
}
