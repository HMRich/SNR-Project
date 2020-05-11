package application.models;

import application.enums.TrainerIds;
import application.interfaces.ITrainer;
import application.trainers.TrainerBuilder;

public class PathOneModel
{
	private ITrainer mKelly;

	public PathOneModel()
	{
		mKelly = TrainerBuilder.createTrainer(TrainerIds.Kelly, 1, 13, 17);
	}

	public ITrainer getKelly()
	{
		return mKelly;
	}
}
