package application.models;

import application.enums.TrainerIds;
import application.interfaces.ITrainer;
import application.trainers.Trainer;

public class PathOneModel
{
	private ITrainer mKelly;

	public PathOneModel()
	{
		mKelly = Trainer.createTrainer(TrainerIds.Kelly, 1, 13, 17);
	}

	public ITrainer getKelly()
	{
		return mKelly;
	}
}
