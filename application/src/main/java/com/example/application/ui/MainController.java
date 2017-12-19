package com.example.application.ui;

import com.example.application.model.Student;
import com.example.application.repository.StudentRepository;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

@Slf4j
public class MainController {

	private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	@FXML
	private TableView<Student> table;
	@FXML
	private TextField txtName = new TextField("Денис");
	@FXML
	private TextField txtBirthDay;

	@Autowired
	private StudentRepository studentRepository;

	private ObservableList<Student> data;

	@FXML
	public void initialize() {
	}

	@SuppressWarnings("unchecked")
	@PostConstruct
	public void init() {
		List<Student> contacts = studentRepository.findAll();
		data = FXCollections.observableArrayList(contacts);

		// Столбцы таблицы
		TableColumn<Student, String> idColumn = new TableColumn<>("ID");
		idColumn.setCellValueFactory(new PropertyValueFactory<>("code"));

		TableColumn<Student, String> nameColumn = new TableColumn<>("Имя");
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

		TableColumn<Student, String> phoneColumn = new TableColumn<>("Дата рождения");
		phoneColumn.setCellValueFactory(new PropertyValueFactory<>("birthDay"));

		TableColumn deleteColumn = new TableColumn("Удалить");
		deleteColumn.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));

		Callback<TableColumn<Student, String>, TableCell<Student, String>> cellFactory =
			(param -> new TableCell<Student, String>() {

				final Button btn = new Button("Удалить");

				@Override
				public void updateItem(String item, boolean empty) {
					super.updateItem(item, empty);
					if (empty) {
						setGraphic(null);
						setText(null);
					} else {
						btn.setOnAction(event -> {
							Student student = getTableView().getItems().get(getIndex());
							log.info("Delete student: {}", student);
							studentRepository.delete(student);

							data.remove(student);
						});
						setGraphic(btn);
						setText(null);
					}
				}
			});

		deleteColumn.setCellFactory(cellFactory);

		table.getColumns().setAll(idColumn, nameColumn, phoneColumn, deleteColumn);

		table.setItems(data);
	}

	@FXML
	public void addStudent() {
		String name = txtName.getText();
		String birthDay = txtBirthDay.getText();
		if (StringUtils.isEmpty(name) || StringUtils.isEmpty(birthDay)) {
			return;
		}

		Student student = new Student();
		student.setDescription(name);
		student.setBirthDay(LocalDate.parse(birthDay, DATE_FORMAT));
		student = studentRepository.save(student);

		data.add(student);

		txtName.setText("");
		txtBirthDay.setText("");
	}

}
