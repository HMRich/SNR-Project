package application;

import java.util.ArrayList;

import application.controllers.LoggerController;
import application.enums.AttackEffectiveness;
import application.enums.LoggingTypes;
import application.enums.Type;
import application.interfaces.IAnature;
import application.interfaces.IMove;

public class TypeAdvantage
{
	/*
	 * PUBLIC METHODS
	 */

	public static AttackEffectiveness anatureEffectiveness(IAnature attacker, IAnature defender)
	{
		AttackEffectiveness attackEffectiveness = AttackEffectiveness.NotSet;

		ArrayList<Type> attackerTypes = attacker.getTypes();
		ArrayList<Type> defenderTypes = defender.getTypes();

		for(Type attackerType : attackerTypes)
		{
			for(Type defenderType : defenderTypes)
			{
				attackEffectiveness = checkAdvantage(attackerType, defenderType, attackEffectiveness);
			}
		}

		if(attackEffectiveness == AttackEffectiveness.NotSet)
		{
			LoggerController.logEvent(LoggingTypes.Error, "attackerEffectiveness was not set. Take a look at the logic for type advantage.");
			return AttackEffectiveness.Error;
		}

		return attackEffectiveness;
	}

	public static AttackEffectiveness moveEffectiveness(IMove attackerMove, IAnature defender)
	{
		AttackEffectiveness attackEffectiveness = AttackEffectiveness.NotSet;

		Type attackMoveType = attackerMove.getType();
		ArrayList<Type> defenderTypes = defender.getTypes();

		for(Type defenderType : defenderTypes)
		{
			attackEffectiveness = checkAdvantage(attackMoveType, defenderType, attackEffectiveness);
		}

		return attackEffectiveness;
	}

	public static AttackEffectiveness parseInt(int value)
	{
		switch(value)
		{
			case 0:
				return AttackEffectiveness.NoEffect;

			case 1:
				return AttackEffectiveness.NotEffective;

			case 2:
				return AttackEffectiveness.Normal;

			case 3:
				return AttackEffectiveness.SuperEffective;

			default:
				LoggerController.logEvent(LoggingTypes.Error,
						"IllegalArgumentException in TypeAdvantage.java Method: parseInt(int value), passed value was not within the range 0 - 3.");
				return AttackEffectiveness.Error;
		}
	}

	public static AttackEffectiveness decrementEffectiveness(AttackEffectiveness value)
	{
		switch(value)
		{
			case NoEffect:
				return AttackEffectiveness.NoEffect;

			case NotEffective:
				return AttackEffectiveness.NoEffect;

			case Normal:
				return AttackEffectiveness.NotEffective;

			case SuperEffective:
				return AttackEffectiveness.Normal;

			default:
				LoggerController.logEvent(LoggingTypes.Error,
						"IllegalArgumentException in TypeAdvantage.java Method: decrementEffectiveness(AttackEffectiveness value), passed value was not valid. Value was "
								+ value.toString() + ".");
				return AttackEffectiveness.Error;
		}
	}

	// TODO Is this even used?
	public static AttackEffectiveness incrementEffectiveness(AttackEffectiveness value)
	{
		switch(value)
		{
			case NoEffect:
				return AttackEffectiveness.NotEffective;

			case NotEffective:
				return AttackEffectiveness.Normal;

			case Normal:
				return AttackEffectiveness.SuperEffective;

			case SuperEffective:
				return AttackEffectiveness.SuperEffective;

			default:
				LoggerController.logEvent(LoggingTypes.Error,
						"IllegalArgumentException in TypeAdvantage.java Method: incrementEffectiveness(AttackEffectiveness value), passed value was not valid. Value was "
								+ value.toString() + ".");
				return AttackEffectiveness.Error;
		}
	}

	// TODO Is this even used?
	public int compareAdvantage(AttackEffectiveness effectivenessToCheck, AttackEffectiveness effectivenessToCheckAgainst)
	{
		if(effectivenessToCheck == effectivenessToCheckAgainst)
		{
			return 0;
		}

		AttackEffectiveness returnedEffectiveness = compareEffectiveness(effectivenessToCheck, effectivenessToCheckAgainst);

		if(returnedEffectiveness == effectivenessToCheck)
		{
			return -1;
		}

		else
		{
			return 1;
		}
	}

	public static boolean isNoEffect(AttackEffectiveness effectiveness)
	{
		return effectiveness == AttackEffectiveness.NoEffect;
	}

	public static boolean isAboveNoEffect(AttackEffectiveness effectiveness)
	{
		return !isNoEffect(effectiveness);
	}

	public static boolean isAboveNotEffective(AttackEffectiveness effectiveness)
	{
		return effectiveness != AttackEffectiveness.NotEffective && isAboveNoEffect(effectiveness);
	}

	public static boolean isAboveNormal(AttackEffectiveness effectiveness)
	{
		return effectiveness != AttackEffectiveness.Normal && isAboveNotEffective(effectiveness);
	}

	/*
	 * PRIVATE METHODS
	 */

	private static AttackEffectiveness checkAdvantage(Type attackerType, Type defenderType, AttackEffectiveness attackEffectiveness)
	{
		switch(attackerType)
		{
			case Normal:
				return normalType(defenderType, attackEffectiveness);

			case Fire:
				return fireType(defenderType, attackEffectiveness);

			case Fighting:
				return fightingType(defenderType, attackEffectiveness);

			case Water:
				return waterType(defenderType, attackEffectiveness);

			case Flying:
				return flyingType(defenderType, attackEffectiveness);

			case Grass:
				return grassType(defenderType, attackEffectiveness);

			case Poison:
				return poisonType(defenderType, attackEffectiveness);

			case Electric:
				return electricType(defenderType, attackEffectiveness);

			case Ground:
				return groundType(defenderType, attackEffectiveness);

			case Psychic:
				return psychicType(defenderType, attackEffectiveness);

			case Rock:
				return rockType(defenderType, attackEffectiveness);

			case Ice:
				return iceType(defenderType, attackEffectiveness);

			case Bug:
				return bugType(defenderType, attackEffectiveness);

			case Dragon:
				return dragonType(defenderType, attackEffectiveness);

			case Ghost:
				return ghostType(defenderType, attackEffectiveness);

			case Dark:
				return darkType(defenderType, attackEffectiveness);

			case Steel:
				return steelType(defenderType, attackEffectiveness);

			case Fairy:
				return fairyType(defenderType, attackEffectiveness);

			default:
				LoggerController.logEvent(LoggingTypes.Error, "attackerType was not available. Was the type " + attackEffectiveness.toString() + " missing?");
				return AttackEffectiveness.Error;
		}
	}

	private static AttackEffectiveness compareEffectiveness(AttackEffectiveness newEffectiveness, AttackEffectiveness currentEffectiveness)
	{
		switch(newEffectiveness)
		{
			case NoEffect:
				return AttackEffectiveness.NoEffect;
	
			case NotEffective:
				if(currentEffectiveness == AttackEffectiveness.NoEffect)
				{
					return AttackEffectiveness.NoEffect;
				}
	
				return AttackEffectiveness.NotEffective;
	
			case Normal:
				if(currentEffectiveness == AttackEffectiveness.NoEffect)
				{
					return AttackEffectiveness.NoEffect;
				}
	
				else if(currentEffectiveness == AttackEffectiveness.NotEffective)
				{
					return AttackEffectiveness.NotEffective;
				}
	
				return AttackEffectiveness.Normal;
	
			case SuperEffective:
				if(currentEffectiveness == AttackEffectiveness.NoEffect)
				{
					return AttackEffectiveness.NoEffect;
				}
	
				else if(currentEffectiveness == AttackEffectiveness.NotEffective)
				{
					return AttackEffectiveness.NotEffective;
				}
	
				else if(currentEffectiveness == AttackEffectiveness.Normal)
				{
					return AttackEffectiveness.Normal;
				}
	
				return AttackEffectiveness.SuperEffective;
	
			case Error:
				return AttackEffectiveness.Error;
	
			default:
				LoggerController.logEvent(LoggingTypes.Error, "currentEffectiveness was not available. Was the effectiveness " + currentEffectiveness.toString()
						+ " missing? Setting Attack Effectiveness to \"Error\".");
				return AttackEffectiveness.Error;
		}
	}

	private static AttackEffectiveness normalType(Type defenderType, AttackEffectiveness attackEffectiveness)
	{
		switch(defenderType)
		{
			case Rock:
				return compareEffectiveness(AttackEffectiveness.NotEffective, attackEffectiveness);

			case Ghost:
				return compareEffectiveness(AttackEffectiveness.NoEffect, attackEffectiveness);

			case Steel:
				return compareEffectiveness(AttackEffectiveness.NotEffective, attackEffectiveness);

			default:
				return compareEffectiveness(AttackEffectiveness.Normal, attackEffectiveness);
		}
	}

	private static AttackEffectiveness fireType(Type defenderType, AttackEffectiveness attackEffectiveness)
	{
		switch(defenderType)
		{
			case Fire:
				return compareEffectiveness(AttackEffectiveness.NotEffective, attackEffectiveness);

			case Water:
				return compareEffectiveness(AttackEffectiveness.NotEffective, attackEffectiveness);

			case Grass:
				return compareEffectiveness(AttackEffectiveness.SuperEffective, attackEffectiveness);

			case Ice:
				return compareEffectiveness(AttackEffectiveness.SuperEffective, attackEffectiveness);

			case Bug:
				return compareEffectiveness(AttackEffectiveness.SuperEffective, attackEffectiveness);

			case Rock:
				return compareEffectiveness(AttackEffectiveness.NotEffective, attackEffectiveness);

			case Dragon:
				return compareEffectiveness(AttackEffectiveness.NotEffective, attackEffectiveness);

			case Steel:
				return compareEffectiveness(AttackEffectiveness.SuperEffective, attackEffectiveness);

			default:
				return compareEffectiveness(AttackEffectiveness.Normal, attackEffectiveness);
		}
	}

	private static AttackEffectiveness waterType(Type defenderType, AttackEffectiveness attackEffectiveness)
	{
		switch(defenderType)
		{
			case Fire:
				return compareEffectiveness(AttackEffectiveness.SuperEffective, attackEffectiveness);

			case Water:
				return compareEffectiveness(AttackEffectiveness.NotEffective, attackEffectiveness);

			case Grass:
				return compareEffectiveness(AttackEffectiveness.NotEffective, attackEffectiveness);

			case Ground:
				return compareEffectiveness(AttackEffectiveness.SuperEffective, attackEffectiveness);

			case Rock:
				return compareEffectiveness(AttackEffectiveness.SuperEffective, attackEffectiveness);

			case Dragon:
				return compareEffectiveness(AttackEffectiveness.NotEffective, attackEffectiveness);

			default:
				return compareEffectiveness(AttackEffectiveness.Normal, attackEffectiveness);
		}
	}

	private static AttackEffectiveness electricType(Type defenderType, AttackEffectiveness attackEffectiveness)
	{
		switch(defenderType)
		{
			case Water:
				return compareEffectiveness(AttackEffectiveness.SuperEffective, attackEffectiveness);

			case Electric:
				return compareEffectiveness(AttackEffectiveness.NotEffective, attackEffectiveness);

			case Grass:
				return compareEffectiveness(AttackEffectiveness.NotEffective, attackEffectiveness);

			case Ground:
				return compareEffectiveness(AttackEffectiveness.NoEffect, attackEffectiveness);

			case Flying:
				return compareEffectiveness(AttackEffectiveness.SuperEffective, attackEffectiveness);

			case Dragon:
				return compareEffectiveness(AttackEffectiveness.NotEffective, attackEffectiveness);

			default:
				return compareEffectiveness(AttackEffectiveness.Normal, attackEffectiveness);
		}
	}

	private static AttackEffectiveness grassType(Type defenderType, AttackEffectiveness attackEffectiveness)
	{
		switch(defenderType)
		{
			case Fire:
				return compareEffectiveness(AttackEffectiveness.NotEffective, attackEffectiveness);

			case Water:
				return compareEffectiveness(AttackEffectiveness.SuperEffective, attackEffectiveness);

			case Grass:
				return compareEffectiveness(AttackEffectiveness.NotEffective, attackEffectiveness);

			case Poison:
				return compareEffectiveness(AttackEffectiveness.NotEffective, attackEffectiveness);

			case Ground:
				return compareEffectiveness(AttackEffectiveness.SuperEffective, attackEffectiveness);

			case Flying:
				return compareEffectiveness(AttackEffectiveness.NotEffective, attackEffectiveness);

			case Bug:
				return compareEffectiveness(AttackEffectiveness.NotEffective, attackEffectiveness);

			case Rock:
				return compareEffectiveness(AttackEffectiveness.SuperEffective, attackEffectiveness);

			case Dragon:
				return compareEffectiveness(AttackEffectiveness.NotEffective, attackEffectiveness);

			case Steel:
				return compareEffectiveness(AttackEffectiveness.NotEffective, attackEffectiveness);

			default:
				return compareEffectiveness(AttackEffectiveness.Normal, attackEffectiveness);
		}
	}

	private static AttackEffectiveness iceType(Type defenderType, AttackEffectiveness attackEffectiveness)
	{
		switch(defenderType)
		{
			case Fire:
				return compareEffectiveness(AttackEffectiveness.NotEffective, attackEffectiveness);

			case Water:
				return compareEffectiveness(AttackEffectiveness.NotEffective, attackEffectiveness);

			case Grass:
				return compareEffectiveness(AttackEffectiveness.SuperEffective, attackEffectiveness);

			case Ice:
				return compareEffectiveness(AttackEffectiveness.NotEffective, attackEffectiveness);

			case Ground:
				return compareEffectiveness(AttackEffectiveness.SuperEffective, attackEffectiveness);

			case Flying:
				return compareEffectiveness(AttackEffectiveness.SuperEffective, attackEffectiveness);

			case Dragon:
				return compareEffectiveness(AttackEffectiveness.SuperEffective, attackEffectiveness);

			case Steel:
				return compareEffectiveness(AttackEffectiveness.NotEffective, attackEffectiveness);

			default:
				return compareEffectiveness(AttackEffectiveness.Normal, attackEffectiveness);
		}
	}

	private static AttackEffectiveness fightingType(Type defenderType, AttackEffectiveness attackEffectiveness)
	{
		switch(defenderType)
		{
			case Normal:
				return compareEffectiveness(AttackEffectiveness.SuperEffective, attackEffectiveness);

			case Ice:
				return compareEffectiveness(AttackEffectiveness.SuperEffective, attackEffectiveness);

			case Poison:
				return compareEffectiveness(AttackEffectiveness.NotEffective, attackEffectiveness);

			case Flying:
				return compareEffectiveness(AttackEffectiveness.NotEffective, attackEffectiveness);

			case Psychic:
				return compareEffectiveness(AttackEffectiveness.NotEffective, attackEffectiveness);

			case Bug:
				return compareEffectiveness(AttackEffectiveness.NotEffective, attackEffectiveness);

			case Rock:
				return compareEffectiveness(AttackEffectiveness.SuperEffective, attackEffectiveness);

			case Ghost:
				return compareEffectiveness(AttackEffectiveness.NoEffect, attackEffectiveness);

			case Dark:
				return compareEffectiveness(AttackEffectiveness.SuperEffective, attackEffectiveness);

			case Steel:
				return compareEffectiveness(AttackEffectiveness.SuperEffective, attackEffectiveness);

			case Fairy:
				return compareEffectiveness(AttackEffectiveness.NotEffective, attackEffectiveness);

			default:
				return compareEffectiveness(AttackEffectiveness.Normal, attackEffectiveness);
		}
	}

	private static AttackEffectiveness poisonType(Type defenderType, AttackEffectiveness attackEffectiveness)
	{
		switch(defenderType)
		{
			case Grass:
				return compareEffectiveness(AttackEffectiveness.SuperEffective, attackEffectiveness);

			case Poison:
				return compareEffectiveness(AttackEffectiveness.NotEffective, attackEffectiveness);

			case Ground:
				return compareEffectiveness(AttackEffectiveness.NotEffective, attackEffectiveness);

			case Rock:
				return compareEffectiveness(AttackEffectiveness.NotEffective, attackEffectiveness);

			case Ghost:
				return compareEffectiveness(AttackEffectiveness.NotEffective, attackEffectiveness);

			case Steel:
				return compareEffectiveness(AttackEffectiveness.NoEffect, attackEffectiveness);

			case Fairy:
				return compareEffectiveness(AttackEffectiveness.SuperEffective, attackEffectiveness);

			default:
				return compareEffectiveness(AttackEffectiveness.Normal, attackEffectiveness);
		}
	}

	private static AttackEffectiveness groundType(Type defenderType, AttackEffectiveness attackEffectiveness)
	{
		switch(defenderType)
		{
			case Fire:
				return compareEffectiveness(AttackEffectiveness.SuperEffective, attackEffectiveness);

			case Electric:
				return compareEffectiveness(AttackEffectiveness.SuperEffective, attackEffectiveness);

			case Grass:
				return compareEffectiveness(AttackEffectiveness.NotEffective, attackEffectiveness);

			case Poison:
				return compareEffectiveness(AttackEffectiveness.SuperEffective, attackEffectiveness);

			case Flying:
				return compareEffectiveness(AttackEffectiveness.NoEffect, attackEffectiveness);

			case Bug:
				return compareEffectiveness(AttackEffectiveness.NotEffective, attackEffectiveness);

			case Rock:
				return compareEffectiveness(AttackEffectiveness.SuperEffective, attackEffectiveness);

			case Steel:
				return compareEffectiveness(AttackEffectiveness.SuperEffective, attackEffectiveness);

			default:
				return compareEffectiveness(AttackEffectiveness.Normal, attackEffectiveness);
		}
	}

	private static AttackEffectiveness flyingType(Type defenderType, AttackEffectiveness attackEffectiveness)
	{
		switch(defenderType)
		{
			case Electric:
				return compareEffectiveness(AttackEffectiveness.NotEffective, attackEffectiveness);

			case Grass:
				return compareEffectiveness(AttackEffectiveness.SuperEffective, attackEffectiveness);

			case Fighting:
				return compareEffectiveness(AttackEffectiveness.SuperEffective, attackEffectiveness);

			case Bug:
				return compareEffectiveness(AttackEffectiveness.SuperEffective, attackEffectiveness);

			case Rock:
				return compareEffectiveness(AttackEffectiveness.NotEffective, attackEffectiveness);

			case Steel:
				return compareEffectiveness(AttackEffectiveness.NotEffective, attackEffectiveness);

			default:
				return compareEffectiveness(AttackEffectiveness.Normal, attackEffectiveness);
		}
	}

	private static AttackEffectiveness psychicType(Type defenderType, AttackEffectiveness attackEffectiveness)
	{
		switch(defenderType)
		{
			case Fighting:
				return compareEffectiveness(AttackEffectiveness.SuperEffective, attackEffectiveness);

			case Poison:
				return compareEffectiveness(AttackEffectiveness.SuperEffective, attackEffectiveness);

			case Psychic:
				return compareEffectiveness(AttackEffectiveness.NotEffective, attackEffectiveness);

			case Dark:
				return compareEffectiveness(AttackEffectiveness.NoEffect, attackEffectiveness);

			case Steel:
				return compareEffectiveness(AttackEffectiveness.NotEffective, attackEffectiveness);

			default:
				return compareEffectiveness(AttackEffectiveness.Normal, attackEffectiveness);
		}
	}

	private static AttackEffectiveness bugType(Type defenderType, AttackEffectiveness attackEffectiveness)
	{
		switch(defenderType)
		{
			case Fire:
				return compareEffectiveness(AttackEffectiveness.NotEffective, attackEffectiveness);

			case Grass:
				return compareEffectiveness(AttackEffectiveness.SuperEffective, attackEffectiveness);

			case Fighting:
				return compareEffectiveness(AttackEffectiveness.NotEffective, attackEffectiveness);

			case Poison:
				return compareEffectiveness(AttackEffectiveness.NotEffective, attackEffectiveness);

			case Flying:
				return compareEffectiveness(AttackEffectiveness.NotEffective, attackEffectiveness);

			case Psychic:
				return compareEffectiveness(AttackEffectiveness.SuperEffective, attackEffectiveness);

			case Ghost:
				return compareEffectiveness(AttackEffectiveness.NotEffective, attackEffectiveness);

			case Dark:
				return compareEffectiveness(AttackEffectiveness.SuperEffective, attackEffectiveness);

			case Steel:
				return compareEffectiveness(AttackEffectiveness.NotEffective, attackEffectiveness);

			case Fairy:
				return compareEffectiveness(AttackEffectiveness.NotEffective, attackEffectiveness);

			default:
				return compareEffectiveness(AttackEffectiveness.Normal, attackEffectiveness);
		}
	}

	private static AttackEffectiveness rockType(Type defenderType, AttackEffectiveness attackEffectiveness)
	{
		switch(defenderType)
		{
			case Fire:
				return compareEffectiveness(AttackEffectiveness.SuperEffective, attackEffectiveness);

			case Ice:
				return compareEffectiveness(AttackEffectiveness.SuperEffective, attackEffectiveness);

			case Fighting:
				return compareEffectiveness(AttackEffectiveness.NotEffective, attackEffectiveness);

			case Ground:
				return compareEffectiveness(AttackEffectiveness.NotEffective, attackEffectiveness);

			case Flying:
				return compareEffectiveness(AttackEffectiveness.SuperEffective, attackEffectiveness);

			case Bug:
				return compareEffectiveness(AttackEffectiveness.SuperEffective, attackEffectiveness);

			case Steel:
				return compareEffectiveness(AttackEffectiveness.NotEffective, attackEffectiveness);

			default:
				return compareEffectiveness(AttackEffectiveness.Normal, attackEffectiveness);
		}
	}

	private static AttackEffectiveness ghostType(Type defenderType, AttackEffectiveness attackEffectiveness)
	{
		switch(defenderType)
		{
			case Normal:
				return compareEffectiveness(AttackEffectiveness.NoEffect, attackEffectiveness);

			case Psychic:
				return compareEffectiveness(AttackEffectiveness.SuperEffective, attackEffectiveness);

			case Ghost:
				return compareEffectiveness(AttackEffectiveness.SuperEffective, attackEffectiveness);

			case Dark:
				return compareEffectiveness(AttackEffectiveness.NotEffective, attackEffectiveness);

			default:
				return compareEffectiveness(AttackEffectiveness.Normal, attackEffectiveness);
		}
	}

	private static AttackEffectiveness dragonType(Type defenderType, AttackEffectiveness attackEffectiveness)
	{
		switch(defenderType)
		{
			case Dragon:
				return compareEffectiveness(AttackEffectiveness.SuperEffective, attackEffectiveness);

			case Steel:
				return compareEffectiveness(AttackEffectiveness.NotEffective, attackEffectiveness);

			case Fairy:
				return compareEffectiveness(AttackEffectiveness.NoEffect, attackEffectiveness);

			default:
				return compareEffectiveness(AttackEffectiveness.Normal, attackEffectiveness);
		}
	}

	private static AttackEffectiveness darkType(Type defenderType, AttackEffectiveness attackEffectiveness)
	{
		switch(defenderType)
		{
			case Fighting:
				return compareEffectiveness(AttackEffectiveness.NotEffective, attackEffectiveness);

			case Psychic:
				return compareEffectiveness(AttackEffectiveness.SuperEffective, attackEffectiveness);

			case Ghost:
				return compareEffectiveness(AttackEffectiveness.SuperEffective, attackEffectiveness);

			case Dark:
				return compareEffectiveness(AttackEffectiveness.NotEffective, attackEffectiveness);

			case Fairy:
				return compareEffectiveness(AttackEffectiveness.NotEffective, attackEffectiveness);

			default:
				return compareEffectiveness(AttackEffectiveness.Normal, attackEffectiveness);
		}
	}

	private static AttackEffectiveness steelType(Type defenderType, AttackEffectiveness attackEffectiveness)
	{
		switch(defenderType)
		{
			case Fire:
				return compareEffectiveness(AttackEffectiveness.NotEffective, attackEffectiveness);

			case Water:
				return compareEffectiveness(AttackEffectiveness.NotEffective, attackEffectiveness);

			case Electric:
				return compareEffectiveness(AttackEffectiveness.NotEffective, attackEffectiveness);

			case Ice:
				return compareEffectiveness(AttackEffectiveness.SuperEffective, attackEffectiveness);

			case Rock:
				return compareEffectiveness(AttackEffectiveness.SuperEffective, attackEffectiveness);

			case Steel:
				return compareEffectiveness(AttackEffectiveness.NotEffective, attackEffectiveness);

			case Fairy:
				return compareEffectiveness(AttackEffectiveness.SuperEffective, attackEffectiveness);

			default:
				return compareEffectiveness(AttackEffectiveness.Normal, attackEffectiveness);
		}
	}

	private static AttackEffectiveness fairyType(Type defenderType, AttackEffectiveness attackEffectiveness)
	{
		switch(defenderType)
		{
			case Fire:
				return compareEffectiveness(AttackEffectiveness.NotEffective, attackEffectiveness);

			case Fighting:
				return compareEffectiveness(AttackEffectiveness.SuperEffective, attackEffectiveness);

			case Poison:
				return compareEffectiveness(AttackEffectiveness.NotEffective, attackEffectiveness);

			case Dragon:
				return compareEffectiveness(AttackEffectiveness.SuperEffective, attackEffectiveness);

			case Dark:
				return compareEffectiveness(AttackEffectiveness.SuperEffective, attackEffectiveness);

			case Steel:
				return compareEffectiveness(AttackEffectiveness.NotEffective, attackEffectiveness);

			default:
				return compareEffectiveness(AttackEffectiveness.Normal, attackEffectiveness);
		}
	}
}
