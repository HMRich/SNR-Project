package application;

import java.util.HashMap;

import application.enums.MoveIds;

public class MovePool {

	private static HashMap<MoveIds, Move> mMoves;

	public static Move getMove(MoveIds moveId) {
		if (mMoves == null) {
			generateMoves();
		}
		return mMoves.get(moveId);
	}

	private static void generateMoves() {
		mMoves = new HashMap<MoveIds, Move>();
	}

}
