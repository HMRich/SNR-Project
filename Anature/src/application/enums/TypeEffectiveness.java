package application.enums;

import application.controllers.LoggerController;
import application.interfaces.IAnature;
import application.interfaces.IMove;

public enum TypeEffectiveness
{
	Not_Set("", -1),
	No_Effect("No Effect", 0),
	Seriously_Not_Effective("Seriously Not Effective", 0.25),
	Not_Effective("Not Effective", 0.5),
	Normal("Noraml", 1),
	Super_Effective("Super Effective", 2),
	Extremely_Effective("Extremely Effective", 4);

	private final String mName;
	private final double mEffectiveness;

	private TypeEffectiveness(String name, double effectiveness)
	{
		mName = name;
		mEffectiveness = effectiveness;
	}

	/*
	 * PUBLIC GETS
	 */

	public String toString()
	{
		return mName;
	}

	public double getEffectivenes()
	{
		return mEffectiveness;
	}

	public boolean isAtOrAboveThreshold(TypeEffectiveness threshold)
	{
		return getEffectivenes() >= threshold.getEffectivenes();
	}

	/*
	 * PUBLIC METHDOS
	 */

	public static TypeEffectiveness typeEffectiveness(IAnature source, IAnature target)
	{
		if(source.getPrimaryType() == Type.NotSet)
		{
			throw new IllegalArgumentException("Target's tpye was " + source.getPrimaryType() + ".");
		}

		if(target.getPrimaryType() == Type.NotSet)
		{
			throw new IllegalArgumentException("Target's tpye was " + target.getPrimaryType() + ".");
		}

		Type sourceType = source.getPrimaryType();
		Type targetPriamryType = target.getPrimaryType();
		Type targetSecondaryType = target.getSecondaryType();

		TypeEffectiveness targetPrimaryResult = checkEffectiveness(sourceType, targetPriamryType);
		TypeEffectiveness targetSecondaryResult = checkEffectiveness(sourceType, targetSecondaryType);

		return meshEffectiveness(targetPrimaryResult, targetSecondaryResult);
	}

	public static TypeEffectiveness typeEffectiveness(IMove source, IAnature target)
	{
		if(source.getType() == Type.NotSet)
		{
			throw new IllegalArgumentException("Source move's type was " + source.getType() + ".");
		}

		if(target.getPrimaryType() == Type.NotSet)
		{
			throw new IllegalArgumentException("Target's tpye was " + target.getPrimaryType() + ".");
		}

		Type sourceType = source.getType();
		Type targetPriamryType = target.getPrimaryType();
		Type targetSecondaryType = target.getSecondaryType();

		TypeEffectiveness targetPrimaryResult = checkEffectiveness(sourceType, targetPriamryType);
		TypeEffectiveness targetSecondaryResult = checkEffectiveness(sourceType, targetSecondaryType);

		return meshEffectiveness(targetPrimaryResult, targetSecondaryResult);
	}

	public static TypeEffectiveness decrementEffectiveness(TypeEffectiveness typeEffectiveness)
	{
		switch(typeEffectiveness)
		{
			case Extremely_Effective:
				return Super_Effective;

			case Super_Effective:
				return Normal;

			case Normal:
				return Not_Effective;

			case Not_Effective:
				return Seriously_Not_Effective;

			case Seriously_Not_Effective:
				return No_Effect;

			default:
				return No_Effect;
		}
	}

	/*
	 * PRIVATE METHODS
	 */

	private static TypeEffectiveness meshEffectiveness(TypeEffectiveness primaryResult, TypeEffectiveness secondaryResult)
	{
		return parseDouble(primaryResult.getEffectivenes() * secondaryResult.getEffectivenes());
	}

	private static TypeEffectiveness parseDouble(double number)
	{
		if(number <= 0)
		{
			return TypeEffectiveness.No_Effect;
		}

		else if(number <= 0.25)
		{
			return TypeEffectiveness.Seriously_Not_Effective;
		}

		else if(number <= 0.5)
		{
			return TypeEffectiveness.Not_Effective;
		}

		else if(number <= 1)
		{
			return TypeEffectiveness.Normal;
		}

		else if(number <= 2)
		{
			return TypeEffectiveness.Super_Effective;
		}

		else
		{
			return TypeEffectiveness.Extremely_Effective;
		}
	}

	private static TypeEffectiveness checkEffectiveness(Type sourceType, Type targetType)
	{
		switch(sourceType)
		{
			case Normal:
				return normalType(targetType);

			case Fire:
				return fireType(targetType);

			case Fighting:
				return fightingType(targetType);

			case Water:
				return waterType(targetType);

			case Flying:
				return flyingType(targetType);

			case Grass:
				return grassType(targetType);

			case Poison:
				return poisonType(targetType);

			case Electric:
				return electricType(targetType);

			case Ground:
				return groundType(targetType);

			case Psychic:
				return psychicType(targetType);

			case Rock:
				return rockType(targetType);

			case Ice:
				return iceType(targetType);

			case Bug:
				return bugType(targetType);

			case Dragon:
				return dragonType(targetType);

			case Ghost:
				return ghostType(targetType);

			case Dark:
				return darkType(targetType);

			case Steel:
				return steelType(targetType);

			case Fairy:
				return fairyType(targetType);

			default:
				LoggerController.logEvent(LoggingTypes.Error, "sourceType was not available. Was the type " + sourceType.toString() + " missing?");
				return TypeEffectiveness.Normal;
		}
	}

	private static TypeEffectiveness normalType(Type targetType)
	{
		switch(targetType)
		{
			case Rock:
				return TypeEffectiveness.Not_Effective;

			case Ghost:
				return TypeEffectiveness.No_Effect;

			case Steel:
				return TypeEffectiveness.Not_Effective;

			default:
				return TypeEffectiveness.Normal;
		}
	}

	private static TypeEffectiveness fireType(Type targetType)
	{
		switch(targetType)
		{
			case Fire:
				return TypeEffectiveness.Not_Effective;

			case Water:
				return TypeEffectiveness.Not_Effective;

			case Grass:
				return TypeEffectiveness.Super_Effective;

			case Ice:
				return TypeEffectiveness.Super_Effective;

			case Bug:
				return TypeEffectiveness.Super_Effective;

			case Rock:
				return TypeEffectiveness.Not_Effective;

			case Dragon:
				return TypeEffectiveness.Not_Effective;

			case Steel:
				return TypeEffectiveness.Super_Effective;

			default:
				return TypeEffectiveness.Normal;
		}
	}

	private static TypeEffectiveness waterType(Type targetType)
	{
		switch(targetType)
		{
			case Fire:
				return TypeEffectiveness.Super_Effective;

			case Water:
				return TypeEffectiveness.Not_Effective;

			case Grass:
				return TypeEffectiveness.Not_Effective;

			case Ground:
				return TypeEffectiveness.Super_Effective;

			case Rock:
				return TypeEffectiveness.Super_Effective;

			case Dragon:
				return TypeEffectiveness.Not_Effective;

			default:
				return TypeEffectiveness.Normal;
		}
	}

	private static TypeEffectiveness electricType(Type targetType)
	{
		switch(targetType)
		{
			case Water:
				return TypeEffectiveness.Super_Effective;

			case Electric:
				return TypeEffectiveness.Not_Effective;

			case Grass:
				return TypeEffectiveness.Not_Effective;

			case Ground:
				return TypeEffectiveness.No_Effect;

			case Flying:
				return TypeEffectiveness.Super_Effective;

			case Dragon:
				return TypeEffectiveness.Not_Effective;

			default:
				return TypeEffectiveness.Normal;
		}
	}

	private static TypeEffectiveness grassType(Type targetType)
	{
		switch(targetType)
		{
			case Fire:
				return TypeEffectiveness.Not_Effective;

			case Water:
				return TypeEffectiveness.Super_Effective;

			case Grass:
				return TypeEffectiveness.Not_Effective;

			case Poison:
				return TypeEffectiveness.Not_Effective;

			case Ground:
				return TypeEffectiveness.Super_Effective;

			case Flying:
				return TypeEffectiveness.Not_Effective;

			case Bug:
				return TypeEffectiveness.Not_Effective;

			case Rock:
				return TypeEffectiveness.Super_Effective;

			case Dragon:
				return TypeEffectiveness.Not_Effective;

			case Steel:
				return TypeEffectiveness.Not_Effective;

			default:
				return TypeEffectiveness.Normal;
		}
	}

	private static TypeEffectiveness iceType(Type targetType)
	{
		switch(targetType)
		{
			case Fire:
				return TypeEffectiveness.Not_Effective;

			case Water:
				return TypeEffectiveness.Not_Effective;

			case Grass:
				return TypeEffectiveness.Super_Effective;

			case Ice:
				return TypeEffectiveness.Not_Effective;

			case Ground:
				return TypeEffectiveness.Super_Effective;

			case Flying:
				return TypeEffectiveness.Super_Effective;

			case Dragon:
				return TypeEffectiveness.Super_Effective;

			case Steel:
				return TypeEffectiveness.Not_Effective;

			default:
				return TypeEffectiveness.Normal;
		}
	}

	private static TypeEffectiveness fightingType(Type targetType)
	{
		switch(targetType)
		{
			case Normal:
				return TypeEffectiveness.Super_Effective;

			case Ice:
				return TypeEffectiveness.Super_Effective;

			case Poison:
				return TypeEffectiveness.Not_Effective;

			case Flying:
				return TypeEffectiveness.Not_Effective;

			case Psychic:
				return TypeEffectiveness.Not_Effective;

			case Bug:
				return TypeEffectiveness.Not_Effective;

			case Rock:
				return TypeEffectiveness.Super_Effective;

			case Ghost:
				return TypeEffectiveness.No_Effect;

			case Dark:
				return TypeEffectiveness.Super_Effective;

			case Steel:
				return TypeEffectiveness.Super_Effective;

			case Fairy:
				return TypeEffectiveness.Not_Effective;

			default:
				return TypeEffectiveness.Normal;
		}
	}

	private static TypeEffectiveness poisonType(Type targetType)
	{
		switch(targetType)
		{
			case Grass:
				return TypeEffectiveness.Super_Effective;

			case Poison:
				return TypeEffectiveness.Not_Effective;

			case Ground:
				return TypeEffectiveness.Not_Effective;

			case Rock:
				return TypeEffectiveness.Not_Effective;

			case Ghost:
				return TypeEffectiveness.Not_Effective;

			case Steel:
				return TypeEffectiveness.No_Effect;

			case Fairy:
				return TypeEffectiveness.Super_Effective;

			default:
				return TypeEffectiveness.Normal;
		}
	}

	private static TypeEffectiveness groundType(Type targetType)
	{
		switch(targetType)
		{
			case Fire:
				return TypeEffectiveness.Super_Effective;

			case Electric:
				return TypeEffectiveness.Super_Effective;

			case Grass:
				return TypeEffectiveness.Not_Effective;

			case Poison:
				return TypeEffectiveness.Super_Effective;

			case Flying:
				return TypeEffectiveness.No_Effect;

			case Bug:
				return TypeEffectiveness.Not_Effective;

			case Rock:
				return TypeEffectiveness.Super_Effective;

			case Steel:
				return TypeEffectiveness.Super_Effective;

			default:
				return TypeEffectiveness.Normal;
		}
	}

	private static TypeEffectiveness flyingType(Type targetType)
	{
		switch(targetType)
		{
			case Electric:
				return TypeEffectiveness.Not_Effective;

			case Grass:
				return TypeEffectiveness.Super_Effective;

			case Fighting:
				return TypeEffectiveness.Super_Effective;

			case Bug:
				return TypeEffectiveness.Super_Effective;

			case Rock:
				return TypeEffectiveness.Not_Effective;

			case Steel:
				return TypeEffectiveness.Not_Effective;

			default:
				return TypeEffectiveness.Normal;
		}
	}

	private static TypeEffectiveness psychicType(Type targetType)
	{
		switch(targetType)
		{
			case Fighting:
				return TypeEffectiveness.Super_Effective;

			case Poison:
				return TypeEffectiveness.Super_Effective;

			case Psychic:
				return TypeEffectiveness.Not_Effective;

			case Dark:
				return TypeEffectiveness.No_Effect;

			case Steel:
				return TypeEffectiveness.Not_Effective;

			default:
				return TypeEffectiveness.Normal;
		}
	}

	private static TypeEffectiveness bugType(Type targetType)
	{
		switch(targetType)
		{
			case Fire:
				return TypeEffectiveness.Not_Effective;

			case Grass:
				return TypeEffectiveness.Super_Effective;

			case Fighting:
				return TypeEffectiveness.Not_Effective;

			case Poison:
				return TypeEffectiveness.Not_Effective;

			case Flying:
				return TypeEffectiveness.Not_Effective;

			case Psychic:
				return TypeEffectiveness.Super_Effective;

			case Ghost:
				return TypeEffectiveness.Not_Effective;

			case Dark:
				return TypeEffectiveness.Super_Effective;

			case Steel:
				return TypeEffectiveness.Not_Effective;

			case Fairy:
				return TypeEffectiveness.Not_Effective;

			default:
				return TypeEffectiveness.Normal;
		}
	}

	private static TypeEffectiveness rockType(Type targetType)
	{
		switch(targetType)
		{
			case Fire:
				return TypeEffectiveness.Super_Effective;

			case Ice:
				return TypeEffectiveness.Super_Effective;

			case Fighting:
				return TypeEffectiveness.Not_Effective;

			case Ground:
				return TypeEffectiveness.Not_Effective;

			case Flying:
				return TypeEffectiveness.Super_Effective;

			case Bug:
				return TypeEffectiveness.Super_Effective;

			case Steel:
				return TypeEffectiveness.Not_Effective;

			default:
				return TypeEffectiveness.Normal;
		}
	}

	private static TypeEffectiveness ghostType(Type targetType)
	{
		switch(targetType)
		{
			case Normal:
				return TypeEffectiveness.No_Effect;

			case Psychic:
				return TypeEffectiveness.Super_Effective;

			case Ghost:
				return TypeEffectiveness.Super_Effective;

			case Dark:
				return TypeEffectiveness.Not_Effective;

			default:
				return TypeEffectiveness.Normal;
		}
	}

	private static TypeEffectiveness dragonType(Type targetType)
	{
		switch(targetType)
		{
			case Dragon:
				return TypeEffectiveness.Super_Effective;

			case Steel:
				return TypeEffectiveness.Not_Effective;

			case Fairy:
				return TypeEffectiveness.No_Effect;

			default:
				return TypeEffectiveness.Normal;
		}
	}

	private static TypeEffectiveness darkType(Type targetType)
	{
		switch(targetType)
		{
			case Fighting:
				return TypeEffectiveness.Not_Effective;

			case Psychic:
				return TypeEffectiveness.Super_Effective;

			case Ghost:
				return TypeEffectiveness.Super_Effective;

			case Dark:
				return TypeEffectiveness.Not_Effective;

			case Fairy:
				return TypeEffectiveness.Not_Effective;

			default:
				return TypeEffectiveness.Normal;
		}
	}

	private static TypeEffectiveness steelType(Type targetType)
	{
		switch(targetType)
		{
			case Fire:
				return TypeEffectiveness.Not_Effective;

			case Water:
				return TypeEffectiveness.Not_Effective;

			case Electric:
				return TypeEffectiveness.Not_Effective;

			case Ice:
				return TypeEffectiveness.Super_Effective;

			case Rock:
				return TypeEffectiveness.Super_Effective;

			case Steel:
				return TypeEffectiveness.Not_Effective;

			case Fairy:
				return TypeEffectiveness.Super_Effective;

			default:
				return TypeEffectiveness.Normal;
		}
	}

	private static TypeEffectiveness fairyType(Type targetType)
	{
		switch(targetType)
		{
			case Fire:
				return TypeEffectiveness.Not_Effective;

			case Fighting:
				return TypeEffectiveness.Super_Effective;

			case Poison:
				return TypeEffectiveness.Not_Effective;

			case Dragon:
				return TypeEffectiveness.Super_Effective;

			case Dark:
				return TypeEffectiveness.Super_Effective;

			case Steel:
				return TypeEffectiveness.Not_Effective;

			default:
				return TypeEffectiveness.Normal;
		}
	}

}
