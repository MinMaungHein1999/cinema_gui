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

public class SeatDaoImpl extends SeatDao {

	private PgSqlConnectionFactory connectionFactory;
	private AbstractDao<Theatre> theatreDao;

	public SeatDaoImpl() {
		this.connectionFactory = new PgSqlConnectionFactory();
		this.theatreDao = new TheatreDao();
	}

	@Override
	public List<Seat> getAllSeatByTheatre(int id) {
		List<Seat> seats = new ArrayList<>();
		try {
			String sql = "select * from seats where theatre_id = ?";
			Connection connection = this.connectionFactory.createConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Seat seat = this.convertToObject(resultSet);
				seats.add(seat);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally{
			this.connectionFactory.closeConnection();
		}

		return seats;
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

	@Override
	public void setUpdateParameters(PreparedStatement preparedStatement, Seat entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getUpdateQuery() {
		// TODO Auto-generated method stub
		return null;
	}
}
