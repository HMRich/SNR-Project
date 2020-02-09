package application.animations;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.Transition;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class PlayerAnimation extends Transition
{
	private ImageView mImageView;
	private List<Image> mImages;
	private double mOpacity;
	public BooleanProperty isFinished;
	
	public PlayerAnimation(ImageView imageView)
	{
		mImageView = imageView;
		mImages = new ArrayList<Image>();
		mOpacity = 1;
		
		setCycleDuration(Duration.millis(4000));
		isFinished = new SimpleBooleanProperty(false);
		
		mImages.add(new Image(getClass().getResource("/resources/images/player/Player_Female_1.png").toExternalForm()));
		mImages.add(new Image(getClass().getResource("/resources/images/player/Player_Female_2.png").toExternalForm()));
		mImages.add(new Image(getClass().getResource("/resources/images/player/Player_Female_3.png").toExternalForm()));
		mImages.add(new Image(getClass().getResource("/resources/images/player/Player_Female_4.png").toExternalForm()));
		mImages.add(new Image(getClass().getResource("/resources/images/player/Player_Female_5.png").toExternalForm()));
	}
	
	
	@Override
	protected void interpolate(double frac)
	{
		if(frac >= 0.7)
			mImageView.setImage(mImages.get(4));
		
		else if(frac >= 0.6)
			mImageView.setImage(mImages.get(3));
		
		else if(frac >= 0.5)
			mImageView.setImage(mImages.get(2));
		
		else if(frac >= 0.4)
			mImageView.setImage(mImages.get(1));
		
		else
			mImageView.setImage(mImages.get(0));
		
//		int index = (int) (frac*(mImages.size()-1));
//		mImageView.setImage(mImages.get(index));
		
		if(frac <= 0.7)
			mImageView.layoutXProperty().bind(mImageView.getScene().widthProperty().divide((frac / 0.8) * 8));
		
		else if(frac > 0.8)
		{
			mImageView.setOpacity(mOpacity);
			mOpacity -= 0.2;
		}
		
		if(frac >= 0.85 && !isFinished.get())
			isFinished.set(true);
	}
}
