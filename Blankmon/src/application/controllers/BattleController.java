package application.controllers;

import java.util.ArrayList;
import java.util.Random;

import application.Anature;
import application.Backpack;
import application.FightManager;
import application.ItemResult;
import application.MoveResult;
import application.MoveSet;
import application.Player;
import application.animations.BlinkingAnimation;
import application.animations.OpacityAnimation;
import application.animations.PlayerAnimation;
import application.animations.ProgressBarDecrease;
import application.animations.ProgressBarIncrease;
import application.animations.XSlideAnimation;
import application.enums.BattleChoice;
import application.enums.Gender;
import application.enums.LoggingTypes;
import application.items.Item;
import application.items.ItemPool;
import application.moves.Move;
import application.trainers.Trainer;
import application.views.AnatureSlot;
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
import javafx.scene.Node;
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
	@FXML private ImageView mClickIndicatorImg;
	@FXML private Button mTestBtn;
	
	@FXML private ImageView mSwitchSelection, mSwitchDialogue, mSwitchBtn, mSwitchBackBtn, mSwitchSelectedImg, 
	mSwitchSelectedTypeOne, mSwitchSelectedTypeTwo, mSwitchPageLeft, mSwitchPageRight;
	@FXML private Text mSwitchSelectedCatalogNum, mSwitchSelectedName, mSwitchSelectedOwner, mSwitchSelectedCurrXp, mSwitchSelectedNextXp, mSwitchPageTxt;
	@FXML private Text mSwitchSelectedHp, mSwitchSelectedAtk, mSwitchSelectedSpAtk, mSwitchSelectedDef, mSwitchSelectedSpDef, mSwitchSelectedSpeed, mSwitchSelectedAbilityName, mSwitchSelectedAbilityDesc;
	private Image mSwitchPageOneImg, mSwitchPageTwoImg, mItemTabSelected, mItemTabDeselected, mItemPotion, mItemGreatPotion, mItemUltraPotion, mItemMasterPotion;
	
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
	private BooleanProperty mShowItemSelection, mShowSwitch, mShowPlayerBars, mShowSwitchPageOne, mCanClick;
	private StringProperty mDialogueTxt, mPlayerName, mEnemyName, mSelectedItemTxt;
	private BooleanProperty mShowBtns, mShowMoveSelection, mShowMoveSe, 
	mShowMoveOne, mShowMoveTwo, mShowMoveThree, mShowMoveFour,
	mShowMoveSeOne, mShowMoveSeTwo, mShowMoveSeThree, mShowMoveSeFour,
	mSwitchSlotOne, mSwitchSlotTwo, mSwitchSlotThree, mSwitchSlotFour, mSwitchSlotFive, mSwitchSlotSix;
	private StringProperty mAttackNameOneTxt, mAttackMpOneTxt, mAttackNameTwoTxt, mAttackMpTwoTxt, 
	mAttackNameThreeTxt, mAttackMpThreeTxt, mAttackNameFourTxt, mAttackMpFourTxt;
	
	private FightManager mFightManager;
	private Trainer mEnemyTrainer;
	private Player mPlayer;
	private ClickQueue mClickQueue;
	private AnatureSlot mSlotOne, mSlotTwo, mSlotThree, mSlotFour, mSlotFive, mSlotSix;
	private int mSwitchPageNum, mSwitchIndexSelected;
	
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
		
		mAttackNameOneTxt = new SimpleStringProperty("Attack 1");
		mAttackNameTwoTxt = new SimpleStringProperty("Attack 2");
		mAttackNameThreeTxt = new SimpleStringProperty("Attack 3");
		mAttackNameFourTxt = new SimpleStringProperty("Attack 4");
		
		mAttackMpOneTxt = new SimpleStringProperty("10/10");
		mAttackMpTwoTxt = new SimpleStringProperty("10/10");
		mAttackMpThreeTxt = new SimpleStringProperty("10/10");
		mAttackMpFourTxt = new SimpleStringProperty("10/10");

		mSwitchSlotOne = new SimpleBooleanProperty(false);
		mSwitchSlotTwo = new SimpleBooleanProperty(false);
		mSwitchSlotThree = new SimpleBooleanProperty(false);
		mSwitchSlotFour = new SimpleBooleanProperty(false);
		mSwitchSlotFive = new SimpleBooleanProperty(false);
		mSwitchSlotSix = new SimpleBooleanProperty(false);
		
		mShowSwitchPageOne = new SimpleBooleanProperty(true);
		mShowPlayerBars = new SimpleBooleanProperty(true);
		mShowSwitch = new SimpleBooleanProperty(false);
		mShowItemSelection = new SimpleBooleanProperty(false);
		mShowMoveSelection = new SimpleBooleanProperty(false);
		mShowMoveOne = new SimpleBooleanProperty(false);
		mShowMoveTwo = new SimpleBooleanProperty(false);
		mShowMoveThree = new SimpleBooleanProperty(false);
		mShowMoveFour = new SimpleBooleanProperty(false);
		mShowMoveSe = new SimpleBooleanProperty(true);
		mShowMoveSeOne = new SimpleBooleanProperty(false);
		mShowMoveSeTwo = new SimpleBooleanProperty(false);
		mShowMoveSeThree = new SimpleBooleanProperty(false);
		mShowMoveSeFour = new SimpleBooleanProperty(false);
		
		mShowBtns = new SimpleBooleanProperty(false);
		mFightManager = null;
		mEnemyTrainer = null;
		mClickQueue = new ClickQueue();
		mCanClick = new SimpleBooleanProperty(false);
		mSwitchPageNum = 1;
		mSwitchIndexSelected = 0;
		
		mSwitchPageOneImg = new Image(getClass().getResource("/resources/images/battle/switching/Switch_Selection_Panel_Page1.png").toExternalForm());
		mSwitchPageTwoImg = new Image(getClass().getResource("/resources/images/battle/switching/Switch_Selection_Panel_Page2.png").toExternalForm());

		mItemTabSelected = new Image(getClass().getResource("/resources/images/battle/items/White_Tab.png").toExternalForm());
		mItemTabDeselected = new Image(getClass().getResource("/resources/images/battle/items/Grey_Tab.png").toExternalForm());
		
		mItemPotion = new Image(getClass().getResource("/resources/images/items/Potion.png").toExternalForm());
		mItemGreatPotion = new Image(getClass().getResource("/resources/images/items/Great_Potion.png").toExternalForm());
		mItemUltraPotion = new Image(getClass().getResource("/resources/images/items/Ultra_Potion.png").toExternalForm());
		mItemMasterPotion = new Image(getClass().getResource("/resources/images/items/Master_Potion.png").toExternalForm());
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
		
		setUpSwitchElements(scene);
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
		
		mClickIndicatorImg.layoutXProperty().bind(scene.widthProperty().divide(2.03));
		mClickIndicatorImg.layoutYProperty().bind(scene.heightProperty().divide(1.095));
		mClickIndicatorImg.fitWidthProperty().bind(scene.widthProperty().divide(40));
		mClickIndicatorImg.fitHeightProperty().bind(scene.heightProperty().divide(30));
		mClickIndicatorImg.visibleProperty().bind(mCanClick);
		
		BlinkingAnimation blinkAnimation = new BlinkingAnimation(mClickIndicatorImg, Duration.seconds(1.5));
		blinkAnimation.play();
	}
	
	private void setUpSprites(Scene scene)
	{
		XSlideAnimation playerSlide = new XSlideAnimation(mPlayerImage, Duration.millis(1500), 1, 7);
		playerSlide.setOnFinished(event -> mCanClick.set(true));
		playerSlide.play();
		
		mClickQueue.enqueue(new Runnable()
		{
			@Override
			public void run()
			{
				PlayerAnimation playerAnimation = new PlayerAnimation(mPlayerImage);
				playerAnimation.isFinished.addListener(new ChangeListener<Boolean>()
				{
					@Override
					public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue)
					{
						OpacityAnimation back = new OpacityAnimation(mAnatureBack, Duration.millis(200), true);
						back.play();
					}
				});
				
				playerAnimation.play();
				
				OpacityAnimation trainerFade = new OpacityAnimation(mTrainerImage, Duration.millis(1000), false);
				trainerFade.setOnFinished(new EventHandler<ActionEvent>()
				{
					@Override
					public void handle(ActionEvent actionEvent)
					{
						OpacityAnimation back = new OpacityAnimation(mAnatureFront, Duration.millis(200), true);
						back.setOnFinished(event -> mShowBtns.set(true));
						back.play();
					}
				});
				
				trainerFade.play();
			}
		});

		mPlayerImage.layoutYProperty().bind(scene.heightProperty());
		mPlayerImage.layoutYProperty().bind(scene.heightProperty().divide(4.5));
		mPlayerImage.fitWidthProperty().bind(scene.widthProperty().divide(3));
		mPlayerImage.fitHeightProperty().bind(scene.heightProperty().divide(1.9));

		XSlideAnimation trainerSlide = new XSlideAnimation(mTrainerImage, Duration.millis(1500), 1, 1.8);
		trainerSlide.play();
		
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
		ObjectProperty<Font> fontProperty = getFontProperty(55, scene);

		mPlayerNameTxt.layoutYProperty().bind(scene.heightProperty().divide(2.08));
		mPlayerNameTxt.layoutXProperty().bind(scene.widthProperty().divide(1.75));
		mPlayerNameTxt.fontProperty().bind(fontProperty);
		mPlayerNameTxt.textProperty().bind(mPlayerName);
		
		mEnemyNameTxt.layoutYProperty().bind(scene.heightProperty().divide(9.7));
		mEnemyNameTxt.layoutXProperty().bind(scene.widthProperty().divide(4.9));
		mEnemyNameTxt.fontProperty().bind(fontProperty);
		mEnemyNameTxt.textProperty().bind(mEnemyName);
	}
	
	private void setUpAnatureHpTxt(Scene scene)
	{
		ObjectProperty<Font> fontProperty = getFontProperty(85, scene);
		
		StringProperty playerHpTxt = new SimpleStringProperty(mPlayerHp.getValue().intValue() + " / " + mPlayerHpTotal.getValue().intValue());
		mPlayerHp.addListener((observable, oldValue, newValue) -> playerHpTxt.set(mPlayerHp.getValue().intValue() + " / " + mPlayerHpTotal.getValue().intValue()));
		
		StringProperty enemyHpTxt = new SimpleStringProperty(mEnemyHp.getValue().intValue() + " / " + mEnemyHpTotal.getValue().intValue());
		mEnemyHp.addListener((observable, oldValue, newValue) -> enemyHpTxt.set(mEnemyHp.getValue().intValue() + " / " + mEnemyHpTotal.getValue().intValue()));

		mPlayerHpTxt.textProperty().bind(playerHpTxt);
		mPlayerHpTxt.layoutYProperty().bind(scene.heightProperty().divide(1.83));
		mPlayerHpTxt.layoutXProperty().bind(scene.widthProperty().divide(1.41));
		mPlayerHpTxt.fontProperty().bind(fontProperty);

		mEnemyHpTxt.textProperty().bind(enemyHpTxt);
		mEnemyHpTxt.layoutYProperty().bind(scene.heightProperty().divide(5.8));
		mEnemyHpTxt.layoutXProperty().bind(scene.widthProperty().divide(4.7));
		mEnemyHpTxt.fontProperty().bind(fontProperty);
	}
	
	private void setUpAnatureLvlTxt(Scene scene)
	{
		ObjectProperty<Font> fontProperty = getFontProperty(85, scene);
		
		StringProperty mPlayerLvlTxtTxt = new SimpleStringProperty("Lvl " + mPlayerLvl.get());
		mPlayerLvl.addListener((observable, oldValue, newValue) -> mPlayerLvlTxtTxt.set("Lvl " + mPlayerLvl.get()));

		StringProperty mEnemyLvlTxtTxt = new SimpleStringProperty("Lvl " + mEnemyLvl.get());
		mEnemyLvl.addListener((observable, oldValue, newValue) -> mEnemyLvlTxtTxt.set("Lvl " + mEnemyLvl.get()));

		mPlayerLvlTxt.textProperty().bind(mPlayerLvlTxtTxt);
		mPlayerLvlTxt.setTextAlignment(TextAlignment.LEFT);
		mPlayerLvlTxt.setFill(Color.BLACK);
		mPlayerLvlTxt.layoutYProperty().bind(scene.heightProperty().divide(1.83));
		mPlayerLvlTxt.layoutXProperty().bind(scene.widthProperty().divide(1.71));
		mPlayerLvlTxt.fontProperty().bind(fontProperty);

		mEnemyLvlTxt.textProperty().bind(mEnemyLvlTxtTxt);
		mEnemyLvlTxt.setTextAlignment(TextAlignment.LEFT);
		mEnemyLvlTxt.setFill(Color.BLACK);
		mEnemyLvlTxt.layoutYProperty().bind(scene.heightProperty().divide(5.8));
		mEnemyLvlTxt.layoutXProperty().bind(scene.widthProperty().divide(2.61));
		mEnemyLvlTxt.fontProperty().bind(fontProperty);
	}
	
	private void setUpAnatureHpAndXpBars(Scene scene)
	{
		HpBar playerHpBar = new HpBar(mPlayerHp, mPlayerHpTotal, scene);
		playerHpBar.bindX(1.509);
		playerHpBar.bindY(1.995);
		playerHpBar.progressProperty().bind(mPlayerHp.divide(mPlayerHpTotal));
		playerHpBar.visibleProperty().bind(mShowPlayerBars);
		
		mPane.getChildren().add(playerHpBar);
		
		HpBar enemyHpBar = new HpBar(mEnemyHp, mEnemyHpTotal, scene);
		enemyHpBar.bindX(4.15);
		enemyHpBar.bindY(7.95);
		enemyHpBar.progressProperty().bind(mEnemyHp.divide(mEnemyHpTotal));
		
		mPane.getChildren().add(enemyHpBar);
		
		XpBar playerXpBar = new XpBar(mPlayerXp, mPlayerXpTotal, scene);
		playerXpBar.bindX(1.723);
		playerXpBar.bindY(1.78);
		playerXpBar.visibleProperty().bind(mShowPlayerBars);
		
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
		anatureImage.setOnAction(event -> onSwitchBtn());
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
		ObjectProperty<Font> fontProperty = getFontProperty(75, scene);
		
		mDialogueTxtArea.textProperty().bind(mDialogueTxt);
		mDialogueTxtArea.getStylesheets().add("/resources/css/BattleStyle.css");
		mDialogueTxtArea.prefWidthProperty().bind(scene.widthProperty().divide(3.2));
		mDialogueTxtArea.prefHeightProperty().bind(scene.heightProperty().divide(5));
		mDialogueTxtArea.layoutYProperty().bind(scene.heightProperty().divide(1.32));
		mDialogueTxtArea.layoutXProperty().bind(scene.widthProperty().divide(4.6));
		mDialogueTxtArea.fontProperty().bind(fontProperty);
	}
	
	private void setUpClickTracker(Scene scene)
	{
		scene.setOnMouseClicked(new EventHandler<Event>()
		{
			@Override
			public void handle(Event event)
			{
				if(mCanClick.get())
				{
					Runnable toRun = mClickQueue.dequeue();
					
					if(toRun != null)
					{
						mCanClick.set(false);
						toRun.run();
						
						if(mPlayerHp.get() == 0) // TODO Just for Demo. Change to do swapping here.
						{
							mDialogueTxt.set(mFightManager.getPlayerTeam().get(0).getName() + " has been defeated!");
							mCanClick.set(false);
							mShowBtns.set(false);
						}
						
						else if(mEnemyHp.get() == 0)
						{
							mDialogueTxt.set(mFightManager.getEnemyTeam().get(0).getName() + " has been defeated!");
							mCanClick.set(false);
							mShowBtns.set(false);
						}
					}
				}
			}
		});
	}
	
	private void setUpSwitchElements(Scene scene)
	{
		ObjectProperty<Font> fontProperty = getFontProperty(45, scene);
		ObjectProperty<Font> pageFontProperty = getFontProperty(65, scene);
		
		mSwitchSelection.fitWidthProperty().bind(scene.widthProperty());
		mSwitchSelection.fitHeightProperty().bind(scene.heightProperty());
		mSwitchSelection.visibleProperty().bind(mShowSwitch);

		mSwitchDialogue.fitWidthProperty().bind(scene.widthProperty());
		mSwitchDialogue.fitHeightProperty().bind(scene.heightProperty());
		mSwitchDialogue.visibleProperty().bind(mShowSwitch);
		
		mSwitchBtn.layoutXProperty().bind(scene.widthProperty().divide(2.71));
		mSwitchBtn.layoutYProperty().bind(scene.heightProperty().divide(1.5));
		mSwitchBtn.fitWidthProperty().bind(scene.widthProperty().divide(8.31));
		mSwitchBtn.fitHeightProperty().bind(scene.heightProperty().divide(21.818));
		mSwitchBtn.visibleProperty().bind(mShowSwitch);
		mSwitchBtn.setOnMouseClicked(event -> activateTurn(BattleChoice.Switch));
		
		mSwitchBackBtn.layoutXProperty().bind(scene.widthProperty().divide(2.25));
		mSwitchBackBtn.layoutYProperty().bind(scene.heightProperty().divide(1.16));
		mSwitchBackBtn.fitWidthProperty().bind(scene.widthProperty().divide(9));
		mSwitchBackBtn.fitHeightProperty().bind(scene.heightProperty().divide(11));
		mSwitchBackBtn.visibleProperty().bind(mShowSwitch);
		mSwitchBackBtn.setOnMouseClicked(event -> onBackBtn());
		
		mSwitchSelectedImg.layoutXProperty().bind(scene.widthProperty().divide(2.889));
		mSwitchSelectedImg.layoutYProperty().bind(scene.heightProperty().divide(3.396));
		mSwitchSelectedImg.fitWidthProperty().bind(scene.widthProperty().divide(5.638));
		mSwitchSelectedImg.fitHeightProperty().bind(scene.heightProperty().divide(3.03));
		mSwitchSelectedImg.visibleProperty().bind(mShowSwitch);
		
		setUpSwitchPageOne(scene, fontProperty);
		setUpSwitchPageTwo(scene, fontProperty);
		
		mSwitchPageTxt.layoutXProperty().bind(scene.widthProperty().divide(1.16));
		mSwitchPageTxt.layoutYProperty().bind(scene.heightProperty().divide(5.4));
		mSwitchPageTxt.fontProperty().bind(pageFontProperty);
		mSwitchPageTxt.visibleProperty().bind(mShowSwitch);
		
		mSwitchPageLeft.layoutXProperty().bind(scene.widthProperty().divide(1.245));
		mSwitchPageLeft.layoutYProperty().bind(scene.heightProperty().divide(6.99));
		mSwitchPageLeft.fitWidthProperty().bind(scene.widthProperty().divide(46.23));
		mSwitchPageLeft.fitHeightProperty().bind(scene.heightProperty().divide(18.46));
		mSwitchPageLeft.visibleProperty().bind(mShowSwitch);
		
		mSwitchPageRight.layoutXProperty().bind(scene.widthProperty().divide(1.043));
		mSwitchPageRight.layoutYProperty().bind(scene.heightProperty().divide(6.99));
		mSwitchPageRight.fitWidthProperty().bind(scene.widthProperty().divide(46.23));
		mSwitchPageRight.fitHeightProperty().bind(scene.heightProperty().divide(18.46));
		mSwitchPageRight.visibleProperty().bind(mShowSwitch);
		
		setUpAnatureTabs(scene);
	}
	
	private void setUpSwitchPageOne(Scene scene, ObjectProperty<Font> fontTracking)
	{
		ObjectProperty<Font> nameFontTracking = getFontProperty(75, scene);
		
		mSwitchSelectedCatalogNum.layoutXProperty().bind(scene.widthProperty().divide(1.2895));
		mSwitchSelectedCatalogNum.layoutYProperty().bind(scene.heightProperty().divide(3.7));
		mSwitchSelectedCatalogNum.fontProperty().bind(fontTracking);
		mSwitchSelectedCatalogNum.visibleProperty().bind(mShowSwitchPageOne.and(mShowSwitch));
		
		mSwitchSelectedName.layoutXProperty().bind(scene.widthProperty().divide(1.2895));
		mSwitchSelectedName.layoutYProperty().bind(scene.heightProperty().divide(2.8));
		mSwitchSelectedName.fontProperty().bind(fontTracking);
		mSwitchSelectedName.visibleProperty().bind(mShowSwitchPageOne.and(mShowSwitch));
		
		mSwitchSelectedOwner.layoutXProperty().bind(scene.widthProperty().divide(1.2895));
		mSwitchSelectedOwner.layoutYProperty().bind(scene.heightProperty().divide(1.93));
		mSwitchSelectedOwner.fontProperty().bind(nameFontTracking);
		mSwitchSelectedOwner.visibleProperty().bind(mShowSwitchPageOne.and(mShowSwitch));
		
		mSwitchSelectedCurrXp.layoutXProperty().bind(scene.widthProperty().divide(1.2895));
		mSwitchSelectedCurrXp.layoutYProperty().bind(scene.heightProperty().divide(1.65));
		mSwitchSelectedCurrXp.fontProperty().bind(fontTracking);
		mSwitchSelectedCurrXp.visibleProperty().bind(mShowSwitchPageOne.and(mShowSwitch));
		
		mSwitchSelectedNextXp.layoutXProperty().bind(scene.widthProperty().divide(1.2895));
		mSwitchSelectedNextXp.layoutYProperty().bind(scene.heightProperty().divide(1.45));
		mSwitchSelectedNextXp.fontProperty().bind(fontTracking);
		mSwitchSelectedNextXp.visibleProperty().bind(mShowSwitchPageOne.and(mShowSwitch));
		
		mSwitchSelectedTypeOne.layoutXProperty().bind(scene.widthProperty().divide(1.298));
		mSwitchSelectedTypeOne.layoutYProperty().bind(scene.heightProperty().divide(2.52));
		mSwitchSelectedTypeOne.fitWidthProperty().bind(scene.widthProperty().divide(10.578));
		mSwitchSelectedTypeOne.fitHeightProperty().bind(scene.heightProperty().divide(18));
		mSwitchSelectedTypeOne.visibleProperty().bind(mShowSwitchPageOne.and(mShowSwitch));
		
		mSwitchSelectedTypeTwo.layoutXProperty().bind(scene.widthProperty().divide(1.1469));
		mSwitchSelectedTypeTwo.layoutYProperty().bind(scene.heightProperty().divide(2.52));
		mSwitchSelectedTypeTwo.fitWidthProperty().bind(scene.widthProperty().divide(10.578));
		mSwitchSelectedTypeTwo.fitHeightProperty().bind(scene.heightProperty().divide(18));
//		mSwitchSelectedTypeTwo.visibleProperty().bind(mShowSwitchPageOne.and(mShowSwitch));
		mSwitchSelectedTypeTwo.setVisible(false);
	}
	
	private void setUpSwitchPageTwo(Scene scene, ObjectProperty<Font> fontTracking)
	{
		ObjectProperty<Font> abilityDescFontTracking = getFontProperty(85, scene);
		
		mSwitchSelectedHp.layoutXProperty().bind(scene.widthProperty().divide(1.4));
		mSwitchSelectedHp.layoutYProperty().bind(scene.heightProperty().divide(3.7));
		mSwitchSelectedHp.fontProperty().bind(fontTracking);
		mSwitchSelectedHp.visibleProperty().bind(mShowSwitchPageOne.not().and(mShowSwitch));

		mSwitchSelectedAtk.layoutXProperty().bind(scene.widthProperty().divide(1.4));
		mSwitchSelectedAtk.layoutYProperty().bind(scene.heightProperty().divide(3));
		mSwitchSelectedAtk.fontProperty().bind(fontTracking);
		mSwitchSelectedAtk.visibleProperty().bind(mShowSwitchPageOne.not().and(mShowSwitch));

		mSwitchSelectedSpAtk.layoutXProperty().bind(scene.widthProperty().divide(1.4));
		mSwitchSelectedSpAtk.layoutYProperty().bind(scene.heightProperty().divide(2.55));
		mSwitchSelectedSpAtk.fontProperty().bind(fontTracking);
		mSwitchSelectedSpAtk.visibleProperty().bind(mShowSwitchPageOne.not().and(mShowSwitch));

		mSwitchSelectedDef.layoutXProperty().bind(scene.widthProperty().divide(1.4));
		mSwitchSelectedDef.layoutYProperty().bind(scene.heightProperty().divide(2.2));
		mSwitchSelectedDef.fontProperty().bind(fontTracking);
		mSwitchSelectedDef.visibleProperty().bind(mShowSwitchPageOne.not().and(mShowSwitch));

		mSwitchSelectedSpDef.layoutXProperty().bind(scene.widthProperty().divide(1.4));
		mSwitchSelectedSpDef.layoutYProperty().bind(scene.heightProperty().divide(1.94));
		mSwitchSelectedSpDef.fontProperty().bind(fontTracking);
		mSwitchSelectedSpDef.visibleProperty().bind(mShowSwitchPageOne.not().and(mShowSwitch));

		mSwitchSelectedSpeed.layoutXProperty().bind(scene.widthProperty().divide(1.4));
		mSwitchSelectedSpeed.layoutYProperty().bind(scene.heightProperty().divide(1.73));
		mSwitchSelectedSpeed.fontProperty().bind(fontTracking);
		mSwitchSelectedSpeed.visibleProperty().bind(mShowSwitchPageOne.not().and(mShowSwitch));

		mSwitchSelectedAbilityName.layoutXProperty().bind(scene.widthProperty().divide(1.4));
		mSwitchSelectedAbilityName.layoutYProperty().bind(scene.heightProperty().divide(1.57));
		mSwitchSelectedAbilityName.fontProperty().bind(fontTracking);
		mSwitchSelectedAbilityName.visibleProperty().bind(mShowSwitchPageOne.not().and(mShowSwitch));

		mSwitchSelectedAbilityDesc.layoutXProperty().bind(scene.widthProperty().divide(1.4));
		mSwitchSelectedAbilityDesc.layoutYProperty().bind(scene.heightProperty().divide(1.48));
		mSwitchSelectedAbilityDesc.fontProperty().bind(abilityDescFontTracking);
		mSwitchSelectedAbilityDesc.wrappingWidthProperty().bind(scene.widthProperty().divide(3.71));
		mSwitchSelectedAbilityDesc.visibleProperty().bind(mShowSwitchPageOne.not().and(mShowSwitch));
	}
	
	private void setUpAnatureTabs(Scene scene)
	{
		Image anatureImg = new Image(getClass().getResource("/resources/images/anatures/Null_Front.png").toExternalForm());
		
		mSlotOne = new AnatureSlot(scene, true, anatureImg, Gender.Female, "Null", "Lvl 5", "20/20", mShowSwitch, mSwitchSlotOne, 100.0, true);
		mSlotOne.layoutXProperty().bind(scene.widthProperty().divide(85));
		mSlotOne.layoutYProperty().bind(scene.heightProperty().divide(4.2));
		mSlotOne.prefWidthProperty().bind(scene.widthProperty().divide(3.7));
		mSlotOne.prefHeightProperty().bind(scene.heightProperty().divide(15.1));
		mSlotOne.setOnMouseClick(event -> updateSwitchSelected(0));
		
		mSlotTwo = new AnatureSlot(scene, false, anatureImg, Gender.Male, "Null", "Lvl 5", "20/20", mShowSwitch, mSwitchSlotTwo, 100.0, false);
		mSlotTwo.layoutXProperty().bind(scene.widthProperty().divide(85));
		mSlotTwo.layoutYProperty().bind(scene.heightProperty().divide(3.157));
		mSlotTwo.prefWidthProperty().bind(scene.widthProperty().divide(3.7));
		mSlotTwo.prefHeightProperty().bind(scene.heightProperty().divide(15.1));
		mSlotTwo.setOnMouseClick(event -> updateSwitchSelected(1));
		
		mSlotThree = new AnatureSlot(scene, false, anatureImg, Gender.Female, "Null", "Lvl 5", "20/20", mShowSwitch, mSwitchSlotThree, 100.0, false);
		mSlotThree.layoutXProperty().bind(scene.widthProperty().divide(85));
		mSlotThree.layoutYProperty().bind(scene.heightProperty().divide(2.54));
		mSlotThree.prefWidthProperty().bind(scene.widthProperty().divide(3.7));
		mSlotThree.prefHeightProperty().bind(scene.heightProperty().divide(15.1));
		mSlotThree.setOnMouseClick(event -> updateSwitchSelected(2));
		
		mSlotFour = new AnatureSlot(scene, false, anatureImg, Gender.Male, "Null", "Lvl 5", "20/20", mShowSwitch, mSwitchSlotFour, 100.0, false);
		mSlotFour.layoutXProperty().bind(scene.widthProperty().divide(85));
		mSlotFour.layoutYProperty().bind(scene.heightProperty().divide(2.114));
		mSlotFour.prefWidthProperty().bind(scene.widthProperty().divide(3.7));
		mSlotFour.prefHeightProperty().bind(scene.heightProperty().divide(15.1));
		mSlotFour.setOnMouseClick(event -> updateSwitchSelected(3));
		
		mSlotFive = new AnatureSlot(scene, false, anatureImg, Gender.Female, "Null", "Lvl 5", "20/20", mShowSwitch, mSwitchSlotFive, 100.0, false);
		mSlotFive.layoutXProperty().bind(scene.widthProperty().divide(85));
		mSlotFive.layoutYProperty().bind(scene.heightProperty().divide(1.82));
		mSlotFive.prefWidthProperty().bind(scene.widthProperty().divide(3.7));
		mSlotFive.prefHeightProperty().bind(scene.heightProperty().divide(15.1));
		mSlotFive.setOnMouseClick(event -> updateSwitchSelected(4));
		
		mSlotSix = new AnatureSlot(scene, false, anatureImg, Gender.Male, "Null", "Lvl 5", "20/20", mShowSwitch, mSwitchSlotSix, 100.0, false);
		mSlotSix.layoutXProperty().bind(scene.widthProperty().divide(85));
		mSlotSix.layoutYProperty().bind(scene.heightProperty().divide(1.59));
		mSlotSix.prefWidthProperty().bind(scene.widthProperty().divide(3.7));
		mSlotSix.prefHeightProperty().bind(scene.heightProperty().divide(15.1));
		mSlotSix.setOnMouseClick(event -> updateSwitchSelected(5));
		
		ArrayList<Node> slots = new ArrayList<Node>();
		slots.add(mSlotOne);
		slots.add(mSlotTwo);
		slots.add(mSlotThree);
		slots.add(mSlotFour);
		slots.add(mSlotFive);
		slots.add(mSlotSix);
		
		mPane.getChildren().addAll(mPane.getChildren().size() - 15, slots);
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
		mItemUseBtn.setOnMouseClicked(event -> activateTurn(BattleChoice.Item));
		
		mItemPotionsTab.layoutXProperty().bind(scene.widthProperty().divide(4.07));
		mItemPotionsTab.layoutYProperty().bind(scene.heightProperty().divide(4.71));
		mItemPotionsTab.fitWidthProperty().bind(scene.widthProperty().divide(23));
		mItemPotionsTab.fitHeightProperty().bind(scene.heightProperty().divide(13));
		mItemPotionsTab.visibleProperty().bind(mShowItemSelection);
		mItemPotionsTab.setOnMouseClicked(event -> onItemTab(true));
		
		mItemPotionTabImg.layoutXProperty().bind(scene.widthProperty().divide(4.03));
		mItemPotionTabImg.layoutYProperty().bind(scene.heightProperty().divide(4.71));
		mItemPotionTabImg.fitWidthProperty().bind(scene.widthProperty().divide(26));
		mItemPotionTabImg.fitHeightProperty().bind(scene.heightProperty().divide(13));
		mItemPotionTabImg.visibleProperty().bind(mShowItemSelection);
		mItemPotionTabImg.setOnMouseClicked(event -> onItemTab(true));
		
		mItemAnaCubeTab.layoutXProperty().bind(scene.widthProperty().divide(4.06));
		mItemAnaCubeTab.layoutYProperty().bind(scene.heightProperty().divide(3.5));
		mItemAnaCubeTab.fitWidthProperty().bind(scene.widthProperty().divide(23));
		mItemAnaCubeTab.fitHeightProperty().bind(scene.heightProperty().divide(13));
		mItemAnaCubeTab.visibleProperty().bind(mShowItemSelection);
		mItemAnaCubeTab.setOnMouseClicked(event -> onItemTab(false));
		
		mItemAnaCubeTabImg.layoutXProperty().bind(scene.widthProperty().divide(4.07));
		mItemAnaCubeTabImg.layoutYProperty().bind(scene.heightProperty().divide(3.5));
		mItemAnaCubeTabImg.fitWidthProperty().bind(scene.widthProperty().divide(23));
		mItemAnaCubeTabImg.fitHeightProperty().bind(scene.heightProperty().divide(13));
		mItemAnaCubeTabImg.visibleProperty().bind(mShowItemSelection);
		mItemAnaCubeTabImg.setOnMouseClicked(event -> onItemTab(false));
		
		mItemList.layoutXProperty().bind(scene.widthProperty().divide(32));
		mItemList.layoutYProperty().bind(scene.heightProperty().divide(2.23));
		mItemList.prefWidthProperty().bind(scene.widthProperty().divide(5.5));
		mItemList.prefHeightProperty().bind(scene.heightProperty().divide(5));
		mItemList.visibleProperty().bind(mShowItemSelection);
		mItemList.setOnMouseClicked(event -> onItemSelect());
		
		mItemListBg.layoutXProperty().bind(scene.widthProperty().divide(32));
		mItemListBg.layoutYProperty().bind(scene.heightProperty().divide(2.23));
		mItemListBg.widthProperty().bind(scene.widthProperty().divide(5.5));
		mItemListBg.heightProperty().bind(scene.heightProperty().divide(5));
		mItemListBg.visibleProperty().bind(mShowItemSelection);
		
		ObjectProperty<Font> fontProperty = getFontProperty(85, scene);
		
		mSelectedItemName.fontProperty().bind(fontProperty);
		mSelectedItemName.layoutXProperty().bind(scene.widthProperty().divide(28.44));
		mSelectedItemName.layoutYProperty().bind(scene.heightProperty().divide(4));
		mSelectedItemName.visibleProperty().bind(mShowItemSelection);
		mSelectedItemName.wrappingWidthProperty().bind(scene.widthProperty().divide(5.8479));
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
		
		ObjectProperty<Font> moveNameFontProperty = getFontProperty(75, scene);
		ObjectProperty<Font> moveMpFontProperty = getFontProperty(95, scene);
		
		mAttackImgOne.layoutXProperty().bind(scene.widthProperty().divide(4.5));
		mAttackImgOne.layoutYProperty().bind(scene.heightProperty().divide(1.31));
		mAttackImgOne.fitWidthProperty().bind(scene.widthProperty().divide(8.5));
		mAttackImgOne.fitHeightProperty().bind(scene.heightProperty().divide(11));
		mAttackImgOne.visibleProperty().bind(mShowMoveSelection.and(mShowMoveOne));
		mAttackImgOne.setOnMouseClicked(event -> activateTurn(BattleChoice.Attack_1));
		
		mAttackNameOne.textProperty().bind(mAttackNameOneTxt);
		mAttackNameOne.layoutXProperty().bind(scene.widthProperty().divide(4.39));
		mAttackNameOne.layoutYProperty().bind(scene.heightProperty().divide(1.24));
		mAttackNameOne.wrappingWidthProperty().bind(scene.widthProperty().divide(9.36));
		mAttackNameOne.fontProperty().bind(moveNameFontProperty);
		mAttackNameOne.visibleProperty().bind(mShowMoveSelection.and(mShowMoveOne));
		mAttackNameOne.setOnMouseClicked(event -> activateTurn(BattleChoice.Attack_1));

		mAttackMpOne.textProperty().bind(mAttackMpOneTxt);
		mAttackMpOne.layoutXProperty().bind(scene.widthProperty().divide(4.39));
		mAttackMpOne.layoutYProperty().bind(scene.heightProperty().divide(1.19));
		mAttackMpOne.wrappingWidthProperty().bind(scene.widthProperty().divide(9.36));
		mAttackMpOne.fontProperty().bind(moveMpFontProperty);
		mAttackMpOne.visibleProperty().bind(mShowMoveSelection.and(mShowMoveOne));
		mAttackMpOne.setOnMouseClicked(event -> activateTurn(BattleChoice.Attack_1));
		
		mAttackImgTwo.layoutXProperty().bind(scene.widthProperty().divide(4.5));
		mAttackImgTwo.layoutYProperty().bind(scene.heightProperty().divide(1.158));
		mAttackImgTwo.fitWidthProperty().bind(scene.widthProperty().divide(8.5));
		mAttackImgTwo.fitHeightProperty().bind(scene.heightProperty().divide(11));
		mAttackImgTwo.visibleProperty().bind(mShowMoveSelection.and(mShowMoveTwo));
		mAttackImgTwo.setOnMouseClicked(event -> activateTurn(BattleChoice.Attack_2));

		mAttackNameTwo.textProperty().bind(mAttackMpTwoTxt);
		mAttackNameTwo.layoutXProperty().bind(scene.widthProperty().divide(4.39));
		mAttackNameTwo.layoutYProperty().bind(scene.heightProperty().divide(1.103));
		mAttackNameTwo.wrappingWidthProperty().bind(scene.widthProperty().divide(9.36));
		mAttackNameTwo.fontProperty().bind(moveNameFontProperty);
		mAttackNameTwo.visibleProperty().bind(mShowMoveSelection.and(mShowMoveTwo));
		mAttackNameTwo.setOnMouseClicked(event -> activateTurn(BattleChoice.Attack_2));

		mAttackMpTwo.textProperty().bind(mAttackMpTwoTxt);
		mAttackMpTwo.textProperty().bind(mAttackNameTwoTxt);
		mAttackMpTwo.layoutXProperty().bind(scene.widthProperty().divide(4.39));
		mAttackMpTwo.layoutYProperty().bind(scene.heightProperty().divide(1.063));
		mAttackMpTwo.wrappingWidthProperty().bind(scene.widthProperty().divide(9.36));
		mAttackMpTwo.fontProperty().bind(moveMpFontProperty);
		mAttackMpTwo.visibleProperty().bind(mShowMoveSelection.and(mShowMoveTwo));
		mAttackMpTwo.setOnMouseClicked(event -> activateTurn(BattleChoice.Attack_2));
		
		mAttackImgThree.layoutXProperty().bind(scene.widthProperty().divide(1.515));
		mAttackImgThree.layoutYProperty().bind(scene.heightProperty().divide(1.31));
		mAttackImgThree.fitWidthProperty().bind(scene.widthProperty().divide(8.5));
		mAttackImgThree.fitHeightProperty().bind(scene.heightProperty().divide(11));
		mAttackImgThree.visibleProperty().bind(mShowMoveSelection.and(mShowMoveThree));
		mAttackImgThree.setOnMouseClicked(event -> activateTurn(BattleChoice.Attack_3));

		mAttackNameThree.textProperty().bind(mAttackNameThreeTxt);
		mAttackNameThree.layoutXProperty().bind(scene.widthProperty().divide(1.507));
		mAttackNameThree.layoutYProperty().bind(scene.heightProperty().divide(1.24));
		mAttackNameThree.wrappingWidthProperty().bind(scene.widthProperty().divide(9.36));
		mAttackNameThree.fontProperty().bind(moveNameFontProperty);
		mAttackNameThree.visibleProperty().bind(mShowMoveSelection.and(mShowMoveThree));
		mAttackNameThree.setOnMouseClicked(event -> activateTurn(BattleChoice.Attack_3));

		mAttackMpThree.textProperty().bind(mAttackMpThreeTxt);
		mAttackMpThree.layoutXProperty().bind(scene.widthProperty().divide(1.507));
		mAttackMpThree.layoutYProperty().bind(scene.heightProperty().divide(1.19));
		mAttackMpThree.wrappingWidthProperty().bind(scene.widthProperty().divide(9.36));
		mAttackMpThree.fontProperty().bind(moveMpFontProperty);
		mAttackMpThree.visibleProperty().bind(mShowMoveSelection.and(mShowMoveThree));
		mAttackMpThree.setOnMouseClicked(event -> activateTurn(BattleChoice.Attack_3));
		
		mAttackImgFour.layoutXProperty().bind(scene.widthProperty().divide(1.515));
		mAttackImgFour.layoutYProperty().bind(scene.heightProperty().divide(1.158));
		mAttackImgFour.fitWidthProperty().bind(scene.widthProperty().divide(8.5));
		mAttackImgFour.fitHeightProperty().bind(scene.heightProperty().divide(11));
		mAttackImgFour.visibleProperty().bind(mShowMoveSelection.and(mShowMoveFour));
		mAttackImgFour.setOnMouseClicked(event -> activateTurn(BattleChoice.Attack_4));

		mAttackNameFour.textProperty().bind(mAttackNameFourTxt);
		mAttackNameFour.layoutXProperty().bind(scene.widthProperty().divide(1.507));
		mAttackNameFour.layoutYProperty().bind(scene.heightProperty().divide(1.103));
		mAttackNameFour.wrappingWidthProperty().bind(scene.widthProperty().divide(9.36));
		mAttackNameFour.fontProperty().bind(moveNameFontProperty);
		mAttackNameFour.visibleProperty().bind(mShowMoveSelection.and(mShowMoveFour));
		mAttackNameFour.setOnMouseClicked(event -> activateTurn(BattleChoice.Attack_4));

		mAttackMpFour.textProperty().bind(mAttackMpFourTxt);
		mAttackMpFour.layoutXProperty().bind(scene.widthProperty().divide(1.507));
		mAttackMpFour.layoutYProperty().bind(scene.heightProperty().divide(1.063));
		mAttackMpFour.wrappingWidthProperty().bind(scene.widthProperty().divide(9.36));
		mAttackMpFour.fontProperty().bind(moveMpFontProperty);
		mAttackMpFour.visibleProperty().bind(mShowMoveSelection.and(mShowMoveFour));
		mAttackMpFour.setOnMouseClicked(event -> activateTurn(BattleChoice.Attack_4));
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
		
		mEnemyHp.set(enemyCurr.getCurrHp());
		mEnemyHpTotal.set(enemyCurr.getTotalHp());
		
		mEnemyLvl.set(enemyCurr.getLevel());

		mPlayer = player;
		mEnemyTrainer = enemyTrainer;
		
		updatePlayerAnature(playerCurr);
		updateMoves(playerCurr);
		updateSwitch(player.getAnatures(), player.getSelectedIndex());
		updateBagMenu();
		
		mDialogueTxt.set(enemyTrainer.getName() + " has started a battle with " + player.getName() + "!");

		mFightManager = new FightManager(player.getAnatures(), enemyTrainer.getAnatures(), player.getName(), enemyTrainer.getName());
	}
	
	private void updatePlayerAnature(Anature playerCurr)
	{
		mPlayerName.set(playerCurr.getName());
		
		mPlayerHp.set(playerCurr.getCurrHp());
		mPlayerHpTotal.set(playerCurr.getTotalHp());
		
		mPlayerXp.set(playerCurr.getCurrentXp());
		mPlayerXpTotal.set(100); // TODO change to a standard
		
		mPlayerLvl.set(playerCurr.getLevel());
		
		updateMoves(playerCurr);
	}
	
	private void updateMoves(Anature playerCurr)
	{
		MoveSet moves = playerCurr.getMoves(); // TODO Make move btn color change based on move type
		Move move1 = moves.getMove(0);
		Move move2 = moves.getMove(1);
		Move move3 = moves.getMove(2);
		Move move4 = moves.getMove(3);
		
		updateMove(move1, moves.getMovePoints(0), mShowMoveOne, mAttackNameOneTxt, mAttackMpOneTxt);
		updateMove(move2, moves.getMovePoints(1), mShowMoveTwo, mAttackNameTwoTxt, mAttackMpTwoTxt);
		updateMove(move3, moves.getMovePoints(2), mShowMoveThree, mAttackNameThreeTxt, mAttackMpThreeTxt);
		updateMove(move4, moves.getMovePoints(3), mShowMoveFour, mAttackNameFourTxt, mAttackMpFourTxt);
	}
	
	private void updateMove(Move moveToCheck, int currMp, BooleanProperty showMove, StringProperty nameTxt, StringProperty mpTxt)
	{
		if(moveToCheck != null)
		{
			showMove.set(true);
			nameTxt.set(moveToCheck.getName());
			mpTxt.set(currMp + "/" + moveToCheck.getTotalMovePoints());
		}
		
		else
		{
			showMove.set(false);
		}
	}
	
	private void updateSwitch(ArrayList<Anature> party, int selectedIndex) 
	{
		Image anatureImg = new Image(getClass().getResource("/resources/images/anatures/Null_Front.png").toExternalForm());
		boolean isSelected = false;
		
		switch(party.size())
		{
			case 6:
				isSelected = false;
				
				if(selectedIndex == 5)
					isSelected = true;
					
				updateSwitchSlot(party.get(5), anatureImg, mSwitchSlotSix, mSlotSix, isSelected);
				
			case 5:
				isSelected = false;
				
				if(selectedIndex == 4)
					isSelected = true;
					
				updateSwitchSlot(party.get(4), anatureImg, mSwitchSlotFive, mSlotFive, isSelected);
				
			case 4:
				isSelected = false;
				
				if(selectedIndex == 3)
					isSelected = true;
					
				updateSwitchSlot(party.get(3), anatureImg, mSwitchSlotFour, mSlotFour, isSelected);
				
			case 3:
				isSelected = false;
				
				if(selectedIndex == 2)
					isSelected = true;
					
				updateSwitchSlot(party.get(2), anatureImg, mSwitchSlotThree, mSlotThree, isSelected);
				
			case 2:
				isSelected = false;
				
				if(selectedIndex == 1)
					isSelected = true;
					
				updateSwitchSlot(party.get(1), anatureImg, mSwitchSlotTwo, mSlotTwo, isSelected);
				
			case 1:
				isSelected = false;
				
				if(selectedIndex == 0)
					isSelected = true;
					
				updateSwitchSlot(party.get(0), anatureImg, mSwitchSlotOne, mSlotOne, isSelected);
		}
		
		updateSwitchSelected(mPlayer.getSelectedIndex());
	}
	
	private void updateSwitchSelected(int selectedIndex)
	{
		mSlotOne.setIsSelected(false);
		mSlotTwo.setIsSelected(false);
		mSlotThree.setIsSelected(false);
		mSlotFour.setIsSelected(false);
		mSlotFive.setIsSelected(false);
		mSlotSix.setIsSelected(false);
		
		switch(selectedIndex)
		{
			case 0:
				mSlotOne.setIsSelected(true);
				break;

			case 1:
				mSlotTwo.setIsSelected(true);
				break;

			case 2:
				mSlotThree.setIsSelected(true);
				break;

			case 3:
				mSlotFour.setIsSelected(true);
				break;

			case 4:
				mSlotFive.setIsSelected(true);
				break;

			case 5:
				mSlotSix.setIsSelected(true);
				break;	
		}
		
		mSwitchIndexSelected = selectedIndex;
		
		ArrayList<Anature> party = mPlayer.getAnatures();
		Anature selected = party.get(selectedIndex);
		
		mSwitchSelectedCatalogNum.setText(String.format("%03d", selected.getIndexNum()));
		mSwitchSelectedName.setText(selected.getName());
		mSwitchSelectedOwner.setText(selected.getOwner());
		mSwitchSelectedCurrXp.setText(selected.getCurrentXp() + "");
		mSwitchSelectedNextXp.setText((100 - selected.getCurrentXp()) + "");
		
		mSwitchSelectedHp.setText(selected.getTotalHp() + "");
		mSwitchSelectedAtk.setText(selected.getAttack() + "");
		mSwitchSelectedSpAtk.setText(selected.getSpecialAttack() + "");
		mSwitchSelectedDef.setText(selected.getDefense() + "");
		mSwitchSelectedSpDef.setText(selected.getSpecialDefense() + "");
		
		mSwitchSelectedAbilityName.setText(selected.getAbility().getAbilityName());
		mSwitchSelectedAbilityDesc.setText(selected.getAbility().getAbilityDescription());
		
		// TODO Add different type imgs
	}
	
	private void updateSwitchSlot(Anature curr, Image anatureImg, BooleanProperty visibleProp, AnatureSlot slot, boolean isSelected)
	{
		visibleProp.set(true);
		slot.updateSlot(isSelected, anatureImg, curr.getGender(), curr.getName(), "Lvl " + curr.getLevel(),
				curr.getCurrHp() + "/" + curr.getTotalHp(), mShowSwitch.get(), visibleProp.get(), curr.getCurrHp());
	}
	
	private void updateBagMenu()
	{
		ObservableList<String> items = mItemList.getItems();
		items.clear();
		
		Backpack backpack = mPlayer.getBackpack();
		
		int potionCount = backpack.getPotionCount();
		int greatPotionCount = backpack.getGreatPotionCount();
		int ultraPotionCount = backpack.getUltraPotionCount();
		int masterPotionCount = backpack.getMasterPotionCount();
		
		if(potionCount > 0)
			items.add("Potions " + potionCount + "x");
		
		if(greatPotionCount > 0)
			items.add("Great Potions " + greatPotionCount + "x");
		
		if(ultraPotionCount > 0)
			items.add("Ultra Potions " + ultraPotionCount + "x");
		
		if(masterPotionCount > 0)
			items.add("Master Potions " + masterPotionCount + "x");
		
		mItemList.getSelectionModel().select(0);
		onItemSelect();
		
		mItemList.setItems(items);
	}
	
	private void onItemSelect()
	{
		String selectedItem = mItemList.getSelectionModel().getSelectedItem();
		
		if(selectedItem == null)
			return;
		
		if(selectedItem.startsWith("Potions"))
		{
			mSelectedItemImg.setImage(mItemPotion);
			mSelectedItemTxt.set("Potion");
		}
		
		else if(selectedItem.startsWith("Great"))
		{
			mSelectedItemImg.setImage(mItemGreatPotion);
			mSelectedItemTxt.set("Great Potion");
		}
		
		else if(selectedItem.startsWith("Ultra"))
		{
			mSelectedItemImg.setImage(mItemUltraPotion);
			mSelectedItemTxt.set("Ultra Potion");
		}
		
		else if(selectedItem.startsWith("Master"))
		{
			mSelectedItemImg.setImage(mItemMasterPotion);
			mSelectedItemTxt.set("Master Potion");
		}
	}
	
	@FXML
	public void onPageChange()
	{
		mSwitchPageNum++;
		
		if(mSwitchPageNum == 2)
		{
			mSwitchSelection.setImage(mSwitchPageTwoImg);
			mShowSwitchPageOne.set(false);
		}
		
		else
		{
			mSwitchSelection.setImage(mSwitchPageOneImg);
			mShowSwitchPageOne.set(true);
			mSwitchPageNum = 1;
		}
		
		mSwitchPageTxt.setText("Page " + mSwitchPageNum);
	}
	
	private void onItemTab(boolean isPotion)
	{
		if(isPotion)
		{
			mItemPotionsTab.setImage(mItemTabSelected);
			mItemAnaCubeTab.setImage(mItemTabDeselected);
		}
		
		else
		{
			mItemPotionsTab.setImage(mItemTabDeselected);
			mItemAnaCubeTab.setImage(mItemTabSelected);
		}
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
		
		if(choice == BattleChoice.Switch || choice == BattleChoice.Item)
		{
			whoGoesFirst = 0;
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
		
		onBackBtn();
		mShowBtns.set(false);
		mClickQueue.dequeue().run();
	}
	
	private void playerTurn(BattleChoice choice)
	{
		switch(choice)
		{
			case Attack_1:
				mClickQueue.enqueue(() -> healthDrainMove(mFightManager.attackEnemy(0), mEnemyHp));
				break;

			case Attack_2:
				mClickQueue.enqueue(() -> healthDrainMove(mFightManager.attackEnemy(1), mEnemyHp));
				break;

			case Attack_3:
				mClickQueue.enqueue(() -> healthDrainMove(mFightManager.attackEnemy(2), mEnemyHp));
				break;

			case Attack_4:
				mClickQueue.enqueue(() -> healthDrainMove(mFightManager.attackEnemy(3), mEnemyHp));
				break;
				
			case Item:
				mClickQueue.enqueue(new Runnable()
				{
					@Override
					public void run()
					{
						Item selectedItem = ItemPool.getItems(mItemList.getSelectionModel().getSelectedItem());
						
						ItemResult result = mFightManager.itemUse(true, 0, selectedItem); // TODO Change it so u can use items on other anatures
						healthGain(result, mPlayerHp);
						
						mPlayer.getBackpack().removeItem(selectedItem.getItemId());
						updateBagMenu();
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
						mCanClick.set(true);
					}
				});
				break;
				
			case Switch:
				mClickQueue.enqueue(new Runnable()
				{
					@Override
					public void run()
					{
						mFightManager.setPlayerSelectedIndex(mSwitchIndexSelected);
						Anature oldAnature = mPlayer.getAnatures().get(mPlayer.getSelectedIndex());
						mPlayer.setSelectedIndex(mSwitchIndexSelected);
						Anature newAnature = mPlayer.getAnatures().get(mPlayer.getSelectedIndex());
						
						OpacityAnimation fadeOld = new OpacityAnimation(mAnatureBack, Duration.millis(400), false);
						fadeOld.setOnFinished(new EventHandler<ActionEvent>()
						{
							@Override
							public void handle(ActionEvent event)
							{
								updatePlayerAnature(newAnature);
								
								try
								{
									Thread.sleep(500);
								}
								
								catch(InterruptedException e)
								{
									LoggerController.logEvent(LoggingTypes.Default, e.getMessage());
								}
								
								OpacityAnimation fadeInNew = new OpacityAnimation(mAnatureBack, Duration.millis(400), true);
								fadeInNew.setOnFinished(actionEvent -> mCanClick.set(true));
								fadeInNew.play();
							}
						});
						
						fadeOld.play();
						
						mDialogueTxt.set("Come on back " + oldAnature.getName() + ".");
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
					healthDrainMove(mFightManager.attackPlayer(0), mPlayerHp); // TODO Change to above when Demo is Done! 
				}
			});
		}
	}
	
	private void healthDrainMove(MoveResult result, DoubleProperty toChange)
	{
		mDialogueTxt.set(result.getDialogue());
		ProgressBarDecrease decrease = new ProgressBarDecrease(toChange, Duration.millis(3000), result.getDamageDone());
		decrease.setOnFinished(event -> mCanClick.set(true));
		decrease.play();
		
		if(result.isPlayer())
		{
			StringProperty mpTxt = null;
			switch(result.getMoveIndex())
			{
				case 0:
					mpTxt = mAttackMpOneTxt;
					break;
					
				case 1:
					mpTxt = mAttackMpTwoTxt;
					break;
					
				case 2:
					mpTxt = mAttackMpThreeTxt;
					break;
					
				case 3:
					mpTxt = mAttackMpFourTxt;
					break;
					
				default:
					return;
			}
			
			mpTxt.set(result.getMpTxt());
		}
	}
	
	private void healthGain(ItemResult result, DoubleProperty toChange)
	{
		mDialogueTxt.set(result.getDialogue());
		ProgressBarIncrease increase = new ProgressBarIncrease(toChange, Duration.millis(2000), result.getHpGained());
		increase.setOnFinished(event -> mCanClick.set(true));
		increase.play();
	}
	
	private void onSwitchBtn()
	{
		updateSwitch(mPlayer.getAnatures(), mSwitchIndexSelected);
		mShowSwitch.set(true);
		mShowBtns.set(false);
		mShowPlayerBars.set(false);
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
		mSwitchIndexSelected = mPlayer.getSelectedIndex();
		mShowPlayerBars.set(true);
		mShowSwitch.set(false);
		mShowItemSelection.set(false);
		mShowMoveSelection.set(false);
		mShowMoveSe.set(false);
		mShowBtns.set(true);
	}
	
	private ObjectProperty<Font> getFontProperty(int toDivideBy, Scene scene)
	{
		Font font = Font.loadFont(getClass().getResourceAsStream("/resources/font/pixelFJ8pt1__.TTF"), toDivideBy);
		ObjectProperty<Font> fontProperty = new SimpleObjectProperty<Font>(font);
		
		scene.widthProperty().addListener((observableValue, oldWidth, newWidth) -> 
		fontProperty.set(Font.loadFont(getClass().getResourceAsStream("/resources/font/pixelFJ8pt1__.TTF"), getFontSize(scene) / toDivideBy)));
		
		scene.heightProperty().addListener((observableValue, oldHeight, newHeight) -> 
		fontProperty.set(Font.loadFont(getClass().getResourceAsStream("/resources/font/pixelFJ8pt1__.TTF"), getFontSize(scene) / toDivideBy)));
		
		return fontProperty;
	}
}