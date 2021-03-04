package edu.miu.cs401.todo;

import edu.miu.cs401.todo.model.Project;
import edu.miu.cs401.todo.model.Task;
import javafx.scene.control.ListCell;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class TaskCell extends ListCell<Task> {
    @Override
    public void updateItem(Task item, boolean empty)
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
            name =  String.format("%s \t\t %s \t\t [ %s ]", item.getTitle(), item.getDueDate(),
            		item.isCompleted() ? "Completed!" : "");
	        if (item.isCompleted()) {
	        	this.setDisable(true);
	        	this.setStyle("-fx-background-color: #AAAAAA");
	        }
        }

        
        this.setText(name);
        Circle circle = new Circle(10);
        assert item != null;
        setGraphic(null);
    }
}
