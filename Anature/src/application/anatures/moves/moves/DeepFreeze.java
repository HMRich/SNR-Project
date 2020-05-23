package application.anatures.moves.moves;

import application.anatures.moves.Move;
import application.interfaces.IAnature;

public class DeepFreeze extends Move
{
	@Override
	public void activateMove(IAnature source, IAnature target)
	{
		target.getStats().decreaseTempSpeed();
		target.getStats().decreaseTempSpeed();
		target.getStats().decreaseTempDefense();
		target.getStats().decreaseTempSpecialDefense();
		
		source.getStats().decreaseTempAttack();
		source.getStats().decreaseTempAttack();
		source.getStats().decreaseTempDefense();
		source.getStats().decreaseTempDefense();
	}
}
