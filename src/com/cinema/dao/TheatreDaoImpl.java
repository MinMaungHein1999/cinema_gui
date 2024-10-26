package com.cinema.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cinema.database.PgSqlConnectionFactory;
import com.cinema.model.Cinema;
import com.cinema.model.Theatre;

public class TheatreDaoImpl extends TheatreDao {
	
	private PgSqlConnectionFactory connectionFactory;
	private AbstractDao<Cinema> cinemaDao;
	
	public TheatreDaoImpl() {
		this.connectionFactory = new PgSqlConnectionFactory();
		this.cinemaDao = new CinemaDaoImpl();
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

	@Override
	public void setUpdateParameters(PreparedStatement preparedStatement, Theatre entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getUpdateQuery() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Theatre> getTheatresByCinema(int cinemaId) {
		String query = "select * from theatres where cinema_id = ? order by id desc";
		List<Theatre> objects = new ArrayList<>();
		try {
			Connection connection = this.connectionFactory.createConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, cinemaId);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Theatre theatre = this.convertToObject(resultSet);
				objects.add(theatre);
			}
		} catch (SQLException e) {
			System.out.print(e.getMessage());
		} finally {
			this.connectionFactory.closeConnection();
		}
		return objects;
	}
}
