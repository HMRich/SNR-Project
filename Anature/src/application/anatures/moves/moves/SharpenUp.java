package application.anatures.moves.moves;

import application.anatures.moves.Move;
import application.interfaces.IAnature;

public class SharpenUp extends Move
{
	private static final long serialVersionUID = -7594705633399116810L;

	@Override
	public void activateMove(IAnature source, IAnature target)
	{
		source.getStats().increaseTempAttack();
		source.getStats().increaseTempSpecialAttack();
	}
}
