package edu.miu.cs401.todo.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import dataaccess.DataAccess;
import dataaccess.DatabaseException;
import edu.miu.cs401.todo.model.Project;
import edu.miu.cs401.todo.model.Task;

public class TaskDao implements TodoDao {
	private String insert = "INSERT INTO Task (label_name , proj_id, "
			+ "prog_id, title, description, due_date, created_at, updated_at, "
			+ "is_completed) "
			+"VALUES (" +
			"'" +"%s" + "'," +
			"'" +"%s" + "'," +
			"'" +"%s" + "'," +
			"'" +"%s" + "'," +
			"'" +"%s" + "'," +
			"'" +"%s" + "'," +
			"'" +"%s" + "'," +
			"'" +"%s" + "'," +
			"'" +"%d" + "')";
	private String insertSubTask = "INSERT INTO TaskSubTask (pid, cid)"
			+ "VALUES('%d', '%d')";
	private String select = "SELECT * FROM Task WHERE proj_id = %d;";
	private String selectSubTask = "SELECT * FROM TaskSubTask WHERE pid = %d;";
	private String delete = "DELETE FROM Task WHERE id='%d';";
	private String update = "UPDATE Task SET is_completed = "+ "'" + "%d" + "'" + " WHERE " +
			"id = " + "'" + "%d" + "'";
	private final static Logger LOG = Logger.getLogger(Project.class.getName());
	private final static String SELECT = "select";
	private final static String SELECT_SUB_TASK = "selectSubTask";
	private final static String INSERT_SUB_TASK = "insertSubTask";
	private final static String UPDATE = "update";
	private final static String INSERT = "insert";
	private final static String DELETE = "delete";
	private String queryType;
	private String query;
	private DataAccess da = DataAccessFactory.getDataAccess();
	private Task task;
	private int proj_id;
	private int task_id;
	private ResultSet resultSet;
	private Task subtask;
	private TaskUpdate taskUpdate;
	
	TaskDao() {}
	
	@Override
	public void buildQuery() throws DatabaseException {
		if(queryType.equals(INSERT)) {
			query = String.format(insert, task.getLabel().name(), 
					String.valueOf(proj_id), String.valueOf(task.getProgress().getId()),
					task.getTitle(), task.getDescription(),
					task.getDueDate().toString(), task.getCreatedAt().toString(),
					task.getUpdatedAt().toString(), task.isCompleted() ? 1 : 0);
		}
		else if(queryType.equals(SELECT)) {
			query = String.format(select,proj_id);
		}
		else if(queryType.equals(INSERT_SUB_TASK)) {
			query = String.format(insertSubTask, task.getId(), subtask.getId());
		}
		else if(queryType.equals(SELECT_SUB_TASK)) {
			query = String.format(selectSubTask, task_id);
		}
		else if(queryType.equals(DELETE)) {
			query = String.format(delete, this.task.getId());
		}
		else if(queryType.equals(UPDATE)) {
			query = String.format(update, taskUpdate.isCompleted() ? 1 : 0, taskUpdate.getId());
		}
	}

	@Override
	public String getQuery() {
		return query;
	}

	@Override
	public void populateEntity(ResultSet rs) throws DatabaseException {
		this.resultSet = rs;
	}
	
	private int insertTask() throws DatabaseException{
		this.queryType = INSERT;
		return da.save();
	}
	
	private int updateTask() throws DatabaseException{
		this.queryType = UPDATE;
		return da.save();
	}

	int insertTask(Task task, int proj_id) throws DatabaseException {
		this.task = task;
		this.proj_id = proj_id;
		da.createConnection(this);
		da.startTransaction();
		try {
			// create progress first;
        	int result = insertTask();
        	task.setId(result);
        	da.commit();
        	return result;
        } catch(DatabaseException e) {
        	LOG.warning("Attempting to rollback...");
        	da.rollback();
        	throw (e);
        }  finally {
        	da.releaseConnection();
        }
	}
	
	private int insertSubTask() throws DatabaseException{
		this.queryType = INSERT_SUB_TASK;
		return da.save();
	}
	
	int insertSubTask(List<Task> subTasks, Task task) throws DatabaseException {
		this.task = task;
		da.createConnection(this);
		da.startTransaction();
		try {
			int result=-1;
			for (Task sbtask : subTasks) {
				this.subtask = sbtask;
				result = insertSubTask();
			}
        	da.commit();
        	return result;
        } catch(DatabaseException e) {
        	LOG.warning("Attempting to rollback...");
        	da.rollback();
        	throw (e);
        }  finally {
        	da.releaseConnection();
        }
	}
	
	private void readAll() throws DatabaseException{
		this.queryType = SELECT;
		da.read();
	}
	
	private void readSubTasks() throws DatabaseException{
		this.queryType = SELECT_SUB_TASK;
		da.read();
	}
	
	List<Task> readTasks(Project proj) throws DatabaseException, SQLException{
		List<Task> tasks = new ArrayList<>();
		try {
			this.proj_id = proj.getId();
			da.createConnection(this);
			da.startTransaction();
			readAll();
			da.commit();
			while(resultSet.next()) {
				Task tsk = proj.addTask(resultSet.getInt("id"),
						resultSet.getString("title"),
						resultSet.getString("description"),
						LocalDate.parse(resultSet.getString("due_date")),
						LocalDate.parse(resultSet.getString("created_at")),
						LocalDate.parse(resultSet.getString("updated_at")),
						resultSet.getBoolean("is_completed"));
				tasks.add(tsk);
			}
		} catch (DatabaseException e) {
			e.printStackTrace();
			da.rollback();
			throw (e);
		} finally {
			da.releaseConnection();
		}
		return readSubTasks(tasks);
	}
	
	List<Task> readSubTasks(List<Task> tasks) throws DatabaseException, SQLException{
		try {
			for(Task t : tasks) {
				this.task_id = t.getId();
				da.createConnection(this);
				da.startTransaction();
				readSubTasks();
				da.commit();
				while(resultSet.next()) {
					int cid = resultSet.getInt("cid");
					for(Task ct : tasks) {
						if(ct.getId() == cid) {
							t.addSubTask(ct);
						}
					}
				}
			}
		} catch (DatabaseException e) {
			e.printStackTrace();
			da.rollback();
			throw (e);
		} finally {
			da.releaseConnection();
		}
		return tasks;
	}
	
	private int deleteTask() throws DatabaseException{
		this.queryType = DELETE;
		return da.delete();
	}
	
	public int deleteTask(Task task) throws DatabaseException {
		this.task = task;
		try {
			da.createConnection(this);
			da.startTransaction();
			int result=deleteTask();
        	da.commit();
        	return result;
        } catch(DatabaseException e) {
        	LOG.warning("Attempting to rollback...");
        	da.rollback();
        	throw (e);
        }  finally {
        	da.releaseConnection();
        }
	}
	
	public int updateTask(Task task, TaskUpdate update) throws DatabaseException {
		this.task = task;
		this.taskUpdate = update;
		try {
			da.createConnection(this);
			da.startTransaction();
			int result=updateTask();
        	da.commit();
        	return result;
        } catch(DatabaseException e) {
        	LOG.warning("Attempting to rollback...");
        	da.rollback();
        	throw (e);
        }  finally {
        	da.releaseConnection();
        }
	}
}






