package edu.miu.cs401.todo.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dataaccess.DataAccess;
import dataaccess.DatabaseException;
import edu.miu.cs401.todo.model.Label;

public class LabelDao implements TodoDao{

	private String select = "SELECT * FROM label;";

	private final static String SELECT = "select";
	
	private String queryType;
	private String query;
	private DataAccess da = DataAccessFactory.getDataAccess();
	private ResultSet resultSet;
	
	@Override
	public void buildQuery() throws DatabaseException {
		if(queryType == SELECT) query = select;
		else throw new RuntimeException("Label is read only!");
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
	
	public List<Label> readLabels() throws SQLException, DatabaseException{
		List<Label> labels = new ArrayList<>();
		da.createConnection(this);
		da.startTransaction();
		try {
			readAll();
        	da.commit();
			while(this.resultSet.next()) {
				String name = this.resultSet.getString("label_name");
				if(name.equals(Label.GREEN.name()))
					labels.add(Label.GREEN);
				else if(name.equals(Label.RED.name()))
					labels.add(Label.RED);
				else if(name.equals(Label.BLUE.name()))
					labels.add(Label.BLUE);
				else if(name.equals(Label.YELLOW.name()))
					labels.add(Label.YELLOW);
			}
		} catch (Exception e) {
			e.printStackTrace();
        	da.rollback();
        	throw (e);
        }  finally {
        	da.releaseConnection();
        }
		return labels;
	}
}
