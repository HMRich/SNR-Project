package application.controllers.overworld_cells;

import application.LoggerStartUp;
import application.views.overworld_cells.PathOneCell;
import javafx.scene.input.KeyEvent;

public class PathOneController extends AbstractController
{
	public PathOneController(LoggerStartUp logger, PathOneCell view)
	{
		super(logger, view);
		mPlayer.setX(17);
		mPlayer.setY(305);
	}

	@Override
	protected void timerHook()
	{
		
	}

	@Override
	protected void keyPressHook(KeyEvent event)
	{
		
	}
}
