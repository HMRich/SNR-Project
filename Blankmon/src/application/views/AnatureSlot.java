package application.views;

import application.enums.Gender;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;
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
	
	public AnatureSlot(Scene scene, boolean isSelected, Image anatureImg, Gender gender, StringProperty nameTxt, StringProperty lvlTxt, StringProperty hpTxt, BooleanProperty isShown)
	{
		nullChecks(scene, anatureImg, nameTxt, lvlTxt, hpTxt);
		
		setPrefHeight(300);
		setPrefWidth(600);
		
		setLayoutX(20);
		setLayoutY(20);
		mIsShown = isShown;
		
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
		
		mNameTxt = createText(nameTxt, scene);
		mLvlTxt = createText(lvlTxt, scene);
		mHpTxt = createText(hpTxt, scene);
		
		getChildren().addAll(mBg, mAnatureImg, mHpBg, mGender, mNameTxt, mLvlTxt, mHpTxt);
		createBindings(scene);
	}

	private Text createText(StringProperty txt, Scene scene)
	{
		int fontSize = 55;
		
		Font nameFont = Font.loadFont(getClass().getResourceAsStream("/resources/font/pixelFJ8pt1__.TTF"), getFontSize(scene) / fontSize);
		ObjectProperty<Font> nameFontTracking = new SimpleObjectProperty<Font>(nameFont);
		
		scene.widthProperty().addListener((observableValue, oldWidth, newWidth) -> 
		nameFontTracking.set(Font.loadFont(getClass().getResourceAsStream("/resources/font/pixelFJ8pt1__.TTF"), getFontSize(scene) / fontSize)));
		
		scene.heightProperty().addListener((observableValue, oldHeight, newHeight) -> 
		nameFontTracking.set(Font.loadFont(getClass().getResourceAsStream("/resources/font/pixelFJ8pt1__.TTF"), getFontSize(scene) / fontSize)));	
		
		Text text = new Text();
		text.textProperty().bind(txt);
		text.setWrappingWidth(195.44921875);
		text.setFill(Color.WHITE);
		text.setStroke(Color.BLACK);
		text.setStrokeWidth(2);
		
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
		mBg.layoutXProperty().bind(scene.widthProperty().divide(85));
		mBg.layoutYProperty().bind(scene.heightProperty().divide(4.2));
		mBg.fitWidthProperty().bind(scene.widthProperty().divide(3.7));
		mBg.fitHeightProperty().bind(scene.heightProperty().divide(15.1));
		mBg.visibleProperty().bind(mIsShown);
		
		mAnatureImg.layoutXProperty().bind(scene.widthProperty().divide(84.8));
		mAnatureImg.layoutYProperty().bind(scene.heightProperty().divide(4.2));
		mAnatureImg.fitWidthProperty().bind(scene.widthProperty().divide(26.3));
		mAnatureImg.fitHeightProperty().bind(scene.heightProperty().divide(15.1));
		mAnatureImg.visibleProperty().bind(mIsShown);
		
		mHpBg.layoutXProperty().bind(scene.widthProperty().divide(20));
		mHpBg.layoutYProperty().bind(scene.heightProperty().divide(3.65));
		mHpBg.fitWidthProperty().bind(scene.widthProperty().divide(6.7));
		mHpBg.fitHeightProperty().bind(scene.heightProperty().divide(38.5));
		mHpBg.visibleProperty().bind(mIsShown);
		
		mGender.layoutXProperty().bind(scene.widthProperty().divide(3.786));
		mGender.layoutYProperty().bind(scene.heightProperty().divide(3.75));
		mGender.fitWidthProperty().bind(scene.widthProperty().divide(67.368));
		mGender.fitHeightProperty().bind(scene.heightProperty().divide(32));
		mGender.visibleProperty().bind(mIsShown);
		
		mNameTxt.layoutXProperty().bind(scene.widthProperty().divide(19.69));
		mNameTxt.layoutYProperty().bind(scene.heightProperty().divide(3.75));
		mNameTxt.visibleProperty().bind(mIsShown);
		
		mLvlTxt.layoutXProperty().bind(scene.widthProperty().divide(4.8));
		mLvlTxt.layoutYProperty().bind(scene.heightProperty().divide(3.75));
		mLvlTxt.visibleProperty().bind(mIsShown);
		
		mHpTxt.layoutXProperty().bind(scene.widthProperty().divide(4.8));
		mHpTxt.layoutYProperty().bind(scene.heightProperty().divide(3.35));
		mHpTxt.visibleProperty().bind(mIsShown);
	}
}
