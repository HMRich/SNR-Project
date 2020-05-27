package application.interfaces;

import java.util.ArrayList;

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

	public ArrayList<IAnature> getAnatureParty();

	public ArrayList<IHealthPotion> getHealthPotions();

	public IAnature getCurrentAnature();

	/*
	 * PUBLIC METHODS
	 */

	public Image getBattleSprite();

	public int getNextAnature(int index);

	public int getAnatureIndex(IAnature anatureBase);

	public boolean canBattle();

	public AiChoiceObject<?> useTurn(IAnature playerAnature);
}
