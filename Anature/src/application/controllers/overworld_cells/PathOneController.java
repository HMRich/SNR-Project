package application.controllers.overworld_cells;

import application.LoggerStartUp;
import application.enums.Direction;
import application.enums.WarpPoints;
import application.views.overworld_cells.PathOneCell;
import javafx.scene.input.KeyEvent;

public class PathOneController extends AbstractController
{
	private PathOneCell mView;
	
	public PathOneController(LoggerStartUp logger, PathOneCell view)
	{
		super(logger, view);
		mView = view;
	}

	@Override
	protected void timerHook(double elapsedSeconds)
	{
		int trainerIndex = mView.mKelly.getIndex(mView.getBackground());
		int playerIndex = mPlayer.getIndex(mView.getBackground());

		if(mPlayer.getBoxY() > mView.mKelly.getCollisionY() && playerIndex < trainerIndex)
		{
			mPlayer.removeFromContainer(mView.getBackground());
			mPlayer.addToContainer(mView.getBackground(), trainerIndex + 1);
		}

		else if(mPlayer.getBoxY() <= mView.mKelly.getCollisionY() && playerIndex > trainerIndex)
		{
			mPlayer.removeFromContainer(mView.getBackground());
			mPlayer.addToContainer(mView.getBackground(), trainerIndex);
		}
		
		mView.mKelly.update(mPlayer, mSpeed, elapsedSeconds);
	}

	@Override
	protected void keyPressHook(KeyEvent event)
	{
		
	}

	@Override
	public void movePlayer(WarpPoints warpPoint)
	{
		if(warpPoint == null)
			return;
		
		switch(warpPoint)
		{
			case Path_1_Starter_Town_Exit:
				mPlayer.setX(45);
				mPlayer.setY(292);
				mView.setPlayerFacing(Direction.Right);
				break;

			default:
				break;
		}
	}
}
