package application.pools;

import java.util.HashMap;

import application.anatures.moves.MoveBuilder;
import application.anatures.moves.moves.Coil;
import application.anatures.moves.moves.DoublePunch;
import application.anatures.moves.moves.Flail;
import application.anatures.moves.moves.Flamethrower;
import application.anatures.moves.moves.Grumble;
import application.anatures.moves.moves.HoseDown;
import application.anatures.moves.moves.JustDamageDealing;
import application.anatures.moves.moves.PocketSand;
import application.anatures.moves.moves.SkipTurn;
import application.anatures.moves.moves.Tackle;
import application.anatures.moves.moves.WaterToss;
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
		
		mMoves.put(MoveIds.Water_Toss,
				new MoveBuilder<WaterToss>(MoveIds.Water_Toss)
					.withName("Water Toss")
					.withType(Type.Water)
					.doesDamage(true)
					.isPhysicalAttack(false)
					.withTotalMovePoints(30)
					.withMovePower(40)
					.withAccuracy(95)
					.create()
				);
		
		mMoves.put(MoveIds.Hose_Down,
				new MoveBuilder<HoseDown>(MoveIds.Hose_Down)
					.withName("Hose Down")
					.withType(Type.Water)
					.doesDamage(false)
					.isPhysicalAttack(false)
					.withTotalMovePoints(15)
					.withMovePower(0)
					.withAccuracy(90)
					.create()
				);
		
		mMoves.put(MoveIds.Coil,
				new MoveBuilder<Coil>(MoveIds.Coil)
					.withName("Coil")
					.withType(Type.Normal)
					.doesDamage(true)
					.isPhysicalAttack(true)
					.withTotalMovePoints(25)
					.withMovePower(15)
					.withAccuracy(100)
					.create()
				);
		
		mMoves.put(MoveIds.Water_Fang,
				new MoveBuilder<JustDamageDealing>(MoveIds.Water_Fang)
					.withName("Water Fang")
					.withType(Type.Water)
					.doesDamage(true)
					.isPhysicalAttack(true)
					.withTotalMovePoints(15)
					.withMovePower(55)
					.withAccuracy(100)
					.create()
				);
		
		mMoves.put(MoveIds.Water_Blast,
				new MoveBuilder<JustDamageDealing>(MoveIds.Water_Blast)
					.withName("Water Blast")
					.withType(Type.Water)
					.doesDamage(true)
					.isPhysicalAttack(false)
					.withTotalMovePoints(15)
					.withMovePower(75)
					.withAccuracy(100)
					.create()
				);
		
		mMoves.put(MoveIds.Pounce,
				new MoveBuilder<JustDamageDealing>(MoveIds.Pounce)
					.withName("Pounce")
					.withType(Type.Normal)
					.doesDamage(true)
					.isPhysicalAttack(true)
					.withTotalMovePoints(20)
					.withMovePower(65)
					.withAccuracy(90)
					.create()
				);
		
		mMoves.put(MoveIds.Sharpen_Up,
				new MoveBuilder<JustDamageDealing>(MoveIds.Sharpen_Up)
					.withName("Sharpen Up")
					.withType(Type.Normal)
					.doesDamage(false)
					.isPhysicalAttack(false)
					.withTotalMovePoints(15)
					.withMovePower(0)
					.withAccuracy(100)
					.create()
				);
		
		mMoves.put(MoveIds.Flood,
				new MoveBuilder<JustDamageDealing>(MoveIds.Flood)
					.withName("Flood")
					.withType(Type.Water)
					.doesDamage(true)
					.isPhysicalAttack(false)
					.withTotalMovePoints(5)
					.withMovePower(120)
					.withAccuracy(75)
					.create()
				);
		
		mMoves.put(MoveIds.Body_Slam,
				new MoveBuilder<JustDamageDealing>(MoveIds.Body_Slam)
					.withName("Body Slam")
					.withType(Type.Normal)
					.doesDamage(true)
					.isPhysicalAttack(true)
					.withTotalMovePoints(10)
					.withMovePower(90)
					.withAccuracy(100)
					.create()
				);
		
		mMoves.put(MoveIds.Wing_Bash,
				new MoveBuilder<JustDamageDealing>(MoveIds.Wing_Bash)
					.withName("Wing Bash")
					.withType(Type.Flying)
					.doesDamage(true)
					.isPhysicalAttack(true)
					.withTotalMovePoints(20)
					.withMovePower(80)
					.withAccuracy(100)
					.create()
				);
		
		mMoves.put(MoveIds.Tornado,
				new MoveBuilder<JustDamageDealing>(MoveIds.Tornado)
					.withName("Tornado")
					.withType(Type.Flying)
					.doesDamage(true)
					.isPhysicalAttack(false)
					.withTotalMovePoints(5)
					.withMovePower(110)
					.withAccuracy(85)
					.create()
				);
		
		mMoves.put(MoveIds.Zap,
				new MoveBuilder<JustDamageDealing>(MoveIds.Zap)
					.withName("Zap")
					.withType(Type.Electric)
					.doesDamage(true)
					.isPhysicalAttack(false)
					.withTotalMovePoints(25)
					.withMovePower(45)
					.withAccuracy(100)
					.create()
				);
		
		mMoves.put(MoveIds.Shock_Blast,
				new MoveBuilder<JustDamageDealing>(MoveIds.Shock_Blast)
					.withName("Shock Blast")
					.withType(Type.Electric)
					.doesDamage(true)
					.isPhysicalAttack(false)
					.withTotalMovePoints(25)
					.withMovePower(65)
					.withAccuracy(95)
					.create()
				);
		
		mMoves.put(MoveIds.Wire_Smack,
				new MoveBuilder<JustDamageDealing>(MoveIds.Wire_Smack)
					.withName("Wire Smack")
					.withType(Type.Electric)
					.doesDamage(true)
					.isPhysicalAttack(true)
					.withTotalMovePoints(25)
					.withMovePower(75)
					.withAccuracy(95)
					.create()
				);
		
		mMoves.put(MoveIds.Upgrade,
				new MoveBuilder<JustDamageDealing>(MoveIds.Upgrade)
					.withName("Upgrade")
					.withType(Type.Electric)
					.doesDamage(false)
					.isPhysicalAttack(false)
					.withTotalMovePoints(10)
					.withMovePower(0)
					.withAccuracy(100)
					.create()
				);
		
		mMoves.put(MoveIds.Factory_Reset,
				new MoveBuilder<JustDamageDealing>(MoveIds.Factory_Reset)
					.withName("Factory Reset")
					.withType(Type.Electric)
					.doesDamage(false)
					.isPhysicalAttack(false)
					.withTotalMovePoints(10)
					.withMovePower(0)
					.withAccuracy(100)
					.create()
				);
		
		mMoves.put(MoveIds.Thunder_Blast,
				new MoveBuilder<JustDamageDealing>(MoveIds.Thunder_Blast)
					.withName("Thunder Blast")
					.withType(Type.Electric)
					.doesDamage(true)
					.isPhysicalAttack(false)
					.withTotalMovePoints(15)
					.withMovePower(95)
					.withAccuracy(100)
					.create()
				);
		
		mMoves.put(MoveIds.Wired_Mess,
				new MoveBuilder<JustDamageDealing>(MoveIds.Wired_Mess)
					.withName("Wired Mess")
					.withType(Type.Electric)
					.doesDamage(false)
					.isPhysicalAttack(false)
					.withTotalMovePoints(10)
					.withMovePower(0)
					.withAccuracy(100)
					.create()
				);
		
		mMoves.put(MoveIds.Voltage_Overload,
				new MoveBuilder<JustDamageDealing>(MoveIds.Voltage_Overload)
					.withName("Voltage Overload")
					.withType(Type.Electric)
					.doesDamage(true)
					.isPhysicalAttack(false)
					.withTotalMovePoints(5)
					.withMovePower(110)
					.withAccuracy(85)
					.create()
				);
	}
}
