package com.example.application;

import com.example.application.ui.MainController;
import java.io.IOException;
import java.io.InputStream;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ControllersConfiguration {

	@Bean(name = "mainView")
	public ViewHolder getMainView() throws IOException {
		return loadView("fxml/main.fxml");
	}

	@Bean
	public MainController getMainController() throws IOException {
		return (MainController) getMainView().getController();
	}

	protected ViewHolder loadView(String url) throws IOException {
		try (InputStream fxmlStream = getClass().getClassLoader().getResourceAsStream(url)) {
			FXMLLoader loader = new FXMLLoader();
			loader.load(fxmlStream);
			return new ViewHolder(loader.getRoot(), loader.getController());
		}
	}

	@AllArgsConstructor
	@Data
	public class ViewHolder {

		private Parent view;
		private Object controller;
	}

}
