package edu.miu.cs401.todo.model.dao;

import dataaccess.DataAccessSubsystemFacade;

public class DataAccessFactory {
	public static dataaccess.DataAccess getDataAccess() {
		return new DataAccessSubsystemFacade();
	}
}
