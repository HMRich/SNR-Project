package application.interfaces;

import java.util.ArrayList;

import application.anatures.AnatureBuilder;
import application.anatures.movesets.MoveSet;
import application.enums.BattleAnimationType;
import application.enums.Gender;
import application.enums.Natures;
import application.enums.Species;
import application.enums.StatusEffects;
import application.enums.Type;
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
	public Natures getNature();
	public MoveSet getMoveSet();
	public IAbility getAbility();
	public StatusEffects getStatus();
	public int getIndexNumber();
	public int getLevel();
	public int getCurrentExperiencePoints();
	public int getTotalHitPoints();
	public int getCurrentHitPoints();
	public int getAttack();
	public int getDefense();
	public int getSpecialAttack();
	public int getSpecialDefense();
	public int getSpeed();
	public int getAccuracy();
	
	/*
	 * PUBLIC METHODS
	 */
	
	public void adjustAttack(double attackAdjustment);
	public void adjustDefense(double defenseAdjustment);
	public void adjustSpecialAttack(double specialAttackAdjustment);
	public void adjustSpecialDefense(double specialDefenseAdjustment);
	public void adjustSpeed(double speedAdjustment);
	public void adjustAccuracy(double accuracyAdjustment);
	public void updateName(String name);
	public void updateStatus(StatusEffects status);
	public void updateCurrentHitPoints(int hitPoints);
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
