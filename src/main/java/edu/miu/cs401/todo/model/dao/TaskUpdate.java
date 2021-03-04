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
	private LocalDate updatedAt;
	private boolean isCompleted;
	private Label label;
	private Progress progress;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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
	}

	public Label getLabel() {
		return label;
	}

	public void setLabel(Label label) {
		this.label = label;
	}

	public Progress getProgress() {
		return progress;
	}

	public void setProgress(Progress progress) {
		this.progress = progress;
	}

	public TaskUpdate(boolean b, int id) {
		this.isCompleted = b;
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
