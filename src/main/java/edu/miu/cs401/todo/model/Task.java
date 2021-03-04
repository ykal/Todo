package edu.miu.cs401.todo.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Task {
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
	private Task parent;
	// NO REFERENCE TO THE PROJECT, ADD LATER IF NEEDED. 
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public LocalDate getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDate updatedAt) {
		this.updatedAt = updatedAt;
	}

	public boolean isCompleted() {
		return isCompleted;
	}

	public void setCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
		this.parent.increamentProgress();
	}

	public Label getLabel() {
		return label;
	}

	public void setLabel(Label label) {
		this.label = label;
	}

	public List<Task> getSubTasks() {
		return subTasks;
	}

	public void addSubTask(Task subTask) {
		this.subTasks .add(subTask);
	}

	public LocalDate getCreatedAt() {
		return createdAt;
	}

	public Progress getProgress() {
		return progress;
	}

	public Task getParent() {
		return parent;
	}

	private Task(String title, String description, LocalDate dueDate,
			LocalDate createdAt, LocalDate updatedAt,
			boolean isCompleted) {
		super();
		this.title = title;
		this.description = description;
		this.dueDate = dueDate;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.isCompleted = isCompleted;
		this.progress = Progress.createProgressPercent(0);
		subTasks = new ArrayList<>();
	}
	
	private Task(int id, String title, String description, LocalDate dueDate, LocalDate createdAt,
			LocalDate updatedAt, boolean isCompleted) {
		this(title, description, dueDate, createdAt, updatedAt, isCompleted);
		this.id = id;
	}

	Progress increamentProgress() {
		this.progress.increamentPercentage(1 / this.subTasks.size());
		return this.progress;
	}
	
	static Task createTask(String title, String description, LocalDate dueDate,
			LocalDate createdAt, LocalDate updatedAt,
			boolean isCompleted) {
		return new Task(title, description, dueDate, createdAt, updatedAt, isCompleted);
	}
	
	@Override
	public boolean equals(Object ob) {
		if (ob == null) return false;
		if (ob.getClass() != this.getClass())
			return false;
		return ((Task)ob).getId() == getId();
	}

	static Task createTask(int id, String title, String description, LocalDate dueDate, LocalDate createdAt,
			LocalDate updatedAt, boolean isCompleted) {
		return new Task(id, title, description, dueDate, createdAt, updatedAt, isCompleted);
	}
	
	@Override
	public String toString() {
		String str = this.getTitle();
		for (Task t : getSubTasks()) {
			str += String.format("\n\t %s", t.toString());
		}
		return str;
	}

	public void setId(int id) {
		this.id = id;
	}
}






