package view;

import interfaces.ComandAssistence;
import interfaces.ComandProductor;
import interfaces.StrategyPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class GerenciarAssocView implements StrategyPane, ComandProductor{
	private ComandAssistence a;
	private Pane pane;
	
	public GerenciarAssocView () {
		pane = new Pane();
		
		//labels
		Label lblgerreserva = new Label ("Dados do Associado");
		lblgerreserva.relocate (250, 30);
		lblgerreserva.setFont(new Font("Arial",20));
		Label lblnome = new Label("Nome Completo");
		lblnome.relocate(250,110);
		lblnome.setFont(Font.font("Arial",FontWeight.BOLD, 14));
		Label inputNome = new Label("Lindinha");
		inputNome.relocate(250,130);
		inputNome.setFont(new Font("Arial", 14));
		Label lbldatanasc = new Label ("Data de Nascimento");
		lbldatanasc.relocate (500, 110);
		lbldatanasc.setFont(Font.font("Arial",FontWeight.BOLD, 14));
		Label inputDatanasc = new Label ("15/01/2002");
		inputDatanasc.relocate (500, 130);
		inputDatanasc.setFont(new Font("Arial", 14));
		Label lblrg = new Label ("RG");
		lblrg.relocate(250, 160);
		lblrg.setFont(Font.font("Arial", FontWeight.BOLD, 14));
		Label inputRg = new Label ("37.250.473-5");
		inputRg.relocate(250, 180);
		inputRg.setFont(new Font("Arial", 14));
		Label lblcpf = new Label ("CPF");
		lblcpf.relocate(500, 160);
		lblcpf.setFont(Font.font("Arial",FontWeight.BOLD, 14));
		Label inputCpf = new Label ("317.819.208-50");
		inputCpf.relocate(500, 180);
		inputCpf.setFont(new Font("Arial", 14));
		Label lblcontato = new Label ("Contato");
		lblcontato.relocate(250, 210);
		lblcontato.setFont(Font.font("Arial",FontWeight.BOLD, 14));
		Label inputContato = new Label ("(11) 995475-1212");
		inputContato.relocate(250, 230);
		inputContato.setFont(new Font("Arial", 14));
		Label lblendereco = new Label ("Endereço Completo");
		lblendereco.relocate(500, 210);
		lblendereco.setFont(Font.font("Arial",FontWeight.BOLD, 14));
		Label inputEndereco = new Label ("Rua xxxx, xx, Sampa - SP");
		inputEndereco.relocate(500, 230);
		inputEndereco.setFont(new Font("Arial", 14));
		Label lbldependentes = new Label ("Dependentes");
		lbldependentes.relocate(250, 260);
		lbldependentes.setFont(Font.font("Arial",FontWeight.BOLD, 14));
		Label inputDependentes = new Label ("Nenhum");
		inputDependentes.relocate(250, 280);
		inputDependentes.setFont(new Font("Arial", 14));
		Label lbltiposocio = new Label ("Tipo de Sócio");
		lbltiposocio.relocate(500, 260);
		lbltiposocio.setFont(Font.font("Arial",FontWeight.BOLD, 14));
		Label inputTiposocio = new Label ("Gold");
		inputTiposocio.relocate(500, 280);
		inputTiposocio.setFont(new Font("Arial", 14));
		Label lblstatusmensalidade = new Label ("Status mensalidade");
		lblstatusmensalidade.relocate(250, 310);
		lblstatusmensalidade.setFont(Font.font("Arial",FontWeight.BOLD, 14));
		Label inputStatusmensalidade = new Label ("Pendente");
		inputStatusmensalidade.relocate(250, 330);
		inputStatusmensalidade.setFont(new Font("Arial", 14));
		
		//TextFields
		TextField txtpesquisa = new TextField ("Buscar pelo CPF");
		txtpesquisa.relocate(250, 60);
		txtpesquisa.setMinHeight(30);
		txtpesquisa.setMinWidth(300);
		
		pane.getChildren().addAll(lblgerreserva,lblnome,inputNome,lbldatanasc,inputDatanasc,lblrg, inputRg,
				lblcpf, inputCpf, lblcontato, inputContato, lblendereco, inputEndereco, lbldependentes, inputDependentes,
				lbltiposocio, inputTiposocio,txtpesquisa, lblstatusmensalidade,inputStatusmensalidade);
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
	
}
