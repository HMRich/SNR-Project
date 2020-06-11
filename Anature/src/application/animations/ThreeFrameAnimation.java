package application.animations;

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
		setCycleCount(1);
		
		mFrame1 = frame1;
		mFrame2 = frame2;
		mFrame3 = frame3;
	}
	
	@Override
	protected void interpolate(double frac) //0.0 to 1.0
	{
		
		if(frac >= 0 && frac <= 0.166) 
		{
			mImageView.setOpacity(100);
			mImageView.setImage(mFrame1);
		} 
		else if(frac > 0.166 && frac <= 0.33)
		{
			mImageView.setOpacity(0);
		} 
		else if(frac > 0.33 && frac <= 0.50)
		{
			mImageView.setOpacity(100);
			mImageView.setImage(mFrame2);
		}
		else if(frac > 0.50 && frac <= 0.66) 
		{
			mImageView.setOpacity(0);
		}
		else if(frac > 0.66 && frac <= 0.833) 
		{
			mImageView.setOpacity(100);
			mImageView.setImage(mFrame3);
		}
		else if(frac > 0.833 && frac <= 1.00) 
		{
			mImageView.setOpacity(0);
		}
		
	}
}
