package com.cinema.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cinema.database.PgSqlConnectionFactory;
import com.cinema.model.Seat;
import com.cinema.model.Theatre;

public class SeatDao extends AbstractDao<Seat> {
	
	private PgSqlConnectionFactory connectionFactory;
	private AbstractDao<Theatre> theatreDao;
	
	public SeatDao() {
		this.connectionFactory = new PgSqlConnectionFactory();
		this.theatreDao = new TheatreDao();
	}

	@Override
	public String getTableName() {
		return "seats";
	}

	@Override
	public Seat convertToObject(ResultSet resultSet) throws SQLException {
			Seat seat = new Seat();
			seat.setId(resultSet.getInt("id"));
			seat.setTitle(resultSet.getString("name"));
			int theatre_id = resultSet.getInt("theatre_id");
			Theatre theatre = this.theatreDao.findbyId(theatre_id);
			seat.setTheatre(theatre);
			return seat;
	}


	@Override
	public String getInsertValues() {
		return "(name, theatre_id) values (?, ?)";
	}


	@Override
	public void setParameters(PreparedStatement preparedStatement, Seat entity) throws SQLException {
		preparedStatement.setString(1, entity.getTitle());
		preparedStatement.setInt(2, entity.getTheatre().getId());
	}
}
