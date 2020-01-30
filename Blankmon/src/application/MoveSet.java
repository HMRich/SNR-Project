package application;

public class MoveSet {

	private int mMove1MovePoints;
	private Move mMove1;
	private int mMove2MovePoints;
	private Move mMove2;
	private int mMove3MovePoints;
	private Move mMove3;
	private int mMove4MovePoints;
	private Move mMove4;

	public MoveSet(Move move1, Move move2, Move move3, Move move4) {
		mMove1 = move1;
		mMove2 = move2;
		mMove3 = move3;
		mMove4 = move4;
		generateMovePointTotals();
	}

	private void generateMovePointTotals() {
		mMove1MovePoints = mMove1.getTotalMovePoints();
		mMove2MovePoints = mMove2.getTotalMovePoints();
		mMove3MovePoints = mMove3.getTotalMovePoints();
		mMove4MovePoints = mMove4.getTotalMovePoints();
	}

	public Move getMove1() {
		return mMove1;
	}

	public Move getMove2() {
		return mMove2;
	}

	public Move getMove3() {
		return mMove3;
	}

	public Move getMove4() {
		return mMove4;
	}

	public void setMove1(Move move) {
		mMove1 = move;
	}

	public void setMove2(Move move) {
		mMove2 = move;
	}

	public void setMove3(Move move) {
		mMove3 = move;
	}

	public void setMove4(Move move) {
		mMove4 = move;
	}

	public int getMove1MovePoints() {
		return mMove1MovePoints;
	}

	public int getMove2MovePoints() {
		return mMove2MovePoints;
	}

	public int getMove3MovePoints() {
		return mMove3MovePoints;
	}

	public int getMove4MovePoints() {
		return mMove4MovePoints;
	}

	public void setMove1MovePoints(int value) {
		mMove1MovePoints = value;
	}

	public void setMove2MovePoints(int value) {
		mMove2MovePoints = value;
	}

	public void setMove3MovePoints(int value) {
		mMove3MovePoints = value;
	}

	public void setMove4MovePoints(int value) {
		mMove4MovePoints = value;
	}

}