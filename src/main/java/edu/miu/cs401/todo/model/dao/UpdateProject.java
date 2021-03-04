package edu.miu.cs401.todo.model.dao;

import java.util.List;

import edu.miu.cs401.todo.model.Task;

public class UpdateProject {
	private String description;
	private String title;
	private int id;
	private List<Task> tasks;
	
	public UpdateProject(String desc) {
		this.description = desc;
	}
	
	public UpdateProject(List<Task> tasks) {
		this.tasks = tasks;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getId() {
		return id;
	}
	
}
