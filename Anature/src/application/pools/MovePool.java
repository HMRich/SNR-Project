package application.pools;

import java.util.HashMap;

import application.anatures.moves.Move;
import application.anatures.moves.moves.DoublePunch;
import application.anatures.moves.moves.Flail;
import application.anatures.moves.moves.Flamethrower;
import application.anatures.moves.moves.Grumble;
import application.anatures.moves.moves.PocketSand;
import application.anatures.moves.moves.SkipTurn;
import application.anatures.moves.moves.Tackle;
import application.enums.MoveIds;
import application.enums.Type;
import application.interfaces.IMove;

public class MovePool
{
	private static HashMap<MoveIds, IMove> mMoves;

	public static IMove getMove(MoveIds moveId)
	{
		if(mMoves == null)
		{
			generateMoves();
		}
		return mMoves.get(moveId);
	}

	private static void generateMoves()
	{
		mMoves = new HashMap<MoveIds, IMove>();

		mMoves.put(MoveIds.Grumble,
				new Move<Grumble>().withName("Grumble")
						.withMoveId(MoveIds.Grumble)
						.withType(Type.Normal)
						.doesDamage(false)
						.isPhysicalAttack(false)
						.withTotalMovePoints(25)
						.withAccuracy(1)
						.create());

		mMoves.put(MoveIds.Double_Punch,
				new Move<DoublePunch>().withName("Double Punch")
						.withMoveId(MoveIds.Double_Punch)
						.withType(Type.Fighting)
						.doesDamage(true)
						.isPhysicalAttack(true)
						.withTotalMovePoints(20)
						.withAccuracy(1)
						.create());

		mMoves.put(MoveIds.Flamethrower,
				new Move<Flamethrower>().withName("Flamethrower")
						.withMoveId(MoveIds.Flamethrower)
						.withType(Type.Fire)
						.doesDamage(true)
						.isPhysicalAttack(false)
						.withTotalMovePoints(10)
						.withAccuracy(.75)
						.create());

		mMoves.put(MoveIds.Pocket_Sand,
				new Move<PocketSand>().withName("Pocket Sand")
						.withMoveId(MoveIds.Pocket_Sand)
						.withType(Type.Ground)
						.doesDamage(false)
						.isPhysicalAttack(false)
						.withTotalMovePoints(25)
						.withAccuracy(1)
						.create());

		mMoves.put(MoveIds.Skip_Turn,
				new Move<SkipTurn>().withName("Skip Turn")
						.withMoveId(MoveIds.Skip_Turn)
						.withType(Type.Normal)
						.doesDamage(false)
						.isPhysicalAttack(false)
						.withTotalMovePoints(0)
						.withAccuracy(1)
						.create());

		// TODO Struggle will eventually run out of Move Points... almost impossible but
		// there might be a work around
		mMoves.put(MoveIds.Flail,
				new Move<Flail>().withName("Flail")
						.withMoveId(MoveIds.Flail)
						.withType(Type.Normal)
						.doesDamage(true)
						.isPhysicalAttack(true)
						.withTotalMovePoints(Integer.MAX_VALUE)
						.withAccuracy(1)
						.create());

		mMoves.put(MoveIds.Tackle,
				new Move<Tackle>().withName("Tackle")
						.withMoveId(MoveIds.Tackle)
						.withType(Type.Normal)
						.doesDamage(true)
						.isPhysicalAttack(true)
						.withTotalMovePoints(25)
						.withAccuracy(1)
						.create());
	}
}
