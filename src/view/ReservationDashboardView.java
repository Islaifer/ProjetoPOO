package view;


import interfaces.ComandAssistence;
import interfaces.ComandProductor;
import interfaces.StrategyPane;
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
	
	@SuppressWarnings("unchecked")
	public ReservationDashboardView() {
		this.pane = new Pane();
		Label lbltittle = new Label ("Reservas");
		lbltittle.relocate (250, 30);
		lbltittle.setFont(new Font("Arial",18));
		
		Label lblreservation = new Label ("Reservas");
		lblreservation.relocate (250, 230);
		lblreservation.setFont(new Font("Arial",13));
		
		TableView<Reservation> table = new TableView<>();
		//table.setItems(FXCollections.observableArrayList(teste));
		table.relocate(235, 250);
		table.setMinWidth(530);
		table.setMaxWidth(530);
		table.setMaxHeight(250);
		
		//colunas
		TableColumn<Reservation, String> columnDate = new TableColumn<>("Data");
		columnDate.setCellValueFactory(new PropertyValueFactory<>("date"));
		columnDate.setMinWidth(110);
		columnDate.setMaxWidth(110);
		TableColumn<Reservation, String> columnHour = new TableColumn<>("Hora");
		columnHour.setCellValueFactory(new PropertyValueFactory<>("date"));
		columnHour.setMinWidth(70);
		columnHour.setMaxWidth(70);
		TableColumn<Reservation, String> columnName = new TableColumn<>("Associado");
		columnName.setCellValueFactory(new PropertyValueFactory<>("username"));
		columnName.setMinWidth(130);
		columnName.setMaxWidth(130);
		TableColumn<Reservation, String> columnSpace = new TableColumn<>("Espaco");
		columnSpace.setCellValueFactory(new PropertyValueFactory<>("space"));
		columnSpace.setMinWidth(70);
		columnSpace.setMaxWidth(70);
		TableColumn<Reservation, String> columnQntd = new TableColumn<>("Quantidade de pessoas");
		columnQntd.setCellValueFactory(new PropertyValueFactory<>("peopleqnt"));
		columnQntd.setMinWidth(150);
		columnQntd.setMaxWidth(150);
		table.getColumns().addAll(columnDate, columnHour, columnName, columnSpace, columnQntd);
		
		
		
		pane.getChildren().addAll(lbltittle, lblreservation, table);
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
