package UI;



import javafx.application.Application;
//import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Separator;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class MenuAlone2J extends Application {
	
	static boolean use = false;
	static ToggleGroup formePlateau = new ToggleGroup();
	static ToggleGroup typeClimat = new ToggleGroup();
	static ToggleGroup personnage = new ToggleGroup();
	static TextField pseudo;
	
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setScene(MenuAlone2J.getScene(primaryStage, primaryStage.getWidth(), primaryStage.getHeight()));
		primaryStage.show();
		//primaryStage.setMinWidth(1280);
		//primaryStage.setMinHeight(720);
	}
	
	/**
	 * 
	 * @param s Stage principal
	 * @param width Largeur souhaitée
	 * @param height Hauteur souhaitée
	 * @return Une scène du Menu
	 */
	public static Scene getScene(Stage s, double width, double height) {
		VBox root = new VBox();
		root.setBackground(new Background(
				new BackgroundImage(
						new Image("file:ressources/images/fondTest.png"),  BackgroundRepeat.NO_REPEAT,
																	   BackgroundRepeat.NO_REPEAT, 
																	   BackgroundPosition.CENTER, 
																	   null)));
		root.getChildren().addAll(getSep(), getPlateau(), getSep(), getPseudo(s));
		root.setAlignment(Pos.CENTER);
		if(isFullscreen(s)) root.setPadding(new Insets(500));
		else root.setPadding(new Insets(200));
		return new Scene(root, width, height);
	}
	
	/**
	 * 
	 * @return Un séparateur d'élément
	 */
	private static Separator getSep() {
		Separator sep = new Separator();
		sep.setPadding(new Insets(5));
		
		return sep;
	}
	
	/**
	 * 
	 * @return Choix du type de plateau
	 */
	private static VBox getPlateau() {
		VBox root = new VBox();
		
		Label title = new Label("Plateau");
		title.setStyle("-fx-font: 32px Verdana;");
		title.setTextFill(Color.WHITE);
		
		//Création des groupes des RadioButtons
		

		HBox size = new HBox(); // Modifier les BUTTONs avec des IMG
		RadioButton square = new RadioButton("Carré");
		RadioButton rect = new RadioButton("Rectangulaire");
		RadioButton circle = new RadioButton("Circulaire");
		
		//Assignation des RadioButtons de la forme du plateau
		square.setToggleGroup(formePlateau);
		rect.setToggleGroup(formePlateau);
		circle.setToggleGroup(formePlateau);
		
		square.setTextFill(Color.WHITE);
		rect.setTextFill(Color.WHITE);
		circle.setTextFill(Color.WHITE);
		
		square.setSelected(true);
		
		size.getChildren().addAll(square, rect, circle);

		square.setMinSize(64, 64);
		square.setMaxSize(64, 64);
		HBox.setMargin(square, new Insets(25));
		rect.setMinSize(64, 64);
		rect.setMaxSize(64, 64);
		HBox.setMargin(rect, new Insets(25));
		circle.setMinSize(64, 64);
		circle.setMaxSize(64, 64);
		HBox.setMargin(circle, new Insets(25));
		
		size.setAlignment(Pos.CENTER);
		
		HBox type = new HBox(); // Modifier les BUTTONs avec des IMG
		RadioButton temp = new RadioButton("Tempéré");
		RadioButton desert = new RadioButton("Désertique");
		RadioButton frozen = new RadioButton("Glacial");
		RadioButton mel = new RadioButton("Mélange");
		
		temp.setTextFill(Color.WHITE);
		desert.setTextFill(Color.WHITE);
		frozen.setTextFill(Color.WHITE);
		mel.setTextFill(Color.WHITE);
		
		//Assignation des RadioButtons du type de climat
		temp.setToggleGroup(typeClimat);
		desert.setToggleGroup(typeClimat);
		frozen.setToggleGroup(typeClimat);
		mel.setToggleGroup(typeClimat);
		
		temp.setSelected(true);
		
		type.getChildren().addAll(temp, desert, frozen, mel);
		
		temp.setMinSize(64, 64);
		temp.setMaxSize(64, 64);
		HBox.setMargin(temp, new Insets(5));
		desert.setMinSize(64, 64);
		desert.setMaxSize(64, 64);
		HBox.setMargin(desert, new Insets(5));
		frozen.setMinSize(64, 64);
		frozen.setMaxSize(64, 64);
		HBox.setMargin(frozen, new Insets(5));
		mel.setMinSize(64, 64);
		mel.setMaxSize(64, 64);
		HBox.setMargin(mel, new Insets(5));
		
		type.setAlignment(Pos.CENTER);
		
		root.getChildren().addAll(title, size, type);
		VBox.setMargin(title, new Insets(5));
		VBox.setMargin(size, new Insets(5));
		VBox.setMargin(type, new Insets(5));
		root.setAlignment(Pos.TOP_CENTER);
		
		return root;
	}
	
	public static int getForme() {
		if(formePlateau.getSelectedToggle() == null) return 0;
		else if(formePlateau.getSelectedToggle().toString().contains("Carré")) {
			return 0;
		} else if(formePlateau.getSelectedToggle().toString().contains("Rectangulaire")) {
			return 1;
		} else {
			return 2;
		}
	}
	
	public static int getClimat() {
		if(typeClimat.getSelectedToggle() == null) return 0;
		if(typeClimat.getSelectedToggle().toString().contains("Tempéré")) {
			//System.out.println(typeClimat.getSelectedToggle().toString());
			return 0;
		} else if(typeClimat.getSelectedToggle().toString().contains("Désertique")) {
			//System.out.println(typeClimat.getSelectedToggle().toString());
			return 1;
		} else if(typeClimat.getSelectedToggle().toString().contains("Glacial")) {
			//System.out.println(typeClimat.getSelectedToggle().toString());
			return 2;
		} else { //si c'est Mélange qui est sélectionné
			//System.out.println(typeClimat.getSelectedToggle().toString());
			return 4;
		}
	}
	
	/**
	 * 
	 * @param s Stage principal
	 * @return Saisie du pseudo
	 */
	
	private static HBox getPseudo(Stage s) {
		HBox root = new HBox();
		
		TextField pseudo = new TextField();
		MenuAlone2J.pseudo = pseudo;
		pseudo.setPromptText("Saisissez votre pseudo");
		pseudo.setStyle("-fx-border-radius: 3 0 0 3;"
				+ "-fx-background-radius: 3 0 0 3;"
				+ "-fx-outline: none;"
				+ "-fx-border-color: rgb(179,205,224);"
				+ "-fx-padding: 15 20 15 20;"
				+ "-fx-background-color: rgb(179,205,224);"
				+ "-fx-text-fill: rgb(77,100,141);"
				+ "-fx-focus-color: transparent;"
				+ "-fx-faint-focus-color: transparent;"
				+ "-fx-font: 24px Verdana;"
				+ "-fx-height: 55px;");
		Pane btStart = new Pane();
		btStart.setPrefSize(150, 70);
		btStart.setMaxSize(150, 70);
		/*
		Button start = new Button("Jouer !");
		start.setStyle("-fx-border-radius: 0 3 3 0;"
				+ "-fx-background-radius: 0 3 3 0;"
				+ "-fx-border : 0 0 0 0;"
				+ "-fx-padding: 16 10 15 0;"
				+ "-fx-background-color: rgb(179,205,224);"
				+ "-fx-text-fill: rgb(77,100,141);"
				+ "-fx-font: 24px Verdana;"
				+ "-fx-height: 55px;");
		*/
		Canvas display = new Canvas(150,70);
		Image start = new Image("file:ressources/images/boutonJouer.png");
		display.getGraphicsContext2D().drawImage(start, 0, 0);
		btStart.getChildren().add(display);
		btStart.addEventHandler(MouseEvent.MOUSE_CLICKED, e->{
			LancementGameUI m = new LancementGameUI();
			try {
				use = true;
				m.start(s);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		root.getChildren().addAll(pseudo, btStart);
		root.setAlignment(Pos.TOP_CENTER);
		
		return root;
	}
	
	/**
	 * 
	 * @param s Stage principal
	 * @return Statut (FULLSCREEN) du Stage
	 */
	private static boolean isFullscreen(Stage s) {
		return s.isFullScreen();
	}
	
	public static String getPseudo() {
		if(pseudo != null && !pseudo.getText().equals("")) return pseudo.getText();
		else return "Aucun nom";
	}
}