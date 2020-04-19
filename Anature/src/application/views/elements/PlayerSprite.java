package application.views.elements;

import java.util.ArrayList;

import application.controllers.LoggerController;
import application.enums.LoggingTypes;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class PlayerSprite
{
	private ImageView mImage, mEmote;
	private Rectangle mBoxCollision, mTopCollision, mBotCollision, mRightCollision, mLeftCollision;
	private Rectangle mULCornerCollision, mURCornerCollision, mLLCornerCollision, mLRCornerCollision, mTotalCollision;
	private BooleanProperty mShowCollision, mShowEmote;
	private ArrayList<Node> mContent;
	
	public PlayerSprite(Image sprite, double startingX, double startingY, DoubleProperty zoom, BooleanProperty showCollisionProp)
	{
		mShowCollision = showCollisionProp;
		mShowEmote = new SimpleBooleanProperty(false);
		
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
		
		mEmote = new ImageView(new Image(getClass().getResource("/resources/images/symbols/Exclamation.png").toExternalForm(), 100, 100, true, false));
		mEmote.fitWidthProperty().bind(mImage.fitWidthProperty().divide(2));
		mEmote.fitHeightProperty().bind(mEmote.fitWidthProperty());
		mEmote.xProperty().bind(mImage.xProperty().add(mImage.getFitWidth() / 4));
		mEmote.yProperty().bind(mImage.yProperty().subtract(30));
		mEmote.visibleProperty().bind(mShowEmote);
		
		createCollisionBoxes();
		mContent = new ArrayList<Node>();
		mContent.add(mEmote);
		mContent.add(mImage);

		mContent.add(mTotalCollision);
		mContent.add(mBoxCollision);
		mContent.add(mTopCollision);
		mContent.add(mBotCollision);
		mContent.add(mLeftCollision);
		mContent.add(mRightCollision);
		
		mContent.add(mULCornerCollision);
		mContent.add(mURCornerCollision);
		mContent.add(mLLCornerCollision);
		mContent.add(mLRCornerCollision);
	}
	
	private void createCollisionBoxes()
	{
		mTopCollision = new Rectangle();
		mTopCollision.xProperty().bind(mBoxCollision.xProperty());
		mTopCollision.yProperty().bind(mBoxCollision.yProperty().subtract(5));
		mTopCollision.widthProperty().bind(mBoxCollision.widthProperty());
		mTopCollision.setHeight(5);
		mTopCollision.visibleProperty().bind(mShowCollision);
		mTopCollision.setFill(Color.RED);

		mBotCollision = new Rectangle();
		mBotCollision.xProperty().bind(mBoxCollision.xProperty());
		mBotCollision.yProperty().bind(mBoxCollision.yProperty().add(mBoxCollision.heightProperty()));
		mBotCollision.widthProperty().bind(mBoxCollision.widthProperty());
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
		
		mULCornerCollision = new Rectangle();
		mULCornerCollision.xProperty().bind(mBoxCollision.xProperty().subtract(5));
		mULCornerCollision.yProperty().bind(mBoxCollision.yProperty().subtract(5));
		mULCornerCollision.setWidth(5);
		mULCornerCollision.setHeight(5);
		mULCornerCollision.visibleProperty().bind(mShowCollision);
		mULCornerCollision.setFill(Color.GREEN);
		
		mURCornerCollision = new Rectangle();
		mURCornerCollision.xProperty().bind(mBoxCollision.xProperty().add(mBoxCollision.widthProperty()));
		mURCornerCollision.yProperty().bind(mBoxCollision.yProperty().subtract(5));
		mURCornerCollision.setWidth(5);
		mURCornerCollision.setHeight(5);
		mURCornerCollision.visibleProperty().bind(mShowCollision);
		mURCornerCollision.setFill(Color.GREEN);
		
		mLLCornerCollision = new Rectangle();
		mLLCornerCollision.xProperty().bind(mBoxCollision.xProperty().subtract(5));
		mLLCornerCollision.yProperty().bind(mBoxCollision.yProperty().add(mBoxCollision.heightProperty()));
		mLLCornerCollision.setWidth(5);
		mLLCornerCollision.setHeight(5);
		mLLCornerCollision.visibleProperty().bind(mShowCollision);
		mLLCornerCollision.setFill(Color.GREEN);
		
		mLRCornerCollision = new Rectangle();
		mLRCornerCollision.xProperty().bind(mBoxCollision.xProperty().add(mBoxCollision.widthProperty()));
		mLRCornerCollision.yProperty().bind(mBoxCollision.yProperty().add(mBoxCollision.heightProperty()));
		mLRCornerCollision.setWidth(5);
		mLRCornerCollision.setHeight(5);
		mLRCornerCollision.visibleProperty().bind(mShowCollision);
		mLRCornerCollision.setFill(Color.GREEN);
		
		mTotalCollision = new Rectangle();
		mTotalCollision.widthProperty().bind(mBoxCollision.widthProperty().add(10));
		mTotalCollision.heightProperty().bind(mBoxCollision.heightProperty().add(10));
		mTotalCollision.xProperty().bind(mBoxCollision.xProperty().subtract(5));
		mTotalCollision.yProperty().bind(mBoxCollision.yProperty().subtract(5));
		mTotalCollision.setFill(Color.BLACK);
		mTotalCollision.visibleProperty().bind(mShowCollision);
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
	
	public Bounds getTotalBounds()
	{
		return mTotalCollision.getBoundsInLocal();
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
	
	public Bounds getUpperRightBounds()
	{
		return mURCornerCollision.getBoundsInLocal();
	}
	
	public Bounds getUpperLeftBounds()
	{
		return mULCornerCollision.getBoundsInLocal();
	}
	
	public Bounds getLowerRightBounds()
	{
		return mLRCornerCollision.getBoundsInLocal();
	}
	
	public Bounds getLowerLeftBounds()
	{
		return mLLCornerCollision.getBoundsInLocal();
	}
	
	public boolean isEmoteShown()
	{
		return mShowEmote.get();
	}
	
	public void showEmote()
	{
		mShowEmote.set(true);
	}
	
	public void hideEmote()
	{
		mShowEmote.set(false);
	}
}
