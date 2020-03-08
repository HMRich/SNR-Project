package application.views.elements;

import application.enums.Gender;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class AnatureSlot extends Pane
{
	private ImageView mBg, mAnatureImg, mHpBg, mGender;
	private Text mNameTxt, mLvlTxt, mHpTxt;
	private BooleanProperty mIsShown, mIsTabVisible, mIsSelected, mIsCurrent;
	private StringProperty mNameProperty, mLvlProperty, mHpProperty;
	private Image mSelected, mDeselected, mDeselectedHover;
	private AnatureSlotHpBar mHpBar;

	public AnatureSlot(Scene scene, boolean isSelected, Image anatureImg, Gender gender, String nameTxt, String lvlTxt, String hpTxt, BooleanProperty isShown,
			BooleanProperty isTabVisible, double hpNum, boolean isCurrent)
	{
		nullChecks(scene, anatureImg, nameTxt, lvlTxt, hpTxt);
		mIsShown = isShown;
		mIsTabVisible = isTabVisible;
		mIsSelected = new SimpleBooleanProperty(isSelected);
		mIsCurrent = new SimpleBooleanProperty(isCurrent);

		mSelected = new Image(getClass().getResource("/resources/images/battle/switching/Switch_Anature_Slot_Selected.png").toExternalForm());
		mDeselected = new Image(getClass().getResource("/resources/images/battle/switching/Switch_Anature_Slot_Deselected.png").toExternalForm());
		mDeselectedHover = new Image(getClass().getResource("/resources/images/battle/switching/Switch_Anature_Slot_Deselected_Hover.png").toExternalForm());

		if(isSelected)
			mBg = new ImageView(mSelected);

		else
			mBg = new ImageView(mDeselected);

		switch(gender)
		{
			case Male:
				mGender = new ImageView(new Image(getClass().getResource("/resources/images/battle/Male_Symbol.png").toExternalForm()));
				break;

			case Female:
				mGender = new ImageView(new Image(getClass().getResource("/resources/images/battle/Female_Symbol.png").toExternalForm()));
				break;

			default:
				mGender = new ImageView();
		}

		mAnatureImg = new ImageView(anatureImg);
		mHpBg = new ImageView(new Image(getClass().getResource("/resources/images/battle/switching/Switch_Hp_Bar.png").toExternalForm()));

		mNameProperty = new SimpleStringProperty(nameTxt);
		mLvlProperty = new SimpleStringProperty(lvlTxt);
		mHpProperty = new SimpleStringProperty(hpTxt);

		mNameTxt = createText(mNameProperty, scene, 65);
		mLvlTxt = createText(mLvlProperty, scene, 65);
		mHpTxt = createText(mHpProperty, scene, 85);

		mHpBar = new AnatureSlotHpBar(hpNum, hpNum, this);
		mHpBar.bindX(3.9);
		mHpBar.bindY(1.55);
		mHpBar.visibleProperty().bind(isShown.and(isTabVisible));

		getChildren().addAll(mBg, mAnatureImg, mHpBg, mHpBar, mGender, mNameTxt, mLvlTxt, mHpTxt);
		createBindings(scene);

		setOnMouseEntered(new EventHandler<Event>()
		{
			@Override
			public void handle(Event event)
			{
				if(!mIsSelected.get() && !mIsCurrent.get())
					mBg.setImage(mDeselectedHover);
			}
		});

		setOnMouseExited(new EventHandler<Event>()
		{
			@Override
			public void handle(Event event)
			{
				if(!mIsSelected.get() && !mIsCurrent.get())
					mBg.setImage(mDeselected);
			}
		});

		mIsSelected.addListener(new ChangeListener<Boolean>()
		{
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue)
			{
				if(mIsSelected.get() && !mIsCurrent.get())
					mBg.setImage(mDeselectedHover);

				else if(!mIsCurrent.get())
					mBg.setImage(mDeselected);
			}
		});
	}

	public void updateSlot(boolean isCurrent, Image anatureImg, Gender gender, String nameTxt, String lvlTxt, String hpTxt, boolean isShown,
			boolean isTabVisible, double hp)
	{
		if(isCurrent)
			mBg.setImage(mSelected);

		else
			mBg.setImage(mDeselected);

		mAnatureImg.setImage(anatureImg);

		switch(gender)
		{
			case Male:
				mGender.setImage(new Image(getClass().getResource("/resources/images/battle/Male_Symbol.png").toExternalForm()));
				break;

			case Female:
				mGender.setImage(new Image(getClass().getResource("/resources/images/battle/Female_Symbol.png").toExternalForm()));
				break;

			default:
				mGender.setImage(null);
		}

		mNameProperty.set(nameTxt);
		mLvlProperty.set(lvlTxt);
		mHpProperty.set(hpTxt);

		mIsCurrent.set(isCurrent);
		mIsSelected.set(false);
		mIsShown.set(isShown);
		mIsTabVisible.set(isTabVisible);

		mHpBar.updateProgress(hp);
	}

	private Text createText(StringProperty txt, Scene scene, int fontSize)
	{
		Font nameFont = Font.loadFont(getClass().getResourceAsStream("/resources/font/pixelFJ8pt1__.TTF"), getFontSize(scene) / fontSize);
		ObjectProperty<Font> nameFontTracking = new SimpleObjectProperty<Font>(nameFont);

		scene.widthProperty().addListener((observableValue, oldWidth, newWidth) -> nameFontTracking
				.set(Font.loadFont(getClass().getResourceAsStream("/resources/font/pixelFJ8pt1__.TTF"), getFontSize(scene) / fontSize)));

		scene.heightProperty().addListener((observableValue, oldHeight, newHeight) -> nameFontTracking
				.set(Font.loadFont(getClass().getResourceAsStream("/resources/font/pixelFJ8pt1__.TTF"), getFontSize(scene) / fontSize)));

		Text text = new Text();
		text.textProperty().bind(txt);
		text.fontProperty().bind(nameFontTracking);
		text.setWrappingWidth(195.44921875);
		text.setFill(Color.WHITE);

		return text;
	}

	private double getFontSize(Scene scene)
	{
		double value = scene.getWidth();

		if(scene.getHeight() < 464)
			value = scene.getHeight() / 0.45;

		if(scene.getWidth() >= 1940)
			value = value - (scene.getWidth() - 1940);

		return value;
	}

	private void nullChecks(Scene scene, Image anatureImg, String nameTxt, String lvlTxt, String hpTxt)
	{
		if(scene == null)
			throw new IllegalArgumentException("Passed in scene is null.");

		else if(anatureImg == null)
			throw new IllegalArgumentException("Passed in anatureImg is null.");

		else if(nameTxt == null)
			throw new IllegalArgumentException("Passed in nameTxt is null.");

		else if(lvlTxt == null)
			throw new IllegalArgumentException("Passed in lvlTxt is null.");

		else if(hpTxt == null)
			throw new IllegalArgumentException("Passed in hpTxt is null.");
	}

	private void createBindings(Scene scene)
	{
		mBg.fitWidthProperty().bind(prefWidthProperty());
		mBg.fitHeightProperty().bind(prefHeightProperty());
		mBg.visibleProperty().bind(mIsShown.and(mIsTabVisible));

		mAnatureImg.layoutXProperty().bind(scene.widthProperty().divide(160));
		mAnatureImg.layoutYProperty().bind(scene.heightProperty().divide(180));
		mAnatureImg.fitWidthProperty().bind(scene.widthProperty().divide(33.68));
		mAnatureImg.fitHeightProperty().bind(scene.heightProperty().divide(18));
		mAnatureImg.visibleProperty().bind(mIsShown.and(mIsTabVisible));

		mHpBg.layoutXProperty().bind(scene.widthProperty().divide(25.6));
		mHpBg.layoutYProperty().bind(scene.heightProperty().divide(24.827));
		mHpBg.fitWidthProperty().bind(scene.widthProperty().divide(6.808));
		mHpBg.fitHeightProperty().bind(scene.heightProperty().divide(45));
		mHpBg.visibleProperty().bind(mIsShown.and(mIsTabVisible));

		mGender.layoutXProperty().bind(scene.widthProperty().divide(3.926));
		mGender.layoutYProperty().bind(scene.heightProperty().divide(31.3));
		mGender.fitWidthProperty().bind(scene.widthProperty().divide(75.294));
		mGender.fitHeightProperty().bind(scene.heightProperty().divide(31.3));
		mGender.visibleProperty().bind(mIsShown.and(mIsTabVisible));

		mNameTxt.layoutXProperty().bind(scene.widthProperty().divide(25.09));
		mNameTxt.layoutYProperty().bind(scene.heightProperty().divide(28));
		mNameTxt.visibleProperty().bind(mIsShown.and(mIsTabVisible));

		mLvlTxt.layoutXProperty().bind(scene.widthProperty().divide(5.31));
		mLvlTxt.layoutYProperty().bind(scene.heightProperty().divide(28));
		mLvlTxt.visibleProperty().bind(mIsShown.and(mIsTabVisible));

		mHpTxt.layoutXProperty().bind(scene.widthProperty().divide(5.31));
		mHpTxt.layoutYProperty().bind(scene.heightProperty().divide(16.5));
		mHpTxt.visibleProperty().bind(mIsShown.and(mIsTabVisible));
	}

	public void setOnMouseClick(EventHandler<MouseEvent> event)
	{
		mBg.setOnMouseClicked(event);
		mAnatureImg.setOnMouseClicked(event);
		mHpBg.setOnMouseClicked(event);
		mHpBar.setOnMouseClicked(event);
		mGender.setOnMouseClicked(event);
		mNameTxt.setOnMouseClicked(event);
		mLvlTxt.setOnMouseClicked(event);
		mHpTxt.setOnMouseClicked(event);
	}

	public void setIsSelected(boolean isSelected)
	{
		mIsSelected.set(isSelected);
	}

	public void setIsCurrent(boolean isCurrent)
	{
		mIsCurrent.set(isCurrent);
	}
}
