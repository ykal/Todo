package edu.miu.cs401.todo;

import edu.miu.cs401.todo.model.Project;
import edu.miu.cs401.todo.model.Task;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class TaskFactory implements Callback<ListView<Task>, ListCell<Task>> {
    @Override
    public ListCell<Task> call(ListView<Task> listview)
    {
        return new TaskCell();
    }
}
