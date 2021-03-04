package edu.miu.cs401.todo.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dataaccess.DataAccess;
import dataaccess.DatabaseException;
import edu.miu.cs401.todo.model.Label;
import edu.miu.cs401.todo.model.Theme;

public class ThemeDao implements TodoDao{

	private String select = "SELECT * FROM Theme;";

	private final static String SELECT = "select";
	
	private String queryType;
	private String query;
	private DataAccess da = DataAccessFactory.getDataAccess();
	private ResultSet resultSet;
	
	@Override
	public void buildQuery() throws DatabaseException {
		if(queryType == SELECT) query = select;
		else throw new RuntimeException("Theme is read only!");
	}

	@Override
	public void populateEntity(ResultSet resultSet) throws DatabaseException {
		this.resultSet = resultSet;
	}

	@Override
	public String getQuery() {
		return query;
	}
	
	private void readAll() throws DatabaseException {
		queryType = SELECT;
		da.read();
	} 
	
	public List<Theme> readLabels() throws SQLException, DatabaseException{
		List<Theme> themes = new ArrayList<>();
		da.createConnection(this);
		da.startTransaction();
		try {
			readAll();
        	da.commit();
			while(this.resultSet.next()) {
				String name = this.resultSet.getString("theme_name");
				if(name.equals(Theme.GREEN.name()))
					themes.add(Theme.GREEN);
				else if(name.equals(Theme.RED.name()))
					themes.add(Theme.RED);
				else if(name.equals(Theme.PINK.name()))
					themes.add(Theme.PINK);
				else if(name.equals(Theme.YELLOW.name()))
					themes.add(Theme.YELLOW);
				else if(name.equals(Theme.TEAL.name()))
					themes.add(Theme.TEAL);
				else if(name.equals(Theme.PURPLE.name()))
					themes.add(Theme.PURPLE);
			}
		} catch (Exception e) {
			e.printStackTrace();
        	da.rollback();
        	throw (e);
        }  finally {
        	da.releaseConnection();
        }
		return themes;
	}
}
