package application.views.elements;

import java.util.ArrayList;

import application.controllers.LoggerController;
import application.enums.LoggingTypes;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class PlayerSprite
{
	private ImageView mImage;
	private Rectangle mBoxCollision, mTopCollision, mBotCollision, mRightCollision, mLeftCollision;
	private BooleanProperty mShowCollision;
	private ArrayList<Node> mContent;
	
	public PlayerSprite(Image sprite, double startingX, double startingY, DoubleProperty zoom, BooleanProperty showCollisionProp)
	{
		mShowCollision = showCollisionProp;
		
		mImage = new ImageView(sprite);
		mImage.fitWidthProperty().bind(zoom.multiply(24));
		mImage.fitHeightProperty().bind(zoom.multiply(29));
		mImage.setX(startingX);
		mImage.setY(startingY);

		mBoxCollision = new Rectangle();
		mBoxCollision.widthProperty().bind(mImage.fitWidthProperty().multiply(0.75));
		mBoxCollision.heightProperty().bind(mImage.fitHeightProperty().multiply(0.3));
		mBoxCollision.xProperty().bind(mImage.xProperty().add(mImage.getFitWidth() * 0.1));
		mBoxCollision.yProperty().bind(mImage.yProperty().add(mImage.getFitHeight() * 0.7));
		mBoxCollision.setFill(Color.BLUE);
		mBoxCollision.visibleProperty().bind(showCollisionProp);
		
		createCollisionBoxes();
		mContent = new ArrayList<Node>();
		mContent.add(mImage);
		mContent.add(mBoxCollision);
		mContent.add(mTopCollision);
		mContent.add(mBotCollision);
		mContent.add(mLeftCollision);
		mContent.add(mRightCollision);
	}
	
	private void createCollisionBoxes()
	{
		mTopCollision = new Rectangle();
		mTopCollision.xProperty().bind(mBoxCollision.xProperty());
		mTopCollision.yProperty().bind(mBoxCollision.yProperty());
		mTopCollision.widthProperty().bind(mBoxCollision.widthProperty().add(5));
		mTopCollision.setHeight(5);
		mTopCollision.visibleProperty().bind(mShowCollision);
		mTopCollision.setFill(Color.RED);

		mBotCollision = new Rectangle();
		mBotCollision.xProperty().bind(mBoxCollision.xProperty());
		mBotCollision.yProperty().bind(mBoxCollision.yProperty().add(mBoxCollision.heightProperty()));
		mBotCollision.widthProperty().bind(mBoxCollision.widthProperty().add(5));
		mBotCollision.setHeight(5);
		mBotCollision.visibleProperty().bind(mShowCollision);
		mBotCollision.setFill(Color.RED);

		mLeftCollision = new Rectangle();
		mLeftCollision.xProperty().bind(mBoxCollision.xProperty());
		mLeftCollision.yProperty().bind(mBoxCollision.yProperty());
		mLeftCollision.setWidth(5);
		mLeftCollision.heightProperty().bind(mBoxCollision.heightProperty().add(5));
		mLeftCollision.visibleProperty().bind(mShowCollision);

		mRightCollision = new Rectangle();
		mRightCollision.xProperty().bind(mBoxCollision.xProperty().add(mBoxCollision.widthProperty()));
		mRightCollision.yProperty().bind(mBoxCollision.yProperty());
		mRightCollision.setWidth(5);
		mRightCollision.heightProperty().bind(mBoxCollision.heightProperty().add(5));
		mRightCollision.visibleProperty().bind(mShowCollision);
	}
	
	public void addToContainer(Pane toAddTo)
	{
		toAddTo.getChildren().addAll(mContent);
	}
	
	public void addToContainer(Pane toAddTo, int index)
	{
		toAddTo.getChildren().addAll(index, mContent);
	}
	
	public void removeFromContainer(Pane toRemoveFrom)
	{
		toRemoveFrom.getChildren().removeAll(mContent);
	}
	
	public DoubleProperty xProp()
	{
		return mImage.xProperty();
	}
	
	public DoubleProperty yProp()
	{
		return mImage.yProperty();
	}
	
	public double getX()
	{
		return mImage.getX();
	}
	
	public void setX(double x)
	{
		mImage.setX(x);
	}
	
	public double getY()
	{
		return mImage.getY();
	}
	
	public void setY(double y)
	{
		mImage.setY(y);
	}
	
	public double getBoxY()
	{
		return mBoxCollision.getY();
	}
	
	public double getFitWidth()
	{
		return mImage.getFitWidth();
	}
	
	public double getFitHeight()
	{
		return mImage.getFitHeight();
	}
	
	public DoubleProperty xProperty()
	{
		return mImage.xProperty();
	}
	
	public DoubleProperty yProperty()
	{
		return mImage.yProperty();
	}
	
	public int getIndex(Pane toCheck)
	{
		return toCheck.getChildren().indexOf(mImage);
	}
	
	public Image getImage()
	{
		return mImage.getImage();
	}
	
	public void setImage(Image image)
	{
		if(image == null)
		{
			LoggerController.logEvent(LoggingTypes.Error, "Tried making Player Sprite null.");
			return;
		}
		
		mImage.setImage(image);
	}
	
	public Bounds getBoxBounds()
	{
		return mBoxCollision.getBoundsInLocal();
	}
	
	public Bounds getTopBounds()
	{
		return mTopCollision.getBoundsInLocal();
	}
	
	public Bounds getBotBounds()
	{
		return mBotCollision.getBoundsInLocal();
	}
	
	public Bounds getRightBounds()
	{
		return mRightCollision.getBoundsInLocal();
	}
	
	public Bounds getLeftBounds()
	{
		return mLeftCollision.getBoundsInLocal();
	}
}
