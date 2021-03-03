package edu.miu.cs401.todo;

/**
 * Sample Skeleton for 'base.fxml' Controller Class
 */

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;


public class BaseController {
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="leftPane"
    private AnchorPane leftPane; // Value injected by FXMLLoader

    @FXML // fx:id="contentContainer"
    private AnchorPane contentContainer; // Value injected by FXMLLoader

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() throws IOException {
        assert leftPane != null : "fx:id=\"leftPane\" was not injected: check your FXML file 'base.fxml'.";
        assert contentContainer != null : "fx:id=\"contentContainer\" was not injected: check your FXML file 'base.fxml'.";

        Pane content = (Pane) loadFXML("info");
        this.contentContainer.getChildren().add(content);
    }


    @FXML
    void showProjectForm(ActionEvent event) throws IOException {
         AnchorPane projectForm = (AnchorPane) loadFXML("projectForm");
        this.contentContainer.getChildren().clear();
        this.contentContainer.getChildren().add(projectForm);
    }


    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }


}
