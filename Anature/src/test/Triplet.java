package test;

import application.Anature;
import application.enums.AttackEffectiveness;

class TypeEffectivenessTestCase
{
	Anature attacker;
	Anature defender;
	AttackEffectiveness expectedEffectiveness;

	TypeEffectivenessTestCase(Anature attacker, Anature defender, AttackEffectiveness expectedEffectiveness)
	{
		this.attacker = attacker;
		this.defender = defender;
		this.expectedEffectiveness = expectedEffectiveness;
	}

	String testCaseToString()
	{
		return "Effectiveness_" + testCaseTypesToString(attacker) + "To" + testCaseTypesToString(defender) + "_" +expectedEffectiveness.toString();
	}

	private String testCaseTypesToString(Anature anature)
	{
		String types = "";
		if(anature.getPrimaryType() != null)
		{
			types = types.concat(anature.getPrimaryType().toString());
		}
		if(anature.getSecondaryType() != null)
		{
			types = types.concat(anature.getSecondaryType().toString());
		}
		return types;
	}
}
