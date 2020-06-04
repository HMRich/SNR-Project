package application.interfaces;

import java.util.ArrayList;

import application.anatures.movesets.MoveSet;
import application.enums.BattleAnimationType;
import application.enums.Gender;
import application.enums.Species;
import application.enums.StatusEffects;
import application.enums.Type;
import application.interfaces.stats.IStats;
import javafx.scene.image.Image;

public interface IAnature
{
	/*
	 * PUBLIC SETS
	 */

	public IAnature setName(String name);

	public IAnature setOwnerName(String ownerName);

	public IAnature setIsShiny(boolean isShiny);

	public IAnature setSpecies(Species species);

	public IAnature setGender(Gender gender);

	public IAnature setPrimaryType(Type primaryType);

	public IAnature setSecondaryType(Type secondaryType);

	public IAnature setMoveSet(MoveSet moveSet);

	public IAnature setAbility(IAbility iAbility);

	public IAnature setStatus(StatusEffects statusEffect);

	public IAnature setStats(IStats stats);

	public IAnature setIndexNumber(int indexNumber);

	public IAnature setCatchRate(int catchRate);

	/*
	 * PUBLIC GETS
	 */

	public String getName();

	public String getOwner();

	public boolean isShiny();

	public Species getSpecies();

	public Gender getGender();

	public Type getPrimaryType();

	public Type getSecondaryType();

	public MoveSet getMoveSet();

	public IAbility getAbility();

	public StatusEffects getStatus();

	public IStats getStats();

	public int getIndexNumber();

	public int getCatchRate();

	/*
	 * PUBLIC METHODS
	 */

	public ArrayList<Type> getTypes();

	public void resetTempStats();

	public void applyDamage(int damage);

	public String applyHeal(int healAmount);

	public void restore();

	public double getHitPointsPercent();

	public IAnature getClone();

	public Image getFrontSprite();

	public Image getBackSprite();

	public BattleAnimationType getMoveAnimationType(int moveIndex);
}
