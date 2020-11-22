package view;

import interfaces.ComandAssistence;
import interfaces.StrategyPane;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application implements EventHandler<ActionEvent>,
													ComandAssistence{
	private BorderPane mainPane;
	private final MainView mainView = new MainView();
	private final ReservasView reservasview = new ReservasView();
	private final CadastroView cadastroview = new CadastroView();
	private StrategyPane targetPane = mainView;
	
	
	@Override
	public void start(Stage stage) throws Exception {
		mainPane = new BorderPane();
		Scene scn = new Scene(mainPane, 800, 600);
		
		mainView.setAssistence(this);
		reservasview.setAssistence(this);
		cadastroview.setAssistence(this);
		
		context();
		
		stage.setScene(scn);
		stage.setTitle("Clube da Bolinha");
		stage.show();
	}
	
	private void context() {
		mainPane.setCenter(targetPane.getPane());
	}
	
	@Override
	public void handle(ActionEvent event) {
		
	}
	
	@Override
	public void executeCommand(String cmd) {
		if(cmd.equals("Enter")) {
			System.out.println("Entrou");
		}else if (cmd.equals("Reservar")) {
			System.out.println("Reservou");
		}else if (cmd.equals("Cadastrar")) {
			System.out.println("Cadastrou");
		}
		this.context();
	}
	
	public static void main(String[] args) {
		Application.launch(Main.class, args);
	}

	

}
