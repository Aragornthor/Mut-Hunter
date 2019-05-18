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
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class PlayerInfo /*extends Application*/{
	
	private List<BoutonCompetence> bt_Comp;
	private TextArea info;
	private Label infoTitle;
	private EnergyBar valueEnergy;
	private Label playerStatuts;

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
		
		Canvas playerIcon = new Canvas(100,100);
		GraphicsContext gc = playerIcon.getGraphicsContext2D();
		gc.setFill(Color.ORANGE);
		gc.fillRect(0, -10, 100, 100);
		
		this.playerStatuts = new Label("Statut :");
		
		this.valueEnergy = new EnergyBar();
		
		this.infoTitle = new Label("Info : ");
		this.info = this.initTextArea();
		this.info.setEditable(false);	
		
		
		this.bt_Comp = new ArrayList<BoutonCompetence>();
		this.bt_Comp.add(this.initComp1(this.infoTitle));
		this.bt_Comp.add(this.initComp2(this.infoTitle));
		
		
		GridPane.setMargin(playerIcon, new Insets(5));
		GridPane.setMargin(this.valueEnergy, new Insets(5));
		GridPane.setMargin(this.bt_Comp.get(0), new Insets(5));
		GridPane.setMargin(this.bt_Comp.get(1), new Insets(5));
		GridPane.setMargin(this.infoTitle, new Insets(5));
		GridPane.setMargin(this.info, new Insets(5));
		GridPane.setMargin(this.playerStatuts, new Insets(5));
		
		playerInfo.add(playerIcon, 0, 0, 1, 2);
		playerInfo.add(this.bt_Comp.get(0), 1, 0, 1, 1);
		playerInfo.add(this.bt_Comp.get(1), 1, 1, 1, 1);
		playerInfo.add(this.infoTitle, 2, 0, 1, 1);
		playerInfo.add(this.playerStatuts, 0, 2, 3, 1);
		playerInfo.add(this.valueEnergy.getEnergyBar(),  0, 3, 2, 1);
		playerInfo.add(this.info, 2, 1, 1, 3);
		
		this.valueEnergy.setEnergyWidth(75*2);
		
		return playerInfo;
	}
	
	private TextArea initTextArea() {
		TextArea info = new TextArea();
		info.setWrapText(true);
		return info;
	}
	
	private BoutonCompetence initComp1(Label infoTitle) {
		BoutonCompetence comp1 = new BoutonCompetence("Competences 1");
		comp1.setMinSize(150, 25);
		comp1.addEventHandler(MouseEvent.MOUSE_ENTERED, e ->{
			this.infoTitle.setText("Info : Comp1");
			this.info.setText("Ceci est un test");
		});
		
		comp1.addEventHandler(MouseEvent.MOUSE_EXITED, e ->{
			this.infoTitle.setText("Info : ");
			this.info.setText("");
		});
		return comp1;
	}
	
	
	private BoutonCompetence initComp2(Label infoTitle) {
		BoutonCompetence comp2 = new BoutonCompetence("Competences 2");
		comp2.setMinSize(150, 25);
		comp2.addEventHandler(MouseEvent.MOUSE_ENTERED, e ->{
			this.infoTitle.setText("Info : Comp2");
			this.info.setText("Ceci est un test");
		});
		
		comp2.addEventHandler(MouseEvent.MOUSE_EXITED, e ->{
			this.infoTitle.setText("Info : ");
			this.info.setText("");
		});
		return comp2;
	}
	
	
	public void ajoutCompetence(Competences c,int idx) {
		this.bt_Comp.get(0).setComp(c);
		this.bt_Comp.get(0).setText(c.getNom());
		this.bt_Comp.get(0).addEventHandler(MouseEvent.MOUSE_ENTERED, e ->{
			this.infoTitle.setText("Info : "+c.getNom());
			this.info.setText(PlayerInfo.getCompDesc(c.getNom()));
		});
		this.bt_Comp.get(0).addEventHandler(MouseEvent.MOUSE_CLICKED, e ->{
			if(this.valueEnergy.getEnergyWidth()/2 > c.getCout()) {
				//System.out.println(this.valueEnergy.getEnergyWidth()/2);
				this.valueEnergy.setEnergyWidth((this.valueEnergy.getEnergyWidth()) - c.getCout()*2);
				//System.out.println(this.valueEnergy.getEnergyWidth()/2);
			}		
		});
		
	}
	
	public BoutonCompetence getComp1() {
		return this.bt_Comp.get(0);
	}
	
	public BoutonCompetence getComp2() {
		return this.bt_Comp.get(1);
	}
	
	public TextArea getInfo() {
		return this.info;
	}
	
	public Label getInfoTitle() {
		return this.infoTitle;
	}
	
	
	public Pane getEnergyValue() {
		return this.valueEnergy;
	}
	
	public Label getPlayerStatu() {
		return this.playerStatuts;
	}
	
	public void setPlayerStatut(String s) {
		this.playerStatuts.setText(s);
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
