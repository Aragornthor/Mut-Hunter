package UI;

import classes.Personnage;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;
/**
 * Interface du menu de victoire ou défaite
 * @author Gallifa Robin
 *
 */
public class MenuVictoire extends Application{
	
	private Label title;
	private Button retour;
	private Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
	private static Scene sc;
	private String vainqueur;
	private String persoJoueur;
	private boolean victoireJoueur;
	
	
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		VBox root = getRoot();
		Scene sc = new Scene(root, screenSize.getWidth(), screenSize.getHeight());
		primaryStage.setScene(sc);
		primaryStage.setTitle("Mut'Hunter");
		
		retour.addEventHandler(MouseEvent.MOUSE_CLICKED, e ->{
			Main mm = new Main();
			try {
				mm.start(Main.getStage());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
				
		primaryStage.show();	
	}

	
	
	/**
	 * Initialise le menu de victoire ou défaite
	 */
	public MenuVictoire() {
		MenuVictoire.sc = new Scene(getRoot(),screenSize.getWidth(),screenSize.getHeight());
	}
	
	/**
	 * @return la scene du menu de victoire ou défaite
	 */
	public static Scene getScene() {		
		return sc;
	}
	
	/**
	 * @return le menu de victoire ou défaite
	 */
	public VBox getRoot() {
		System.out.println("GetPerso "+MenuAlone.getPerso());
		System.out.println("Perso 1  "+persoJoueur);
		if(MenuAlone.getPerso() == 0) persoJoueur = "Chasseur";
		else persoJoueur = "Monstre";
		System.out.println("Perso 2  "+persoJoueur);
		vainqueur = GameUI.getVainqueur();
		if(vainqueur==persoJoueur) victoireJoueur=true;
		else victoireJoueur=false;
		System.out.println("Victoire "+victoireJoueur);
		VBox root = new VBox();
		Pane top = new Pane();
		top.setPrefWidth(1000);
		System.out.println("Pseudo  "+MenuAlone.getPseudo());
		if(victoireJoueur) this.title = new Label("VICTOIRE" /*"+MenuAlone.getPseudo()+"*/+" !");
		else this.title = new Label("DEFAITE" /*+MenuAlone.getPseudo()*/+"...");
		this.title.setFont(new Font(50));
		this.title.setPrefWidth(275);
		this.title.setLayoutX(screenSize.getWidth()/2 - this.title.getPrefWidth()/2);

		this.title.setTextFill(Color.WHITE);
	
		Label message = new Label("Le monstre ne causera plus de soucis dans la région. Vous l'avez éliminé et il ne reviendra plus ! Les habitants seront heureux de l'apprendre.");	
		message.setTextFill(Color.WHITE);
		message.setPadding(new Insets(50));
		message.setWrapText(true);
		message.setFont(new Font(35));
		System.out.println("VicJ "+victoireJoueur);
		System.out.println("Perso "+persoJoueur);
		System.out.println("Label "+message);
		if(victoireJoueur && persoJoueur.equals("Monstre")) message.setText("Vous avez réussi à fuir la région ! Le chasseur ne devrait plus vous suivre ici. Ces terres sont fertiles et accueillantes, l'endroit idéal pour s'installer. La vengeance sera pour plus tard...");
		else if(!victoireJoueur && persoJoueur.equals("Monstre")) message.setText("Vous êtes mort. Le chasseur s'en va fièrement montrer votre tête aux siens. C'est ici que l'aventure se finit...");
		else if(!victoireJoueur && persoJoueur.equals("Chasseur")) message.setText("Le monstre s'est enfuit... Vous revenez les mains bredouilles et les habitants de la région doivent maintenant vivre dans la peur constante de voir ce monstre revenir...");
		this.retour = initRetour();
		
		top.getChildren().addAll(this.title,retour);
		
		Separator sep = new Separator();
		sep.setPadding(new Insets(5));
		
		VBox.setMargin(top, new Insets(5));
		root.getChildren().addAll(top,sep, message);
		root.setAlignment(Pos.TOP_CENTER);
			
		
		root.setBackground(new Background(
				new BackgroundImage(
						new Image("file:ressources/images/fondTest.png"),  BackgroundRepeat.NO_REPEAT,
																	   BackgroundRepeat.NO_REPEAT, 
																	   BackgroundPosition.CENTER, 
																	   null)));
		return root;
	}
	
	private Button initRetour() {
		Button retour = new Button("Menu principal");
		retour.setPrefHeight(60);
		retour.setLayoutX(10);
		
		
		
		return retour;
	}
	
	public Button getRetour() {
		return this.retour;
	}

}
