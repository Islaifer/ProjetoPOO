package view;

import controllers.ReservationController;
import controllers.UserController;
import interfaces.ComandAssistence;
import interfaces.ComandProductor;
import interfaces.StrategyPane;
import javafx.collections.FXCollections;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import models.Reservation;
import models.User;

public class DashboardView implements StrategyPane, ComandProductor {
	private ComandAssistence a;
	private Pane pane;
	private TableView <Reservation> tableA;
	private TableView <User> tableB;
	private ReservationController reservationController;
	private UserController userController;
	
	@SuppressWarnings("unchecked")
	public DashboardView() {
		reservationController = new ReservationController();
		userController = new UserController();
		pane = new Pane();
			
		tableA = new TableView();
		tableA.relocate(235,100);
		tableA.setMinWidth(500);
		tableA.setMaxWidth(500);
		tableA.setMaxHeight(200);
		
		tableB = new TableView();
		tableB.relocate(235,350);
		tableB.setMinWidth(500);
		tableB.setMaxWidth(500);
		tableB.setMaxHeight(200);
		
		Label lblreservation = new Label ("Reservas do dia");
		lblreservation.relocate (250, 70);
		lblreservation.setFont(Font.font("Arial", FontWeight.BOLD, 14));
		
		Label lblusers = new Label ("Cadastros");
		lblusers.relocate (250,320);
		lblusers.setFont(Font.font("Arial",FontWeight.BOLD,14));
		
		TableColumn<Reservation, String> columnDate = new TableColumn<>("Data");
		columnDate.setCellValueFactory(new PropertyValueFactory<>("date"));
		columnDate.setMinWidth(110);
		columnDate.setMaxWidth(110);
		TableColumn<Reservation, String> columnName = new TableColumn<>("Associado");
		columnName.setCellValueFactory(new PropertyValueFactory<>("username"));
		columnName.setMinWidth(130);
		columnName.setMaxWidth(130);
		TableColumn<Reservation, String> columnSpace = new TableColumn<>("Espaco");
		columnSpace.setCellValueFactory(new PropertyValueFactory<>("spaceName"));
		columnSpace.setMinWidth(130);
		columnSpace.setMaxWidth(130);
		TableColumn<Reservation, String> columnQntd = new TableColumn<>("Quantidade de pessoas");
		columnQntd.setCellValueFactory(new PropertyValueFactory<>("peopleqnt"));
		columnQntd.setMinWidth(120);
		columnQntd.setMaxWidth(120);
		this.refreshTableA();
		tableA.getColumns().addAll(columnDate, columnName, columnSpace, columnQntd);
		
		TableColumn<User, String> columnUsername = new TableColumn<>("Associado");
		columnUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
		columnUsername.setMinWidth(110);
		columnUsername.setMaxWidth(110);
		TableColumn<User, String> columnCpf = new TableColumn<>("CPF");
		columnCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
		columnCpf.setMinWidth(130);
		columnCpf.setMaxWidth(130);
		TableColumn<User, String> columnBirth = new TableColumn<>("Aniversário");
		columnBirth.setCellValueFactory(new PropertyValueFactory<>("birthdate"));
		columnBirth.setMinWidth(140);
		columnBirth.setMaxWidth(140);
		TableColumn<User, String> columnAddress = new TableColumn<>("Endereço");
		columnAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
		columnAddress.setMinWidth(130);
		columnAddress.setMaxWidth(130);
		this.refreshTableB();
		tableB.getColumns().addAll(columnUsername, columnCpf, columnBirth, columnAddress);
		
		pane.getChildren().addAll(tableA, tableB, lblusers, lblreservation);
	}
	
	public void refreshTableA() {
		try {
			tableA.setItems(FXCollections.observableArrayList(reservationController.getAll()));
					} catch (Exception e) {
			System.err.println(e);
		}
	}
	public void refreshTableB() {
		try {
			tableB.setItems(FXCollections.observableArrayList(userController.getAll()));
					} catch (Exception e) {
			System.err.println(e);
		}
	}
	
	
	@Override
	public void setAssistence(ComandAssistence a) {
		this.a = a;
		
	}
	@Override
	public void exeComand(String cmd) {
		this.a.executeCommand(cmd);		
	}
	@Override
	public Pane getPane() {
		return pane;
	}
}
