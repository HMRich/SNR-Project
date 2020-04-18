package application.moves;

import application.Anature;
import application.enums.MoveIds;
import application.enums.Type;

public class Tackle extends Move
{
	private static int mDamageDone = 30;
	private static int mTotalMp = 25;
	private static double mAccuracyStat = 75;
	private static boolean mDoesDamage = true;
	private static boolean mIsPhysicalAttack = true;

	public Tackle()
	{
		super("Tackle", mTotalMp, MoveIds.Tackle, mDoesDamage, mIsPhysicalAttack, mAccuracyStat, Type.Normal);
	}

	@Override
	public void activateMove(Anature source, Anature target)
	{
		target.takeDamage(mDamageDone);
	}
}
