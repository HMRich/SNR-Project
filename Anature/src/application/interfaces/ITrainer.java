package application.interfaces;

import java.util.ArrayList;

import application.anatures.Anature;
import application.enums.TrainerIds;
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

	public AiChoiceObject<?> useTurn(Anature playerAnature);
}
