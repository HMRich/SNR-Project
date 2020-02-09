package application;

import application.enums.MoveIds;

public abstract class Move
{

	private int mTotalMovePoints;
	private MoveIds mMoveId;
	private boolean mDoesDamage;

	public Move(int totalMovePoints, MoveIds moveId, boolean DoesDamage)
	{
		mTotalMovePoints = totalMovePoints;
		mMoveId = moveId;
		mDoesDamage = DoesDamage;
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