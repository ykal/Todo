package edu.miu.cs401.todo.model;

public class Progress {
	private int id=1; // THIS IS AUTO GENERATED REMOVE.
	private double percentage;

	private Progress(double percentage) {
		super();
		this.percentage = percentage;
	}
	
	public int getId() {
		return id;
	}

	public double getPercentage() {
		return percentage;
	}

	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}

	static Progress createProgressPercent(double percent) {
		return new Progress(percent);
	}

	public double increamentPercentage(double p) {
		this.percentage += p * 100;
		return this.percentage;
	}
}
