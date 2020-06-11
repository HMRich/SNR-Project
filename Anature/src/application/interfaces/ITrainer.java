package application.interfaces;

import java.util.ArrayList;

import application.anatures.Anature;
import application.enums.TrainerIds;
import application.trainers.ai.choice_objects.AiSwitchChoice;
import javafx.scene.image.Image;

public interface ITrainer
{
	/*
	 * PUBLIC GETS
	 */

	public TrainerIds getId();

	public String getName();

	public int getRewardForDefeat();

	public ArrayList<Anature> getAnatureParty();

	public ArrayList<IHealthPotion> getHealthPotions();

	public Anature getCurrentAnature();

	/*
	 * PUBLIC METHODS
	 */

	public Image getBattleSprite();

	public int getNextAnature(int index);

	public int getAnatureIndex(Anature anatureBase);

	public boolean canBattle();

	public AiChoiceObject<?> useTurn(IAnature playerAnature);

	public AiSwitchChoice chooseAnature(IAnature playerAnature);

	public void setCurrentAnature(IAnature currentAnature);
}
