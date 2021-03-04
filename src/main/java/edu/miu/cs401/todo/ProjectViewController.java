package edu.miu.cs401.todo;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import dataaccess.DatabaseException;
import edu.miu.cs401.todo.model.Project;
import edu.miu.cs401.todo.model.Task;
import edu.miu.cs401.todo.model.dao.ProjectDao;
import edu.miu.cs401.todo.model.dao.TaskUpdate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class ProjectViewController {

    private BaseController baseController;
    private Project project;
    private Task selectedTask;


    public ObservableList<Task> tasks;


    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML
    private ListView<Task> tasksList;

    @FXML // fx:id="projectName"
    private Label projectName; // Value injected by FXMLLoader


    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize(BaseController baseController, Project project) {
    	tasks = FXCollections.observableArrayList(project.getTasks());
        this.baseController = baseController;
        this.project = project;
        this.projectName.setText(this.project.getTitle());
        assert projectName != null : "fx:id=\"projectName\" was not injected: check your FXML file 'projectView.fxml'.";
        this.tasksList.setCellFactory(new TaskFactory());
        // load tasks
        this.refreshTasksList(this.tasks);
    }

    public void setTitle(String title) {
        this.projectName.setText(title);
    }

    public void refreshTasksList(ObservableList<Task > tasks) {
        tasksList.getItems().clear();
        tasksList.getItems().addAll(tasks);
    }
    
    @FXML
    public void selectTask(MouseEvent event) throws IOException {
    	selectedTask = tasksList.getSelectionModel().getSelectedItem();
    }
    
    @FXML
    public void completeTask(ActionEvent event) throws IOException {
    	ProjectDao pDao = new ProjectDao();
    	try {
			pDao.updateTask(selectedTask, new TaskUpdate(true, selectedTask.getId()));
			List<Project> projects  = pDao.readProjects();
			List<Task> tsks = projects.stream().filter(p -> p.getId() == this.project.getId())
					.flatMap(p -> p.getTasks().stream())
					.collect(Collectors.toList());
			this.project = projects.stream()
					.filter(p -> p.getId() == this.project.getId())
					.findFirst().get();
			tasks = FXCollections.observableArrayList(tsks);
			this.refreshTasksList(tasks);
			this.baseController.refreshProjectsList(FXCollections.observableArrayList(projects));
		} catch (DatabaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}

