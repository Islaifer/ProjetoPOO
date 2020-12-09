package view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

import controllers.PlanController;
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
import models.Plan;
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
	private ChoiceBox<String> cbplano; 
	private UserController userController;
	
	public CadastroView() {
		
		this.seedCb();
		pane = new Pane();
		userController = new UserController();

		Label lblcadastro = new Label("Cadastro de Usuario");
		lblcadastro.relocate(290, 30);
		lblcadastro.setFont(new Font("Arial", 18));
		Label lbldadosassoc = new Label("Dados do Associado");
		lbldadosassoc.relocate(250, 100);
		lbldadosassoc.setFont(new Font("Arial", 14));
		Label lbldadosend = new Label("Cadastro de Endereco");
		lbldadosend.relocate(250, 300);
		lbldadosend.setFont(new Font("Arial", 14));
		Label lblplano = new Label("Plano");
		lblplano.relocate(250, 400);
		lblplano.setFont(new Font("Arial", 14));

		txtname = new TextField("Nome");
		txtname.relocate(250, 130);
		txtname.setMinHeight(30);
		txtname.setMinWidth(30);
		txtsobrenome = new TextField("Sobrenome");
		txtsobrenome.relocate(500, 130);
		txtsobrenome.setMinHeight(30);
		txtsobrenome.setMinWidth(30);
		txtrg = new TextField("RG");
		txtrg.relocate(250, 180);
		txtrg.setMinHeight(30);
		txtrg.setMinWidth(30);
		txtcpf = new TextField("CPF");
		txtcpf.relocate(500, 180);
		txtcpf.setMinHeight(30);
		txtcpf.setMinWidth(30);
		txtdata = new TextField("Data de Nascimento");
		txtdata.relocate(250, 230);
		txtdata.setMinHeight(30);
		txtdata.setMinWidth(30);
		txttel = new TextField("Telefone para contato");
		txttel.relocate(500, 230);
		txttel.setMinHeight(30);
		txttel.setMinWidth(30);
		txtend = new TextField("Endereco");
		txtend.relocate(250, 330);
		txtend.setMinHeight(30);
		txtend.setMinWidth(400);

		

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
	
	  pane.getChildren().addAll(lblcadastro, lbldadosassoc, lbldadosend, lblplano, txtname, txtsobrenome, txtcpf, txtrg, 
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
	
	public void associatedToControl() {
		try {
			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			String firstName = txtname.getText();
			String lastName = txtsobrenome.getText();
			long rg = Long.parseLong(txtrg.getText());
			long cpf = Long.parseLong(txtcpf.getText());
			Date birthday = df.parse(txtdata.getText());
			long tel = Long.parseLong(txttel.getText());
			String address = txtend.getText();
			int idPlan = idPlane(cbplano.getValue());
			userController.post(firstName, lastName, rg, cpf, birthday, tel, address, idPlan);
			txtname.setText("Nome");
			txtsobrenome.setText("Sobrenome");
			txtrg.setText("RG");
			txtcpf.setText("CPF");
			txtdata.setText("Data de Nascimento");
			txttel.setText("Telefone para contato");
			txtend.setText("Endereco");
			JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso ^-^");
		}catch(NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Digite apenas numeros no rg e cpf!", "ERROR", 0);
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null, "Data inválida!", "ERROR", 0);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro interno!", "ERROR", 0);
		}
	}
	
	private int idPlane(String plan) {
		PlanController planController = new PlanController();
		try {
			List<Plan> listPlan = planController.getAll();
			for(Plan var : listPlan) {
				if(plan.equals(var.getName())) {
					return var.getId();
				}
			}
			return 0;
		} catch (Exception e) {
			return 0;
		}
	}
	
	public void seedCb() {
		PlanController planController = new PlanController();
		try {
			List<Plan> listPlan = planController.getAll();
			List<String> list = new LinkedList<String>();
			for(Plan var : listPlan) {
				list.add(var.getName());
			}
			cbplano = new ChoiceBox<String>();
			cbplano.setItems(FXCollections.observableArrayList(list));
			cbplano.relocate(250, 430);
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
