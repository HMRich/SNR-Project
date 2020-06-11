package application.anatures.moves.moves;

import application.anatures.Anature;
import application.anatures.moves.Move;

public class Mystic_Power extends Move
{
	private static final long serialVersionUID = 1170426260117785279L;

	@Override
	public void activateMove(Anature source, Anature target)
	{
		source.getStats().increaseTempDefense();
		source.getStats().increaseTempSpecialDefense();
	}
}