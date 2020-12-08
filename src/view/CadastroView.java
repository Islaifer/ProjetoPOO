package view;

import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;

import controllers.UserController;
import interfaces.ComandAssistence;
import interfaces.ComandProductor;
import interfaces.StrategyPane;
import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import models.User;

public class CadastroView implements StrategyPane, ComandProductor {
	private ComandAssistence a;
	private Pane pane;
	private TextField txtname;
	private TextField txtsobrenome;
	private TextField txtrg;
	private TextField txtcpf;
	private TextField txtdata;
	private TextField txttel;
	private TextField txtend;
	private ChoiceBox cbplan; 
	private UserController userController;
	
	public CadastroView() {

		pane = new Pane();
		userController = new UserController();

		Label lblcadastro = new Label("Cadastro de Usuário");
		lblcadastro.relocate(290, 30);
		lblcadastro.setFont(new Font("Arial", 18));
		Label lbldadosassoc = new Label("Dados do Associado");
		lbldadosassoc.relocate(250, 100);
		lbldadosassoc.setFont(new Font("Arial", 14));
		Label lbldadosend = new Label("Cadastro de Endereço");
		lbldadosend.relocate(250, 300);
		lbldadosend.setFont(new Font("Arial", 14));
		Label lblplano = new Label("Plano");
		lblplano.relocate(250, 400);
		lblplano.setFont(new Font("Arial", 14));

		TextField txtnome = new TextField("Nome");
		txtnome.relocate(250, 130);
		txtnome.setMinHeight(30);
		txtnome.setMinWidth(30);
		TextField txtsobrenome = new TextField("Sobrenome");
		txtsobrenome.relocate(500, 130);
		txtsobrenome.setMinHeight(30);
		txtsobrenome.setMinWidth(30);
		TextField txtrg = new TextField("RG");
		txtrg.relocate(250, 180);
		txtrg.setMinHeight(30);
		txtrg.setMinWidth(30);
		TextField txtcpf = new TextField("CPF");
		txtcpf.relocate(500, 180);
		txtcpf.setMinHeight(30);
		txtcpf.setMinWidth(30);
		TextField txtdata = new TextField("Data de Nascimento");
		txtdata.relocate(250, 230);
		txtdata.setMinHeight(30);
		txtdata.setMinWidth(30);
		TextField txttel = new TextField("Telefone para contato");
		txttel.relocate(500, 230);
		txttel.setMinHeight(30);
		txttel.setMinWidth(30);
		TextField txtend = new TextField("Endereço");
		txtend.relocate(250, 330);
		txtend.setMinHeight(30);
		txtend.setMinWidth(400);

		ChoiceBox<String> cbplano = new ChoiceBox<String>();
		cbplano.setItems(FXCollections.observableArrayList("Simples", "Master", "Blaster"));
		cbplano.relocate(250, 430);

		Button btnCadastrar = new Button("Cadastrar");
		btnCadastrar.relocate(400, 500);
		btnCadastrar.setMinHeight(40);
		btnCadastrar.setMinWidth(300);
		btnCadastrar.setOnAction((e) -> {
			exeComand("Cadastrar");
		});

		Button btnVoltar = new Button("Voltar");
	  btnVoltar.relocate(230,30);
	  btnVoltar.setMinHeight(30);
	  btnVoltar.setMinWidth(40);
	  btnVoltar.setOnAction((e)->{
		  exeComand("associated");
	  });
	
	  pane.getChildren().addAll(lblcadastro, lbldadosassoc, lbldadosend, lblplano, txtnome, txtsobrenome, txtcpf, txtrg, 
			txtend, txttel, txtdata, cbplano, btnCadastrar, btnVoltar);
	}

	public void controlToAssociated() {
		try {
			
			User user = this.userController.getById(0);
			if (user != null) {
				SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				txtname.setText(user.getFirstName() + " " + user.getLastName());
				txtdata.setText(df.format(user.getBirthdate()));
				txtrg.setText(""+ user.getRg());
				txtcpf.setText("" +user.getCpf());
				txttel.setText("" +user.getPhoneNumber());
				txtend.setText("" +user.getAddress());
				
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Digite apenas os numeros!", "ERROR", 0);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro interno!", "ERROR", 0);
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
