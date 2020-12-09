package view;

import controllers.SpaceController;
import interfaces.ComandAssistence;
import interfaces.ComandProductor;
import interfaces.StrategyPane;
import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import models.Space;

public class SpaceDashboardView implements StrategyPane, ComandProductor {
	private ComandAssistence a;
	private Pane pane;
	private TableView<Space> table;
	private SpaceController spaceController;

	public SpaceDashboardView() {

		this.refreshTable();
		this.pane = new Pane();

		table = new TableView<>();
		table.relocate(235, 250);
		table.setMinWidth(530);
		table.setMaxWidth(530);
		table.setMaxHeight(250);

		// colunas
		TableColumn<Space, String> columnSpace = new TableColumn<>("Espaco");
		columnSpace.setCellValueFactory(new PropertyValueFactory<>("space"));
		columnSpace.setMinWidth(110);
		columnSpace.setMaxWidth(110);
		TableColumn<Space, String> columnQtd = new TableColumn<>("Capacidade");
		columnQtd.setCellValueFactory(new PropertyValueFactory<>("peopleqtd"));
		columnQtd.setMinWidth(110);
		columnQtd.setMaxWidth(110);
		TableColumn<Space, String> columnDisp = new TableColumn<>("Disponibilidade");
		columnDisp.setCellValueFactory(new PropertyValueFactory<>(""));
		columnDisp.setMinWidth(110);
		columnDisp.setMaxWidth(110);
		table.getColumns().addAll(columnSpace, columnDisp, columnQtd);

		pane.getChildren().addAll(table);
	}

	public int spaceSelected() {
		return table.getSelectionModel().getSelectedItem().getId();
	}

	public void refreshTable() {
		try {
			table.setItems(FXCollections.observableArrayList(spaceController.getAll()));
		} catch (Exception e) {
			System.err.println("Deu ruim");
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
