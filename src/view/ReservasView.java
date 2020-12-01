package view;

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

public class ReservasView implements StrategyPane, ComandProductor {
	private ComandAssistence a;
	private Pane pane;
		
	public ReservasView() {
		//Programa��o da tela
		pane = new Pane();
		
		//Labels
		Label lblcadreserva = new Label ("<- Cadastro de Reserva");
		lblcadreserva.relocate (250, 30);
		lblcadreserva.setFont(new Font("Arial",18));
		Label lblreserva = new Label("Dados da Reserva");
		lblreserva.relocate(250,100);
		lblreserva.setFont(new Font("Arial", 14));
		Label lblcliente = new Label ("Dados do Cliente");
		lblcliente.relocate (250, 200);
		lblcliente.setFont(new Font("Arial", 14));
		Label lblhorario = new Label ("Hora da Reserva");
		lblhorario.relocate(250, 300);
		lblhorario.setFont(new Font("Arial", 14));
		
		
		//TextFields
		TextField txtrg = new TextField ("RG do Associado");
		txtrg.relocate(250, 230);
		txtrg.setMinHeight(30);
		txtrg.setMinWidth(30);
		TextField txtqtd = new TextField ("Quantidade de Pessoas");
		txtqtd.relocate(500, 230);
		txtqtd.setMinHeight(30);
		txtqtd.setMinWidth(30);
		TextField txtdata = new TextField ("Data da Reserva");
		txtdata.relocate(500, 130);
		txtdata.setMinHeight(30);
		txtdata.setMinWidth(30);
		
		//Choiceboxes
		ChoiceBox <String> cbespaco = new ChoiceBox <String> ();
		cbespaco.setItems(FXCollections.observableArrayList("Quadra futsal 1","Quadra futsal 2", "Quadra volei","Churrasqueira Norte", "Churrasqueira Sul"));
		cbespaco.relocate(250, 130);
		ChoiceBox <String> cbhorarios = new ChoiceBox <String> ();
		cbhorarios.setItems(FXCollections.observableArrayList("08:00","10:00","12:00","14:00","16:00","18:00","20:00", "Dia inteiro"));
		cbhorarios.relocate(250, 330);
		
		//Botao
		Button btnReservar = new Button("Reservar");
		btnReservar.relocate(400,500);
		btnReservar.setMinHeight(40);
		btnReservar.setMinWidth(300);
		btnReservar.setOnAction((e)->{
			exeComand("Reservar");
		});
		//Coloca todos os objetos na tela
		pane.getChildren().addAll(lblreserva, lblcliente, lblhorario, lblcadreserva, txtrg, txtqtd, txtdata, cbespaco, cbhorarios, btnReservar);
		
	}
	
	//Indica que a main executar� os comandos
	@Override
	public void setAssistence(ComandAssistence a) {
		this.a=a;
		
	}
	//Envia o comando a depender do bot�o acionado
	@Override
	public void exeComand(String cmd) {
		this.a.executeCommand(cmd);
		
	}
	
	//Retorna a tela
	@Override
	public Pane getPane() {
		return pane;
	}
	

}
