package edu.miu.cs401.todo;

import java.io.IOException;
import java.net.URL;
        import java.util.ResourceBundle;
        import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
        import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class ProjectViewController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="projectName"
    private Label projectName; // Value injected by FXMLLoader

    @FXML
    void showTaskForm(MouseEvent event)  {
       try {
           FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("base.fxml"));
//           BaseController baseController = (BaseController)FxmlLoaderHelper.getController("base");
//           AnchorPane taskForm =  (AnchorPane) FxmlLoaderHelper.loadFXML("taskForm");
//           AnchorPane contentContext = baseController.getContentContainer();
//           contentContext.getChildren().clear();
//           System.out.println(contentContext.getChildren());

       } catch (Exception e) {
           System.out.println("Error : " + e);
       }
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert projectName != null : "fx:id=\"projectName\" was not injected: check your FXML file 'projectView.fxml'.";

    }

    public void setTitle(String title) {
        this.projectName.setText(title);
    }
}

