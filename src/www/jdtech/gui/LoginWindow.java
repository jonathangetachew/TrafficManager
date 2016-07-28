package www.jdtech.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginWindow {
	
	private static final float WINDOW_WIDTH = 325f;
	private static final float WINDOW_HEIGHT = 420f;
	
	public static String WEBSITE = "http://www.originteck.wordpress.com/";
	
	private static Stage m_window = new Stage();
	private static String m_username;
	private static String m_password;
	
	public static void create() {
		
		///> set the title bar logo icon
		m_window.getIcons().add(new Image("file:resources/images/logo3.png"));
		m_window.setTitle("Log In");
		m_window.setResizable(false);
		m_window.centerOnScreen();
		
		int maxTextFieldWidth = 250;
		
		///> Main Layout
		BorderPane mainLayout = new BorderPane();
		
		///> Welcome Label
		HBox topLayout = new HBox(20);
		topLayout.setAlignment(Pos.BASELINE_CENTER);
		topLayout.setPadding(new Insets(30, 0, 0, 0));
		Label welcomeLabel = new Label("Welcome");
		welcomeLabel.setId("welcomeLabel");
		topLayout.getChildren().add(welcomeLabel);
		
		///> Form Layout
		VBox formLayout = new VBox(20);
		formLayout.setPadding(new Insets(10, 10, 10, 10));
		formLayout.setAlignment(Pos.BASELINE_CENTER);
		
		///> Input Fields
		TextField usernameField = new TextField();
		usernameField.setMaxWidth(maxTextFieldWidth);
		usernameField.setPromptText("Username");
		
		PasswordField passwordField = new PasswordField();
		passwordField.setMaxWidth(maxTextFieldWidth);
		passwordField.setPromptText("Password");
		
		///> Incorrect user name or password notifier label
		Label errorLabel = new Label("* Incorrect Username or Password entered!");
		errorLabel.setId("errorLabel");
		errorLabel.setVisible(false);
		
		///> Buttons
		HBox buttonLayout = new HBox();
		buttonLayout.setAlignment(Pos.BASELINE_RIGHT);
		buttonLayout.setPadding(new Insets(-15, 20, 0, 0));
		
		Button loginButton = new Button("Log In");
		loginButton.getStyleClass().add("button-login");
		// Make the button usable by a keyboard
		loginButton.defaultButtonProperty().bind(loginButton.focusedProperty());
		loginButton.setOnAction(e -> {
			String username = "1";
			String password = "1234";
			
			String username2 = "2";
			String password2 = "5678";
			
			m_username = usernameField.getText();
			m_password = passwordField.getText();
			
			// Check for username and password 		
			
			if ( m_username.contentEquals(username) && m_password.contentEquals(password) ){
				
				m_window.close();
				DatabaseWindow.create("Admin", "EYOB MESFIN");
				
			} else {
				
				if( m_username.contentEquals(username2) && m_password.contentEquals(password2) ) {
					
					m_window.close();
					DatabaseWindow.create("Admin", "MESERET BEKELE");
					
				} else {
					
					if( m_username.contentEquals("admin") && m_password.contentEquals("admin") ) {
						
						m_window.close();
						DatabaseWindow.create("Admin", "Jonathan Getachew");
						
					} else {
						usernameField.setStyle("-fx-border-color: #AB4642");
						passwordField.setStyle("-fx-border-color: #AB4642");
						errorLabel.setVisible(true);
					}
				}
								
			}
		});
		buttonLayout.getChildren().add(loginButton);
		
		///> Layout for the config button
		HBox buttonLayout2 = new HBox();
		buttonLayout2.setAlignment(Pos.BASELINE_RIGHT);
		buttonLayout2.setPadding(new Insets(0, 20, 0, 0));
		
		
		///> Button for configuring database settings
		Button configButton = new Button("Configure");
		configButton.getStyleClass().add("button-config");
		// Make the button usable by a keyboard
		configButton.defaultButtonProperty().bind(loginButton.focusedProperty());	
		
		configButton.setOnAction(e -> {
			ConfigWindow.create();
		});

		buttonLayout2.getChildren().add(configButton);
		
		///> Adding content to layout2
		formLayout.getChildren().addAll(usernameField, passwordField, errorLabel, buttonLayout, buttonLayout2);
		
		///> Website Link bar
		Label websiteLabel = new Label(WEBSITE);
		websiteLabel.getStyleClass().add("link");
		
		HBox websiteBar = new HBox();
		websiteBar.setAlignment(Pos.BASELINE_RIGHT);
		websiteBar.getChildren().add(websiteLabel);
				
		///> Adding content to main layout
		mainLayout.setTop(topLayout);
		mainLayout.setCenter(formLayout);
		mainLayout.setBottom(websiteBar);
	
		
		///> Setup our scene
		Scene scene = new Scene(mainLayout, WINDOW_WIDTH, WINDOW_HEIGHT);
		
		///> Importing the stylesheet file
		scene.getStylesheets().add("file:///" + StylesheetMananger.STYLE_CSS.getAbsolutePath().replace("\\", "/"));
		
		m_window.setScene(scene);
		m_window.show();
		
	}
		
}
