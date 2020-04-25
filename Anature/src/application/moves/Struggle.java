package application.moves;

import application.Anature;
import application.enums.MoveIds;
import application.enums.Type;

public class Struggle extends Move // TODO Rename to flail
{
	private static double mAccuracyStat = 100;
	private static boolean mDoesDamage = true;
	private static boolean mIsPhysicalAttack = true;

	public Struggle()
	{
		super("Struggle", 999, MoveIds.Struggle, mDoesDamage, mIsPhysicalAttack, mAccuracyStat, Type.Normal);
	}

	@Override
	public void activateMove(Anature source, Anature target)
	{
		source.takeDamage(10);
		target.takeDamage(20);
	}
}
