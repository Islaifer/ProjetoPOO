package view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

import controllers.ReservationController;
import controllers.SpaceController;
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
import models.Space;
import models.Reservation;
public class ReservasView implements StrategyPane, ComandProductor {
	private ComandAssistence a;
	private Pane pane;
	private TextField txtcpf;
	private TextField txtqtd;
	private TextField txtdata;
	private ChoiceBox<String> cbespaco;
	private ChoiceBox<String> cbhorarios;
	private ReservationController reservationController;

	public ReservasView() {
		// Programa��o da tela
		this.seedSpace();
		pane = new Pane();
		reservationController = new ReservationController();
		
		// Labels
		Label lblcadreserva = new Label("Cadastro de Reserva");
		lblcadreserva.relocate(290, 30);
		lblcadreserva.setFont(new Font("Arial", 18));
		Label lblreserva = new Label("Dados da Reserva");
		lblreserva.relocate(250, 100);
		lblreserva.setFont(new Font("Arial", 14));
		Label lblcliente = new Label("Dados do Cliente");
		lblcliente.relocate(250, 200);
		lblcliente.setFont(new Font("Arial", 14));
		Label lblhorario = new Label("Hora da Reserva");
		lblhorario.relocate(250, 300);
		lblhorario.setFont(new Font("Arial", 14));

		// TextFields
		txtcpf = new TextField("CPF do Associado");
		txtcpf.relocate(250, 230);
		txtcpf.setMinHeight(30);
		txtcpf.setMinWidth(30);
		txtqtd = new TextField("Quantidade de Pessoas");
		txtqtd.relocate(500, 230);
		txtqtd.setMinHeight(30);
		txtqtd.setMinWidth(30);
		txtdata = new TextField("Data da Reserva");
		txtdata.relocate(500, 130);
		txtdata.setMinHeight(30);
		txtdata.setMinWidth(30);

		// Choiceboxes
		cbhorarios = new ChoiceBox<String>();
		cbhorarios.setItems(FXCollections.observableArrayList("08:00", "10:00", "12:00", "14:00", "16:00", "18:00",
				"20:00", "Dia inteiro"));
		cbhorarios.relocate(250, 330);

		// Botao
		Button btnReservar = new Button("Reservar");
		btnReservar.relocate(400, 500);
		btnReservar.setMinHeight(40);
		btnReservar.setMinWidth(300);
		btnReservar.setOnAction((e) -> {
			exeComand("Reservar");
		});

		Button btnVoltar = new Button("Voltar");
		btnVoltar.relocate(230, 30);
		btnVoltar.setMinHeight(30);
		btnVoltar.setMinWidth(40);
		btnVoltar.setOnAction((e) -> {
			exeComand("reservation");
		});

		// Coloca todos os objetos na tela
		pane.getChildren().addAll(lblreserva, lblcliente, lblhorario, lblcadreserva, txtcpf, txtqtd, txtdata, cbespaco,
				cbhorarios, btnReservar, btnVoltar);

	}

	public void reservationToControl() {
		try {
			int cpfUser = Integer.parseInt(this.txtcpf.getText());
			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy - hh:mm");
			Date date = df.parse(this.txtdata.getText() + " - " + this.cbhorarios.getValue());
			String spaceName = this.cbhorarios.getValue();
			int peopleqnt = Integer.parseInt(this.txtqtd.getText());
			this.reservationController.post(cpfUser, date, spaceName, peopleqnt);
			this.txtcpf.setText("CPF do Associado");
			this.txtdata.setText("Data da Reserva");
			this.txtqtd.setText("Quantidade de Pessoas");
			this.cbespaco.setValue("");
			this.cbhorarios.setValue("");
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Erro ao digitar rg ou quantidade de pessoas!", "ERROR", 0);
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null, "Erro ao digitar data!", "ERROR", 0);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro interno!", "ERROR", 0);
		}
	}

	public void controlToReservation(int id) {
		try {
			Reservation reservation = this.reservationController.getById(id);
			if (reservation != null) {
				SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				txtcpf.setText("" + reservation.getUser().getCpf() );
				txtdata.setText(df.format(reservation.getDate()));
				txtqtd.setText("" + reservation.getPeopleqnt());
				cbespaco.setValue("" + reservation.getSpaceName());
				cbhorarios.setValue(reservation.getHour());

			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Digite apenas os numeros!", "ERROR", 0);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro interno!", "ERROR", 0);
		}
	}

	public void seedSpace() {
		SpaceController spaceController = new SpaceController();
		try {
			List<Space> listSpace = spaceController.getAll();
			List<String> list = new LinkedList<String>();
			for (Space var : listSpace) {
				list.add(var.getName());
			}
			cbespaco = new ChoiceBox<String>();
			cbespaco.setItems(FXCollections.observableArrayList(list));
			cbespaco.relocate(250, 130);
		} catch (Exception e) {
			System.err.println("Deu ruim");
		}

	}

	// Indica que a main executar� os comandos
	@Override
	public void setAssistence(ComandAssistence a) {
		this.a = a;

	}

	// Envia o comando a depender do bot�o acionado
	@Override
	public void exeComand(String cmd) {
		this.a.executeCommand(cmd);

	}

	// Retorna a tela
	@Override
	public Pane getPane() {
		return pane;
	}

}
