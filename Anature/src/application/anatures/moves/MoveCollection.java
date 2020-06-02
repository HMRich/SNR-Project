package application.anatures.moves;

import java.util.ArrayList;
import java.util.Random;

import application.enums.AbilityIds;
import application.enums.MoveIds;
import application.enums.Stat;
import application.enums.StatusEffects;
import application.enums.Type;
import application.interfaces.IAnature;
import application.interfaces.stats.IStats;
import application.pools.AbilityPool;

public class MoveCollection
{
	/*
	 * STATIC SECTION
	 */

	private static MoveIds mMoveId = MoveIds.NullMove;
	private static MoveCollection mMoveCollection = new MoveCollection();
	private static Random mRandom = new Random();

	public static Move getMoveContext(MoveIds moveId)
	{
		if(moveId == null)
		{
			throw new IllegalArgumentException("Passed value \"moveId\" was null.");
		}

		return mMoveCollection.getMove(moveId);
	}

	public static Move getNullMove()
	{
		return mMoveCollection.getMove(MoveIds.NullMove);
	}

	/*
	 * INSTANCE SECTION
	 */

	private Move mNullMove;

	private MoveCollection()
	{
		mNullMove = new NullMove();
	}

	private Move getMove(MoveIds moveId)
	{
		// auto moveId set
		mMoveId = moveId;

		switch(moveId)
		{
			case Acid_Spit:
			case Body_Bash:
			case Body_Slam:
			case Coil:
			case Double_Punch:
			case Flamethrower:
			case Flood:
			case Ice_Whip:
			case Pounce:
			case Poison_Bite:
			case Poisonous_Slam:
			case Shock_Blast:
			case Tackle:
			case Tail_Slam:
			case Tail_Slap:
			case Tornado:
			case Thunder_Blast:
			case Wire_Smack:
			case Water_Toss:
			case Water_Blast:
			case Water_Fang:
			case Wing_Bash:
			case Voltage_Overload:
			case Zap:
				return new Attack();

			case Forceful_Slam:
			case Ice_Spike:
				return new SpecialAttack();

			case Grumble:
			case Hose_Down:
			case Freezing_Blast:
			case Focus_Up:
			case Fiber_Optic:
			case Disguise:
			case Deep_Freeze:
			case Ice_Layer:
			case Pocket_Sand:
			case Sharpen_Up:
				return new ApplyEffects();

			case Upgrade:
				return new Upgrade();

			case Wired_Mess:
				return new WiredMess();

			case Flail:
				return new Flail();

			case Factory_Reset:
				return new FactoryReset();

			case Skip_Turn:
				return new SkipTurn();

			case Tail_Block:
				return new TailBlock();

			case NullMove:
				return mNullMove;

			default:
				throw new IllegalStateException("A move was not returned by the MoveCollection.");
		}
	}

	/*
	 * PRIVATE METHODS
	 */

	private void applyUniqueEffects(MoveIds moveId, IAnature source, IAnature target)
	{
		applyIncreasedStats(moveId, source, target);
		applyDecreasedStats(moveId, source, target);
		applyStatusEffects(moveId, source, target);
	}

	private void applyIncreasedStats(MoveIds moveId, IAnature source, IAnature target)
	{
		IStats sourceStats = source.getStats();

		switch(moveId)
		{
			case Focus_Up:
				sourceStats.increaceTempStats(new Stat[] { Stat.Attack, Stat.Defense });
				break;

			case Ice_Layer:
				sourceStats.increaceTempStats(new Stat[] { Stat.Defense, Stat.SpecialDefense });
				break;

			case Sharpen_Up:
				sourceStats.increaceTempStats(new Stat[] { Stat.Attack, Stat.SpecialAttack });
				break;

			case Disguise:
				sourceStats.increaseTempStat(Stat.Evasion);
				break;

			case Fiber_Optic:
				sourceStats.increaseTempStat(Stat.Speed);
				break;

			default:
				break;
		}
	}

	private void applyDecreasedStats(MoveIds moveId, IAnature source, IAnature target)
	{
		IStats sourceStats = source.getStats();
		IStats targetStats = target.getStats();

		switch(moveId)
		{
			case Deep_Freeze:
				targetStats.decreaseTempStats(new Stat[] { Stat.Speed, Stat.Speed, Stat.Defense, Stat.SpecialDefense });
				sourceStats.decreaseTempStats(new Stat[] { Stat.Attack, Stat.Attack, Stat.Defense, Stat.Defense });
				break;

			case Hose_Down:
				targetStats.decreaseTempStats(new Stat[] { Stat.Defense, Stat.SpecialDefense });
				break;

			case Coil:
				if(mRandom.nextDouble() < 0.33)
					targetStats.decreaseTempStat(Stat.Speed);
				break;

			case Grumble:
				targetStats.decreaseTempStat(Stat.Attack);
				break;

			case Pocket_Sand:
			case Tornado:
			case Water_Toss:
				targetStats.decreaseTempStat(Stat.Accuracy);
				break;

			case Freezing_Blast:
				targetStats.decreaseTempStat(Stat.Speed);
				break;

			default:
				break;
		}
	}

	private void applyStatusEffects(MoveIds moveId, IAnature source, IAnature target)
	{
		StatusEffects targetStatus = target.getStatus();

		double threshold = 0;
		StatusEffects effect = StatusEffects.None;

		switch(moveId)
		{
			case Flamethrower:
				effect = StatusEffects.Burn;
				break;

			case Acid_Spit:
				threshold = 0.95;
				effect = StatusEffects.Poison;
				break;

			case Poison_Bite:
				threshold = 0.75;
				effect = StatusEffects.Poison;
				break;

			case Poisonous_Slam:
				threshold = 0.25;
				effect = StatusEffects.Poison;
				break;

			case Thunder_Blast:
				threshold = 0.9;
				effect = StatusEffects.Paralysis;
				break;

			case Shock_Blast:
				threshold = 0.5;
				effect = StatusEffects.Paralysis;
				break;

			case Voltage_Overload:
				threshold = 0.6;
				effect = StatusEffects.Paralysis;
				break;

			case Zap:
				threshold = 0.7;
				effect = StatusEffects.Paralysis;
				break;

			default:
				break;
		}

		if(!effect.equals(StatusEffects.None)
				&& targetStatus.equals(StatusEffects.None)
				&& statusVsTypeCheck(effect, target)
				&& mRandom.nextDouble() > threshold)
		{
			target.updateStatus(effect);
		}
	}

	private boolean statusVsTypeCheck(StatusEffects statusEffect, IAnature target)
	{
		ArrayList<Type> targetTypes = target.getTypes();

		switch(statusEffect)
		{
			case Burn:
				return !targetTypes.contains(Type.Fire);

			case Frozen:
				return !targetTypes.contains(Type.Ice);

			case Poison:
				return !targetTypes.contains(Type.Poison);

			case Paralysis:
				return !targetTypes.contains(Type.Electric);

			case Sleep:
				return !target.getAbility()
						.equals(AbilityPool.getAbility(AbilityIds.SleepDeprived));

			case None:
			case NotSet:

			default:
				return true;
		}
	}

	/*
	 * PRIVATE MOVE CLASSES
	 */

	private abstract class MoveWithId extends Move
	{
		private MoveWithId()
		{
			this.setMoveId(mMoveId);
		}
	}

	private class Attack extends MoveWithId
	{
		@Override
		public void activateMove(IAnature source, IAnature target)
		{
			target.takeDamage(calculateDamage(source, target, false));
			applyUniqueEffects(getMoveId(), source, target);
		}
	}

	private class SpecialAttack extends MoveWithId
	{
		@Override
		public void activateMove(IAnature source, IAnature target)
		{
			target.takeDamage(calculateDamage(source, target, true));
			applyUniqueEffects(getMoveId(), source, target);
		}
	}

	private class ApplyEffects extends MoveWithId
	{
		@Override
		public void activateMove(IAnature source, IAnature target)
		{
			applyUniqueEffects(getMoveId(), source, target);
		}
	}

	private class FactoryReset extends MoveWithId
	{
		@Override
		public void activateMove(IAnature source, IAnature target)
		{
			source.getStats()
					.resetTempStats();
			source.updateStatus(StatusEffects.None);
		}

	}

	private class SkipTurn extends MoveWithId
	{
		@Override
		public void activateMove(IAnature source, IAnature target)
		{

		}
	}

	private class NullMove extends SkipTurn
	{

	}

	/*
	 * UNIQUE MOVE CLASSES
	 */

	private class Flail extends MoveWithId
	{
		@Override
		public void activateMove(IAnature source, IAnature target)
		{
			int selfDamage = source.getStats()
					.getTotalHitPoints() / 4;
			source.takeDamage(selfDamage);
			target.takeDamage(calculateDamage(source, target, false));
		}

	}

	private class TailBlock extends MoveWithId
	{
		@Override
		public void activateMove(IAnature source, IAnature target)
		{
			// TODO Add in a protect invisible status and remove these stat changes
			source.getStats()
					.increaseTempDefense();
			source.getStats()
					.increaseTempSpecialDefense();
		}
	}

	private class WiredMess extends MoveWithId
	{
		public void activateMove(IAnature source, IAnature target)
		{
			int firstToDecrease = mRandom.nextInt(4) + 1;
			int secondToDecrease = findUnique(firstToDecrease);

			decreaseStat(firstToDecrease, target);
			decreaseStat(secondToDecrease, target);
		}

		private int findUnique(int firstToDecrease)
		{
			int toReturn = mRandom.nextInt(4) + 1;

			while(toReturn == firstToDecrease)
			{
				toReturn = mRandom.nextInt(4) + 1;
			}

			return toReturn;
		}

		private void decreaseStat(int statNumber, IAnature toLower)
		{
			IStats stats = toLower.getStats();

			switch(statNumber)
			{
				case 1:
					stats.decreaseTempAttack();
					break;

				case 2:
					stats.decreaseTempSpecialAttack();
					break;

				case 3:
					stats.decreaseTempDefense();
					break;

				case 4:
					stats.decreaseTempSpecialDefense();
					break;

				default:
					break;
			}
		}
	}

	private class Upgrade extends MoveWithId
	{
		public void activateMove(IAnature source, IAnature target)
		{
			IStats stats = source.getStats();
			double maxStageValue = calculateMaxStageValue(stats);

			if(stats.getTempAttack()
					.getModifier() < maxStageValue)
			{
				stats.increaseTempAttack();
			}

			if(stats.getTempDefense()
					.getModifier() < maxStageValue)
			{
				stats.increaseTempDefense();
			}

			if(stats.getTempSpecialAttack()
					.getModifier() < maxStageValue)
			{
				stats.increaseTempSpecialAttack();
			}

			if(stats.getTempSpecialDefense()
					.getModifier() < maxStageValue)
			{
				stats.increaseTempSpecialDefense();
			}
		}

		private double calculateMaxStageValue(IStats stats)
		{
			int level = stats.getLevel();

			if(level < 20)
			{
				return 2.0;
			}

			else if(level < 40)
			{
				return 2.5;
			}

			else if(level < 80)
			{
				return 3.5;
			}

			return 4.0;
		}
	}

}
