package application.views.elements;

import java.util.ArrayList;

import application.enums.Direction;
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
	private Image mUpSprite, mDownSprite, mLeftSprite, mRightSprite;
	private Rectangle mBoxCollision, mTopCollision, mBotCollision, mRightCollision, mLeftCollision;
	private BooleanProperty mShowCollision;
	private ArrayList<Node> mContent;
	
	public TrainerSprite(double x, double y, Image down, Image up, Image left, Image right, Direction playerFacing, DoubleProperty zoom, BooleanProperty showProperty)
	{
		mShowCollision = showProperty;
		
		Image toUse = null;
		
		mUpSprite = up;
		mDownSprite = down;
		mLeftSprite = left;
		mRightSprite = right;
		
		switch(playerFacing)
		{
			case Left:
				toUse = left;
				break;
				
			case Right:
				toUse = right;
				break;
				
			case Up:
				toUse = up;
				break;
				
			default:
				toUse = down;
				break;
		}
		
		mSprite = new ImageView(toUse);
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
	
	public String interact(PlayerSprite playerSprite, Direction playerFacing)
	{
		if(playerSprite.getTopBounds().intersects(mBotCollision.getBoundsInParent()) && playerFacing == Direction.Up)
		{
			mSprite.setImage(mDownSprite);
			
			return "";
		}
		
		else if(playerSprite.getBotBounds().intersects(mTopCollision.getBoundsInParent()) && playerFacing == Direction.Down)
		{
			mSprite.setImage(mUpSprite);
			
			return "";
		}
		
		else if(playerSprite.getRightBounds().intersects(mLeftCollision.getBoundsInParent()) && playerFacing == Direction.Right)
		{
			mSprite.setImage(mLeftSprite);
			
			return "";
		}
		
		else if(playerSprite.getLeftBounds().intersects(mRightCollision.getBoundsInParent()) && playerFacing == Direction.Left)
		{
			mSprite.setImage(mRightSprite);
			
			return "";
		}
		
		return null;
	}
}
