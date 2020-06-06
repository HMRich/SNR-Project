package application.anatures.moves.moves;

import application.anatures.moves.Move;
import application.interfaces.IAnature;

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
