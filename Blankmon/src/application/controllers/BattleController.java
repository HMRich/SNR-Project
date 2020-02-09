package application.controllers;

import application.animations.OpacityAnimation;
import application.animations.PlayerAnimation;
import application.animations.XSlideAnimation;
import application.animations.TrainerAnimation;
import application.views.HpBar;
import application.views.ResizableImage;
import application.views.XpBar;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

public class BattleController
{
	@FXML private Pane mPane;
	@FXML private ImageView mBgImage, mDialogueImage, mHpImage;
	@FXML private ImageView mPlayerImage, mTrainerImage;
	@FXML private ImageView mPlayerGroundImage, mTrainerGroundImage;
	@FXML private ImageView mAnatureFront, mAnatureBack;
	@FXML private Text mPlayerNameTxt, mEnemyNameTxt, mPlayerHpTxt, mEnemyHpTxt, mPlayerLvlTxt, mEnemyLvlTxt;
	@FXML private ImageView mPlayerGender, mEnemyGender;
	@FXML private TextArea mDialogueTxtArea;
	@FXML private Button mTestBtn;
	
	private DoubleProperty mEnemyHp, mEnemyHpTotal;
	private DoubleProperty mPlayerHp, mPlayerHpTotal;
	private DoubleProperty mPlayerXp, mPlayerXpTotal;
	private IntegerProperty mEnemyLvl, mPlayerLvl;
	private StringProperty mDialogueTxt;
	
	public void initialize()
	{
		mEnemyHp = new SimpleDoubleProperty(100);
		mEnemyHpTotal = new SimpleDoubleProperty(100);
		mPlayerHp = new SimpleDoubleProperty(100);
		mPlayerHpTotal = new SimpleDoubleProperty(100);
		mPlayerXp = new SimpleDoubleProperty(0);
		mPlayerXpTotal = new SimpleDoubleProperty(100);
		mDialogueTxt = new SimpleStringProperty("1\n2\n3");
		
		mEnemyLvl = new SimpleIntegerProperty(100);
		mPlayerLvl = new SimpleIntegerProperty(100);
	}
	
	public void setUpBindingsAndElements(Scene scene)
	{
		setUpBgImages(scene);
		setUpAnatureImgs(scene);
		setUpAnatureNames(scene);
		setUpAnatureHpTxt(scene);
		setUpAnatureLvlTxt(scene);
		setUpAnatureHpAndXpBars(scene);
		setUpAnatureGenders(scene);
		setUpBtnGrid(scene);
		setUpDialogue(scene);
		setUpTestBtn(scene);
		
		setUpGround(scene);
		setUpSprites(scene);
	}
	
	private void setUpBgImages(Scene scene)
	{
		mBgImage.fitWidthProperty().bind(scene.widthProperty());
		mBgImage.fitHeightProperty().bind(scene.heightProperty());

		mDialogueImage.fitWidthProperty().bind(scene.widthProperty());
		mDialogueImage.fitHeightProperty().bind(scene.heightProperty());
		
		mHpImage.fitWidthProperty().bind(scene.widthProperty());
		mHpImage.fitHeightProperty().bind(scene.heightProperty());
	}
	
	private void setUpSprites(Scene scene)
	{
		PlayerAnimation playerAnimation = new PlayerAnimation(mPlayerImage);
		playerAnimation.isFinished.addListener(new ChangeListener<Boolean>()
		{
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue)
			{
				OpacityAnimation back = new OpacityAnimation(mAnatureBack, Duration.millis(200));
				back.play();
			}
		});
		playerAnimation.play();

		mPlayerImage.layoutYProperty().bind(scene.heightProperty().divide(4.5));
		mPlayerImage.fitWidthProperty().bind(scene.widthProperty().divide(3));
		mPlayerImage.fitHeightProperty().bind(scene.heightProperty().divide(1.9));

		TrainerAnimation trainerAnimation = new TrainerAnimation(mTrainerImage);
		trainerAnimation.isFinished.addListener(new ChangeListener<Boolean>()
		{
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue)
			{
				OpacityAnimation back = new OpacityAnimation(mAnatureFront, Duration.millis(200));
				back.play();
			}
		});
		trainerAnimation.play();
		
		mTrainerImage.layoutYProperty().bind(scene.heightProperty().divide(13));
		mTrainerImage.fitWidthProperty().bind(scene.widthProperty().divide(5));
		mTrainerImage.fitHeightProperty().bind(scene.heightProperty().divide(3));
	}
	
	private void setUpGround(Scene scene)
	{
		XSlideAnimation xPlayerGroundSlide = new XSlideAnimation(mPlayerGroundImage, Duration.millis(1500), 1.1, 8);
		xPlayerGroundSlide.play();
		
		mPlayerGroundImage.layoutYProperty().bind(scene.heightProperty().divide(1.65));
		mPlayerGroundImage.fitWidthProperty().bind(scene.widthProperty().divide(2.4));
		mPlayerGroundImage.fitHeightProperty().bind(scene.heightProperty().divide(5));

		XSlideAnimation xTrainerGroundSlide = new XSlideAnimation(mTrainerGroundImage, Duration.millis(1500), 1.05, 2.05);
		xTrainerGroundSlide.play();

		mTrainerGroundImage.layoutYProperty().bind(scene.heightProperty().divide(3.5));
		mTrainerGroundImage.fitWidthProperty().bind(scene.widthProperty().divide(3));
		mTrainerGroundImage.fitHeightProperty().bind(scene.heightProperty().divide(6));
	}
	
	private void setUpAnatureImgs(Scene scene)
	{		
		mAnatureFront.layoutXProperty().bind(scene.widthProperty().divide(1.75));
		mAnatureFront.layoutYProperty().bind(scene.heightProperty().divide(7.5));
		mAnatureFront.fitWidthProperty().bind(scene.widthProperty().divide(5.5));
		mAnatureFront.fitHeightProperty().bind(scene.heightProperty().divide(3.5));
		mAnatureFront.setOpacity(0);

		mAnatureBack.layoutXProperty().bind(scene.widthProperty().divide(5));
		mAnatureBack.layoutYProperty().bind(scene.heightProperty().divide(2.9));
		mAnatureBack.fitWidthProperty().bind(scene.widthProperty().divide(4));
		mAnatureBack.fitHeightProperty().bind(scene.heightProperty().divide(2.5));
		mAnatureBack.setOpacity(0);
	}
	
	private void setUpAnatureNames(Scene scene)
	{
		Font nameFont = Font.loadFont(getClass().getResourceAsStream("/resources/font/pixelFJ8pt1__.TTF"), 25);
		ObjectProperty<Font> nameFontTracking = new SimpleObjectProperty<Font>(nameFont);
		
		scene.widthProperty().addListener((observableValue, oldWidth, newWidth) -> 
		nameFontTracking.set(Font.loadFont(getClass().getResourceAsStream("/resources/font/pixelFJ8pt1__.TTF"), getFontSize(scene) / 55)));
		
		scene.heightProperty().addListener((observableValue, oldHeight, newHeight) -> 
		nameFontTracking.set(Font.loadFont(getClass().getResourceAsStream("/resources/font/pixelFJ8pt1__.TTF"), getFontSize(scene) / 55)));

		mPlayerNameTxt.setFont(nameFont);
		mPlayerNameTxt.layoutYProperty().bind(scene.heightProperty().divide(2.08));
		mPlayerNameTxt.layoutXProperty().bind(scene.widthProperty().divide(1.75));
		mPlayerNameTxt.fontProperty().bind(nameFontTracking);
		
		mEnemyNameTxt.setFont(nameFont);
		mEnemyNameTxt.layoutYProperty().bind(scene.heightProperty().divide(9.7));
		mEnemyNameTxt.layoutXProperty().bind(scene.widthProperty().divide(4.9));
		mEnemyNameTxt.fontProperty().bind(nameFontTracking);
	}
	
	private void setUpAnatureHpTxt(Scene scene)
	{
		Font hpFont = Font.loadFont(getClass().getResourceAsStream("/resources/font/pixelFJ8pt1__.TTF"), 25);
		ObjectProperty<Font> hpFontTracking = new SimpleObjectProperty<Font>(hpFont);
		
		scene.widthProperty().addListener((observableValue, oldWidth, newWidth) -> 
		hpFontTracking.set(Font.loadFont(getClass().getResourceAsStream("/resources/font/pixelFJ8pt1__.TTF"), getFontSize(scene) / 85)));
		
		scene.heightProperty().addListener((observableValue, oldHeight, newHeight) -> 
		hpFontTracking.set(Font.loadFont(getClass().getResourceAsStream("/resources/font/pixelFJ8pt1__.TTF"), getFontSize(scene) / 85)));
		
		StringProperty playerHpTxt = new SimpleStringProperty(mPlayerHp.getValue().intValue() + " / " + mPlayerHpTotal.getValue().intValue());
		mPlayerHp.addListener((observable, oldValue, newValue) -> playerHpTxt.set(mPlayerHp.getValue().intValue() + " / " + mPlayerHpTotal.getValue().intValue()));
		
		StringProperty enemyHpTxt = new SimpleStringProperty(mEnemyHp.getValue().intValue() + " / " + mEnemyHpTotal.getValue().intValue());
		mEnemyHp.addListener((observable, oldValue, newValue) -> enemyHpTxt.set(mEnemyHp.getValue().intValue() + " / " + mEnemyHpTotal.getValue().intValue()));

		mPlayerHpTxt.textProperty().bind(playerHpTxt);
		mPlayerHpTxt.setFont(hpFont);
		mPlayerHpTxt.layoutYProperty().bind(scene.heightProperty().divide(1.83));
		mPlayerHpTxt.layoutXProperty().bind(scene.widthProperty().divide(1.41));
		mPlayerHpTxt.fontProperty().bind(hpFontTracking);

		mEnemyHpTxt.textProperty().bind(enemyHpTxt);
		mEnemyHpTxt.setFont(hpFont);
		mEnemyHpTxt.layoutYProperty().bind(scene.heightProperty().divide(5.8));
		mEnemyHpTxt.layoutXProperty().bind(scene.widthProperty().divide(4.7));
		mEnemyHpTxt.fontProperty().bind(hpFontTracking);
	}
	
	private void setUpAnatureLvlTxt(Scene scene)
	{
		Font lvlFont = Font.loadFont(getClass().getResourceAsStream("/resources/font/pixelFJ8pt1__.TTF"), 25);
		ObjectProperty<Font> lvlFontTracking = new SimpleObjectProperty<Font>(lvlFont);
		
		scene.widthProperty().addListener((observableValue, oldWidth, newWidth) -> 
		lvlFontTracking.set(Font.loadFont(getClass().getResourceAsStream("/resources/font/pixelFJ8pt1__.TTF"), getFontSize(scene) / 85)));
		
		scene.heightProperty().addListener((observableValue, oldHeight, newHeight) -> 
		lvlFontTracking.set(Font.loadFont(getClass().getResourceAsStream("/resources/font/pixelFJ8pt1__.TTF"), getFontSize(scene) / 85)));
		
		StringProperty mPlayerLvlTxtTxt = new SimpleStringProperty("Lvl " + mPlayerLvl.intValue());
		mPlayerLvl.addListener((observable, oldValue, newValue) -> mPlayerLvlTxtTxt.set("Lvl " + mPlayerLvl));

		StringProperty mEnemyLvlTxtTxt = new SimpleStringProperty("Lvl " + mEnemyLvl.intValue());
		mEnemyLvl.addListener((observable, oldValue, newValue) -> mEnemyLvlTxtTxt.set("Lvl " + mEnemyLvl));

		mPlayerLvlTxt.textProperty().bind(mPlayerLvlTxtTxt);
		mPlayerLvlTxt.setTextAlignment(TextAlignment.LEFT);
		mPlayerLvlTxt.setFont(lvlFont);
		mPlayerLvlTxt.setFill(Color.BLACK);
		mPlayerLvlTxt.layoutYProperty().bind(scene.heightProperty().divide(1.83));
		mPlayerLvlTxt.layoutXProperty().bind(scene.widthProperty().divide(1.71));
		mPlayerLvlTxt.fontProperty().bind(lvlFontTracking);

		mEnemyLvlTxt.textProperty().bind(mEnemyLvlTxtTxt);
		mEnemyLvlTxt.setTextAlignment(TextAlignment.LEFT);
		mEnemyLvlTxt.setFont(lvlFont);
		mEnemyLvlTxt.setFill(Color.BLACK);
		mEnemyLvlTxt.layoutYProperty().bind(scene.heightProperty().divide(5.8));
		mEnemyLvlTxt.layoutXProperty().bind(scene.widthProperty().divide(2.61));
		mEnemyLvlTxt.fontProperty().bind(lvlFontTracking);
	}
	
	private void setUpAnatureHpAndXpBars(Scene scene)
	{
		HpBar playerHpBar = new HpBar(mPlayerHp, mPlayerHpTotal, scene);
		playerHpBar.bindX(1.509);
		playerHpBar.bindY(1.995);
		mPane.getChildren().add(playerHpBar);
		
		HpBar enemyHpBar = new HpBar(mEnemyHp, mEnemyHpTotal, scene);
		enemyHpBar.bindX(4.15);
		enemyHpBar.bindY(7.95);
		mPane.getChildren().add(enemyHpBar);
		
		XpBar playerXpBar = new XpBar(mPlayerXp, mPlayerXpTotal, scene);
		playerXpBar.bindX(1.723);
		playerXpBar.bindY(1.78);
		mPane.getChildren().add(playerXpBar);
	}
	
	private void setUpAnatureGenders(Scene scene)
	{
		mPlayerGender.fitWidthProperty().bind(scene.widthProperty().divide(57));
		mPlayerGender.fitHeightProperty().bind(scene.heightProperty().divide(31));
		mPlayerGender.layoutXProperty().bind(scene.widthProperty().divide(1.79));
		mPlayerGender.layoutYProperty().bind(scene.heightProperty().divide(1.93));
		
		mEnemyGender.fitWidthProperty().bind(scene.widthProperty().divide(57));
		mEnemyGender.fitHeightProperty().bind(scene.heightProperty().divide(31));
		mEnemyGender.layoutXProperty().bind(scene.widthProperty().divide(2.8));
		mEnemyGender.layoutYProperty().bind(scene.heightProperty().divide(7));
	}
	
	private void setUpBtnGrid(Scene scene)
	{
		GridPane grid = new GridPane();
		
		ColumnConstraints colConst = new ColumnConstraints();
        colConst.setPercentWidth(100.0 / 2);
        grid.getColumnConstraints().add(colConst);
        grid.getColumnConstraints().add(colConst);
        
        RowConstraints rowConst = new RowConstraints();
        rowConst.setPercentHeight(100.0 / 2);
        grid.getRowConstraints().add(rowConst);
        grid.getRowConstraints().add(rowConst);
        
		grid.prefWidthProperty().bind(scene.widthProperty().divide(4.5));
		grid.prefHeightProperty().bind(scene.heightProperty().divide(5.8));
		grid.layoutXProperty().bind(scene.widthProperty().divide(1.84));
		grid.layoutYProperty().bind(scene.heightProperty().divide(1.296));
		grid.setHgap(5);
		grid.setVgap(10);
		mPane.getChildren().add(grid);
		
		ResizableImage atkImage = new ResizableImage(new Image(getClass().getResource("/resources/images/battle/Attack_Btn.png").toExternalForm()));
		atkImage.setOnAction(event -> mTestBtn.setText("Attack"));
		grid.addColumn(0, atkImage);
		
		ResizableImage monImage = new ResizableImage(new Image(getClass().getResource("/resources/images/battle/Anature_Btn.png").toExternalForm()));
		monImage.setOnAction(event -> mTestBtn.setText("Anature"));
		grid.addColumn(1, monImage);
		
		ResizableImage bagImage = new ResizableImage(new Image(getClass().getResource("/resources/images/battle/Bag_Btn.png").toExternalForm()));
		bagImage.setOnAction(event -> mTestBtn.setText("Bag"));
		grid.add(bagImage, 0, 1);
		
		ResizableImage escapeImage = new ResizableImage(new Image(getClass().getResource("/resources/images/battle/Escape_Btn.png").toExternalForm()));
		escapeImage.setOnAction(event -> mTestBtn.setText("Escape"));
		grid.add(escapeImage, 1, 1);
	}
	
	private void setUpTestBtn(Scene scene)
	{
		mTestBtn.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				if(mEnemyHp.get() > 0)
					mEnemyHp.set(mEnemyHp.subtract(10).doubleValue());
				
				else
					mEnemyHp.set(100);
				
				if(mPlayerHp.get() > 0)
					mPlayerHp.set(mPlayerHp.subtract(10).doubleValue());
				
				else
					mPlayerHp.set(100);

				if(mPlayerXp.get() < 100)
					mPlayerXp.set(mPlayerXp.add(10).doubleValue());
				
				else
					mPlayerXp.set(0);
			}
		});
	}
	
	private void setUpDialogue(Scene scene)
	{
		Font dialogueFont = Font.loadFont(getClass().getResourceAsStream("/resources/font/pixelFJ8pt1__.TTF"), 25);
		ObjectProperty<Font> dialogueFontTracking = new SimpleObjectProperty<Font>(dialogueFont);
		
		scene.widthProperty().addListener((observableValue, oldWidth, newWidth) -> 
		dialogueFontTracking.set(Font.loadFont(getClass().getResourceAsStream("/resources/font/pixelFJ8pt1__.TTF"), getFontSize(scene) / 55)));
		
		mDialogueTxtArea.textProperty().bind(mDialogueTxt);
		mDialogueTxtArea.setFont(dialogueFont);
		mDialogueTxtArea.getStylesheets().add("/resources/css/BattleStyle.css");
		mDialogueTxtArea.prefWidthProperty().bind(scene.widthProperty().divide(3.2));
		mDialogueTxtArea.prefHeightProperty().bind(scene.heightProperty().divide(5));
		mDialogueTxtArea.layoutYProperty().bind(scene.heightProperty().divide(1.32));
		mDialogueTxtArea.layoutXProperty().bind(scene.widthProperty().divide(4.6));
		mDialogueTxtArea.fontProperty().bind(dialogueFontTracking);
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
}
