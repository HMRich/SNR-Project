package application.controllers.overworld_cells;

import application.LoggerStartUp;
import application.enums.Direction;
import application.enums.WarpPoints;
import application.player.Player;
import application.views.overworld_cells.GrassTownCell;
import javafx.scene.input.KeyEvent;

public class GrassTownController extends AbstractController
{
	public GrassTownController(LoggerStartUp logger, GrassTownCell view, Player playerModel)
	{
		super(logger, view, playerModel);
	}

	@Override
	protected void timerHook(double elapsedSeconds)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void keyPressHook(KeyEvent event)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void movePlayer(WarpPoints warpPoint)
	{
		if(warpPoint == null)
			return;
		
		switch(warpPoint)
		{
			case Grass_Town_Path_1:
				mPlayerView.setX(1135);
				mPlayerView.setY(45);
				mView.setPlayerFacing(Direction.Down);
				break;

			default:
				break;
		}
	}
}
