package resources.logger;

import java.time.LocalDateTime;

import javafx.application.Application;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class GameLogger extends Application
{
	private static Stage stage;
	private static TextArea textArea;
	private static HBox hbox;
	private static GridPane grid;

	@Override
	public void start(Stage primaryStage) throws Exception
	{
		// Styles
		Insets gridPanePadding = new Insets(10.0, 10.0, 10.0, 10.0);
		Insets defaultComponetMargin = new Insets(5.0, 5.0, 5.0, 5.0);
		Insets defaultButtonMargin = new Insets(3.0, 5.0, 3.0, 5.0);

		// Primary Stage
		stage = primaryStage;
		stage.setTitle("Development Logger Tool");
		stage.getIcons().add(new Image("/resources/images/gamelogger/devloggericon.png"));

		// Grid Pane
		grid = new GridPane();
		grid.setPadding(gridPanePadding);

		// Gird Column and Row Constraints
		ColumnConstraints columns = new ColumnConstraints();
		columns.setPercentWidth(100);
		RowConstraints row1 = new RowConstraints();
		row1.setPercentHeight(50);
		RowConstraints row2 = new RowConstraints();
		row2.setMaxHeight(grid.getHeight() - (10 + 10 + 5 + 5 + 30));
		row2.setValignment(VPos.CENTER);
		grid.getColumnConstraints().add(columns);
		grid.getRowConstraints().add(0, row1);
		grid.getRowConstraints().add(1, row1);
		
		// assign to center

		// Add HBox
		hbox = new HBox();
		grid.addRow(0, hbox);

		// Add TexytArea
		textArea = new TextArea();
		grid.addRow(1, textArea);
		GridPane.setMargin(textArea, defaultComponetMargin);

		// Toggle button 1
		ToggleButton clickingTool = new ToggleButton("Clicking Tool");
		clickingTool.setId("clicking_tool");
		clickingTool.setSelected(true);
		clickingTool.setBorder(null);
		hbox.getChildren().add(clickingTool);
		HBox.setMargin(clickingTool, defaultButtonMargin);

		// Toggle Button 2
		ToggleButton standardLogging = new ToggleButton("Standard Logging");
		standardLogging.setId("standard_logging");
		standardLogging.setSelected(true);
		standardLogging.setBorder(null);
		hbox.getChildren().add(standardLogging);
		HBox.setMargin(standardLogging, defaultButtonMargin);

		// Set lines visible
		// grid.setGridLinesVisible(true);

		// Create Scene and show
		Scene scene = new Scene(grid, 800, 600);
		stage.setScene(scene);
		stage.show();

		textArea.requestFocus();
		textArea.setEditable(false);
		logEvent("This is a test.");
	}

	public static void main(String[] args)
	{
		launch(args);
	}

	public static void logEvent(String event)
	{
		textArea.selectEnd();
		textArea.insertText(textArea.getCaretPosition(),
				LocalDateTime.now().toString().replace("T", " ").substring(0, 19) + ": " + event);
	}

}