package www.jdtech.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AboutWindow {

	private static final float WINDOW_WIDTH = 550f;
	private static final float WINDOW_HEIGHT = 325f;
	
	private static String m_info = "\n     Version 1.0\n\n"
			+ "     Traffic Manager is a free and open source \n     traffic management application developed\n     by JDTech Technology Company.";
	private static String m_info2 = "Traffic Manager";
	
	private static Stage m_window = new Stage();
	
	
	public static void create() {
		
		///> set the title bar logo icon
		m_window.getIcons().add(new Image("file:resources/images/logo3.png"));
		m_window.initModality(Modality.APPLICATION_MODAL);
		m_window.setTitle("About");
		m_window.setResizable(false);
		m_window.centerOnScreen();
		
		///> Main Layout
		BorderPane mainLayout = new BorderPane();
		
		HBox topLayout = new HBox();
		topLayout.setPadding(new Insets(20, 20, 0, 20));
		topLayout.setAlignment(Pos.BASELINE_CENTER);
		
		Label top = new Label();
		top.setId("welcomeLabel");
		top.setText(m_info2);
		
		topLayout.getChildren().add(top);
		
		HBox centerLayout = new HBox(20);
		centerLayout.setPadding(new Insets(-20, 20, 20, 20));
		centerLayout.setAlignment(Pos.TOP_CENTER);
		
		Label about = new Label();
		about.setText(m_info);
		about.setPadding(new Insets(50, 0, 0, 0));
		
		// Display image
		StackPane sp = new StackPane();
		Image img = new Image("file:///" + StylesheetMananger.LOGO.getAbsolutePath());
		ImageView imgView = new ImageView(img);
		sp.getChildren().add(imgView);
		
		centerLayout.getChildren().addAll(sp, about);
		///> Adding content to main layout
		mainLayout.setTop(topLayout);
		mainLayout.setCenter(centerLayout);
	
		
		///> Setup our scene
		Scene scene = new Scene(mainLayout, WINDOW_WIDTH, WINDOW_HEIGHT);
		
		///> Importing the stylesheet file
		scene.getStylesheets().add("file:///" + StylesheetMananger.STYLE_CSS.getAbsolutePath().replace("\\", "/"));
		
		m_window.setScene(scene);
		m_window.showAndWait();
		
	}
}
	
