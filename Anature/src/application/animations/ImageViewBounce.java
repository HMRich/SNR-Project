package application.animations;

import javafx.animation.Transition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class ImageViewBounce extends Transition
{
	private ImageView mImageView;
	double mStartY, mEndY;
	
	public ImageViewBounce(ImageView imageView, Duration bounceDuration, int bounceCount)
	{
		mImageView = imageView;
		setCycleDuration(bounceDuration);
		setAutoReverse(true);
		setCycleCount(bounceCount * 2);
		
		mStartY = 3.478; // X: 804, Y: 207
		mEndY = 3.78;
	}
	
	@Override
	protected void interpolate(double frac)
	{
		if(frac <= 0.5)
		{
			mImageView.layoutYProperty().bind(mImageView.getScene().heightProperty().divide((mEndY - mStartY) * (frac * 2) + mStartY));
		}
		
		else
		{
			mImageView.layoutYProperty().bind(mImageView.getScene().heightProperty().divide((mEndY - mStartY) * (frac * 2) + mEndY));
		}
	}
}
