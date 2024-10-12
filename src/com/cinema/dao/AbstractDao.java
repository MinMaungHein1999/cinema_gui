package com.cinema.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.cinema.database.PgSqlConnectionFactory;
import com.cinema.model.Seat;

public abstract class AbstractDao<T> {

	private PgSqlConnectionFactory connectionFactory;

	public AbstractDao() {
		this.connectionFactory = new PgSqlConnectionFactory();
	}

	public abstract String getTableName();

	public abstract T convertToObject(ResultSet resultSet) throws SQLException;

	public abstract String getInsertValues();

	public abstract void setParameters(PreparedStatement preparedStatement, T entity) throws SQLException;

	public String getInsertQuery() {
		String query = "insert into " + getTableName() + " " + this.getInsertValues();
		return query;
	}

	public T findbyId(int id) {
		String query = "select * from " + getTableName() + " where id = ?";
		
		T object = null;
		try {
			Connection connection = this.connectionFactory.createConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				object = this.convertToObject(resultSet);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			this.connectionFactory.closeConnection();
		}
		return object;
	}

	public List<T> getAll() {
		List<T> objects = new ArrayList<>();
		String query = "select * from " + this.getTableName();
		try {
			Connection connection = this.connectionFactory.createConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				T object = this.convertToObject(resultSet);
				objects.add(object);
			}
		} catch (SQLException e) {
			System.out.print(e.getMessage());
		} finally {
			this.connectionFactory.closeConnection();
		}
		return objects;
	}

	public void create(T entity){
		String query = this.getInsertQuery();
		try {
			Connection connection = this.connectionFactory.createConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			this.setParameters(preparedStatement, entity);
			preparedStatement.executeUpdate();
		} catch(SQLException e){
			System.out.print(e.getMessage());
		}finally {
			this.connectionFactory.closeConnection();
		}
	}

	public void delete(int id){
		try {
		String query = "delete from " + this.getTableName() + " where id = ?";
		Connection connection = this.connectionFactory.createConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, id);
		preparedStatement.executeUpdate();
		} catch(SQLException e) {
			System.out.print(e.getMessage());
		} finally {
			this.connectionFactory.closeConnection();
		}
	}
}
