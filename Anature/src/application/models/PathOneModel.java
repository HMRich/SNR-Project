package application.models;

import java.io.Serializable;

import application.enums.TrainerIds;
import application.interfaces.ITrainer;
import application.trainers.TrainerBuilder;

public class PathOneModel implements Serializable
{
	private static final long serialVersionUID = -3494030378374805579L;

	private ITrainer mKelly;

	public PathOneModel()
	{
		if(mKelly == null)
		{
			mKelly = TrainerBuilder.createTrainer(TrainerIds.Kelly, 2, 13, 17);
		}
	}

	public ITrainer getKelly()
	{
		return mKelly;
	}
}
