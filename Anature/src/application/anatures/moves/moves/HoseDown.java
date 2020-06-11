package application.anatures.moves.moves;

import application.anatures.moves.Move;
import application.interfaces.IAnature;

public class HoseDown extends Move
{
	private static final long serialVersionUID = 949985992676131187L;

	@Override
	public void activateMove(IAnature source, IAnature target)
	{
		target.getStats().decreaseTempDefense();
		target.getStats().decreaseTempSpecialDefense();
	}
}
