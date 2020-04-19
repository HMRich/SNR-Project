package application.models;

import application.enums.TrainerIds;
import application.trainers.Trainer;
import application.trainers.TrainerBuilder;

public class PathOneModel
{
private Trainer mKelly;
	
	public PathOneModel()
	{
		mKelly = TrainerBuilder.createTrainer(TrainerIds.Kelly, 1, 13, 17);
	}
	
	public Trainer getKelly()
	{
		return mKelly;
	}
}
