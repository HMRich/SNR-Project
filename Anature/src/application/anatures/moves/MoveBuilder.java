package application.anatures.moves;

import application.anatures.moves.moves.Coil;
import application.anatures.moves.moves.DamageAndStatus;
import application.anatures.moves.moves.Disguise;
import application.anatures.moves.moves.BodySlam;
import application.anatures.moves.moves.DeepFreeze;
import application.anatures.moves.moves.DoublePunch;
import application.anatures.moves.moves.FactoryReset;
import application.anatures.moves.moves.FiberOptic;
import application.anatures.moves.moves.Flail;
import application.anatures.moves.moves.Flamethrower;
import application.anatures.moves.moves.FocusUp;
import application.anatures.moves.moves.ForcefulSlam;
import application.anatures.moves.moves.FreezingBlast;
import application.anatures.moves.moves.Grumble;
import application.anatures.moves.moves.HoseDown;
import application.anatures.moves.moves.JustDamageDealing;
import application.anatures.moves.moves.IceLayer;
import application.anatures.moves.moves.IceSpike;
import application.anatures.moves.moves.IceWhip;
import application.anatures.moves.moves.NullMove;
import application.anatures.moves.moves.PocketSand;
import application.anatures.moves.moves.SharpenUp;
import application.anatures.moves.moves.SkipTurn;
import application.anatures.moves.moves.Tackle;
import application.anatures.moves.moves.TailBlock;
import application.anatures.moves.moves.Tornado;
import application.anatures.moves.moves.Upgrade;
import application.anatures.moves.moves.WaterToss;
import application.anatures.moves.moves.WiredMess;
import application.enums.MoveIds;
import application.enums.StatusEffects;
import application.enums.Type;
import application.interfaces.IBuilder;

public class MoveBuilder<M extends Move> implements IBuilder<M>
{
	private M mMove;
	private MoveIds mMoveId;

	public MoveBuilder(MoveIds moveId)
	{
		setMoveType(moveId);
		generateNewMove();
	}

	/*
	 * PRIVATE SETS
	 */

	private void setMoveType(MoveIds moveId)
	{
		if(moveId == null)
		{
			throw new IllegalArgumentException("Passed value \"moveId\" was null.");
		}

		if(moveId.equals(MoveIds.NullMove))
		{
			throw new IllegalArgumentException("Passed value \"moveId\" was equal to " + moveId.toString() + ".");
		}

		mMoveId = moveId;
	}

	/*
	 * PUBLIC SETS
	 */

	public MoveBuilder<M> withName(String name)
	{
		mMove.setName(name);
		return this;
	}

	public MoveBuilder<M> withType(Type type)
	{
		mMove.setType(type);
		return this;
	}

	public MoveBuilder<M> doesDamage(boolean doesDamage)
	{
		mMove.setDoesDamage(doesDamage);
		return this;
	}

	public MoveBuilder<M> isPhysicalAttack(boolean isPhysicalAttack)
	{
		mMove.setIsPhysicalAttack(isPhysicalAttack);
		return this;
	}

	public MoveBuilder<M> withTotalMovePoints(int totalMovePoints)
	{
		mMove.setTotalMovePoints(totalMovePoints);
		return this;
	}
	
	public MoveBuilder<M> withMovePower(int movePower)
	{
		mMove.setMovePower(movePower);
		return this;
	}

	public MoveBuilder<M> withAccuracy(int accuracy)
	{
		mMove.setAccuracy(accuracy);
		return this;
	}

	/*
	 * PUBLIC METHODS
	 */

	public M create()
	{
		if(buildIsComplete())
		{
			M moveToReturn = mMove;

			generateNewMove();

			return moveToReturn;
		}

		throw new IllegalStateException("All the builder variables need to have a value before you create a Move.");
	}

	/*
	 * PRIVATE METHODS
	 */

	@SuppressWarnings("unchecked")
	private void generateNewMove()
	{
		switch(mMoveId)
		{
			case Grumble:
				mMove = (M) new Grumble();
				break;

			case Double_Punch:
				mMove = (M) new DoublePunch();
				break;

			case Flamethrower:
				mMove = (M) new Flamethrower();
				break;

			case Pocket_Sand:
				mMove = (M) new PocketSand();
				break;

			case Skip_Turn:
				mMove = (M) new SkipTurn();
				break;

			case Flail:
				mMove = (M) new Flail();
				break;

			case Tackle:
				mMove = (M) new Tackle();
				break;

			case NullMove:
				mMove = (M) new NullMove();
				break;
				
			case Water_Toss:
				mMove = (M) new WaterToss();
				break;
				
			case Hose_Down:
				mMove = (M) new HoseDown();
				break;
				
			case Water_Fang:
				mMove = (M) new JustDamageDealing();
				break;
				
			case Water_Blast:
				mMove = (M) new JustDamageDealing();
				break;
				
			case Flood:
				mMove = (M) new JustDamageDealing();
				break;
				
			case Wing_Bash:
				mMove = (M) new JustDamageDealing();
				break;
				
			case Tornado:
				mMove = (M) new Tornado();
				break;
				
			case Coil:
				mMove = (M) new Coil();
				break;
				
			case Body_Slam:
				mMove = (M) new JustDamageDealing();
				break;
				
			case Pounce:
				mMove = (M) new JustDamageDealing();
				break;
				
			case Sharpen_Up:
				mMove = (M) new SharpenUp();
				break;
				
			case Tail_Slap:
				mMove = (M) new JustDamageDealing();
				break;
				
			case Tail_Block:
				mMove = (M) new TailBlock();
				break;
				
			case Disguise:
				mMove = (M) new Disguise();
				break;
				
			case Tail_Slam:
				mMove = (M) new JustDamageDealing();
				break;
				
			case Focus_Up:
				mMove = (M) new FocusUp();
				break;
				
			case Acid_Spit:
				mMove = (M) new DamageAndStatus(StatusEffects.Poison, 0.95);
				break;
				
			case Poison_Bite:
				mMove = (M) new DamageAndStatus(StatusEffects.Poison, 0.75);
				break;
				
			case Poisonous_Slam:
				mMove = (M) new DamageAndStatus(StatusEffects.Poison, 0.25);
				break;
				
			case Zap:
				mMove = (M) new DamageAndStatus(StatusEffects.Paralysis, 0.7);
				break;
				
			case Shock_Blast:
				mMove = (M) new DamageAndStatus(StatusEffects.Paralysis, 0.5);
				break;
				
			case Wire_Smack:
				mMove = (M) new JustDamageDealing();
				break;
				
			case Upgrade:
				mMove = (M) new Upgrade();
				break;
				
			case Factory_Reset:
				mMove = (M) new FactoryReset();
				break;
				
			case Thunder_Blast:
				mMove = (M) new DamageAndStatus(StatusEffects.Paralysis, 0.9);
				break;
				
			case Fiber_Optic:
				mMove = (M) new FiberOptic();
				break;
				
			case Wired_Mess:
				mMove = (M) new WiredMess();
				break;
				
			case Voltage_Overload:
				mMove = (M) new DamageAndStatus(StatusEffects.Paralysis, 0.6);
				break;
			
			case Ice_Whip:
				mMove = (M) new IceWhip();
				break;
				
			case Body_Slam:
				mMove = (M) new BodySlam();
				break;
				
			case Freezing_Blast:
				mMove = (M) new FreezingBlast();
				break;
				
			case Ice_Layer:
				mMove = (M) new IceLayer();
				break;
				
			case Ice_Spike:
				mMove = (M) new IceSpike();
				break;
				
			case Deep_Freeze:
				mMove = (M) new DeepFreeze();
				break;
				
			case Forceful_Slam:
				mMove = (M) new ForcefulSlam();
				break;

			default:
				throw new IllegalStateException("The variable \"moveId\" was not found. Please add it to the list.");
		}

		mMove.setMoveId(mMoveId);
	}

	private boolean buildIsComplete()
	{
		return mMove.canCreate();
	}

}
