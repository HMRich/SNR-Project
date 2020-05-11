package application.interfaces;

import java.util.ArrayList;

import application.trainers.ai.choice_objects.AiMoveChoice;

public interface IAI
{
	/*
	 * PUBLIC METHODS
	 */

	public boolean willUseHealthPotion(ArrayList<IHealthPotion> healthPotionBases, IAnature currentAnature);

	public IHealthPotion healthPotionToUse(ArrayList<IHealthPotion> healthPotionBases, IAnature currentAnature);

	public boolean willSwitchAnature(ArrayList<IAnature> anatureBases, IAnature enemyAnature, IAnature currentAnature);

	public AiMoveChoice chooseMove(IAnature source, IAnature target);

	public IAnature chooseNewAnature(ArrayList<IAnature> anatureBases, IAnature currentAnature, IAnature enemyAnature);
}
