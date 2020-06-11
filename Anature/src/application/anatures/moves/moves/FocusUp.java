package application.anatures.moves.moves;

import application.anatures.Anature;
import application.anatures.moves.Move;

public class FocusUp extends Move
{
	private static final long serialVersionUID = -167621720002994454L;

	@Override
	public void activateMove(IAnature source, IAnature target)
	{
		source.getStats().increaseTempAttack();
		source.getStats().increaseTempDefense();
	}
}
