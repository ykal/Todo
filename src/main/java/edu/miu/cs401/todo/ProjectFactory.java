package edu.miu.cs401.todo;

import edu.miu.cs401.todo.model.Project;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class ProjectFactory implements Callback<ListView<Project>, ListCell<Project>> {
    @Override
    public ListCell<Project> call(ListView<Project> listview)
    {
        return new ProjectCell();
    }
}
