package application.views.elements;

import application.controllers.LoggerController;
import application.enums.LoggingTypes;
import javafx.beans.property.DoubleProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Region;

public class ZoomableImage extends Region
{
	private Canvas mCanvas;
	private Image mImage;
	private DoubleProperty mZoom;
	private double mWidth, mHeight;

	public ZoomableImage(Image image, DoubleProperty zoom, double width, double height)
	{
		mImage = image;
		mZoom = zoom;
		mWidth = width;
		mHeight = height;

		mCanvas = new Canvas();
		mCanvas.setOnMouseClicked(event ->
		{
			if(LoggerController.isMouseLocationEnabled())
			{
				LoggerController.logEvent(LoggingTypes.Mouse, "Mouse click at X: " + event.getX() + " Y: " + event.getY() + " on the image.");
			}
		});
		mCanvas.widthProperty().bind(mZoom.multiply(mWidth).multiply(1.4));
		mCanvas.heightProperty().bind(mZoom.multiply(mHeight).multiply(1.4));

		getChildren().add(mCanvas);
	}

	public void draw()
	{
		GraphicsContext gc = mCanvas.getGraphicsContext2D();
		gc.clearRect(0, 0, mCanvas.getWidth(), mCanvas.getHeight());

		gc.drawImage(mImage, 0, 0, mWidth * mZoom.get() * 1.4, mHeight * mZoom.get() * 1.4);
	}

	@Override
	public void layoutChildren()
	{
		draw();
	}
}
