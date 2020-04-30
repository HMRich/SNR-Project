package application.models;

import application.enums.TrainerIds;
import application.interfaces.ITrainer;
import application.trainers.Trainer;

public class StarterTownModel
{
	private ITrainer mKelly;

	public StarterTownModel()
	{
		mKelly = Trainer.createTrainer(TrainerIds.Kelly, 1, 13, 17);
	}

	public ITrainer getKelly()
	{
		return mKelly;
	}
}
