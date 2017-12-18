package com.example.application.ui;

import com.example.application.model.Student;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.annotation.PostConstruct;
import org.springframework.util.StringUtils;

public class MainController {

	// Инъекции JavaFX
	@FXML
	private TableView<Student> table;
	@FXML
	private TextField txtName;
	@FXML
	private TextField txtPhone;
	@FXML
	private TextField txtEmail;

	private ObservableList<Student> data;

	@FXML
	public void initialize() {	}

	@SuppressWarnings("unchecked")
	@PostConstruct
	public void init() {
		List<Student> contacts = new ArrayList<>();
		data = FXCollections.observableArrayList(contacts);

		// Столбцы таблицы
		TableColumn<Student, String> idColumn = new TableColumn<>("ID");
		idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

		TableColumn<Student, String> nameColumn = new TableColumn<>("Имя");
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

		TableColumn<Student, String> phoneColumn = new TableColumn<>("Телефон");
		phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));

		TableColumn<Student, String> emailColumn = new TableColumn<>("E-mail");
		emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

		table.getColumns().setAll(idColumn, nameColumn, phoneColumn, emailColumn);

		// Данные таблицы
		table.setItems(data);
	}

	@FXML
	public void addContact() {
		String name = txtName.getText();
		String phone = txtPhone.getText();
		String email = txtEmail.getText();
		if (StringUtils.isEmpty(name) || StringUtils.isEmpty(phone) || StringUtils.isEmpty(email)) {
			return;
		}

		Student student = new Student();
		data.add(student);

		txtName.setText("");
		txtPhone.setText("");
		txtEmail.setText("");
	}

}
