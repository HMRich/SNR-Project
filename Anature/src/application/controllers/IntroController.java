package application.controllers;

import application.Startup;
import application.animations.BlinkingAnimation;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class IntroController
{
	@FXML private Label mStartTxt, mPromptTxt;
	@FXML private ImageView mBgImg, mTxtLogoImg, mLogoImg;
	@FXML private Button mLoadBtn, mNewGameBtn;

	private BooleanProperty mShowIntroProperty;

	@FXML
	public void initialize()
	{
		mShowIntroProperty = new SimpleBooleanProperty(true);
	}

	public void updateBinds(Scene scene, EventHandler<KeyEvent> consoleEvent)
	{
		ObjectProperty<Font> fontProperty = createFontProperty(50);

		ChangeListener<Number> fontListener = (observableValue, oldWidth, newWidth) ->
		{
			fontProperty.set(Font.loadFont(getClass().getResourceAsStream("/resources/font/pixelFJ8pt1__.TTF"), getFontSize(scene) / 50));
		};

		EventHandler<KeyEvent> onKeyReleased = keyEvent ->
		{
			if(keyEvent.getText().compareTo("`") == 0)
			{
				consoleEvent.handle(keyEvent);
				return;
			}

			useGameSelectPositions(scene);
		};

		scene.widthProperty().addListener(fontListener);
		scene.heightProperty().addListener(fontListener);
		scene.setOnMouseClicked(mouseEvent -> useGameSelectPositions(scene));
		scene.setOnKeyReleased(onKeyReleased);

		mBgImg.fitWidthProperty().bind(scene.widthProperty());
		mBgImg.fitHeightProperty().bind(scene.heightProperty());

		updateIntroBinds(scene, onKeyReleased, fontProperty);
		updateGameSelectBinds(scene, fontProperty);

		BlinkingAnimation blinkAnimation = new BlinkingAnimation(mStartTxt, Duration.seconds(2));
		blinkAnimation.play();
	}

	private void updateIntroBinds(Scene scene, EventHandler<KeyEvent> onKeyReleased, ObservableValue<Font> fontProperty)
	{
		mTxtLogoImg.fitWidthProperty().bind(scene.widthProperty().divide(calculateValue(1280, 594)));
		mTxtLogoImg.fitHeightProperty().bind(scene.heightProperty().divide(calculateValue(720, 204)));

		mLogoImg.fitWidthProperty().bind(scene.widthProperty().divide(calculateValue(1280, 150)));
		mLogoImg.fitHeightProperty().bind(scene.heightProperty().divide(calculateValue(720, 150)));
		useIntroPositions(scene, onKeyReleased);

		mStartTxt.prefWidthProperty().bind(scene.widthProperty());
		mStartTxt.prefHeightProperty().bind(scene.heightProperty().divide(calculateValue(720, 85)));
		mStartTxt.layoutYProperty().bind(scene.heightProperty().divide(calculateValue(720, 558)));
		mStartTxt.fontProperty().bind(fontProperty);
		mStartTxt.setOnMouseClicked(mouseEvent -> useGameSelectPositions(scene));
		mStartTxt.setOnKeyPressed(onKeyReleased);
		mStartTxt.visibleProperty().bind(mShowIntroProperty);
	}

	private void updateGameSelectBinds(Scene scene, ObservableValue<Font> fontProperty)
	{
		mLoadBtn.prefWidthProperty().bind(scene.widthProperty().divide(calculateValue(1280, 270)));
		mLoadBtn.prefHeightProperty().bind(scene.heightProperty().divide(calculateValue(720, 178)));
		mLoadBtn.layoutXProperty().bind(scene.widthProperty().divide(calculateValue(1280, 346)));
		mLoadBtn.layoutYProperty().bind(scene.heightProperty().divide(calculateValue(720, 469)));
		mLoadBtn.visibleProperty().bind(mShowIntroProperty.not());
		mLoadBtn.fontProperty().bind(fontProperty);

		mNewGameBtn.prefWidthProperty().bind(scene.widthProperty().divide(calculateValue(1280, 270)));
		mNewGameBtn.prefHeightProperty().bind(scene.heightProperty().divide(calculateValue(720, 178)));
		mNewGameBtn.layoutXProperty().bind(scene.widthProperty().divide(calculateValue(1280, 673)));
		mNewGameBtn.layoutYProperty().bind(scene.heightProperty().divide(calculateValue(720, 469)));
		mNewGameBtn.visibleProperty().bind(mShowIntroProperty.not());
		mNewGameBtn.fontProperty().bind(fontProperty);

		mPromptTxt.prefWidthProperty().bind(scene.widthProperty());
		mPromptTxt.prefHeightProperty().bind(scene.heightProperty().divide(calculateValue(720, 85)));
		mPromptTxt.layoutYProperty().bind(scene.heightProperty().divide(calculateValue(720, 300)));
		mPromptTxt.fontProperty().bind(fontProperty);
		mPromptTxt.visibleProperty().bind(mShowIntroProperty.not());
	}

	private void useIntroPositions(Scene scene, EventHandler<KeyEvent> onKeyReleased)
	{
		mTxtLogoImg.layoutXProperty().bind(scene.widthProperty().divide(calculateValue(1280, 347)));
		mTxtLogoImg.layoutYProperty().bind(scene.heightProperty().divide(calculateValue(720, 110)));
		mTxtLogoImg.setOnMouseClicked(mouseEvent -> useGameSelectPositions(scene));
		mTxtLogoImg.setOnKeyReleased(onKeyReleased);

		mLogoImg.layoutXProperty().bind(scene.widthProperty().divide(calculateValue(1280, 565)));
		mLogoImg.layoutYProperty().bind(scene.heightProperty().divide(calculateValue(720, 360)));
		mLogoImg.setOnMouseClicked(mouseEvent -> useGameSelectPositions(scene));
		mLogoImg.setOnKeyReleased(onKeyReleased);

		mShowIntroProperty.set(true);
	}

	private void useGameSelectPositions(Scene scene)
	{
		mTxtLogoImg.layoutXProperty().bind(scene.widthProperty().divide(calculateValue(1280, 257)));
		mTxtLogoImg.layoutYProperty().bind(scene.heightProperty().divide(calculateValue(720, 96)));
		mTxtLogoImg.setOnMouseClicked(null);
		mTxtLogoImg.setOnKeyPressed(null);

		mLogoImg.layoutXProperty().bind(scene.widthProperty().divide(calculateValue(1280, 881)));
		mLogoImg.layoutYProperty().bind(scene.heightProperty().divide(calculateValue(720, 137)));
		mLogoImg.setOnMouseClicked(null);
		mLogoImg.setOnKeyPressed(null);

		mShowIntroProperty.set(false);
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

	@FXML
	private void onLoad()
	{
		Startup.load(false);
	}

	@FXML
	private void onNewGame()
	{
		Startup.newGame();
	}
}
