package com.example.application;

import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application extends AbstractJavaFxApplicationSupport {

	@Value("${ui.title:JavaFX приложение}")//
	private String windowTitle;

	private final ControllersConfiguration.ViewHolder view;

	@Autowired
	public Application(@Qualifier("mainView") ControllersConfiguration.ViewHolder view) {
		this.view = view;
	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle(windowTitle);
		stage.setScene(new Scene(view.getView()));
		stage.setResizable(true);
		stage.centerOnScreen();
		stage.show();
	}

	public static void main(String[] args) {
		launchApp(Application.class, args);
	}
}
