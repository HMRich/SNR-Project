package application.enums;

import application.controllers.LoggerController;
import application.interfaces.IAnature;
import application.interfaces.IMove;

public enum TypeEffectiveness
{
	NotSet("", -1),
	NoEffect("No Effect", 0),
	SeriouslyNotEffective("Seriously Not Effective", 0.25),
	NotEffective("Not Effective", 0.5),
	Normal("Noraml", 1),
	SuperEffective("Super Effective", 2),
	ExtremelyEffective("Extremely Effective", 4);

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
			case ExtremelyEffective:
				return SuperEffective;

			case SuperEffective:
				return Normal;

			case Normal:
				return NotEffective;

			case NotEffective:
				return SeriouslyNotEffective;

			case SeriouslyNotEffective:
				return NoEffect;

			default:
				return NoEffect;
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
			return TypeEffectiveness.NoEffect;
		}

		else if(number <= 0.25)
		{
			return TypeEffectiveness.SeriouslyNotEffective;
		}

		else if(number <= 0.5)
		{
			return TypeEffectiveness.NotEffective;
		}

		else if(number <= 1)
		{
			return TypeEffectiveness.Normal;
		}

		else if(number <= 2)
		{
			return TypeEffectiveness.SuperEffective;
		}

		else
		{
			return TypeEffectiveness.ExtremelyEffective;
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
				return TypeEffectiveness.NotEffective;

			case Ghost:
				return TypeEffectiveness.NoEffect;

			case Steel:
				return TypeEffectiveness.NotEffective;

			default:
				return TypeEffectiveness.Normal;
		}
	}

	private static TypeEffectiveness fireType(Type targetType)
	{
		switch(targetType)
		{
			case Fire:
				return TypeEffectiveness.NotEffective;

			case Water:
				return TypeEffectiveness.NotEffective;

			case Grass:
				return TypeEffectiveness.SuperEffective;

			case Ice:
				return TypeEffectiveness.SuperEffective;

			case Bug:
				return TypeEffectiveness.SuperEffective;

			case Rock:
				return TypeEffectiveness.NotEffective;

			case Dragon:
				return TypeEffectiveness.NotEffective;

			case Steel:
				return TypeEffectiveness.SuperEffective;

			default:
				return TypeEffectiveness.Normal;
		}
	}

	private static TypeEffectiveness waterType(Type targetType)
	{
		switch(targetType)
		{
			case Fire:
				return TypeEffectiveness.SuperEffective;

			case Water:
				return TypeEffectiveness.NotEffective;

			case Grass:
				return TypeEffectiveness.NotEffective;

			case Ground:
				return TypeEffectiveness.SuperEffective;

			case Rock:
				return TypeEffectiveness.SuperEffective;

			case Dragon:
				return TypeEffectiveness.NotEffective;

			default:
				return TypeEffectiveness.Normal;
		}
	}

	private static TypeEffectiveness electricType(Type targetType)
	{
		switch(targetType)
		{
			case Water:
				return TypeEffectiveness.SuperEffective;

			case Electric:
				return TypeEffectiveness.NotEffective;

			case Grass:
				return TypeEffectiveness.NotEffective;

			case Ground:
				return TypeEffectiveness.NoEffect;

			case Flying:
				return TypeEffectiveness.SuperEffective;

			case Dragon:
				return TypeEffectiveness.NotEffective;

			default:
				return TypeEffectiveness.Normal;
		}
	}

	private static TypeEffectiveness grassType(Type targetType)
	{
		switch(targetType)
		{
			case Fire:
				return TypeEffectiveness.NotEffective;

			case Water:
				return TypeEffectiveness.SuperEffective;

			case Grass:
				return TypeEffectiveness.NotEffective;

			case Poison:
				return TypeEffectiveness.NotEffective;

			case Ground:
				return TypeEffectiveness.SuperEffective;

			case Flying:
				return TypeEffectiveness.NotEffective;

			case Bug:
				return TypeEffectiveness.NotEffective;

			case Rock:
				return TypeEffectiveness.SuperEffective;

			case Dragon:
				return TypeEffectiveness.NotEffective;

			case Steel:
				return TypeEffectiveness.NotEffective;

			default:
				return TypeEffectiveness.Normal;
		}
	}

	private static TypeEffectiveness iceType(Type targetType)
	{
		switch(targetType)
		{
			case Fire:
				return TypeEffectiveness.NotEffective;

			case Water:
				return TypeEffectiveness.NotEffective;

			case Grass:
				return TypeEffectiveness.SuperEffective;

			case Ice:
				return TypeEffectiveness.NotEffective;

			case Ground:
				return TypeEffectiveness.SuperEffective;

			case Flying:
				return TypeEffectiveness.SuperEffective;

			case Dragon:
				return TypeEffectiveness.SuperEffective;

			case Steel:
				return TypeEffectiveness.NotEffective;

			default:
				return TypeEffectiveness.Normal;
		}
	}

	private static TypeEffectiveness fightingType(Type targetType)
	{
		switch(targetType)
		{
			case Normal:
				return TypeEffectiveness.SuperEffective;

			case Ice:
				return TypeEffectiveness.SuperEffective;

			case Poison:
				return TypeEffectiveness.NotEffective;

			case Flying:
				return TypeEffectiveness.NotEffective;

			case Psychic:
				return TypeEffectiveness.NotEffective;

			case Bug:
				return TypeEffectiveness.NotEffective;

			case Rock:
				return TypeEffectiveness.SuperEffective;

			case Ghost:
				return TypeEffectiveness.NoEffect;

			case Dark:
				return TypeEffectiveness.SuperEffective;

			case Steel:
				return TypeEffectiveness.SuperEffective;

			case Fairy:
				return TypeEffectiveness.NotEffective;

			default:
				return TypeEffectiveness.Normal;
		}
	}

	private static TypeEffectiveness poisonType(Type targetType)
	{
		switch(targetType)
		{
			case Grass:
				return TypeEffectiveness.SuperEffective;

			case Poison:
				return TypeEffectiveness.NotEffective;

			case Ground:
				return TypeEffectiveness.NotEffective;

			case Rock:
				return TypeEffectiveness.NotEffective;

			case Ghost:
				return TypeEffectiveness.NotEffective;

			case Steel:
				return TypeEffectiveness.NoEffect;

			case Fairy:
				return TypeEffectiveness.SuperEffective;

			default:
				return TypeEffectiveness.Normal;
		}
	}

	private static TypeEffectiveness groundType(Type targetType)
	{
		switch(targetType)
		{
			case Fire:
				return TypeEffectiveness.SuperEffective;

			case Electric:
				return TypeEffectiveness.SuperEffective;

			case Grass:
				return TypeEffectiveness.NotEffective;

			case Poison:
				return TypeEffectiveness.SuperEffective;

			case Flying:
				return TypeEffectiveness.NoEffect;

			case Bug:
				return TypeEffectiveness.NotEffective;

			case Rock:
				return TypeEffectiveness.SuperEffective;

			case Steel:
				return TypeEffectiveness.SuperEffective;

			default:
				return TypeEffectiveness.Normal;
		}
	}

	private static TypeEffectiveness flyingType(Type targetType)
	{
		switch(targetType)
		{
			case Electric:
				return TypeEffectiveness.NotEffective;

			case Grass:
				return TypeEffectiveness.SuperEffective;

			case Fighting:
				return TypeEffectiveness.SuperEffective;

			case Bug:
				return TypeEffectiveness.SuperEffective;

			case Rock:
				return TypeEffectiveness.NotEffective;

			case Steel:
				return TypeEffectiveness.NotEffective;

			default:
				return TypeEffectiveness.Normal;
		}
	}

	private static TypeEffectiveness psychicType(Type targetType)
	{
		switch(targetType)
		{
			case Fighting:
				return TypeEffectiveness.SuperEffective;

			case Poison:
				return TypeEffectiveness.SuperEffective;

			case Psychic:
				return TypeEffectiveness.NotEffective;

			case Dark:
				return TypeEffectiveness.NoEffect;

			case Steel:
				return TypeEffectiveness.NotEffective;

			default:
				return TypeEffectiveness.Normal;
		}
	}

	private static TypeEffectiveness bugType(Type targetType)
	{
		switch(targetType)
		{
			case Fire:
				return TypeEffectiveness.NotEffective;

			case Grass:
				return TypeEffectiveness.SuperEffective;

			case Fighting:
				return TypeEffectiveness.NotEffective;

			case Poison:
				return TypeEffectiveness.NotEffective;

			case Flying:
				return TypeEffectiveness.NotEffective;

			case Psychic:
				return TypeEffectiveness.SuperEffective;

			case Ghost:
				return TypeEffectiveness.NotEffective;

			case Dark:
				return TypeEffectiveness.SuperEffective;

			case Steel:
				return TypeEffectiveness.NotEffective;

			case Fairy:
				return TypeEffectiveness.NotEffective;

			default:
				return TypeEffectiveness.Normal;
		}
	}

	private static TypeEffectiveness rockType(Type targetType)
	{
		switch(targetType)
		{
			case Fire:
				return TypeEffectiveness.SuperEffective;

			case Ice:
				return TypeEffectiveness.SuperEffective;

			case Fighting:
				return TypeEffectiveness.NotEffective;

			case Ground:
				return TypeEffectiveness.NotEffective;

			case Flying:
				return TypeEffectiveness.SuperEffective;

			case Bug:
				return TypeEffectiveness.SuperEffective;

			case Steel:
				return TypeEffectiveness.NotEffective;

			default:
				return TypeEffectiveness.Normal;
		}
	}

	private static TypeEffectiveness ghostType(Type targetType)
	{
		switch(targetType)
		{
			case Normal:
				return TypeEffectiveness.NoEffect;

			case Psychic:
				return TypeEffectiveness.SuperEffective;

			case Ghost:
				return TypeEffectiveness.SuperEffective;

			case Dark:
				return TypeEffectiveness.NotEffective;

			default:
				return TypeEffectiveness.Normal;
		}
	}

	private static TypeEffectiveness dragonType(Type targetType)
	{
		switch(targetType)
		{
			case Dragon:
				return TypeEffectiveness.SuperEffective;

			case Steel:
				return TypeEffectiveness.NotEffective;

			case Fairy:
				return TypeEffectiveness.NoEffect;

			default:
				return TypeEffectiveness.Normal;
		}
	}

	private static TypeEffectiveness darkType(Type targetType)
	{
		switch(targetType)
		{
			case Fighting:
				return TypeEffectiveness.NotEffective;

			case Psychic:
				return TypeEffectiveness.SuperEffective;

			case Ghost:
				return TypeEffectiveness.SuperEffective;

			case Dark:
				return TypeEffectiveness.NotEffective;

			case Fairy:
				return TypeEffectiveness.NotEffective;

			default:
				return TypeEffectiveness.Normal;
		}
	}

	private static TypeEffectiveness steelType(Type targetType)
	{
		switch(targetType)
		{
			case Fire:
				return TypeEffectiveness.NotEffective;

			case Water:
				return TypeEffectiveness.NotEffective;

			case Electric:
				return TypeEffectiveness.NotEffective;

			case Ice:
				return TypeEffectiveness.SuperEffective;

			case Rock:
				return TypeEffectiveness.SuperEffective;

			case Steel:
				return TypeEffectiveness.NotEffective;

			case Fairy:
				return TypeEffectiveness.SuperEffective;

			default:
				return TypeEffectiveness.Normal;
		}
	}

	private static TypeEffectiveness fairyType(Type targetType)
	{
		switch(targetType)
		{
			case Fire:
				return TypeEffectiveness.NotEffective;

			case Fighting:
				return TypeEffectiveness.SuperEffective;

			case Poison:
				return TypeEffectiveness.NotEffective;

			case Dragon:
				return TypeEffectiveness.SuperEffective;

			case Dark:
				return TypeEffectiveness.SuperEffective;

			case Steel:
				return TypeEffectiveness.NotEffective;

			default:
				return TypeEffectiveness.Normal;
		}
	}

}
