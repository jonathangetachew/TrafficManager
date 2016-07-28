package www.jdtech.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConfirmWindow {
	
	private static boolean m_answer;

	public static boolean display(String title, String message) {
		Stage window = new Stage();
		///> set the title bar logo icon
		window.getIcons().add(new Image("file:resources/images/logo3.png"));
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(250);
		window.setResizable(false);
		
		Label label = new Label();
		label.setText(message);
				
		///> Buttons
		// Yes Button
		Button yesButton = new Button("Yes");
		yesButton.getStyleClass().add("button-login");
		yesButton.setOnAction(e -> {
			m_answer = true;
			window.close();
		});
		
		// No Button
		Button noButton = new Button("No");
		noButton.getStyleClass().add("button-login");
		noButton.setOnAction(e -> {
			m_answer = false;
			window.close();
		});
		
		// Button Layout
		HBox buttonLayout = new HBox(20);
		buttonLayout.setPadding(new Insets(0, 15, 0, 0));
		buttonLayout.setAlignment(Pos.BASELINE_RIGHT);
		
		// Adding buttons to layout
		buttonLayout.getChildren().addAll(yesButton, noButton);
		
		VBox mainLayout = new VBox(10);
		mainLayout.setPadding(new Insets(15, 15, 15, 15));
		mainLayout.getChildren().addAll(label, buttonLayout);
		mainLayout.setAlignment(Pos.CENTER);
		
		///> Setting up the scene
		Scene scene = new Scene(mainLayout);
		scene.getStylesheets().add("file:///" + StylesheetMananger.STYLE_CSS.getAbsolutePath().replace("\\", "/"));
		
		window.setScene(scene);
		window.showAndWait();
		
		return m_answer;
	}
	
}
