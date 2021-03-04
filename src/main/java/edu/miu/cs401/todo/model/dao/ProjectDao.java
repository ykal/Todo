package edu.miu.cs401.todo.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import dataaccess.DataAccess;
import dataaccess.DatabaseException;
import edu.miu.cs401.todo.model.Project;
import edu.miu.cs401.todo.model.Task;

public class ProjectDao implements TodoDao {
	private String insert = "INSERT INTO Project (title, description) "
			+"VALUES (" +
			"'" +"%s" + "'," +
			"'" +"%s" + "')";
	private String update = "UPDATE Project SET description = "+ "'" + "%s" + "'" + " WHERE " +
			"title = " + "'" + "%s" + "'";
	private String select = "SELECT * FROM Project;";
	private static final Logger LOG = Logger.getLogger(Project.class.getName());
	private final static String SELECT = "select";
	private final static String UPDATE = "update";
	private final static String INSERT = "insert";
	private String queryType;
	private String query;
	private DataAccess da = DataAccessFactory.getDataAccess();
	private Project proj;
	private UpdateProject updateProject;
	private ResultSet resultSet;
	
	@Override
	public void buildQuery() throws DatabaseException {
		if(queryType.equals(INSERT)) {
			query = String.format(insert, proj.getTitle(), proj.getDescription());
		}
		if(queryType.equals(SELECT)) {
			query = select;
		}
	}

	@Override
	public String getQuery() {
		return query;
	}

	@Override
	public void populateEntity(ResultSet rs) throws DatabaseException {
		this.resultSet = rs	;
	}
	
	private int insertProject() throws DatabaseException{
		this.queryType = INSERT;
		return da.save();
	}
	
	private void readAll() throws DatabaseException{
		this.queryType = SELECT;
		da.read();
	}
	
	public int insertTasks(Project p) throws DatabaseException {
		this.proj = p;
		TaskDao tDao = new TaskDao();
		int result=-1;
		for (Task task : this.proj.getTasks()) {
			result = tDao.insertTask(task, p.getId());
		}
		for(Task task : this.proj.getTasks()) {
			result = tDao.insertSubTask(task.getSubTasks(), task);
		}
		return result;
	}
	
	public int appendTasks(Project proj, UpdateProject update) throws DatabaseException {
		this.proj = proj;
		TaskDao tDao = new TaskDao();
		int result=-1;
		for(Task task : update.getTasks())
			result = tDao.insertTask(task, this.proj.getId());
		for(Task task : update.getTasks())
			result = tDao.insertSubTask(task.getSubTasks(), task);
		return result;
	}
	
	public int insertProject(Project p) throws DatabaseException {
		this.proj = p;
		da.createConnection(this);
		da.startTransaction();
		try {
        	int result = insertProject();
        	da.commit();
        	proj.setId(result);
        	result = insertTasks(this.proj);
        	return result;
        } catch(DatabaseException e) {
        	LOG.warning("Attempting to rollback...");
        	da.rollback();
        	throw (e);
        }  finally {
        	da.releaseConnection();
        }
	}
	
	public List<Project> readProjects() throws SQLException, DatabaseException{
		List<Project> projects = new ArrayList<>();
		da.createConnection(this);
		da.startTransaction();
		readAll();
		da.commit();
		while(resultSet.next()) {
			Project proj = new Project(resultSet.getInt("id"),
					resultSet.getString("title"),
					resultSet.getString("description"));
			TaskDao tdao = new TaskDao();
			tdao.readTasks(proj);
			projects.add(proj);
		}
		return projects;
	}

}






