package com.cinema.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.cinema.model.Customer;
import com.cinema.model.Schedule;
import com.cinema.model.Seat;
import com.cinema.model.Ticket;

public class TicketDao extends AbstractDao<Ticket> {
	
	private AbstractDao<Customer> customerDao;
	private AbstractDao<Seat> seatDao;
	private AbstractDao<Schedule> scheduleDao;
	
	
	public TicketDao() {
		this.customerDao = new CustomerDao();
		this.scheduleDao = new ScheduleDao();
		this.seatDao = new SeatDaoImpl();
	}
	
	@Override
	public String getTableName() {
		return "tickets";
	}

	@Override
	public Ticket convertToObject(ResultSet resultSet) throws SQLException {
		
		int id = resultSet.getInt("id");
		double price = resultSet.getDouble("price");
		int customer_id = resultSet.getInt("customer_id");
		int schedule_id = resultSet.getInt("schedule_id");
		int seat_id = resultSet.getInt("seat_id");
		
		Customer customer = this.customerDao.findbyId(customer_id);
		Schedule schedule = this.scheduleDao.findbyId(schedule_id);
		Seat seat = this.seatDao.findbyId(seat_id);
		
		Ticket ticket = new Ticket();
		ticket.setId(id);
		ticket.setCustomer(customer);
		ticket.setPrice(price);
		ticket.setSeat(seat);
		ticket.setSchedule(schedule);
		
		return ticket;
	}

	@Override
	public String getInsertValues() {
		return "(price, customer_id, seat_id, schedule_id) values (?, ?, ?, ?)";
	}

	@Override
	public void setParameters(PreparedStatement preparedStatement, Ticket entity) throws SQLException {
		preparedStatement.setDouble(1, entity.getPrice());
		preparedStatement.setInt(2, entity.getCustomer().getId());
		preparedStatement.setInt(3, entity.getSeat().getId());
		preparedStatement.setInt(4, entity.getSchedule().getId());
	}

}
