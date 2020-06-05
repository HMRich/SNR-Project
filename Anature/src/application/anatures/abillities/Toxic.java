package application.anatures.abillities;

import application.anatures.Anature;
import application.controllers.LoggerController;
import application.enums.AbilityIds;
import application.enums.LoggingTypes;
import application.enums.StatusEffects;
import application.enums.Type;
import application.interfaces.IAbility;

public class Toxic implements IAbility
{
	public static String activateAbility(Anature source, Anature target)
	{
		if(hasNull(source, target)) 
		{
			return "";
		}
		
		if(target.getStatus() == StatusEffects.None && (target.getPrimaryType() == Type.Poison || target.getSecondaryType() == Type.Poison))
		{
			return target.getName() + " was poisoned by " + source.getName() + "'s ability!";
		}
		
		return "";
	}
	
	private static boolean hasNull(Anature source, Anature target)
	{
		if(source == null)
		{
			LoggerController.logEvent(LoggingTypes.Error, "source parameter in Toxic was null.");
			return true;
		}

		else if(target == null)
		{
			LoggerController.logEvent(LoggingTypes.Error, "target parameter in Toxic was null.");
			return true;
		}

		return false;
	}

	@Override
	public AbilityIds getAbilityId()
	{
		return AbilityIds.Toxic;
	}

	@Override
	public String toString()
	{
		return "Toxic";
	}

	@Override
	public String getAbilityDescription()
	{
		return "Enemies become poisoned if they come into physical contact with the user.";
	}

	@Override
	public boolean happensEveryTurn()
	{
		return true;
	}
}
