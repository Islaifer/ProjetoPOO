package view;

import java.util.List;
import java.util.stream.Collectors;

import controllers.PaymentController;
import controllers.SubscriptionController;
import interfaces.ComandAssistence;
import interfaces.ComandProductor;
import interfaces.StrategyPane;
import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
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
	private CheckBox checkA;
	private CheckBox checkB;
	private SubscriptionController subscriptionController;
	private PaymentController paymentController;

	private TableView<Subscription> table;

	@SuppressWarnings("unchecked")
	public SubscriptionDashboardView() {
		this.subscriptionController = new SubscriptionController();
		this.paymentController = new PaymentController();
		this.pane = new Pane();
		// labels
		Label lbltittle = new Label("Mensalidades");
		lbltittle.relocate(235, 30);
		lbltittle.setFont(new Font("Arial", 20));

		Label lblsubscription = new Label("Mensalidades");
		lblsubscription.relocate(235, 180);
		lblsubscription.setFont(new Font("Arial", 14));

		Label lblfilteratrasados = new Label("Filtrar atrasados");
		lblfilteratrasados.relocate(550, 230);
		lblfilteratrasados.setFont(new Font("Arial", 10));
		checkA = new CheckBox();
		checkA.relocate(630, 228);

		Label lblfilterpendentes = new Label("Filtrar pendentes");
		lblfilterpendentes.relocate(650, 230);
		lblfilterpendentes.setFont(new Font("Arial", 10));
		checkB = new CheckBox();
		checkB.relocate(730, 228);
		// table
		table = new TableView<>();
		table.relocate(235, 250);
		table.setMinWidth(530);
		table.setMaxWidth(530);
		table.setMaxHeight(250);

		// colunas
		TableColumn<Subscription, String> columnDate = new TableColumn<>("Data do vencimento");
		columnDate.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
		columnDate.setMinWidth(150);
		columnDate.setMaxWidth(150);
		TableColumn<Subscription, String> columnStatus = new TableColumn<>("Status");
		columnStatus.setCellValueFactory(new PropertyValueFactory<>("statusName"));
		columnStatus.setMinWidth(80);
		columnStatus.setMaxWidth(80);
		TableColumn<Subscription, String> columnName = new TableColumn<>("Associado");
		columnName.setCellValueFactory(new PropertyValueFactory<>("username"));
		columnName.setMinWidth(130);
		columnName.setMaxWidth(130);
		TableColumn<Subscription, String> columnCpf = new TableColumn<>("CPF");
		columnCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
		columnCpf.setMinWidth(100);
		columnCpf.setMaxWidth(100);
		TableColumn<Subscription, String> columnValor = new TableColumn<>("Valor");
		columnValor.setCellValueFactory(new PropertyValueFactory<>("amount"));
		columnValor.setMinWidth(70);
		columnValor.setMaxWidth(70);
		table.getColumns().addAll(columnDate, columnStatus, columnName, columnCpf, columnValor);
		refreshTable();
		// campos

		TextField txtcpf = new TextField("Buscar por CPF");
		txtcpf.relocate(235, 210);
		txtcpf.setMinHeight(30);
		txtcpf.setMinWidth(150);

		// button
		Button btnPagar = new Button("Pagar");
		btnPagar.relocate(680, 510);
		btnPagar.setMinHeight(30);
		btnPagar.setMinWidth(80);
		btnPagar.setOnAction((e) -> {
			payTheSubscription();
		});

		Button btnGerar = new Button("Gerar mensalidade");
		btnGerar.relocate(550, 510);
		btnGerar.setMinHeight(30);
		btnGerar.setMinWidth(100);
		btnGerar.setOnAction((e) -> {
			exeComand("GerarMensalidades");
		});

		Button btnFiltrar = new Button("Filtrar");
		btnFiltrar.relocate(400, 210);
		btnFiltrar.setMinHeight(30);
		btnFiltrar.setMinWidth(50);
		btnFiltrar.setOnAction((e) -> {
			if (checkIfIsValidCpf(txtcpf.getText())) {
				runFilterWithCpf(checkA.isSelected(), checkB.isSelected(), txtcpf.getText());
			}else {
				runFilter(checkA.isSelected(), checkB.isSelected());
				System.out.println("tá aqui");
			}
		});

		pane.getChildren().addAll(lbltittle, lblsubscription, lblfilteratrasados, btnGerar, lblfilterpendentes, table,
				txtcpf, btnPagar, btnFiltrar, checkA, checkB);
	}

	private void runFilter(boolean isOverdue, boolean isPending) {
		refreshTable();
		List<Subscription> filteredTable = table.getItems();
		if (isPending && isOverdue) {
			filteredTable = filteredTable.stream().filter(
					subscription -> subscription.getStatus().getId() == 2 || subscription.getStatus().getId() == 1)
					.collect(Collectors.toList());
			table.setItems(FXCollections.observableArrayList(filteredTable));
		} else if (isPending) {
			filteredTable = filteredTable.stream().filter(subscription -> subscription.getStatus().getId() == 2)
					.collect(Collectors.toList());
			table.setItems(FXCollections.observableArrayList(filteredTable));
		} else if (isOverdue) {
			filteredTable = filteredTable.stream().filter(subscription -> subscription.getStatus().getId() == 1)
					.collect(Collectors.toList());
			table.setItems(FXCollections.observableArrayList(filteredTable));
		} else {
			refreshTable();
		}

	}

	private void runFilterWithCpf(boolean isOverdue, boolean isPending, String cpf) {
		refreshTable();
		List<Subscription> filteredTable = table.getItems();
		if (isPending && isOverdue) {
			filteredTable = filteredTable.stream().filter(
					subscription -> (subscription.getStatus().getId() == 2 || subscription.getStatus().getId() == 1) && subscription.getUser().getCpf() == Long.parseLong(cpf))
					.collect(Collectors.toList());
			table.setItems(FXCollections.observableArrayList(filteredTable));
		} else if (isPending) {
			filteredTable = filteredTable.stream().filter(subscription -> subscription.getStatus().getId() == 2 && subscription.getUser().getCpf() == Long.parseLong(cpf))
					.collect(Collectors.toList());
			table.setItems(FXCollections.observableArrayList(filteredTable));
		} else if (isOverdue) {
			filteredTable = filteredTable.stream().filter(subscription -> subscription.getStatus().getId() == 1 && subscription.getUser().getCpf() == Long.parseLong(cpf))
					.collect(Collectors.toList());
			table.setItems(FXCollections.observableArrayList(filteredTable));
		} else {
			filteredTable = filteredTable.stream().filter(subscription -> subscription.getUser().getCpf() == Long.parseLong(cpf))
					.collect(Collectors.toList());
			table.setItems(FXCollections.observableArrayList(filteredTable));
		}
	}

	private boolean checkIfIsValidCpf(String cpf) {
		try {
			if (cpf.length() == 11) {
				return true;
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}

	public void generateSubscriptions() {
		try {
			this.subscriptionController.post();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void payTheSubscription() {
		Subscription subscription;
		try {
			subscription = subscriptionController.getById(table.getSelectionModel().getSelectedItem().getId());
			paymentController.post(subscription);
			refreshTable();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void refreshTable() {
		try {
			table.setItems(FXCollections.observableArrayList(subscriptionController.getAll()));
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
		return this.pane;
	}
}