package com.cinema.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cinema.database.PgSqlConnectionFactory;
import com.cinema.model.Cinema;

public class CinemaDao extends AbstractDao<Cinema>{
	
	private PgSqlConnectionFactory connectionFactory;
	
	public CinemaDao() {
		this.connectionFactory = new PgSqlConnectionFactory();
	}

	@Override
	public String getTableName() {
		return "cinemas";
	}


	@Override
	public Cinema convertToObject(ResultSet resultSet) throws SQLException {
		Cinema cinema = new Cinema();
		
			cinema.setId(resultSet.getInt("id"));
			cinema.setName(resultSet.getString("name"));
			cinema.setAddress(resultSet.getString("address"));
			return cinema;
	}

	@Override
	public String getInsertValues() {
		return "(name, address) values (?, ?)";
	}

	@Override
	public void setParameters(PreparedStatement preparedStatement, Cinema entity){
		try {
			preparedStatement.setString(1, entity.getName());
			preparedStatement.setString(2, entity.getAddress());
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}

}
