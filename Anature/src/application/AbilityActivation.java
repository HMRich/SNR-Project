package application;

import java.util.ArrayList;

import application.abillities.AbilityResult;
import application.abillities.Determination;
import application.abillities.DrySkin;
import application.abillities.SleepDeprived;
import application.abillities.Spiky;
import application.abillities.ToughSkin;
import application.abillities.Tyrannize;
import application.enums.AbilityIds;
import application.enums.Type;
import application.moves.Move;

public class AbilityActivation
{
	public static AbilityResult useEntryAbility(AbilityIds abilityId, Anature userAnature, Anature targetAnature)
	{
		AbilityResult result = new AbilityResult(new ArrayList<String>(), false);

		switch(abilityId)
		{
			case Tyrannize: // Intimidate
				result = new AbilityResult(Tyrannize.activateAbility(targetAnature), true);
				break;

			default:
				break;
		}

		return result;
	}

	public static AbilityResult useAbilityCanAttack(AbilityIds abilityIds, Anature userAnature, Anature targetAnature, Move move)
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

	public static AbilityResult useAbilityAfterAttack(AbilityIds abilityId, Anature userAnature, Anature targetAnature, Move moveThatAttacked, int userOldHp)
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
				abilityTxt = ToughSkin.activateAbility(userAnature, moveThatAttacked, userOldHp);
				break;

			case Spiky: // Rough Skin
				abilityTxt = Spiky.activateAbility(userAnature, targetAnature, moveThatAttacked);
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
