package application.moves;

import application.Anature;
import application.enums.MoveIds;
import application.enums.Type;

public class PocketSand extends Move
{
	private static int mTotalMp = 25;
	
	public PocketSand()
	{
		super("Pocket Sand", mTotalMp, MoveIds.Pocket_Sand , false, false, 100, Type.Ground);
	}

	@Override
	public void activateMove(Anature source, Anature target)
	{
		target.setAccuracy(target.getAccuracy() - 10);
	}

}
