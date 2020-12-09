package view;

import config.DatabaseConnection;
import interfaces.ComandAssistence;
import interfaces.StrategyPane;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application implements EventHandler<ActionEvent>,
													ComandAssistence{
	private Pane mainPane;
	private final MainView mainView = new MainView();
	private final ReservasView reservasview = new ReservasView();
	private final CadastroView cadastroview = new CadastroView();
	private final GerenciarAssocView associadoview = new GerenciarAssocView ();
	private final ReservationDashboardView reservationdashboardview = new ReservationDashboardView();
	private final SubscriptionDashboardView subscriptiondashboardview = new SubscriptionDashboardView();
	private final Menu menu = new Menu();
	private StrategyPane targetPane = mainView;
	
	
	@Override
	public void start(Stage stage) throws Exception {
		mainPane = new Pane();
		Scene scn = new Scene(mainPane, 800, 600);
		
		mainView.setAssistence(this);
		reservasview.setAssistence(this);
		cadastroview.setAssistence(this);
		associadoview.setAssistence(this);
		reservationdashboardview.setAssistence(this);
		subscriptiondashboardview.setAssistence(this);
		menu.setAssistence(this);
		
		context();
		
		stage.setScene(scn);
		stage.setTitle("Clube da Bolinha");
		stage.show();
	}
	
	private void context() {
		if (targetPane == mainView) {
			mainPane.getChildren().setAll(targetPane.getPane());
		} else {
			mainPane.getChildren().setAll(targetPane.getPane(),menu.getPane());
		}
	}
	
	@Override
	public void handle(ActionEvent event) {
		
	}
	
	@Override
	public void executeCommand(String cmd) {
		if(cmd.equals("Enter")) {
			targetPane = reservationdashboardview;
		}else if(cmd.equals("dashboard")) {
			System.out.println("Dashboard");
		}else if(cmd.equals("reservation")) {
			targetPane = reservationdashboardview;
		}else if(cmd.equals("associated")) {
			targetPane = associadoview;
		}else if(cmd.equals("tuition")) {
			targetPane = subscriptiondashboardview;
		}else if (cmd.equals("Reservar")) {
			this.reservasview.reservationToControl();
		}else if (cmd.equals("Cadastrar")) {
			this.cadastroview.associatedToControl();
		}else if(cmd.equals("SearchAssociate")) {
			System.out.println("Procurou o associadozada");
		}else if(cmd.equals("AddReserva")) {
			targetPane = reservasview;
		}else if(cmd.equals("AddAssociate")) {
			targetPane = cadastroview;
		}
		context();
	}
	
	public static void main(String[] args) throws Exception {
		DatabaseConnection.createTables();
		Application.launch(Main.class, args);
	}
}
