package edu.miu.cs401.todo;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;

public class FxmlLoaderHelper {
    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static <T extends  Parent, S> ParentControllerPair<T, S> getController(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return new ParentControllerPair<>(fxmlLoader.load(), fxmlLoader.getController());
    }
}
