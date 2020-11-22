package view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import interfaces.ComandAssistence;
import interfaces.ComandProductor;
import interfaces.StrategyPane;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class MainView implements StrategyPane, ComandProductor{
	private Image logo;
	private ImageView lv;
	private Pane pane;
	private Button btnEnter;
	private ComandAssistence a;
	
	public MainView() {
		pane = new Pane();
		try {
			logo = new Image(new FileInputStream(new File("").getAbsolutePath() + "/images/logo.png"), 630, 389, false, false);
		} catch (FileNotFoundException e) {
			System.err.println(new File("").getAbsolutePath() + "/images/logo.png not found");
		}
		lv = new ImageView(logo);
		lv.relocate(90, 60);
		btnEnter = new Button("Entrar");
		btnEnter.relocate(220, 500);
		btnEnter.setMinWidth(361.5);
		btnEnter.setMinHeight(71.25);
		btnEnter.setOnAction((e) -> {
			exeComand("Enter");
		});
		pane.getChildren().addAll(lv, btnEnter);
	}

	@Override
	public Pane getPane() {
		return this.pane;
	}

	@Override
	public void setAssistence(ComandAssistence a) {
		this.a = a;
	}

	@Override
	public void exeComand(String cmd) {
		this.a.executeCommand(cmd);
	}
}
