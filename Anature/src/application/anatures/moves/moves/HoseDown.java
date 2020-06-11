package application.anatures.moves.moves;

import application.anatures.Anature;
import application.anatures.moves.Move;

public class HoseDown extends Move
{
	private static final long serialVersionUID = 949985992676131187L;

	@Override
	public void activateMove(Anature source, Anature target)
	{
		target.getStats().decreaseTempDefense();
		target.getStats().decreaseTempSpecialDefense();
	}
}
