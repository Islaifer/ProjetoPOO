package view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;

import controllers.PaymentController;
import controllers.UserController;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import models.Payment;
import models.User;

public class GerenciarAssocView implements StrategyPane, ComandProductor{
	private ComandAssistence a;
	private Pane pane;
	private Label inputNome;
	private Label inputDatanasc;
	private Label inputRg;
	private Label inputCpf;
	private Label inputContato;
	private Label inputEndereco;
	private Label inputTiposocio;
	private Label inputStatusmensalidade;
	private UserController userController;
	private TextField txtpesquisa;
	private Image searchIcon;
	private ImageView lvSearchIcon;
	private User user;
	private PaymentController paymentController;
	private TableView <Payment> table;
	
	public GerenciarAssocView () {
		this.userController = new UserController();
		paymentController = new PaymentController();
		pane = new Pane();
		pane = new Pane();
		
		table = new TableView<>();
		table.relocate(235,350);
		table.setMinWidth(500);
		table.setMaxWidth(500);
		table.setMaxHeight(200);
		
		try {
			searchIcon = new Image(new FileInputStream(new File("").getAbsolutePath() + "/images/search.png"), 20, 20, false, false);
		} catch (FileNotFoundException e) {
			System.err.println(new File("").getAbsolutePath() + "/images/search.png not found");
		}
		lvSearchIcon = new ImageView(searchIcon);
		
		//labels
		Label lblgerreserva = new Label ("Dados do Associado");
		lblgerreserva.relocate (250, 30);
		lblgerreserva.setFont(new Font("Arial",20));
		Label lblnome = new Label("Nome Completo");
		lblnome.relocate(250,110);
		lblnome.setFont(Font.font("Arial",FontWeight.BOLD, 14));
		inputNome = new Label("");
		inputNome.relocate(250,130);
		inputNome.setFont(new Font("Arial", 14));
		Label lbldatanasc = new Label ("Data de Nascimento");
		lbldatanasc.relocate (500, 110);
		lbldatanasc.setFont(Font.font("Arial",FontWeight.BOLD, 14));
		inputDatanasc = new Label ("");
		inputDatanasc.relocate (500, 130);
		inputDatanasc.setFont(new Font("Arial", 14));
		Label lblrg = new Label ("RG");
		lblrg.relocate(250, 160);
		lblrg.setFont(Font.font("Arial", FontWeight.BOLD, 14));
		inputRg = new Label ("");
		inputRg.relocate(250, 180);
		inputRg.setFont(new Font("Arial", 14));
		Label lblcpf = new Label ("CPF");
		lblcpf.relocate(500, 160);
		lblcpf.setFont(Font.font("Arial",FontWeight.BOLD, 14));
		inputCpf = new Label ("");
		inputCpf.relocate(500, 180);
		inputCpf.setFont(new Font("Arial", 14));
		Label lblcontato = new Label ("Contato");
		lblcontato.relocate(250, 210);
		lblcontato.setFont(Font.font("Arial",FontWeight.BOLD, 14));
		inputContato = new Label ("");
		inputContato.relocate(250, 230);
		inputContato.setFont(new Font("Arial", 14));
		Label lblendereco = new Label ("Endereço Completo");
		lblendereco.relocate(500, 210);
		lblendereco.setFont(Font.font("Arial",FontWeight.BOLD, 14));
		inputEndereco = new Label ("");
		inputEndereco.relocate(500, 230);
		inputEndereco.setFont(new Font("Arial", 14));
		Label lbltiposocio = new Label ("Tipo de Sócio");
		lbltiposocio.relocate(250, 260);
		lbltiposocio.setFont(Font.font("Arial",FontWeight.BOLD, 14));
		inputTiposocio = new Label ("");
		inputTiposocio.relocate(250, 280);
		inputTiposocio.setFont(new Font("Arial", 14));
		Label lblstatusmensalidade = new Label ("Status mensalidade");
		lblstatusmensalidade.relocate(500, 260);
		lblstatusmensalidade.setFont(Font.font("Arial",FontWeight.BOLD, 14));
		inputStatusmensalidade = new Label ("");
		inputStatusmensalidade.relocate(500, 280);
		inputStatusmensalidade.setFont(new Font("Arial", 14));
		
		//TextFields
		txtpesquisa = new TextField ("Buscar pelo CPF");
		txtpesquisa.relocate(250, 60);
		txtpesquisa.setMinHeight(30);
		txtpesquisa.setMinWidth(100);
		//botao
		Button btnSearch = new Button();
		btnSearch.setGraphic(lvSearchIcon);
		btnSearch.relocate(420, 60);
		btnSearch.setMaxHeight(20);
		btnSearch.setMaxWidth(20);
		btnSearch.setOnAction((e)->{
			exeComand("SearchAssociate");
		});
		
		Button btnAddAssociate = new Button("Novo Associado");
		btnAddAssociate.relocate(550,30);
		btnAddAssociate.setMinHeight(30);
		btnAddAssociate.setMinWidth(40);
		btnAddAssociate.setOnAction((e)->{
			exeComand("AddAssociate");
		});
		
		Button btnAttAssociate = new Button("Atualizar");
		btnAttAssociate.relocate(680,30);
		btnAttAssociate.setMinHeight(30);
		btnAttAssociate.setMinWidth(40);
		btnAttAssociate.setOnAction((e)->{
			exeComand("AttAssociate");
		});
		
		Label lblpayments = new Label ("Histórico de Pagamentos");
		lblpayments.relocate(250, 320);
		lblpayments.setFont(Font.font("Arial", 14));
		
		TableColumn<Payment, String> columnDate = new TableColumn<>("Data");
		columnDate.setCellValueFactory(new PropertyValueFactory<>("date"));
		columnDate.setMinWidth(150);
		columnDate.setMaxWidth(150);
		TableColumn<Payment, String> columnName = new TableColumn<>("Associado");
		columnName.setCellValueFactory(new PropertyValueFactory<>("user"));
		columnName.setMinWidth(180);
		columnName.setMaxWidth(180);
		TableColumn<Payment, String> columnPrice = new TableColumn<>("Valor");
		columnPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
		columnPrice.setMinWidth(180);
		columnPrice.setMaxWidth(180);
		this.refreshTable();
		table.getColumns().addAll(columnDate, columnName, columnPrice);
	
		pane.getChildren().addAll(lblgerreserva,lblnome,inputNome,lbldatanasc,inputDatanasc,lblrg, inputRg,
				lblcpf, inputCpf, lblcontato, inputContato, lblendereco, inputEndereco, btnSearch,lblpayments,
				lbltiposocio, inputTiposocio,txtpesquisa, lblstatusmensalidade,inputStatusmensalidade, btnAddAssociate, btnAttAssociate,table);
	}
	
	public void refreshTable() {
		try {
			table.setItems(FXCollections.observableArrayList(paymentController.getAll()));
					} catch (Exception e) {
			System.err.println(e);
		}
	}
	
	public void controlToAssociated() {
		try {
			long cpf = Long.parseLong(txtpesquisa.getText());
			user = this.userController.getByCPF(cpf);
			if(user != null) {
				SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				inputNome.setText(user.getFirstName() + " " + user.getLastName());
				inputDatanasc.setText(df.format(user.getBirthdate()));
				inputRg.setText(this.formRg(user.getRg()));
				inputCpf.setText(this.formCpf(user.getCpf()));;
				inputContato.setText(this.formPhoneNumber(user.getPhoneNumber()));
				inputEndereco.setText(user.getAddress());
				inputTiposocio.setText(user.getPlan().getName());
				inputStatusmensalidade.setText("Colocar Mensalidade");
			}
		}catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Digite apenas os numeros!", "ERROR", 0);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro interno!", "ERROR", 0);
		}
	}
	
	public int targetAssociated() {
		if(user != null) {
			return user.getId();
		}else {
			return 0;
		}
	}
	
	@Override
	public void setAssistence(ComandAssistence a) {
		// TODO Auto-generated method stub
		this.a=a;
		
	}
	@Override
	public void exeComand(String cmd) {
		// TODO Auto-generated method stub
		this.a.executeCommand(cmd);
	}
	@Override
	public Pane getPane() {
		// TODO Auto-generated method stub
		return pane;
	}
	
	private String formCpf(long cpf) {
		String aux = Long.toString(cpf);
		char [] vet = aux.toCharArray();	
		if(vet.length == 11) {
			return aux.substring(0, 3) + "." + aux.substring(3, 6) + "." + aux.substring(6, 9) + "-" + aux.substring(9, 11);
		}else {
			return 0 + aux.substring(0, 2) + "." + aux.substring(2, 5) + "." + aux.substring(5, 8) + "-" + aux.substring(8, 10);
		}
	}
	
	private String formRg(long rg) {
		String aux = "" + rg;
		char [] vet = aux.toCharArray();
		if(vet.length == 9) {
			return aux.substring(0, 2) + "." + aux.substring(2, 5) + "." + aux.substring(5, 8) + "-" + aux.substring(8);
		}else {
			return 0 + aux.substring(0) + "." + aux.substring(1, 4) + "." + aux.substring(4, 7) + "-" + aux.substring(7);
		}
	}
	
	private String formPhoneNumber(long phoneNumber) {
		String aux = "" + phoneNumber;
		char [] vet = aux.toCharArray();
		if(vet.length == 10) {
			return "(" + aux.substring(0, 2) + ") " + aux.substring(2, 6) + "-" + aux.substring(6, 10);
		}else {
			return "(" + aux.substring(0, 2) + ") " + aux.substring(2, 7) + "-" + aux.substring(6, 11);
		}
	}
	
}
