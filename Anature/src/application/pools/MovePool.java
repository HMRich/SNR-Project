package application.pools;

import java.util.HashMap;

import application.anatures.moves.MoveBuilder;
import application.anatures.moves.moves.AntlerShot;
import application.anatures.moves.moves.Cinder;
import application.anatures.moves.moves.Clop;
import application.anatures.moves.moves.DoublePunch;
import application.anatures.moves.moves.ElectroSonic;
import application.anatures.moves.moves.Electrocution;
import application.anatures.moves.moves.FireCell;
import application.anatures.moves.moves.FireTorture;
import application.anatures.moves.moves.Flail;
import application.anatures.moves.moves.FlameBout;
import application.anatures.moves.moves.Flamethrower;
import application.anatures.moves.moves.ForgottenAwakening;
import application.anatures.moves.moves.Grumble;
import application.anatures.moves.moves.HealingWinds;
import application.anatures.moves.moves.Holler;
import application.anatures.moves.moves.LightMissile;
import application.anatures.moves.moves.Lightning;
import application.anatures.moves.moves.MagicalSpice;
import application.anatures.moves.moves.PocketSand;
import application.anatures.moves.moves.ScaryFace;
import application.anatures.moves.moves.SkipTurn;
import application.anatures.moves.moves.Smash;
import application.anatures.moves.moves.SmogWave;
import application.anatures.moves.moves.StormyBreeze;
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
		
		mMoves.put(MoveIds.Cinder,
				new MoveBuilder<Cinder>(MoveIds.Cinder).withName("Cinder")
						.withType(Type.Fire)
						.doesDamage(true)
						.isPhysicalAttack(true)
						.withTotalMovePoints(20)
						.withMovePower(40)
						.withAccuracy(100)
						.create());
		
		mMoves.put(MoveIds.Smash,
				new MoveBuilder<Smash>(MoveIds.Smash).withName("Smash")
						.withType(Type.Fire)
						.doesDamage(true)
						.isPhysicalAttack(true)
						.withTotalMovePoints(25)
						.withMovePower(25)
						.withAccuracy(100)
						.create());
		
		mMoves.put(MoveIds.Fire_Torture,
				new MoveBuilder<FireTorture>(MoveIds.Fire_Torture).withName("Fire Torture")
						.withType(Type.Fire)
						.doesDamage(true)
						.isPhysicalAttack(true)
						.withTotalMovePoints(20)
						.withMovePower(90)
						.withAccuracy(80)
						.create());
		
		mMoves.put(MoveIds.Smog_Wave,
				new MoveBuilder<SmogWave>(MoveIds.Smog_Wave).withName("Smog Wave")
						.withType(Type.Normal)
						.doesDamage(false)
						.isPhysicalAttack(false)
						.withTotalMovePoints(10)
						.withMovePower(0)
						.withAccuracy(100)
						.create());
		
		mMoves.put(MoveIds.Fire_Cell,
				new MoveBuilder<FireCell>(MoveIds.Fire_Cell).withName("Fire Cell")
						.withType(Type.Fire)
						.doesDamage(true)
						.isPhysicalAttack(true)
						.withTotalMovePoints(10)
						.withMovePower(100)
						.withAccuracy(90)
						.create());
		
		mMoves.put(MoveIds.Scary_Face,
				new MoveBuilder<ScaryFace>(MoveIds.Scary_Face).withName("Scary Face")
						.withType(Type.Normal)
						.doesDamage(false)
						.isPhysicalAttack(false)
						.withTotalMovePoints(15)
						.withMovePower(0)
						.withAccuracy(100)
						.create());
		
		mMoves.put(MoveIds.Flame_Bout,
				new MoveBuilder<FlameBout>(MoveIds.Flame_Bout).withName("Flame Bout")
						.withType(Type.Normal)
						.doesDamage(false)
						.isPhysicalAttack(false)
						.withTotalMovePoints(5)
						.withMovePower(120)
						.withAccuracy(80)
						.create());
		
		mMoves.put(MoveIds.Clop,
				new MoveBuilder<Clop>(MoveIds.Clop).withName("Clop")
						.withType(Type.Normal)
						.doesDamage(false)
						.isPhysicalAttack(false)
						.withTotalMovePoints(20)
						.withMovePower(65)
						.withAccuracy(100)
						.create());
		
		mMoves.put(MoveIds.Antler_Shot,
				new MoveBuilder<AntlerShot>(MoveIds.Antler_Shot).withName("Antler Shot")
						.withType(Type.Normal)
						.doesDamage(true)
						.isPhysicalAttack(true)
						.withTotalMovePoints(15)
						.withMovePower(75)
						.withAccuracy(100)
						.create());
		
		mMoves.put(MoveIds.Electro_Sonic,
				new MoveBuilder<ElectroSonic>(MoveIds.Electro_Sonic).withName("Electro Sonic")
						.withType(Type.Electric)
						.doesDamage(true)
						.isPhysicalAttack(true)
						.withTotalMovePoints(20)
						.withMovePower(40)
						.withAccuracy(100)
						.create());
		
		mMoves.put(MoveIds.Stormy_Breeze,
				new MoveBuilder<StormyBreeze>(MoveIds.Stormy_Breeze).withName("Stormy Breeze")
						.withType(Type.Electric)
						.doesDamage(true)
						.isPhysicalAttack(true)
						.withTotalMovePoints(20)
						.withMovePower(20)
						.withAccuracy(100)
						.create());
		
		mMoves.put(MoveIds.Holler,
				new MoveBuilder<Holler>(MoveIds.Holler).withName("Holler")
						.withType(Type.Normal)
						.doesDamage(false)
						.isPhysicalAttack(false)
						.withTotalMovePoints(15)
						.withMovePower(0)
						.withAccuracy(70)
						.create());
		
		mMoves.put(MoveIds.Light_Missle,
				new MoveBuilder<LightMissile>(MoveIds.Light_Missle).withName("Light Missile")
						.withType(Type.Electric)
						.doesDamage(true)
						.isPhysicalAttack(true)
						.withTotalMovePoints(10)
						.withMovePower(80)
						.withAccuracy(80)
						.create());
		
		mMoves.put(MoveIds.Lightning,
				new MoveBuilder<Lightning>(MoveIds.Lightning).withName("Lightning")
						.withType(Type.Electric)
						.doesDamage(true)
						.isPhysicalAttack(true)
						.withTotalMovePoints(5)
						.withMovePower(110)
						.withAccuracy(80)
						.create());
		
		mMoves.put(MoveIds.Electrocution,
				new MoveBuilder<Electrocution>(MoveIds.Electrocution).withName("Electrocution")
						.withType(Type.Electric)
						.doesDamage(true)
						.isPhysicalAttack(true)
						.withTotalMovePoints(5)
						.withMovePower(120)
						.withAccuracy(75)
						.create());
		
		mMoves.put(MoveIds.Magical_Spice,
				new MoveBuilder<MagicalSpice>(MoveIds.Magical_Spice).withName("Magical Spice")
						.withType(Type.Psychic)
						.doesDamage(true)
						.isPhysicalAttack(true)
						.withTotalMovePoints(10)
						.withMovePower(70)
						.withAccuracy(100)
						.create());
		
		mMoves.put(MoveIds.Forgotten_Awakening,
				new MoveBuilder<ForgottenAwakening>(MoveIds.Forgotten_Awakening).withName("Forgotten Awakening")
						.withType(Type.Rock)
						.doesDamage(true)
						.isPhysicalAttack(true)
						.withTotalMovePoints(5)
						.withMovePower(60)
						.withAccuracy(100)
						.create());
		
		mMoves.put(MoveIds.Healing_Wings,
				new MoveBuilder<HealingWinds>(MoveIds.Healing_Wings).withName("Healing Winds")
						.withType(Type.Normal)
						.doesDamage(false)
						.isPhysicalAttack(false)
						.withTotalMovePoints(5)
						.withMovePower(0)
						.withAccuracy(0)
						.create());
		
		
		
	}
}
