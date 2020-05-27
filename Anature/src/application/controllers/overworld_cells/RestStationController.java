package application.controllers.overworld_cells;

import application.LoggerStartUp;
import application.enums.Direction;
import application.enums.WarpPoints;
import application.interfaces.IAnature;
import application.models.KeysPressed;
import application.player.Player;
import application.views.overworld_cells.RestStationCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class RestStationController extends AbstractController
{
	public RestStationController(LoggerStartUp logger, RestStationCell view, Player playerModel)
	{
		super(logger, view, playerModel);
		mView.assignPlayerForShop(mPlayerModel, () -> mClickQueue.dequeue().run());
	}

	@Override
	protected void timerHook(double elapsedSeconds)
	{

	}

	@Override
	protected void keyPressHook(KeyEvent event)
	{
		KeysPressed keys = mView.getKeysPressed();
		event.consume();

		if(event.getCode() == KeyCode.E && mView.getPlayerFacing() == Direction.Up && keys.canMove())
		{
			if(((RestStationCell) mView).interactHealStation())
			{
				keys.setCanMove(false);

				mClickQueue.enqueue(() -> mView.showDialogue("Hi there, I'm the local nurse!"), "Nurse Intro");
				mClickQueue.enqueue(() ->
				{
					mView.showDialogue("I'll heal your Anatures!");

					for(IAnature anature : mPlayerModel.getAnatures())
					{
						anature.restore();
					}

				}, "Nurse Healing");

				mClickQueue.enqueue(() ->
				{
					mView.hideDialogue();
					keys.setCanMove(true);
				}, "End Nurse Dialogue");
			}

			else if(((RestStationCell) mView).interactClerkStation())
			{
				keys.setCanMove(false);

				mClickQueue.enqueue(() -> mView.showDialogue("Hi there, I'm the local store clerk!"), "Clerk Intro");
				mClickQueue.enqueue(() -> mView.showDialogue("Are you interested in some of my wares?"), "Clerk Buying Text");
				mClickQueue.enqueue(() ->
				{
					mView.showshopMenu();
					mView.hideDialogue();

				}, "Show Buying Menu");

				mClickQueue.enqueue(() ->
				{
					mView.hideShopMenu();
					keys.setCanMove(true);

				}, "End Clerk Dialogue");
			}
		}
	}

	@Override
	public void movePlayer(WarpPoints warpPoint)
	{
		if(warpPoint == null)
			return;

		switch(warpPoint)
		{
			case Rest_Station_Grass_Town:
				mPlayerView.setX(392);
				mPlayerView.setY(575);
				mView.setPlayerFacing(Direction.Up);
				break;

			default:
				break;
		}
	}
}
