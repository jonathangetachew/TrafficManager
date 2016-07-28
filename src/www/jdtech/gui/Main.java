package www.jdtech.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class Main extends Application {
	
	Stage window;
	Scene scene;
	Button button;
	ListView<String> listView;
	
	public static void main(String[] args) {
		
		 launch(args);

	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {		
		///> Create the login window
		LoginWindow.create();
				
	}	
		
}		
