package application;

import java.util.ArrayList;

import application.controllers.LoggerController;
import application.enums.LoggingTypes;
import application.moves.Move;
import application.moves.MovePool;

public class MoveSet
{
	private int mMove1MovePoints, mMove2MovePoints, mMove3MovePoints, mMove4MovePoints;
	private Move mMove1, mMove2, mMove3, mMove4;
	private Move mSkipTurn = MovePool.getMove(MoveIds.Skip_Turn);

	public MoveSet(Move move1, Move move2, Move move3, Move move4)
	{
		setMove(1, move1);
		setMove(2, move2);
		setMove(3, move3);
		setMove(4, move4);
	}

	public Move getMove(int moveNumber)
	{
		switch(moveNumber)
		{
			case 1:
				return mMove1;

			case 2:
				return mMove2;

			case 3:
				return mMove3;

			case 4:
				return mMove4;

			case -1:
				return mSkipTurn;

			default:
				throw new IllegalStateException("moveNumber was not in a vaild state.");
		}
	}

	public ArrayList<Move> getMoves()
	{
		ArrayList<Move> moves = new ArrayList<Move>();

		if(hasMove(1))
			moves.add(mMove1);

		if(hasMove(2))
			moves.add(mMove2);

		if(hasMove(3))
			moves.add(mMove3);

		if(hasMove(4))
			moves.add(mMove4);

		return moves;
	}

	public boolean setMove(int moveNumber, Move move)
	{
		switch(moveNumber)
		{
			case 1:
				mMove1 = move;
				return setMovePoints(moveNumber);

			case 2:
				mMove2 = move;
				return setMovePoints(moveNumber);

			case 3:
				mMove3 = move;
				return setMovePoints(moveNumber);

			case 4:
				mMove4 = move;
				return setMovePoints(moveNumber);

			case -1:
				break;

			default:
				LoggerController.logEvent(LoggingTypes.Error,
						"IllegalStateException in MoveSet.java, Method: setMove(int moveNumber, Move move). Passed moveNumber was not 1-4.");
				return false;
		}
	}

	public boolean hasMove(int moveNumber)
	{
		switch(moveNumber)
		{
			case 1:
				return getMove(1) != null;

			case 2:
				return getMove(2) != null;

			case 3:
				return getMove(3) != null;

			case 4:
				return getMove(4) != null;

			default:
				throw new IllegalStateException("moveNumber was not in a valid value.");
		}
	}

	public int getMovePoints(int moveNumber)
	{
		switch(moveNumber)
		{
			case 1:
				return mMove1MovePoints;

			case 2:
				return mMove2MovePoints;

			case 3:
				return mMove3MovePoints;

			case 4:
				return mMove4MovePoints;

			case -1:
				return 0;

			default:
				throw new IllegalStateException("moveNumber was not in a vaild state.");
		}
	}

	public boolean setMovePoints(int moveNumber)
	{
		if(hasMove(moveNumber))
		{
			return setMovePoints(moveNumber, getMove(moveNumber).getTotalMovePoints());
		}
		return setMovePoints(moveNumber, 0);
	}

	public boolean setMovePoints(int moveNumber, int movePoints)
	{
		switch(moveNumber)
		{
			case 1:
				if(validPointValue(moveNumber, movePoints))
				{
					mMove1MovePoints = movePoints;
					return true;
				}
				return false;

			case 2:
				if(validPointValue(moveNumber, movePoints))
				{
					mMove2MovePoints = movePoints;
					return true;
				}
				return false;

			case 3:
				if(validPointValue(moveNumber, movePoints))
				{
					mMove3MovePoints = movePoints;
					return true;
				}
				return false;

			case 4:
				if(validPointValue(moveNumber, movePoints))
				{
					mMove4MovePoints = movePoints;
					return true;
				}
				return false;

			default:
				throw new IllegalStateException("moveNumber was not in a valid value.");
		}
	}

	private boolean validPointValue(int moveNumber, int movePoints)
	{
		if(hasMove(moveNumber))
		{
			int totalPointValue = getMove(moveNumber).getTotalMovePoints();

			if(movePoints <= totalPointValue)
				return true;
		}
		return false;
	}

	public boolean useMovePoint(int moveNumber)
	{
		switch(moveNumber)
		{
			case 1:
				return canUseMove(moveNumber);

			case 2:
				return canUseMove(moveNumber);

			case 3:
				return canUseMove(moveNumber);

			case 4:
				return canUseMove(moveNumber);

			default:
				throw new IllegalStateException("moveNumber was not in a valid value.");
		}
	}

	private boolean canUseMove(int moveNumber)
	{
		int movePoints = getMovePoints(moveNumber);

		if(movePoints <= 0)
			return false;

		return setMovePoints(moveNumber, movePoints--);
	}

	public void refreshAllMovePoints()
	{
		mMove1MovePoints = mMove1 != null ? mMove1.getTotalMovePoints() : 0;
		mMove2MovePoints = mMove2 != null ? mMove2.getTotalMovePoints() : 0;
		mMove3MovePoints = mMove3 != null ? mMove3.getTotalMovePoints() : 0;
		mMove4MovePoints = mMove4 != null ? mMove4.getTotalMovePoints() : 0;
	}

}