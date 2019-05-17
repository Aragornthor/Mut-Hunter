package UI;



import competences.Competences;
import javafx.geometry.Insets;
import javafx.scene.Scene;
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
	
	private Button comp1;
	private Button comp2;
	private TextArea info;
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
	
	
	public Scene getScene() {
		GridPane playerInfo = new GridPane();
		
		Canvas playerIcon = new Canvas(100,100);
		GraphicsContext gc = playerIcon.getGraphicsContext2D();
		gc.setFill(Color.ORANGE);
		gc.fillRect(0, 0, 100, 100);
		Label valueEnergy = new Label();
		valueEnergy.setWrapText(true);
		Label infoTitle = new Label("Info : ");
		this.info = new TextArea();
		info.setEditable(false);	
		
		
		Slider energy = getSlider(valueEnergy);
		this.comp1 = initComp1(infoTitle);
		this.comp2 = initComp2(infoTitle);	
		
		
		GridPane.setMargin(playerIcon, new Insets(5));
		GridPane.setMargin(energy, new Insets(5));
		GridPane.setMargin(valueEnergy, new Insets(5));
		GridPane.setMargin(this.comp1, new Insets(5));
		GridPane.setMargin(this.comp2, new Insets(5));
		GridPane.setMargin(infoTitle, new Insets(5));
		GridPane.setMargin(this.info, new Insets(5));
		
		playerInfo.add(playerIcon, 0, 0, 1, 2);
		playerInfo.add(this.comp1, 1, 0, 1, 1);
		playerInfo.add(this.comp2, 1, 1, 1, 1);
		playerInfo.add(infoTitle, 2, 0, 1, 1);
		playerInfo.add(energy, 0, 2, 2, 1);
		playerInfo.add(valueEnergy, 0, 3, 3, 1);
		playerInfo.add(this.info, 2, 1, 1, 3);
		
		return new Scene(playerInfo,720,250);
	}
	
	
	
	private Button initComp1(Label infoTitle) {
		Button comp1 = new Button("Competences 1");
		comp1.setMinSize(150, 25);
		comp1.addEventHandler(MouseEvent.MOUSE_ENTERED, e ->{
			infoTitle.setText("Info : Comp1");
			this.info.setText("Ceci est un test");
		});
		
		comp1.addEventHandler(MouseEvent.MOUSE_EXITED, e ->{
			infoTitle.setText("Info : ");
			this.info.setText("");
		});
		return comp1;
	}
	
	
	
	private Button initComp2(Label infoTitle) {
		Button comp2 = new Button("Competences 2");
		comp2.setMinSize(150, 25);
		comp2.addEventHandler(MouseEvent.MOUSE_ENTERED, e ->{
			infoTitle.setText("Info : Comp2");
			this.info.setText("Ceci est un test");
		});
		
		comp2.addEventHandler(MouseEvent.MOUSE_EXITED, e ->{
			infoTitle.setText("Info : ");
			this.info.setText("");
		});
		return comp2;
	}
	
	private Slider getSlider(Label valueEnergy) {
		Slider energy = new Slider(0,100,75);
		valueEnergy.setText("Energie : "+(int)energy.getValue()+"/100");
		energy.addEventHandler(MouseEvent.MOUSE_DRAGGED, e ->{
			valueEnergy.setText("Energie : "+(int)energy.getValue()+"/100");
		});
		return energy;
	}
	
	
	public static void ajoutCompetence(Competences c,Button bt,TextArea txtA) {
		bt.setText(c.getNom());
	}
	
	public Button getComp1() {
		return this.comp1;
	}
	
	public TextArea getInfo() {
		return this.info;
	}
	
	 

}
