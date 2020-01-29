package application;

public class MoveSet {

	private int movePoints1;
	private Move move1;
	private int movePoints2;
	private Move move2;
	private int movePoints3;
	private Move move3;
	private int movePoints4;
	private Move move4;
	
	public MoveSet(Move move1, Move move2, Move move3, Move move4) {
		this.move1 = move1;
		this.move2 = move2;
		this.move3 = move3;
		this.move4 = move4;
		generateMovePointTotals();
	}
	
	private void generateMovePointTotals() {
		this.movePoints1 = this.move1.getTotalMp();
		this.movePoints2 = this.move2.getTotalMp();
		this.movePoints3 = this.move3.getTotalMp();
		this.movePoints4 = this.move4.getTotalMp();
	}
	
	public Move getMove1() {
		return this.move1;
	}
	
	public Move getMove2() {
		return this.move2;
	}
	
	public Move getMove3() {
		return this.move3;
	}
	
	public Move getMove4() {
		return this.move4;
	}
	
	public Move setMove1() {
		return this.move1;
	}
	
	public Move setMove2() {
		return this.move2;
	}
	
	public Move setMove3() {
		return this.move3;
	}
	
	public Move setMove4() {
		return this.move4;
	}
	
	public boolean hasMove1() {
		return this.move1 != null;
	}
	
	public boolean hasMove2() {
		return this.move2 != null;
	}
	
	public boolean hasMove3() {
		return this.move3 != null;
	}
	
	public boolean hasMove4() {
		return this.move4 != null;
	}
	
	public boolean canUseMove1() {
		return this.move1 > 0;
	}
	
	public boolean canUseMove2() {
		return this.move2 > 0;
	}
	
	public boolean canUseMove3() {
		return this.move3 > 0;
	}
	
	public boolean canUseMove4() {
		return this.move4 > 0;
	}
	
}
