package UI;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import classes.Chasseur;
import classes.Monstre;
import competences.Competences;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class PlayerInfo /*extends Application*/{
	
	private List<BoutonCompetence> bt_Comp;
	private InfoDisplay infoComp;
	private EnergyBar valueEnergy;
	private Label playerStatuts;
	private Canvas playerIcon;
	
	private Chasseur chasseur;
	private Monstre monstre;
	private boolean tourChasseur;

	/*
	public static void main(String[] args) {
		Application.launch(args);

	}
	
	@Override
	public void start(Stage stage) throws Exception {
		VBox root = new VBox();
		
		root.getChildren().addAll(getGridPane(),new EnergyBar().getEnergyBar());
		
		stage.setScene(new Scene(root,1000,1000));
		stage.setTitle("Player Info");
		stage.show();
		
	}

	*/
	
	/**
	 * Instancie PlayerInfo
	 * @param p Le plateau de jeu
	 * @param c Le chasseur
	 * @param m Le monstre
	 */
	public PlayerInfo(Chasseur c, Monstre m) {
		this.chasseur = c;
		this.monstre = m;
		this.tourChasseur = true;
	}
	
	/**
	 * 
	 * @return Renvoie l'interface de l'entité en jeu
	 */
	public GridPane getGridPane() {
		GridPane playerInfo = new GridPane();
		
		this.playerIcon = new Canvas(100,100);
		GraphicsContext gc = playerIcon.getGraphicsContext2D();
		gc.setFill(Color.ORANGE);
		gc.fillRect(0, 0, 100, 100);
		
		this.infoComp = initInfoComp();
		
		this.playerStatuts = new Label("Statut : ");
		
		this.valueEnergy = new EnergyBar();
		
		
		this.bt_Comp = new ArrayList<BoutonCompetence>();	
		this.bt_Comp.add(initComp1());
		this.bt_Comp.add(initComp2());
		
		GridPane.setMargin(this.playerIcon, new Insets(5));
		GridPane.setMargin(this.valueEnergy, new Insets(5));
		GridPane.setMargin(this.bt_Comp.get(0), new Insets(5));
		GridPane.setMargin(this.bt_Comp.get(1), new Insets(5));
		GridPane.setMargin(this.playerStatuts, new Insets(5));
		GridPane.setMargin(this.infoComp, new Insets(5));
		
		playerInfo.add(this.playerIcon, 0, 0, 1, 2);
		playerInfo.add(this.bt_Comp.get(0), 1, 0, 1, 1);
		playerInfo.add(this.bt_Comp.get(1), 1, 1, 1, 1);
		playerInfo.add(this.playerStatuts, 0, 2, 3, 1);
		playerInfo.add(this.valueEnergy.getEnergyBar(),  0, 3, 2, 1);
		playerInfo.add(this.infoComp, 2, 0, 1, 4);
		
		this.valueEnergy.setEnergyWidth(75);
		
		return playerInfo;
	}
	
	/**
	 * 
	 * @return Renvoie l'interface de détail de la compétence
	 */
	private InfoDisplay initInfoComp() {
		InfoDisplay tP = new InfoDisplay();
		tP.setCollapsible(false);
		tP.setMinWidth(500);
		tP.setWrapText(true);
		tP.setMaxWidth(500);
		tP.setMaxHeight(700);
		return tP;
	}
	
	/**
	 * 
	 * @return Renvoie un bouton avec une compétence assignée 
	 */
	private BoutonCompetence initComp1() {
		BoutonCompetence comp1 = new BoutonCompetence("Competences 1",null);
		comp1.addEventHandler(MouseEvent.MOUSE_EXITED, e ->{
			this.infoComp.setText("Info");
			this.infoComp.changeText(this.infoComp.getTxtBase());
		});
		return comp1;
	}
	
	/**
	 * 
	 * @return Renvoie un bouton avec une compétence assignée
	 */
	private BoutonCompetence initComp2() {
		BoutonCompetence comp2 = new BoutonCompetence("Competences 2",null);
		comp2.addEventHandler(MouseEvent.MOUSE_EXITED, e ->{
			this.infoComp.setText("Info");
			this.infoComp.changeText(this.infoComp.getTxtBase());
		});
		return comp2;
	}
	
	/**
	 * Permet l'ajout d'une compétence à une entité
	 * @param c La compétence à ajouter
	 * @param idx La priorité de la compétence
	 */
	public void ajoutCompetence(Competences c,int idx) {
		if(c == null) {
			if(idx < 2 || idx >= 0) {
				this.bt_Comp.get(idx).setComp(null);
				this.bt_Comp.get(idx).getCanvas().getGraphicsContext2D().clearRect(0, 0, 150, 25);;
				this.bt_Comp.get(idx).addEventHandler(MouseEvent.MOUSE_ENTERED, e ->{
					this.infoComp.setText("Info");
					this.infoComp.changeText("");
				});
				this.bt_Comp.get(idx);
			}
		} else {
			if(idx < 2 || idx >= 0) {
				this.bt_Comp.get(idx).setDisable(false);
				this.bt_Comp.get(idx).setComp(c);
				this.bt_Comp.get(idx).getCanvas().getGraphicsContext2D().clearRect(0, 0, 150, 25);
				this.bt_Comp.get(idx).setNewImage(c.getIcon());
				this.bt_Comp.get(idx).getCanvas().getGraphicsContext2D().drawImage(this.bt_Comp.get(idx).getImage(), 0, 0);
				this.bt_Comp.get(idx).addEventHandler(MouseEvent.MOUSE_ENTERED, e ->{
					this.infoComp.setText("Info - "+c.getNom());
					this.infoComp.changeText(getCompDesc(c.getNom()));

				});
			}
		}
	}
	
	/**
	 * Permet l'ajout de plusieurs compétences
	 * @param comp La compétence à ajouter
	 */
	public void ajoutCompetence(Competences[] comp) {
		this.ajoutCompetence(comp[0], 0);
		if(comp[1] != null) {
			this.ajoutCompetence(comp[1], 1);
		}else {
			this.ajoutCompetence(null, 1);
		}
	}
	
	/**
	 * 
	 * @return Renvoie le bouton de la 1ère compétence
	 */
	public BoutonCompetence getComp1() {
		return this.bt_Comp.get(0);
	}
	
	/**
	 * 
	 * @return Renvoie le bouton de la 2nde compétence
	 */
	public BoutonCompetence getComp2() {
		return this.bt_Comp.get(1);
	}
	
	public List<BoutonCompetence> getListBouton(){
		return this.bt_Comp;
	}
	
	/**
	 * 
	 * @return Renvoie l'interface de niveau d'énergie de l'entité en jeu
	 */
	public EnergyBar getEnergyValue() {
		return this.valueEnergy;
	}
	
	/**
	 * 
	 * @return Renvoie l'interface de Statut de l'entité en jeu
	 */
	public Label getPlayerStatut() {
		return this.playerStatuts;
	}
	
	/**
	 * Permet d'actualiser le Statut de l'entité en jeu dans l'interface
	 * @param s
	 */
	public void setPlayerStatut(String s) {
		this.playerStatuts.setText("Statut : "+s);
	}
	
	/**
	 * 
	 * @return Renvoie l'icon de l'entité en jeu
	 */
	public Canvas getPlayerIcon() {
		return this.playerIcon;
	}
	
	/**
	 * 
	 * @return Renvoie l'interface d'information de la compétence survolée
	 */
	public InfoDisplay getInfoDisplay() {
		return this.infoComp;
	}
	
	/**
	 * Permet de modifier l'icon de l'entité en jeu
	 * @param img La nouvelle icon
	 */
	public void setPlayerIcon(Image img) {
		this.playerIcon.getGraphicsContext2D().clearRect(0, 0, this.playerIcon.getWidth(), this.playerIcon.getHeight());
		this.playerIcon.getGraphicsContext2D().drawImage(img,
				(this.playerIcon.getWidth()/2) - img.getWidth()/2,
				(this.playerIcon.getHeight()/2) - img.getHeight()/2);
	}
	
	/**
	 * Permet de gérer qui joue
	 * @param b TRUE si c'est au chasseur sinon FALSE
	 */
	public void setTourChasseur(boolean b) {
		this.tourChasseur = b;
	}
	
	/**
	 * Gère la barre d'énergie de l'interface
	 */
	public void displayEnergy() {
		if(this.tourChasseur) {
			this.valueEnergy.setEnergyWidth(this.chasseur.getEnergie());
		} else {
			this.valueEnergy.setEnergyWidth(this.monstre.getEnergie());
		}
	}
	
	/**
	 * Permet l'accès aux descriptions des compétences
	 * @param compName La compétence souhaitée
	 * @return Renvoie la description de la compétence spécifiée
	 */
	@SuppressWarnings("resource")
	private static String getCompDesc(String compName) {
		String desc = "";
		File f = new File("ressources/Desc_Comp");
		FileReader fr = null;
		try {
			fr = new FileReader(f);
		} catch (FileNotFoundException e) {
			return null;
		}
		BufferedReader br = new BufferedReader(fr);
		String c = null;
		try {
			c = br.readLine();
		} catch (IOException e) {
			return null;
		}
		while(c!=null) {
			if(c.contains(compName)) {
				desc = c.split(";", c.length())[1];
			}
			try {
				c = br.readLine();
			} catch (IOException e) {
				return null;
			}
		}
	
		try {
			br.close();
		} catch (IOException e) {
			return null;
		}
		
		desc = desc.replaceAll("#", "\n");
		return desc;
	}
	
	 

}
