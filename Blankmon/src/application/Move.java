package application;

import application.enums.MoveIds;

public abstract class Move {

	private int mTotalMovePoints;
	private MoveIds mMoveId;

	public Move(int totalMovePoints, MoveIds moveId) {
		mTotalMovePoints = totalMovePoints;
		mMoveId = moveId;
	}

	public int getTotalMovePoints() {
		return mTotalMovePoints;
	}

	public MoveIds getMoveId() {
		return mMoveId;
	}

	public abstract void activateMove(Anature source, Anature target);
}