package view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import controllers.SpaceController;
import interfaces.ComandAssistence;
import interfaces.ComandProductor;
import interfaces.StrategyPane;
import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import models.Space;

public class SpaceDashboardView implements StrategyPane, ComandProductor {
	private ComandAssistence a;
	private Pane pane;
	private TableView<Space> table;
	private SpaceController spaceController;
	private Date date;
	private TextField buscarData;

	@SuppressWarnings("unchecked")
	public SpaceDashboardView() {
		date = new Date();
		this.pane = new Pane();
		spaceController = new SpaceController();
		
		Label lbltittle = new Label ("Espaços");
		lbltittle.relocate (250, 30);
		lbltittle.setFont(new Font("Arial",18));
		
		Label lblspace = new Label ("Espaços");
		lblspace.relocate (250, 180);
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
		columnDisp.setCellValueFactory(new PropertyValueFactory<>("disp"));
		columnDisp.setMinWidth(175);
		columnDisp.setMaxWidth(175);
		table.getColumns().addAll(columnSpace, columnQtd, columnDisp);
		this.refreshTable();
		
		//field
		buscarData = new TextField ("Buscar por Data");
		buscarData.relocate(235, 210);
		buscarData.setMinHeight(30);
		buscarData.setMinWidth(150);
		
		//button
		Button btnBuscar = new Button("Buscar");
		btnBuscar.relocate(400, 210);
		btnBuscar.setMinHeight(30);
		btnBuscar.setMinWidth(50);
		btnBuscar.setOnAction((e) -> {
			exeComand("SearchSpace");
		});

		pane.getChildren().addAll(table, lbltittle, lblspace, btnBuscar, buscarData);
	}

	public int spaceSelected() {
		return table.getSelectionModel().getSelectedItem().getId();
	}
	
	public void filterDate() {
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		try {
			this.date = df.parse(this.buscarData.getText());
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null, "Digite a data corretamente!", "ERROR", 0);
		}finally {
			refreshTable();
		}
	}

	public void refreshTable() {
		try {
			List<Space> list = spaceController.getAll();
			disp(list);
			table.setItems(FXCollections.observableArrayList(list));
		} catch (Exception e) {
			System.err.println(e);
		}
	}
	
	private void disp(List<Space> list) {
		try {
			for(Space var : list) {
				if(spaceController.disp(var.getId(), this.date)) {
					var.setDisp("Sim");
				}else {
					var.setDisp("Nao");
				}		
			}
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
