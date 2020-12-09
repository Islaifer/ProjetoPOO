package view;

import controllers.SpaceController;
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
import models.Space;

public class SpaceDashboardView implements StrategyPane, ComandProductor {
	private ComandAssistence a;
	private Pane pane;
	private TableView<Space> table;
	private SpaceController spaceController;

	@SuppressWarnings("unchecked")
	public SpaceDashboardView() {
		this.pane = new Pane();
		spaceController = new SpaceController();
		
		Label lbltittle = new Label ("Espa�os");
		lbltittle.relocate (250, 30);
		lbltittle.setFont(new Font("Arial",18));
		
		Label lblspace = new Label ("Espa�os");
		lblspace.relocate (250, 230);
		lblspace.setFont(new Font("Arial",13));
		
		table = new TableView<>();
		table.relocate(235, 250);
		table.setMinWidth(530);
		table.setMaxWidth(530);
		table.setMaxHeight(250);

		// colunas
		TableColumn<Space, String> columnSpace = new TableColumn<>("Espaco");
		columnSpace.setCellValueFactory(new PropertyValueFactory<>("name"));
		columnSpace.setMinWidth(175);
		columnSpace.setMaxWidth(175);
		TableColumn<Space, String> columnQtd = new TableColumn<>("Capacidade");
		columnQtd.setCellValueFactory(new PropertyValueFactory<>("capacity"));
		columnQtd.setMinWidth(175);
		columnQtd.setMaxWidth(175);
		TableColumn<Space, String> columnDisp = new TableColumn<>("Disponibilidade");
		columnDisp.setCellValueFactory(new PropertyValueFactory<>(""));
		columnDisp.setMinWidth(175);
		columnDisp.setMaxWidth(175);
		table.getColumns().addAll(columnSpace, columnDisp, columnQtd);
		this.refreshTable();

		pane.getChildren().addAll(table, lbltittle, lblspace);
	}

	public int spaceSelected() {
		return table.getSelectionModel().getSelectedItem().getId();
	}

	public void refreshTable() {
		try {
			table.setItems(FXCollections.observableArrayList(spaceController.getAll()));
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
