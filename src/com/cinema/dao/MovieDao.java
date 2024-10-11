package com.cinema.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cinema.database.PgSqlConnectionFactory;
import com.cinema.model.Movie;

public class MovieDao extends AbstractDao<Movie>{
	
	private PgSqlConnectionFactory connectionFactory;
	
	public MovieDao() {
		this.connectionFactory = new PgSqlConnectionFactory();
	}
	 

	@Override
	public String getTableName() {
		return "movies";
	}

	@Override
	public Movie convertToObject(ResultSet resultSet) throws SQLException {
	
			Movie movie = new Movie();
			movie.setId(resultSet.getInt("id"));
			movie.setTitle(resultSet.getString("title"));
			movie.setDuration(resultSet.getString("duration"));
			return movie;
	}

	@Override
	public String getInsertValues() {
		return "(title, duration) values (?, ?)";
	}

	@Override
	public void setParameters(PreparedStatement preparedStatement, Movie entity) throws SQLException {
		preparedStatement.setString(1, entity.getTitle());
		preparedStatement.setString(2, entity.getDuration());
		
	}

}
