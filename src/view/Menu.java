package view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import interfaces.ComandAssistence;
import interfaces.ComandProductor;
import interfaces.StrategyPane;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class Menu implements StrategyPane, ComandProductor {
	private Pane pane;
	private Image logo;
	private ImageView lv;
	private ComandAssistence a;
	public Menu() {
		pane = new Pane();
		try {
			logo = new Image(new FileInputStream(new File("").getAbsolutePath() + "/images/logo.png"), 160, 100, false, false);
		} catch (FileNotFoundException e) {
			System.err.println(new File("").getAbsolutePath() + "/images/logo.png not found");
		}
		lv = new ImageView(logo);
		lv.relocate(20, 25);
		// Menu
		Rectangle bckgMenu = new Rectangle();
		bckgMenu.setWidth(200);
		bckgMenu.setHeight(600);
		bckgMenu.setFill(Color.valueOf("#DFDFDF"));
		Label dashboard = new Label("Dashboard");
		dashboard.setFont(new Font("Arial", 16));
		dashboard.relocate(60, 150);
		dashboard.setTextFill(Color.BLACK);
		dashboard.setOnMouseEntered((e) -> {
			dashboard.setTextFill(Color.BLUE);
		});
		dashboard.setOnMouseExited((e) -> {
			dashboard.setTextFill(Color.BLACK);
		});
		dashboard.setOnMouseClicked((e) -> {
			exeComand("dashboard");
		});
		Label reservation = new Label("Reserva");
		reservation.setFont(new Font("Arial", 16));
		reservation.relocate(68, 200);
		reservation.setTextFill(Color.BLACK);
		reservation.setOnMouseEntered((e) -> {
			reservation.setTextFill(Color.BLUE);
		});
		reservation.setOnMouseExited((e) -> {
			reservation.setTextFill(Color.BLACK);
		});
		reservation.setOnMouseClicked((e) -> {
			exeComand("reservation");
		});
		Label associated = new Label("Gerenciar Associados");
		associated.setFont(new Font("Arial", 16));
		associated.relocate(20, 250);
		associated.setTextFill(Color.BLACK);
		associated.setOnMouseEntered((e) -> {
			associated.setTextFill(Color.BLUE);
		});
		associated.setOnMouseExited((e) -> {
			associated.setTextFill(Color.BLACK);
		});
		associated.setOnMouseClicked((e) -> {
			exeComand("associated");
		});
		Label tuition = new Label("Gerenciar Mensalidades");
		tuition.setFont(new Font("Arial", 16));
		tuition.relocate(15, 300);
		tuition.setTextFill(Color.BLACK);
		tuition.setOnMouseEntered((e) -> {
			tuition.setTextFill(Color.BLUE);
		});
		tuition.setOnMouseExited((e) -> {
			tuition.setTextFill(Color.BLACK);
		});
		tuition.setOnMouseClicked((e) -> {
			exeComand("tuition");
		});
		
		pane.getChildren().addAll(bckgMenu, reservation, dashboard, tuition, associated, lv);
	}

	@Override
	public void setAssistence(ComandAssistence a) {
		this.a = a;

	}

	@Override
	public void exeComand(String cmd) {
		a.executeCommand(cmd);

	}

	@Override
	public Pane getPane() {
		return pane;
	}
}
