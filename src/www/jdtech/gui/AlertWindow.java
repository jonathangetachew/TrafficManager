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

public class AlertWindow {

	public static void display(String title, String message) {
		Stage window = new Stage();
		
		///> set the title bar logo icon
		window.getIcons().add(new Image("file:resources/images/logo3.png"));
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(250);
		window.setResizable(false);
		
		Label label = new Label();
		label.setText(message);
		
		Button closeButton = new Button("Close");
		closeButton.setOnAction(e -> window.close());
		closeButton.getStyleClass().add("button-login");
		
		HBox buttonLayout = new HBox(20);
		buttonLayout.setAlignment(Pos.BASELINE_RIGHT);
		buttonLayout.setPadding(new Insets(10, 10, 10, 10));
		buttonLayout.getChildren().add(closeButton);
		
		VBox layout = new VBox(20);
		layout.setPadding(new Insets(10, 10, 0, 0));
		layout.getChildren().addAll(label, buttonLayout);
		layout.setAlignment(Pos.CENTER);
		
		///> Setup the scene
		Scene scene = new Scene(layout);
		scene.getStylesheets().add("file:///" + StylesheetMananger.STYLE_CSS.getAbsolutePath().replace("\\", "/"));
		
		window.setScene(scene);
		window.showAndWait();
	}
	
}
