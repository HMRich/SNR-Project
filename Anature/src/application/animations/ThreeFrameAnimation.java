package application.animations;

import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class ThreeFrameAnimation extends Transition
{
	private ImageView mImageView;
	private Image mFrame1, mFrame2, mFrame3; 
	
	public ThreeFrameAnimation(ImageView imageView, Duration duration, Image frame1, Image frame2, Image frame3)
	{
		mImageView = imageView;
		setCycleDuration(duration);
		setCycleCount(Animation.INDEFINITE);
		
		mFrame1 = frame1;
		mFrame2 = frame2;
		mFrame3 = frame3;
	}
	
	@Override
	protected void interpolate(double frac) //0.0 to 1.0
	{
		
		//Set image based on the Frac
		mImageView.setImage(mFrame1);
		
		
	}
}
