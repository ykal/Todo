package edu.miu.cs401.todo;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.Collections;
import java.util.ResourceBundle;

import dataaccess.DatabaseException;
import edu.miu.cs401.todo.model.Project;
import edu.miu.cs401.todo.model.dao.ProjectDao;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;


public class ProjectFormController {

    private BaseController baseController;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="container"
    private AnchorPane container; // Value injected by FXMLLoader

    @FXML // fx:id="projectNameInput"
    private TextField projectNameInput; // Value injected by FXMLLoader

    @FXML // fx:id="projectNameInput"
    private ColorPicker projectThemeInput; // Value injected by FXMLLoader

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize(BaseController controller) {
        assert container != null : "fx:id=\"container\" was not injected: check your FXML file 'projectForm.fxml'.";
        this.setBaseController(controller);
        System.out.println("========= setting base controller =============" + this.baseController.projects);
    }

    @FXML
    void addProject(MouseEvent event) {
        System.out.println(this.projectNameInput.getText() + " --- " + this.projectThemeInput.getValue());
        ObservableList<Project> projects = this.baseController.projects;
        ProjectDao proDao = new ProjectDao();
        Project project = new Project(this.projectNameInput.getText(), "");
        int id;
		try {
			id = proDao.insertProject(project);
	        project.setId(id);
	        projects.add(project);
	        this.baseController.refreshProjectsList(projects);
	        this.baseController.getContentContainer().getChildren().clear();
	        this.baseController.showProjectView(project);
		} catch (DatabaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public void setBaseController(BaseController controller) {
        this.baseController = controller;
    }
}
