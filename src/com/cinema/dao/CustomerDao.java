package com.cinema.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cinema.database.PgSqlConnectionFactory;
import com.cinema.model.Customer;

public class CustomerDao extends AbstractDao<Customer> {
	
	private PgSqlConnectionFactory connectionFactory;
	
	public CustomerDao() {
		this.connectionFactory = new PgSqlConnectionFactory();
	}

	@Override
	public String getTableName() {
		return "customers";
	}

	@Override
	public Customer convertToObject(ResultSet resultSet) throws SQLException {
			Customer customer = new Customer();
			customer.setId(resultSet.getInt("id"));
			customer.setName(resultSet.getString("name"));
			return customer;
		
	}

	@Override
	public String getInsertValues() {
		return "(name) values (?)";
	}

	@Override
	public void setParameters(PreparedStatement preparedStatement, Customer entity) throws SQLException {
		preparedStatement.setString(1, entity.getName());
	}

}
