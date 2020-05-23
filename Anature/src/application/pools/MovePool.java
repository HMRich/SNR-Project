package application.pools;

import java.util.HashMap;

import application.anatures.moves.MoveBuilder;
import application.anatures.moves.moves.BodySlam;
import application.anatures.moves.moves.DeepFreeze;
import application.anatures.moves.moves.DoublePunch;
import application.anatures.moves.moves.Flail;
import application.anatures.moves.moves.Flamethrower;
import application.anatures.moves.moves.ForcefulSlam;
import application.anatures.moves.moves.FreezingBlast;
import application.anatures.moves.moves.Grumble;
import application.anatures.moves.moves.IceLayer;
import application.anatures.moves.moves.IceSpike;
import application.anatures.moves.moves.IceWhip;
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

		mMoves.put(MoveIds.Grumble, new MoveBuilder<Grumble>(MoveIds.Grumble).withName("Grumble")
				.withType(Type.Normal)
				.doesDamage(false)
				.isPhysicalAttack(false)
				.withTotalMovePoints(25)
				.withMovePower(0)
				.withAccuracy(100)
				.create());

		mMoves.put(MoveIds.Double_Punch, new MoveBuilder<DoublePunch>(MoveIds.Double_Punch).withName("Double Punch")
				.withType(Type.Fighting)
				.doesDamage(true)
				.isPhysicalAttack(true)
				.withTotalMovePoints(20)
				.withMovePower(35)
				.withAccuracy(75)
				.create());

		mMoves.put(MoveIds.Flamethrower, new MoveBuilder<Flamethrower>(MoveIds.Flamethrower).withName("Flamethrower")
				.withType(Type.Fire)
				.doesDamage(true)
				.isPhysicalAttack(false)
				.withTotalMovePoints(10)
				.withMovePower(80)
				.withAccuracy(75)
				.create());

		mMoves.put(MoveIds.Pocket_Sand, new MoveBuilder<PocketSand>(MoveIds.Pocket_Sand).withName("Pocket Sand")
				.withType(Type.Ground)
				.doesDamage(false)
				.isPhysicalAttack(false)
				.withTotalMovePoints(25)
				.withMovePower(0)
				.withAccuracy(100)
				.create());

		mMoves.put(MoveIds.Skip_Turn, new MoveBuilder<SkipTurn>(MoveIds.Skip_Turn).withName("Skip Turn")
				.withType(Type.Normal)
				.doesDamage(false)
				.isPhysicalAttack(false)
				.withTotalMovePoints(0)
				.withMovePower(0)
				.withAccuracy(100)
				.create());

		mMoves.put(MoveIds.Flail, new MoveBuilder<Flail>(MoveIds.Flail).withName("Flail")
				.withType(Type.Normal)
				.doesDamage(true)
				.isPhysicalAttack(true)
				.withTotalMovePoints(Integer.MAX_VALUE)
				.withMovePower(50)
				.withAccuracy(100)
				.create());

		mMoves.put(MoveIds.Tackle, new MoveBuilder<Tackle>(MoveIds.Tackle).withName("Tackle")
				.withType(Type.Normal)
				.doesDamage(true)
				.isPhysicalAttack(true)
				.withTotalMovePoints(25)
				.withMovePower(60)
				.withAccuracy(85)
				.create());

		mMoves.put(MoveIds.Ice_Whip, new MoveBuilder<IceWhip>(MoveIds.Ice_Whip).withName("Ice Whip")
				.withType(Type.Ice)
				.doesDamage(true)
				.isPhysicalAttack(true)
				.withTotalMovePoints(20)
				.withMovePower(60)
				.withAccuracy(80)
				.create());

		mMoves.put(MoveIds.Body_Slam, new MoveBuilder<BodySlam>(MoveIds.Body_Slam).withName("Body Slam")
				.withType(Type.Steel)
				.doesDamage(true)
				.isPhysicalAttack(true)
				.withTotalMovePoints(20)
				.withMovePower(60)
				.withAccuracy(90)
				.create());

		mMoves.put(MoveIds.Freezing_Blast, new MoveBuilder<FreezingBlast>(MoveIds.Freezing_Blast).withName("Freezing Blast")
				.withType(Type.Ice)
				.doesDamage(false)
				.isPhysicalAttack(false)
				.withTotalMovePoints(40)
				.withMovePower(0)
				.withAccuracy(100)
				.create());

		mMoves.put(MoveIds.Ice_Layer, new MoveBuilder<IceLayer>(MoveIds.Ice_Layer).withName("Ice Layer")
				.withType(Type.Ice)
				.doesDamage(false)
				.isPhysicalAttack(false)
				.withTotalMovePoints(15)
				.withMovePower(0)
				.withAccuracy(100)
				.create());

		mMoves.put(MoveIds.Ice_Spike, new MoveBuilder<IceSpike>(MoveIds.Ice_Spike).withName("Ice Spike")
				.withType(Type.Ice)
				.doesDamage(true)
				.isPhysicalAttack(false)
				.withTotalMovePoints(10)
				.withMovePower(80)
				.withAccuracy(90)
				.create());

		mMoves.put(MoveIds.Deep_Freeze, new MoveBuilder<DeepFreeze>(MoveIds.Deep_Freeze).withName("Deep Freeze")
				.withType(Type.Ice)
				.doesDamage(false)
				.isPhysicalAttack(false)
				.withTotalMovePoints(5)
				.withMovePower(0)
				.withAccuracy(100)
				.create());

		mMoves.put(MoveIds.Forceful_Slam, new MoveBuilder<ForcefulSlam>(MoveIds.Forceful_Slam).withName("Forceful Slam")
				.withType(Type.Steel)
				.doesDamage(true)
				.isPhysicalAttack(true)
				.withTotalMovePoints(15)
				.withMovePower(110)
				.withAccuracy(85)
				.create());
	}
}
