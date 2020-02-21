package application.views;

import application.enums.Gender;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class AnatureSlot extends Pane
{
	private ImageView mBg, mAnatureImg, mHpBg, mGender;
	private Text mNameTxt, mLvlTxt, mHpTxt;
	private BooleanProperty mIsShown;
	private boolean mIsSelected;
	
	public AnatureSlot(Scene scene, boolean isSelected, Image anatureImg, Gender gender, 
			StringProperty nameTxt, StringProperty lvlTxt, StringProperty hpTxt, BooleanProperty isShown, DoubleProperty hpProp)
	{
		nullChecks(scene, anatureImg, nameTxt, lvlTxt, hpTxt);
		mIsShown = isShown;
		mIsSelected = isSelected;
		
		if(isSelected)
			mBg = new ImageView(new Image(getClass().getResource("/resources/images/battle/switching/Switch_Anature_Slot_Selected.png").toExternalForm()));
		
		else
			mBg = new ImageView(new Image(getClass().getResource("/resources/images/battle/switching/Switch_Anature_Slot_Deselected.png").toExternalForm()));
		
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
		
		mNameTxt = createText(nameTxt, scene, 55);
		mLvlTxt = createText(lvlTxt, scene, 55);
		mHpTxt = createText(hpTxt, scene, 85);
		
		AnatureSlotHpBar hpBar = new AnatureSlotHpBar(hpProp, hpProp, this);
		hpBar.bindX(3.9);
		hpBar.bindY(1.55);
		hpBar.visibleProperty().bind(isShown);
		
		getChildren().addAll(mBg, mAnatureImg, mHpBg, hpBar, mGender, mNameTxt, mLvlTxt, mHpTxt);
		createBindings(scene);
		
		setOnMouseEntered(new EventHandler<Event>()
		{
			@Override
			public void handle(Event event)
			{
				if(!mIsSelected)
					mBg.setImage(new Image(getClass().getResource("/resources/images/battle/switching/Switch_Anature_Slot_Deselected_Hover.png").toExternalForm()));
			}
		});
		
		setOnMouseExited(new EventHandler<Event>()
		{
			@Override
			public void handle(Event event)
			{
				if(!mIsSelected)
					mBg.setImage(new Image(getClass().getResource("/resources/images/battle/switching/Switch_Anature_Slot_Deselected.png").toExternalForm()));
			}
		});
	}

	private Text createText(StringProperty txt, Scene scene, int fontSize)
	{
		Font nameFont = Font.loadFont(getClass().getResourceAsStream("/resources/font/pixelFJ8pt1__.TTF"), getFontSize(scene) / fontSize);
		ObjectProperty<Font> nameFontTracking = new SimpleObjectProperty<Font>(nameFont);
		
		scene.widthProperty().addListener((observableValue, oldWidth, newWidth) -> 
		nameFontTracking.set(Font.loadFont(getClass().getResourceAsStream("/resources/font/pixelFJ8pt1__.TTF"), getFontSize(scene) / fontSize)));
		
		scene.heightProperty().addListener((observableValue, oldHeight, newHeight) -> 
		nameFontTracking.set(Font.loadFont(getClass().getResourceAsStream("/resources/font/pixelFJ8pt1__.TTF"), getFontSize(scene) / fontSize)));	
		
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
			value = scene.getHeight()  / 0.45;
		
		if(scene.getWidth() >= 1940)
			value = value - (scene.getWidth() - 1940);
		
		return value;
	}
	
	private void nullChecks(Scene scene, Image anatureImg, StringProperty nameTxt, StringProperty lvlTxt, StringProperty hpTxt)
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
		mBg.visibleProperty().bind(mIsShown);
		
		mAnatureImg.layoutXProperty().bind(scene.widthProperty().divide(160));
		mAnatureImg.layoutYProperty().bind(scene.heightProperty().divide(180));
		mAnatureImg.fitWidthProperty().bind(scene.widthProperty().divide(33.68));
		mAnatureImg.fitHeightProperty().bind(scene.heightProperty().divide(18));
		mAnatureImg.visibleProperty().bind(mIsShown);
		
		mHpBg.layoutXProperty().bind(scene.widthProperty().divide(25.6));
		mHpBg.layoutYProperty().bind(scene.heightProperty().divide(24.827));
		mHpBg.fitWidthProperty().bind(scene.widthProperty().divide(6.808));
		mHpBg.fitHeightProperty().bind(scene.heightProperty().divide(45));
		mHpBg.visibleProperty().bind(mIsShown);
		
		mGender.layoutXProperty().bind(scene.widthProperty().divide(3.926));
		mGender.layoutYProperty().bind(scene.heightProperty().divide(31.3));
		mGender.fitWidthProperty().bind(scene.widthProperty().divide(75.294));
		mGender.fitHeightProperty().bind(scene.heightProperty().divide(31.3));
		mGender.visibleProperty().bind(mIsShown);
		
		mNameTxt.layoutXProperty().bind(scene.widthProperty().divide(25.09));
		mNameTxt.layoutYProperty().bind(scene.heightProperty().divide(28));
		mNameTxt.visibleProperty().bind(mIsShown);
		
		mLvlTxt.layoutXProperty().bind(scene.widthProperty().divide(5.31));
		mLvlTxt.layoutYProperty().bind(scene.heightProperty().divide(28));
		mLvlTxt.visibleProperty().bind(mIsShown);
		
		mHpTxt.layoutXProperty().bind(scene.widthProperty().divide(5.31));
		mHpTxt.layoutYProperty().bind(scene.heightProperty().divide(16.5));
		mHpTxt.visibleProperty().bind(mIsShown);
	}
}
