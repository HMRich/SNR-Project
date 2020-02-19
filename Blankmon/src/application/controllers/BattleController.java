package application.controllers;

import java.util.Random;

import application.Anature;
import application.FightManager;
import application.MoveResult;
import application.Player;
import application.animations.OpacityAnimation;
import application.animations.PlayerAnimation;
import application.animations.ProgressBarDecrease;
import application.animations.TrainerAnimation;
import application.animations.XSlideAnimation;
import application.enums.BattleChoice;
import application.trainers.Trainer;
import application.views.HpBar;
import application.views.ResizableImage;
import application.views.XpBar;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
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
	
	@FXML private ImageView mItemSelectionBg, mItemDialogue, mSelectedItem, mItemBackBtn, mItemUseBtn, mItemPotionsTab, mItemAnaCubeTab;
	@FXML private ListView<String> mItemList;
	@FXML private Rectangle mItemListBg;
	@FXML private ImageView mItemPotionTabImg, mItemAnaCubeTabImg, mSelectedItemImg;
	@FXML private Text mSelectedItemName;
	
	@FXML private ImageView mAttackDialogue, mAttackSeOne, mAttackSeTwo, mAttackSeThree, mAttackSeFour, mAttackBackBtn;
	@FXML private ImageView mAttackImgOne, mAttackImgTwo, mAttackImgThree, mAttackImgFour;
	@FXML private Text mAttackNameOne, mAttackMpOne, mAttackNameTwo, mAttackMpTwo, mAttackNameThree, mAttackMpThree, mAttackNameFour, mAttackMpFour;
	@FXML private Group mMoveSeGroup;
	
	private DoubleProperty mEnemyHp, mEnemyHpTotal;
	private DoubleProperty mPlayerHp, mPlayerHpTotal;
	private DoubleProperty mPlayerXp, mPlayerXpTotal;
	private IntegerProperty mEnemyLvl, mPlayerLvl;
	private BooleanProperty mShowItemSelection;
	private StringProperty mDialogueTxt, mPlayerName, mEnemyName, mSelectedItemTxt;
	private BooleanProperty mShowBtns, mShowMoveSelection, mShowMoveSe, mShowMoveSeOne, mShowMoveSeTwo, mShowMoveSeThree, mShowMoveSeFour;
	
	private FightManager mFightManager;
	private Trainer mEnemyTrainer;
	private ClickQueue mClickQueue;
	
	private boolean mCanClick;
	
	public void initialize()
	{
		mEnemyHp = new SimpleDoubleProperty(100);
		mEnemyHpTotal = new SimpleDoubleProperty(100);
		mPlayerHp = new SimpleDoubleProperty(100);
		mPlayerHpTotal = new SimpleDoubleProperty(100);
		mPlayerXp = new SimpleDoubleProperty(0);
		mPlayerXpTotal = new SimpleDoubleProperty(100);
		mDialogueTxt = new SimpleStringProperty("1\n2\n3");
		mPlayerName = new SimpleStringProperty("Player Name");
		mEnemyName = new SimpleStringProperty("Enemy Name");

		mSelectedItemTxt = new SimpleStringProperty("Item Name");
		
		mEnemyLvl = new SimpleIntegerProperty(100);
		mPlayerLvl = new SimpleIntegerProperty(100);

		mShowItemSelection = new SimpleBooleanProperty(false);
		
		mShowMoveSelection = new SimpleBooleanProperty(false);
		mShowMoveSe = new SimpleBooleanProperty(true);
		mShowMoveSeOne = new SimpleBooleanProperty(false);
		mShowMoveSeTwo = new SimpleBooleanProperty(false);
		mShowMoveSeThree = new SimpleBooleanProperty(false);
		mShowMoveSeFour = new SimpleBooleanProperty(false);
		
		mShowBtns = new SimpleBooleanProperty(false);
		mFightManager = null;
		mEnemyTrainer = null;
		mClickQueue = new ClickQueue();
		mCanClick = false;
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
		setUpClickTracker(scene);
		
		setUpGround(scene);
		setUpSprites(scene);
		
		setUpItemElements(scene);
		
		setUpMoveSelection(scene);
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
				back.setOnFinished(new EventHandler<ActionEvent>()
				{
					@Override
					public void handle(ActionEvent event)
					{
						mShowBtns.set(true);
					}
				});
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
		mPlayerNameTxt.textProperty().bind(mPlayerName);
		
		mEnemyNameTxt.setFont(nameFont);
		mEnemyNameTxt.layoutYProperty().bind(scene.heightProperty().divide(9.7));
		mEnemyNameTxt.layoutXProperty().bind(scene.widthProperty().divide(4.9));
		mEnemyNameTxt.fontProperty().bind(nameFontTracking);
		mEnemyNameTxt.textProperty().bind(mEnemyName);
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
		
		StringProperty mPlayerLvlTxtTxt = new SimpleStringProperty("Lvl " + mPlayerLvl.get());
		mPlayerLvl.addListener((observable, oldValue, newValue) -> mPlayerLvlTxtTxt.set("Lvl " + mPlayerLvl.get()));

		StringProperty mEnemyLvlTxtTxt = new SimpleStringProperty("Lvl " + mEnemyLvl.get());
		mEnemyLvl.addListener((observable, oldValue, newValue) -> mEnemyLvlTxtTxt.set("Lvl " + mEnemyLvl.get()));

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
		playerHpBar.progressProperty().bind(mPlayerHp.divide(mPlayerHpTotal));
		
		mPane.getChildren().add(playerHpBar);
		
		HpBar enemyHpBar = new HpBar(mEnemyHp, mEnemyHpTotal, scene);
		enemyHpBar.bindX(4.15);
		enemyHpBar.bindY(7.95);
		enemyHpBar.progressProperty().bind(mEnemyHp.divide(mEnemyHpTotal));
		
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
		atkImage.setOnAction(event -> onAttackBtn());
		grid.addColumn(0, atkImage);
		
		ResizableImage anatureImage = new ResizableImage(new Image(getClass().getResource("/resources/images/battle/Anature_Btn.png").toExternalForm()));
		anatureImage.setOnAction(event -> activateTurn(BattleChoice.Switch));
		grid.addColumn(1, anatureImage);
		
		ResizableImage bagImage = new ResizableImage(new Image(getClass().getResource("/resources/images/battle/Bag_Btn.png").toExternalForm()));
		bagImage.setOnAction(event -> onBagBtn());
		grid.add(bagImage, 0, 1);
		
		ResizableImage escapeImage = new ResizableImage(new Image(getClass().getResource("/resources/images/battle/Escape_Btn.png").toExternalForm()));
		escapeImage.setOnAction(event -> activateTurn(BattleChoice.Escape));
		grid.add(escapeImage, 1, 1);
		
		grid.visibleProperty().bind(mShowBtns);
	}
	
	private void setUpTestBtn(Scene scene)
	{
		mTestBtn.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
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
	
	public void updateElements(Player player, Trainer enemyTrainer)
	{
		Anature enemyCurr = enemyTrainer.getAnatures().get(0);
		Anature playerCurr = player.getAnatures().get(0);
		
		mEnemyName.set(enemyCurr.getName());
		mPlayerName.set(playerCurr.getName());
		
		mEnemyHp.set(enemyCurr.getCurrHp());
		mEnemyHpTotal.set(enemyCurr.getTotalHp());
		mPlayerHp.set(playerCurr.getCurrHp());
		mPlayerHpTotal.set(playerCurr.getTotalHp());
		
		mPlayerXp.set(playerCurr.getCurrentXp());
		mPlayerXpTotal.set(100); // TODO change to a standard
		
		mEnemyLvl.set(enemyCurr.getLevel());
		mPlayerLvl.set(playerCurr.getLevel());
		
		mDialogueTxt.set(enemyTrainer.getName() + " has started a battle with " + player.getName() + "!");

		mFightManager = new FightManager(player.getAnatures(), enemyTrainer.getAnatures(), player.getName(), enemyTrainer.getName());
		mEnemyTrainer = enemyTrainer;
	}
	
	private void setUpClickTracker(Scene scene)
	{
		scene.setOnMouseClicked(new EventHandler<Event>()
		{
			@Override
			public void handle(Event event)
			{
				if(mCanClick)
				{
					Runnable toRun = mClickQueue.dequeue();
					
					if(toRun != null)
					{
						mCanClick = false;
						toRun.run();
						
						if(mPlayerHp.get() == 0) // TODO Just for Demo. Change to do swapping here.
						{
							mDialogueTxt.set(mFightManager.getPlayerTeam().get(0).getName() + " has been defeated!");
							mCanClick = false;
							mShowBtns.set(false);
						}
						
						else if(mEnemyHp.get() == 0)
						{
							mDialogueTxt.set(mFightManager.getEnemyTeam().get(0).getName() + " has been defeated!");
							mCanClick = false;
							mShowBtns.set(false);
						}
					}
				}
			}
		});
	}
	
	private void setUpItemElements(Scene scene)
	{
		mItemSelectionBg.fitWidthProperty().bind(scene.widthProperty());
		mItemSelectionBg.fitHeightProperty().bind(scene.heightProperty());
		mItemSelectionBg.visibleProperty().bind(mShowItemSelection);
		
		mItemDialogue.fitWidthProperty().bind(scene.widthProperty());
		mItemDialogue.fitHeightProperty().bind(scene.heightProperty());
		mItemDialogue.visibleProperty().bind(mShowItemSelection);
		
		mSelectedItem.fitWidthProperty().bind(scene.widthProperty());
		mSelectedItem.fitHeightProperty().bind(scene.heightProperty());
		mSelectedItem.visibleProperty().bind(mShowItemSelection);
		
		mItemBackBtn.layoutXProperty().bind(scene.widthProperty().divide(2.3));
		mItemBackBtn.layoutYProperty().bind(scene.heightProperty().divide(1.19));
		mItemBackBtn.fitWidthProperty().bind(scene.widthProperty().divide(7));
		mItemBackBtn.fitHeightProperty().bind(scene.heightProperty().divide(9));
		mItemBackBtn.visibleProperty().bind(mShowItemSelection);
		mItemBackBtn.setOnMouseClicked(event -> onBackBtn());
		
		mItemUseBtn.layoutXProperty().bind(scene.widthProperty().divide(20));
		mItemUseBtn.layoutYProperty().bind(scene.heightProperty().divide(1.52));
		mItemUseBtn.fitWidthProperty().bind(scene.widthProperty().divide(7));
		mItemUseBtn.fitHeightProperty().bind(scene.heightProperty().divide(17));
		mItemUseBtn.visibleProperty().bind(mShowItemSelection);
		mItemUseBtn.setOnMouseClicked(event -> System.out.println("USE ITEM"));
		
		mItemPotionsTab.layoutXProperty().bind(scene.widthProperty().divide(4.07));
		mItemPotionsTab.layoutYProperty().bind(scene.heightProperty().divide(4.71));
		mItemPotionsTab.fitWidthProperty().bind(scene.widthProperty().divide(23));
		mItemPotionsTab.fitHeightProperty().bind(scene.heightProperty().divide(13));
		mItemPotionsTab.visibleProperty().bind(mShowItemSelection);
		mItemPotionsTab.setOnMouseClicked(event -> System.out.println("POTION"));
		
		mItemPotionTabImg.layoutXProperty().bind(scene.widthProperty().divide(4.03));
		mItemPotionTabImg.layoutYProperty().bind(scene.heightProperty().divide(4.71));
		mItemPotionTabImg.fitWidthProperty().bind(scene.widthProperty().divide(26));
		mItemPotionTabImg.fitHeightProperty().bind(scene.heightProperty().divide(13));
		mItemPotionTabImg.visibleProperty().bind(mShowItemSelection);
		mItemPotionTabImg.setOnMouseClicked(event -> System.out.println("POTION"));
		
		mItemAnaCubeTab.layoutXProperty().bind(scene.widthProperty().divide(4.06));
		mItemAnaCubeTab.layoutYProperty().bind(scene.heightProperty().divide(3.5));
		mItemAnaCubeTab.fitWidthProperty().bind(scene.widthProperty().divide(23));
		mItemAnaCubeTab.fitHeightProperty().bind(scene.heightProperty().divide(13));
		mItemAnaCubeTab.visibleProperty().bind(mShowItemSelection);
		mItemAnaCubeTab.setOnMouseClicked(event -> System.out.println("ANACUBE"));
		
		mItemAnaCubeTabImg.layoutXProperty().bind(scene.widthProperty().divide(4.07));
		mItemAnaCubeTabImg.layoutYProperty().bind(scene.heightProperty().divide(3.5));
		mItemAnaCubeTabImg.fitWidthProperty().bind(scene.widthProperty().divide(23));
		mItemAnaCubeTabImg.fitHeightProperty().bind(scene.heightProperty().divide(13));
		mItemAnaCubeTabImg.visibleProperty().bind(mShowItemSelection);
		mItemAnaCubeTabImg.setOnMouseClicked(event -> System.out.println("ANACUBE"));
		
		mItemList.layoutXProperty().bind(scene.widthProperty().divide(32));
		mItemList.layoutYProperty().bind(scene.heightProperty().divide(2.23));
		mItemList.prefWidthProperty().bind(scene.widthProperty().divide(5.5));
		mItemList.prefHeightProperty().bind(scene.heightProperty().divide(5));
		mItemList.visibleProperty().bind(mShowItemSelection);
		
		ObservableList<String> items = mItemList.getItems();
		items.add("Potions 1x");
		
		mItemList.setItems(items);
		
		mItemListBg.layoutXProperty().bind(scene.widthProperty().divide(32));
		mItemListBg.layoutYProperty().bind(scene.heightProperty().divide(2.23));
		mItemListBg.widthProperty().bind(scene.widthProperty().divide(5.5));
		mItemListBg.heightProperty().bind(scene.heightProperty().divide(5));
		mItemListBg.visibleProperty().bind(mShowItemSelection);
		
		Font selectedItemFont = Font.loadFont(getClass().getResourceAsStream("/resources/font/pixelFJ8pt1__.TTF"), 25);
		ObjectProperty<Font> selectedItemFontTracking = new SimpleObjectProperty<Font>(selectedItemFont);
		
		scene.widthProperty().addListener((observableValue, oldWidth, newWidth) -> 
		selectedItemFontTracking.set(Font.loadFont(getClass().getResourceAsStream("/resources/font/pixelFJ8pt1__.TTF"), getFontSize(scene) / 85)));
		
		scene.heightProperty().addListener((observableValue, oldHeight, newHeight) -> 
		selectedItemFontTracking.set(Font.loadFont(getClass().getResourceAsStream("/resources/font/pixelFJ8pt1__.TTF"), getFontSize(scene) / 85)));
		
		mSelectedItemName.fontProperty().bind(selectedItemFontTracking);
		mSelectedItemName.layoutXProperty().bind(scene.widthProperty().divide(12.5));
		mSelectedItemName.layoutYProperty().bind(scene.heightProperty().divide(4));
		mSelectedItemName.visibleProperty().bind(mShowItemSelection);
		mSelectedItemName.textProperty().bind(mSelectedItemTxt);
		
		mSelectedItemImg.layoutXProperty().bind(scene.widthProperty().divide(14.1));
		mSelectedItemImg.layoutYProperty().bind(scene.heightProperty().divide(4));
		mSelectedItemImg.fitWidthProperty().bind(scene.widthProperty().divide(10));
		mSelectedItemImg.fitHeightProperty().bind(scene.heightProperty().divide(5));
		mSelectedItemImg.visibleProperty().bind(mShowItemSelection);
		
		mShowItemSelection.set(false);
	}
	
	private void setUpMoveSelection(Scene scene)
	{
		mAttackDialogue.fitWidthProperty().bind(scene.widthProperty());
		mAttackDialogue.fitHeightProperty().bind(scene.heightProperty());
		mAttackDialogue.visibleProperty().bind(mShowMoveSelection);

		mAttackSeOne.fitWidthProperty().bind(scene.widthProperty());
		mAttackSeOne.fitHeightProperty().bind(scene.heightProperty());
		mAttackSeOne.visibleProperty().bind(mShowMoveSeOne);

		mAttackSeTwo.fitWidthProperty().bind(scene.widthProperty());
		mAttackSeTwo.fitHeightProperty().bind(scene.heightProperty());
		mAttackSeTwo.visibleProperty().bind(mShowMoveSeTwo);

		mAttackSeThree.fitWidthProperty().bind(scene.widthProperty());
		mAttackSeThree.fitHeightProperty().bind(scene.heightProperty());
		mAttackSeThree.visibleProperty().bind(mShowMoveSeThree);

		mAttackSeFour.fitWidthProperty().bind(scene.widthProperty());
		mAttackSeFour.fitHeightProperty().bind(scene.heightProperty());
		mAttackSeFour.visibleProperty().bind(mShowMoveSeFour);
		
		mMoveSeGroup.visibleProperty().bind(mShowMoveSe);
		
		mAttackBackBtn.layoutXProperty().bind(scene.widthProperty().divide(2.25));
		mAttackBackBtn.layoutYProperty().bind(scene.heightProperty().divide(1.16));
		mAttackBackBtn.fitWidthProperty().bind(scene.widthProperty().divide(9));
		mAttackBackBtn.fitHeightProperty().bind(scene.heightProperty().divide(11));
		mAttackBackBtn.visibleProperty().bind(mShowMoveSelection);
		mAttackBackBtn.setOnMouseClicked(event -> onBackBtn());
		
		Font moveNameFont = Font.loadFont(getClass().getResourceAsStream("/resources/font/pixelFJ8pt1__.TTF"), 25);
		ObjectProperty<Font> moveNameFontTracking = new SimpleObjectProperty<Font>(moveNameFont);

		scene.widthProperty().addListener((observableValue, oldWidth, newWidth) -> 
		moveNameFontTracking.set(Font.loadFont(getClass().getResourceAsStream("/resources/font/pixelFJ8pt1__.TTF"), getFontSize(scene) / 75)));

		scene.heightProperty().addListener((observableValue, oldHeight, newHeight) -> 
		moveNameFontTracking.set(Font.loadFont(getClass().getResourceAsStream("/resources/font/pixelFJ8pt1__.TTF"), getFontSize(scene) / 75)));
		
		Font moveMpFont = Font.loadFont(getClass().getResourceAsStream("/resources/font/pixelFJ8pt1__.TTF"), 25);
		ObjectProperty<Font> moveMpFontTracking = new SimpleObjectProperty<Font>(moveMpFont);

		scene.widthProperty().addListener((observableValue, oldWidth, newWidth) -> 
		moveMpFontTracking.set(Font.loadFont(getClass().getResourceAsStream("/resources/font/pixelFJ8pt1__.TTF"), getFontSize(scene) / 95)));

		scene.heightProperty().addListener((observableValue, oldHeight, newHeight) -> 
		moveMpFontTracking.set(Font.loadFont(getClass().getResourceAsStream("/resources/font/pixelFJ8pt1__.TTF"), getFontSize(scene) / 95)));
		
		mAttackImgOne.layoutXProperty().bind(scene.widthProperty().divide(4.5));
		mAttackImgOne.layoutYProperty().bind(scene.heightProperty().divide(1.31));
		mAttackImgOne.fitWidthProperty().bind(scene.widthProperty().divide(8.5));
		mAttackImgOne.fitHeightProperty().bind(scene.heightProperty().divide(11));
		mAttackImgOne.visibleProperty().bind(mShowMoveSelection);
		mAttackImgOne.setOnMouseClicked(event -> mShowMoveSeOne.set(true));
		
		mAttackNameOne.layoutXProperty().bind(scene.widthProperty().divide(4.9));
		mAttackNameOne.layoutYProperty().bind(scene.heightProperty().divide(1.24));
		mAttackNameOne.fontProperty().bind(moveNameFontTracking);
		mAttackNameOne.visibleProperty().bind(mShowMoveSelection);
		mAttackNameOne.setOnMouseClicked(event -> mShowMoveSeOne.set(true));
		
		mAttackMpOne.layoutXProperty().bind(scene.widthProperty().divide(4.9));
		mAttackMpOne.layoutYProperty().bind(scene.heightProperty().divide(1.19));
		mAttackMpOne.fontProperty().bind(moveMpFontTracking);
		mAttackMpOne.visibleProperty().bind(mShowMoveSelection);
		mAttackMpOne.setOnMouseClicked(event -> mShowMoveSeOne.set(true));
		
		mAttackImgTwo.layoutXProperty().bind(scene.widthProperty().divide(4.5));
		mAttackImgTwo.layoutYProperty().bind(scene.heightProperty().divide(1.158));
		mAttackImgTwo.fitWidthProperty().bind(scene.widthProperty().divide(8.5));
		mAttackImgTwo.fitHeightProperty().bind(scene.heightProperty().divide(11));
		mAttackImgTwo.visibleProperty().bind(mShowMoveSelection);
		mAttackImgTwo.setOnMouseClicked(event -> System.out.println("Move 2"));
		
		mAttackNameTwo.layoutXProperty().bind(scene.widthProperty().divide(4.9));
		mAttackNameTwo.layoutYProperty().bind(scene.heightProperty().divide(1.103));
		mAttackNameTwo.fontProperty().bind(moveNameFontTracking);
		mAttackNameTwo.visibleProperty().bind(mShowMoveSelection);
		mAttackNameTwo.setOnMouseClicked(event -> System.out.println("Move 2"));
		
		mAttackMpTwo.layoutXProperty().bind(scene.widthProperty().divide(4.9));
		mAttackMpTwo.layoutYProperty().bind(scene.heightProperty().divide(1.063));
		mAttackMpTwo.fontProperty().bind(moveMpFontTracking);
		mAttackMpTwo.visibleProperty().bind(mShowMoveSelection);
		mAttackMpTwo.setOnMouseClicked(event -> System.out.println("Move 2"));
		
		mAttackImgThree.layoutXProperty().bind(scene.widthProperty().divide(1.515));
		mAttackImgThree.layoutYProperty().bind(scene.heightProperty().divide(1.31));
		mAttackImgThree.fitWidthProperty().bind(scene.widthProperty().divide(8.5));
		mAttackImgThree.fitHeightProperty().bind(scene.heightProperty().divide(11));
		mAttackImgThree.visibleProperty().bind(mShowMoveSelection);
		mAttackImgThree.setOnMouseClicked(event -> System.out.println("Move 3"));
		
		mAttackNameThree.layoutXProperty().bind(scene.widthProperty().divide(1.557));
		mAttackNameThree.layoutYProperty().bind(scene.heightProperty().divide(1.24));
		mAttackNameThree.fontProperty().bind(moveNameFontTracking);
		mAttackNameThree.visibleProperty().bind(mShowMoveSelection);
		mAttackNameThree.setOnMouseClicked(event -> System.out.println("Move 3"));
		
		mAttackMpThree.layoutXProperty().bind(scene.widthProperty().divide(1.557));
		mAttackMpThree.layoutYProperty().bind(scene.heightProperty().divide(1.19));
		mAttackMpThree.fontProperty().bind(moveMpFontTracking);
		mAttackMpThree.visibleProperty().bind(mShowMoveSelection);
		mAttackMpThree.setOnMouseClicked(event -> System.out.println("Move 3"));
		
		mAttackImgFour.layoutXProperty().bind(scene.widthProperty().divide(1.515));
		mAttackImgFour.layoutYProperty().bind(scene.heightProperty().divide(1.158));
		mAttackImgFour.fitWidthProperty().bind(scene.widthProperty().divide(8.5));
		mAttackImgFour.fitHeightProperty().bind(scene.heightProperty().divide(11));
		mAttackImgFour.visibleProperty().bind(mShowMoveSelection);
		mAttackImgFour.setOnMouseClicked(event -> System.out.println("Move 4"));
		
		mAttackNameFour.layoutXProperty().bind(scene.widthProperty().divide(1.557));
		mAttackNameFour.layoutYProperty().bind(scene.heightProperty().divide(1.103));
		mAttackNameFour.fontProperty().bind(moveNameFontTracking);
		mAttackNameFour.visibleProperty().bind(mShowMoveSelection);
		mAttackNameFour.setOnMouseClicked(event -> System.out.println("Move 4"));
		
		mAttackMpFour.layoutXProperty().bind(scene.widthProperty().divide(1.557));
		mAttackMpFour.layoutYProperty().bind(scene.heightProperty().divide(1.063));
		mAttackMpFour.fontProperty().bind(moveMpFontTracking);
		mAttackMpFour.visibleProperty().bind(mShowMoveSelection);
		mAttackMpFour.setOnMouseClicked(event -> System.out.println("Move 4"));
	}
	
	private void activateTurn(BattleChoice choice)
	{
		mShowBtns.set(false);
		Anature enemyCurr = mFightManager.getEnemyTeam().get(0);
		Anature playerCurr = mFightManager.getPlayerTeam().get(0);
		
		String enemyTurn = mEnemyTrainer.useTurn(playerCurr); // TODO Change to an Enum
		
		int whoGoesFirst = playerCurr.getSpeed() - enemyCurr.getSpeed();
		
		if(whoGoesFirst == 0) // Will either add 0 or 1 to the total
		{
			Random r = new Random();
			whoGoesFirst += r.nextInt(2);
		}
		
		if(whoGoesFirst == 0) // Player goes first
		{
			playerTurn(choice);
			enemyTurn(enemyTurn);
		}
		
		else // Enemy goes first
		{
			enemyTurn(enemyTurn);
			playerTurn(choice);
		}
		
		mClickQueue.enqueue(new Runnable()
		{
			@Override
			public void run()
			{
				mShowBtns.set(true);
				mDialogueTxt.set("What will you do?");
			}
		});
		
		mClickQueue.dequeue().run();
	}
	
	private void playerTurn(BattleChoice choice)
	{
		switch(choice)
		{
			case Attack:
				mClickQueue.enqueue(new Runnable()
				{
					@Override
					public void run()
					{
						healthDrain(mFightManager.attackEnemy(0), mEnemyHp); // TODO Change move selected based on the one clicked
					}
				});
				break;
				
			case Bag:
				mClickQueue.enqueue(new Runnable()
				{
					@Override
					public void run()
					{
						mDialogueTxt.set("You clicked on the Bag!\nThat has yet to be implemented!");
						mCanClick = true;
					}
				});
				break;
				
			case Escape:
				mClickQueue.enqueue(new Runnable()
				{
					@Override
					public void run()
					{
						mDialogueTxt.set("You clicked on Escape!\nThat has yet to be implemented!");
						mCanClick = true;
					}
				});
				break;
				
			case Switch:
				mClickQueue.enqueue(new Runnable()
				{
					@Override
					public void run()
					{
						mDialogueTxt.set("You clicked on Anature!\nThat has yet to be implemented!");
						mCanClick = true;
					}
				});
				break;			
		}
	}
	
	private void enemyTurn(String enemyTurn)
	{
		if(enemyTurn.startsWith("Move"))
		{
			mClickQueue.enqueue(new Runnable()
			{
				@Override
				public void run()
				{
//					healthDrain(mFightManager.attackPlayer(Integer.parseInt(enemyTurn.charAt(4) + "")), mPlayerHp);
					healthDrain(mFightManager.attackPlayer(0), mPlayerHp); // TODO Change to above when Demo is Done! 
				}
			});
		}
	}
	
	private void healthDrain(MoveResult result, DoubleProperty toChange)
	{
		mDialogueTxt.set(result.getDialogueTxt());
		ProgressBarDecrease decrease = new ProgressBarDecrease(toChange, Duration.millis(3000), result.getDamageDone());
		decrease.setOnFinished(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				mCanClick = true;
			}
		});
		
		decrease.play();
	}
	
	private void onBagBtn()
	{
		mShowItemSelection.set(true);
		mShowBtns.set(false);
	}
	
	private void onAttackBtn()
	{
		mShowMoveSelection.set(true);
		mShowMoveSe.set(true);
		mShowBtns.set(false);
	}
	
	private void onBackBtn()
	{
		mShowItemSelection.set(false);
		mShowMoveSelection.set(false);
		mShowMoveSe.set(false);
		mShowBtns.set(true);
	}
}