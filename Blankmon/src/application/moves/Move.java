package application.moves;

import application.Anature;
import application.enums.MoveIds;
import application.enums.Type;

public abstract class Move
{
	private int mTotalMovePoints;
	private MoveIds mMoveId;
	private boolean mDoesDamage;
	private boolean mIsPhysicalAttack;
	private double mAccuracyStat;
	private Type mType;

	public Move(int totalMovePoints, MoveIds moveId, boolean DoesDamage, boolean isPhysicalAttack, double AccuracyStat, Type enumType)
	{
		mTotalMovePoints = totalMovePoints;
		mMoveId = moveId;
		mDoesDamage = DoesDamage;
		mIsPhysicalAttack = isPhysicalAttack;
		mAccuracyStat = AccuracyStat;
		mType = enumType;
				
	}

	public int getTotalMovePoints()
	{
		return mTotalMovePoints;
	}

	public MoveIds getMoveId()
	{
		return mMoveId;
	}

	public boolean getDoesDamage()
	{
		return mDoesDamage;
	}

	public abstract void activateMove(Anature source, Anature target);
}