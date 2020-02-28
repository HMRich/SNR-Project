package application.views.overworld_cells;

import application.LoggerStartUp;
import application.Startup;
import application.enums.Direction;
import application.enums.SceneType;
import application.views.elements.ImageLayer;
import application.views.elements.TrainerSprite;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;

public class StarterTownCell extends AbstractCell
{
	private TrainerSprite mKelly;

	public StarterTownCell(LoggerStartUp logger)
	{
		super(logger, 427, 468);
	}

	@Override
	protected void addToBackground()
	{
		Image up = new Image(getClass().getResource("/resources/images/trainers/kelly/up_stand.png").toExternalForm(), 100.0, 100.0, true, false);
		Image down = new Image(getClass().getResource("/resources/images/trainers/kelly/down_stand.png").toExternalForm(), 100.0, 100.0, true, false);
		Image right = new Image(getClass().getResource("/resources/images/trainers/kelly/right_stand.png").toExternalForm(), 100.0, 100.0, true, false);
		Image left = new Image(getClass().getResource("/resources/images/trainers/kelly/left_stand.png").toExternalForm(), 100.0, 100.0, true, false);
		mKelly = new TrainerSprite(427, 1310, down, up, left, right, Direction.Down, mZoom, mShowCollision);

		mCollisions.add(mKelly.getCollisionBox());
		mKelly.addToContainer(mBackground);
	}

	@Override
	protected void timerHook()
	{
		int trainerIndex = mKelly.getIndex(mBackground);
		int playerIndex = mPlayer.getIndex(mBackground);

		if(mPlayer.getBoxY() > mKelly.getCollisionY() && playerIndex < trainerIndex)
		{
			mPlayer.removeFromContainer(mBackground);
			mPlayer.addToContainer(mBackground, trainerIndex + 1);
		}

		else if(mPlayer.getBoxY() <= mKelly.getCollisionY() && playerIndex > trainerIndex)
		{
			mPlayer.removeFromContainer(mBackground);
			mPlayer.addToContainer(mBackground, trainerIndex);
		}
	}

	@Override
	protected ImageLayer createBackground()
	{
		Image bg = new Image(getClass().getResource("/resources/images/overworld/starter_town_background.png").toExternalForm(), 1000.0, 1000.0, true, false);
		return new ImageLayer(mWidth, mHeight, mZoom, bg);
	}

	@Override
	protected ImageLayer createForeground()
	{
		Image bg = new Image(getClass().getResource("/resources/images/overworld/starter_town_foreground.png").toExternalForm(), 1000.0, 1000.0, true, false);
		return new ImageLayer(mWidth, mHeight, mZoom, bg);
	}

	@Override
	protected void createCollisons()
	{
		// Border
		addCollisionRectangle(277, 55, 1151, 50);
		addCollisionRectangle(1430, 76, 15, 1539);
		addCollisionRectangle(108, 1591, 1539, 15);
		addCollisionRectangle(90, 680, 15, 1000);
		addCollisionRectangle(90, 660, 200, 15);
		addCollisionRectangle(277, 37, 15, 620);

		// Houses
		addCollisionRectangle(446, 510, 250, 127);
		addCollisionRectangle(1077, 794, 250, 127);
		addCollisionRectangle(236, 1180, 250, 127);

		mBackground.getChildren().addAll(mCollisions);
	}

	@Override
	protected void keyPressHook(KeyEvent event)
	{
		if(event.getCode() == KeyCode.E)
		{
			if(mKelly.interact(mPlayer, getPlayerFacing()) != null)
			{
//				System.out.println("ACTIVATE BATTLE");
				mRight = false;
				mLeft = false;
				mDown = false;
				mUp = false;
				
				Startup.changeScene(SceneType.Battle);
			}
		}
	}

	@Override
	protected boolean xCollisionCheck()
	{
		Bounds left = mPlayer.getLeftBounds();
		Bounds right = mPlayer.getRightBounds();
		
		for(Rectangle toCheck : mCollisions)
		{
			boolean rightCheck = right.intersects(toCheck.getBoundsInParent());
			boolean leftCheck = left.intersects(toCheck.getBoundsInParent());
			
			if((rightCheck && ! leftCheck) || (leftCheck && !rightCheck))
			{
				return false;
			}
		}

		return true;
	}

	@Override
	protected boolean yCollisionCheck()
	{
		Bounds top = mPlayer.getTopBounds();
		Bounds bot = mPlayer.getBotBounds();
		
		for(Rectangle toCheck : mCollisions)
		{
			boolean topCheck = top.intersects(toCheck.getBoundsInParent());
			boolean botCheck = bot.intersects(toCheck.getBoundsInParent());
			
			if((topCheck && !botCheck) || (botCheck && !topCheck))
			{
				return false;
			}
		}

		return true;
	}

	private void addCollisionRectangle(double x, double y, double width, double height)
	{
		Rectangle collision = new Rectangle(x, y, width, height);
		collision.visibleProperty().bind(mShowCollision);
		mCollisions.add(collision);
	}
}
