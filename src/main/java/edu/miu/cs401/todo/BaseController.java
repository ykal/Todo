package edu.miu.cs401.todo;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="leftPane"
    private AnchorPane leftPane; // Value injected by FXMLLoader

    @FXML // fx:id="contentContainer"
    private AnchorPane contentContainer; // Value injected by FXMLLoader

    @FXML
    private ListView<String> projectsList;

    public ObservableList<String> projects = FXCollections.<String>observableArrayList("Shopping", "Study", "Gym");


    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() throws IOException {
        assert leftPane != null : "fx:id=\"leftPane\" was not injected: check your FXML file 'base.fxml'.";
        assert contentContainer != null : "fx:id=\"contentContainer\" was not injected: check your FXML file 'base.fxml'.";
        assert projectsList != null : "fx:id=\"projectsList\" was not injected: check your FXML file 'base.fxml'.";
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
        AnchorPane taskForm = (AnchorPane) FxmlLoaderHelper.loadFXML("taskForm");
        this.contentContainer.getChildren().clear();
        this.contentContainer.getChildren().add(taskForm);
    }

    @FXML
    void onProjectSelected(MouseEvent event) throws IOException {
        String selectedProject = projectsList.getSelectionModel().getSelectedItem();
        showProjectView(selectedProject);
    }

    private void showProjectView(String selectedProject) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("projectView.fxml"));
        Parent parent = fxmlLoader.load();
        ProjectViewController controller = (ProjectViewController)fxmlLoader.getController();
        controller.setTitle(selectedProject);
        this.contentContainer.getChildren().clear();
        this.contentContainer.getChildren().add(parent);
    }

    public AnchorPane getContentContainer() {
         return this.contentContainer;
    }

    public void refreshProjectsList(ObservableList<String > projects) {
        projectsList.getItems().clear();
        projectsList.getItems().addAll(projects);
    }

}
