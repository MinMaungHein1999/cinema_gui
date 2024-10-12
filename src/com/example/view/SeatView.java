package com.example.view;

import java.awt.BorderLayout;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListModel;

import com.cinema.dao.ScheduleDao;
import com.cinema.dao.SeatDao;
import com.cinema.dao.SeatDaoImpl;
import com.cinema.model.Cinema;
import com.cinema.model.Movie;
import com.cinema.model.Schedule;
import com.cinema.model.Seat;
import com.cinema.dao.AbstractDao;

public class SeatView {
	
	private JFrame frame;
	private JScrollPane scrollPane;
	private JTable seatsTable;
	private JButton bookingBtn;
	private AbstractDao<Schedule> scheduleDao;
	private SeatDao seatDao;
	private Schedule schedule;
	private String[] columns = { "id", "name" };
	
	public SeatView(int scheduleId) throws SQLException {
		this.scheduleDao = new ScheduleDao();
		this.seatDao = new SeatDaoImpl();
		this.schedule = this.scheduleDao.findbyId(scheduleId);
		Cinema cinema = schedule.getThreatre().getCinema();
		Movie movie = schedule.getMovie();
		
		this.frame = new JFrame("Available Seats - "+ movie.getTitle() + "("+cinema.getName()+")");
		this.frame.setSize(500, 700);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setLayout(new BorderLayout());
		
		this.seatsTable = new JTable(getSeatsData(), columns);
		this.scrollPane = new JScrollPane(seatsTable);
		this.frame.add(scrollPane, BorderLayout.CENTER);
		
		this.frame.setLocation(300, 100);
		this.frame.setVisible(true);
	}
	
	public String[][] getSeatsData() throws SQLException{
		
		List<Seat> seats = this.seatDao.getAllSeatByTheatre(schedule.getThreatre().getId());
		
		String[][] seatsData = new String[seats.size()][columns.length];
		
		int rowCount = 0;
		for(Seat seat : seats) {
			for(int i =0 ; i < columns.length; i++) {
				seatsData[rowCount][i] = seat.toArray()[i];
			}
			rowCount++;
		}
		
		return seatsData;
	}

}
