package test;

import application.anatures.Anature;
import application.enums.TypeEffectiveness;

class TypeEffectivenessTestCase
{
	Anature attacker;
	Anature defender;
	TypeEffectiveness expectedEffectiveness;

	public TypeEffectivenessTestCase(Anature attacker, Anature defender, TypeEffectiveness expectedEffectiveness)
	{
		this.attacker = attacker;
		this.defender = defender;
		this.expectedEffectiveness = expectedEffectiveness;
	}

	String testCaseToString()
	{
		return "EffectivenessValue_" + testCaseTypesToString(attacker) + "To" + testCaseTypesToString(defender) + "_Returns" + expectedEffectiveness.toString();
	}

	private String testCaseTypesToString(Anature anatureBase)
	{
		String types = "";
		if(anatureBase.getPrimaryType() != null)
		{
			types = types.concat(anatureBase.getPrimaryType()
					.toString());
		}
		if(anatureBase.getSecondaryType() != null)
		{
			types = types.concat(anatureBase.getSecondaryType()
					.toString());
		}
		return types;
	}
}
