package view;

import interfaces.ComandAssistence;
import interfaces.ComandProductor;
import interfaces.StrategyPane;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import models.Subscription;

public class SubscriptionDashboardView implements StrategyPane, ComandProductor {
	private ComandAssistence a;
	private Pane pane;

	@SuppressWarnings("unchecked")
	public SubscriptionDashboardView() {
		this.pane = new Pane();
		// labels
		Label lbltittle = new Label("Mensalidades");
		lbltittle.relocate(235, 30);
		lbltittle.setFont(new Font("Arial", 18));

		Label lblsubscription = new Label("Mensalidades");
		lblsubscription.relocate(235, 180);
		lblsubscription.setFont(new Font("Arial", 13));
		
		Label lblfilteratrasados = new Label ("Filtrar atrasados");
		lblfilteratrasados.relocate(450, 230);
		lblfilteratrasados.setFont(new Font("Arial", 10));
		
		Label lblfilterpendentes = new Label ("Filtrar pendentes");
		lblfilterpendentes.relocate(550, 230);
		lblfilterpendentes.setFont(new Font("Arial", 10));
		//table
		TableView<Subscription> table = new TableView<>();
		// table.setItems(FXCollections.observableArrayList(teste));
		table.relocate(235, 250);
		table.setMinWidth(530);
		table.setMaxWidth(530);
		table.setMaxHeight(250);

		// colunas
		TableColumn<Subscription, String> columnDate = new TableColumn<>("Data do vencimento");
		columnDate.setCellValueFactory(new PropertyValueFactory<>("valid"));
		columnDate.setMinWidth(150);
		columnDate.setMaxWidth(150);
		TableColumn<Subscription, String> columnStatus = new TableColumn<>("Status");
		columnStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
		columnStatus.setMinWidth(80);
		columnStatus.setMaxWidth(80);
		TableColumn<Subscription, String> columnName = new TableColumn<>("Associado");
		columnName.setCellValueFactory(new PropertyValueFactory<>("username"));
		columnName.setMinWidth(130);
		columnName.setMaxWidth(130);
		TableColumn<Subscription, String> columnCpf = new TableColumn<>("CPF");
		columnCpf.setCellValueFactory(new PropertyValueFactory<>("userCpf"));
		columnCpf.setMinWidth(100);
		columnCpf.setMaxWidth(100);
		TableColumn<Subscription, String> columnValor = new TableColumn<>("Valor");
		columnValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
		columnValor.setMinWidth(70);
		columnValor.setMaxWidth(70);
		table.getColumns().addAll(columnDate, columnStatus, columnName, columnCpf, columnValor);
		
		//campos
		
		TextField txtcpf = new TextField ("Buscar por CPF");
		txtcpf.relocate(235, 210);
		txtcpf.setMinHeight(30);
		txtcpf.setMinWidth(150);
		
		//button
		Button btnPagar = new Button("Pagar");
		btnPagar.relocate(680, 210);
		btnPagar.setMinHeight(30);
		btnPagar.setMinWidth(40);
		btnPagar.setOnAction((e) -> {
			exeComand("pagar");
		});
		
		pane.getChildren().addAll(lbltittle, lblsubscription, lblfilteratrasados, lblfilterpendentes, table, txtcpf, btnPagar);
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