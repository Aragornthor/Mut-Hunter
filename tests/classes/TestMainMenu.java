package classes;

import java.io.File;

import UI.MainMenu;
import UI.MenuAlone;
import UI.RegleMenu;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

public class TestMainMenu extends Application{
	
	MainMenu menu = new MainMenu();
	FadeTransition ft = new FadeTransition();
	MediaPlayer music;
	Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();

	public static void main(String[] args) {
		Application.launch(args);

	}

	@Override
	public void start(Stage stage) throws Exception {
		
		Media pick = new Media(new File("ressources/sounds/menuAlone.mp3").toURI().toString());
		music = new MediaPlayer(pick);
		
		music.setCycleCount(MediaPlayer.INDEFINITE);
		music.setVolume(0.2);
		
		Scene sc = new Scene(menu.getRoot(),screenSize.getWidth(),screenSize.getHeight());
		
		menu.getOnePlayer().addEventHandler(MouseEvent.MOUSE_CLICKED, e ->{
			ft.setDuration(Duration.millis(1000));
			ft.setNode(menu.getRoot());
			ft.setFromValue(1);
			ft.setToValue(0);
			ft.setOnFinished((ActionEvent event) ->{
				Scene s = MenuAlone.getScene(stage, stage.getWidth(), stage.getHeight());
				s.setFill(Color.BLACK);
				stage.setScene(s);
				menu.music.stop();
				music.play();
			});
			ft.play();
		});
		
		RegleMenu rm = new RegleMenu();
		rm.getRetour().addEventHandler(MouseEvent.MOUSE_CLICKED, e ->{
			MainMenu m = new MainMenu();
			try {
				stage.setScene(new Scene(m.getRoot(),
											  screenSize.getWidth(),
											  screenSize.getHeight()));
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		menu.getRegle().addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
			ft.setDuration(Duration.millis(1000));
			ft.setNode(menu.getRoot());
			ft.setFromValue(1);
			ft.setToValue(0);
			ft.setOnFinished((ActionEvent event) ->{	
				Scene s = rm.getScene();
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
					newRoot.setBackground(new Background(
							new BackgroundImage(
									new Image("file:ressources/images/fondTest.png"),  BackgroundRepeat.NO_REPEAT,
																				   BackgroundRepeat.NO_REPEAT, 
																				   BackgroundPosition.CENTER, 
																				   null)));
					menu.setRoot(newRoot);
					menu.getRoot().setAlignment(Pos.TOP_CENTER);
					Scene newScene = new Scene(menu.getRoot(),stage.getWidth(),stage.getHeight());
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
					newRoot.setBackground(new Background(
							new BackgroundImage(
									new Image("file:ressources/images/fondTest.png"),  BackgroundRepeat.NO_REPEAT,
																				   BackgroundRepeat.NO_REPEAT, 
																				   BackgroundPosition.CENTER, 
																				   null)));
					menu.setRoot(newRoot);
					menu.getRoot().setAlignment(Pos.TOP_CENTER);
					Scene newScene = new Scene(menu.getRoot(),stage.getWidth(),stage.getHeight());
					newScene.setFill(Color.BLACK);
					stage.setScene(newScene);
				});
				
				ft.play();
				menu.setIsMenuDisplay(true);;
			}
		});
	}

}
