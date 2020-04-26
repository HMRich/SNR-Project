package test;

import application.anatures.Anature;
import application.enums.AttackEffectiveness;

class TypeEffectivenessTestCase {
	Anature attacker;
	Anature defender;
	AttackEffectiveness expectedEffectiveness;

	public TypeEffectivenessTestCase(Anature attacker, Anature defender, AttackEffectiveness expectedEffectiveness) {
		this.attacker = attacker;
		this.defender = defender;
		this.expectedEffectiveness = expectedEffectiveness;
	}

	String testCaseToString() {
		return "EffectivenessValue_" + testCaseTypesToString(attacker) + "To" + testCaseTypesToString(defender) + "_Returns"
				+ expectedEffectiveness.toString();
	}

	private String testCaseTypesToString(Anature anature) {
		String types = "";
		if (anature.getPrimaryType() != null) {
			types = types.concat(anature.getPrimaryType().toString());
		}
		if (anature.getSecondaryType() != null) {
			types = types.concat(anature.getSecondaryType().toString());
		}
		return types;
	}
}
