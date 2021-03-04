package edu.miu.cs401.todo;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import dataaccess.DatabaseException;
import edu.miu.cs401.todo.model.Project;
import edu.miu.cs401.todo.model.dao.ProjectDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


public class BaseController {

    private Project selectedProject;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="leftPane"
    private AnchorPane leftPane; // Value injected by FXMLLoader

    @FXML // fx:id="contentContainer"
    private AnchorPane contentContainer; // Value injected by FXMLLoader

    @FXML
    private ListView<Project> projectsList;

    public ObservableList<Project> projects;


    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() throws IOException {
    	this.loadProject();
        assert leftPane != null : "fx:id=\"leftPane\" was not injected: check your FXML file 'base.fxml'.";
        assert contentContainer != null : "fx:id=\"contentContainer\" was not injected: check your FXML file 'base.fxml'.";
        assert projectsList != null : "fx:id=\"projectsList\" was not injected: check your FXML file 'base.fxml'.";
        projectsList.setCellFactory(new ProjectFactory());
        System.out.println(this.projects);
        this.refreshProjectsList(this.projects);
        Pane content = (Pane) FxmlLoaderHelper.loadFXML("info");
        this.contentContainer.getChildren().add(content);
    }

    @FXML
    void showProjectForm(ActionEvent event) throws IOException {
        ParentControllerPair<AnchorPane, ProjectFormController> pair = FxmlLoaderHelper.getController("projectForm");
        this.contentContainer.getChildren().clear();
        pair.getController().initialize(this);
        this.contentContainer.getChildren().add(pair.getParent());
    }

    @FXML
    void showNewTaskForm(ActionEvent event) throws IOException {
    	if(this.selectedProject == null)
    		return;
        ParentControllerPair<Parent, TaskFormController> pair = FxmlLoaderHelper.getController("taskForm");
        this.contentContainer.getChildren().clear();
        pair.getController().initialize(this,this.selectedProject);
        this.contentContainer.getChildren().add(pair.getParent());
    }

    @FXML
    void onProjectSelected(MouseEvent event) throws IOException {
        Project selectedProject = projectsList.getSelectionModel().getSelectedItem();
        showProjectView(selectedProject);
    }
    
    @FXML
    void deleteProject(ActionEvent event) throws IOException {
        Project selectedProject = projectsList.getSelectionModel().getSelectedItem();
        ProjectDao pDao = new ProjectDao();
        try {
			pDao.deleteProject(selectedProject);
			projects.remove(selectedProject);
			this.refreshProjectsList(projects);
			this.contentContainer.getChildren().clear();
	        Pane content = (Pane) FxmlLoaderHelper.loadFXML("info");
	        this.contentContainer.getChildren().add(content);
		} catch (DatabaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void showProjectView(Project selectedProject) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("projectView.fxml"));
        Parent parent = fxmlLoader.load();
        this.selectedProject = selectedProject;
        ProjectViewController controller = (ProjectViewController)fxmlLoader.getController();
        controller.initialize(this, Optional.of(selectedProject).orElse(null));
        this.contentContainer.getChildren().clear();
        this.contentContainer.getChildren().add(parent);
    }

    public void loadProject() {
    	ProjectDao proDao = new ProjectDao();
    	try {
			projects = FXCollections.observableArrayList(proDao.readProjects());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DatabaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public AnchorPane getContentContainer() {
         return this.contentContainer;
    }

    public void refreshProjectsList(ObservableList<Project > projects) {
        projectsList.getItems().clear();
        projectsList.getItems().addAll(projects);
    }

}
