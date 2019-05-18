package UI;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import competences.Competences;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class PlayerInfo /*extends Application*/{
	
	private BoutonCompetence comp1;
	private Button comp2;
	private TextArea info;
	private Label infoTitle;
	private Slider energy;
	private Label valueEnergy;
/*
	public static void main(String[] args) {
		Application.launch(args);

	}
	
	@Override
	public void start(Stage stage) throws Exception {
		stage.setScene(getScene());
		stage.setTitle("Player Info");
		stage.show();
		
	}
*/
	
	
	public GridPane getGridPane() {
		GridPane playerInfo = new GridPane();
		
		Canvas playerIcon = new Canvas(100,100);
		GraphicsContext gc = playerIcon.getGraphicsContext2D();
		gc.setFill(Color.ORANGE);
		gc.fillRect(0, 0, 100, 100);
		this.valueEnergy = new Label();
		valueEnergy.setWrapText(true);
		this.infoTitle = new Label("Info : ");
		this.info = this.initTextArea();
		info.setEditable(false);	
		
		
		this.energy = this.initSlider(this.valueEnergy,75);
		this.comp1 = this.initComp1(this.infoTitle);
		this.comp2 = this.initComp2(this.infoTitle);	
		
		
		GridPane.setMargin(playerIcon, new Insets(5));
		GridPane.setMargin(this.energy, new Insets(5));
		GridPane.setMargin(this.valueEnergy, new Insets(5));
		GridPane.setMargin(this.comp1, new Insets(5));
		GridPane.setMargin(this.comp2, new Insets(5));
		GridPane.setMargin(this.infoTitle, new Insets(5));
		GridPane.setMargin(this.info, new Insets(5));
		
		playerInfo.add(playerIcon, 0, 0, 1, 2);
		playerInfo.add(this.comp1, 1, 0, 1, 1);
		playerInfo.add(this.comp2, 1, 1, 1, 1);
		playerInfo.add(this.infoTitle, 2, 0, 1, 1);
		playerInfo.add(this.energy, 0, 2, 2, 1);
		playerInfo.add(this.valueEnergy, 0, 3, 3, 1);
		playerInfo.add(this.info, 2, 1, 1, 3);
		
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
	
	
	
	private Button initComp2(Label infoTitle) {
		Button comp2 = new Button("Competences 2");
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
	
	private Slider initSlider(Label valueEnergy,int energie) {
		Slider energy = new Slider(0,100,energie);
		this.valueEnergy.setText("Energie : "+(int)energy.getValue()+"/100");
		energy.addEventHandler(MouseEvent.MOUSE_DRAGGED, e ->{
			this.valueEnergy.setText("Energie : "+(int)energy.getValue()+"/100");
		});
		return energy;
	}
	
	
	public static void ajoutCompetence(Competences c,BoutonCompetence bt,TextArea txtA,Label info,Slider s,Label energyValue) {
		bt.setComp(c);
		bt.setText(c.getNom());
		bt.addEventHandler(MouseEvent.MOUSE_ENTERED, e ->{
			info.setText("Info : "+c.getNom());
			txtA.setText(PlayerInfo.getCompDesc(c.getNom()));
		});
		bt.addEventHandler(MouseEvent.MOUSE_CLICKED, e ->{
			if(s.getValue() > c.getCout()) {
				s.setValue(s.getValue()-c.getCout());
				energyValue.setText("Energie : "+(int)s.getValue()+"/100");
			}		
		});
		
	}
	
	public BoutonCompetence getComp1() {
		return this.comp1;
	}
	
	public TextArea getInfo() {
		return this.info;
	}
	
	public Label getInfoTitle() {
		return this.infoTitle;
	}
	
	public Slider getEnergy() {
		return this.energy;
	}
	
	public Label getEnergyValue() {
		return this.valueEnergy;
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
