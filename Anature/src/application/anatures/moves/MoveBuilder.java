package application.anatures.moves;

import application.anatures.moves.moves.DoublePunch;
import application.anatures.moves.moves.Flail;
import application.anatures.moves.moves.Flamethrower;
import application.anatures.moves.moves.Grumble;
import application.anatures.moves.moves.NullMove;
import application.anatures.moves.moves.PocketSand;
import application.anatures.moves.moves.SkipTurn;
import application.anatures.moves.moves.Tackle;
import application.enums.MoveIds;
import application.enums.Type;
import application.interfaces.IBuilder;
import application.anatures.moves.moves.AntlerShot;
import application.anatures.moves.moves.Cinder;
import application.anatures.moves.moves.Clop;
import application.anatures.moves.moves.Electrocution;
import application.anatures.moves.moves.ElectroSonic;
import application.anatures.moves.moves.FireCell;
import application.anatures.moves.moves.FireTorture;
import application.anatures.moves.moves.FlameBout;
import application.anatures.moves.moves.ForgottenAwakening;
import application.anatures.moves.moves.HealingWinds;
import application.anatures.moves.moves.Holler;
import application.anatures.moves.moves.Leen;
import application.anatures.moves.moves.LightMissile;
import application.anatures.moves.moves.Lightning;
import application.anatures.moves.moves.MagicalSpice;
import application.anatures.moves.moves.ScaryFace;
import application.anatures.moves.moves.Smash;
import application.anatures.moves.moves.SmogWave;
import application.anatures.moves.moves.StormyBreeze;



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
				
			case Antler_Shot:
				mMove = (M) new AntlerShot();
				break;
				
			case Cinder:
				mMove = (M) new Cinder();
				break;
				
			case Clop:
				mMove = (M) new Clop();
				break;
				
			case Electrocution:
				mMove = (M) new Electrocution();
				break;
				
			case Electro_Sonic:
				mMove = (M) new ElectroSonic();
				break;
				
			case Fire_Cell:
				mMove = (M) new FireCell();
				break;
				
			case Fire_Torture:
				mMove = (M) new FireTorture();
				break;
				
			case Flame_Bout:
				mMove = (M) new FlameBout();
				break;
				
			case Forgotten_Awakening:
				mMove = (M) new ForgottenAwakening();
				break;
				
			case Healing_Winds:
				mMove = (M) new HealingWinds();
				break;
				
			case Holler:
				mMove = (M) new Holler();
				break;
				
			case Leen:
				mMove = (M) new Leen();
				break;

			case NullMove:
				mMove = (M) new NullMove();
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
