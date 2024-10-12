package com.example.view;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import com.cinema.dao.AbstractDao;
import com.cinema.model.Schedule;
import com.cinema.dao.ScheduleDao;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class BookingPage {
	
	private AbstractDao<Schedule> scheduleDao;
	private JFrame bookingframe;
	private JTable moviesTable;
	private JScrollPane scrollPane;
	private JButton bookingBtn;
	private String[] columns = {"id", "Movie Title", "Cinema Name", "Theatre Name", "Start Time", "End Time", "Public Date", "Duration"};
	
	public BookingPage() {
		System.out.println("calling constructor !!!!");
		this.scheduleDao = new ScheduleDao();
		this.initializeComponents();
		
	}
	
	private void initializeComponents() {
		this.bookingframe = new JFrame("Movie Booking");
		this.bookingframe.setSize(800, 500);
		this.bookingframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.bookingframe.setLayout(new BorderLayout());
		this.moviesTable = new JTable(this.getMoviesData(), this.columns);
		TableColumn column = this.moviesTable.getColumnModel().getColumn(1);
		column.setPreferredWidth(200);
		
		this.scrollPane = new JScrollPane(this.moviesTable);
		
		this.bookingframe.add(this.scrollPane, BorderLayout.CENTER);
		
		this.bookingBtn = new JButton("Select Movie & Book Seat");
		this.bookingframe.add(bookingBtn, BorderLayout.SOUTH);
		selectMovieForBookingAction();
		this.bookingframe.setLocationRelativeTo(null);
		this.bookingframe.setVisible(true);
	}
	
	private String[][] getMoviesData(){
		
		List<Schedule> schedules = this.scheduleDao.getAll();
		String[][] moviesData = new String[schedules.size()][columns.length];
		int rowCount = 0;
		for(Schedule schedule : schedules) {
			for(int i =0 ; i < columns.length; i++) {
				moviesData[rowCount][i] = schedule.toArray()[i];
			}
			rowCount++;
		}
		
		return moviesData;
	}
	
	private void selectMovieForBookingAction() {
		this.bookingBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = moviesTable.getSelectedRow();
				if(selectedRow != -1) {
					int scheduleId = Integer.parseInt(getMoviesData()[selectedRow][0]);
					try {
						SeatView seatView = new SeatView(scheduleId);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(bookingframe, "Please select a movie");
				}
				
			}
			
		});
	}
}
