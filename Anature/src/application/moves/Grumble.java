package application.moves;

import application.Anature;
import application.enums.MoveIds;
import application.enums.Type;

public class Grumble extends Move
{
	private static double mAccuracyStat = 100;
	private static boolean mDoesDamage = false;
	private static boolean mIsPhysicalAttack = false;

	public Grumble()
	{
		super("Grumble", 15, MoveIds.Grumble, mDoesDamage, mIsPhysicalAttack, mAccuracyStat, Type.Normal);
	}

	@Override
	public void activateMove(Anature source, Anature target)
	{
		target.setTempAttack((int) - (target.getAttack() * 0.1)); // TODO Fully Implement
	}
}
