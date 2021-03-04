package edu.miu.cs401.todo.model.dao;

import java.time.LocalDate;
import java.util.List;

import edu.miu.cs401.todo.model.Label;
import edu.miu.cs401.todo.model.Progress;
import edu.miu.cs401.todo.model.Task;

public class TaskUpdate {
	private int id;
	private String title;
	private String description;
	private LocalDate dueDate;
	private LocalDate createdAt;
	private LocalDate updatedAt;
	private boolean isCompleted;
	public static final Label DEFAULT_LABEL = Label.GREEN;
	
	private Label label = DEFAULT_LABEL;
	private Progress progress;
	private List<Task> subTasks;
	
	public TaskUpdate(String desc) {
		this.description = desc;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
