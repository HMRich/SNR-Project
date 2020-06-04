package application.controllers.menus;

import java.util.ArrayList;

import application.anatures.NewAnatureBuilder;
import application.animations.BlinkingAnimation;
import application.animations.EvolutionAnimation;
import application.controllers.ClickQueue;
import application.enums.Species;
import application.interfaces.IAnature;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class EvolutionController
{
	@FXML private ImageView mBgImg;
	@FXML private ImageView mDialogueImg;
	@FXML private TextArea mDialogueTxt;
	@FXML private ImageView mAnatureImg;
	@FXML private ImageView mClickIndicator;

	private ClickQueue mClickQueue;
	private BooleanProperty mCanClick;
	private Runnable mToRunWhenDone;

	// Setup & Initialize Methods

	public void initialize()
	{
		mClickQueue = new ClickQueue();
		mCanClick = new SimpleBooleanProperty(true);
	}

	public void updateBinds(Scene scene)
	{
		mBgImg.fitWidthProperty()
				.bind(scene.widthProperty());
		mBgImg.fitHeightProperty()
				.bind(scene.heightProperty());

		mDialogueImg.fitWidthProperty()
				.bind(scene.widthProperty());
		mDialogueImg.fitHeightProperty()
				.bind(scene.heightProperty());

		mDialogueTxt.layoutXProperty()
				.bind(scene.widthProperty()
						.divide(calculateValue(1280, 87)));
		mDialogueTxt.layoutYProperty()
				.bind(scene.heightProperty()
						.divide(calculateValue(720, 554)));
		mDialogueTxt.prefWidthProperty()
				.bind(scene.widthProperty()
						.divide(calculateValue(1280, 1095)));
		mDialogueTxt.prefHeightProperty()
				.bind(scene.heightProperty()
						.divide(calculateValue(720, 141)));
		mDialogueTxt.fontProperty()
				.bind(getFontProperty(80, scene));

		mAnatureImg.layoutXProperty()
				.bind(scene.widthProperty()
						.divide(calculateValue(1280, 475)));
		mAnatureImg.layoutYProperty()
				.bind(scene.heightProperty()
						.divide(calculateValue(720, 122)));
		mAnatureImg.fitWidthProperty()
				.bind(scene.widthProperty()
						.divide(calculateValue(1280, 320)));
		mAnatureImg.fitHeightProperty()
				.bind(scene.heightProperty()
						.divide(calculateValue(720, 320)));

		mClickIndicator.layoutXProperty()
				.bind(scene.widthProperty()
						.divide(calculateValue(1280, 1146)));
		mClickIndicator.layoutYProperty()
				.bind(scene.heightProperty()
						.divide(calculateValue(720, 667)));
		mClickIndicator.fitWidthProperty()
				.bind(scene.widthProperty()
						.divide(calculateValue(1280, 36)));
		mClickIndicator.fitHeightProperty()
				.bind(scene.heightProperty()
						.divide(calculateValue(720, 28)));
		mClickIndicator.visibleProperty()
				.bind(mCanClick);

		setUpClickTracker(scene);
	}

	private void setUpClickTracker(Scene scene)
	{
		scene.setOnMouseClicked(event ->
		{
			if(mCanClick.get())
			{
				dequeueClickTracker(event);
			}
		});

		scene.setOnKeyReleased(keyEvent ->
		{
			switch(keyEvent.getCode())
			{
				case E:
					if(mCanClick.get())
					{
						dequeueClickTracker(keyEvent);
					}
					break;

				case B:
					stopEvolution();
					break;

				default:
					break;
			}
		});
	}

	private ObjectProperty<Font> getFontProperty(int toDivideBy, Scene scene)
	{
		Font font = Font.loadFont(getClass().getResourceAsStream("/resources/font/pixelFJ8pt1__.TTF"), toDivideBy);
		ObjectProperty<Font> fontProperty = new SimpleObjectProperty<Font>(font);

		scene.widthProperty()
				.addListener((observableValue, oldWidth, newWidth) -> fontProperty
						.set(Font.loadFont(getClass().getResourceAsStream("/resources/font/pixelFJ8pt1__.TTF"), getFontSize(scene) / toDivideBy)));

		scene.heightProperty()
				.addListener((observableValue, oldHeight, newHeight) -> fontProperty
						.set(Font.loadFont(getClass().getResourceAsStream("/resources/font/pixelFJ8pt1__.TTF"), getFontSize(scene) / toDivideBy)));

		return fontProperty;
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

	// Functionality Methods

	private void dequeueClickTracker(Event event)
	{
		if(event != null)
		{
			event.consume();
		}

		if(mClickQueue.upNextName() != null)
		{
			Runnable toRun = mClickQueue.dequeue();
			toRun.run();
		}
	}

	public void startEvolution(ArrayList<IAnature> party, IAnature toEvolve, Species evolveInto, Runnable toRunWhenDone)
	{
		BlinkingAnimation blinkingClickIndicator = new BlinkingAnimation(mClickIndicator, Duration.millis(500));
		blinkingClickIndicator.play();

		mCanClick.set(true);
		mToRunWhenDone = toRunWhenDone;
		mAnatureImg.setImage(toEvolve.getFrontSprite());
		mDialogueTxt.setText("What's this!");

		for(int i = 0; i < 5; i++)
		{
			mClickQueue.enqueue(() ->
			{
				mCanClick.set(false);
				mDialogueTxt.setText(toEvolve.getName() + " is evolving!");
				EvolutionAnimation animation = new EvolutionAnimation(mAnatureImg, Duration.seconds(2));
				animation.setOnFinished(value -> dequeueClickTracker(null));
				animation.play();
			}, "Play Evolution Blink: " + (i + 1));
		}

		mClickQueue.enqueue(() ->
		{
			String imgPath = "/resources/images/anatures/" + evolveInto.toString() + "_Front.png";
			mAnatureImg.setImage(new Image(getClass().getResource(imgPath)
					.toExternalForm(), 1000.0, 1000.0, true, false));

			mDialogueTxt.setText(toEvolve.getName() + " evolved into a " + evolveInto + "!");
			IAnature evolved = NewAnatureBuilder.createEvolvedAnature(toEvolve, evolveInto);
			int indexToReplace = party.indexOf(toEvolve);
			party.remove(indexToReplace);
			party.add(indexToReplace, evolved);
			mCanClick.set(true);
		}, "Evolve");

		mClickQueue.enqueue(mToRunWhenDone, "End Evolution");
	}

	private void stopEvolution()
	{
		if(mClickQueue.upNextName() != null)
		{
			mCanClick.set(true);
			mAnatureImg.setEffect(null);
			mClickQueue.clear();
			mDialogueTxt.setText("You stopped the evolution!");
			mClickQueue.enqueue(mToRunWhenDone, "End Evolution Scene");
		}
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
