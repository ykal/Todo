package edu.miu.cs401.todo;

import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class TaskFormController {

    private String project;
    private BaseController baseController;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="taskNameInput"
    private TextField taskNameInput; // Value injected by FXMLLoader

    @FXML // fx:id="taskDescriptionInput"
    private TextArea taskDescriptionInput; // Value injected by FXMLLoader

    @FXML // fx:id="taskDueDateInput"
    private DatePicker taskDueDateInput; // Value injected by FXMLLoader

    @FXML
    void addTask(MouseEvent event) {
        String taskName = taskNameInput.getText();
        String taskDescription = taskDescriptionInput.getText();
        LocalDate dueDate = taskDueDateInput.getValue();
        // Todo: insert the task to db
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize(BaseController controller, String project) {
        assert taskNameInput != null : "fx:id=\"taskNameInput\" was not injected: check your FXML file 'taskForm.fxml'.";
        assert taskDescriptionInput != null : "fx:id=\"taskDescriptionInput\" was not injected: check your FXML file 'taskForm.fxml'.";
        assert taskDueDateInput != null : "fx:id=\"taskDueDateInput\" was not injected: check your FXML file 'taskForm.fxml'.";

    }
}

