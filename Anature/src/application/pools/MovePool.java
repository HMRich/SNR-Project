package application.pools;

import java.util.HashMap;

import application.anatures.moves.MoveBuilder;
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
				new MoveBuilder<Grumble>(MoveIds.Grumble).withName("Grumble")
						.withType(Type.Normal)
						.doesDamage(false)
						.isPhysicalAttack(false)
						.withTotalMovePoints(25)
						.withMovePower(0)
						.withAccuracy(100)
						.create());

		mMoves.put(MoveIds.Double_Punch,
				new MoveBuilder<DoublePunch>(MoveIds.Double_Punch).withName("Double Punch")
						.withType(Type.Fighting)
						.doesDamage(true)
						.isPhysicalAttack(true)
						.withTotalMovePoints(20)
						.withMovePower(35)
						.withAccuracy(75)
						.create());

		mMoves.put(MoveIds.Flamethrower,
				new MoveBuilder<Flamethrower>(MoveIds.Flamethrower).withName("Flamethrower")
						.withType(Type.Fire)
						.doesDamage(true)
						.isPhysicalAttack(false)
						.withTotalMovePoints(10)
						.withMovePower(80)
						.withAccuracy(75)
						.create());

		mMoves.put(MoveIds.Pocket_Sand,
				new MoveBuilder<PocketSand>(MoveIds.Pocket_Sand).withName("Pocket Sand")
						.withType(Type.Ground)
						.doesDamage(false)
						.isPhysicalAttack(false)
						.withTotalMovePoints(25)
						.withMovePower(0)
						.withAccuracy(100)
						.create());

		mMoves.put(MoveIds.Skip_Turn,
				new MoveBuilder<SkipTurn>(MoveIds.Skip_Turn).withName("Skip Turn")
						.withType(Type.Normal)
						.doesDamage(false)
						.isPhysicalAttack(false)
						.withTotalMovePoints(0)
						.withMovePower(0)
						.withAccuracy(100)
						.create());

		// TODO Flail will eventually run out of Move Points... almost impossible but
		// there might be a work around
		mMoves.put(MoveIds.Flail,
				new MoveBuilder<Flail>(MoveIds.Flail).withName("Flail")
						.withType(Type.Normal)
						.doesDamage(true)
						.isPhysicalAttack(true)
						.withTotalMovePoints(Integer.MAX_VALUE)
						.withMovePower(50)
						.withAccuracy(100)
						.create());

		mMoves.put(MoveIds.Tackle,
				new MoveBuilder<Tackle>(MoveIds.Tackle).withName("Tackle")
						.withType(Type.Normal)
						.doesDamage(true)
						.isPhysicalAttack(true)
						.withTotalMovePoints(25)
						.withMovePower(60)
						.withAccuracy(85)
						.create());
	}
}
