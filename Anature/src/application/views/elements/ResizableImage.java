package application.views.elements;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;

public class ResizableImage extends Region
{
	private final ImageView mImage;

	public ResizableImage(Image image)
	{
		if(image == null)
			throw new IllegalArgumentException("Null Image");

		mImage = new ImageView(image);
		mImage.setPreserveRatio(false);
		getChildren().add(mImage);
	}

	public final Image getImage()
	{
		return mImage.getImage();
	}

	public final void setImage(Image image)
	{
		if(image == null)
			throw new IllegalArgumentException("Null Image");

		mImage.setImage(image);
	}

	@Override
	protected void layoutChildren()
	{
		Insets insets = getInsets();
		double width = getWidth() - insets.getLeft() - insets.getRight();
		double height = getHeight() - insets.getTop() - insets.getBottom();

		mImage.setFitHeight(height);
		mImage.setFitWidth(width);
	}

	public void setOnAction(EventHandler<MouseEvent> event)
	{
		mImage.setOnMouseClicked(event);
	}
}
