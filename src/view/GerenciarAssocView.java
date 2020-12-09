package view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;

import controllers.UserController;
import interfaces.ComandAssistence;
import interfaces.ComandProductor;
import interfaces.StrategyPane;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
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
	
	public GerenciarAssocView () {
		this.userController = new UserController();
		pane = new Pane();
		pane = new Pane();
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
		txtpesquisa.setMinWidth(300);
		//botao
		Button btnSearch = new Button();
		btnSearch.setGraphic(lvSearchIcon);
		btnSearch.relocate(560, 60);
		btnSearch.setMaxHeight(20);
		btnSearch.setMaxWidth(20);
		btnSearch.setOnAction((e)->{
			exeComand("SearchAssociate");
		});
		
		Button btnAddAssociate = new Button("Adicionar Associado");
		btnAddAssociate.relocate(650,40);
		btnAddAssociate.setMinHeight(30);
		btnAddAssociate.setMinWidth(40);
		btnAddAssociate.setOnAction((e)->{
			exeComand("AddAssociate");
		});
		
		pane.getChildren().addAll(lblgerreserva,lblnome,inputNome,lbldatanasc,inputDatanasc,lblrg, inputRg,
				lblcpf, inputCpf, lblcontato, inputContato, lblendereco, inputEndereco, btnSearch,
				lbltiposocio, inputTiposocio,txtpesquisa, lblstatusmensalidade,inputStatusmensalidade, btnAddAssociate);
	}
	
	public void controlToAssociated() {
		try {
			int cpf = Integer.parseInt(txtpesquisa.getText());
			User user = this.userController.getById(cpf);
			if(user != null) {
				SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				inputNome.setText(user.getFirstName() + " " + user.getLastName());
				inputDatanasc.setText(df.format(user.getBirthdate()));
				inputRg.setText(this.formRg(user.getRg()));
				inputCpf.setText(this.formCpf(user.getCpf()));;
				inputContato.setText(this.formPhoneNumber(user.getPhoneNumber()));
				inputEndereco.setText(user.getAddress());
				inputTiposocio.setText("Colocar plan");
				inputStatusmensalidade.setText("Colocar Mensalidade");
			}
		}catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Digite apenas os numeros!", "ERROR", 0);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro interno!", "ERROR", 0);
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
	
	private String formCpf(int cpf) {
		String aux = "" + cpf;
		char [] vet = aux.toCharArray();
		return vet[0] + vet[1] + "." + vet[2] + vet[3] + vet[4] + "." + vet[5] + vet[6] + vet[7] + "-" + vet[8];
	}
	
	private String formRg(int rg) {
		String aux = "" + rg;
		char [] vet = aux.toCharArray();
		return vet[0] + vet[1] + vet[2] + "." + vet[3] + vet[4] + vet[5] + "." + vet[6] + vet[7] + vet[8] + "-" + vet[9];
	}
	
	private String formPhoneNumber(int phoneNumber) {
		String aux = "" + phoneNumber;
		char [] vet = aux.toCharArray();
		if(vet.length == 10) {
			return "(" + vet[0] + vet[1] + ") " + vet[2] + vet[3] + vet[4] + vet[5] + "-" + vet[6] + vet[7] + vet[8]  + vet[9];
		}else {
			return "(" + vet[0] + vet[1] + ") " + vet[2] + vet[3] + vet[4] + vet[5] + vet[6] + "-" + vet[7] + vet[8]  + vet[9] + vet[10];
		}
	}
	
}
