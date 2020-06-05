package application.interfaces;

import java.util.ArrayList;

import application.anatures.Anature;
import application.trainers.ai.choice_objects.AiMoveChoice;

public interface IAI
{
	/*
	 * PUBLIC METHODS
	 */

	public boolean willUseHealthPotion(ArrayList<IHealthPotion> healthPotionBases, Anature currentAnature);

	public IHealthPotion healthPotionToUse(ArrayList<IHealthPotion> healthPotionBases, Anature currentAnature);

	public boolean willSwitchAnature(ArrayList<Anature> anatureBases, Anature enemyAnature, Anature currentAnature);

	public AiMoveChoice chooseMove(Anature source, Anature target);

	public Anature chooseNewAnature(ArrayList<Anature> anatureBases, Anature currentAnature, Anature enemyAnature);
}
