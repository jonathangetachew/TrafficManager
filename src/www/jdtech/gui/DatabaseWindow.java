package www.jdtech.gui;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import www.jdtech.databaseManager.Configuration;
import www.jdtech.databaseManager.DatabaseManager;

public class DatabaseWindow {
	
	private static Stage m_window = new Stage();
	
	private static final float WINDOW_WIDTH = 900f;
	private static final float WINDOW_HEIGHT = 550f;
	
	private static ListView<String> m_driverList;
	private static int m_driverID = 0;
	private static VBox profileBar = new VBox(20);
	
	public static void create(String userType, String username) {
		m_window.setTitle("Howdy, " + username);
		///> set the title bar logo icon
		m_window.getIcons().add(new Image("file:resources/images/logo3.png"));
		///> Disable the close button and handle the operation myself
		m_window.setOnCloseRequest(e -> {
			e.consume();
			exitRequest();		
		});
		m_window.setResizable(false);
		
		
		// Main layout
		BorderPane mainLayout = new BorderPane();
		
		///> Menu bar/ Search Bar/ Logout

		// File Menu
		Menu fileMenu = new Menu("_File");
		
		// Menu Items
		MenuItem logoutItem = new MenuItem("Logout");
		logoutItem.setOnAction(e -> logoutRequest());
		fileMenu.getItems().add(logoutItem);
		fileMenu.getItems().add(new SeparatorMenuItem());	// Separator
		MenuItem exitItem = new MenuItem("Exit");
		exitItem.setOnAction(e -> exitRequest());
		fileMenu.getItems().add(exitItem);
		
		// Help Menu
		Menu helpMenu = new Menu("_Help");
		
		// Menu Items
		MenuItem aboutItem = new MenuItem("About");
//		aboutItem.setDisable(true);
		aboutItem.setOnAction(e -> AboutWindow.create());
		helpMenu.getItems().add(aboutItem);
		
		// Menu bar
		MenuBar mainMenu = new MenuBar();
		mainMenu.getMenus().addAll(fileMenu,helpMenu);
		
		///> Side bar	
		
		// Search Bar container
		HBox searchBar = new HBox(-30);
		searchBar.setAlignment(Pos.BASELINE_LEFT);
		
		// Search Field
		TextField searchField = new TextField();
		searchField.setPromptText("Search");
		searchField.setMinWidth(250);
		
		// Search Button
		Button searchButton = new Button("   ");
		searchButton.getStyleClass().add("button-search");
		searchButton.setOnAction(e -> {
			
			if(!searchField.getText().contentEquals("")) {
				ArrayList<String> searchResult = new ArrayList<>();
				searchResult = DatabaseManager.searchByName(searchField.getText());
				
				if(searchResult == null) {
					profileBar.setVisible(false);
					profileBar.getChildren().clear();
					
					VBox outerLayout = new VBox(20);
					outerLayout.setPadding(new Insets(20, 20, 20, 20));
					outerLayout.setStyle("-fx-background-color: #FFFFFF");
					
					Label noDrivers = new Label();
					noDrivers.setText("No drivers were found");
					
					outerLayout.getChildren().add(noDrivers);
					
					profileBar.getChildren().addAll(outerLayout);
		
					profileBar.setVisible(true);
				} else {
					profileBar.setVisible(false);
					profileBar.getChildren().clear();
					
					// Get the drivers ID
					m_driverID = Integer.parseInt(searchResult.get(7));
					
					// Labels for the driver info view
					Label fullNameLabel = new Label("Full Name: ");
					Label genderLabel = new Label("Gender: ");
					Label birthDateLabel = new Label("Birth Date: ");
					Label cityLabel = new Label("City: ");
					Label subCityLabel = new Label("Sub City: ");
					Label kebeleLabel = new Label("Kebele: ");
					Label pointLabel = new Label("Point: ");				
					
					// Driver info extracted from the database ArrayList
					Label fullName = new Label(searchResult.get(0).toUpperCase());
					Label gender = new Label(searchResult.get(1).toUpperCase());
					Label birthDate = new Label(searchResult.get(2));
					Label city = new Label(searchResult.get(3).toUpperCase());
					Label subCity = new Label(searchResult.get(4).toUpperCase());
					Label kebele = new Label(searchResult.get(5));
					Label point = new Label(searchResult.get(6));
							
					// Layout for the driver details view
					HBox outerLayout = new HBox(20);
					outerLayout.setPadding(new Insets(50, 50, 50, 50));
					outerLayout.setStyle("-fx-background-color: #FFFFFF");
					
					
					VBox driverInfoLayout = new VBox(20);
					driverInfoLayout.setPadding(new Insets(5, 5, 5, 5));
					
					HBox layout = new HBox(20);
					layout.getChildren().addAll(fullNameLabel, fullName);
					driverInfoLayout.getChildren().add(layout);
					
					layout = new HBox(20);
					layout.getChildren().addAll(genderLabel, gender);
					driverInfoLayout.getChildren().add(layout);
					
					layout = new HBox(20);
					layout.getChildren().addAll(birthDateLabel, birthDate);
					driverInfoLayout.getChildren().add(layout);
					
					layout = new HBox(20);
					layout.getChildren().addAll(cityLabel, city);
					driverInfoLayout.getChildren().add(layout);
					
					layout = new HBox(20);
					layout.getChildren().addAll(subCityLabel, subCity);
					driverInfoLayout.getChildren().add(layout);
					
					layout = new HBox(20);
					layout.getChildren().addAll(kebeleLabel, kebele);
					driverInfoLayout.getChildren().add(layout);
					
					layout = new HBox(20);
					layout.getChildren().addAll(pointLabel, point);
					driverInfoLayout.getChildren().add(layout);
					
					// Display image
					StackPane sp = new StackPane();
					Image img = new Image("file:///" + StylesheetMananger.PROFILE_PIC.getAbsolutePath());
					ImageView imgView = new ImageView(img);
					sp.getChildren().add(imgView);
					
					outerLayout.getChildren().addAll(driverInfoLayout, sp);
					outerLayout.setAlignment(Pos.TOP_CENTER);
					
					///> Punish button area
					HBox punishBar = new HBox(20);
					punishBar.setPadding(new Insets(-5,20,5,5));
					punishBar.setAlignment(Pos.TOP_RIGHT);
					
					///> Input Fields
					TextField punishmentAmount = new TextField();
					punishmentAmount.setMaxWidth(250);
					punishmentAmount.setPromptText("Punishment Amount");
					
					Button punish = new Button("Punish");
					punish.getStyleClass().add("button-login");
					punish.setOnAction(e1 -> {
						
						if ( !punishmentAmount.getText().contentEquals("") ) {
						
							boolean answer = ConfirmWindow.display("Punish", "Are you sure you want to punish by " + punishmentAmount.getText() + " points?");
							
							if ( answer ) {
								int punishAmount = Integer.parseInt(punishmentAmount.getText());
								DatabaseManager.updatePoints(punishAmount, m_driverID);
							} else {
								AlertWindow.display("Cancel", "Punishment Canceled");
							}
							
						} else {
							AlertWindow.display("Error", "Enter amount first!");
						}
					});
					
					punishBar.getChildren().addAll(punishmentAmount, punish);
					
					profileBar.getChildren().addAll(outerLayout, punishBar);
		
					profileBar.setVisible(true);
					
				}
				
			}else {}
			
		});

		searchBar.getChildren().addAll(searchField, searchButton);
		
		ObservableList<String> names = FXCollections.observableArrayList(DatabaseManager.retrieveDrivers());
		
		m_driverList = new ListView<>();
		m_driverList.getItems().addAll(names);
		m_driverList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		
		// view profile button
		Button viewProfile = new Button("View Profile");
		viewProfile.getStyleClass().add("button-login");
		viewProfile.setOnAction(e -> {

			m_driverID = getDriverID();
			
			if(m_driverID != 0) {
				
				profileBar.setVisible(false);
				profileBar.getChildren().clear();
				
				// Array list for the database data
				ArrayList<String> string = new ArrayList<>();
				string = DatabaseManager.searchByID(m_driverID);
				
				// Labels for the driver info view
				Label fullNameLabel = new Label("Full Name: ");
				Label genderLabel = new Label("Gender: ");
				Label birthDateLabel = new Label("Birth Date: ");
				Label cityLabel = new Label("City: ");
				Label subCityLabel = new Label("Sub City: ");
				Label kebeleLabel = new Label("Kebele: ");
				Label pointLabel = new Label("Point: ");				
				
				// Driver info extracted from the database ArrayList
				Label fullName = new Label(string.get(0).toUpperCase());
				Label gender = new Label(string.get(1).toUpperCase());
				Label birthDate = new Label(string.get(2));
				Label city = new Label(string.get(3).toUpperCase());
				Label subCity = new Label(string.get(4).toUpperCase());
				Label kebele = new Label(string.get(5));
				Label point = new Label(string.get(6));
						
				// Layout for the driver details view
				HBox outerLayout = new HBox(20);
				outerLayout.setPadding(new Insets(50, 50, 50, 50));
				outerLayout.setStyle("-fx-background-color: #FFFFFF");
				
				
				VBox driverInfoLayout = new VBox(20);
				driverInfoLayout.setPadding(new Insets(5, 5, 5, 5));
				
				HBox layout = new HBox(20);
				layout.getChildren().addAll(fullNameLabel, fullName);
				driverInfoLayout.getChildren().add(layout);
				
				layout = new HBox(20);
				layout.getChildren().addAll(genderLabel, gender);
				driverInfoLayout.getChildren().add(layout);
				
				layout = new HBox(20);
				layout.getChildren().addAll(birthDateLabel, birthDate);
				driverInfoLayout.getChildren().add(layout);
				
				layout = new HBox(20);
				layout.getChildren().addAll(cityLabel, city);
				driverInfoLayout.getChildren().add(layout);
				
				layout = new HBox(20);
				layout.getChildren().addAll(subCityLabel, subCity);
				driverInfoLayout.getChildren().add(layout);
				
				layout = new HBox(20);
				layout.getChildren().addAll(kebeleLabel, kebele);
				driverInfoLayout.getChildren().add(layout);
				
				layout = new HBox(20);
				layout.getChildren().addAll(pointLabel, point);
				driverInfoLayout.getChildren().add(layout);
				
				// Display image
				StackPane sp = new StackPane();
				Image img = new Image("file:///" + StylesheetMananger.PROFILE_PIC.getAbsolutePath());
				ImageView imgView = new ImageView(img);
				sp.getChildren().add(imgView);
				
				outerLayout.getChildren().addAll(driverInfoLayout, sp);
				outerLayout.setAlignment(Pos.TOP_CENTER);
				
				///> Punish button area
				HBox punishBar = new HBox(20);
				punishBar.setPadding(new Insets(-5,20,5,5));
				punishBar.setAlignment(Pos.TOP_RIGHT);
				
				///> Input Fields
				TextField punishmentAmount = new TextField();
				punishmentAmount.setMaxWidth(250);
				punishmentAmount.setPromptText("Punishment Amount");
				
				Button punish = new Button("Punish");
				punish.getStyleClass().add("button-login");
				punish.setOnAction(e1 -> {
					
					if ( !punishmentAmount.getText().contentEquals("") ) {
					
						boolean answer = ConfirmWindow.display("Punish", "Are you sure you want to punish by " + punishmentAmount.getText() + " points?");
						
						if ( answer ) {
							int punishAmount = Integer.parseInt(punishmentAmount.getText());
							DatabaseManager.updatePoints(punishAmount, m_driverID);
						} else {
							AlertWindow.display("Cancel", "Punishment Canceled");
						}
						
					} else {
						AlertWindow.display("Error", "Enter amount first!");
					}
				});
				
				punishBar.getChildren().addAll(punishmentAmount, punish);
				
				profileBar.getChildren().addAll(outerLayout, punishBar);
	
				profileBar.setVisible(true);
				
			} else {
				
				AlertWindow.display("Alert", "Select a driver first");
				
			}
			
		});		
		
		// Sidebar layout
		VBox sideBar = new VBox(10);
		sideBar.setPadding(new Insets(20, 20, 20, 20));
		sideBar.getChildren().addAll(searchBar, m_driverList, viewProfile);
		
		///> Status Bar
		HBox statusBar = new HBox(10);
		statusBar.setPadding(new Insets(5, 5, 5, 20));
		
		Label address = new Label();
		address.setText("Connected to: " + Configuration.getServerAddress());
		Label port = new Label();
		port.setText("Port No: " + Configuration.getServerPort());
		Label user = new Label();
		user.setText("User: " + username);

		statusBar.getChildren().addAll(address, port, user);
		
		///> Adding content to the main container
		mainLayout.setTop(mainMenu);
		mainLayout.setLeft(sideBar);
		mainLayout.setBottom(statusBar);
		mainLayout.setCenter(profileBar);
		
		///> Setup the scene
		Scene scene = new Scene(mainLayout, WINDOW_WIDTH, WINDOW_HEIGHT);
		scene.getStylesheets().add("file:///" + StylesheetMananger.STYLE_CSS.getAbsolutePath().replace("\\", "/"));
		
		m_window.setScene(scene);
		m_window.show();
	}
	
	///> Method to get driver id 
	private static int getDriverID() {
		String user = "";
		int id = 1;
		ObservableList<String> selectedUser, allUsers;
		selectedUser = m_driverList.getSelectionModel().getSelectedItems();
		allUsers = m_driverList.getItems();
		
		user = selectedUser.get(0);
		
		if(!(user==null)) {
		
			for(String m: allUsers) {
				if(m.contentEquals(user)) {
					break;
				} else {
					id++;
				}
			}
			
			return id;
		
		} else {
			return 0;
		}
		
	}
	
	private static void logoutRequest() {
		boolean answer = ConfirmWindow.display("Log Out", "Are you sure you want to log out?");
		
		if ( answer ) {
			m_window.close();
			LoginWindow.create();
		} 
	}
	
	private static void exitRequest() {
		boolean answer = ConfirmWindow.display("Exit", "Are you sure you want to exit?");
		
		if ( answer ) {
			m_window.close();
		} 
	}
	
}
