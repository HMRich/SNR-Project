package application;

import java.util.ArrayList;

import application.enums.MoveIds;
import application.moves.Move;
import application.moves.MovePool;

public class MoveSet
{
	private int mMove1MovePoints, mMove2MovePoints, mMove3MovePoints, mMove4MovePoints;
	private Move mMove1, mMove2, mMove3, mMove4;
	private Move mSkipTurn = MovePool.getMove(MoveIds.Skip_Turn);

	public MoveSet(Move move1, Move move2, Move move3, Move move4)
	{
		mMove1 = move1;
		mMove2 = move2;
		mMove3 = move3;
		mMove4 = move4;
		generateMovePointTotals();
	}

	private void generateMovePointTotals()
	{
		mMove1MovePoints = mMove1 != null ? mMove1.getTotalMovePoints() : 0;
		mMove2MovePoints = mMove2 != null ? mMove2.getTotalMovePoints() : 0;
		mMove3MovePoints = mMove3 != null ? mMove3.getTotalMovePoints() : 0;
		mMove4MovePoints = mMove4 != null ? mMove4.getTotalMovePoints() : 0;
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

	public Move getMove(int index)
	{
		switch(index)
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
				throw new IllegalStateException("index was not in a vaild state.");
		}
	}

	public void setMove(int index, Move move)
	{
		switch(index)
		{
			case 1:
				mMove1 = move;
				break;

			case 2:
				mMove2 = move;
				break;

			case 3:
				mMove3 = move;
				break;

			case 4:
				mMove4 = move;
				break;

			case -1:
				break;

			default:
				throw new IllegalStateException("index was not in a valid value.");
		}
	}

	public int getMovePoints(int index)
	{
		switch(index)
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
				throw new IllegalStateException("index was not in a vaild state.");
		}
	}

	public void setMovePoints(int index, int movePoints)
	{
		switch(index)
		{
			case 1:
				mMove1MovePoints = movePoints;
				break;

			case 2:
				mMove2MovePoints = movePoints;
				break;

			case 3:
				mMove3MovePoints = movePoints;
				break;

			case 4:
				mMove4MovePoints = movePoints;
				break;

			case -1:
				break;

			default:
				throw new IllegalStateException("index was not in a valid value.");
		}
	}

	public void useMp(int index)
	{
		switch(index)
		{
			case 1:
				mMove1MovePoints--;
				break;

			case 2:
				mMove2MovePoints--;
				break;

			case 3:
				mMove3MovePoints--;
				break;

			case 4:
				mMove4MovePoints--;
				break;

			case -1:
				break;

			default:
				throw new IllegalStateException("index was not in a valid value.");
		}
	}

	public void refreshAllMovePoints()
	{
		generateMovePointTotals();
	}

}