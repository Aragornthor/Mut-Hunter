package classes;

import UI.PlayerInfo;
import competences.Acide;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TestPlayerInfo extends Application{

	public static void main(String[] args) {
		Application.launch(args);
		

	}

	@Override
	public void start(Stage stage) throws Exception {
		PlayerInfo pI = new PlayerInfo();
		Scene sc = pI.getScene();
		stage.setTitle("Test PLayerInfo");
		PlayerInfo.ajoutCompetence(new Acide(), pI.getComp1(), pI.getInfo());
		stage.setScene(sc);
		stage.show();
		
	}

}
