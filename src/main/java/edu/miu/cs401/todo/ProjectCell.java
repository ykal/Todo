package edu.miu.cs401.todo;

import edu.miu.cs401.todo.model.Project;
import javafx.scene.control.ListCell;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class ProjectCell extends ListCell<Project> {
    @Override
    public void updateItem(Project item, boolean empty)
    {
        super.updateItem(item, empty);

        int index = this.getIndex();
        String name = null;

        // Format name
        if (item == null || empty)
        {
        }
        else
        {
            name =  item.getTitle();
        }

        this.setText(name);
        Circle circle = new Circle(10);
        assert item != null;
        setGraphic(null);
    }
}
