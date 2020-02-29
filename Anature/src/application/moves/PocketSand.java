package application.moves;

import application.Anature;
import application.enums.MoveIds;
import application.enums.Type;

public class PocketSand extends Move
{

	public PocketSand(String name, int totalMovePoints, MoveIds moveId, boolean doesDamage, boolean isPhysicalAttack, double accuracyStat, Type enumType)
	{
		super("Pocket Sand", 15, MoveIds.Pocket_Sand , false, false, 100, Type.Ground);
	}

	@Override
	public void activateMove(Anature source, Anature target)
	{
		target.setAccuracy(target.getAccuracy() - 10);
	}

}
