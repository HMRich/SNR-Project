package application.animations;

import javafx.animation.Transition;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class TrainerAnimation extends Transition
{
	private ImageView mImageView;
	private double mOpacity;
	public BooleanProperty isFinished;
	
	public TrainerAnimation(ImageView imageView)
	{
		mImageView = imageView;
		mOpacity = 1;
		setCycleDuration(Duration.millis(3000));
		isFinished = new SimpleBooleanProperty(false);
	}
	
	
	@Override
	protected void interpolate(double frac)
	{		
		if(frac <= 0.8)
			mImageView.layoutXProperty().bind(mImageView.getScene().widthProperty().divide(frac + 1));
		
		else if(frac > 0.9)
		{
			mImageView.setOpacity(mOpacity);
			mOpacity -= 0.2;
		}
		
		if(frac >= 0.85 && !isFinished.get())
			isFinished.set(true);
	}
}
