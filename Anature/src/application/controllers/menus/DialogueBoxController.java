package application.controllers.menus;

import application.animations.BlinkingAnimation;
import application.views.elements.ImageLayer;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class DialogueBoxController
{
	@FXML private Pane mPane;
	@FXML private ImageView mBgImg, mClickIndicatorImg;
	@FXML private TextArea mTxtArea;
	
	public void initialize()
	{
		mBgImg.fitWidthProperty().bind(mPane.prefWidthProperty());
		mBgImg.fitHeightProperty().bind(mPane.prefHeightProperty());
		
		mClickIndicatorImg.layoutXProperty().bind(mPane.prefWidthProperty().divide(1.09));
		mClickIndicatorImg.layoutYProperty().bind(mPane.prefHeightProperty().divide(1.426));
		mClickIndicatorImg.fitWidthProperty().bind(mPane.prefWidthProperty().divide(34.77));
		mClickIndicatorImg.fitHeightProperty().bind(mPane.prefHeightProperty().divide(5.178));
		
		mTxtArea.layoutXProperty().bind(mPane.prefWidthProperty().divide(24.549));
		mTxtArea.layoutYProperty().bind(mPane.prefHeightProperty().divide(29.25));
		mTxtArea.prefWidthProperty().bind(mPane.prefWidthProperty().divide(1.1454));
		mTxtArea.prefHeightProperty().bind(mPane.prefHeightProperty().divide(1.07));
		
		BlinkingAnimation blinkAnimation = new BlinkingAnimation(mClickIndicatorImg, Duration.seconds(1.5));
		blinkAnimation.play();
	}
	
	public void updateBinds(Scene scene, ImageLayer bg, BooleanProperty visibilityProp, ObjectProperty<Font> fontProperty, StringProperty dialogueTxtProperty)
	{
		ChangeListener<Number> listener = new ChangeListener<Number>()
		{
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue)
			{
				if(scene.getWidth() > bg.getPrefWidth())
				{
					mPane.prefWidthProperty().bind(bg.prefWidthProperty().divide(1.022));
					mPane.layoutXProperty().bind(bg.layoutXProperty().add(bg.prefWidthProperty().divide(91.42)));
					mPane.prefHeightProperty().bind(scene.heightProperty().divide(5.5));
					mPane.layoutYProperty().bind(scene.heightProperty().divide(1.25));
				}
				
				else
				{
					mPane.prefWidthProperty().bind(scene.widthProperty().divide(1.022));
					mPane.layoutXProperty().bind(scene.widthProperty().divide(91.428));
					mPane.prefHeightProperty().bind(scene.heightProperty().divide(4.5569));
					mPane.layoutYProperty().bind(scene.heightProperty().divide(1.306));
				}
			}
		};
		
		scene.widthProperty().addListener(listener);
		scene.heightProperty().addListener(listener);
		bg.prefWidthProperty().addListener(listener);
		bg.prefHeightProperty().addListener(listener);
		
		mPane.visibleProperty().bind(visibilityProp);
		
		mTxtArea.fontProperty().bind(fontProperty);
		mTxtArea.textProperty().bind(dialogueTxtProperty);
	}
	
	public double getWidth()
	{
		return mPane.getPrefWidth();
	}
}
