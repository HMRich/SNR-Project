package application.views.elements;

import java.util.ArrayList;

import application.controllers.LoggerController;
import application.enums.Direction;
import application.enums.LoggingTypes;
import application.enums.TrainerIds;
import application.trainers.Trainer;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class TrainerSprite
{
	private ImageView mSprite;
	private TrainerIds mId;
	private Rectangle mBoxCollision, mTopCollision, mBotCollision, mRightCollision, mLeftCollision;
	private BooleanProperty mShowCollision;
	private ArrayList<Node> mContent;
	
	private boolean mUp, mDown, mLeft, mRight, mCanMove;
	private Direction mFacing;
	private Image mWalkUpImg, mWalkDownImg, mWalkRightImg, mWalkLeftImg, mStandUpImg, mStandDownImg, mStandRightImg, mStandLeftImg;
	
	private int mFrame;
	private double[][] mKeyFrames;
	
	private String[] mDialogue;
	private boolean mHasBattle;
	private Trainer mTrainer;
	private String mName;	
	
	public TrainerSprite(double x, double y, TrainerIds id, Direction facing, DoubleProperty zoom, BooleanProperty showProperty, 
			double[][] keyFrames, int startingFrame, String[] dialogue, boolean hasBattle, String name)
	{
		mId = id;
		mShowCollision = showProperty;
		mDialogue = dialogue;
		mHasBattle = hasBattle;
		mFacing = facing;
		createSprites();
		
		if(name == null)
		{
			mName = mId.toString();
		}
		
		else
		{
			mName = name;
		}
		
		updateSprite();
		mSprite.setFitHeight(29 * zoom.get());
		mSprite.setFitWidth(24 * zoom.get());
		mSprite.setX(x);
		mSprite.setY(y);

		mBoxCollision = new Rectangle();
		mBoxCollision.widthProperty().bind(mSprite.fitWidthProperty().multiply(0.75));
		mBoxCollision.heightProperty().bind(mSprite.fitHeightProperty().multiply(0.3));
		mBoxCollision.xProperty().bind(mSprite.xProperty().add(mSprite.getFitWidth() * 0.1));
		mBoxCollision.yProperty().bind(mSprite.yProperty().add(mSprite.getFitHeight() * 0.7));
		mBoxCollision.setFill(Color.BLUE);
		mBoxCollision.visibleProperty().bind(showProperty);
		
		createCollisionBoxes();
		mContent = new ArrayList<Node>();
		mContent.add(mSprite);
		mContent.add(mTopCollision);
		mContent.add(mBotCollision);
		mContent.add(mLeftCollision);
		mContent.add(mRightCollision);
		
		mKeyFrames = keyFrames;
		mFrame = startingFrame;
		mCanMove = true;
	}
	
	private void createCollisionBoxes()
	{
		mTopCollision = new Rectangle();
		mTopCollision.xProperty().bind(mBoxCollision.xProperty().add(3));
		mTopCollision.yProperty().bind(mBoxCollision.yProperty().subtract(5));
		mTopCollision.widthProperty().bind(mBoxCollision.widthProperty().subtract(6));
		mTopCollision.setHeight(5);
		mTopCollision.visibleProperty().bind(mShowCollision);
		mTopCollision.setFill(Color.RED);

		mBotCollision = new Rectangle();
		mBotCollision.xProperty().bind(mBoxCollision.xProperty().add(3));
		mBotCollision.yProperty().bind(mBoxCollision.yProperty().add(mBoxCollision.heightProperty()));
		mBotCollision.widthProperty().bind(mBoxCollision.widthProperty().subtract(6));
		mBotCollision.setHeight(5);
		mBotCollision.visibleProperty().bind(mShowCollision);
		mBotCollision.setFill(Color.RED);

		mLeftCollision = new Rectangle();
		mLeftCollision.xProperty().bind(mBoxCollision.xProperty().subtract(5));
		mLeftCollision.yProperty().bind(mBoxCollision.yProperty());
		mLeftCollision.setWidth(5);
		mLeftCollision.heightProperty().bind(mBoxCollision.heightProperty());
		mLeftCollision.visibleProperty().bind(mShowCollision);

		mRightCollision = new Rectangle();
		mRightCollision.xProperty().bind(mBoxCollision.xProperty().add(mBoxCollision.widthProperty()));
		mRightCollision.yProperty().bind(mBoxCollision.yProperty());
		mRightCollision.setWidth(5);
		mRightCollision.heightProperty().bind(mBoxCollision.heightProperty());
		mRightCollision.visibleProperty().bind(mShowCollision);
	}
	
	private void createSprites()
	{
		mWalkUpImg = new Image(getClass().getResource("/resources/images/trainers/" + mId.toString().toLowerCase() + 
				"/up_walk.gif").toExternalForm(), 100.0, 100.0, true, false);
		mWalkDownImg = new Image(getClass().getResource("/resources/images/trainers/" + mId.toString().toLowerCase() + 
				"/down_walk.gif").toExternalForm(), 100.0, 100.0, true, false);
		mWalkRightImg = new Image(getClass().getResource("/resources/images/trainers/" + mId.toString().toLowerCase() + 
				"/right_walk.gif").toExternalForm(), 100.0, 100.0, true, false);
		mWalkLeftImg = new Image(getClass().getResource("/resources/images/trainers/" + mId.toString().toLowerCase() + 
				"/left_walk.gif").toExternalForm(), 100.0, 100.0, true, false);

		mStandUpImg = new Image(getClass().getResource("/resources/images/trainers/" + mId.toString().toLowerCase() + 
				"/up_stand.png").toExternalForm(), 100.0, 100.0, true, false);
		mStandDownImg = new Image(getClass().getResource("/resources/images/trainers/" + mId.toString().toLowerCase() + 
				"/down_stand.png").toExternalForm(), 100.0, 100.0, true, false);
		mStandRightImg = new Image(getClass().getResource("/resources/images/trainers/" + mId.toString().toLowerCase() + 
				"/right_stand.png").toExternalForm(), 100.0, 100.0, true, false);
		mStandLeftImg = new Image(getClass().getResource("/resources/images/trainers/" + mId.toString().toLowerCase() + 
				"/left_stand.png").toExternalForm(), 100.0, 100.0, true, false);
		
		mSprite = new ImageView(mStandDownImg);
	}
	
	public void addToContainer(Pane pane)
	{
		pane.getChildren().addAll(mContent);
	}
	
	public Rectangle getCollisionBox()
	{
		return mBoxCollision;
	}
	
	public Bounds getInteractionBoxBounds()
	{
		return mSprite.getBoundsInParent();
	}
	
	public int getIndex(Pane toCheck)
	{
		return toCheck.getChildren().indexOf(mSprite);
	}
	
	public double getCollisionX()
	{
		return mBoxCollision.getX();
	}
	
	public double getCollisionY()
	{
		return mBoxCollision.getY();
	}
	
	public String getName()
	{
		return mName;
	}
	
	public Trainer getTrainerModel()
	{
		return mTrainer;
	}
	
	public void setTrainerModel(Trainer trainer)
	{
		if(trainer == null)
		{
			LoggerController.logEvent(LoggingTypes.Error, "Tried giving trainer sprite a null model.");
			return;
		}
		
		mTrainer = trainer;
	}
	
	public String[] getDialogue()
	{
		if(mHasBattle && mTrainer.canBattle())
			return mDialogue;
		
		else if(mHasBattle && !mTrainer.canBattle())
			return new String[] {"Nice Battle!"};
		
		else
			return new String[] {"Nice to meet you!"};
	}
	
	public boolean interact(PlayerSprite playerSprite, Direction playerFacing)
	{
		mCanMove = false;
		mUp = false;
		mDown = false;
		mLeft = false;
		mRight = false;
		
		if(playerSprite.getTopBounds().intersects(mBotCollision.getBoundsInParent()) && playerFacing == Direction.Up)
		{
			mFacing = Direction.Down;
			return true;
		}
		
		else if(playerSprite.getBotBounds().intersects(mTopCollision.getBoundsInParent()) && playerFacing == Direction.Down)
		{
			mFacing = Direction.Up;
			return true;
		}
		
		else if(playerSprite.getRightBounds().intersects(mLeftCollision.getBoundsInParent()) && playerFacing == Direction.Right)
		{
			mFacing = Direction.Left;
			return true;
		}
		
		else if(playerSprite.getLeftBounds().intersects(mRightCollision.getBoundsInParent()) && playerFacing == Direction.Left)
		{
			mFacing = Direction.Right;
			return true;
		}

		mCanMove = true;
		return false;
	}
	
	public void update(PlayerSprite player, double speed, double elapsedSeconds)
	{
		if(mCanMove && mKeyFrames.length != 0)
		{
			updateFrame();

			double deltaX = 0, deltaY = 0;
			
			if(mRight)
			{
				deltaX += speed;
			}

			if(mLeft)
			{
				deltaX -= speed;
			}

			if(mDown)
			{
				deltaY += speed;
			}

			if(mUp)
			{
				deltaY -= speed;
			}

			updateSprite();

			double oldX = mSprite.getX();
			double oldY = mSprite.getY();

			mSprite.setX(mSprite.getX() + deltaX * elapsedSeconds);
			mSprite.setY(mSprite.getY() + deltaY * elapsedSeconds);
			
			if(player.getTopBounds().intersects(mBotCollision.getBoundsInParent()) && mFacing == Direction.Down ||
					player.getBotBounds().intersects(mTopCollision.getBoundsInParent()) && mFacing == Direction.Up)
			{
				mSprite.setY(oldY);
			}
			
			if(player.getRightBounds().intersects(mLeftCollision.getBoundsInParent()) && mFacing == Direction.Right ||
					player.getLeftBounds().intersects(mRightCollision.getBoundsInParent()) && mFacing == Direction.Left)
			{
				mSprite.setX(oldX);
			}
			
			if(player.getBoxBounds().intersects(mBoxCollision.getBoundsInParent()))
			{
				mSprite.setX(oldX);
				mSprite.setY(oldY);
			}
		}
		
		else
		{
			updateSprite();
		}
	}
	
	private void updateFrame()
	{
		int goalX = (int) mKeyFrames[mFrame][0];
		int goalY = (int) mKeyFrames[mFrame][1];
		
		mUp = false;
		mDown = false;
		mRight = false;
		mLeft = false;

		if((goalX - 10 < mSprite.getX() && mSprite.getX() < goalX + 10) && (goalY - 10 < mSprite.getY() && mSprite.getY() < goalY + 10))
		{
			mFrame++;
			
			if(mFrame >= mKeyFrames.length)
			{
				mFrame = 0;
			}
		}
		
		else
		{
			int lastFrame = mFrame - 1;
			
			if(lastFrame < 0)
			{
				lastFrame = mKeyFrames.length - 1;
			}
			
			double oldX = (int) mKeyFrames[lastFrame][0];
			double oldY = (int) mKeyFrames[lastFrame][1];
			
			if(oldX > goalX)
			{
				mLeft = true;
			}
			
			else if(oldX < goalX)
			{
				mRight = true;
			}
			
			else if(oldY > goalY)
			{
				mUp = true;
			}
			
			else if(oldY < goalY)
			{
				mDown = true;
			}
		}
	}

	private void updateSprite()
	{
		if(mUp && mDown && mLeft && mRight)
		{
			if(mSprite.getImage().equals(mStandDownImg))
				return;

			mSprite.setImage(mStandDownImg);
			mFacing = Direction.Down;
		}

		else if(mRight && mLeft && mUp)
		{
			if(mSprite.getImage().equals(mWalkUpImg))
				return;

			mSprite.setImage(mWalkUpImg);
			mFacing = Direction.Up;
		}

		else if(mRight && mLeft && mDown)
		{
			if(mSprite.getImage().equals(mWalkDownImg))
				return;

			mSprite.setImage(mWalkDownImg);
			mFacing = Direction.Down;
		}

		else if(mUp && mDown && mRight)
		{
			if(mSprite.getImage().equals(mWalkRightImg))
				return;

			mSprite.setImage(mWalkRightImg);
			mFacing = Direction.Right;
		}

		else if(mUp && mDown && mLeft)
		{
			if(mSprite.getImage().equals(mWalkLeftImg))
				return;

			mSprite.setImage(mWalkLeftImg);
			mFacing = Direction.Left;
		}

		else if((mUp && mDown) || (mRight && mLeft))
		{
			mSprite.setImage(mStandDownImg);
			mFacing = Direction.Down;
		}

		else if(mRight && !mSprite.getImage().equals(mWalkRightImg))
		{
			mSprite.setImage(mWalkRightImg);
			mFacing = Direction.Right;
		}

		else if(mLeft && !mSprite.getImage().equals(mWalkLeftImg))
		{
			mSprite.setImage(mWalkLeftImg);
			mFacing = Direction.Left;
		}

		else if(mDown && !mSprite.getImage().equals(mWalkDownImg) && !mLeft && !mRight)
		{
			mSprite.setImage(mWalkDownImg);
			mFacing = Direction.Down;
		}

		else if(mUp && !mSprite.getImage().equals(mWalkUpImg) && !mLeft && !mRight)
		{
			mSprite.setImage(mWalkUpImg);
			mFacing = Direction.Up;
		}

		else if(!mUp && !mDown && !mRight && !mLeft)
		{
			switch(mFacing)
			{
				case Up:
					if(!mSprite.getImage().equals(mStandUpImg))
						mSprite.setImage(mStandUpImg);
					break;

				case Right:
					if(!mSprite.getImage().equals(mStandRightImg))
						mSprite.setImage(mStandRightImg);
					break;

				case Left:
					if(!mSprite.getImage().equals(mStandLeftImg))
						mSprite.setImage(mStandLeftImg);
					break;

				default:
					if(!mSprite.getImage().equals(mStandDownImg))
						mSprite.setImage(mStandDownImg);
					break;
			}
		}
	}
}
