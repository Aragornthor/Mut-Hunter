package UI;



import javafx.application.Application;
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
import javafx.stage.Stage;

public class PlayerInfo extends Application{

	public static void main(String[] args) {
		Application.launch(args);

	}
	
	@Override
	public void start(Stage stage) throws Exception {
		stage.setScene(getScene());
		stage.setTitle("Player Info");
		stage.show();
		
	}
	
	
	
	public Scene getScene() {
		GridPane playerInfo = new GridPane();
		
		Canvas playerIcon = new Canvas(100,100);
		GraphicsContext gc = playerIcon.getGraphicsContext2D();
		gc.setFill(Color.ORANGE);
		gc.fillRect(0, 0, 100, 100);
		Label valueEnergy = new Label();
		Label infoTitle = new Label("Info : ");
		TextArea info = new TextArea();
		info.setEditable(false);	
		
		
		Slider energy = getSlider(valueEnergy);
		Button comp1 = getComp1(infoTitle,info);
		Button comp2 = getComp2(infoTitle,info);	
		
		
		GridPane.setMargin(playerIcon, new Insets(5));
		GridPane.setMargin(energy, new Insets(5));
		GridPane.setMargin(valueEnergy, new Insets(5));
		GridPane.setMargin(comp1, new Insets(5));
		GridPane.setMargin(comp2, new Insets(5));
		GridPane.setMargin(infoTitle, new Insets(5));
		GridPane.setMargin(info, new Insets(5));
		
		playerInfo.add(playerIcon, 0, 0, 1, 2);
		playerInfo.add(comp1, 1, 0, 1, 1);
		playerInfo.add(comp2, 1, 1, 1, 1);
		playerInfo.add(infoTitle, 2, 0, 1, 1);
		playerInfo.add(energy, 0, 2, 2, 1);
		playerInfo.add(valueEnergy, 0, 3, 3, 1);
		playerInfo.add(info, 2, 1, 1, 3);
		
		return new Scene(playerInfo,720,250);
	}
	
	
	
	private Button getComp1(Label infoTitle,TextArea info) {
		Button comp1 = new Button("Competences 1");
		comp1.addEventHandler(MouseEvent.MOUSE_ENTERED, e ->{
			infoTitle.setText("Info : Comp1");
			info.setText("Ceci est un test");
		});
		
		comp1.addEventHandler(MouseEvent.MOUSE_EXITED, e ->{
			infoTitle.setText("Info : ");
			info.setText("");
		});
		return comp1;
	}
	
	
	
	private Button getComp2(Label infoTitle,TextArea info) {
		Button comp2 = new Button("Competences 2");
		comp2.addEventHandler(MouseEvent.MOUSE_ENTERED, e ->{
			infoTitle.setText("Info : Comp2");
			info.setText("Ceci est un test");
		});
		
		comp2.addEventHandler(MouseEvent.MOUSE_EXITED, e ->{
			infoTitle.setText("Info : ");
			info.setText("");
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
	
	
	 

}
