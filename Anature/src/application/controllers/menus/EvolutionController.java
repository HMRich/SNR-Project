package application.controllers.menus;

import java.util.ArrayList;

import application.enums.Species;
import application.interfaces.IAnature;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;

public class EvolutionController
{
	@FXML private ImageView mBgImg;
	@FXML private ImageView mDialogueImg;
	@FXML private TextArea mDialogueTxt;
	
	public void updateBinds(Scene scene)
	{
		mBgImg.fitWidthProperty().bind(scene.widthProperty());
		mBgImg.fitHeightProperty().bind(scene.heightProperty());

		mDialogueImg.fitWidthProperty().bind(scene.widthProperty());
		mDialogueImg.fitHeightProperty().bind(scene.heightProperty());
		
		mDialogueTxt.layoutXProperty().bind(scene.widthProperty().divide(calculateValue(87, 1280)));
		mDialogueTxt.layoutYProperty().bind(scene.heightProperty().divide(calculateValue(554, 720)));
		mDialogueTxt.prefWidthProperty().bind(scene.widthProperty().divide(calculateValue(1095, 1280)));
		mDialogueTxt.prefHeightProperty().bind(scene.heightProperty().divide(calculateValue(141, 720)));
	}
	
	public void startEvolution(ArrayList<IAnature> party, IAnature toEvolve, Species toEvolveInto)
	{
		// TODO get xp overload and reapply it
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
