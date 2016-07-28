package www.jdtech.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import www.jdtech.databaseManager.Configuration;

///> Class to configure the database settings
public class ConfigWindow {

	private static final float WINDOW_WIDTH = 325f;
	private static final float WINDOW_HEIGHT = 200f;
	
	private static Stage m_window = new Stage();
	private static Label m_label = new Label();
	private static Label m_label2 = new Label();	
	
	public static void create() {
		
		///> set the title bar logo icon
				m_window.getIcons().add(new Image("file:resources/images/logo3.png"));
				m_window.setTitle("Configure Database");
				m_window.initModality(Modality.APPLICATION_MODAL);
				m_window.setResizable(false);
				m_window.centerOnScreen();
				m_window.setOnCloseRequest(e -> {
					e.consume();
					cancelRequest();		
				});
				
				int maxTextFieldWidth = 250;
				
				///> Main Layout
				BorderPane mainLayout = new BorderPane();
								
				///> Form Layout
				VBox formLayout = new VBox(20);
				formLayout.setPadding(new Insets(10, 10, 10, 10));
				formLayout.setAlignment(Pos.BASELINE_CENTER);
				
				///> Layout to manage the labels and text fields in the main form layout
				HBox fieldLayout = new HBox(20);
				fieldLayout.setAlignment(Pos.BASELINE_RIGHT);
				
				// Address Label
				m_label.setText("Server Address");
				
				///> Input Fields
				// Address Field
				TextField addressField = new TextField();
				addressField.setMaxWidth(maxTextFieldWidth);
				addressField.setText(Configuration.getServerAddress());
				
				fieldLayout.getChildren().addAll(m_label, addressField);
				
				HBox fieldLayout2 = new HBox(20);
				fieldLayout2.setAlignment(Pos.BASELINE_RIGHT);
				
				m_label2.setText("Server Port");
				
				TextField portField = new TextField();
				portField.setMaxWidth(maxTextFieldWidth);
				portField.setText(Configuration.getServerPort()+"");
				
				fieldLayout2.getChildren().addAll(m_label2, portField);
				
				///> Buttons
				HBox buttonLayout = new HBox(20);
				buttonLayout.setAlignment(Pos.BASELINE_RIGHT);
				buttonLayout.setPadding(new Insets(0, 20, 0, 0));
				
				Button saveButton = new Button("Save");
				saveButton.getStyleClass().add("button-login");
				// Make the button usable by a keyboard
				saveButton.defaultButtonProperty().bind(saveButton.focusedProperty());
				saveButton.setOnAction(e -> {
					
					Configuration.setServerAdress(addressField.getText());
					Configuration.setServerPort(Integer.parseInt(portField.getText()));
					
					m_window.close();
				});
				
				///> Button for configuring database settings
				Button cancelButton = new Button("Cancel");
				cancelButton.getStyleClass().add("button-config");
				// Make the button usable by a keyboard
				cancelButton.defaultButtonProperty().bind(cancelButton.focusedProperty());				
				cancelButton.setOnAction(e -> cancelRequest());

				buttonLayout.getChildren().addAll(saveButton, cancelButton);
				
				
				///> Adding content to layout2
				formLayout.getChildren().addAll(fieldLayout, fieldLayout2, buttonLayout);
				
						
				///> Adding content to main layout
				mainLayout.setCenter(formLayout);			
				
				///> Setup our scene
				Scene scene = new Scene(mainLayout, WINDOW_WIDTH, WINDOW_HEIGHT);
				
				///> Importing the stylesheet file
				scene.getStylesheets().add("file:///" + StylesheetMananger.STYLE_CSS.getAbsolutePath().replace("\\", "/"));
				
				m_window.setScene(scene);
				m_window.showAndWait();
		
	}
	
	private static void cancelRequest() {
		boolean answer = ConfirmWindow.display("Cancel", "Are you sure you want to discard changes?");
		
		if ( answer ) {
			m_window.close();
		} 
	}
	
}
