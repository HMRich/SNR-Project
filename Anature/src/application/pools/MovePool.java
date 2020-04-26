package application.pools;

import java.util.HashMap;

import application.anatures.moves.Flamethrower;
import application.anatures.moves.Grumble;
import application.anatures.moves.Move;
import application.anatures.moves.SkipTurn;
import application.anatures.moves.Struggle;
import application.anatures.moves.Tackle;
import application.enums.MoveIds;

public class MovePool
{
	private static HashMap<MoveIds, Move> mMoves;

	public static Move getMove(MoveIds moveId)
	{
		if(mMoves == null)
		{
			generateMoves();
		}
		return mMoves.get(moveId);
	}

	private static void generateMoves()
	{
		mMoves = new HashMap<MoveIds, Move>();
		mMoves.put(MoveIds.Tackle, new Tackle());
		mMoves.put(MoveIds.Grumble, new Grumble());
		mMoves.put(MoveIds.Flamethrower, new Flamethrower());
		mMoves.put(MoveIds.Struggle, new Struggle());
		mMoves.put(MoveIds.Skip_Turn, new SkipTurn()); 
	}
}
