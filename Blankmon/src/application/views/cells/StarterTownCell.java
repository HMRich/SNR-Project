package application.views.cells;

import java.util.ArrayList;

import application.LoggerStartUp;
import application.Startup;
import application.enums.SceneType;
import application.views.ImageLayer;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class StarterTownCell extends AbstractCell
{
	private ImageView mTrainer;
	private Rectangle mTrainerCollisionBox;
	private boolean mBattleTrainer;
	
	public StarterTownCell(LoggerStartUp logger)
	{
		super(logger, 427, 468);
	}

	@Override
	protected void addToBackground()
	{
		mTrainer = new ImageView(new Image(getClass().getResource("/resources/images/trainers/kelly/down_stand.png").toExternalForm(), 100.0, 100.0, true, false));
		mTrainer.setFitHeight(29 * mZoom.get());
		mTrainer.setFitWidth(24 * mZoom.get());
		mTrainer.setX(427);
		mTrainer.setY(1310);
		
		mTrainerCollisionBox = new Rectangle();
		mTrainerCollisionBox.widthProperty().bind(mTrainer.fitWidthProperty().multiply(0.75));
		mTrainerCollisionBox.heightProperty().bind(mTrainer.fitHeightProperty().multiply(0.3));
		mTrainerCollisionBox.xProperty().bind(mTrainer.xProperty().add(mTrainer.getFitWidth() * 0.1));
		mTrainerCollisionBox.yProperty().bind(mTrainer.yProperty().add(mTrainer.getFitHeight() * 0.7));
		mTrainerCollisionBox.setFill(Color.BLUE);
		mTrainerCollisionBox.visibleProperty().bind(mShowCollision);
		
		mBattleTrainer = false;
		
		mBackground.getChildren().addAll(mTrainer, mTrainerCollisionBox);
	}

	@Override
	protected void timerHook()
	{
		if(mPlayerCollisionBox.getBoundsInParent().intersects(mTrainer.getBoundsInParent()))
			mBattleTrainer = true;
		
		else
			mBattleTrainer = false;

		int trainerIndex = mBackground.getChildren().indexOf(mTrainer);
		int playerIndex = mBackground.getChildren().indexOf(mPlayer);
		
		if(mPlayerCollisionBox.getY() > mTrainerCollisionBox.getY() && playerIndex < trainerIndex)
		{
			mBackground.getChildren().remove(mPlayer);
			mBackground.getChildren().add(trainerIndex + 1, mPlayer);
		}
		
		else if(mPlayerCollisionBox.getY() <= mTrainerCollisionBox.getY() && playerIndex > trainerIndex)
		{
			mBackground.getChildren().remove(mPlayer);
			mBackground.getChildren().add(trainerIndex, mPlayer);
		}
	}

	@Override
	protected ImageLayer createBackground()
	{
		Image bg = new Image(getClass().getResource("/resources/images/overworld/starter_town_background.png").toExternalForm(), 10000.0, 10000.0, true, false);
		return new ImageLayer(mWidth, mHeight, mZoom, bg);
	}

	@Override
	protected ImageLayer createForeground()
	{
		Image bg = new Image(getClass().getResource("/resources/images/overworld/starter_town_foreground.png").toExternalForm(), 10000.0, 10000.0, true, false);
		return new ImageLayer(mWidth, mHeight, mZoom, bg);
	}
	
	@Override
	protected void createCollisons()
	{
		addCollisionRectangle(277, 55, 1151, 50, mUpCollisions);
		addCollisionRectangle(1430, 76, 15, 1539, mRightCollisions);
		addCollisionRectangle(108, 1591, 1539, 15, mDownCollisions);
		addCollisionRectangle(90, 680, 15, 1000, mLeftCollisions);
		addCollisionRectangle(90, 660, 200, 15, mUpCollisions);
		addCollisionRectangle(277, 37, 15, 620, mLeftCollisions);
		
		addCollisionHouse(446, 510);
		addCollisionHouse(1077, 794);
		addCollisionHouse(236, 1180);
		
		mBackground.getChildren().addAll(mUpCollisions);
		mBackground.getChildren().addAll(mDownCollisions);
		mBackground.getChildren().addAll(mRightCollisions);
		mBackground.getChildren().addAll(mLeftCollisions);
	}
	
	private void addCollisionRectangle(double x, double y, double width, double height, ArrayList<Rectangle> toAddTo)
	{
		Rectangle collision = new Rectangle(x, y, width, height);
		collision.visibleProperty().bind(mShowCollision);
		toAddTo.add(collision);
	}
	
	private void addCollisionHouse(double xUpperLeft, double yUpperLeft)
	{
		Rectangle houseSouth = new Rectangle(xUpperLeft + 10, yUpperLeft + 110, 230, 20);
		houseSouth.visibleProperty().bind(mShowCollision);
		houseSouth.setFill(Color.RED);
		mUpCollisions.add(houseSouth);
		
		Rectangle houseWest = new Rectangle(xUpperLeft, yUpperLeft, 17, 127);
		houseWest.visibleProperty().bind(mShowCollision);
		mRightCollisions.add(houseWest);
		
		Rectangle houseEast = new Rectangle(xUpperLeft + 233, yUpperLeft, 17, 127);
		houseEast.visibleProperty().bind(mShowCollision);
		mLeftCollisions.add(houseEast);
		
		Rectangle houseNorth = new Rectangle(xUpperLeft + 10, yUpperLeft, 230, 20);
		houseNorth.visibleProperty().bind(mShowCollision);
		houseNorth.setFill(Color.RED);
		mDownCollisions.add(houseNorth);
	}
	
	@Override
	protected void keyPressHook(KeyEvent event)
	{
		if(event.getCode() == KeyCode.E && mBattleTrainer)
		{
			Startup.changeScene(SceneType.Battle);

			mPlayer.setX(485);
			mPlayer.setY(599);
			
			mRight = false;
			mLeft = false;
			mDown = false;
			mUp = false;
		}
	}

	@Override
	protected boolean xCollisionCheck()
	{
		Bounds playerBounds = mPlayerCollisionBox.getBoundsInLocal();

		for(Rectangle west : mLeftCollisions)
		{
			if(playerBounds.intersects(west.getBoundsInParent()))
			{
				return false;
			}
		}

		for(Rectangle east : mRightCollisions)
		{
			if(playerBounds.intersects(east.getBoundsInParent()))
			{
				return false;
			}
		}
		
		return true;
	}

	@Override
	protected boolean YCollisionCheck()
	{
		Bounds playerBounds = mPlayerCollisionBox.getBoundsInLocal();
		
		for(Rectangle north : mUpCollisions)
		{
			if(playerBounds.intersects(north.getBoundsInParent()))
			{
				return false;
			}
		}

		for(Rectangle south : mDownCollisions)
		{
			if(playerBounds.intersects(south.getBoundsInParent()))
			{
				return false;
			}
		}
		
		return true;
	}

	@Override
	protected boolean otherCollisionCheck()
	{
		Bounds playerBounds = mPlayerCollisionBox.getBoundsInLocal();
		
		if(playerBounds.intersects(mTrainerCollisionBox.getBoundsInParent()))
		{
			return false;
		}
		
		return true;
	}
}
