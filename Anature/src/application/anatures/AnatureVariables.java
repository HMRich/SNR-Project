package application.anatures;

import application.anatures.abillities.NullAbility;
import application.anatures.movesets.MoveSet;
import application.anatures.movesets.NullMoveSet;
import application.anatures.stats.NullStats;
import application.enums.Gender;
import application.enums.Species;
import application.enums.StatusEffects;
import application.enums.Type;
import application.interfaces.IAbility;
import application.interfaces.stats.IStats;

public abstract class AnatureVariables
{
	public String anatureName = "";
	public String anatureOwnerName = "";
	public boolean anatureIsShiny = false;
	public Species anatureSpecies = Species.NotSet;
	public Gender anatureGender = Gender.NotSet;
	public Type anaturePrimaryType = Type.NotSet;
	public Type anatureSecondaryType = Type.NotSet;
	public MoveSet anatureMoveSet = NullMoveSet.getNullMoveSet();
	public IAbility anatureAbility = NullAbility.getNullAbility();
	public StatusEffects anatureStatus = StatusEffects.None;
	public IStats anatureStats = NullStats.getNullStats();
	public int anatureIndexNumber = -1;
	public int anatureCatchRate = -1;

	public abstract void getVariables();
}
