//Made By Tzu-Hao Huang for a personal project.
package application;
import java.io.File;
import java.net.MalformedURLException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCombination;
import javafx.scene.paint.Color;


public class Main extends Application {
	Player player;
	FileChooser fileChooser;
	public void start(final Stage primaryStage) {
		
		MenuItem open = new MenuItem("Open");
		MenuItem on = new MenuItem("On");
		MenuItem off = new MenuItem("Off");
		Menu fileBar = new Menu("File");
		Menu fullScreenBar = new Menu("Full Screen");
		
		MenuBar menu = new MenuBar();
		
		fileBar.getItems().add(open);
		fullScreenBar.getItems().add(on);
		fullScreenBar.getItems().add(off);
		menu.getMenus().add(fileBar);
		menu.getMenus().add(fullScreenBar);
		
		fileChooser = new FileChooser();
				
		open.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e){
				player.player.pause();
				File file = fileChooser.showOpenDialog(primaryStage);
				if(file != null) {
					try {						
						player = new Player(file.toURI().toURL().toExternalForm());
						player.setTop(menu);
						primaryStage.setScene(new Scene(player, 640, 430, Color.BLACK ));
						primaryStage.show();
					} catch (MalformedURLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		
		on.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e) {
				player.setTop(menu);
				primaryStage.setFullScreenExitHint("");
				primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
				primaryStage.setFullScreen(true);
				player.fullScreen();	
			}
		});
		
		off.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e) {
				player.setTop(menu);
				primaryStage.setFullScreen(false);
				player.fullScreenOff();	
			}
		});		
		
		player = new Player("file:///Users/ryu1031/Downloads/courseintromp4.mp4");
		player.setTop(menu);
		primaryStage.setScene(new Scene(player, 640, 430, Color.BLACK));
		primaryStage.show();
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
