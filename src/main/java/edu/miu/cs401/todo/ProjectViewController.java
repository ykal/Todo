package edu.miu.cs401.todo;

import java.io.IOException;
import java.net.URL;
        import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class ProjectViewController {

    private BaseController baseController;
    private String project;


    public ObservableList<String> tasks = FXCollections.<String>observableArrayList("Shopping", "Study", "Gym");


    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML
    private ListView<String> tasksList;

    @FXML // fx:id="projectName"
    private Label projectName; // Value injected by FXMLLoader


    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize(BaseController baseController, String project) {
        this.baseController = baseController;
        this.project = project;
        this.projectName.setText(this.project);
        assert projectName != null : "fx:id=\"projectName\" was not injected: check your FXML file 'projectView.fxml'.";
        // load tasks
        this.refreshTasksList(this.tasks);
    }

    public void setTitle(String title) {
        this.projectName.setText(title);
    }

    public void refreshTasksList(ObservableList<String > tasks) {
        tasksList.getItems().clear();
        tasksList.getItems().addAll(tasks);
    }
}

