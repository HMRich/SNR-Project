package application.anatures.moves.moves;

import application.anatures.moves.Move;
import application.interfaces.IAnature;

public class SharpenUp extends Move
{
	public void activateMove(IAnature source, IAnature target)
	{
		source.getStats().increaseTempAttack();
		source.getStats().increaseTempSpecialAttack();
	}
}