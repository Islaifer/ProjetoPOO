package view;


import controllers.ReservationController;
import interfaces.ComandAssistence;
import interfaces.ComandProductor;
import interfaces.StrategyPane;
import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import models.Reservation;

public class ReservationDashboardView implements StrategyPane, ComandProductor{
	private ComandAssistence a;
	private Pane pane;
	private TableView<Reservation> table;
	private ReservationController reservationController;
	
	@SuppressWarnings("unchecked")
	public ReservationDashboardView() {
		reservationController = new ReservationController();
		this.pane = new Pane();
		Label lbltittle = new Label ("Reservas");
		lbltittle.relocate (250, 30);
		lbltittle.setFont(new Font("Arial",18));
		
		Label lblreservation = new Label ("Reservas");
		lblreservation.relocate (250, 230);
		lblreservation.setFont(new Font("Arial",13));
		
		table = new TableView<>();
		table.relocate(235, 250);
		table.setMinWidth(530);
		table.setMaxWidth(530);
		table.setMaxHeight(250);
		
		//colunas
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
		columnSpace.setMinWidth(140);
		columnSpace.setMaxWidth(140);
		TableColumn<Reservation, String> columnQntd = new TableColumn<>("Quantidade de pessoas");
		columnQntd.setCellValueFactory(new PropertyValueFactory<>("peopleqnt"));
		columnQntd.setMinWidth(150);
		columnQntd.setMaxWidth(150);
		this.refreshTable();
		table.getColumns().addAll(columnDate, columnName, columnSpace, columnQntd);
		
		//botao
		Button btnAddReservation = new Button("Adicionar Reserva");
		btnAddReservation.relocate(650,210);
		btnAddReservation.setMinHeight(30);
		btnAddReservation.setMinWidth(30);
		btnAddReservation.setOnAction((e)->{
			exeComand("AddReserva");
		});
		
		Button btnEditReservation = new Button("Editar Reserva");
		btnEditReservation.relocate(655,170);
		btnEditReservation.setMinHeight(30);
		btnEditReservation.setMinWidth(100);
		btnEditReservation.setOnAction((e)->{
			exeComand("AttReserva");
		});
		
		pane.getChildren().addAll(lbltittle, lblreservation, table, btnAddReservation, btnEditReservation);
	}
	
	public int reservationSelected() {
		return table.getSelectionModel().getSelectedItem().getId();
	}
	
	public void refreshTable() {
		try {
			table.setItems(FXCollections.observableArrayList(reservationController.getAll()));
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
		return this.pane;
	}
	
}
