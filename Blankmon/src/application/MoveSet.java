package application;

public class MoveSet {

	private int mMove1MovePoints, mMove2MovePoints, mMove3MovePoints, mMove4MovePoints;
	private Move mMove1, mMove2, mMove3, mMove4;

	public MoveSet(Move move1, Move move2, Move move3, Move move4) {
		mMove1 = move1;
		mMove2 = move2;
		mMove3 = move3;
		mMove4 = move4;
		generateMovePointTotals();
	}

	private void generateMovePointTotals() {
		mMove1MovePoints = mMove1 != null ? mMove1.getTotalMovePoints(): 0;
		mMove2MovePoints = mMove2 != null ? mMove2.getTotalMovePoints(): 0;
		mMove3MovePoints = mMove3 != null ? mMove3.getTotalMovePoints(): 0;
		mMove4MovePoints = mMove4 != null ? mMove4.getTotalMovePoints(): 0;
	}
	
	public Move getMove(int index) {
		switch(index) {
			case 0:
				return mMove1;
			case 1:
				return mMove2;
			case 2:
				return mMove3;
			case 3:
				return mMove4;
			default:
				throw new IllegalStateException("index was not in a vaild state.");
		}
	}
	
	public Move setMove(int index, Move move) {
		switch(index) {
			case 0:
				mMove1 = move;
			case 1:
				mMove2 = move;
			case 2:
				mMove3 = move;
			case 3:
				mMove4 = move;
			default:
				throw new IllegalStateException("index was not in a valid value.");
		}
	}
	
	public int getMovePoints(int index) {
		switch(index) {
			case 0:
				return mMove1MovePoints;
			case 1:
				return mMove2MovePoints;
			case 2:
				return mMove3MovePoints;
			case 3:
				return mMove4MovePoints;
			default:
				throw new IllegalStateException("index was not in a vaild state.");
		}
	}
	
	public Move setMovePoints(int index, int movePoints) {
		switch(index) {
			case 0:
				mMove1MovePoints = movePoints;
			case 1:
				mMove2MovePoints = movePoints;
			case 2:
				mMove3MovePoints = movePoints;
			case 3:
				mMove4MovePoints = movePoints;
			default:
				throw new IllegalStateException("index was not in a valid value.");
		}
	}
	
	public void refreshAllMovePoints() {
		generateMovePointTotals();
	}
	
}