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
import javafx.scene.paint.Color;


public class Main extends Application {
	Player player;
	FileChooser fileChooser;
	public void start(final Stage primaryStage) {
		
		MenuItem open = new MenuItem("Open");
		Menu fileBar = new Menu("File");
		MenuBar menu = new MenuBar();
		
		fileBar.getItems().add(open);
		menu.getMenus().add(fileBar);
		
		fileChooser = new FileChooser();
				
		open.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e){
				player.player.pause();
				File file = fileChooser.showOpenDialog(primaryStage);
				if(file != null) {
					try {						
						player = new Player(file.toURI().toURL().toExternalForm(), 640, 360);
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
		
		player = new Player("file:///Users/ryu1031/Downloads/courseintromp4.mp4", 640, 360);
		player.setTop(menu);
		primaryStage.setScene(new Scene(player, 640, 430, Color.BLACK));
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
