package edu.miu.cs401.todo.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Project {
	private int id;
	private String title;
	private String description;
	private List<Task> tasks;
	
	public Project(String title, String description) {
		super();
		this.title = title;
		this.description = description;
		this.tasks = new ArrayList<>();
	}
	
	public Project(int id, String title, String description) {
		this(title, description);
		this.id = id;
	}
	
	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public List<Task> getTasks(){
		return this.tasks;
	}
	
	public Task addTask(String title, String description, LocalDate dueDate,
			LocalDate createdAt, LocalDate updatedAt,
			boolean isCompleted) {
		Task tsk = Task.createTask(title, description, dueDate,
				createdAt, updatedAt, isCompleted);
		this.tasks.add(tsk);
		return tsk;
	}
	
	public Task addTask(int id, String title, String description, LocalDate dueDate,
			LocalDate createdAt, LocalDate updatedAt,
			boolean isCompleted) {
		Task tsk = Task.createTask(id, title, description, dueDate,
				createdAt, updatedAt, isCompleted);
		this.tasks.add(tsk);
		return tsk;
	}
	
	public Task addSubTask(Task ptask, String title, String description, LocalDate dueDate,
			LocalDate createdAt, LocalDate updatedAt,
			boolean isCompleted) {
		Task tsk = Task.createTask(title, description, dueDate,
				createdAt, updatedAt, isCompleted);
		ptask.addSubTask(ptask);
		return tsk;
	}
	
	@Override
	public String toString() {
		String str =  String.format("\nProjectName: %s", getTitle());
		for(Task t : getTasks()) {
			str += String.format("\n\t%s", t.toString());
		}
		return str;
	}

	public void setId(int id) {
		this.id = id;
	}
}
