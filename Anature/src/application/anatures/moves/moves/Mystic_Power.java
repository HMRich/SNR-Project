package application.anatures.moves.moves;

import application.anatures.moves.Move;
import application.interfaces.IAnature;

public class Mystic_Power extends Move
{
	private static final long serialVersionUID = 1170426260117785279L;

	@Override
	public void activateMove(IAnature source, IAnature target)
	{
		source.getStats().increaseTempDefense();
		source.getStats().increaseTempSpecialDefense();
	}
}