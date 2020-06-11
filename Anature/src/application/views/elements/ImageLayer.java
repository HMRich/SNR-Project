package application.views.elements;

import javafx.beans.property.DoubleProperty;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class ImageLayer extends Pane
{
	private double mWidth, mHeight;
	private DoubleProperty mZoom;

	public ImageLayer(double width, double height, DoubleProperty zoom, Image image)
	{
		mWidth = width;
		mHeight = height;
		mZoom = zoom;

		getChildren().add(new ZoomableImage(image, mZoom, mWidth, mHeight));

		minWidthProperty().bind(mZoom.multiply(mWidth).multiply(1.4));
		maxWidthProperty().bind(mZoom.multiply(mWidth).multiply(1.4));
		prefWidthProperty().bind(mZoom.multiply(mWidth).multiply(1.4));

		minHeightProperty().bind(mZoom.multiply(mHeight).multiply(1.4));
		maxHeightProperty().bind(mZoom.multiply(mHeight).multiply(1.4));
		prefHeightProperty().bind(mZoom.multiply(mHeight).multiply(1.4));
	}
}
