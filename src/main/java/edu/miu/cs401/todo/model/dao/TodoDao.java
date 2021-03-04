package edu.miu.cs401.todo.model.dao;

import dataaccess.Dao;

public interface TodoDao extends Dao {
    default String getDbUrl() {
    	return "jdbc:mysql:///todo";
    }
    
    default String[] getLogin() {
    	return new String[] {"todo", "todo"};
    }
}
