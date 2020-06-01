package application.interfaces;

import java.util.ArrayList;

import application.anatures.AnatureBuilder;
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

	/*
	 * PUBLIC METHODS
	 */

	public void updateName(String name);

	public void updateStatus(StatusEffects status);

	// public void updateCurrentHitPoints(int hitPoints);

	public ArrayList<Type> getTypes();

	public void resetTempStats();

	public void takeDamage(int damage);

	public String healAnature(int healAmount);

	public void restore();

	public double getHitPointsPercent();

	public AnatureBuilder getClone();

	public Image getFrontSprite();

	public Image getBackSprite();

	public BattleAnimationType getMoveAnimationType(int moveIndex);
}
