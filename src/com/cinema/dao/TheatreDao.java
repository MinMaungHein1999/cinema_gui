package com.cinema.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cinema.database.PgSqlConnectionFactory;
import com.cinema.model.Cinema;
import com.cinema.model.Seat;
import com.cinema.model.Theatre;

public class TheatreDao extends AbstractDao<Theatre> {
	
	private PgSqlConnectionFactory connectionFactory;
	private AbstractDao<Cinema> cinemaDao;
	
	public TheatreDao() {
		this.connectionFactory = new PgSqlConnectionFactory();
		this.cinemaDao = new CinemaDao();
	}

	@Override
	public String getTableName() {
		return "theatres";
	}

	@Override
	public Theatre convertToObject(ResultSet resultSet) throws SQLException {
			
			Theatre threatre = new Theatre();
			threatre.setId(resultSet.getInt("id"));
			threatre.setName(resultSet.getString("name"));
			int cinema_id = resultSet.getInt("cinema_id");
			threatre.setCinema(this.cinemaDao.findbyId(cinema_id));
			return threatre;
	}
	
	@Override
	public String getInsertValues() {
		return "(name, cinema_id) values (?, ?)";
	}

	@Override
	public void setParameters(PreparedStatement preparedStatement, Theatre entity) throws SQLException {
		preparedStatement.setString(1, entity.getName());
		preparedStatement.setInt(2, entity.getCinema().getId());
	}
}
