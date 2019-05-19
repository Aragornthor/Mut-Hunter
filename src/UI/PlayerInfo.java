package UI;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import competences.Competences;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
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
		
		this.valueEnergy.setEnergyWidth(75*2);
		
		return playerInfo;
	}
	
	private InfoDisplay initInfoComp() {
		InfoDisplay tP = new InfoDisplay();
		tP.setCollapsible(false);
		tP.setMinWidth(500);
		tP.setWrapText(true);
		tP.setMaxWidth(500);
		tP.setMaxHeight(700);
		return tP;
	}
	
	private BoutonCompetence initComp1() {
		BoutonCompetence comp1 = new BoutonCompetence("Competences 1");
		comp1.setMinSize(150, 25);
		comp1.addEventHandler(MouseEvent.MOUSE_ENTERED, e ->{
			this.infoComp.setText("Info - Competences 1");
			this.infoComp.changeText("Competence 1");
		});
		
		comp1.addEventHandler(MouseEvent.MOUSE_EXITED, e ->{
			this.infoComp.setText("Info");
			this.infoComp.changeText(" ");
		});
		return comp1;
	}
	
	
	private BoutonCompetence initComp2() {
		BoutonCompetence comp2 = new BoutonCompetence("Competences 2");
		comp2.setMinSize(150, 25);
		comp2.addEventHandler(MouseEvent.MOUSE_ENTERED, e ->{
			this.infoComp.setText("Info - Competences 2");
			this.infoComp.changeText("Competence 2");
		});
		
		comp2.addEventHandler(MouseEvent.MOUSE_EXITED, e ->{
			this.infoComp.setText("Info");
			this.infoComp.changeText(" ");
		});
		return comp2;
	}
	
	
	public void ajoutCompetence(Competences c,int idx) {
		this.bt_Comp.get(0).setComp(c);
		this.bt_Comp.get(0).setText(c.getNom());
		this.bt_Comp.get(0).addEventHandler(MouseEvent.MOUSE_ENTERED, e ->{
			this.infoComp.setText("Info - "+c.getNom());
			this.infoComp.changeText(getCompDesc(c.getNom()));

		});
		this.bt_Comp.get(0).addEventHandler(MouseEvent.MOUSE_CLICKED, e ->{
			if(this.valueEnergy.getEnergyWidth()/2 > c.getCout()) {
				this.valueEnergy.perdreEnergy(c.getCout());
				
			}		
		});
		
	}
	
	public BoutonCompetence getComp1() {
		return this.bt_Comp.get(0);
	}
	
	public BoutonCompetence getComp2() {
		return this.bt_Comp.get(1);
	}
	
	public TitledPane getInfoComp() {
		return this.infoComp;
	}
	
	public EnergyBar getEnergyValue() {
		return this.valueEnergy;
	}
	
	public Label getPlayerStatut() {
		return this.playerStatuts;
	}
	
	public void setPlayerStatut(String s) {
		this.playerStatuts.setText(s);
	}
	
	public Canvas getPlayerIcon() {
		return this.playerIcon;
	}
	
	public void setPlayerIcon(Image img) {
		this.playerIcon.getGraphicsContext2D().drawImage(img,
				(this.playerIcon.getWidth()/2) - img.getWidth()/2,
				(this.playerIcon.getHeight()/2) - img.getHeight()/2);
	}
	
	@SuppressWarnings("resource")
	private static String getCompDesc(String compName) {
		String desc = null;
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
				desc = c.split(":", c.length())[1];
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
		return desc;
	}
	
	 

}
