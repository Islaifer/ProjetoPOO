package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application{
	private Pane mainPane;

	@Override
	public void start(Stage stage) throws Exception {
		mainPane = new Pane();
		Scene scn = new Scene(mainPane);
		
		stage.setScene(scn);
		stage.setTitle("Clube da bolinha");
		stage.show();
	}
	
	public static void main(String[] args) {
		Application.launch(Main.class, args);
	}

}
