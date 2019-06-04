package UI;

import java.io.File;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main_v2 extends Application{
	
	MenuAlone sousMenu = new MenuAlone();
	MainMenu menu = new MainMenu();
	FadeTransition ft = new FadeTransition();
	MediaPlayer music;

	public static void main(String[] args) {
		Application.launch(args);

	}

	public void start(Stage stage) throws Exception {
		
		Media pick = new Media(new File("ressources/sounds/menuAlone.mp3").toURI().toString());
		music = new MediaPlayer(pick);
		
		music.setCycleCount(MediaPlayer.INDEFINITE);
		music.setVolume(0.2);
		
		Scene sc = new Scene(menu.getRoot(),Double.MAX_VALUE,Double.MAX_VALUE);
		
		menu.getOnePlayer().addEventHandler(MouseEvent.MOUSE_CLICKED, e ->{
			ft.setDuration(Duration.millis(1000));
			ft.setNode(menu.getRoot());
			ft.setFromValue(1);
			ft.setToValue(0);
			ft.setOnFinished((ActionEvent event) ->{
				Scene s = MenuAlone.getScene(stage, 1920, 1000);
				s.setFill(Color.BLACK);
				stage.setScene(s);
				menu.music.stop();
				music.play();
			});
			ft.play();
		});
		sc.setFill(Color.BLACK);
		stage.setScene(sc);
		stage.setTitle("Mut-Hunter");
		addEvent(stage);
		stage.show();
		
		menu.music.play();
		
		
	}
	
	private void addEvent(Stage stage) {
		stage.addEventHandler(KeyEvent.KEY_TYPED, e ->{
			if(!menu.getIsMenuDisplay()) {
				ft.setDuration(Duration.millis(1000));
				ft.setNode(menu.getRoot());
				ft.setFromValue(1);
				ft.setToValue(0);
				ft.setOnFinished((ActionEvent event) ->{
					VBox newRoot = new VBox(menu.getTitle(),menu.getNbPlayer(),menu.getRegle(),menu.getPersonnage());
					menu.setRoot(newRoot);
					menu.getRoot().setAlignment(Pos.TOP_CENTER);
					Scene newScene = new Scene(menu.getRoot(),1920,1000);
					newScene.setFill(Color.BLACK);
					stage.setScene(newScene);
				});
				
				ft.play();
				menu.setIsMenuDisplay(true);;
			}
		});
		stage.addEventHandler(MouseEvent.MOUSE_CLICKED, e ->{
			if(!menu.getIsMenuDisplay()) {
				ft.setDuration(Duration.millis(1000));
				ft.setNode(menu.getRoot());
				ft.setFromValue(1);
				ft.setToValue(0);
				ft.setOnFinished((ActionEvent event) ->{
					VBox newRoot = new VBox(menu.getTitle(),menu.getNbPlayer(),menu.getRegle(),menu.getPersonnage());
					menu.setRoot(newRoot);
					menu.getRoot().setAlignment(Pos.TOP_CENTER);
					Scene newScene = new Scene(menu.getRoot(),1920,1000);
					newScene.setFill(Color.BLACK);
					stage.setScene(newScene);
				});
				
				ft.play();
				menu.setIsMenuDisplay(true);;
			}
		});
	}
	

}