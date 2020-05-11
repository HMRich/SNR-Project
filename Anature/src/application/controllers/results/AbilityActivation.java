package application.controllers.results;

import java.util.ArrayList;

import application.anatures.abillities.Determination;
import application.anatures.abillities.DrySkin;
import application.anatures.abillities.SleepDeprived;
import application.anatures.abillities.Spiky;
import application.anatures.abillities.ToughSkin;
import application.anatures.abillities.Tyrannize;
import application.enums.AbilityIds;
import application.enums.Type;
import application.interfaces.IAnature;
import application.interfaces.IMove;

public class AbilityActivation
{
	public static AbilityResult useEntryAbility(AbilityIds abilityId, IAnature userAnature, IAnature targetAnature)
	{
		AbilityResult result = new AbilityResult(new ArrayList<String>(), false);

		switch(abilityId)
		{
			case Tyrannize: // Intimidate
				result = new AbilityResult(Tyrannize.activateAbility(userAnature, targetAnature), true);
				break;

			default:
				break;
		}

		return result;
	}

	public static AbilityResult useAbilityCanAttack(AbilityIds abilityIds, IAnature userAnature, IAnature targetAnature, IMove move)
	{
		ArrayList<String> dialogue = new ArrayList<String>();
		AbilityResult result = new AbilityResult(dialogue, false);

		switch(abilityIds)
		{
			case Dry_Skin: // Dry Skin
				if(move.getType() == Type.Water)
				{
					dialogue.add(DrySkin.activateAbility(userAnature));
					result.setActivated(true);
				}
				break;

			default:
				break;
		}

		return result;
	}

	public static AbilityResult useAbilityAfterAttack(AbilityIds abilityId, IAnature userAnature, IAnature targetAnature, IMove moveThatAttacked, int userOldHp,
			boolean isUserAttacking, boolean attackMissed)
	{
		ArrayList<String> dialogue = new ArrayList<String>();
		AbilityResult result = new AbilityResult(dialogue, false);
		String abilityTxt = "";

		switch(abilityId)
		{
			case Determination: // Sturdy
				abilityTxt = Determination.activateAbility(userAnature, moveThatAttacked, userOldHp);
				break;

			case SleepDeprived: // Insomnia
				abilityTxt = SleepDeprived.activateAbility(userAnature);
				break;

			case ToughSkin: // Fluffy (but for all physical moves)
				abilityTxt = ToughSkin.activateAbility(userAnature, moveThatAttacked, userOldHp, attackMissed);
				break;

			case Spiky: // Rough Skin
				abilityTxt = Spiky.activateAbility(userAnature, targetAnature, moveThatAttacked, isUserAttacking, attackMissed);
				break;

			default:
				break;
		}

		if(!abilityTxt.equals(""))
		{
			dialogue.add(abilityTxt);
		}

		return result;
	}
}
