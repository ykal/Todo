package edu.miu.cs401.todo.model;

import java.awt.Toolkit;
import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;

import dataaccess.DatabaseException;
import edu.miu.cs401.todo.model.dao.ProjectDao;

 
public class Reminder {
	private int id;

	public int getId() {
		return id;
	}
	
    Toolkit toolkit;
 
    Timer timer;
 
    public Reminder() {
        toolkit = Toolkit.getDefaultToolkit();
        timer = new Timer();
        ProjectDao pDao = new ProjectDao();
        try {
			pDao.readProjects().stream()
					.flatMap(p -> p.getTasks().stream())
					.filter(t -> !t.isCompleted())
					.filter(t -> t.getDueDate() != null)
					.forEach(t -> timer.schedule(new TaskReminder(t), t.getId()+10*1000, 500));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DatabaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
 
    class TaskReminder extends TimerTask {
        int loop = 10;
        private Task task;
        public TaskReminder(Task task) {
        	this.task = task;
        }
 
        public void run() {
            if (loop > 0) {
                toolkit.beep();
                System.out.println(String.format("TaskName: %s\tDueDate: %s", task.getTitle(), task.getDueDate()));
                loop--;
            } else {
                toolkit.beep();
                timer.cancel();
            }
        }
    }
 
    public static void main(String args[]) {
        new Reminder();
        System.out.format("Task scheduled..!%n \n");
    }
}