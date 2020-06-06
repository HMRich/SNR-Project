package application.controllers;

import application.Startup;
import application.animations.BlinkingAnimation;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class IntroController
{
	@FXML private Label mStartTxt;
	@FXML private ImageView mBgImg, mTxtLogoImg, mLogoImg;
	
	public void updateBinds(Scene scene, EventHandler<KeyEvent> consoleEvent)
	{
		ObjectProperty<Font> fontProperty = createFontProperty(50);
		
		ChangeListener<Number> fontListener = (observableValue, oldWidth, newWidth) -> 
		{
			fontProperty.set(Font.loadFont(getClass().getResourceAsStream("/resources/font/pixelFJ8pt1__.TTF"), getFontSize(scene) / 50));
		};
		
		EventHandler<KeyEvent> onKeyPress = keyEvent ->
		{
			if(keyEvent.getText().compareTo("`") == 0)
			{
				consoleEvent.handle(keyEvent);
				return;
			}
			
			Startup.load(false);
		};
		
		scene.widthProperty().addListener(fontListener);
		scene.heightProperty().addListener(fontListener);
		scene.setOnMouseClicked(mouseEvent -> Startup.load(false));
		scene.setOnKeyPressed(onKeyPress);
		
		mBgImg.fitWidthProperty().bind(scene.widthProperty());
		mBgImg.fitHeightProperty().bind(scene.heightProperty());
		
		mTxtLogoImg.fitWidthProperty().bind(scene.widthProperty().divide(calculateValue(1280, 594)));
		mTxtLogoImg.fitHeightProperty().bind(scene.heightProperty().divide(calculateValue(720, 204)));
		mTxtLogoImg.layoutXProperty().bind(scene.widthProperty().divide(calculateValue(1280, 347)));
		mTxtLogoImg.layoutYProperty().bind(scene.heightProperty().divide(calculateValue(720, 110)));
		mTxtLogoImg.setOnMouseClicked(mouseEvent -> Startup.load(false));
		mTxtLogoImg.setOnKeyPressed(onKeyPress);
		
		mLogoImg.fitWidthProperty().bind(scene.widthProperty().divide(calculateValue(1280, 150)));
		mLogoImg.fitHeightProperty().bind(scene.heightProperty().divide(calculateValue(720, 150)));
		mLogoImg.layoutXProperty().bind(scene.widthProperty().divide(calculateValue(1280, 565)));
		mLogoImg.layoutYProperty().bind(scene.heightProperty().divide(calculateValue(720, 360)));
		mLogoImg.setOnMouseClicked(mouseEvent -> Startup.load(false));
		mLogoImg.setOnKeyPressed(onKeyPress);
		
		mStartTxt.prefWidthProperty().bind(scene.widthProperty());
		mStartTxt.prefHeightProperty().bind(scene.heightProperty().divide(calculateValue(720, 85)));
		mStartTxt.layoutYProperty().bind(scene.heightProperty().divide(calculateValue(720, 558)));
		mStartTxt.fontProperty().bind(fontProperty);
		mStartTxt.setOnMouseClicked(mouseEvent -> Startup.load(false));
		mStartTxt.setOnKeyPressed(onKeyPress);
		
		BlinkingAnimation blinkAnaimation = new BlinkingAnimation(mStartTxt, Duration.seconds(2));
		blinkAnaimation.play();
	}
	
	private ObjectProperty<Font> createFontProperty(int toDivideBy)
	{
		Font font = Font.loadFont(getClass().getResourceAsStream("/resources/font/pixelFJ8pt1__.TTF"), toDivideBy);
		return new SimpleObjectProperty<Font>(font);
	}

	private double getFontSize(Scene scene)
	{
		double value = scene.getWidth();

		if(scene.getHeight() < 464)
		{
			value = scene.getHeight() / 0.45;
		}

		if(scene.getWidth() >= 1940)
		{
			value = value - (scene.getWidth() - 1940);
		}

		return value;
	}
	
	private double calculateValue(double first, double second)
	{
		String value = (first / second) + "";

		if(value.length() > 4)
		{
			value = value.substring(0, 4);
		}

		return Double.parseDouble(value);
	}
}
